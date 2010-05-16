package com.ulticast.api

import java.text.SimpleDateFormat
import java.util.HashMap

import com.ulticast.domain.AuthUser
import com.ulticast.domain.AuthToken

class ApiUtil {
	public static  SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z")
	protected static HEADER_ERROR = "error"
	protected static HEADER_OK = "ok"
	
	protected static final Integer ERR_CODE_UNKNOWN = 1
	protected static final Integer ERR_CODE_SAVING = 2
	protected static final Integer ERR_CODE_INVALID_TOKEN = 3
	protected static final Integer ERR_CODE_EXPIRED_TOKEN = 4
	protected static final Integer ERR_CODE_INVALID_LOGIN_PASS = 5
	protected static final Integer ERR_CODE_NO_PERM = 5
	protected static final Integer ERR_CODE_NEWER_ON_SERVER = 6
	protected static final Integer ERR_CODE_MISSING_PARAM = 7

	
//	def static apiAuthenticateService
	
//	public static AuthToken getToken(String username, String password) {
//		apiAuthenticateService.getToken(username, password, "ROLE_MOBILE") 
//	}
//	
//	private static AuthUser getUser(String username, String password) {
//		apiAuthenticateService.validateCredentials(username,password,"ROLE_MOBILE")
//	}
		

	
	protected static HashMap wrapInvalidTokenError() {
		wrapError("The supplied token is either invalid or expired.",ERR_CODE_INVALID_TOKEN)
	}
	
	protected static HashMap getErrorMap(String error) {
		def errors = [error]
		[errors: errors]
	}
	
	protected static HashMap getStatusOk() {
		[status: HEADER_OK]
	}
	
	protected static HashMap getStatusError(def code) {
		def map = [:]
		map.status = HEADER_ERROR
		if (code)
			map.code = code
		return map
	}	
	
	/**
	 * Wraps response with header and body.
	 * 
	 * @param contents
	 * @param error
	 * @return
	 */
	protected static def wrapResponse(contents, isSuccess, errorCode) {
		def output = [:]
		isSuccess ? output.put("header", getStatusOk()) : output.put("header", getStatusError(errorCode))
		output.put("body", contents)
		return output 
	}
	
	protected static def wrapResponse(contents, isSuccess) {
		wrapResponse(contents, isSuccess, null)
	}
	
	protected static def wrapError(error, errorCode) {
		wrapResponse(getErrorMap(error), false, errorCode)
	}
	
	
}
