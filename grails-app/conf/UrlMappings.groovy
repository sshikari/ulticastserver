class UrlMappings {




    static mappings = {
       "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
	      }
       }

       "/"(view:"/index")
	  "500"(view:'/error')

//       "/game/$id?"(controller:"event", parseRequest:true) {
//           action = [GET:"show", PUT:"update", DELETE:"delete", POST:"save"]
//       }
    }
}
