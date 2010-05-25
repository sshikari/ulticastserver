package com.ulticast.domain

class TimeEvent extends Event {
	public static final def TIME_TYPE_START = 1
	public static final def TIME_TYPE_END = 2
	public static final def TIME_TYPE_TIMEOUT = 3
	public static final def TIME_TYPE_HALFTIME = 4
	public static final def TIME_TYPE_INJURY = 5
	public static final def TIME_TYPES = [TIME_TYPE_START,
	                                      TIME_TYPE_END,
	                                      TIME_TYPE_TIMEOUT,
	                                      TIME_TYPE_HALFTIME,
	                                      TIME_TYPE_INJURY]
    
    Integer timeType
	Team callTeam
    Player injuredPlayer
    Player subInPlayer
    Player otherTeamSubInPlayer
    Player otherTeamSubOutPlayer
    
    static constraints = {
	    timeType(inList:TIME_TYPES)
		callTeam(nullable:true)
		injuredPlayer(nullable:true)
		subInPlayer(nullable:true)
		otherTeamSubInPlayer(nullable:true)
		otherTeamSubOutPlayer(nullable:true)
    }
}
