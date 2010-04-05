package com.ulticast.domain

class TurnEvent extends Event{
    String turnType
    Team team
    Player player  
    static constraints = {
    	player(nullable:true)
    }
}
