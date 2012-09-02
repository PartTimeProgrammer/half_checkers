       /*
  Game of checkers
  
  Author James C Vernon
  
  This is the game of checkers.  Right now the game enforces some rules
  but relies on the users to enforce some as well.
  
  For instance I was unable to force the one tile move. Also the logice to force a jump
  is not correct either.  There is no automated win condition either.
  
  This is the most complex program I have written, and I dont think it messaures up.
  
  It was however a good lesson in programming in OO design.
  
  
  */
   
	
	
	import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
   import java.util.*;
	
   public class CheckerBoard extends JPanel implements MouseMotionListener, MouseListener 
   {
   
      private int ms_x, ms_y;
      private int hlt_x, hlt_y;
      private GameEngine game;
      int startX, startY, endX, endY;
      private boolean pressed = false;
      private String moveString;
   
      public CheckerBoard()
      {
      
         game = new GameEngine();
         addMouseMotionListener(this);
         addMouseListener(this);
      
      }  
      public void paintComponent(Graphics graphics)
      {
         game.paintBoard(this, graphics);
         game.paintCheckers(this, graphics);
      
      
         if(pressed)
         {
            graphics.setColor(Color.WHITE);
            graphics.drawOval(hlt_x, hlt_y, 50,50);
         }
      }  
      
      public void mouseMoved(java.awt.event.MouseEvent e)
      {
        	
            
      }
      public void mouseDragged(java.awt.event.MouseEvent e)
      {
      
      }
      public void mouseClicked(java.awt.event.MouseEvent e)
      {
      	
         
      		
         if(!pressed && game.isChecker(e.getX(), e.getY()))
         {
            startX = e.getX();
            startY = e.getY();
            pressed = true;
            hlt_x = game.getChecker(startX, startY).getX();
            hlt_y = game.getChecker(startX, startY).getY();
         }
         else if(pressed)
         {
            endX = e.getX();
            endY = e.getY();
            pressed = false;
            moveString = game.move(startX, startY, endX, endY);
            GameWindow.box.append(getPlayer() +": " + moveString + "\n");
         }
      
         repaint();  
      }
      public void mouseEntered(java.awt.event.MouseEvent e)
      {
        
      }
      public void mouseExited(java.awt.event.MouseEvent e)
      {
         
      }
      public void mousePressed(java.awt.event.MouseEvent e)
      {
         
        
      }
      public void mouseReleased(java.awt.event.MouseEvent e)
      {
         
      }
      public void reset()
      {
         
         game.makeRed();
         game.makeBlack();
         game.resetBoard();
         repaint();
      }
      public String getPlayer()
      {
         return game.playersTurn();
      }
      public String getMove()
      {
         return moveString;
      }
   	   
   
   
   }
