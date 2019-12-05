package Controller

import Actors._
import MainSystem.MyGame
import scalafx.event.ActionEvent
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Label, TextField}
import scalafx.scene.shape.Circle
import scalafxml.core.macros.sfxml
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.Await

@sfxml
class MainPageController(
    val ip: TextField,
    val port: TextField,
    val status: Label,
    val name: TextField
) {
  def handleJoin(action: ActionEvent): Unit = {
    try {
      if (name.text.value != "" && ip.text.value !="" && port.text.value !="") {
         MyGame.clientRef ! Client.Join(
            ip.text.value,
            port.text.value.toInt,
            name.text.value)
      }
      else
      {
        val alert = new Alert(AlertType.Error){
          title = "Error"
          headerText = "Some input is not found"
          contentText = "Please make sure you did not leave any field empty"
        }.showAndWait()
      }
    }
    catch{
      case e: NumberFormatException =>
        val alert = new Alert(AlertType.Error){
          title = "Error"
          headerText = "Port Number must be number"
          contentText = "Cannot convert port to Integer!"
        }.showAndWait()
    }

  }

  def displayStatus(a: String): Unit = {
    status.text = a
  }

}
