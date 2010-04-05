package com.ulticast.domain

class ScoreEvent extends Event {
    Player player
    Team team
    Player assister
    String distanceDescriptor
    int score = 0

    static constraints = {
        player(nullable:true)
	assister(nullable:true)
	distanceDescriptor(nullable:true)
    }
}
