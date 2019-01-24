package game.files.service;

import game.files.model.Stone;
import org.springframework.stereotype.Service;

@Service
public class OutputService {

  public static void draw(Stone[][] brett) {
    int boardHeight = brett.length;
    System.out.print(" ");
    for (int i = 0; i < boardHeight; i++) { // Numbering of the rows
      System.out.print(" " + i);
    }
    System.out.println();
    System.out.print(" ");
    for (int i = 0; i < boardHeight; i++) {
      System.out.print(" _");
    }
    System.out.println();
    for (int i = 0; i < boardHeight; i++) {
      System.out.print(i); // Numbering of the columns
      for (int j = 0; j < boardHeight; j++) {
        if (brett[i][j] == null) {
          System.out.print("| ");
        }
        if (brett[i][j] != null && !brett[i][j].isStoneWhite()) {
          System.out.print("|o"); // o for black
        }
        if (brett[i][j] != null && brett[i][j].isStoneWhite()) {
          System.out.print("|*"); // * for white
        }
        if (j + 1 == boardHeight) {
          System.out.print("|");
        }
      }
      System.out.println();
    }
    System.out.print(" ");
    for (int i = 0; i < boardHeight; i++) {
      System.out.print(" ¯");
    }
    System.out.println();
  }

  public static void printCurrentPlayer(boolean isWhite) {
    if (isWhite) {
      System.out.println("White's turn");
    } else {
      System.out.println("Black's turn");
    }
  }

  public static void gameOver(int pointsWhite, int pointsBlack) {
    System.out.println();
    System.out.println("Game Over");
    System.out.println("White has " + pointsWhite + " points");
    System.out.println("Black has " + pointsBlack + " points");
    System.out.println();

    if (pointsBlack > pointsWhite) {
      System.out.println("Black won");
    } else if (pointsBlack < pointsWhite) {
      System.out.println("White won");
    } else {
      System.out.println("Tie");
    }
  }
}
