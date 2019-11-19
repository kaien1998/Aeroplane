package ds

import ds.view.GameBoardController
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

import scala.concurrent._

object myGame extends JFXApp{

  val rootResource = getClass.getResource("/ds/GameBoard1.fxml")
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load();
  val roots = loader.getRoot[jfxs.layout.AnchorPane]

  stage = new PrimaryStage{
    title = "AeroplaneGame"
    scene = new Scene{
      root = roots
    }
  }

}
