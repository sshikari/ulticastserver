package com.ulticast.domain

import java.util.Date;

class Team {
	AuthUser owner           
	String teamName
	Boolean isOwnerTeam
	Boolean isActive = true
	Date dateCreated
	Date lastUpdated
	
	static hasMany = [ players : Player ]
	
	static mapping = {
		autoTimestamp true
	}
	
	static constraints = {
		teamName(nullable:false, blank:false)
		lastUpdated(nullable:true)
	}
}
