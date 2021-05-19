/*Application class that will hold the game itself - Mackenzie Kimmel*/
 
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.geometry.*;
import javafx.stage.*;
import java.util.*;
import javafx.scene.paint.Color;

public class GameWindow extends Application

{
  boolean win = false;
  //create a new mazecanvas object
   MazeCanvas canvas = new MazeCanvas();
  
  public void start (Stage stage)
   {
     //add a new flow pane named top
     FlowPane top = new FlowPane();
     
     //set allignment to flow pane to center (Pos.CENTER)
     top.setAlignment(Pos.CENTER);
     
     
     //add canvas to flow pane
     top.getChildren().add(canvas);
     
     //add key listener
      top.setOnKeyPressed(new KeyListenerDown());
     
     //set scene
     Scene scene = new Scene(top, 525, 525);
     stage.setScene(scene);
     /*if (win == false)
     {   
          stage.setTitle(" Fancy Maze Game ");
     }
     else 
      {stage.setTitle("You Win!");}*/
      
      if( canvas.getWin())
      {
          stage.setTitle(" Fancy Maze Game ");

      }
      else 
      {
         stage.setTitle("You Win!"); 
      }
      
     stage.show();
     //set focus to flow pane
     top.requestFocus();
    }
    
  public static void main(String[] args)
   {
      launch(args);
   }
   
   public class KeyListenerDown implements EventHandler<KeyEvent>  
   {
     //KeyEvent action
      public void handle(KeyEvent event) 
      {
          KeyCode key = event.getCode();
          canvas.move(key);
         
      }
   }

 }