package Actors

import akka.actor.{Actor, ActorSelection}
import akka.pattern.ask
import akka.util.Timeout
import Actors.Client._
import scalafx.application.Platform
import scalafx.scene.control.Alert.AlertType
import MainSystem.MyGame

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.duration._
import scalafx.scene.control.Alert
import scala.collection.mutable.{ArrayBuffer, Map}

class Client extends Actor {
  var server: ActorSelection = null
  implicit val timeout: Timeout = Timeout(10.minute)

  var name: String = null
  var colour: String = null

  def receive = {
    case Join(ip, port, name) => {
      server = context.actorSelection(
        s"akka.tcp://board@$ip:${port.toString}/user/server"
      )

      this.name = name
      val result = server ? Server.Join(context.self)
      result.foreach(x => {
        if (x.equals("ok")) {
          Platform.runLater {
            MyGame.goToLobby()
          }
          context.become(lobby)
        }

        if (x.equals("full")) {
          Platform.runLater {}
        }
      })
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

  def lobby: Receive = {

    case SelectColour(colour) => {
      val result = server ? Server.SelectColour(context.self, colour, name)
      this.colour = colour
      result.foreach(x => {
        if (x.equals("taken")) {
          Platform.runLater {
            val alert = new Alert(AlertType.Warning) {
              initOwner(MyGame.stage)
              headerText = "Colour is taken"
            }.showAndWait()
          }
        }
      })
    }

    case UpdateLobby(colour, name) => {
      Platform.runLater {
        MyGame.lobbyControllerRef.displayPlayerSelection(colour, name)
      }
    }

    case "notReady" => {
      Platform.runLater {
        val alert = new Alert(AlertType.Information) {
          initOwner(MyGame.stage)
          headerText = "Game is beginning soon. Please select colour"
        }.showAndWait()
      }
    }

    case "begin" => {
      Platform.runLater {
        MyGame.goToGameBoard()
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

  def errorMsg = {
    val alert = new Alert(AlertType.Error) {
      initOwner(MyGame.stage)
      title = "Lost connection to server"
      headerText = "Server Connection Lost"
      contentText = "Could Not Connect to Server! Host closed connection"
    }.showAndWait()
  }
  // def joined: Receive = {
  //   case Join(ip, port, name) =>
  //     Platform.runLater {}

  //   case "begin" =>
  //     Platform.runLater {
  //       // MyGame.control.hideBall()
  //     }
  //     sender ! "taken"
  //   case "take" =>
  //     context.become(hasBall)
  //     Platform.runLater {
  //       // MyGame.control.showBall()
  //     }
  //   case _ =>
  // }
  // def hasBall: Receive = {
  //   case "pass" =>
  //     for (server <- serverOpt) {
  //       val result = server ? "pass"
  //       result foreach { x =>
  //         Platform.runLater {
  //           // MyGame.control.hideBall()
  //         }
  //         context.become(joined)
  //       }
  //     }
  //   case _ =>
  // }
}
object Client {
  final case class Join(ip: String, port: Int, name: String)
  final case class SelectColour(colour: String)
  final case class UpdateLobby(colour: String, name: String)
}
