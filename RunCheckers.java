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
   import java.awt.*;
   import javax.swing.*;
   import java.util.*;
   
   public class RunCheckers
   {
      public static void main(String[] args)
      {
         GameWindow board = new GameWindow();
         board.display();
      }
   }