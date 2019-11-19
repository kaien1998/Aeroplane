package ds.view

import ds.myGame.getClass
import javafx.fxml.FXML
import scalafx.animation.TranslateTransition
import scalafx.event.ActionEvent
import scalafx.scene.control.{Button, TableColumn}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.{AnchorPane, GridPane}
import scalafx.util.Duration
import scalafxml.core.macros.sfxml
import scalafx.Includes._

@sfxml
class GameBoardController (
                          var yellowHouse1 : ImageView,var yellowHouse2 : ImageView,var yellowHouse3 : ImageView,var yellowHouse4 : ImageView,
                          var yellowStartPoint : ImageView,
                          var planeYRandom : ImageView,
                          val board : AnchorPane,
                          var i: Int,
                          ) {


//  while(i != 4){
//    var planeY+i : ImageView = new ImageView(new Image(getClass.getResourceAsStream("/ds/image/plane-yellow.png")))
//  }

  var planeY1 : ImageView = new ImageView(new Image(getClass.getResourceAsStream("/ds/image/plane-yellow.png")))
  var planeY2 : ImageView = new ImageView(new Image(getClass.getResourceAsStream("/ds/image/plane-yellow.png")))
  var planeY3 : ImageView = new ImageView(new Image(getClass.getResourceAsStream("/ds/image/plane-yellow.png")))
  var planeY4 : ImageView = new ImageView(new Image(getClass.getResourceAsStream("/ds/image/plane-yellow.png")))
  board.getChildren().addAll(planeY1,planeY2,planeY3,planeY4)
  planeY1.fitWidth = 42
  planeY1.fitHeight = 42
  planeY1.layoutX = yellowHouse1.layoutX()
  planeY1.layoutY = yellowHouse1.layoutY()
  planeY2.fitWidth = 42
  planeY2.fitHeight = 42
  planeY2.layoutX = yellowHouse2.layoutX()
  planeY2.layoutY = yellowHouse2.layoutY()
  planeY3.fitWidth = 42
  planeY3.fitHeight = 42
  planeY3.layoutX = yellowHouse3.layoutX()
  planeY3.layoutY = yellowHouse3.layoutY()
  planeY4.fitWidth = 42
  planeY4.fitHeight = 42
  planeY4.layoutX = yellowHouse4.layoutX()
  planeY4.layoutY = yellowHouse4.layoutY()


  val boxes = Array.ofDim[Double](3,2)
  boxes(0)(0) = yellowStartPoint.layoutX() - planeY1.layoutX()
  boxes(0)(1) = yellowStartPoint.layoutY() - planeY1.layoutY()

  planeY1.onMouseClicked = myMoveToStart

  def myMoveToStart(m:MouseEvent) = {

    val img = m.getSource().asInstanceOf[javafx.scene.image.ImageView]
    moveToStart(img)

  }

  def moveToStart(plane: ImageView): Unit ={
    val movePlane:TranslateTransition = new TranslateTransition(new Duration(1000), plane)
    movePlane.toX = boxes(0)(0) // move x coordinate
    movePlane.toY = boxes(0)(1) //move y coordinate
    movePlane.play()
  }

//  var movePlane:TranslateTransition = new TranslateTransition(new Duration(1000), planeY1)
//  movePlane.toX = boxes(0)(0)
//  movePlane.toY = boxes(0)(1)
//
//  movePlane.play()


}
