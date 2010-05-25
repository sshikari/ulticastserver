package com.ulticast.domain

class TurnEvent extends Event{
	public static final def TURN_TYPE_THROW_AWAY = 1
	public static final def TURN_TYPE_DROP = 2
	public static final def TURN_TYPE_TEAM_D = 3
	public static final def TURN_TYPE_D_BLOCK = 4
	public static final def TURN_TYPE_STALL = 5
	public static final def TURN_TYPES = [TURN_TYPE_THROW_AWAY,
	                                      TURN_TYPE_DROP,
	                                      TURN_TYPE_TEAM_D,
	                                      TURN_TYPE_D_BLOCK,
	                                      TURN_TYPE_STALL]
    Integer turnType
    Team newOffenseTeam
    Team newDefenseTeam
    Player hadPossessionPlayer
    Player madeDefensePlayer
    Integer stallCount
    
    static constraints = {
		turnType(inList: TURN_TYPES)
    	hadPossessionPlayer(nullable:true)
    	madeDefensePlayer(nullable:true)
    	stallCount(nullable:true, range:0..9)
    }
}
