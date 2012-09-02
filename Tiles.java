   import java.awt.*;
   import javax.swing.*;


   public enum Tiles implements Paintable 
   {
      RED(new Color(210,95,95), 50),
      GRAY(Color.GRAY, 50);
   
      private Color tileColor;
      private int tileSize;
   
      private Tiles(Color color, int tileSize)
      {
         tileColor = color;
         this.tileSize = tileSize;
      }
      public Color getTileColor()
      {
         return tileColor;
      }
      public Color getDarkColor()
      {
         return	tileColor.darker();
      }
      public void draw(JPanel panel, Graphics graphics, int x, int y)
      {
       	
         graphics.setColor(tileColor);
         graphics.fillRect(x, y, tileSize, tileSize); 
      }
      public int getTileSize()
      {
         return tileSize;
      }
    
   }
