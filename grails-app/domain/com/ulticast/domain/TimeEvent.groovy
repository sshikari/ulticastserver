package com.ulticast.domain

class TimeEvent extends Event {
    String timeType    // START, STOP, TIMEOUT, HALFTIME, INJURY  
    Team team
      
    static constraints = {
       team(nullable:true)
    }
}
