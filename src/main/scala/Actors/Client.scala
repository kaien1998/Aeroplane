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

    case "started" =>{
      Platform.runLater {
        val alert = new Alert(AlertType.Warning) {
          initOwner(MyGame.stage)
          title = "Game in progress."
          headerText = "Game has started. Unable to join."
        }.showAndWait()
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

    case Begin(players) => {
      Platform.runLater {
        context.become(gameBoard)
        MyGame.goToGameBoard()
        MyGame.gameBoardControllerRef.initialize(players)
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

    case ServerAskRoll(colour) =>{
      Platform.runLater {
        MyGame.gameBoardControllerRef.serverAskRoll(colour)
      }
    }

    case Roll(number) => { 
      server ! Server.Roll(context.self, this.colour, number)
    }

    case BroadcastRoll(dice) =>{
      Platform.runLater{
        MyGame.gameBoardControllerRef.updateDiceValue(dice)
      }
    }

    case ServerAskSelectPlane(colour) =>{
      Platform.runLater{
        MyGame.gameBoardControllerRef.serverAskSelectPlane(colour)
      }
    } 

    case SelectPlane(index) => {
      server ! Server.SelectPlane(context.self, this.colour, index)
    }

    case BroadcastMove(index, colour) =>{
      Platform.runLater{
        MyGame.gameBoardControllerRef.serverAskMove(index,colour)
      }
    }
    
    
    case DisplayGameStatus(colour, action) =>{
      Platform.runLater{
        MyGame.gameBoardControllerRef.displayStatus(s"$colour player $action")
      }
    }

    case "move done" => {
      server ! "move done"
    }

    case "win" => {
      server ! Server.Win(context.self, colour)
    }

    case BroadcastWinner(colour) => {
      MyGame.gameBoardControllerRef.serverAskDeclareWinner(colour)
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



  def errorMsg = {
    val alert = new Alert(AlertType.Error) {
      initOwner(MyGame.stage)
      title = "Lost connection to server"
      headerText = "Server Connection Lost"
      contentText = "Could Not Connect to Server! Host closed connection "
    }.showAndWait()
  }

}
object Client {
  final case class Join(ip: String, port: Int, name: String)
  final case class SelectColour(colour: String)
  final case class UpdateSelection(colour: String, name: String, number: Int)
  final case class UpdateTotalPlayer(number: Int)
  final case class Begin(players: Iterable[String])
  final case class ServerAskRoll(colour: String)
  final case class ServerAskSelectPlane(colour: String)
  final case class DisplayGameStatus(colour: String, action: String)
  final case class BroadcastRoll(number: Int)
  final case class Roll(number: Int)
  final case class SelectPlane(index: Int)
  final case class BroadcastMove(index: Int, colour: String)
  final case class BroadcastWinner(colour: String)


}
