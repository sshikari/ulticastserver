import java.sql.Timestamp
import grails.converters.JSON
import com.ulticast.domain.*



class BootStrap {
     def init = { servletContext ->



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

//	new Game(homeTeam:Team.findByTeamName("Harvard"),
//		 awayTeam:Team.findByTeamName("Tufts"))
	 	         		
     }
     def destroy = {
     }
} 