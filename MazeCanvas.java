/* MazeCanvas class to read in information from the file and draw*/
  
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
import java.lang.*;
import java.io.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class MazeCanvas extends Canvas
{
   private int x;
   private int y;
   private int xpos;
   private int ypos;
   private int zero=0;
   private boolean win;
   private KeyCode key; 
   private int checkX;
   private int checkY;
   
   //Create drawing
    GraphicsContext gc = getGraphicsContext2D();
   //make  2D array 21x21 Grid for squares
    Integer[][] gameBoard = new Integer [21][21];
   
     public MazeCanvas()
      {
        //set size of canvas
        super (525,525);
  
        //read in information from file
        //scanner
        try 
        {
             Scanner scan = new Scanner(new File("maze.txt"));
             
               while (scan.hasNextInt())              
               {
                   for (int i =0; i<21;i++)
                     {
                         for(int j=0; j<21; j++)
                         {                         
                           gameBoard[j][i]= scan.nextInt();
                                                      
                           //draw white sqaure for 0
                           if (gameBoard[j][i] ==0) 
                           {
                              zero++;                              
                              draw2(j*25, i*25); 
                              if (zero ==1 )
                              {
                                 xpos = j*25;
                                 ypos = i*25;
                                 drawPlayer(j*25, i*25);
                                 win = false;
                              } 
                              
                              // Change this - check last 0 in array = win 
                              if (zero ==200)
                              {
                                 win = true;
                              }  
                                                                                     
                           } 
                                                     
                           //draw black square for 1
                           if (gameBoard[j][i] ==1)
                           {
                              draw1( j*25, i*25);
         
                           } 
                                       
                         }
                       //System.out.println("zeros: "+zero);  
                     }
                }//end while
          }//end try
        catch (FileNotFoundException fnfe)
        {
          System.out.println("This file does not exist");
        }
         
       
      }//end constructor
      
      public void draw1(int x,int y)
      {
         this.x = x;
         this.y = y;
         gc.setFill(Color.BLACK);
         gc.fillRect(x,y,25,25);
      }
      
      public void draw2(int x, int y)
      {
         this.x = x;
         this.y = y;
         gc.setFill(Color.WHITE);
         gc.fillRect(x,y,25,25);
      }
      
      public void drawPlayer(int x, int y)
      {
         this.x = x;
         this.y = y;
         gc.clearRect(x,y,25,25);
         gc.setFill(Color.CYAN);
         gc.fillRect(x,y,25,25);
      }
      
      public void clearPlayer(int x, int y)
      {
         this.x = x;
         this.y = y;
         gc.clearRect(x,y,25,25);
         gc.setFill(Color.WHITE);
         gc.fillRect(x,y,25,25);
      
      }
      
      public void move(KeyCode key)
      {
       this.key = key;
       checkY = ypos/25;
       checkX = xpos/25;
       
            switch (key)
            {
             case UP : if (gameBoard[checkX][(checkY)-1]==0)
                         {
                           clearPlayer(xpos,ypos);
                           ypos -= 25;
                           drawPlayer(xpos,ypos);
                         }
               break;
               
             case DOWN:if (gameBoard[checkX][(checkY)+1]==0)
                         {
                           clearPlayer(xpos,ypos);
                           ypos += 25;
                           drawPlayer(xpos,ypos);
                         }

               break;
               
             case LEFT: if (gameBoard[(checkX)-1][(checkY)]==0)
                         {
                           clearPlayer(xpos,ypos);
                           xpos -= 25;
                           drawPlayer(xpos,ypos);
                         }
                     
                   break;
               
             case RIGHT:if (gameBoard[(checkX)+1][(checkY)]==0)
                         {
                           clearPlayer(xpos,ypos);
                           xpos += 25;
                           drawPlayer(xpos,ypos);
                         }
               break;
            }//end switch
            
          //if last 0 in array in 21st row- win 
          if (ypos/25 ==20)
               {
                    if (gameBoard[checkX][checkY]==0)
                    {
                       win = true;
                       System.out.println("You win");
                       gc.setFill(Color.PINK);
                       gc.fillRect(132,132,262,132);
                       gc.strokeText("YOU WIN!!", 223,198);
 
                    }
               }
      }
      
      
      public boolean getWin()
      {
          return win;
      }
      
      

      

}