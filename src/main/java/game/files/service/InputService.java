package game.files.service;

import game.files.model.Stone;
import java.util.Scanner;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class InputService {

  private Scanner scanner;
  private int boardHeight;
  private Stone[][] gameBoard;

  public InputService() {
  }

  public InputService(Scanner scanner) {
    this.scanner = scanner;
    this.boardHeight = findSize();
    this.gameBoard = new Stone[boardHeight][boardHeight];
  }


  private int scanInput() {
    String input = scanner.nextLine();
    if (input.length() <= 0) {
      return -1;
    }

    // Checks if the player actually wants to make a move or pass
    if (input.equalsIgnoreCase("pass")) {
      return -1337;
    }
    if (input.matches("^[0-9]*$")) {
      return Integer.parseInt(input);
    }
    return -1;
  }

  private int readInput(String hint) {
    System.out.println(hint);
    int input = scanInput();
    if (input == -1337) {
      return -1337;
    }
    while (!isInBorder(input)) {
      System.out.println("Invalid Input\n" + hint);
      input = scanInput();
      if (input == -1337) {
        return -1337;
      }
    }
    return input;
  }

  private boolean isInBorder(int input) {
    return input < boardHeight && input >= 0;
  }

  public int[] findRowAndColumn() {
    int row;
    int column;
    do {
      row = readInput("Row?");

      if (row == -1337) {
        return new int[]{-1337, -1337};
      }

      column = readInput("Column?");

      if (column == -1337) {
        return new int[]{-1337, -1337};
      }
      if (gameBoard[row][column] != null) {
        System.out.println("Invalid Move");
      } else {
        break;
      }
    } while (true);

    return new int[]{row, column};
  }

  private int findSize() {
    System.out.println("Board Size?");
    int size = scanInput();
    while (size <= 0 || size > 19) {
      System.out.println("Please enter a positive value below 20");
      size = scanInput();
    }
    System.out.println();
    return size;
  }
}
