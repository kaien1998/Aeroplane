package Actors

import akka.actor.{Actor, ActorRef}
import akka.pattern.ask
import akka.remote.DisassociatedEvent
import akka.util.Timeout
import Actors.Server._
// import scalafx.collections.ObservableHashSet

import scala.concurrent.Await
import scala.concurrent.duration._
import scalafx.application.Platform
import scalafx.scene.control.Alert
import _root_.scalafx.scene.control.Alert.AlertType
import MainSystem.MyGame
import scala.collection.mutable.{ArrayBuffer, Map}
class Server extends Actor {
  implicit val timeout = Timeout(30.second)


  var members = new ArrayBuffer[ActorRef]()
  var circularIterOpt: Option[Iterator[ActorRef]] = None
  var playerColour: Map[String, ActorRef] = Map()
  var listOfActorRef = new ArrayBuffer[ActorRef]()
  var counter:Int = 0

  def receive = {

    case Join(x) => {
      if (members.size <= 3) {
        members += x
        sender ! ArrayBuffer[String]("ok", s"${members.size.toString()}")
      }
      //handle when lobby is full
      else {
        sender ! ArrayBuffer[String]("full", "")
      }
    }

    case "getTotalPlayer" => {
      for (member <- members) {
        member ! Client.UpdateTotalPlayer(members.size)
      }
    }



    case SelectColour(actorRef, colour, name) => {
      if (playerColour.contains(colour)) {
        sender ! "taken"
      } else if (listOfActorRef.contains(actorRef)){
        sender ! "same"
      } else {
        playerColour += (colour -> actorRef)
        sender ! "hideButton"
        //sender ! "ok"
        listOfActorRef += actorRef
        //broadcast to all member
        for (member <- members) {
          member ! Client.UpdateSelection(colour, name, playerColour.size)
        }
      }
    }

    case "start" => {
      if (members.size != playerColour.size) {
        for (member <- members) {
          member ! "notReady"
        }
      } else {
        for (member <- members) {
          member ! Client.Begin(playerColour.keys)
          context.become(started)

        }
        Thread.sleep(1000)
        if (!circularIterOpt.isDefined) {
            circularIterOpt = Option(Iterator.continually(members.toList).flatten)
          }
          //iteratr that take 1
          var clr = for (circleIter <- circularIterOpt) yield {
          val memberIter = circleIter.take(1)
          val nextMember = memberIter.next()
          val colour = playerColour.find(_._2 == nextMember).get._1
          nextMember! Client.ServerAskRoll(colour)
          colour
        }

        for(member <- members){
          member ! Client.DisplayGameStatus(clr.get, "is rolling")
        }
        
      }
    }
    
    

    case _ => {
      Platform.runLater {
        val alert = new Alert(AlertType.Error) {
          initOwner(MyGame.stage)
          title = "Unknown Request"
          headerText = "Server is unable to handle unknown request"
        }.showAndWait()
      }
    }
  }

  def started: Receive = {
    case Join(x) => {
      sender ! "started"
    }

    case Roll(actorRef, colour, dice) =>{
      for(member  <- members){
        member ! Client.BroadcastRoll(dice)
        member ! Client.DisplayGameStatus(colour, "is selecting plane")
      }

      actorRef ! Client.ServerAskSelectPlane(colour)
    } 

    case SelectPlane(actorRef, colour, index) => {
      for(member <- members){
        member ! Client.BroadcastMove(index, colour)
      }
    }

    case "move done" =>{
      counter += 1
      if(counter == playerColour.size){
        counter = 0
        var clr = for (circleIter <- circularIterOpt) yield {
          val memberIter = circleIter.take(1)
          val nextMember = memberIter.next()
          val colour = playerColour.find(_._2 == nextMember).get._1
          nextMember! Client.ServerAskRoll(colour)
          colour
        }

        for(member <- members){
          member ! Client.DisplayGameStatus(clr.get, "is rolling")
        }
      }
      
    }

     case Win(actorRef, colour) => {
       for(member <- members){
         member ! Client.BroadcastWinner(colour)
       }
       MyGame.system.stop(context.self)
     } 

    case _ => {
      Platform.runLater {
        val alert = new Alert(AlertType.Error) {
          initOwner(MyGame.stage)
          title = "Unknown Request"
          headerText = "Server is unable to handle unknown request"
        }.showAndWait()
      }
    }
  }
}

object Server {
  final case class Join(ref: ActorRef)
  final case class SelectColour(ref: ActorRef, colour: String, name: String)
  final case class Roll(ref: ActorRef, colour: String, dice: Int)
  final case class SelectPlane(ref: ActorRef, colour: String, index: Int)
  final case class Win(ref: ActorRef, colour: String)

}
