package Controller

import Actors.Client._
import MainSystem.MyGame
import scalafx.event.ActionEvent
import scalafx.scene.control.{Label, TextField}
import scalafx.scene.shape.Circle
import scalafxml.core.macros.sfxml
@sfxml
class LobbyController(
    val yellowPlayer: Label,
    val bluePlayer: Label,
    val redPlayer: Label,
    val greenPlayer: Label,
    val playerCount: Label,
    val playerMsg: Label
) {

  val colourMap: Map[String, Label] = Map(
    "Yellow" -> yellowPlayer,
    "Blue" -> bluePlayer,
    "Red" -> redPlayer,
    "Green" -> greenPlayer
  )

  def selectYellow(action: ActionEvent): Unit = {
    MyGame.clientRef ! SelectColour("Yellow")
  }
  def selectBlue(action: ActionEvent): Unit = {
    MyGame.clientRef ! SelectColour("Blue")
  }

  def selectRed(action: ActionEvent): Unit = {
    MyGame.clientRef ! SelectColour("Red")
  }

  def selectGreen(action: ActionEvent): Unit = {
    MyGame.clientRef ! SelectColour("Green")
  }

  def displayPlayerSelection(colour: String, name: String): Unit = {
    colourMap(colour).text.value = name
  }

  def handleStart(action: ActionEvent): Unit = {
    MyGame.serverRef ! "start"
  }

}
