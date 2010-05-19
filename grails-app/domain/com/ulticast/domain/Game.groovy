package com.ulticast.domain

class Game {
	AuthUser owner
    Team homeTeam
    Team awayTeam
    int homeTeamScore
    int awayTeamScore

    static constraints = {
    }
}
