import java.util.Random;

/**
   Author: Murphy Peisel
   Class: CS110-A
   Represents a grid of minesweeper squares. 
*/

public class Grid extends Minesweeper
{
   /** grid of Squares */
   private Square[][] grid;
   /** width of grid */
   private int width;
   /** height of grid */
   private int height;
   /** number of mines */
   private int numMines;
   /** number of uncovered squares */
   private int numSquaresUncovered;

   /**
      Default constructor creates a representation of a grid.
      @param height height of grid
      @param width width of grid
      @param numMines number of mines in grid
   */
   public Grid(int height, int width, int numMines)
   {
      this.height = height;
      this.width = width;
      this.numMines = numMines;
      grid = new Square[height][width];
      
      // populate grid with MineSquares
      Random rand = new Random();
      int randRow;
      int randCol;
      for (int i = 0; i < numMines; i++)
      {
         do
         {
            randRow = rand.nextInt(height);
            randCol = rand.nextInt(width);
         }
         while(grid[randRow][randCol] != null);
         grid[randRow][randCol] = new MineSquare();
         
      }
      
      // populate grid with NumberSquares
      for (int i = 0; i < height; i++)
      {
         for (int j = 0; j < width; j++)
         {
            if (grid[i][j] == null)
            {
               // unknown number of adjacent mines, so 0
               grid[i][j] = new NumberSquare(i, j, 0);
            }
         }
      }
      
      // repopulate grid with NumberSquares with number of adjacent mines
      for (int i = 0; i < height; i++)
      {
         for (int j = 0; j < width; j++)
         {
            if (!grid[i][j].isMine())
            {
               grid[i][j] = new NumberSquare(i, j, getNeighbors(i, j));
            }
         }
      }
   }
   
   /**
      getNeighbors gets the number of squares that are mines adjacent to the selected square
      @param row row position of square
      @param col column position of square 
      @return number of neighbors containing mines
   */
   public int getNeighbors(int row, int col)
   {
      int numNeighbors = 0;  
      for (int i = row - 1; (i <= row + 1) && (i < height); i++)
      {
         for (int j = col - 1; (j <= col + 1) && (j < width); j++)
         {
            if (i >= 0 && j >= 0)
            {
               if (grid[i][j].isMine())
               {
                  numNeighbors++;
               }
            }
         }
      }
      return numNeighbors;
   }
   
   /**
      uncoverSquare uncovers the square at the selected position
      @param row row position of square
      @param col column position of square 
      @return game status
   */
   public Status uncoverSquare(int row, int col)
   {
      // check if square is flagged
      if (grid[row][col].isFlagged())
      {
         return Status.OK;
      }
      
      // check if square is a mine
      if (grid[row][col].isMine())
      {
         return Status.MINE;
      }

      else
      {
         // check if square has no adjacent mines, uncover 5x5
         if (getNeighbors(row, col) == 0)
         {
            for (int i = row - 2; (i <= row + 2) && (i < height); i++)
            {
               for (int j = col - 2; (j <= col + 2) && (j < width); j++)
               {
                  if (i >= 0 && j >= 0)
                  {
                     if (!grid[i][j].isMine() && !grid[i][j].isUncovered())
                     {
                        grid[i][j].uncover();
                        numSquaresUncovered++;
                     }
                  }
               }
            }
         }
         // check if square has 1 adjacent mine, uncover 3x3
         else if (getNeighbors(row, col) == 1)
         {
            for (int i = row - 1; (i <= row + 1) && (i < height); i++)
            {
               for (int j = col - 1; (j <= col + 1) && (j < width); j++)
               {
                  if (i >= 0 && j >= 0)
                  {
                     if (!grid[i][j].isMine() && !grid[i][j].isUncovered())
                     {
                        grid[i][j].uncover();
                        numSquaresUncovered++;
                     }
                  }
               }
            }
         }
         // check if square has more than 1 adjacent mines, uncover 1
         else
         {
            if (!grid[row][col].isMine() && !grid[row][col].isUncovered())
               {
                  grid[row][col].uncover();
                  numSquaresUncovered++;
               }
         }
         // check game status
         if (numSquaresUncovered == (width * height - numMines))
         {
            return Status.WIN;
         }
         else
         {
            return Status.OK;
         }
      }
   }
   
   /**
      flagSquare flags a square
      @param row row position of square
      @param col column position of square
   */
   public void flagSquare(int row, int col)
   {
      grid[row][col].flagSquare();
   }
   
   /**
      exposeMines uncovers all mines
   */
   public void exposeMines()
   {
      for (int i = 0; i < height; i++)
      {
         for (int j = 0; j < width; j++)
         {
            if(grid[i][j].isMine())
            {
               grid[i][j].uncover();
            }
         }
      }
   }

   /**
      toString
      @return String representation of a Grid
   */
   @Override
   public String toString()
   {
      String gridDisplay = "\t";
      for (int a = 0; a < width; a++)
      {
         gridDisplay += String.format("%3d", a);
      }
      for (int i = 0; i < height; i++)
      {
         gridDisplay += String.format("\n%3d", i);
         for (int j = 0; j < width; j++)
         {
            gridDisplay += String.format("%3s", grid[i][j]);
         }
      }
      return gridDisplay;
   }

}

