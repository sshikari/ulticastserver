class UrlMappings {
	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		"/"(view:"/index")
		"500"(view:'/error')
		
		//BEGIN API MAPPINGS
		"/api/login" {
			controller = "apiMain"
			action = "login"
		}
		
		"/api/logout" {
			controller = "apiMain"
			action = "logout"
		}
		
		"/api/team/overview" {
			controller = "apiTeam"
			action = "overview"
		}
		
		"/api/team/all" {
			controller = "apiTeam"
			action = "all"
		}
		
		"/api/team/detail" {
			controller = "apiTeam"
			action = "detail"
		}
		
		"/api/team/update" {
			controller = "apiTeam"
			action = "update0"
		}
		
		"/api/team/new" {
			controller = "apiTeam"
			action = "new0"
		}
		
		"/api/team/delete" {
			controller = "apiTeam"
			action = "delete0" 
		}
		
		"/api/event/new" {
			controller = "apiEvent"
			action = "new0"
		}
				
		//END API MAPPINGS
	}
}