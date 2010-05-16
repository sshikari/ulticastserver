package com.ulticast.api.controller;

import grails.test.*
import grails.converters.JSON
import com.ulticast.domain.*
import com.ulticast.api.ApiUtil
import com.ulticast.service.ApiAuthenticateService

class ApiTeamControllerTests extends ControllerUnitTestCase {
	AuthToken token
	
	protected void setUp() {
		super.setUp()
		AuthUser userBlake = new AuthUser(authorities:[],
				username:"blake", userRealName:"Blake Barnes", 
				passwd:"blake",
				enabled:true, email:"blake@blake.com", emailShow:false).save()
		
		AuthRole mobileRole = new AuthRole(people:[userBlake], authority:"ROLE_MOBILE", description: "Mobile user").save()
		token = new AuthToken(token:"test",user:userBlake,role:mobileRole, isActive:true)
		
		//need to mock authentication service?
		
		// ULTICAST
		Player bill = new Player(nickname:"Bill", number:7)
		Player hill = new Player(nickname:"Hillary", number:8)
		Player rich = new Player(nickname:"Rich", number:10)
		Player blake = new Player(nickname:"Blake", number:11)
		
		Team harvard = new Team(owner:userBlake, isOwnerTeam:false, teamName:"Harvard")
		harvard.addToPlayers(bill)
				.addToPlayers(hill)
				.addToPlayers(new Player(nickname:"H3", number:3))
				.addToPlayers(new Player(nickname:"H4", number:4))
				.addToPlayers(new Player(nickname:"H5", number:5))
				.addToPlayers(new Player(nickname:"H6", number:6))
				.addToPlayers(new Player(nickname:"H7", number:7))
				.save()
		
		Team tufts = new Team(owner:userBlake, isOwnerTeam:true, teamName:"Tufts")
		tufts.addToPlayers(rich)	
				.addToPlayers(blake)
				.addToPlayers(new Player(nickname:"T3", number:3))
				.addToPlayers(new Player(nickname:"T4", number:4))
				.addToPlayers(new Player(nickname:"T5", number:5))
				.addToPlayers(new Player(nickname:"T6", number:6))
				.addToPlayers(new Player(nickname:"T7", number:7))
				.save()	
	}
	
	protected void tearDown() {
		super.tearDown()
		token = null
	}
	
	void testOverviewSucess() {
		ApiTeamController.metaClass.getParams = {-> [token:"test"]}
		def controller = new ApiTeamController()
		def authControl
		authControl = mockFor(ApiAuthenticateService)
		authControl.demand.validateToken(1..1) {String arg1 -> return token}
		controller.apiAuthenticateService = authControl.createMock()
		controller.overview()
		def json = JSON.parse(controller.getResponse().getContentAsString())
		assertEquals ApiUtil.HEADER_OK, json.header.status 
		assertEquals json.body.teams.size(), 2
	}
	
	void testOverviewFailure() {
		ApiTeamController.metaClass.getParams = {-> [token:"test"]}
		def controller = new ApiTeamController()
		def authControl
		authControl = mockFor(ApiAuthenticateService)
		authControl.demand.validateToken(1..1) {String arg1 -> return null}
		controller.apiAuthenticateService = authControl.createMock()
		controller.overview()
		def json = JSON.parse(controller.getResponse().getContentAsString())
		assertEquals ApiUtil.HEADER_ERROR, json.header.status 
	}
	
	
}
