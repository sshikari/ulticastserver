import java.sql.Timestamp
import grails.converters.JSON
import com.ulticast.domain.*
import com.test.Book
import com.test.Author


class BootStrap {
     def init = { servletContext ->
        // Create some test data
//        new Book(author:"Stephen King",title:"The Shining").save()
//        new Book(author:"James Patterson",title:"Along Came a Spider").save()
//        new Book(author:"JRR Tolkien",title:"The Hobbit").save()

         //JSON.registerObjectMarshaller(Date) {
         //   return it?.format("dd-MM-yyyy hh:mm:ss")
        // }

        new Author(firstName:"Stephen",lastName:"King")
        .addToBooks(new Book(title:"The Shining"))
        .addToBooks(new Book(title:"Cujo"))
 	.save()
        new Author(firstName:"JRR",lastName:"Tolkien")
        .addToBooks(new Book(title:"The Bored of the Rings"))
 	.save()


       // ULTICAST
        Player bill = new Player(name:"Bill", number:7)
	bill.save()
        Player hill = new Player(name:"Hillary", number:8)
	hill.save()
        Player rich = new Player(name:"Rich", number:10)
        Player blake = new Player(name:"Blake", number:11)

	Team harvard = new Team(teamName:"Harvard")
		harvard.addToPlayers(bill)
		.addToPlayers(hill)
		.save()
	Team tufts = new Team(teamName:"Tufts")
		tufts.addToPlayers(rich)	
		.addToPlayers(blake)
		.save()

        Game game = new Game(homeTeam:harvard, awayTeam:tufts)
	game.save()

        Game game2 = new Game(homeTeam:harvard, awayTeam:tufts)
	game2.save()

        Date now = new Date()
        new TimeEvent(game:game, timeType: "START",
                      team: harvard,
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