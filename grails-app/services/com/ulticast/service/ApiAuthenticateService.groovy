package com.ulticast.service

import com.ulticast.domain.AuthRole;
import com.ulticast.domain.AuthToken;
import com.ulticast.domain.AuthUser;

class ApiAuthenticateService {
	
	boolean transactional = true
	
	def authenticateService
	
	/**
	 * Verifies that the user exists for the supplied username, password and 
	 * AuthRole.
	 * 
	 * @param username
	 * @param password
	 * @param role
	 * @return
	 */
	public AuthUser validateCredentials(String username, String password, AuthRole role) {
		return validateCredentials(username,password,role.authority)
	}
	
	/**
	 * Verified that the user exists for the supplied username, password
	 * and AuthRole.authority String.
	 * @param username
	 * @param password
	 * @param authority
	 * @return
	 */
	public AuthUser validateCredentials(String username, String password, String role) {
		def c = AuthUser.createCriteria()
		def passwd = authenticateService.encodePassword(password)
		def user = c {
			and {
				eq("username", username)
				eq("passwd",passwd)
				eq("enabled", true)
				authorities { eq("authority", role) }
			}
		}
		user.size() > 0 ? user.get(0) : null	
	}
	
	/**
	 * Returns an AuthToken as long as the supplied username, password and role 
	 * are valid. 
	 * @param username
	 * @param password
	 * @param role
	 * @return
	 */
	public AuthToken getToken(String username, String password, String role) {
		def user = validateCredentials(username, password, role)
		if (user) {
			def token = new AuthToken(token: generateTokenString(), 
					user:user, role:AuthRole.findByAuthority(role), isActive: true)
			if (token.save(flush:true)) 
				return token
		} 
		return null
	}
	
	private String generateTokenString() {
		UUID.randomUUID().toString();	
	}	
	
	/**
	 * Returns an AuthToken if the supplied tokenString is valid and null
	 * otherwise. 
	 * @param tokenString
	 * @return
	 */
	public AuthToken validateToken(String tokenString) {
		if (!tokenString)
			return null
			
		AuthToken token = AuthToken.findByToken(tokenString)
		
		if (token && token.isActive) {
			if (token.expirationDate && token.expirationDate < new Date()) {
				token.isActive = false
				token.save(flush: true)
				return null
			} else {
				return token
			}
		}
		return null
	}
	
	/** Returns an AuthUser if the supplied tokenString is valid
	 * for the supplied role.
	 * 
	 * @param tokenString
	 * @param role
	 * @return
	 */
	public AuthUser validateToken(String tokenString, String role) {
		def token = validateToken(tokenString)
		if (token && token.role.authority.equals(role)) {
			return token.user
		} 
		return null 
	}
	
	/**
	 * Invalidates a token. 
	 * 
	 * @param token
	 */
	public void invalidateToken(AuthToken token) {
		token.isActive = false
		token.save(flush:true)
	}
	
	public void invalidateToken(String tokenString) {
		AuthToken token = AuthToken.findByToken(tokenString)
		if (token) 
			invalidateToken(token)
	}
	
	
}
