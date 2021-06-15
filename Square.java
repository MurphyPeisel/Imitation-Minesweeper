/**
   Author: Murphy Peisel
   Class: CS110-A
   Represents a single minesweeper square. 
*/

public abstract class Square
{
   /** current representation of the Square */
   private String element;
   /** is the Square flagged or not */
   private boolean flagged;
   /** is the square uncovered or not */
   private boolean uncovered;
   
   /**
      Default constructor creates a representation of a square.
   */
   public Square()
   {
      element = "x";
      flagged = false;
      uncovered = false;
   }
   
   /**
      Alternate constructor creates a representation of a square.
      @param element current representation of the Square
      @param flagged is the Square flagged or not
      @param uncovered is the square uncovered or not
   */
   public Square(String element, boolean flagged, boolean uncovered)
   {
      this.element = element;
      this.flagged = flagged;
      this.uncovered = flagged;
   }
   
   /**
      isFlagged
      @return is Square flagged or not
   */
   public boolean isFlagged()
   {
      return flagged;
   }

   /**
      isUncovered
      @return is Square uncovered or not
   */
   public boolean isUncovered()
   {
      return uncovered;
   }
   
   /**
      flagSquare sets flagged to true.
   */
   public void flagSquare()
   {
      // if square is already flagged, unflag it 
      if (isFlagged())
      {
         flagged = false;
         setElement("x");
      }
      else
      {
         flagged = true;
         setElement("f");
      }
   }

   /**
      setUncovered sets uncovered to true.
   */
   public void setUncovered()
   {
      uncovered = true;
   }

   /**
      setElement sets the Square's element.
      @param element current representation of the square
   */
   public void setElement(String element)
   {
      this.element = element;
   }
   
   /**
      toString
      @return String representation of a Square
   */
   @Override
   public String toString()
   {
      return String.format("%s", element);
   }
      
   /**
      uncover changes uncovered and element of the Square
      @return did uncover succeed
   */
   public abstract boolean uncover();
   
   /**
      isMine
      @return is Square a mine or not
   */
   public abstract boolean isMine();
}