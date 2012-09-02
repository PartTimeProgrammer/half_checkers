   import java.awt.*;
   import javax.swing.*;
 
   public enum Checkers implements Paintable
   {	
      RED("Reds", "./_icons/red_checker.png"),
      RED_KING("Reds King", "./_icons/red_checker_king.png"),
      BLACK("Blacks", "./_icons/black_checker.png"),
      BLACK_KING("Blacks Kings", "./_icons/black_checker_king.png");
   
      private String name;
      private String fp;
   
      private Checkers(String name, String fp)
      {
         this.name = name;
         this.fp = fp;
      }
      public String getName()
      {
         return name;
      }
      public String getFilePath()
      {
         return fp;
      }
      public void draw(JPanel panel, Graphics graphics, int x, int y)
      {
         ImageIcon image = new ImageIcon(fp);
         image.paintIcon(panel, graphics, x, y);
      }
   }