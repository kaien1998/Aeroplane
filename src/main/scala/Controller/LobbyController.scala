package Controller

import Actors.Client.Join
import MainSystem.MyGame
import scalafx.event.ActionEvent
import scalafx.scene.control.{Label, TextField}
import scalafx.scene.shape.Circle
import scalafxml.core.macros.sfxml
@sfxml
class MainWindowController(
    private val ip: TextField,
    private val port: TextField,
    private val status: Label
) {
  def handleJoin(action: ActionEvent): Unit = {
    // MyGame.clientRef ! Join(txtServer.text.value, txtPort.text.value.toInt)
  }
  def handleStart(action: ActionEvent): Unit = {
    // MyGame.serverRef ! "start"
  }

  def displayStatus(a: String): Unit = {
    status.text = a
  }

}
