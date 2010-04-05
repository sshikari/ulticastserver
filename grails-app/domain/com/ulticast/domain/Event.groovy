package com.ulticast.domain

class Event {
    Game game
    String notes
    Date timestamp
    Date lastUpdated


   def beforeUpdate() {
       lastUpdated = new Date()
   }

    static mapping = {
        tablePerHierarchy false
    }

    static constraints = {
        notes(nullable:true)
    }
}
