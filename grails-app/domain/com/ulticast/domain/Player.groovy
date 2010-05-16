package com.ulticast.domain

import java.util.Date;

class Player {
	String nickname
	String firstName
	String lastName	
	Integer number 
	Date lastUpdated
	Date dateCreated
	Boolean isActive = true
	
	static belongsTo = Team
	static hasMany = [teams: Team]
	
	static fetchMode = [teams:'eager']
	
	static mapping = {
		autoTimestamp true
	}
	
	static constraints = {
		nickname(nullable: false)
		firstName(nullable:true)
		lastName(nullable:true)	
		number(nullable:true)
		lastUpdated(nullable:true)
	}
}
