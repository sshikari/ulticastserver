package com.ulticast.api.controller

import grails.converters.JSON
import com.ulticast.domain.*
import java.text.SimpleDateFormat;
import java.util.HashMap
import com.ulticast.api.ApiUtil as U

class ApiTeamController {
	
	def apiAuthenticateService
	
	def overview = {
		AuthToken token = apiAuthenticateService.validateToken(params.token)
		
		if (!token) {
			render U.wrapInvalidTokenError() as JSON
			return
		}
		
		def c = Team.createCriteria()
		def teams = c {
			eq("owner", token.user)
			eq("isActive", true)
		}
		
		def teamOverviews = []
		
		teams.each() {
			def team = [:]
			team.id = it.id
			team.team_name = it.teamName
			team.is_owner_team = it.isOwnerTeam
			team.last_updated = it.lastUpdated ? U.DATE_FORMATTER.format(it.lastUpdated) : it.lastUpdated
			teamOverviews.add(team)
		}
		
		render U.wrapResponse([teams:teamOverviews], true) as JSON
	}
	
	def all = {
		AuthToken token = apiAuthenticateService.validateToken(params.token)
		if (!token) {
			render U.wrapInvalidTokenError() as JSON
			return
		}
		
		def c = Team.createCriteria()
		def teams = c {
			eq("owner", token.user)
			eq("isActive", true)
		}
		
		render U.wrapResponse([teams:teams], true) as JSON
	}
	
	def detail = {
		AuthToken token = apiAuthenticateService.validateToken(params.token)
		if (!token) {
			render U.wrapInvalidTokenError() as JSON
			return
		}
		
		def teamId = params.long("id")
		
		if (!teamId) {
			render U.wrapError("id is required.", U.ERR_CODE_MISSING_PARAM) as JSON
			return
		}
		
		def team = Team.findByIdAndIsActive(teamId, true)
		
		if (team && team.owner != token.user) {
			render U.wrapError("You do not have permission to view this team.",
					U.ERR_CODE_NO_PERM) as JSON
			return
		}
		
		render U.wrapResponse([team:team], true) as JSON
	}
	
	def update0 = {
		AuthToken token = apiAuthenticateService.validateToken(params.token)
		if (!token) {
			render U.wrapInvalidTokenError() as JSON
			return
		}
		def teamId = params.long("id")	
		
		if (!teamId) {
			render U.wrapError("id is required.", U.ERR_CODE_MISSING_PARAM) as JSON
			return
		}
		
		def team = Team.findByIdAndIsActive(teamId, true)
		
		if (team && team.owner != token.user) {
			render U.wrapError("You do not have permission to update this team.",
					U.ERR_CODE_NO_PERM) as JSON
			return
		}
		
		if (team.version > params.long("version") && !params.overwrite) {
			render U.wrapError("Error, newer version on server. " +
					"Use overwrite param to overwrite", U.ERR_CODE_NEWER_ON_SERVER) as JSON
			return
		}
		
		team.teamName = params.name
		team.isOwnerTeam = params.is_my_team
		
		if (!params.no_player_update) {
			def players = []
			def jsonPlayers = JSON.parse(params.players)		
			
			jsonPlayers.each() {
				Player p = new Player(id:it.id,
				nickname:it.nickname,
				firstName:it.first_name,
				lastName:it.last_name,
				number:it.number)
				players.add(p)
			}
			team.players = players
		}
		
		if (team.save(flush:true)) {
			def returnMap = [:]
			returnMap.id = team.id
			returnMap.version= team.version
			returnMap.name = team.teamName
			render U.wrapResponse([team:returnMap],true) as JSON
		} else {
			render U.wrapError("Unknown error. Could not save.", U.ERR_CODE_SAVING) as JSON
		}
	}
	
	def new0 = {
		AuthToken token = apiAuthenticateService.validateToken(params.token)
		if (!token) {
			render U.getInvalidTokenError() as JSON
			return
		}
	
		def players = []
		if (params.players) {
			def jsonPlayers = JSON.parse(params.players)		
			
			jsonPlayers.each() {
				Player p = new Player(id:it.id,
				nickname:it.nickname,
				firstName:it.first_name,
				lastName:it.last_name,
				number:it.number)
				players.add(p)
			}
		}
		
		def team = new Team(owner:token.user, 
			teamName:params.name,
			isOwnerTeam:params.is_my_team,
			players:players)
		
		if (team.save(flush:true)) {
			def returnMap = [:]
			returnMap.id = team.id
			returnMap.version = team.version
			render U.wrapRespnse([team:returnMap], true) as JSON
		} else {
			render U.wrapResponse(team.errors, false, U.ERR_CODE_SAVING) as JSON
		}
	}
	
	def delete0 = {
		AuthToken token = apiAuthenticateService.validateToken(params.token)
		if (!token) {
			render U.wrapInvalidTokenError() as JSON
			return
		}
		def teamId = params.long("id")	
		
		if (!teamId) {
			render U.wrapError("id is required.",U.ERR_CODE_MISSING_PARAM) as JSON
			return
		}
		
		def team = Team.get(teamId)
		
		if (team && team.owner != token.user) {
			render U.wrapError("You do not have permission to delete this team.",
					U.ERR_CODE_NO_PERM) as JSON
			return
		}		
		
		team.isActive = false
		
		if (team.save(flush:true)) {
			render U.wrapResponse([message:"Team successfully deleted"], true) as JSON
		} else {
			render U.wrapError("Error. Could not delete.",U.ERR_CODE_UNKNOWN) as JSON
		}
	}
	
}
