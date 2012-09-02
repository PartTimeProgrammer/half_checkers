    /*
  Game of checkers
  
  Author James C Vernon
  
  This is the game of checkers.  Right now the game enforces some rules
  but relies on the users to enforce some as well.
  
  For instance I was unable to force the one tile move. Also the logic to force a jump
  is not correct either.  There is no automated win condition either.
  
  This is the most complex program I have written, and I dont think it messaures up.
  
  It was however a good lesson in programming in OO design.
  
  
  */
  
  
  
  
  
  
  
   import java.util.*;
   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;


   public class GameEngine
   {
      //, startTile, endTile;
      private Checker activeChecker;
      
      private ArrayList<Checker> redCheckers;
      private ArrayList<Checker> blackCheckers;
      private Tile [][] board;
      
      private boolean canJump, outOfBounds;
   
      private int xBoundsLower, yBoundsLower, xBoundsUpper, yBoundsUpper;
      private int tileSize, x_cord, y_cord, column, row;
      
      private final String RIGHT = "RIGHT";
      private final String LEFT = "LEFT";
      
      private final int ONE_TILE = 50, TWO_TILE = 100;
      private boolean redTurn; 
   
      public GameEngine( )
      {
         makeBoard();
         makeBlack();
         makeRed(); 
         getBounds();
         redTurn = true;
        
      }
   
      public Checker getChecker(int x, int y)
      {
         for(Checker checker: redCheckers)
         {
            if( (checker.checkX(x)) && (checker.checkY(y)) )
            { 
               return checker;
            }
          
            
         }
         for(Checker checker: blackCheckers)
         {
            if( (checker.checkX(x)) && (checker.checkY(y)) )
            { 
               return checker;
            }	 
          
         }
         return null;
      	
      }
    
      public Tile getTile(int x, int y)
      {
         for(int row = 0; row < board[column].length; row ++)
         { 
         //do all in row
            for(int column = 0; column < board[row].length; column++)
            {
               if( (board[row][column].checkX(x)) &&  (board[row][column].checkY(y)) )
               {
                  return board[row][column];
                  //break;
               }
            	
            }
            column = 0;
         } 
         return null;
      } 
      
   
      private boolean checkBounds(int x, int y)
      {
         if( (( x < xBoundsLower) && ( y < yBoundsLower)) || ((x > xBoundsUpper) && (y > yBoundsUpper)) )
         {
            return false;
         }
         else 
            return true;
      }
   		
      private void getBounds ()
      {
         xBoundsLower = board[0][0].getX();
         yBoundsLower = board[0][0].getY();
         int size = board.length;
         xBoundsUpper = board[size - 1 ][size - 1].getX();
         yBoundsUpper = board[size - 1][size - 1].getY();
      }
      public String move(int startX, int startY, int endX, int endY)
      {
      
         Tile startTile = getTile(startX, startY);
         Checker activeChecker = getChecker(startX,startY);
         Tile endTile = getTile(endX, endY);
       
         if( (!endTile.occupied()) && (endTile.isRed()) && (!endTile.equals(startTile)) )
         { 
         //see if the move is a jump and didnt make the jump
            if(canJump()) 
            {
               if( (!(didJump(activeChecker, startTile, endTile))) )
               {
                  JOptionPane.showMessageDialog(null, "Need to Jump");
                  return "Jump Please";
               } 
               //jump
               else if(didJump(activeChecker, startTile, endTile))
               {
                  jump(activeChecker, startTile, endTile);
                  activeChecker.setPosition(endTile.getX(), endTile.getY());
                  startTile.removeChecker();
                  endTile.addChecker();
                  makeKing(activeChecker);
                  changePlayer();
                  return "Jumped from "
                     + Integer.toString(startTile.getX())+", " + Integer.toString(startTile.getY()) +
                     " to " 
                     +  Integer.toString(endTile.getX())+", " + Integer.toString(endTile.getY());
               }
            }
            
            //regular move
            else if((activeChecker.isRed()) && ((endTile.getY()) >= (endTile.getY() - ONE_TILE)))
            {
               activeChecker.setPosition(endTile.getX(), endTile.getY());
               startTile.removeChecker();
               endTile.addChecker();
               makeKing(activeChecker);
               changePlayer();
               return "Moved from "
                  +  Integer.toString(startTile.getX())+", " + Integer.toString(startTile.getY()) +
                  " to " 
                  + Integer.toString(endTile.getX()) +", "+ Integer.toString(endTile.getY());
               
            }
            else if((activeChecker.isBlack()) && ((endTile.getY()) <= (endTile.getY() + ONE_TILE) ))
            {
               activeChecker.setPosition(endTile.getX(), endTile.getY());
               startTile.removeChecker();
               endTile.addChecker();
               makeKing(activeChecker);
               changePlayer();
               return "Moved from "
                  +  Integer.toString(startTile.getX())+", " + Integer.toString(startTile.getY()) +
                  " to " 
                  +  Integer.toString(endTile.getX())+", " + Integer.toString(endTile.getY());
            }
          
         }
        
         return "No move";  
      }
        
   //tile x,y
      private boolean canJump()
      {
         if(redTurn)
         {
            for(Checker checker: redCheckers)
            {
               if( (checkJumpLeft(checker)) || (checkJumpRight(checker)) )
                  return true;
            }
         }
         else if(!redTurn)
         {
            for(Checker checker: blackCheckers)
            {
               if( (checkJumpLeft(checker)) || (checkJumpRight(checker)) )
                  return true;
            }
         }
         return false;
      
      	
      }
      private void makeKing(Checker checker)
      {
         if((checker.isRed()) && (checker.getY() < 50) )
         {
            redCheckers.add(new Checker(Checkers.RED_KING, checker.getX(), checker.getY()));
            redCheckers.remove(checker);
         }
           
         
         else if((checker.isBlack()) && (checker.getY() >= 350) )
         {
            blackCheckers.add(new Checker(Checkers.BLACK_KING, checker.getX(), checker.getY()));
            blackCheckers.remove(checker);
         }  
       
      }
      private boolean checkJumpLeft(Checker checker)
      {
         Tile jumpTile, jumpedTile; 
         Checker jumpChecker;
      	
         if(checker.isRed() || checker.isKing())
         {
            if( (checker.getX() <= 100) || (checker.getY() < 100) )
            {
               return false;
            }
            else
            {
               jumpedTile = getTile(checker.getX() - ONE_TILE, checker.getY() - ONE_TILE);
               jumpTile = getTile(checker.getX() - TWO_TILE, checker.getY() - TWO_TILE);
               jumpChecker = getChecker(checker.getX() - ONE_TILE, checker.getY() - ONE_TILE);
            	//check to see if tile is occupies one up and over to the left
               if(jumpedTile.occupied() )
               {
                  if(jumpChecker.isBlack())
                  {
                     if(!jumpTile.occupied() )
                        return true;
                  } 
               }
            }
         }
         
         else if(checker.isBlack() || checker.isKing())
         {
         
            if( (checker.getX() <= 100) || (checker.getY() >= 300) )
            {
               return false;
            }
            
            else
            {
               jumpedTile = getTile(checker.getX() - ONE_TILE, checker.getY() + ONE_TILE);
               jumpTile = getTile(checker.getX() - TWO_TILE, checker.getY() + TWO_TILE);
               jumpChecker = getChecker(checker.getX() - ONE_TILE, checker.getY() + ONE_TILE);
            	//check to see if tile is occupies one up and over to the left
               if(jumpedTile.occupied()) 
               {
                  if(jumpChecker.isRed())
                  {
                     if(!jumpTile.occupied())
                        return true;  
                  }
               }
            }           
         }
         
         return false;
      }
      private boolean checkJumpRight(Checker checker)
      {	
         Tile jumpTile, jumpedTile; 
         Checker jumpChecker;
         if(checker.isRed() || checker.isKing())
         {
            if( (checker.getX() >= 300) || (checker.getY() < 100) )
            {
               return false;
            }
            else
            {
               jumpedTile = getTile(checker.getX() + ONE_TILE, checker.getY() - ONE_TILE);
               jumpTile = getTile(checker.getX() + TWO_TILE, checker.getY() - TWO_TILE);
               jumpChecker = getChecker(checker.getX() + ONE_TILE, checker.getY() - ONE_TILE);
            	//check to see if tile is occupies one up and over to the left
               if(jumpedTile.occupied())
               {
                  if(jumpChecker.isBlack())
                  {
                     if(!jumpTile.occupied())
                        return true;
                  }  
               }
            }
         }
         
         else if(checker.isBlack() || checker.isKing())
         {
           
            if((checker.getX() >= 100) || (checker.getY() >= 300) )
            {
               return false;
            }
            
            else
            {
               jumpedTile = getTile(checker.getX() + ONE_TILE, checker.getY() + ONE_TILE);
               jumpTile = getTile(checker.getX() + TWO_TILE, checker.getY() + TWO_TILE);
               jumpChecker = getChecker(checker.getX() + ONE_TILE, checker.getY() + ONE_TILE);
            	//check to see if tile is occupies one up and over to the left
               if(jumpedTile.occupied())
               { 
                  if(jumpChecker.isRed()) 
                  {
                     if(!jumpTile.occupied())
                        return true;  
                  }
               }
            }           
         }
         
         return false;
      }
      private boolean didJump(Checker checker, Tile startTile, Tile endTile)
      {
         if(checker.isRed() || checker.isKing())
         {
            if((startTile.getY() - TWO_TILE) <= (endTile.getY()) )
               return true;
         }
         else if(checker.isBlack() || checker.isKing())
         {
            if((startTile.getY() + TWO_TILE) >= (endTile.getY()) )
               return true;		
         }
         return false;
      
      }
      private void jump(Checker activeChecker, Tile startTile, Tile endTile)
      {
         int x,y;
         Tile removeTile;
         x = (startTile.getX() + endTile.getX()) / 2;
         y = (startTile.getY() + endTile.getY()) / 2;
         if(activeChecker.isRed())
         {
            blackCheckers.remove(getChecker(x,y));
            removeTile = getTile(x,y);
            removeTile.removeChecker();
         }
         else if(activeChecker.isBlack())
         {
            redCheckers.remove(getChecker(x,y));
            removeTile = getTile(x,y);
            removeTile.removeChecker();
         }
      }
      public void makeBoard()
      {
          //board
         board = new Tile[8][8];
      //start at the first array 
         Tiles tile = Tiles.GRAY;
         for(row = 0; row < board[column].length; row ++)
         { 
         //do all in row
            for(column = 0; column < board[row].length; column++)
            {
               board[row][column] = new Tile(tile, x_cord, y_cord, false);
               x_cord += 50;
               if(tile == Tiles.RED)
               {
                  tile = Tiles.GRAY;
               }
               else
               {
                  tile = Tiles.RED;
               }	
            }
         //update row
            x_cord = 0;
            y_cord += 50;
            column = 0;
            if(tile == Tiles.RED)
            {
               tile = Tiles.GRAY;
            }
            else
            {
               tile = Tiles.RED;
            }	         
         }
      }
      public void makeBlack()
      {
         Tile activeTile;
         blackCheckers = new ArrayList<Checker>();
         x_cord = 50;
         y_cord = 0;
         for(int i = 0; i <   12 ; i++)
         {
            blackCheckers.add(new Checker(Checkers.BLACK, x_cord, y_cord));
            activeTile = getTile(x_cord, y_cord);
            activeTile.addChecker();
            x_cord += 100;
            if( (x_cord > 350) && (y_cord == 0) )
            {
               y_cord = 50;
               x_cord = 0;
            }        
            if ((x_cord > 300) && (y_cord == 50) )
            {
               y_cord = 100;
               x_cord = 50; 
            }	
         }
      }
      public void makeRed()
      {
         Tile activeTile;
         redCheckers = new ArrayList<Checker>();
         x_cord = 0;
         y_cord = 250;
         for(int i = 0; i <   12 ; i++)
         {
            redCheckers.add(new Checker(Checkers.RED, x_cord, y_cord));
            activeTile = getTile(x_cord, y_cord);
            activeTile.addChecker();
            x_cord += 100;
            if( (x_cord > 300) && (y_cord == 250) )
            {
               y_cord += 50;
               x_cord = 50;
            }        
            if ((x_cord > 350) && (y_cord == 300) )
            {
               y_cord += 50;
               x_cord = 0; 
            }	
         }
      }
      public void paintBoard(JPanel panel, Graphics graphics)
      {
      	//paint board
         for(row = 0; row < board[column].length; row ++)
         { 
         //do all in row
            for(column = 0; column < board[row].length; column++)
            {
               board[row][column].drawTile(panel, graphics);
            	
            }
            column = 0;
         }
      }
   
      public void paintCheckers(JPanel panel, Graphics graphics)
      {
       
         //paint black inital
         for (Checker checker : blackCheckers)
         {
            checker.drawChecker(panel, graphics);
          
         }
      
      	  //initial reds
         for (Checker checker : redCheckers)
         {
            checker.drawChecker(panel, graphics); 
          
         }
         
      }
      public boolean isChecker(int x, int y)
      {
         for(Checker checker: redCheckers)
         {
            if( (checker.checkX(x)) && (checker.checkY(y)) )
            { 
               return true;
            }
          
            
         }
         for(Checker checker: blackCheckers)
         {
            if( (checker.checkX(x)) && (checker.checkY(y)) )
            { 
               return true;
            }	 
          
         }
         return false;
      
      }
      public String playersTurn()
      {
         if(!redTurn)
            return "Red";
         else
            return "Black";	
      }
   
      public void resetBoard()
      {
         int x,y;
      	//paint board
         for(row = 0; row < board[column].length; row ++)
         { 
         //do all in row
            for(column = 0; column < board[row].length; column++)
            {
               x = board[row][column].getX();
               y = board[row][column].getY();
            
               if(getChecker(x,y)!= null)
               {
                  board[row][column].addChecker();
               }
               else 
               {
                  board[row][column].removeChecker();
               }
            	
            }
            column = 0;
         }
      
      
      
      
      
      }
      public void changePlayer()
      {
         if(redTurn)
            redTurn = false;
         else
            redTurn = true;
      }
   
   
   }
   
