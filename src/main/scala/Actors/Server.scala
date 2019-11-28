package Actors

import akka.actor.{Actor, ActorRef}
import akka.pattern.ask
import akka.remote.DisassociatedEvent
import akka.util.Timeout
import Actors.Server._
import scalafx.collections.ObservableHashSet

import scala.concurrent.Await
import scala.concurrent.duration._
class Server extends Actor {
  implicit val timeout = Timeout(10.second)

  val members = new ObservableHashSet[ActorRef]()
  var circularIterOpt: Option[Iterator[ActorRef]] = None

  def receive = {
    case DisassociatedEvent(localAddress, remoteAddress, _) =>
      if (members.exists(x => x.path.address.equals(remoteAddress))) {
        val refOpt = members.find(x => x.path.address.equals(remoteAddress))
        for (ref <- refOpt) {
          members.remove(ref)
        }
      }
    case "start" =>
      context.become(started)

      val results = for (member <- members) yield {
        member ? "begin"
      }
      for (resultf <- results) {
        Await.result(resultf, 20.second)
      }

      //take
      if (!circularIterOpt.isDefined) {
        circularIterOpt = Option(Iterator.continually(members.toList).flatten)
      }
      //iteratr that take 1
      for (circleIter <- circularIterOpt) {
        val memberIter = circleIter.take(1)
        memberIter.next() ! "take"
      }

    case Join(x) =>
      members += x
      sender ! "ok"
    case _ =>
  }

  def started: Receive = {
    case DisassociatedEvent(localAddress, remoteAddress, _) =>
      for (member <- members) {
        member ! "gameend"
      }

    case Join(x) =>
      sender ! "game in session"
    case "pass" =>
      sender ! "ok"
      for (circleIter <- circularIterOpt) {
        val memberIter = circleIter.take(1)
        memberIter.next() ! "take"
      }
    case _ =>
  }
}
object Server {
  final case class Join(ref: ActorRef)

}
