import java.util.Scanner;
/**
   Author: Murphy Peisel
   Class: CS110-A
   Puts together components of Minesweeper to make Minesweeper. 
*/

public class Minesweeper
{
   /** height of grid */
   private int height;
   /** width of grid */
   private int width;
   
   /**
      start allows the user to pick a difficulty and creates a Grid accordingly
      @return Grid of chosen difficulty
   */
   public Grid start()
   {
      System.out.println("Please pick a difficulty:");
      System.out.println("(B)eginner - 8 x 8 grid with 8 mines");
      System.out.println("(I)ntermediate - 10 x 12 grid with 10 mines");
      System.out.println("(E)xpert - 16 x 20 grid with 50 mines");
      
      // create Scanner object
      Scanner keyboard = new Scanner(System.in);
      // get user's choice
      String difficulty = "";
      do
      {
         difficulty = keyboard.nextLine();
      }
      while((!difficulty.toUpperCase().equals("B")) &&
            (!difficulty.toUpperCase().equals("I")) &&
            (!difficulty.toUpperCase().equals("E")));
      difficulty = difficulty.toUpperCase();
      
      // create grid based on difficulty
      if (difficulty.equals("B"))
      {
         // 8 x 8 with 8 mines
         Grid grid = new Grid(8,8,8);
         width = 8;
         height = 8;
         return grid;
      }
      else if (difficulty.equals("I"))
      {
         // 10 x 12 with 10 mines
         Grid grid = new Grid(10,12,10);
         width = 10;
         height = 12;
         return grid;
      }
      else if (difficulty.equals("E"))
      {
         // 16 x 20 with 50 mines
         Grid grid = new Grid(16,20,50);
         width = 16;
         height = 20;
         return grid;
      }
      
      // if an option isn't selected, select default grid (intermediate)
      Grid grid = new Grid(10,12,10);
      return grid;
   }
   
   /**
      getMove gets the user's move and puts it into an array
      @param grid minesweeper grid
      @return array of Strings containing the user's move
   */
   public String[] getMove(Grid grid)
   {
      System.out.println(grid);
      // create Scanner object
      Scanner keyboard = new Scanner(System.in);
      System.out.println("What next?");
      String userInput = "";
      String [] pieces = {"", "", ""};
      // row to act upon
      int row;
      // column to act upon
      int col;
      // sentinel
      boolean inputStatus = true;
      // loops until sentinel is false
      while (inputStatus)
      {
         try
         {
            do
            {
               System.out.println("Options: (U)ncover r c, (F)lag r c, (Q)uit");
               userInput = keyboard.nextLine();
               pieces = userInput.split("\\s+");
               // other checks don't matter if user wants to quit
               if (pieces[0].toUpperCase().equals("Q"))
               {
                  return pieces;
               }
               // turn row and col into integers
               row = Integer.parseInt(pieces[1]);
               col = Integer.parseInt(pieces[2]);
               inputStatus = false;
            }
         
            while ((!pieces[0].toUpperCase().equals("U") &&
                   !pieces[0].toUpperCase().equals("F")) ||
                   (pieces.length < 3) || // pieces must be at least 3 characters long
                   (row < 0 || 
                   row > width ||
                   col < 0 ||
                   col > height));
         }
         catch (NumberFormatException e)
         {
            // tells loop to continue
            inputStatus = true;
         }
         catch (ArrayIndexOutOfBoundsException e)
         {
            // tells loop to continue
            inputStatus = true;
         }
      }
      return pieces;
   }
   
   /**
      makeMove allows the user to choose to uncover/flag/quit
      @param grid minesweeper grid
      @return status of game (win (WIN), lose (MINE), neither (OK))
   */
   public Status makeMove(Grid grid)
   {
      // split up into uncover/flag/quit and row + col
      String [] move = getMove(grid);
      String moveOption = move[0].toUpperCase();
      // check if user wants to quit
      if (moveOption.equals("Q"))
      {
         return Status.MINE;
      }
      int row = Integer.parseInt(move[1]);
      int col = Integer.parseInt(move[2]);
      
      // check move type
      if (moveOption.equals("U"))
      {
         return grid.uncoverSquare(row, col);
      }
      // only other option is flag
      else
      {
         grid.flagSquare(row, col);
         return Status.OK;
      }
   }
   
   /**
      play allows the user to actually play minesweeper.
   */
   public void play()
   {
      // status of the game
      Status gameStatus;
      // does user want to play again
      String playAgain = "";
      // minesweeper grid of selected difficulty
      Grid grid = start();
      
      // play until user wins or loses
      do
      {
         gameStatus = makeMove(grid);
      }
      while (gameStatus != Status.WIN &&
             gameStatus != Status.MINE);
             
      // check if user won or lost
      if (gameStatus == Status.WIN)
      {
         grid.exposeMines();
         System.out.println(grid);
         System.out.println("Good job! You win!");
      }
      else if (gameStatus == Status.MINE)
      {
         grid.exposeMines();
         System.out.println(grid);
         System.out.println("Better luck next time! You lose!");
      }
      
      // ask user if they want to play again
      // create Scanner object
      Scanner keyboard = new Scanner(System.in);
      System.out.println("Would you like to play again? (y/n)");
      do
      {
         playAgain = keyboard.nextLine().toUpperCase();
      }
      while (!playAgain.equals("Y") &&
             !playAgain.equals("N"));
             
      if (playAgain.equals("Y"))
      {
         play();
      }
   }
}