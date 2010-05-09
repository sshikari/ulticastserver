package com.ulticast.domain

class AuthToken {
	String token
	AuthUser user
	AuthRole role
	Date creationDate
	Date expirationDate
	boolean isActive 
	
    static constraints = {
		token(nullable: false, unique: true)
		user(nullable: false)
		role(nullable: false)
		creationDate(nullabke: false)
		expirationDate(nullable: true)
		isActive(nullable: false)
    }
}
