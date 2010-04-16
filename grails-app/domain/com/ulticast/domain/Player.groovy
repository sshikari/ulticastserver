package com.ulticast.domain

class Player {
	static belongsTo = Team
	static hasMany = [teams: Team]
	static fetchMode = [teams:'eager']
                  
    String nickname
	String firstName
	String lastName	
    int number 

    static constraints = {
		firstName(nullable:true)
		lastName(nullable:true)		
    }
}
