package com.ulticast.domain

class Game {
	AuthUser owner
    Team homeTeam
    Team awayTeam
    Integer homeTeamScore = 0 
    Integer awayTeamScore = 0

    static constraints = {
    }
}
