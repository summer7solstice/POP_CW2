import java.io.BufferedReader;
import java.io.IOException;

public class Player
{
  private char counter;
  // is computer or real player
  public boolean isComputer;

  public Player(char counter)
  {
    this.counter = counter;
  }

  public char getCounter()
  {
    return counter;
  }
  
  public void setComputer(boolean isComputer) 
  {
    this.isComputer = isComputer;
  }

  /*---------- get inputs from player and computer ----------*/
  public int getInput(BufferedReader input)
  {
    int column = 0;
    if (isComputer) 
    {
      // controlled by computer
      // get random input and limit it into the range [1,7]
      int randomInt = (int) Math.floor(Math.random()*100 % 7);
      column = 1 + randomInt;
    }
    else
    {
      // controlled by player
      column = Integer.parseInt(getUserInput(input));
    }
    return column;
  }

  private String getUserInput(BufferedReader input)
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