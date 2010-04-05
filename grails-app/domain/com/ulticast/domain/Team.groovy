package com.ulticast.domain

class Team {
    static hasMany = [ players : com.ulticast.domain.Player ]

    String teamName

    static constraints = {
    }
}
