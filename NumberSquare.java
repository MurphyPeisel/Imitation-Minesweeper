/**
   Author: Murphy Peisel
   Class: CS110-A
   Represents a single minesweeper number square. 
*/

public class NumberSquare extends Square
{

   /**  the number of adjacent Squares that are mines */
   private int neighborMines;
   /**  the Square's row position */
   private int myRow;
   /**  the Square's column position */
   private int myCol;
   
   /**
      Creates a representation of a number square
      @param neighborMines number of adjacent Squares that are mines
      @param myRow Square's row position
      @param  myCol Square's column position
   */
   public NumberSquare(int myRow, int myCol, int neighborMines)
   {
      this.neighborMines = neighborMines;
      this.myRow = myRow;
      this.myCol = myCol;
   }
   
   /**
      getNeighborMines
      @return number of adjacent mines
   */
   public int getNeighborMines()
   {
      return neighborMines;
   }
   
   /**
      isMine
      @return is Square a mine or not
   */
   public boolean isMine()
   {
      return false;
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
         if (getNeighborMines() == 0)
         {
            setElement("_");
         }
         else
         {
            setElement(Integer.toString(getNeighborMines()));
         }
         return true;
      }
      return false;
   }
}