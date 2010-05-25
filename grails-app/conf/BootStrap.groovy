import java.sql.Timestamp
import grails.converters.JSON
import com.ulticast.domain.*
import com.ulticast.api.ApiUtil


class BootStrap {
	def authenticateService
	
	def init = { servletContext ->
		
		JSON.registerObjectMarshaller(AuthToken) {
			def returnMap = [:]
			returnMap.username = it.user.username
			returnMap.token = it.token
			returnMap.date_created = ApiUtil.DATE_FORMATTER.format(it.dateCreated)
			returnMap.expiration_date = it.expirationDate ? ApiUtil.DATE_FORMATTER.format(it.expirationDate) : it.expirationDate 
			returnMap.is_active = it.isActive
			return returnMap
		}
		
		JSON.registerObjectMarshaller(Team) {
			def returnMap = [:]
			returnMap.id = it.id
			returnMap.name = it.teamName
			returnMap.is_my_team = it.isOwnerTeam
			returnMap.players = it.players
			returnMap.last_updated = it.lastUpdated ? ApiUtil.DATE_FORMATTER.format(it.lastUpdated) : it.lastUpdated
			returnMap.version = it.version
			return returnMap
		}
		
		
		JSON.registerObjectMarshaller(Player) {
			def returnMap = [:]
			returnMap.id = it.id
			returnMap.nickname = it.nickname
			returnMap.first_name = it.firstName
			returnMap.last_name = it.lastName
			returnMap.number = it.number
			returnMap.last_updated = it.lastUpdated ? ApiUtil.DATE_FORMATTER.format(it.lastUpdated) : it.lastUpdated
			returnMap.version = it.version
			return returnMap
		}
		
		if (!Player.get(1)) {
			
			AuthUser userBlake = new AuthUser(authorities:[],
				username:"blake", firstName:"Blake", lastName:"Barnes", 
				passwd:authenticateService.encodePassword("blake"),
				enabled:true, email:"blake@blake.com", emailShow:false, 
				description:"test").save()
			
			AuthUser userTest = new AuthUser(authorities:[],
				username:"test", firstName:"Test",lastName:"User", 
				passwd:authenticateService.encodePassword("test"),
				enabled:true, email:"test@test.com", emailShow:false,
				description:"test").save()
			
			AuthRole userRole = new AuthRole(people:[userBlake,userTest], 
					authority:"ROLE_WEB", description: "Web user").save()
			AuthRole adminRole = new AuthRole(people:[userBlake,userTest], 
					authority:"ROLE_ADMIN", description: "Admin user").save()
			AuthRole mobileRole = new AuthRole(people:[userBlake,userTest], 
					authority:"ROLE_MOBILE", description: "Mobile user").save()
			
			Player bill = new Player(nickname:"Bill", number:7)
			Player hill = new Player(nickname:"Hillary", number:8)
			Player rich = new Player(nickname:"Rich", number:10)
			Player blake = new Player(nickname:"Blake", number:11)
			
			Team harvard = new Team(owner:userBlake, isOwnerTeam:false, teamName:"Harvard")
			harvard.addToPlayers(bill)
				.addToPlayers(hill)
				.addToPlayers(new Player(nickname:"H3", number:3))
				.addToPlayers(new Player(nickname:"H4", number:4))
				.addToPlayers(new Player(nickname:"H5", number:5))
				.addToPlayers(new Player(nickname:"H6", number:6))
				.addToPlayers(new Player(nickname:"H7", number:7))
				.save()
			
			Team tufts = new Team(owner:userBlake, isOwnerTeam:true, teamName:"Tufts")
			tufts.addToPlayers(rich)	
				.addToPlayers(blake)
				.addToPlayers(new Player(nickname:"T3", number:3))
				.addToPlayers(new Player(nickname:"T4", number:4))
				.addToPlayers(new Player(nickname:"T5", number:5))
				.addToPlayers(new Player(nickname:"T6", number:6))
				.addToPlayers(new Player(nickname:"T7", number:7))
				.save()
			
			Game game = new Game(owner:userBlake, homeTeam:harvard, awayTeam:tufts)
			game.save()
			
			Game game2 = new Game(owner:userBlake, homeTeam:harvard, awayTeam:tufts)
			game2.save()
			
			Date now = new Date()
			
			new TimeEvent(game:game, timeType: TimeEvent.TIME_TYPE_START, callTeam: harvard,
					timestamp: now).save()
			
			new PassEvent(player:bill, 
				game: game,
				offenseTeam: harvard, 
				stallCount: 5,
				passCount: 1,
				timestamp: now).save()
			
			new PassEvent(player:hill, 
				game: game, 
				offenseTeam: harvard, 
				stallCount: 6,
				passCount: 2,
				timestamp: now).save()	
			
			new ScoreEvent(player:bill, 
				assister:hill,
				distance: ScoreEvent.DISTANCE_MIDFIELD,
				game: game, 
				scorerTeam: harvard,
				stallCount: 7,
				passCount: 3,
				timestamp: now).save()
			
			new TimeEvent(game:game, callTeam: tufts, timeType: TimeEvent.TIME_TYPE_TIMEOUT,
					timestamp: now).save()
			
			new PassEvent(player:rich, 
				game: game,
				offenseTeam: tufts, 
				stallCount: 3,
				passCount: 1,
				timestamp: now).save()
			
			new PassEvent(player:blake, 
				game: game, 
				offenseTeam: tufts, 
				stallCount: 6,
				passCount: 2,
				timestamp: now).save()	
			
			new TurnEvent(player:rich, 
				turnType: TurnEvent.TURN_TYPE_DROP,
				game: game, 
				newDefenseTeam: tufts,
				newOffenseTeam: harvard,
				stallCount: 8,
				passCount: 3,
				notes: "rich missed an easy one...:(",
				timestamp: now).save()
			
			new CallEvent(callType: CallEvent.CALL_TYPE_DEFENSIVE_FOUL, 
				offensiveTeam: tufts, 
				defensiveTeam: harvard,
				isContested: true,
				game: game, 
				timestamp: now).save()	
		}        		
	}
	
	def destroy = {
	
	}
} 