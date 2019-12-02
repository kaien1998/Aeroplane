package Controller

import scalafx.Includes._
import scalafx.animation.TranslateTransition
import scalafx.scene.control.Label
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.AnchorPane
import scalafx.util.Duration
import scalafxml.core.macros.sfxml

import scala.collection.mutable.ArrayBuffer


case class MainRoute(mRoute: ImageView, color: String, isGoalRoute: Boolean)
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
                            val status: Label
                          ) {


//  while(i != 4){
//    var planeY+i : ImageView = new ImageView(new Image(getClass.getResourceAsStream("/ds/image/plane-yellow.png")))
//  }

val mRoutes = ArrayBuffer[MainRoute](
  MainRoute(mRoute1, "Red", false),
  MainRoute(mRoute2, "Yellow", false),
  MainRoute(mRoute3, "Blue", false),
  MainRoute(mRoute4, "Green", false),
  MainRoute(mRoute5, "Red", false),
  MainRoute(mRoute6, "Yellow", false),
  MainRoute(mRoute7, "Blue", false),
  MainRoute(mRoute8, "Green", false),
  MainRoute(mRoute9, "Red", false),
  MainRoute(mRoute10, "Yellow", false),
  MainRoute(mRoute11, "Blue", true),
  MainRoute(mRoute12, "Green", false),
  MainRoute(mRoute13, "Red", false),
  MainRoute(mRoute14, "Yellow", false),
  MainRoute(mRoute15, "Blue", false),
  MainRoute(mRoute16, "Green", false),
  MainRoute(mRoute17, "Red", false),
  MainRoute(mRoute18, "Yellow", false),
  MainRoute(mRoute19, "Blue", false),
  MainRoute(mRoute20, "Green", false),
  MainRoute(mRoute21, "Red", false),
  MainRoute(mRoute22, "Yellow", false),
  MainRoute(mRoute23, "Blue", false),
  MainRoute(mRoute24, "Green", true),
  MainRoute(mRoute25, "Red", false),
  MainRoute(mRoute26, "Yellow", false),
  MainRoute(mRoute27, "Blue", false),
  MainRoute(mRoute28, "Green", false),
  MainRoute(mRoute29, "Red", false),
  MainRoute(mRoute30, "Yellow", false),
  MainRoute(mRoute31, "Blue", false),
  MainRoute(mRoute32, "Green", false),
  MainRoute(mRoute33, "Red", false),
  MainRoute(mRoute34, "Yellow", false),
  MainRoute(mRoute35, "Blue", false),
  MainRoute(mRoute36, "Green", false),
  MainRoute(mRoute37, "Red", true),
  MainRoute(mRoute38, "Yellow", false),
  MainRoute(mRoute39, "Blue", false),
  MainRoute(mRoute40, "Green", false),
  MainRoute(mRoute41, "Red", false),
  MainRoute(mRoute42, "Yellow", false),
  MainRoute(mRoute43, "Blue", false),
  MainRoute(mRoute44, "Green", false),
  MainRoute(mRoute45, "Red", false),
  MainRoute(mRoute46, "Yellow", false),
  MainRoute(mRoute47, "Blue", false),
  MainRoute(mRoute48, "Green", false),
  MainRoute(mRoute49, "Red", false),
  MainRoute(mRoute50, "Yellow", true),
  MainRoute(mRoute51, "Blue", false),
  MainRoute(mRoute52, "Green", false),
);

// yStartRoute = mRoute1
// bStartRoute = mRoute14
// gStartRoute = mRoute27
// rStartRoute = mRoute40


  ///////////////////////////////////////////Initialize Yellow Plane and Route///////////////////////////////////////////////////////////////////////////

  var yScore : Int = 0;
  var planeY1 : ImageView  = new ImageView(new Image(getClass.getResourceAsStream("/image/plane-yellow1.png")))
  var planeY2: ImageView = new ImageView(new Image(getClass.getResourceAsStream("/image/plane-yellow1.png")))
  var planeY3: ImageView = new ImageView(new Image(getClass.getResourceAsStream("/image/plane-yellow1.png")))
  var planeY4: ImageView = new ImageView(new Image(getClass.getResourceAsStream("/image/plane-yellow1.png")))
  var yellowRoute: ArrayBuffer[ImageView] = ArrayBuffer(goalPoint,yRoute6,yRoute5,yRoute4,yRoute3,yRoute2,yRoute1)
  var yellowRoute1: ArrayBuffer[ImageView] = ArrayBuffer(yellowStartPoint,mRoute1,mRoute2,mRoute3,mRoute4,mRoute5,mRoute6,mRoute7,mRoute8,mRoute9,mRoute10,mRoute11,mRoute12,mRoute13,mRoute14,mRoute15,mRoute16,mRoute17,mRoute18,mRoute19,mRoute20,
    mRoute21,mRoute22,mRoute23,mRoute24,mRoute25,mRoute26,mRoute27,mRoute28,mRoute29,mRoute30,mRoute31,mRoute32,mRoute33,mRoute34,mRoute35,mRoute36,mRoute37,mRoute38,mRoute39,mRoute40,
    mRoute41,mRoute42,mRoute43,mRoute44,mRoute45,mRoute46,mRoute47,mRoute48,mRoute49,mRoute50,yRoute1,yRoute2,yRoute3,yRoute4,yRoute5,yRoute6,goalPoint);
  var yellowRoute2: ArrayBuffer[ImageView] = ArrayBuffer(yellowStartPoint,mRoute1,mRoute2,mRoute3,mRoute4,mRoute5,mRoute6,mRoute7,mRoute8,mRoute9,mRoute10,mRoute11,mRoute12,mRoute13,mRoute14,mRoute15,mRoute16,mRoute17,mRoute18,mRoute19,mRoute20,
    mRoute21,mRoute22,mRoute23,mRoute24,mRoute25,mRoute26,mRoute27,mRoute28,mRoute29,mRoute30,mRoute31,mRoute32,mRoute33,mRoute34,mRoute35,mRoute36,mRoute37,mRoute38,mRoute39,mRoute40,
    mRoute41,mRoute42,mRoute43,mRoute44,mRoute45,mRoute46,mRoute47,mRoute48,mRoute49,mRoute50,yRoute1,yRoute2,yRoute3,yRoute4,yRoute5,yRoute6,goalPoint);
  var yellowRoute3: ArrayBuffer[ImageView] = ArrayBuffer(yellowStartPoint,mRoute1,mRoute2,mRoute3,mRoute4,mRoute5,mRoute6,mRoute7,mRoute8,mRoute9,mRoute10,mRoute11,mRoute12,mRoute13,mRoute14,mRoute15,mRoute16,mRoute17,mRoute18,mRoute19,mRoute20,
    mRoute21,mRoute22,mRoute23,mRoute24,mRoute25,mRoute26,mRoute27,mRoute28,mRoute29,mRoute30,mRoute31,mRoute32,mRoute33,mRoute34,mRoute35,mRoute36,mRoute37,mRoute38,mRoute39,mRoute40,
    mRoute41,mRoute42,mRoute43,mRoute44,mRoute45,mRoute46,mRoute47,mRoute48,mRoute49,mRoute50,yRoute1,yRoute2,yRoute3,yRoute4,yRoute5,yRoute6,goalPoint);
  var yellowRoute4: ArrayBuffer[ImageView] = ArrayBuffer(yellowStartPoint,mRoute1,mRoute2,mRoute3,mRoute4,mRoute5,mRoute6,mRoute7,mRoute8,mRoute9,mRoute10,mRoute11,mRoute12,mRoute13,mRoute14,mRoute15,mRoute16,mRoute17,mRoute18,mRoute19,mRoute20,
    mRoute21,mRoute22,mRoute23,mRoute24,mRoute25,mRoute26,mRoute27,mRoute28,mRoute29,mRoute30,mRoute31,mRoute32,mRoute33,mRoute34,mRoute35,mRoute36,mRoute37,mRoute38,mRoute39,mRoute40,
    mRoute41,mRoute42,mRoute43,mRoute44,mRoute45,mRoute46,mRoute47,mRoute48,mRoute49,mRoute50,yRoute1,yRoute2,yRoute3,yRoute4,yRoute5,yRoute6,goalPoint);

///////////////////////////////////////////Initialize Blue Plane and Route///////////////////////////////////////////////////////////////////////////

  var bScore : Int = 0;
  var planeB1 : ImageView  = new ImageView(new Image(getClass.getResourceAsStream("/image/plane-blue1.png")))
  var planeB2: ImageView = new ImageView(new Image(getClass.getResourceAsStream("/image/plane-blue1.png")))
  var planeB3: ImageView = new ImageView(new Image(getClass.getResourceAsStream("/image/plane-blue1.png")))
  var planeB4: ImageView = new ImageView(new Image(getClass.getResourceAsStream("/image/plane-blue1.png")))
  var blueRoute: ArrayBuffer[ImageView] = ArrayBuffer(goalPoint,bRoute6,bRoute5,bRoute4,bRoute3,bRoute2,bRoute1)
  var blueRoute1: ArrayBuffer[ImageView] = ArrayBuffer(blueStartPoint,mRoute14,mRoute15,mRoute16,mRoute17,mRoute18,mRoute19,mRoute20,mRoute21,mRoute22,mRoute23,mRoute24,
    mRoute25,mRoute26,mRoute27,mRoute28,mRoute29,mRoute30,mRoute31,mRoute32,mRoute33,mRoute34,mRoute35,mRoute36,mRoute37,mRoute38,mRoute39,mRoute40,mRoute41,mRoute42,mRoute43,mRoute44,
    mRoute45,mRoute46,mRoute47,mRoute48,mRoute49,mRoute50,mRoute51,mRoute52,mRoute1,mRoute2,mRoute3,mRoute4,mRoute5,mRoute6,mRoute7,mRoute8,mRoute9,mRoute10,mRoute11,bRoute1,bRoute2,bRoute3,bRoute4,bRoute5,bRoute6,goalPoint);
  var blueRoute2: ArrayBuffer[ImageView] = ArrayBuffer(blueStartPoint,mRoute14,mRoute15,mRoute16,mRoute17,mRoute18,mRoute19,mRoute20,mRoute21,mRoute22,mRoute23,mRoute24,
    mRoute25,mRoute26,mRoute27,mRoute28,mRoute29,mRoute30,mRoute31,mRoute32,mRoute33,mRoute34,mRoute35,mRoute36,mRoute37,mRoute38,mRoute39,mRoute40,mRoute41,mRoute42,mRoute43,mRoute44,
    mRoute45,mRoute46,mRoute47,mRoute48,mRoute49,mRoute50,mRoute51,mRoute52,mRoute1,mRoute2,mRoute3,mRoute4,mRoute5,mRoute6,mRoute7,mRoute8,mRoute9,mRoute10,mRoute11,bRoute1,bRoute2,bRoute3,bRoute4,bRoute5,bRoute6,goalPoint);
  var blueRoute3: ArrayBuffer[ImageView] = ArrayBuffer(blueStartPoint,mRoute14,mRoute15,mRoute16,mRoute17,mRoute18,mRoute19,mRoute20,mRoute21,mRoute22,mRoute23,mRoute24,
    mRoute25,mRoute26,mRoute27,mRoute28,mRoute29,mRoute30,mRoute31,mRoute32,mRoute33,mRoute34,mRoute35,mRoute36,mRoute37,mRoute38,mRoute39,mRoute40,mRoute41,mRoute42,mRoute43,mRoute44,
    mRoute45,mRoute46,mRoute47,mRoute48,mRoute49,mRoute50,mRoute51,mRoute52,mRoute1,mRoute2,mRoute3,mRoute4,mRoute5,mRoute6,mRoute7,mRoute8,mRoute9,mRoute10,mRoute11,bRoute1,bRoute2,bRoute3,bRoute4,bRoute5,bRoute6,goalPoint);
  var blueRoute4: ArrayBuffer[ImageView] = ArrayBuffer(blueStartPoint,mRoute14,mRoute15,mRoute16,mRoute17,mRoute18,mRoute19,mRoute20,mRoute21,mRoute22,mRoute23,mRoute24,
    mRoute25,mRoute26,mRoute27,mRoute28,mRoute29,mRoute30,mRoute31,mRoute32,mRoute33,mRoute34,mRoute35,mRoute36,mRoute37,mRoute38,mRoute39,mRoute40,mRoute41,mRoute42,mRoute43,mRoute44,
    mRoute45,mRoute46,mRoute47,mRoute48,mRoute49,mRoute50,mRoute51,mRoute52,mRoute1,mRoute2,mRoute3,mRoute4,mRoute5,mRoute6,mRoute7,mRoute8,mRoute9,mRoute10,mRoute11,bRoute1,bRoute2,bRoute3,bRoute4,bRoute5,bRoute6,goalPoint);

///////////////////////////////////////////Initialize Red Plane and Route///////////////////////////////////////////////////////////////////////////

  var rScore : Int = 0;
  var planeR1 : ImageView  = new ImageView(new Image(getClass.getResourceAsStream("/image/plane-red1.png")))
  var planeR2: ImageView = new ImageView(new Image(getClass.getResourceAsStream("/image/plane-red1.png")))
  var planeR3: ImageView = new ImageView(new Image(getClass.getResourceAsStream("/image/plane-red1.png")))
  var planeR4: ImageView = new ImageView(new Image(getClass.getResourceAsStream("/image/plane-red1.png")))
  var redRoute: ArrayBuffer[ImageView] = ArrayBuffer(goalPoint,rRoute6,rRoute5,rRoute4,rRoute3,rRoute2,rRoute1)
  var redRoute1: ArrayBuffer[ImageView] = ArrayBuffer(redStartPoint,mRoute40,mRoute41,mRoute42,mRoute43,mRoute44,mRoute45,mRoute46,mRoute47,mRoute48,mRoute49,mRoute50,
    mRoute51,mRoute52,mRoute1,mRoute2,mRoute3,mRoute4,mRoute5,mRoute6,mRoute7,mRoute8,mRoute9,mRoute10,mRoute11,mRoute12,mRoute13,mRoute14,mRoute15,mRoute16,mRoute17,mRoute18,mRoute19,mRoute20,
    mRoute21,mRoute22,mRoute23,mRoute24,mRoute25,mRoute26,mRoute27,mRoute28,mRoute29,mRoute30,mRoute31,mRoute32,mRoute33,mRoute34,mRoute35,mRoute36,mRoute37,rRoute1,rRoute2,rRoute3,rRoute4,rRoute5,rRoute6,goalPoint);
  var redRoute2: ArrayBuffer[ImageView] = ArrayBuffer(redStartPoint,mRoute40,mRoute41,mRoute42,mRoute43,mRoute44,mRoute45,mRoute46,mRoute47,mRoute48,mRoute49,mRoute50,
    mRoute51,mRoute52,mRoute1,mRoute2,mRoute3,mRoute4,mRoute5,mRoute6,mRoute7,mRoute8,mRoute9,mRoute10,mRoute11,mRoute12,mRoute13,mRoute14,mRoute15,mRoute16,mRoute17,mRoute18,mRoute19,mRoute20,
    mRoute21,mRoute22,mRoute23,mRoute24,mRoute25,mRoute26,mRoute27,mRoute28,mRoute29,mRoute30,mRoute31,mRoute32,mRoute33,mRoute34,mRoute35,mRoute36,mRoute37,rRoute1,rRoute2,rRoute3,rRoute4,rRoute5,rRoute6,goalPoint)
  var redRoute3: ArrayBuffer[ImageView] = ArrayBuffer(redStartPoint,mRoute40,mRoute41,mRoute42,mRoute43,mRoute44,mRoute45,mRoute46,mRoute47,mRoute48,mRoute49,mRoute50,
    mRoute51,mRoute52,mRoute1,mRoute2,mRoute3,mRoute4,mRoute5,mRoute6,mRoute7,mRoute8,mRoute9,mRoute10,mRoute11,mRoute12,mRoute13,mRoute14,mRoute15,mRoute16,mRoute17,mRoute18,mRoute19,mRoute20,
    mRoute21,mRoute22,mRoute23,mRoute24,mRoute25,mRoute26,mRoute27,mRoute28,mRoute29,mRoute30,mRoute31,mRoute32,mRoute33,mRoute34,mRoute35,mRoute36,mRoute37,rRoute1,rRoute2,rRoute3,rRoute4,rRoute5,rRoute6,goalPoint)
  var redRoute4: ArrayBuffer[ImageView] = ArrayBuffer(redStartPoint,mRoute40,mRoute41,mRoute42,mRoute43,mRoute44,mRoute45,mRoute46,mRoute47,mRoute48,mRoute49,mRoute50,
    mRoute51,mRoute52,mRoute1,mRoute2,mRoute3,mRoute4,mRoute5,mRoute6,mRoute7,mRoute8,mRoute9,mRoute10,mRoute11,mRoute12,mRoute13,mRoute14,mRoute15,mRoute16,mRoute17,mRoute18,mRoute19,mRoute20,
    mRoute21,mRoute22,mRoute23,mRoute24,mRoute25,mRoute26,mRoute27,mRoute28,mRoute29,mRoute30,mRoute31,mRoute32,mRoute33,mRoute34,mRoute35,mRoute36,mRoute37,rRoute1,rRoute2,rRoute3,rRoute4,rRoute5,rRoute6,goalPoint)

///////////////////////////////////////////Initialize Green Plane and Route///////////////////////////////////////////////////////////////////////////

  var gScore : Int = 0;
  var planeG1 : ImageView  = new ImageView(new Image(getClass.getResourceAsStream("/image/plane-green1.png")))
  var planeG2: ImageView = new ImageView(new Image(getClass.getResourceAsStream("/image/plane-green1.png")))
  var planeG3: ImageView = new ImageView(new Image(getClass.getResourceAsStream("/image/plane-green1.png")))
  var planeG4: ImageView = new ImageView(new Image(getClass.getResourceAsStream("/image/plane-green1.png")))
  var greenRoute: ArrayBuffer[ImageView] = ArrayBuffer(goalPoint,gRoute6,gRoute5,gRoute4,gRoute3,gRoute2,gRoute1)
  var greenRoute1: ArrayBuffer[ImageView] = ArrayBuffer(greenStartPoint,mRoute27,mRoute28,mRoute29,mRoute30,mRoute31,mRoute32,mRoute33,mRoute34,mRoute35,mRoute36,mRoute37,
    mRoute38,mRoute39,mRoute40,mRoute41,mRoute42,mRoute43,mRoute44,mRoute45,mRoute46,mRoute47,mRoute48,mRoute49,mRoute50,mRoute51,mRoute52,mRoute1,mRoute2,mRoute3,mRoute4,mRoute5,mRoute6,mRoute7,
    mRoute8,mRoute9,mRoute10,mRoute11,mRoute12,mRoute13,mRoute14,mRoute15,mRoute16,mRoute17,mRoute18,mRoute19,mRoute20,mRoute21,mRoute22,mRoute23,mRoute24,gRoute1,gRoute2,gRoute3,gRoute4,gRoute5,gRoute6,goalPoint);
  var greenRoute2: ArrayBuffer[ImageView] = ArrayBuffer(greenStartPoint,mRoute27,mRoute28,mRoute29,mRoute30,mRoute31,mRoute32,mRoute33,mRoute34,mRoute35,mRoute36,mRoute37,
    mRoute38,mRoute39,mRoute40,mRoute41,mRoute42,mRoute43,mRoute44,mRoute45,mRoute46,mRoute47,mRoute48,mRoute49,mRoute50,mRoute51,mRoute52,mRoute1,mRoute2,mRoute3,mRoute4,mRoute5,mRoute6,mRoute7,
    mRoute8,mRoute9,mRoute10,mRoute11,mRoute12,mRoute13,mRoute14,mRoute15,mRoute16,mRoute17,mRoute18,mRoute19,mRoute20,mRoute21,mRoute22,mRoute23,mRoute24,gRoute1,gRoute2,gRoute3,gRoute4,gRoute5,gRoute6,goalPoint);
  var greenRoute3: ArrayBuffer[ImageView] = ArrayBuffer(greenStartPoint,mRoute27,mRoute28,mRoute29,mRoute30,mRoute31,mRoute32,mRoute33,mRoute34,mRoute35,mRoute36,mRoute37,
    mRoute38,mRoute39,mRoute40,mRoute41,mRoute42,mRoute43,mRoute44,mRoute45,mRoute46,mRoute47,mRoute48,mRoute49,mRoute50,mRoute51,mRoute52,mRoute1,mRoute2,mRoute3,mRoute4,mRoute5,mRoute6,mRoute7,
    mRoute8,mRoute9,mRoute10,mRoute11,mRoute12,mRoute13,mRoute14,mRoute15,mRoute16,mRoute17,mRoute18,mRoute19,mRoute20,mRoute21,mRoute22,mRoute23,mRoute24,gRoute1,gRoute2,gRoute3,gRoute4,gRoute5,gRoute6,goalPoint);
  var greenRoute4: ArrayBuffer[ImageView] = ArrayBuffer(greenStartPoint,mRoute27,mRoute28,mRoute29,mRoute30,mRoute31,mRoute32,mRoute33,mRoute34,mRoute35,mRoute36,mRoute37,
    mRoute38,mRoute39,mRoute40,mRoute41,mRoute42,mRoute43,mRoute44,mRoute45,mRoute46,mRoute47,mRoute48,mRoute49,mRoute50,mRoute51,mRoute52,mRoute1,mRoute2,mRoute3,mRoute4,mRoute5,mRoute6,mRoute7,
    mRoute8,mRoute9,mRoute10,mRoute11,mRoute12,mRoute13,mRoute14,mRoute15,mRoute16,mRoute17,mRoute18,mRoute19,mRoute20,mRoute21,mRoute22,mRoute23,mRoute24,gRoute1,gRoute2,gRoute3,gRoute4,gRoute5,gRoute6,goalPoint);

//////////////////////////////////////////////////////General Initialization//////////////////////////////////////////////////////////////////////////

  var mainRoute = ArrayBuffer (0);
  var boxes = Array.ofDim[Double] (2);
  //val dice = 5;
  var r = scala.util.Random
  var dice: Int= 0;


  def spawnYellow() {

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
    planeY1.onMouseClicked = myMoveToStart //need to make this = null first, then when its player turn, enable again
    planeY2.onMouseClicked = myMoveToStart
    planeY3.onMouseClicked = myMoveToStart
    planeY4.onMouseClicked = myMoveToStart
  }

  def spawnBlue(): Unit ={

    board.getChildren().addAll(planeB1,planeB2,planeB3,planeB4)
    planeB1.fitWidth = 42
    planeB1.fitHeight = 42
    planeB1.layoutX = blueHouse1.layoutX()
    planeB1.layoutY = blueHouse1.layoutY()
    planeB2.fitWidth = 42
    planeB2.fitHeight = 42
    planeB2.layoutX = blueHouse2.layoutX()
    planeB2.layoutY = blueHouse2.layoutY()
    planeB3.fitWidth = 42
    planeB3.fitHeight = 42
    planeB3.layoutX = blueHouse3.layoutX()
    planeB3.layoutY = blueHouse3.layoutY()
    planeB4.fitWidth = 42
    planeB4.fitHeight = 42
    planeB4.layoutX = blueHouse4.layoutX()
    planeB4.layoutY = blueHouse4.layoutY()
    planeB1.onMouseClicked = myMoveToStart //need to make this = null first, then when its player turn, enable again
    planeB2.onMouseClicked = myMoveToStart
    planeB3.onMouseClicked = myMoveToStart
    planeB4.onMouseClicked = myMoveToStart
  }

  def spawnRed(): Unit ={

    board.getChildren().addAll(planeR1,planeR2,planeR3,planeR4)
    planeR1.fitWidth = 42
    planeR1.fitHeight = 42
    planeR1.layoutX = redHouse1.layoutX()
    planeR1.layoutY = redHouse1.layoutY()
    planeR2.fitWidth = 42
    planeR2.fitHeight = 42
    planeR2.layoutX = redHouse2.layoutX()
    planeR2.layoutY = redHouse2.layoutY()
    planeR3.fitWidth = 42
    planeR3.fitHeight = 42
    planeR3.layoutX = redHouse3.layoutX()
    planeR3.layoutY = redHouse3.layoutY()
    planeR4.fitWidth = 42
    planeR4.fitHeight = 42
    planeR4.layoutX = redHouse4.layoutX()
    planeR4.layoutY = redHouse4.layoutY()
    planeR1.onMouseClicked = myMoveToStart //need to make this = null first, then when its player turn, enable again
    planeR2.onMouseClicked = myMoveToStart
    planeR3.onMouseClicked = myMoveToStart
    planeR4.onMouseClicked = myMoveToStart
  }

  def spawnGreen(): Unit ={

    board.getChildren().addAll(planeG1,planeG2,planeG3,planeG4)
    planeG1.fitWidth = 42
    planeG1.fitHeight = 42
    planeG1.layoutX = greenHouse1.layoutX()
    planeG1.layoutY = greenHouse1.layoutY()
    planeG2.fitWidth = 42
    planeG2.fitHeight = 42
    planeG2.layoutX = greenHouse2.layoutX()
    planeG2.layoutY = greenHouse2.layoutY()
    planeG3.fitWidth = 42
    planeG3.fitHeight = 42
    planeG3.layoutX = greenHouse3.layoutX()
    planeG3.layoutY = greenHouse3.layoutY()
    planeG4.fitWidth = 42
    planeG4.fitHeight = 42
    planeG4.layoutX = greenHouse4.layoutX()
    planeG4.layoutY = greenHouse4.layoutY()
    planeG1.onMouseClicked = myMoveToStart //need to make this = null first, then when its player turn, enable again
    planeG2.onMouseClicked = myMoveToStart
    planeG3.onMouseClicked = myMoveToStart
    planeG4.onMouseClicked = myMoveToStart

  }

  def random(): Unit ={ //random dice 1-6
    dice = 1 + r.nextInt((6-1)+1)
    diceCount.text=dice.toString
  }


  ////////////////////////////////////////////////// Get Mouse Click on Plane///////////////////////////////////////////////////////
  def myMoveToStart(m:MouseEvent) = {

    val img:ImageView = m.getSource().asInstanceOf[javafx.scene.image.ImageView]

    if(img == planeY1) { ////////////////////////////////For Yellow Plane////////////////////////
      findDistanceY(img, yellowRoute1)
      movePlane(img,yellowRoute1)
    }else if(img == planeY2) {
      findDistanceY(img, yellowRoute2)
      movePlane(img,yellowRoute2)
    }else if(img == planeY3) {
      findDistanceY(img, yellowRoute3)
      movePlane(img,yellowRoute3)
    }else if(img == planeY4) {
      findDistanceY(img, yellowRoute4)
      movePlane(img,yellowRoute4)
    }else if(img == planeB1) { ////////////////////////////For Blue Plane/////////////////////////
      findDistanceB(img, blueRoute1)
      movePlane(img,blueRoute1)
    }else if(img == planeB2) {
      findDistanceB(img, blueRoute2)
      movePlane(img,blueRoute2)
    }else if(img == planeB3) {
      findDistanceB(img, blueRoute3)
      movePlane(img,blueRoute3)
    }else if(img == planeB4) {
      findDistanceB(img, blueRoute4)
      movePlane(img,blueRoute4)
    }else if(img == planeR1) { //////////////////For Red Plane////////////////////////////////////
      findDistanceR(img, redRoute1)
      movePlane(img,redRoute1)
    }else if(img == planeR2) {
      findDistanceR(img, redRoute2)
      movePlane(img,redRoute2)
    }else if(img == planeR3) {
      findDistanceR(img, redRoute3)
      movePlane(img,redRoute3)
    }else if(img == planeR4) {
      findDistanceR(img, redRoute4)
      movePlane(img,redRoute4)
    }else if(img == planeG1) { /////////////////For Green Plane ///////////////////////////////
      findDistanceG(img, greenRoute1)
      movePlane(img,greenRoute1)
    }else if(img == planeG2) {
      findDistanceG(img, greenRoute2)
      movePlane(img,greenRoute2)
    }else if(img == planeG3) {
      findDistanceG(img, greenRoute3)
      movePlane(img,greenRoute3)
    }else if(img == planeG4) {
      findDistanceG(img, greenRoute4)
      movePlane(img,greenRoute4)
    }
  }

  ////////////////////////////////////////////Move Plane Core function//////////////////////////////////////////////////////////////

  def movePlane(plane: ImageView, route: ArrayBuffer[ImageView]): Unit ={
    val movePlane:TranslateTransition = new TranslateTransition(new Duration(1000), plane)

    movePlane.toX = boxes(0) // move x coordinate
    movePlane.toY = boxes(1)//move y coordinate
    movePlane.play()
  }

  //////////////////////////////////////////////////Find Distance for Yellow planes///////////////////////////////////////////////////
  //Will be repeated 4 times, can be combined de, but logic will be abit confusing

  def findDistanceY (plane: ImageView, route: ArrayBuffer[ImageView]) = {
    if(route.contains(yellowStartPoint)){
      boxes(0) = route(0).layoutX() - plane.layoutX()
      boxes(1) = route(0).layoutY() - plane.layoutY()
      route.remove(0)

    }else if(!(route.contains(yRoute1))){ //check if plane is in the yellow route to goal
      if (route.length < dice){ //plane did not reach goal, go back in steps
        var index = dice - route.length
        print(index)
        route.clear()
        while(index != -1){
          route.append(yellowRoute(index))
          index-=1
        }
        boxes(0) = route(0).layoutX() - plane.layoutX()
        boxes(1) = route(0).layoutY() - plane.layoutY()
        route.remove(0)
      } else if(route.length > dice){
        boxes(0) = route(dice-1).layoutX() - plane.layoutX()
        boxes(1) = route(dice-1).layoutY() - plane.layoutY()
        route.remove(0,dice)
      } else { //Plane reach goal
        boxes(0) = goalPoint.layoutX() - plane.layoutX()
        boxes(1) = goalPoint.layoutY() - plane.layoutY()
        print("Plane Reach Goal")
        yScore +=1
        yellowScore.text = yScore.toString
      }

    }else{
      boxes(0) = route(dice-1).layoutX() - plane.layoutX()
      boxes(1) = route(dice-1).layoutY() - plane.layoutY()
      route.remove(0,dice)
      //mainRoute:+yellowRoute1(dice-1)
      //print(route(0))
    }
  }

  //////////////////////////////////////////////////Find Distance for Blue planes///////////////////////////////////////////////////

  def findDistanceB (plane: ImageView, route: ArrayBuffer[ImageView]) = {
    if(route.contains(blueStartPoint)){
      boxes(0) = route(0).layoutX() - plane.layoutX()
      boxes(1) = route(0).layoutY() - plane.layoutY()
      route.remove(0)

    }else if(!(route.contains(bRoute1))){ //check if plane is in the blue route to goal
      if (route.length < dice){ //plane did not reach goal, go back in steps
        var index = dice - route.length
        print(index)
        route.clear()
        while(index != -1){
          route.append(blueRoute(index))
          index-=1
        }
        boxes(0) = route(0).layoutX() - plane.layoutX()
        boxes(1) = route(0).layoutY() - plane.layoutY()
        route.remove(0)
      } else if(route.length > dice){
        boxes(0) = route(dice-1).layoutX() - plane.layoutX()
        boxes(1) = route(dice-1).layoutY() - plane.layoutY()
        route.remove(0,dice)
      } else { //Plane reach goal
        boxes(0) = goalPoint.layoutX() - plane.layoutX()
        boxes(1) = goalPoint.layoutY() - plane.layoutY()
        print("Plane Reach Goal")
        bScore +=1
        blueScore.text = bScore.toString
      }

    }else{
      boxes(0) = route(dice-1).layoutX() - plane.layoutX()
      boxes(1) = route(dice-1).layoutY() - plane.layoutY()
      route.remove(0,dice)
      //mainRoute:+yellowRoute1(dice-1) //future function to add to main, so that can know when to kick players back
      //print(route(0))
    }
  }

  //////////////////////////////////////////////////Find Distance for Red planes///////////////////////////////////////////////////

  def findDistanceR (plane: ImageView, route: ArrayBuffer[ImageView]) = {
    if(route.contains(redStartPoint)){
      boxes(0) = route(0).layoutX() - plane.layoutX()
      boxes(1) = route(0).layoutY() - plane.layoutY()
      route.remove(0)

    }else if(!(route.contains(rRoute1))){ //check if plane is in the red route to goal
      if (route.length < dice){ //plane did not reach goal, go back in steps
        var index = dice - route.length
        print(index)
        route.clear()
        while(index != -1){
          route.append(redRoute(index))
          index-=1
        }
        boxes(0) = route(0).layoutX() - plane.layoutX()
        boxes(1) = route(0).layoutY() - plane.layoutY()
        route.remove(0)
      } else if(route.length > dice){
        boxes(0) = route(dice-1).layoutX() - plane.layoutX()
        boxes(1) = route(dice-1).layoutY() - plane.layoutY()
        route.remove(0,dice)
      } else { //Plane reach goal
        boxes(0) = goalPoint.layoutX() - plane.layoutX()
        boxes(1) = goalPoint.layoutY() - plane.layoutY()
        print("Plane Reach Goal")
        rScore +=1
        redScore.text = rScore.toString
      }

    }else{
      boxes(0) = route(dice-1).layoutX() - plane.layoutX()
      boxes(1) = route(dice-1).layoutY() - plane.layoutY()
      route.remove(0,dice)
      //mainRoute:+yellowRoute1(dice-1)
      //print(route(0))
    }
  }


  //////////////////////////////////////////////////Find Distance for Green planes///////////////////////////////////////////////////


  def findDistanceG (plane: ImageView, route: ArrayBuffer[ImageView]) = {
    if(route.contains(greenStartPoint)){
      boxes(0) = route(0).layoutX() - plane.layoutX()
      boxes(1) = route(0).layoutY() - plane.layoutY()
      route.remove(0)

    }else if(!(route.contains(gRoute1))){ //check if plane is in the yellow route to goal
      if (route.length < dice){ //plane did not reach goal, go back in steps
        var index = dice - route.length
        print(index)
        route.clear()
        while(index != -1){
          route.append(greenRoute(index))
          index-=1
        }
        boxes(0) = route(0).layoutX() - plane.layoutX()
        boxes(1) = route(0).layoutY() - plane.layoutY()
        route.remove(0)
      } else if(route.length > dice){
        boxes(0) = route(dice-1).layoutX() - plane.layoutX()
        boxes(1) = route(dice-1).layoutY() - plane.layoutY()
        route.remove(0,dice)
      } else { //Plane reach goal
        boxes(0) = goalPoint.layoutX() - plane.layoutX()
        boxes(1) = goalPoint.layoutY() - plane.layoutY()
        print("Plane Reach Goal")
        gScore +=1
        yellowScore.text = gScore.toString
      }

    }else{
      boxes(0) = route(dice-1).layoutX() - plane.layoutX()
      boxes(1) = route(dice-1).layoutY() - plane.layoutY()
      route.remove(0,dice)
      //mainRoute:+yellowRoute1(dice-1)
      //print(route(0))
    }
  }




}
