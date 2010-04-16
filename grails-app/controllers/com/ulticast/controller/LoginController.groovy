package com.ulticast.controller

import com.ulticast.domain.*
import grails.converters.JSON;
 
import org.hibernate.SessionFactory
import org.hibernate.Session

class LoginController {
	
    SessionFactory sessionFactory
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {

       /**
       TODO
       - authenticate username and password
 	 - retrieve player/user info 
	      - id, first/lastname, etc.
	      - teams?
	 - return auth key, player id, teamNames and ids?
	*/
	// eager, so teams are retrieved, but the JSON parser is not
	// deeply parsing right now.
	if (params.username == null) {
	   response.status = 401
	   render "username required"
 	   return
	}
	Player p = Player.findByNickname(params.username)
	if (p == null) {
	    response.status = 401 // temp error status
	    render "Login or password incorrect"	    	   
	} else {
	    render p as JSON
	}
    }
}