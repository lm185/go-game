package game.files.service;

import game.files.model.Stone;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LibertiesService {

  private Stone[][] gameBoard;
  private int boardHeight;

  public LibertiesService() {
  }

  public LibertiesService(Stone[][] gameBoard) {
    this.gameBoard = gameBoard;
    this.boardHeight = gameBoard.length;
  }

  public int findConnectionsForStone(int row, int column) {
    boolean isStoneWhite = gameBoard[row][column].isStoneWhite();
    int connectionCounter = 0;

    if (isAdjacentFieldFriend(row - 1, column, isStoneWhite)) {
      connectionCounter++;
    }
    if (isAdjacentFieldFriend(row + 1, column, isStoneWhite)) {
      connectionCounter++;
    }
    if (isAdjacentFieldFriend(row, column - 1, isStoneWhite)) {
      connectionCounter++;
    }
    if (isAdjacentFieldFriend(row, column + 1, isStoneWhite)) {
      connectionCounter++;
    }

    return connectionCounter;
  }

  public int findLibertiesForStone(int row, int column) {
    int libertiesCounter = 4;
    boolean isStoneWhite = gameBoard[row][column].isStoneWhite();

    /* Remove Liberties if Stone is on the edge of the board */
    if (row == 0 || row == boardHeight - 1) {
      libertiesCounter -= 1;
    }
    if (column == 0 || column == boardHeight - 1) {
      libertiesCounter -= 1;
    }

    /* Remove Liberties for enemy stones */
    if (isAdjacentFieldEnemy(row - 1, column, isStoneWhite)) {
      libertiesCounter--;
    }
    if (isAdjacentFieldEnemy(row + 1, column, isStoneWhite)) {
      libertiesCounter--;
    }
    if (isAdjacentFieldEnemy(row, column - 1, isStoneWhite)) {
      libertiesCounter--;
    }
    if (isAdjacentFieldEnemy(row, column + 1, isStoneWhite)) {
      libertiesCounter--;
    }

    return libertiesCounter;
  }

  private boolean isAdjacentFieldFriend(int row, int column, boolean isStoneWhite) {
    try {
      return (gameBoard[row][column] != null
          && gameBoard[row][column].isStoneWhite() == isStoneWhite);
    } catch (ArrayIndexOutOfBoundsException ignored) {
      return false;
    }
  }

  private boolean isAdjacentFieldEnemy(int row, int column, boolean isStoneWhite) {
    try {
      return (gameBoard[row][column] != null
          && gameBoard[row][column].isStoneWhite() == !isStoneWhite);
    } catch (ArrayIndexOutOfBoundsException ignored) {
      return false;
    }
  }
}
