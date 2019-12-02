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
    val totalPlayer: Label,
    val readyPlayer: Label,
    val playerMsg: Label
) {

  val colourMap: Map[String, Label] = Map(
    "Yellow" -> yellowPlayer,
    "Blue" -> bluePlayer,
    "Red" -> redPlayer,
    "Green" -> greenPlayer
  )

  MyGame.clientRef ! "getTotalPlayer"

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

  def displayWelcomeMsg(name: String): Unit = {
    playerMsg.text.value = s"Welcome, $name"
  }

  def displayTotalPlayer(number: String) {
    totalPlayer.text.value = number
  }

  //update selected colour and number of ready player
  def displayPlayerSelection(
      colour: String,
      name: String,
      number: Int
  ): Unit = {
    colourMap(colour).text.value = name
    readyPlayer.text.value = number.toString()
  }

  def handleStart(action: ActionEvent): Unit = {
    MyGame.serverRef ! "start"
  }

}
