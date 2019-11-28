package Model


import scala.collection.mutable.ArrayBuffer
import scalafx.scene.image.ImageView

case class MainRoute(mRoute: ImageView, color: String, isGoalRoute: Boolean)
class Board(
    val players: ArrayBuffer[Player],
    val mRoute: ArrayBuffer[ImageView],
    val gRoutes: Map[String, ArrayBuffer[ImageView]],
    var dice: Int
)