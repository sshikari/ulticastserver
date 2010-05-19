package com.ulticast.api.controller

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONException
import org.codehaus.groovy.grails.web.converters.exceptions.ConverterException
import org.springframework.orm.hibernate3.HibernateSystemException
import com.ulticast.domain.*
import java.text.SimpleDateFormat;
import java.util.HashMap
import com.ulticast.api.ApiUtil as U
import com.ulticast.api.EventParserJson
import org.hibernate.TypeMismatchException

class ApiEventController {
	public static final EVENT_TYPE_PASS = "pass";
	public static final EVENT_TYPE_SCORE = "score";
	public static final EVENT_TYPE_TURN = "turn";
	public static final EVENT_TYPE_TIME = "time";
	public static final EVENT_TYPE_CALL = "call";
	
	def apiAuthenticateService
	
	def index = {
	
	}
	
	def save0 = {
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
		}
		
		event.game = game
		event.notes = json.notes
		event.timestamp = U.DATE_FORMATTER.parse(json.timestamp)
		return event
	}
	
	private static Event createPassEvent(json) {
		def event = new PassEvent()
		event.player = Player.get(json.player_id)
		event.team = Team.get(json.team_id)
		return event
	}
	
	private static Event createScoreEvent(json) {
		def event = new ScoreEvent()
		event.team = Team.get(json.team_id)
		event.scorer = Player.get(json.scorer)
		event.assister = Player.get(json.assister)
		event.distance = json.distance
		return event
	}
	
	private static Event createTurnEvent(json) {
		def event = new TurnEvent()
		event.turnType = json.type
		event.team = Team.get(json.team_id)
		event.player = Player.get(json.player_id)
		return event
	}
	
	private static Event createTimeEvent(json) {
		def event = new TimeEvent()
		event.timeType = json.type
		event.team = Team.get(json.team_id)
		return event
	}
	
	private static Event createCallEvent(json) {
		def event = new CallEvent()
		event.callType = json.type
		event.team = Team.get(json.team_id)
		event.isContested = json.is_contested
		event.caller = Player.get(json.caller_id)
		event.fouler = Player.get(json.fouler_id)
		return event
	}
}
