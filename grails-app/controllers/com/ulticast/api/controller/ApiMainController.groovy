package com.ulticast.api.controller

import grails.converters.JSON;
import com.ulticast.domain.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import com.ulticast.api.ApiUtil as U;

class ApiMainController {
	
	def apiAuthenticateService
	
	def index = { 	
		
	}
	
	def login = {
		AuthToken token = apiAuthenticateService.getToken(params.username, params.password, "ROLE_MOBILE") 
		
		if (!token) {
			render U.wrapError("Invalid username or password.", U.ERR_CODE_INVALID_LOGIN_PASS) as JSON 
			return 
		} 
		
		render U.wrapResponse(token, true) as JSON
	}
	
	def logout = {
		if (!params.token) {
			render U.wrapError("token must be supplied", U.ERR_CODE_MISSING_PARAM) as JSON
			return
		} 		
		apiAuthenticateService.invalidateToken(params.token)
		render U.wrapResponse([message: "Successfully logged out."], true) as JSON 
	}
}
