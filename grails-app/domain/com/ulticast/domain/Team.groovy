package com.ulticast.domain

import java.util.Date;

class Team {
	AuthUser owner           
	String teamName
	Boolean isOwnerTeam
	Boolean isActive = true
	Date dateCreated
	Date lastUpdated
	
	static hasMany = [ players : com.ulticast.domain.Player ]
	
	static mapping = {
		autoTimestamp true
	}
	
	static constraints = {
		teamName(nullable: false)
		lastUpdated(nullable:true)
	}
}
