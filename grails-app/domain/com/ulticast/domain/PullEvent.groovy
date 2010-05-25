package com.ulticast.domain

class PullEvent extends Event {

	Team pullTeam
	Team receiveTeam
	Player pullPlayer
	Player receivePlayer
	
	static hasMany = [lineupPullTeam:Player,lineupReceiveTeam:Player]
	                   
	static constraints = {
		pullPlayer(nullable:true)
		receivePlayer(nullable:true)
	}
}
