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
      result
        .mapTo[ArrayBuffer[String]]
        .foreach(x => {
          //when server is not full
          if (x(0).equals("ok")) {
            Platform.runLater {
              MyGame.goToLobby()
              MyGame.lobbyControllerRef.displayWelcomeMsg(name)
              MyGame.lobbyControllerRef.displayTotalPlayer(x(1))
            }
            context.become(lobby)
          }

          //when server is full
          if (x(0).equals("full")) {
            Platform.runLater {
              val alert = new Alert(AlertType.Warning) {
                initOwner(MyGame.stage)
                title = "Lobby Full"
                headerText = "Lobby is full. Unable to join."
              }.showAndWait()
            }
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
        //when colour is taken
        if (x.equals("taken")) {
          Platform.runLater {
            val alert = new Alert(AlertType.Warning) {
              initOwner(MyGame.stage)
              headerText = "Colour is taken"
            }.showAndWait()
          }
        } else if (x.equals("same")) {
          Platform.runLater {
            val alert = new Alert(AlertType.Warning) {
              initOwner(MyGame.stage)
              headerText = "Please Select Only One"
            }.showAndWait()
          }
        } else if (x.equals("hideButton")) {
          Platform.runLater {
            MyGame.lobbyControllerRef.hideButton(colour)
          }
        }
      })
    }

    case UpdateSelection(colour, name, number) => {
      Platform.runLater {

        MyGame.lobbyControllerRef.displayPlayerSelection(colour, name, number)
      }
    }

    case UpdateTotalPlayer(number) => {
      Platform.runLater {
        MyGame.lobbyControllerRef.displayTotalPlayer(number.toString())
      }
    }

    case "getTotalPlayer" => {
      server ! "getTotalPlayer"
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
        context.become(gameBoard)
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

  def gameBoard: Receive = {
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
  final case class UpdateSelection(colour: String, name: String, number: Int)
  final case class UpdateTotalPlayer(number: Int)

}
