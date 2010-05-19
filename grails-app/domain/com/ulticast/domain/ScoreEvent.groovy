package com.ulticast.domain

class ScoreEvent extends Event {
	public static final Integer DISTANCE_CLOSE = 1
	public static final Integer DISTANCE_MID = 2
	public static final Integer DISTANCE_FAR = 3
	
	Team team
	Player scorer
	Player assister
	Integer distance
	
	static constraints = {
		scorer(nullable:true)
		assister(nullable:true)
		distance(nullable:true)
	}
}
