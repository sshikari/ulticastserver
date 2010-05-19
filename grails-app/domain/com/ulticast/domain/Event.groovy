package com.ulticast.domain

class Event {
	Game game
	String notes
	Date timestamp
	Date lastUpdated
	Date dateCreated
	
	static mapping = {
		tablePerHierarchy false
		autoTimestamp true
	}
	
	static constraints = {
		notes(nullable:true)
		lastUpdated(nullable:true)
	}

}
