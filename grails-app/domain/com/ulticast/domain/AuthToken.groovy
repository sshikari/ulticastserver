package com.ulticast.domain

class AuthToken {
	String token
	AuthUser user
	AuthRole role
	Date expirationDate
	boolean isActive 
	Date dateCreated
	Date lastUpdated

	static mapping = {
		autoTimestamp true
	}
	
    static constraints = {
		token(nullable: false, unique: true)
		user(nullable: false)
		role(nullable: false)
		lastUpdated(nullable: true)
		expirationDate(nullable: true)
		isActive(nullable: false)
    }
}
