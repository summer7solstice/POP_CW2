import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
/*
  1.‘abstraction’: In this version of code, I would replace 'i' with 'row', 'j' with 'column', which makes code more readable.

  2.The player's counter is still 'r', computer's is still 'y'.

  3.Encapsulation:
  Define the new class 'Board' with some public functions.
  Define the new class 'Player' with some public functions.

*/
public class MyConnectFour 
{
  private BufferedReader input;
  private Board board;
  private Player player1;
  private Player player2; 

  // initialize functions
  public MyConnectFour() 
  {
    input = new BufferedReader(new InputStreamReader(System.in));
    player1 = new Player('r');
    player2 = new Player('y');

    System.out.println("Do you want play PVE(player vs computer) or PVP(player vs player)?");
    System.out.println("Press 1 for PVE, any others for PVP");
    
    int chooseFlag = Integer.parseInt(pveOrPvp());
    if (chooseFlag == 1)
    {
      // PVE
      player2.setComputer(true);
    }

    board = new Board(player1.getCounter(), player2.getCounter());
    playGame();
  }

  /*---------- the main function ----------*/
  private void playGame() 
  {
    showIntroduction();
    System.out.println(board.getPrintableString());

    Player[] playerArray = {player1,player2};

    // loop until there is a win or draw
    boolean win = false;
    boolean draw = false;
    // flag: input sequence, even: player1 inputs, odd: player2 inputs
    int flag = 0;
    while (!win)
    {
      Player tempPlayer = playerArray[flag % 2];

      int playerInput = tempPlayer.getInput(input);
      if(!isValid(playerInput))
      {
        // input wrong, jump out of this loop
        System.out.println("Please input string from '1' to '7'!");
        continue;
      }

      // check it can be placed or not
      boolean placed = board.placeCounter(tempPlayer.getCounter(), playerInput);
      if (placed)
      {
        win = board.checkWinOrNot(tempPlayer.getCounter());
        flag++;
        System.out.println(board.getPrintableString());
      }
      else
      {
        // the column is full, input again
        System.out.println("This column is full, please drop your counter in another column!");
        continue;
      }

      // the board is full but no one wins
      if (flag == 42 && !win) 
      {
        draw = true;
        break;
      }
    }

    if (draw)
    {
      // no one wins
      System.out.print("The game is a draw.");
    }
    else
    {
      // someone wins
      System.out.print(flag % 2 != 0 ? "Player1 wins!" : "Player2 wins!");
    }
  }

  /*---------- show the rules to player ----------*/
  private void showIntroduction()
  {
    String[] introStrArray = {
                              "Welcome to Connect 4",
                              "There are 2 players",
                              "Player1 is Red, player2 is Yellow",
                              "To play the game type in the number of the column you want to drop your counter in",
                              "A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally"
                              };
    for (String introStr : introStrArray)
    {
        System.out.println(introStr);
    }
  }

  /*---------- check the input is valid or not ----------*/
  private boolean isValid(int input)
  {
    boolean isValid = false;
    int[] validArray = {1,2,3,4,5,6,7};
    for(int validInput : validArray)
    {
      if(input == validInput) {
        isValid = true;
      }
    }
    return isValid;
  }
  /*---------- choose PVE or PVP ----------*/
  private String pveOrPvp()
  {
    String toReturn = null;
    try 
    {
      toReturn = input.readLine();
    } catch (IOException e) {
      System.out.println(e);
    }
    return toReturn;
  }
}