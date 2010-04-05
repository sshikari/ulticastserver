package com.ulticast.domain

class PassEvent extends Event {
    Player player
    Team team
    static constraints = {
       player(nullable:true)
    }
}
