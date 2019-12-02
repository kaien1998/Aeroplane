package MainSystem

import Controller._
import Model._
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

  /******************************Akka Configuration**************************/
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
       |    warn-about-java-serializer-usage = false
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

  /**********************************************************************************/
  var lobbyControllerRef: LobbyController#Controller = null
  var gameBoardControllerRef: GameBoardController#Controller = null

  val rootResource = getClass.getResource("/View/MainPage.fxml")
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load();
  val roots = loader.getRoot[jfxs.layout.AnchorPane]
  val control = loader.getController[MainPageController#Controller]

  stage = new PrimaryStage {
    title = "AeroplaneGame"
    scene = new Scene {
      root = roots
    }
  }
  stage.setResizable(false)

  stage.onCloseRequest = handle({
    system.terminate
  })

  def goToMainPage() {
    val rootResource = getClass.getResource("/View/MainPage.fxml")
    val loader = new FXMLLoader(rootResource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    val scene = new Scene(roots)
    stage.setScene(scene)
  }

  def goToLobby() {
    val rootResource = getClass.getResource("/View/LobbyPage.fxml")
    val loader = new FXMLLoader(rootResource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    lobbyControllerRef = loader.getController[LobbyController#Controller]
    val scene = new Scene(roots)
    stage.setScene(scene)
  }

  def goToGameBoard() {
    val rootResource = getClass.getResource("/View/GameBoard1.fxml")
    val loader = new FXMLLoader(rootResource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    gameBoardControllerRef =
      loader.getController[GameBoardController#Controller]
    val scene = new Scene(roots)
    stage.setScene(scene)
  }

}
