/**
   Author: Murphy Peisel
   Class: CS110-A
   Runs Minesweeper. 
*/

/** EXTRA CREDIT CONSIDERATION
   I have added the following functionality, I would like evaluated for extra credit:
   1.) Allow the user to select a level when they start (5)
   2.) Early submission (5/7 by end of day) (5)
   3.) Allow the user to restart the game (3)
   
*/

public class Driver
{
   public static void main(String[] args)
   {
      Minesweeper sweep = new Minesweeper();
      sweep.play();
   }
}
