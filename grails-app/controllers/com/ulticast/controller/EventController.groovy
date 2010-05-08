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

	
	def saveEvents = {
		println "SAVING EVENTS"
		
		def gameId = params.gameId
		println "game id: " + gameId
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
			println "event :" + event;
			event.timestamp = Date.parse("yyyy-MM-dd hh:mm:ss", event.timestamp)
			println i + " : eventType : " + event.eventType
			println "teamName : " + event.team.name
			
			println "parsing event : " + event
		        EventWrapper eventWrapper = initEventWrapper(event, game)
		        eventWrapper.setup(game)		

		        Event newEvent = eventWrapper.newEvent()			
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

    def initEventWrapper(event, Game game) {
    	def eventType = event.eventType
	println "parsing event type : " + eventType
        if (eventType == WebConstants.EVENT_TYPE_PASS) {    	    	
	   new PassEventWrapper(event)
	} else if (eventType.equals(WebConstants.EVENT_TYPE_SCORE)) {
	   new ScoreEventWrapper(event);
        } else if (eventType == WebConstants.EVENT_TYPE_TURN){
	   new TurnEventWrapper(event);
        } else if (eventType == WebConstants.EVENT_TYPE_CALL){
	   new CallEventWrapper(event);
        } else if (eventType == WebConstants.EVENT_TYPE_TIME) {
	   new TimeEventWrapper(event);
        } else {
	   throw new RuntimeException("Unknown event type : " + eventType);    
	}
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

    abstract class EventWrapper {
       def event
       def homeTeam;
       def awayTeam;

       EventWrapper(e){ event = e }
       
       def homePlayers() { homeTeam.players }
       def awayPlayers() { awayTeam.players }
 
       def setup(Game game) {
          if (game.homeTeam.id == event.team.teamId) {
       	     homeTeam = game.homeTeam;
       	     awayTeam = game.awayTeam;
          } else if (game.awayTeam.id == event.team.teamId) {
       	     homeTeam = game.awayTeam;
       	     awayTeam = game.homeTeam;
          } else {
             throw new RuntimeException("Unknown team for game : " + event.team);
          }
	  event.team = homeTeam;
	  event.game = game;
	  setEventSpecificProperties()
       } 	  
       def findPlayerById = { coll, val ->
	  coll.find{ it.id == val}
       }
       abstract setEventSpecificProperties()
       abstract newEvent()
    }

    class ScoreEventWrapper extends EventWrapper {
    	  ScoreEventWrapper(e){ super(e) }
	  def setEventSpecificProperties() {
	      if (event.player != null) {
	      	  println "players : " + homeTeam.players + ", looking for player : " + event.player
		  event.player = findPlayerById(homeTeam.players, event.player.id)				
	      }
	      if (event.assister != null) {
		  event.assister = findPlayerById(homeTeam.players, event.assister.id)
	      }
	  }
	  def newEvent() {new ScoreEvent(event)}
    }
    class PassEventWrapper extends EventWrapper {
    	  PassEventWrapper(e){ super(e) }
	  def setEventSpecificProperties() {
	      if (event.player != null) {
	      	  event.player = findPlayerById(homeTeam.players, event.player.id)
              }
	  }
	  def newEvent() {new PassEvent(event)}
    }
    class TurnEventWrapper extends EventWrapper {
    	  TurnEventWrapper(e){ super(e) }
	  def setEventSpecificProperties() {
	      if (event.player != null) {
	      	  event.player = findPlayerById(homeTeam.players, event.player.id)
              }
	  }
	  def newEvent() {new TurnEvent(event)}
    }
    class CallEventWrapper extends EventWrapper {
    	  CallEventWrapper(e){ super(e) }
	  def setEventSpecificProperties() {
	      if (event.fouler != null) {							
		  event.fouler = findPlayerById(awayTeam.players, event.fouler.id)								
	      }
	      if (event.caller != null) {
		  event.caller = findPlayerById(homeTeam.players, event.caller.id)									            }				    
	  }
	  def newEvent() {new CallEvent(event)}
    }
    class TimeEventWrapper extends EventWrapper {
    	  TimeEventWrapper(e){ super(e) }
	  def setEventSpecificProperties() {
	  }
	  def newEvent() {new TimeEvent(event)}
    }



