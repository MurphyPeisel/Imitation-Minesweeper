/**
   Author: Murphy Peisel
   Class: CS110-A
   Represents a single minesweeper mine square. 
*/

public class MineSquare extends Square
{
   /**
      isMine
      @return is Square a mine or not
   */
   public boolean isMine()
   {
      return true;
   }
      
   /**
      uncover changes uncovered and element of the Square
      @return did uncover succeed
   */
   public boolean uncover()
   {
      if (!isFlagged())
      {
         setUncovered();
         setElement("*");
         return true;
      }
      else
      {
         return false;
      }
   }
}