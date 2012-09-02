   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
   import java.util.*;
	
   public class Checker    
   {
   
      private Checkers checker;
      private int x_cord, y_cord;
      private Point position;
      private final int ICON_SIZE = 50;
      
   	//specify_cord with constructor using constants
      public Checker(Checkers checker, int x_cord, int y_cord)
      {
         this.checker = checker;
         this.x_cord = x_cord;
         this.y_cord = y_cord;
         position = new Point(x_cord, y_cord);
       
         
      
      }
    
      public void drawChecker(JPanel panel, Graphics graphics)
      {
         checker.draw(panel, graphics, x_cord, y_cord);
      }
      
      public Point getPosistion()
      {
         return position;
      }
      
      public void setPosition(int x, int y)
      {
         position.setLocation(x, y);
         x_cord = x;
         y_cord = y;
      }
      
      public boolean checkX(int x)
      {
         if( ( x >= x_cord) && ( x < x_cord + ICON_SIZE))
         {
            return true;
         }
         else
         {
            return false;
         }
      }
      
      public boolean checkY(int y)
      {
         if( ( y >= y_cord) && ( y < y_cord + ICON_SIZE))
         {
            return true;
         }
         else
         {
            return false;
         }
         
      }
      
      public int getX()
      {
         return x_cord;
      }
      
      public int getY()
      {
         return y_cord;
      }
      public boolean isKing()
      {
         if((checker == Checkers.BLACK_KING) || (checker == Checkers.RED_KING))
         {
            return true;
         }
         else 
         {
            return false;
         }
      }
      public boolean isRed()
      {
         if(checker == Checkers.RED || checker == Checkers.RED_KING)
            return true;
         else 
            return false;
      }
      public boolean isBlack()
      {
         if(checker == Checkers.BLACK || checker == Checkers.BLACK_KING)
            return true;
         else 
            return false;
      }
   	
   }