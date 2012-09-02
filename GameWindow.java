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

   public class GameWindow  
   {
   
      private  JFrame window;
      private CheckerBoard board;
      public static JTextArea box;
      private JPanel controlBox;
      private JButton draw;
      private JButton newGame;
   	
   
      public GameWindow()
      {
         window = new JFrame("Checkers");
         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         window.setBackground(Color.WHITE);
         window.setSize(850, 450);
         window.setLocationRelativeTo(null);
         
         board = new CheckerBoard();
         box = new JTextArea();
         controlBox = new JPanel();
         draw = new JButton("Forefit");
         newGame  = new JButton("New game");
         
         controlBox.setLayout(new FlowLayout(FlowLayout.CENTER));
         controlBox.add(draw);
         controlBox.add(newGame);
         controlBox.add(box);
         box.setVisible(true);  
         //box.setRows(50);
         window.setLayout(new GridLayout());
         window.add(board);
         window.add(controlBox);
         
         newGame.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     board.reset();
                     window.repaint();
                  }
               }
            			 );
         draw.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                  	
                     JOptionPane.showMessageDialog(null, board.getPlayer() + " forefits");
                  }
               }
            			 );
       	 
      	
      
       	 
      	
      	
      }
           
      public int display()
      {
         window.setVisible(true);
         return 0;
      }
   }