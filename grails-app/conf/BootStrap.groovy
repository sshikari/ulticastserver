import com.ulticast.controller.api.ApiController;
import java.sql.Timestamp
import grails.converters.JSON
import com.ulticast.domain.*



class BootStrap {
	def authenticateService
	
	def init = { servletContext ->
		
		JSON.registerObjectMarshaller(AuthToken) {
			def returnMap = [:]
			returnMap.username = it.user.username
			returnMap.token = it.token
			returnMap.creationDate = ApiController.DATE_FORMATTER.format(it.creationDate)
			returnMap.expirationDate = it.expirationDate ? ApiController.DATE_FORMATTER.format(it.expirationDate) : null
			returnMap.isActive = it.isActive
			return returnMap
		}
		
		if (!Player.get(1)) {
			
			AuthUser testUser = new AuthUser(authorities:[],
			username:"blake", userRealName:"Blake Barnes", 
			passwd:authenticateService.encodePassword("blake"),
			enabled:true, email:"blake@blake.com", emailShow:false).save()
			
			AuthRole userRole = new AuthRole(people:[testUser], authority:"ROLE_WEB", description: "Web user").save()
			AuthRole adminRole = new AuthRole(people:[testUser], authority:"ROLE_ADMIN", description: "Admin user").save()
			AuthRole mobileRole = new AuthRole(people:[testUser], authority:"ROLE_MOBILE", description: "Mobile user").save()
			
			// ULTICAST
			Player bill = new Player(nickname:"Bill", number:7)
			Player hill = new Player(nickname:"Hillary", number:8)
			Player rich = new Player(nickname:"Rich", number:10)
			Player blake = new Player(nickname:"Blake", number:11)
			
			Team harvard = new Team(teamName:"Harvard")
			harvard.addToPlayers(bill)
			.addToPlayers(hill)
			.addToPlayers(new Player(nickname:"H3", number:3))
			.addToPlayers(new Player(nickname:"H4", number:4))
			.addToPlayers(new Player(nickname:"H5", number:5))
			.addToPlayers(new Player(nickname:"H6", number:6))
			.addToPlayers(new Player(nickname:"H7", number:7))
			.save()
			Team tufts = new Team(teamName:"Tufts")
			tufts.addToPlayers(rich)	
			.addToPlayers(blake)
			.addToPlayers(new Player(nickname:"T3", number:3))
			.addToPlayers(new Player(nickname:"T4", number:4))
			.addToPlayers(new Player(nickname:"T5", number:5))
			.addToPlayers(new Player(nickname:"T6", number:6))
			.addToPlayers(new Player(nickname:"T7", number:7))
			.save()
			
			Game game = new Game(homeTeam:harvard, awayTeam:tufts)
			game.save()
			
			Game game2 = new Game(homeTeam:harvard, awayTeam:tufts)
			game2.save()
			
			Date now = new Date()
			new TimeEvent(game:game, timeType: "START", team: harvard,
			timestamp: now).save()
			
			new PassEvent(player:bill, 
			game: game,
			team: harvard, 
			timestamp: now).save()
			new PassEvent(player:hill, 
			game: game, 
			team: harvard, 
			timestamp: now).save()	
			new ScoreEvent(player:bill, 
			assister:hill,
			distanceDescriptor: "from midfield!!!",
			game: game, 
			team: harvard,
			score: 1, 
			timestamp: now).save()
			new TimeEvent(game:game, team: tufts, timeType: "TIMEOUT",
			timestamp: now).save()
			new PassEvent(player:rich, 
			game: game,
			team: tufts, 
			timestamp: now).save()
			new PassEvent(player:blake, 
			game: game, 
			team: tufts, 
			timestamp: now).save()	
			new TurnEvent(player:rich, 
			turnType: "DROP",
			game: game, 
			team: tufts,
			notes: "rich missed an easy one...:(",
			timestamp: now).save()
			new CallEvent(callType: "FOUL", 
			contested: Boolean.TRUE,
			caller:bill,
			fouler:rich,
			game: game, 
			team: tufts, 
			timestamp: now).save()	
			new TimeEvent(game:game, timeType: "END", team: harvard).save()
			
		}        		
	}
	def destroy = {
	}
} 