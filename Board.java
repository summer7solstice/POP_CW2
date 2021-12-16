public class Board
{
  private char[][] board;
  // counters could be fedined
  private char counter1;
  private char counter2;

  public Board(char counter1, char counter2)
  {
      board = new char[6][7];
      this.counter1 = counter1;
      this.counter2 = counter2;
  }

  /**
   * The func to get printable string from the board
   * @param None
   * @return The printable string
   */
  public String getPrintableString()
  {
      String s = "";
      for (int row = 0; row < board.length; row++) 
      {
        s += (row + 1 + " ");
        for (int column = 0; column < board[row].length; column++) 
        {
          if (board[row][column] == counter1) 
          {
            s += ("|" + " " + counter1 + " ");
          } 
          else if (board[row][column] == counter2) 
          {
            s += ("|" + " " + counter2 + " ");
          } 
          else 
          {
            s += ("|   ");
          }
        }
        s += ("|\n");
      }
      s += ("    1   2   3   4   5   6   7\n");
      return s;
  }

  /**
   * The func to check anyone has won or not
   * @param Counter: the counter（char type）of the 'Player' class
   * @return Haswon: the player who win or not
   */
  public boolean checkWinOrNot(char counter) {
    boolean hasWon = false;
    // use this origin point logic, do not have to enumarate every position
    // (row,column) makes the origin of the line, then check whether it is longer or equal than 4
    // check horizontal
    for (int row = 0; row < board.length; row++) 
    {
      for (int column = 0; column < board[row].length - 3; column++) 
      {
        if(board[row][column] == counter &&
            board[row][column+1] == counter &&
            board[row][column+2] == counter &&
            board[row][column+3] == counter)
        {
          hasWon = true;
          System.out.printf("The line is (%d,%d), (%d,%d), (%d,%d), (%d,%d)\n",column+1,row+1,column+2,row+1,column+3,row+1,column+4,row+1);
        }
      }
    }
    // check vertical
    for (int row = board.length - 1; row > board.length - 1 - 3; row--) 
    {
      for (int column = 0; column < board[row].length; column++) 
      {
        if(board[row][column] == counter &&
            board[row-1][column] == counter &&
            board[row-2][column] == counter &&
            board[row-3][column] == counter)
        {
          hasWon = true;
          System.out.printf("The line is (%d,%d), (%d,%d), (%d,%d), (%d,%d)\n",column+1,row+1,column+1,row,column+1,row-1,column+1,row-2);
        }
      }
    }
    // check diagonally negatively sloped
    for (int row = 0; row < board.length - 3; row++) 
    {
      for (int column = 0; column < board[row].length - 3; column++) 
      {
        if(board[row][column] == counter &&
            board[row+1][column+1] == counter &&
            board[row+2][column+2] == counter &&
            board[row+3][column+3] == counter)
        {
          hasWon = true;
          System.out.printf("The line is (%d,%d), (%d,%d), (%d,%d), (%d,%d)\n",column+1,row+1,column+2,row+2,column+3,row+3,column+4,row+4);
        }
      }
    }
    // check diagonally positively sloped
    for (int row = board.length - 1; row > board.length - 1 - 3 ; row--) 
    {
      for (int column = 0; column < board[row].length - 3; column++) 
      {
        if(board[row][column] == counter &&
            board[row-1][column+1] == counter &&
            board[row-2][column+2] == counter &&
            board[row-3][column+3] == counter)
        {
          hasWon = true;
          System.out.printf("The line is (%d,%d), (%d,%d), (%d,%d), (%d,%d)\n",column+1,row+1,column+2,row,column+3,row-1,column+4,row-2);
        }
      }
    }
    return hasWon;
  }

  /**
   * The func to place the counter into the board
   * @param Counter: the counter（char type）of the 'Player' class
   *        Position: the column player choose to put the counter in
   * @return Placed: the counter is placed or not
   */
  public boolean placeCounter(char counter, int position) 
  {
    boolean placed = false;
    if (counter == counter1) 
    {
      for (int i = board.length - 1; i >= 0; i--) 
      {
        if (!placed) 
        {
          if (board[i][position - 1] == counter2) 
          {
            // skip
          } 
          else if (board[i][position - 1] != counter1) 
          {
            // this means the place is empty
            board[i][position - 1] = counter1;
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
          if (board[i][position - 1] == counter1) 
          {
            // skip
          } 
          else if (board[i][position - 1] != counter2) 
          {
            // this means the place is empty
            board[i][position - 1] = counter2;
            placed = true;
          }
        }
      }
    }
    return placed;
  }
}