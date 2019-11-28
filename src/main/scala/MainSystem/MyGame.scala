package MainSystem

import Controller.GameBoardController
import Model.Player
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafx.animation.TranslateTransition
import scalafx.scene.image.{Image, ImageView}
import scalafx.stage.{Modality, Stage}
import scalafx.util.Duration

import akka.actor.{ActorSystem, Props}
import scala.concurrent._
import com.typesafe.config.ConfigFactory
import java.net.NetworkInterface

import scala.collection.JavaConverters._

object MyGame extends JFXApp {

  var count = -1
  val addresses = (for (inf <- NetworkInterface.getNetworkInterfaces.asScala;
                        add <- inf.getInetAddresses.asScala) yield {
    count = count + 1
    (count -> add)
  }).toMap
  for ((i, add) <- addresses) {
    println(s"$i = $add")
  }
  println("please select which interface to bind")
  var selection: Int = 0
  do {
    selection = scala.io.StdIn.readInt()
  } while (!(selection >= 0 && selection < addresses.size))

  val ipaddress = addresses(selection)

  val overrideConf = ConfigFactory.parseString(s"""
       |akka {
       |  loglevel = "INFO"
       |
 |  actor {
       |    provider = "akka.remote.RemoteActorRefProvider"
       |  }
       |
 |  remote {
       |    enabled-transports = ["akka.remote.netty.tcp"]
       |    netty.tcp {
       |      hostname = "${ipaddress.getHostAddress}"
       |      port = 0
       |    }
       |
 |    log-sent-messages = on
       |    log-received-messages = on
       |  }
       |
 |}
       |
     """.stripMargin)

  val myConf = overrideConf.withFallback(ConfigFactory.load())
  val system = ActorSystem("board", myConf)
  val serverRef = system.actorOf(Props[Actors.Server](), "server")
  val clientRef = system.actorOf(Props[Actors.Client](), "client")

  val rootResource = getClass.getResource("/View/GameBoard1.fxml")
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load();
  val roots = loader.getRoot[jfxs.layout.AnchorPane]
  val control =
    loader.getController[Controller.GameBoardController#Controller]()

  stage = new PrimaryStage {
    title = "AeroplaneGame"
    scene = new Scene {
      root = roots
    }
  }

}
