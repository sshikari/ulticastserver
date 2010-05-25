package com.ulticast.domain

class ScoreEvent extends Event {
	public static final def DISTANCE_ENDZONE = 1
	public static final def DISTANCE_BRICK = 2
	public static final def DISTANCE_MIDFIELD = 3
	public static final def DISTANCE_HUCK = 4
	public static final def DISTANCE_SICK = 5
	
	public static final def DISTANCES = [DISTANCE_ENDZONE,
	                                     DISTANCE_BRICK,
	                                     DISTANCE_MIDFIELD,
	                                     DISTANCE_HUCK,
	                                     DISTANCE_SICK]
	Team scorerTeam
	Player scorerPlayer
	Player assisterPlayer
	Integer distance
	Integer passCount
	Integer stallCount
	
	static constraints = {
		scorerPlayer(nullable:true)
		assisterPlayer(nullable:true)
		distance(nullable:true,inList:DISTANCES)
		stallCount(nullable:true,range:0..9)
	}
}
