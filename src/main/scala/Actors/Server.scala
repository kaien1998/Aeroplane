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

  // val members = new ObservableHashSet[ActorRef]()
  var members = new ArrayBuffer[ActorRef]()
  var circularIterOpt: Option[Iterator[ActorRef]] = None
  var playerColour: Map[String, ActorRef] = Map()
  var listOfActorRef = new ArrayBuffer[ActorRef]()

  def receive = {

    case Join(x) => {
      if (members.size <= 1) {
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
          member ! "begin"
        }
      }
    }
    // context.become(started)

    // val results = for (member <- members) yield {
    //   member ? "begin"
    // }
    // for (resultf <- results) {
    //   Await.result(resultf, 20.second)
    // }

    // //take
    // if (!circularIterOpt.isDefined) {
    //   circularIterOpt = Option(Iterator.continually(members.toList).flatten)
    // }
    // //iteratr that take 1
    // for (circleIter <- circularIterOpt) {
    //   val memberIter = circleIter.take(1)
    //   memberIter.next() ! "take"
    // }

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
      Platform.runLater {
        val alert = new Alert(AlertType.Information) {
          initOwner(MyGame.stage)
          title = "Maximum Capacity"
          headerText = "Unable "
        }.showAndWait()
      }
    }

    case "pass" =>
      sender ! "ok"
      for (circleIter <- circularIterOpt) {
        val memberIter = circleIter.take(1)
        memberIter.next() ! "take"
      }
    case _ =>
  }
}
object Server {
  final case class Join(ref: ActorRef)
  final case class SelectColour(ref: ActorRef, colour: String, name: String)

}
