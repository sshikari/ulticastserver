package com.ulticast.domain

class CallEvent extends Event {
	public static final def CALL_TYPE_PICK = 1
	public static final def CALL_TYPE_OFFENSIVE_FOUL = 2
	public static final def CALL_TYPE_DEFENSIVE_FOUL = 3
	public static final def CALL_TYPE_TRAVEL = 4
	public static final def CALL_TYPE_STALL = 5
	public static final def CALL_TYPES = [CALL_TYPE_PICK,
	                                  CALL_TYPE_OFFENSIVE_FOUL,
	                                  CALL_TYPE_DEFENSIVE_FOUL,
	                                  CALL_TYPE_TRAVEL,
	                                  CALL_TYPE_STALL]
    Integer callType
    Team offensiveTeam
    Team defensiveTeam
    Player callerPlayer
    Player foulerPlayer
    Boolean isContested = false
    Integer stallCount
    
    static constraints = {
		callType(inList: CALL_TYPES)
		callerPlayer(nullable:true)
		foulerPlayer(nullable:true)
		stallCount(nullable:true, range:0..9)
    }
}
