import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
  1.‘abstraction’: In this version of code, I would replace 'i' with 'row', 'j' with 'column', which makes code more readable.

  2.The player's counter is still 'r', computer's is still 'y'.

  3.Encapsulation:Define the new functions namely 'isValid', 'checkWinOrNot'.


*/
public class MyConnectFour 
{
  // get inputs from keyboard
  private BufferedReader input;
  // the board which stores inputs from play1 and player2(Computer)
  private char[][] board;

  // initialize functions
  public MyConnectFour() 
  {
    board = new char[6][7];
    input = new BufferedReader(new InputStreamReader(System.in));
    // the game starts
    playGame();
  }

  /*---------- the main function ----------*/
  private void playGame() 
  {
    showIntroduction();
    printBoard();

    // loop until there is a win or draw
    boolean win = false;
    boolean draw = false;
    // flag: input sequence, even: player input, odd: computer input
    int flag = 0;
    while (!win) 
    {
      if (flag >= 42)
      {
        // this means evey position of the board is placed, but no one wins
        draw = true;
        break;
      }
      if (flag % 2 == 0)
      {
        // player input
        String userInput = getUserInput();
        if(!isValid(userInput))
        {
          // input wrong, jump out of this loop
          System.out.println("Please input string from '1' to '7'!");
          continue;
        }

        // check it is placed or not
        int playerMove = Integer.parseInt(userInput);
        boolean placedR = placeCounter('r', playerMove);
        if (placedR)
        {
          win = checkWinOrNot('r');
          flag++;
          printBoard();
        }
        else
        {
          // the column is full, input again
          System.out.println("This column is full, please drop your counter in another column!");
          continue;
        }
      }
      else
      {
        // computer input
        int computerMove = getComputerInput();
        boolean placedY = placeCounter('y', computerMove);
        if (placedY)
        {
          win = checkWinOrNot('y');
          flag++;
          printBoard();
        }
        else
        {
          // the column is full, input again
          System.out.println("This column is full, please drop your counter in another column!");
          continue;
        }
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
      System.out.print(flag % 2 != 0 ? "You win!" : "Computer wins!");
    }
  }

  /*---------- show the rules to player ----------*/
  private void showIntroduction()
  {
    String[] introStrArray = {
                              "Welcome to Connect 4",
                              "There are 2 players, the first one is you, the second one is computer",
                              "Player is Red, computer is Yellow",
                              "To play the game type in the number of the column you want to drop your counter in",
                              "A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally"
                              };
    for (String introStr : introStrArray)
    {
        System.out.println(introStr);
    }
  }

  /*---------- get inputs from player and computer ----------*/
  private String getUserInput()
  {
    String toReturn = null;
    try 
    {
      toReturn = input.readLine();
    } catch (Exception e) {
      System.out.print(e);
    }
    return toReturn;
  }
  private int getComputerInput() 
  {
    // get random input and limit it into the range [1,7]
    int toReturn = 0;
    int randomInt = (int) Math.floor(Math.random()*100 % 7);
    return toReturn + 1 + randomInt;
  }

  /*---------- check the input is valid or not ----------*/
  private boolean isValid(String input)
  {
    boolean isValid = false;
    String[] validStrArray = {"1","2","3","4","5","6","7"};
    for(String validString : validStrArray)
    {
      if(validString.equals(input)) {
        isValid = true;
      }
    }
    return isValid;
  }

  /*---------- check anyone has won or not ----------*/
  // Encapsulation
  /*
    params:
    player: r is the player, y is the computer
  */
  private boolean checkWinOrNot(char player) {
    boolean hasWon = false;
    // use this origin point logic, do not have to enumarate every position
    // check horizontal
    for (int row = 0; row < board.length; row++) 
    {
      for (int column = 0; column < board[row].length - 3; column++) 
      {
        // (row,column) makes the origin of the line, then check whether it is longer or equal than 4
        if(board[row][column] == player &&
            board[row][column+1] == player &&
            board[row][column+2] == player &&
            board[row][column+3] == player)
        {
          hasWon = true;
        }
      }
    }
    // check vertical
    for (int row = board.length - 1; row > board.length - 1 - 3; row--) 
    {
      for (int column = 0; column < board[row].length; column++) 
      {
        // (row,column) makes the origin of the line, then check whether it is longer or equal than 4
        if(board[row][column] == player &&
            board[row-1][column] == player &&
            board[row-2][column] == player &&
            board[row-3][column] == player)
        {
          hasWon = true;
        }
      }
    }
    // check diagonally negatively sloped
    for (int row = 0; row < board.length - 3; row++) 
    {
      for (int column = 0; column < board[row].length - 3; column++) 
      {
        // (row,column) makes the origin of the line, then check whether it is longer or equal than 4
        if(board[row][column] == player &&
            board[row+1][column+1] == player &&
            board[row+2][column+2] == player &&
            board[row+3][column+3] == player)
        {
          hasWon = true;
        }
      }
    }
    // check diagonally positively sloped
    for (int row = board.length - 1; row > board.length - 1 - 3 ; row--) 
    {
      for (int column = 0; column < board[row].length - 3; column++) 
      {
        // (row,column) makes the origin of the line, then check whether it is longer or equal than 4
        if(board[row][column] == player &&
            board[row-1][column+1] == player &&
            board[row-2][column+2] == player &&
            board[row-3][column+3] == player)
        {
          hasWon = true;
        }
      }
    }
    return hasWon;
  }

  /*---------- function to print the chess board ----------*/
  private void printBoard() 
  {
    System.out.print("\n");
    for (int row = 0; row < board.length; row++) 
    {
      System.out.print(row + 1 + " ");
      for (int column = 0; column < board[row].length; column++) 
      {
        if (board[row][column] == 'r') 
        {
          System.out.print("| r ");
        } 
        else if (board[row][column] == 'y') 
        {
          System.out.print("| y ");
        } 
        else 
        {
          System.out.print("|   ");
        }
      }
      System.out.println("|");
    }
    System.out.println("    1   2   3   4   5   6   7");
  }

  /*---------- place the counter ----------*/
  /*
    params:
    player: r is the player, y is the computer
    position: which column

    output:
    placed: the column could be full, then player has to input again
  */
  private boolean placeCounter(char player, int position) 
  {
    boolean placed = false;
    if (player == 'r') 
    {
      for (int i = board.length - 1; i >= 0; i--) 
      {
        if (!placed) 
        {
          if (board[i][position - 1] == 'y') 
          {
            // skip
          } 
          else if (board[i][position - 1] != 'r') 
          {
            // this means the place is empty
            board[i][position - 1] = 'r';
            placed = true;
          }
        }
      }
    } 
    else 
    {
      for (int i = board.length - 1; i >= 0; i--) 
      {
        if (!placed) 
        {
          if (board[i][position - 1] == 'r') 
          {
            // skip
          } 
          else if (board[i][position - 1] != 'y') 
          {
            // this means the place is empty
            board[i][position - 1] = 'y';
            placed = true;
          }
        }
      }
    }
    return placed;
  }
}