package com.ulticast.api.controller

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONException
import org.codehaus.groovy.grails.web.converters.exceptions.ConverterException
import org.springframework.orm.hibernate3.HibernateSystemException
import com.ulticast.domain.*
import java.text.SimpleDateFormat;
import java.util.HashMap
import com.ulticast.api.ApiUtil as U
import org.hibernate.TypeMismatchException

class ApiEventController {
	public static final def EVENT_TYPE_PASS = "pass"
	public static final def EVENT_TYPE_SCORE = "score"
	public static final def EVENT_TYPE_TURN = "turn"
	public static final def EVENT_TYPE_TIME = "time"
	public static final def EVENT_TYPE_CALL = "call"
	public static final def EVENT_TYPE_PULL = "pull"
	
	def apiAuthenticateService
	
	def index = {
	
	}
	
	def new0 = {
		AuthToken token = apiAuthenticateService.validateToken(params.token)
		
		if (!token) {
			render U.wrapInvalidTokenError() as JSON
			return
		}    
		
		def gameId = params.long("game_id")
		def game
		
		if (gameId) 
			game = Game.findByIdAndOwner(gameId, token.user)
		
		if (!game) {
			render U.wrapError("Invalid or missing game_id", 
					U.ERR_CODE_MISSING_PARAM) as JSON
			return
		}
		
		def jsonEvents
		
		try {
			jsonEvents = JSON.parse(params.events)
		} catch (NullPointerException e) {
			render U.wrapError("events param is required.", 
					U.ERR_CODE_MISSING_PARAM) as JSON
			return			
		} catch (ConverterException e) {
			render U.wrapError("Invalid events param.",
					U.ERR_CODE_INVALID_PARAM) as JSON
			return
		}
		
		def events = []
		def errors = []
		              
		jsonEvents.each() {
			try {
				def event = parseEvent(game,it)
				events.add(event)
				
				if(!event.save(flush:true)) {
					event.errors.each() {
						errors.add(it)
					}
				}
			} catch (HibernateSystemException e) {
				render U.wrapError("Invalid events param.",
						U.ERR_CODE_INVALID_PARAM) as JSON
				return
			}
		}
		
		if (errors.size() > 0) {
			render U.wrapResponse(errors, U.ERR_CODE_UNKNOWN) as JSON	
		} else {
			render U.wrapResponse(total_saved: events.size(), true) as JSON
		}
	}
	
	
	private static Event parseEvent(game, json) {
		def event
		def eventType = json.event_type
		
		//need to handle case where we cant find event type
		switch(eventType) {
			case EVENT_TYPE_PASS:
				event = createPassEvent(json)
				break
			case EVENT_TYPE_SCORE:
				event = createScoreEvent(json)
				break
			case EVENT_TYPE_TURN:
				event = createTurnEvent(json)
				break
			case EVENT_TYPE_TIME:
				event = createTimeEvent(json)
				break
			case EVENT_TYPE_CALL:
				event = createCallEvent(json)
				break
			case EVENT_TYPE_PULL:
				event = createPullEvent(json)
				break
		}
		
		event.game = game
		event.notes = json.notes
		event.timestamp = U.DATE_FORMATTER.parse(json.timestamp)
		return event
	}
	
	private static Event createPassEvent(json) {
		def event = new PassEvent()
		event.offenseTeam = Team.get(json.offense_team)
		event.defenseTeam = Team.get(json.defense_team)
		event.throwPlayer = Player.get(json.throw_player)
		event.receivePlayer = Player.get(json.receive_player)
		event.stallCount = json.stall_count
		event.passCount= json.pass_count
		return event
	}
	
	private static Event createScoreEvent(json) {
		def event = new ScoreEvent()
		event.scorerTeam = Team.get(json.scorer_team)
		event.scorerPlayer = Player.get(json.scorer_player)
		event.assisterPlayer = Player.get(json.assister_player)
		event.distance = json.distance
		event.passCount = json.pass_count
		event.stallCount = json.stall_count
		return event
	}
	
	private static Event createTurnEvent(json) {
		def event = new TurnEvent()
		event.turnType = json.turn_type
		event.newOffenseTeam = Team.get(json.new_offense_team)
		event.newDefenseTeam = Team.get(json.new_defense_team)
		event.hadPossessionPlayer = Player.get(json.had_possession_player)
		event.madeDefensePlayer = Player.get(json.made_defense_player)
		event.stallCount = json.stall_count
		return event
	}
	
	private static Event createTimeEvent(json) {
		def event = new TimeEvent()
		event.timeType = json.time_type
		event.callTeam = Team.get(json.call_team)
		event.injuredPlayer = Player.get(json.injured_player)
		event.subInPlayer = Player.get(json.sub_in_player)
		event.otherTeamSubInPlayer = Player.get(json.other_team_sub_in_player)
		event.otherTeamSubOutPlayer = Player.get(json.other_team_sub_out_player)
		return event
	}
	
	private static Event createCallEvent(json) {
		def event = new CallEvent()
		event.callType = json.call_type
		event.offenseTeam = Team.get(json.offense_team)
		event.defenseTeam = Team.get(json.defense_team)
		event.callerPlayer = Player.get(json.caller_player)
		event.foulerPlayer = Player.get(json.fouler_player)
		event.isContested = json.is_contested
		event.stallCount = json.stall_count
		return event
	}
	
	private static Event createPullEvent(json) {
		def event = new PullEvent()
		event.pullTeam = Team.get(json.pull_team)
		event.receiveTeam = Team.get(json.receive_team)
		event.pullPlayer = Player.get(json.pull_player)
		event.receivePlayer = Player.get(json.recieve_player)
		
		//TODO need to handle lineups!!!
		return event
	}
}
