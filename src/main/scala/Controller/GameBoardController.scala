package Controller

import scalafx.Includes._
import scalafx.animation.TranslateTransition
import scalafx.application.Platform
import scalafx.scene.control.Label
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.AnchorPane
import scalafx.util.Duration
import scalafxml.core.macros.sfxml


import scala.collection.mutable.ArrayBuffer
import Model.{Player, Plane}
import scalafx.scene.control.Alert
import MainSystem.MyGame
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.effect.Glow
import scalafx.animation.FadeTransition
import scalafx.animation.Animation
import scalafx.event.ActionEvent
import scala.util.Random
import Actors.Client.Roll
import Actors.Client.SelectPlane
import scalafx.scene.control.Button
import scalafx.scene.control.TextField



case class MainRoute(mRoute: ImageView, color: String, isGoalRoute: Boolean = false, isFlyRoute: Boolean = false, container: Plane = null)
// case class Hangar(hangar: ImageView, isEmpty: )

@sfxml
class GameBoardController (
                            var yellowHouse1 : ImageView, var yellowHouse2 : ImageView, var yellowHouse3 : ImageView, var yellowHouse4 : ImageView,
                            var blueHouse1 : ImageView, var blueHouse2 : ImageView, var blueHouse3 : ImageView, var blueHouse4 : ImageView,
                            var redHouse1 : ImageView, var redHouse2 : ImageView, var redHouse3 : ImageView, var redHouse4 : ImageView,
                            var greenHouse1 : ImageView, var greenHouse2 : ImageView, var greenHouse3 : ImageView, var greenHouse4 : ImageView,
                            var yellowStartPoint : ImageView, var blueStartPoint : ImageView, var redStartPoint : ImageView, var greenStartPoint : ImageView,
                            var mRoute1 :ImageView, var mRoute2:ImageView, var mRoute3:ImageView, var mRoute4:ImageView, var mRoute5:ImageView, var mRoute6:ImageView, var mRoute7:ImageView, var mRoute8:ImageView, var mRoute9:ImageView, var mRoute10:ImageView,
                            var mRoute11:ImageView, var mRoute12:ImageView, var mRoute13:ImageView, var mRoute14:ImageView, var mRoute15:ImageView, var mRoute16:ImageView, var mRoute17:ImageView, var mRoute18:ImageView, var mRoute19:ImageView, var mRoute20:ImageView,
                            var mRoute21:ImageView, var mRoute22:ImageView, var mRoute23:ImageView, var mRoute24:ImageView, var mRoute25:ImageView, var mRoute26:ImageView, var mRoute27:ImageView, var mRoute28:ImageView, var mRoute29:ImageView, var mRoute30:ImageView,
                            var mRoute31:ImageView, var mRoute32:ImageView, var mRoute33:ImageView, var mRoute34:ImageView, var mRoute35:ImageView, var mRoute36:ImageView, var mRoute37:ImageView, var mRoute38:ImageView, var mRoute39:ImageView, var mRoute40:ImageView,
                            var mRoute41:ImageView, var mRoute42:ImageView, var mRoute43:ImageView, var mRoute44:ImageView, var mRoute45:ImageView, var mRoute46:ImageView, var mRoute47:ImageView, var mRoute48:ImageView, var mRoute49:ImageView, var mRoute50:ImageView,
                            var mRoute51:ImageView, var mRoute52:ImageView,
                            var yRoute1 : ImageView, var yRoute2 : ImageView, var yRoute3 : ImageView, var yRoute4 : ImageView, var yRoute5 : ImageView, var yRoute6 : ImageView,
                            var bRoute1 : ImageView, var bRoute2 : ImageView, var bRoute3 : ImageView, var bRoute4 : ImageView, var bRoute5 : ImageView, var bRoute6 : ImageView,
                            var rRoute1 : ImageView, var rRoute2 : ImageView, var rRoute3 : ImageView, var rRoute4 : ImageView, var rRoute5 : ImageView, var rRoute6 : ImageView,
                            var gRoute1 : ImageView, var gRoute2 : ImageView, var gRoute3 : ImageView, var gRoute4 : ImageView, var gRoute5 : ImageView, var gRoute6 : ImageView,
                            var goalPoint : ImageView,
                            val board : AnchorPane,
                            var diceCount: Label,
                            var yellowScore: Label, var blueScore: Label, var redScore: Label, var greenScore: Label,
                            val status: Label, val rollBtn: Button, val fixedDice: TextField
                          ) {


//  while(i != 4){
//    var planeY+i : ImageView = new ImageView(new Image(getClass.getResourceAsStream("/ds/image/plane-yellow.png")))
//  }


val mRoutes = ArrayBuffer[MainRoute](
  MainRoute(mRoute1, "Red"),
  MainRoute(mRoute2, "Yellow"),
  MainRoute(mRoute3, "Blue"),
  MainRoute(mRoute4, "Green"),
  MainRoute(mRoute5, "Red", isFlyRoute = true),
  MainRoute(mRoute6, "Yellow"),
  MainRoute(mRoute7, "Blue"),
  MainRoute(mRoute8, "Green"),
  MainRoute(mRoute9, "Red"),
  MainRoute(mRoute10, "Yellow"),
  MainRoute(mRoute11, "Blue", isGoalRoute = true),
  MainRoute(mRoute12, "Green"),
  MainRoute(mRoute13, "Red"),
  MainRoute(mRoute14, "Yellow"),
  MainRoute(mRoute15, "Blue"),
  MainRoute(mRoute16, "Green"),
  MainRoute(mRoute17, "Red"),
  MainRoute(mRoute18, "Yellow", isFlyRoute = true),
  MainRoute(mRoute19, "Blue"),
  MainRoute(mRoute20, "Green"),
  MainRoute(mRoute21, "Red"),
  MainRoute(mRoute22, "Yellow"),
  MainRoute(mRoute23, "Blue"),
  MainRoute(mRoute24, "Green", isGoalRoute = true),
  MainRoute(mRoute25, "Red"),
  MainRoute(mRoute26, "Yellow"),
  MainRoute(mRoute27, "Blue"),
  MainRoute(mRoute28, "Green"),
  MainRoute(mRoute29, "Red"),
  MainRoute(mRoute30, "Yellow"),
  MainRoute(mRoute31, "Blue", isFlyRoute = true),
  MainRoute(mRoute32, "Green"),
  MainRoute(mRoute33, "Red"),
  MainRoute(mRoute34, "Yellow"),
  MainRoute(mRoute35, "Blue"),
  MainRoute(mRoute36, "Green"),
  MainRoute(mRoute37, "Red", isGoalRoute = true),
  MainRoute(mRoute38, "Yellow"),
  MainRoute(mRoute39, "Blue"),
  MainRoute(mRoute40, "Green"),
  MainRoute(mRoute41, "Red"),
  MainRoute(mRoute42, "Yellow"),
  MainRoute(mRoute43, "Blue"),
  MainRoute(mRoute44, "Green", isFlyRoute = true),
  MainRoute(mRoute45, "Red"),
  MainRoute(mRoute46, "Yellow"),
  MainRoute(mRoute47, "Blue"),
  MainRoute(mRoute48, "Green"),
  MainRoute(mRoute49, "Red"),
  MainRoute(mRoute50, "Yellow", isGoalRoute = true),
  MainRoute(mRoute51, "Blue"),
  MainRoute(mRoute52, "Green"),
);

val yellowHouse = ArrayBuffer[ImageView](yellowHouse1, yellowHouse2, yellowHouse3, yellowHouse4)
val yellowGoalRoute = ArrayBuffer[ImageView](mRoute50, yRoute1, yRoute2, yRoute3, yRoute4, yRoute5, yRoute6)
var planeY1 : ImageView  = new ImageView()
var planeY2: ImageView = new ImageView()
var planeY3: ImageView = new ImageView()
var planeY4: ImageView = new ImageView()
var yellowPlayer = new Player("Yellow", yellowStartPoint, mRoute1, yRoute6, yellowGoalRoute, mRoute30, "/image/plane-yellow1.png", yellowHouse, ArrayBuffer[ImageView](planeY1, planeY2, planeY3, planeY4 ))


val blueHouse = ArrayBuffer[ImageView](blueHouse1, blueHouse2, blueHouse3, blueHouse4)
val blueGoalRoute = ArrayBuffer[ImageView](mRoute11, bRoute1, bRoute2, bRoute3, bRoute4, bRoute5, bRoute6)
var planeB1 : ImageView  = new ImageView()
var planeB2: ImageView = new ImageView()
var planeB3: ImageView = new ImageView()
var planeB4: ImageView = new ImageView()
var bluePlayer = new Player("Blue", blueStartPoint, mRoute14, bRoute6, blueGoalRoute, mRoute43, "/image/plane-blue1.png", blueHouse, ArrayBuffer[ImageView](planeB1, planeB2, planeB3, planeB4 ))

val greenHouse = ArrayBuffer[ImageView](greenHouse1, greenHouse2, greenHouse3, greenHouse4)
val greenGoalRoute = ArrayBuffer[ImageView](mRoute24, gRoute1, gRoute2, gRoute3, gRoute4, gRoute5, gRoute6)
var planeG1 : ImageView  = new ImageView()
var planeG2: ImageView = new ImageView()
var planeG3: ImageView = new ImageView()
var planeG4: ImageView = new ImageView()
var greenPlayer = new Player("Green", greenStartPoint, mRoute27, gRoute6, greenGoalRoute, mRoute4, "/image/plane-green1.png", greenHouse, ArrayBuffer[ImageView](planeG1, planeG2, planeG3, planeG4 ))

val redHouse = ArrayBuffer[ImageView](redHouse1, redHouse2, redHouse3, redHouse4)
val redGoalRoute = ArrayBuffer[ImageView](mRoute37, rRoute1, rRoute2, rRoute3, rRoute4, rRoute5, rRoute6)
var planeR1 : ImageView  = new ImageView()
var planeR2: ImageView = new ImageView()
var planeR3: ImageView = new ImageView()
var planeR4: ImageView = new ImageView()
var redPlayer = new Player("Red", redStartPoint, mRoute40, rRoute6, redGoalRoute, mRoute17, "/image/plane-red1.png", redHouse, ArrayBuffer[ImageView](planeR1, planeR2, planeR3, planeR4 ))

var pMap = Map[String, Player]("Yellow" -> yellowPlayer, "Blue" -> bluePlayer, "Green" -> greenPlayer, "Red" -> redPlayer)
var players = Map[String, Player]()
var currentPlayerColour = ""

var fadeTransitionList: ArrayBuffer[FadeTransition] = ArrayBuffer[FadeTransition]()
/**********************************************Server Call***************************************/
def initialize(players: Iterable[String]): Unit = {
  for(player <- players){
    this.players += (player -> pMap(player))
  }

  for((k,v) <- this.players){
    v.initialise()
    for(i <- 0 to 3) {
      board.getChildren().addAll(v.planeContainer(i).planeRef)
      v.planeContainer(i).planeRef.onMouseClicked = null
    }
  }
}

def serverAskRoll(colour: String): Unit = {
  var currentPlayer = players(colour)
  currentPlayerColour = colour
  rollBtn.disable = false
}

def serverAskSelectPlane(colour: String): Unit = {
  var currentPlayer = players(colour)
  currentPlayerColour = colour
  for(plane <- currentPlayer.planeContainer){
    if(!plane.atGoal)
    {
      activateFade(plane.planeRef)
      plane.planeRef.onMouseClicked = myMoveToStart
    }
  }
}

def displayStatus(txt: String): Unit ={
  status.text.value = txt
}

def updateDiceValue(dice: Int): Unit ={
  diceCount.text.value = dice.toString()
}

def serverAskMove(index: Int, colour: String): Unit = {
  new Thread {
    override def run {
      currentPlayerColour = colour
      var currentPlayer = players(currentPlayerColour)
      var dice = diceCount.text.value.toInt

      var plane = currentPlayer.planeContainer(index)

      var planeRef = plane.planeRef
      var x, y = 0.0


      // when plane in hangar/house
      if(currentPlayer.house.indexOf(plane.position) >= 0)
      {
        Platform.runLater(new Runnable(){
          override def run {
            x = currentPlayer.startPoint.layoutX() - plane.position.layoutX()
            y = currentPlayer.startPoint.layoutY() - plane.position.layoutY()
            movePlane(planeRef, x, y)
            plane.position = currentPlayer.startPoint
          }
        })
        Thread.sleep(500)
        dice = 0
        return;
      }
      
     
      //when player is at start point
      if(plane.position == currentPlayer.startPoint)
      {
        Platform.runLater(new Runnable(){
          override def run {
            x = currentPlayer.startRoute.layoutX() - plane.position.layoutX()
            y = currentPlayer.startRoute.layoutY() - plane.position.layoutY()
            movePlane(planeRef, x, y)
            plane.position = currentPlayer.startRoute
          }
        })
        Thread.sleep(500)
        dice -= 1
      }

      
      //dummy value
      var routePosition = 0
      //keep updating the next position
      var nextPosition:ImageView = null

      if(plane.isOnGoalRoute){
        routePosition = currentPlayer.goalRoute.indexOf(plane.position)
      }
      else{
        routePosition = mRoutes.indexWhere(_.mRoute == plane.position)
      }

      for(i <- 0 to (dice - 1))
      {
        // when player is on goal route
        if(plane.isOnGoalRoute){
          
          //ensure player move back and forth in goal route
          if(routePosition >= currentPlayer.goalRoute.size - 1){
            plane.toGoalDirection = false
          }

          if(routePosition == 0){
            plane.toGoalDirection = true
          }

          if(plane.toGoalDirection){
            routePosition += 1
          } 
          else{
            routePosition -= 1
          }
          
          nextPosition = currentPlayer.goalRoute(routePosition)
        }
        //when player is on normal route
        else{
          routePosition += 1
          //when value exceed array size
          if(routePosition >= mRoutes.size - 1){
            routePosition = 0 
          }

          nextPosition = mRoutes(routePosition).mRoute

          // change Plane state when next position is on goal route
          if(mRoutes(routePosition).isGoalRoute && mRoutes(routePosition).color == currentPlayer.color){
            plane.isOnGoalRoute = true
            routePosition = 0
          }
        }
        
        x = nextPosition.layoutX() - planeRef.layoutX()
        y = nextPosition.layoutY() - planeRef.layoutY()

        Platform.runLater(new Runnable(){
          override def run {
            movePlane(planeRef, x, y)
            plane.position = nextPosition
          }
        })
        Thread.sleep(500)
      }


      //evaluate in the last position
      if(!plane.isOnGoalRoute){
        var lastPositionIndex = mRoutes.indexWhere(_.mRoute == plane.position)
        
        if(mRoutes(lastPositionIndex).color == currentPlayer){
          if(mRoutes(lastPositionIndex).isFlyRoute){
            nextPosition = currentPlayer.flyPoint

            x = nextPosition.layoutX() - planeRef.layoutX()
            y = nextPosition.layoutY() - planeRef.layoutY()
          }else{
            //find next point with same colour
          }

          Platform.runLater(new Runnable(){
            override def run {
              movePlane(planeRef, x, y)
              plane.position = nextPosition
            }
            Thread.sleep(500)
          })
        }
      }else{
        if(plane.position == currentPlayer.goalPoint){
          plane.atGoal = true
          updateGoalPoint(currentPlayer.planeContainer)
        }
      }

    }
  }.start()

  if(this.players(colour).planeContainer.count(_.atGoal == true) == 4){
    MyGame.clientRef ! "win"
  }else{
    MyGame.clientRef ! "move done"
  }

  //game end condition
  

}

def serverAskDeclareWinner(colour: String): Unit = {
  val alert = new Alert(AlertType.Information) {
    initOwner(MyGame.stage)
    title = "Game Ended"
    headerText = s"$colour player won the game."
  }.showAndWait()

  MyGame.goToMainPage()
}
/*************************************************************************************/

def movePlane(plane: ImageView,x: Double, y: Double): Unit ={
  val movePlane:TranslateTransition = new TranslateTransition(new Duration(500), plane)

  movePlane.toX = x // move x coordinate
  movePlane.toY = y//move y coordinate
  movePlane.play()
}


def activateFade(plane: ImageView): Unit ={
  var fd = new FadeTransition(new Duration(500), plane)
  fd.setFromValue(1.0)
  fd.setToValue(0.6)
  fd.setCycleCount(Animation.INDEFINITE)
  fd.play()
  fadeTransitionList += fd
}

def deactivateFade(plane: ImageView): Unit ={
  for(fd <- fadeTransitionList){
      fd.stop()
  }
  fadeTransitionList.clear()
}

def rollDice(action: ActionEvent): Unit ={
  var rnd = Random
  var dice = 0
  if(fixedDice.text.value.toInt > 0){
    dice = fixedDice.text.value.toInt
  }
  else{
    dice = 1 + rnd.nextInt((6-1)+1)
  }
  rollBtn.disable = true
  MyGame.clientRef ! Roll(dice)
}

def updateGoalPoint(planes: ArrayBuffer[Plane]): Unit = {
  var goalPlane = ArrayBuffer[ImageView]()
  for(plane <- planes){
    if(plane.atGoal){
      goalPlane += plane.planeRef
    }
  }

  for(plane <- goalPlane){
    plane.image_=(new Image(s"/image/plane-${currentPlayerColour.toLowerCase()}${goalPlane.size}.png"))
  }
}

  ////////////////////////////////////////////////// Get Mouse Click on Plane///////////////////////////////////////////////////////
def myMoveToStart(m:MouseEvent) = {
  val img:ImageView = m.getSource().asInstanceOf[javafx.scene.image.ImageView]
  var player = players(currentPlayerColour)
  var index = player.planeContainer.indexWhere( _.planeRef == img)
  deactivateFade(img)
  for(plane <- player.planeContainer){
    plane.planeRef.onMouseClicked = null
  }

  MyGame.clientRef ! SelectPlane(index)
}

  






}
