package game.files.service;

import game.files.model.Stone;
import org.springframework.stereotype.Service;

@Service
public class OutputService {

  private OutputService() {
    throw new IllegalStateException("Utility class");
  }

  public static void draw(Stone[][] brett) {
    int boardHeight = brett.length;
    pritRows(boardHeight);
    for (int i = 0; i < boardHeight; i++) {
      System.out.print(i); // Numbering of the columns
      for (int j = 0; j < boardHeight; j++) {
        printField(brett[i][j], j + 1 == boardHeight);
      }
      System.out.println();
    }
    System.out.print(" ");
    for (int i = 0; i < boardHeight; i++) {
      System.out.print(" ¯");
    }
    System.out.println();
  }

  private static void printField(Stone stone, boolean b) {
    if (stone == null) {
      System.out.print("| ");
    }
    if (stone != null && !stone.isStoneWhite()) {
      System.out.print("|o"); // o for black
    }
    if (stone != null && stone.isStoneWhite()) {
      System.out.print("|*"); // * for white
    }
    if (b) {
      System.out.print("|");
    }
  }

  private static void pritRows(int boardHeight) {
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
