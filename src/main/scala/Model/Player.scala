package Model
import scalafx.scene.image.ImageView
import scala.collection.mutable.ArrayBuffer


case class Plane(planeRef: ImageView, position: ImageView)

class Player(
    color: String, 
    startPoint: ImageView, 
    startRoute: ImageView, 
    goalPoint: ImageView,
    planes: ArrayBuffer[Plane]
)
