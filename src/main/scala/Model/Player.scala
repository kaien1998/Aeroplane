package Model

import scalafx.scene.image.{Image,ImageView}
import scala.collection.mutable.ArrayBuffer


case class Plane(val planeRef: ImageView, var position: ImageView, var isOnGoalRoute: Boolean = false, var toGoalDirection: Boolean = true, var atGoal: Boolean = false)

class Player(
    val color: String, 
    val startPoint: ImageView, 
    val startRoute: ImageView, 
    val goalPoint: ImageView,
    val goalRoute: ArrayBuffer[ImageView],
    val flyPoint: ImageView,
    val initialImg: String,
    val house: ArrayBuffer[ImageView],
    var planeRef: ArrayBuffer[ImageView],
    var planeContainer: ArrayBuffer[Plane] = ArrayBuffer[Plane]()
)
{
    def initialise(): Unit =
    {
        for(i <- 0 to 3)
        {
           planeRef(i).image_=(new Image(initialImg))
           planeContainer += Plane(planeRef(i), house(i))
           planeContainer(i).planeRef.fitWidth = 42
           planeContainer(i).planeRef.fitHeight = 42
           planeContainer(i).planeRef.layoutX = house(i).layoutX()
           planeContainer(i).planeRef.layoutY = house(i).layoutY()
        }
    }
 
}
