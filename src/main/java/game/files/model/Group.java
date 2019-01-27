package game.files.model;

import game.files.service.LibertiesService;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Group {

  private Stone[][] gameBoard;
  private int boardHeight;
  private LibertiesService libertiesService;

  public Group() {
  }

  public Group(Stone[][] gameBoard) {
    this.gameBoard = gameBoard;
    this.boardHeight = gameBoard.length;
    this.libertiesService = new LibertiesService(gameBoard);
  }

  public void findGroup(int row, int column) {
    if (gameBoard[row][column] == null) {
      return;
    }
    gameBoard[row][column].setPartOfGroup(true);
    boolean isGroupWhite = gameBoard[row][column].isStoneWhite();

    /* If one of the adjacent stones has the same color recursively calls itself */
    if (isStonePartOfGroup(row - 1, column, isGroupWhite)) {
      findGroup(row - 1, column);
    }
    if (isStonePartOfGroup(row + 1, column, isGroupWhite)) {
      findGroup(row + 1, column);
    }
    if (isStonePartOfGroup(row, column - 1, isGroupWhite)) {
      findGroup(row, column - 1);
    }
    if (isStonePartOfGroup(row, column + 1, isGroupWhite)) {
      findGroup(row, column + 1);
    }
  }

  private boolean isStonePartOfGroup(int row, int column, boolean isGroupWhite) {
    try {
      return gameBoard[row][column] != null && !gameBoard[row][column].isPartOfGroup
          && gameBoard[row][column].isStoneWhite() == isGroupWhite;
    } catch (ArrayIndexOutOfBoundsException ignored) {
      return false;
    }
  }

  public boolean isGroupAlive() {
    int libertiesGroup = 0;
    int connectionsGroup = 0;

    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardHeight; j++) {
        if (gameBoard[i][j] != null && gameBoard[i][j].isPartOfGroup) {
          libertiesGroup += libertiesService.findLibertiesForStone(i, j);
          connectionsGroup += libertiesService.findConnectionsForStone(i, j);
        }
      }
    }
    return libertiesGroup - connectionsGroup != 0;
  }

  public void resetSelection() {
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardHeight; j++) {
        if (gameBoard[i][j] != null) {
          gameBoard[i][j].setPartOfGroup(false);
        }
      }
    }
  }
}
