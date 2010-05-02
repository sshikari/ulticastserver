package com.ulticast.controller;

import com.ulticast.domain.*
import grails.converters.*
import com.ulticast.util.*

class EventController {
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def index = {
		redirect(action: "list", params: params)
	}
	
	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[eventInstanceList: Event.list(params), eventInstanceTotal: Event.count()]
	}
	
	def create = {
		def eventInstance = new Event()
		eventInstance.properties = params
		return [eventInstance: eventInstance]
	}
	
	def gameFeed = {
		params.max = 50  // pagination size
		
		def gameId = params.gameId
		if (gameId == null) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.gameId])}"
			[eventInstanceList: [], eventInstanceTotal: 0]			
		} else {Game game = Game.get(gameId)
			def events = Event.findAll("from Event as e where e.game.id=?", [gameId.toLong()])		
			[eventInstanceList: events, eventInstanceTotal: events.size()]			
		}			
	}

    def pEvent (event, Game game, Team homeTeam, Team awayTeam){				
		println event;
		def homePlayers = homeTeam.players
		def awayPlayers = awayTeam.players
		
		Team team = null		
		if (homeTeam.teamName.equalsIgnoreCase(event.team.teamName)) {
			team = homeTeam;   
		} else if (awayTeam.teamName.equalsIgnoreCase(event.team.teamName)) {
			team = awayTeam;      
		} else {
			println "Unknown team for game : " + event.team.teamName;
			// error
		}
		
		event.team = team		
		
		Event newEvent = null				
		String eventType = event.eventType;
		
		def findPlayer = { coll, val ->
			coll.find{ it.name == val}
		}
		
		if (eventType == WebConstants.EVENT_TYPE_PASS) {	       
			newEvent = new PassEvent(event)
		} else if (eventType.equals(WebConstants.EVENT_TYPE_SCORE)) {
			if (event.player != null) {							
				event.player = findPlayer(team.players, event.player)				
			}
			if (event.assister != null) {
				event.assister = findPlayer(team.players, event.assister)
			}
			
			newEvent = new ScoreEvent(event)			
		} else if (eventType == WebConstants.EVENT_TYPE_TURN){
			if (event.player != null) {							
				event.player = findPlayer(team.players, event.player)				
			}		
			newEvent = new TurnEvent(event)
		} else if (eventType == WebConstants.EVENT_TYPE_CALL){
			if (event.fouler != null) {							
				event.fouler = findPlayer(awayTeam.players, event.fouler)								
			}
			if (event.caller != null) {
				event.caller = findPlayer(homeTeam.players, event.caller)												
			}				    
			newEvent = new CallEvent(event)
		} else if (eventType == WebConstants.EVENT_TYPE_TIME) {
			newEvent = new TimeEvent(event);
		}
		newEvent.game = game;
		return newEvent
		
	}
	
	def saveEvents = {
		println "SAVING EVENTS"
		
		def gameId = params.gameId
		println gameId
		Game game = Game.findById(gameId);
		println "Found game : " + game
		Team homeTeam = game.homeTeam
		Team awayTeam = game.awayTeam
		
		println "Game :" + homeTeam.teamName + " vs " + awayTeam.teamName
		
		def eventList = params.eventList
		def jsonEventList = JSON.parse(eventList)
		int i = 0;
		for (event in jsonEventList) {
			// parse some fields
			event.timestamp = Date.parse("yyyy-MM-dd hh:mm:ss", event.timestamp)
			println i + " : eventType : " + event.eventType
			println "teamName : " + event.team.teamName
			
			Event newEvent = pEvent(event, game, homeTeam, awayTeam);
			
			println "SAVING OBJECT"
			if(newEvent.save(flush: true)) {
				println "SAVED"
			} else {
				println "Error saving: "
				newEvent.errors.each {
					println it
				}
			} 
			
			i = i+1;			
		} // end for
		render jsonEventList
	}
	
	def save = {
		def eventInstance = new Event(params)
		if (eventInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])}"
			redirect(action: "show", id: eventInstance.id)
		}
		else {
			render(view: "create", model: [eventInstance: eventInstance])
		}
	}
	
	def show = {
		def eventInstance = Event.get(params.id)
		if (!eventInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
			redirect(action: "list")
		}
		else {
			[eventInstance: eventInstance]
		}
	}
	
	def edit = {
		def eventInstance = Event.get(params.id)
		if (!eventInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [eventInstance: eventInstance]
		}
	}
	
	def update = {
		def eventInstance = Event.get(params.id)
		if (eventInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (eventInstance.version > version) {
					
					eventInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'event.label', default: 'Event')] as Object[], "Another user has updated this Event while you were editing")
					render(view: "edit", model: [eventInstance: eventInstance])
					return
				}
			}
			eventInstance.properties = params
			if (!eventInstance.hasErrors() && eventInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])}"
				redirect(action: "show", id: eventInstance.id)
			}
			else {
				render(view: "edit", model: [eventInstance: eventInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
			redirect(action: "list")
		}
	}
	
	def delete = {
		def eventInstance = Event.get(params.id)
		if (eventInstance) {
			try {
				eventInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
			redirect(action: "list")
		}
	}
}
