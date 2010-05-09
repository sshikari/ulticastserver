package com.ulticast.controller.api
import grails.converters.JSON
import com.ulticast.domain.*
import java.util.HashMap

class ApiController {
	protected static HEADER_ERROR = "error"
	protected static HEADER_OK = "ok"
	
	def authenticateService
	
	def index = { 	
		
	}
	
	def login = {
		AuthToken token = authenticateService.getToken(params.username, params.password, "ROLE_MOBILE") 
		if (!token) {
			 render getAuthenticationError() as JSON 
			 return 
		} 
		
		render wrapResponse(token, true) as JSON
	}
	
	def logout = { 
		if (!params.token) {
			render wrapResponse(getErrorMap("token must be supplied"), false) as JSON
			return
		} 		
		authenticateService.invalidateToken(params.token)
		render wrapResponse([], true) as JSON 
	}
	
	private AuthUser getUser(String username, String password) {
		authenticateService.validateCredentials(username,password,"ROLE_MOBILE")
	}
	
	protected static HashMap getAuthenticationError() {
		wrapResponse(getErrorMap("Invalid username or password."), false)
	}
	
	protected static HashMap getErrorMap(String error) {
		def errors = [error]
		[errors: errors]
	}
	
	protected static HashMap getStatusOk() {
		[status: HEADER_OK]
	}
	
	protected static HashMap getStatusError() {
		[status: HEADER_ERROR]
	}	
	
	/**
	 * Wraps response with header and body.
	 * 
	 * @param contents
	 * @param error
	 * @return
	 */
	protected static def wrapResponse(contents, isSuccess) {
		def output = [:]
		isSuccess ? output.put("header", getStatusOk()) : output.put("header", getStatusError())
		output.put("body", contents)
		return output 
	}
}
