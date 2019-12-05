package Controller

import Actors.Client._
import MainSystem.MyGame
import scalafx.event.ActionEvent
import scalafx.scene.control.{Button, Label}
import scalafxml.core.macros.sfxml
@sfxml
class LobbyController(
    val yellowPlayer: Label,
    val bluePlayer: Label,
    val redPlayer: Label,
    val greenPlayer: Label,
    val totalPlayer: Label,
    val readyPlayer: Label,
    val playerMsg: Label,
    val welcomeMsg: Label,
    var select1: Button, var select2: Button, var select3: Button, var select4: Button
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

  def hideButton(colour: String): Unit ={
    if(colour == "Yellow"){
      select1.disable = true
      select1.text.value = "Taken"
    } else if(colour == "Blue"){
      select2.disable = true
      select2.text.value = "Taken"
    } else if(colour == "Red"){
      select3.disable = true
      select3.text.value = "Taken"
    } else if(colour == "Green"){
      select4.disable = true
      select4.text.value = "Taken"
    }
  }

  def displayWelcomeMsg(name: String): Unit = {
    welcomeMsg.text.value = s"Welcome, $name"
  }

  def displayTotalPlayer(number: String) {
    totalPlayer.text.value = s"$number / 4"
  }

  //update selected colour and number of ready player
  def displayPlayerSelection(
      colour: String,
      name: String,
      number: Int
  ): Unit = {
    colourMap(colour).text.value = name
    readyPlayer.text.value = s"$number / 4"
  }

  def handleStart(action: ActionEvent): Unit = {
    MyGame.serverRef ! "start"
  }

}
