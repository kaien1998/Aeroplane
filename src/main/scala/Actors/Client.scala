package Actors

import akka.actor.{Actor, ActorSelection}
import akka.pattern.ask
import akka.util.Timeout
import Actors.Client._
import scalafx.application.Platform
import MainSystem.MyGame

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.duration._
class Client extends Actor {
  var serverOpt: Option[ActorSelection] = None
  implicit val timeout: Timeout = Timeout(10.minute)

  def receive = {
    case Join(ip, port) =>
      val server: ActorSelection = context.actorSelection(
        s"akka.tcp://ball@$ip:${port.toString}/user/server"
      )
      serverOpt = Option(server)

      val result = server ? Server.Join(context.self)
      result.foreach(x => {
        if (x.equals("ok")) {
          Platform.runLater {
            // MyGame.control.displayStatus("joined")
          }
          context.become(joined)
        }
      })
    case _ =>
  }
  def joined: Receive = {
    case Join(ip, port) =>
      Platform.runLater {
        // MyGame.control.displayStatus("Already joined")
      }
    case "begin" =>
      Platform.runLater {
        // MyGame.control.hideBall()
      }
      sender ! "taken"
    case "take" =>
      context.become(hasBall)
      Platform.runLater {
        // MyGame.control.showBall()
      }
    case _ =>
  }
  def hasBall: Receive = {
    case "pass" =>
      for (server <- serverOpt) {
        val result = server ? "pass"
        result foreach { x =>
          Platform.runLater {
            // MyGame.control.hideBall()
          }
          context.become(joined)
        }
      }
    case _ =>
  }
}
object Client {
  final case class Join(ip: String, port: Int)
}
