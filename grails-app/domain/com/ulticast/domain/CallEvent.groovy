package com.ulticast.domain

class CallEvent extends Event {
    String callType
    Team team
    Boolean isContested = false
    Player caller
    Player fouler
      
    static constraints = {
       caller(nullable:true)
       fouler(nullable:true)
    }
}
