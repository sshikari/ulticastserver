package com.ulticast.domain

class PassEvent extends Event {
	Team offenseTeam
	Player throwPlayer
	Player receivePlayer
    Integer stallCount
    Integer passCount
    
    static constraints = {
		throwPlayer(nullable:true)
		receivePlayer(nullable:true)
		stallCount(nullable:true, range:0..9)
    }
}
