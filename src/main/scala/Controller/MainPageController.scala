package Controller

import Actors._
import MainSystem.MyGame
import scalafx.event.ActionEvent
import scalafx.scene.control.{Label, TextField}
import scalafx.scene.shape.Circle
import scalafxml.core.macros.sfxml

@sfxml
class MainPageController(
    val ip: TextField,
    val port: TextField,
    val status: Label,
    val name: TextField
) {
  def handleJoin(action: ActionEvent): Unit = {
    MyGame.clientRef ! Client.Join(
      ip.text.value,
      port.text.value.toInt,
      name.text.value
    )

  }

  def displayStatus(a: String): Unit = {
    status.text = a
  }

}
