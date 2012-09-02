   import java.awt.*;
   import javax.swing.*;


   public class Tile 
   {
   
      private Tiles tile;
      private int x_cord, y_cord;
      private boolean hasPiece;
   
   //specify_cord with constructor using constants
      public Tile(Tiles tile, int x, int y, boolean hasPiece)
      {
         this.tile = tile;
         x_cord = x;
         y_cord = y;
         this.hasPiece = hasPiece;
      }
    
      public void drawTile(JPanel panel, Graphics graphics)
      {
         tile.draw(panel, graphics, x_cord, y_cord);
      }
      public int getX()
      {
         return x_cord;
      }
      public int getY()
      {
         return y_cord;
      }
      public boolean checkX(int x)
      {
         if( ( x >= x_cord) && ( x < x_cord + tile.getTileSize()))
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
         if( ( y >= y_cord) && ( y < y_cord + tile.getTileSize()))
         {
            return true;
         }
         else
         {
            return false;
         }
      } 
      public int getTileSize()
      {
         return tile.getTileSize();
      }	
      public void addChecker()
      {
         hasPiece = true;
      }
      public void removeChecker()
      {
         hasPiece = false;
      }
      public boolean occupied()
      {
         return hasPiece;
      }
      public boolean isRed()
      {
         if (tile == Tiles.RED)
            return true;
         else
            return false;
      }
    
   
   
   }
