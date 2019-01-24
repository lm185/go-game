package game.files.service;

import game.files.model.Group;
import game.files.model.Stone;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class KickService {

  private int boardHeight;
  private Stone[][] gameBoard;
  private Group group;
  private int pointsWhite = 0;
  private int pointsBlack = 0;

  public KickService() {

  }

  public KickService(Stone[][] gameBoard) {
    this.boardHeight = gameBoard.length;
    this.gameBoard = gameBoard;
    this.group = new Group(gameBoard);
  }

  public void findAndKickDeadStones(int row, int column) {
    kickAllAdjacentDeadGroups(row, column);
    removeAllDeadGroups();
  }

  void kickAllAdjacentDeadGroups(int row, int column) {
    /*
     * Checks adjacent fields. If they are from the enemy and the group is dead they are kicked.
     * This is to ensure kick integrity. If a player A puts a stone at a point where
     * either the stones of player A or his opponents group is kicked,
     * the opponent stones need to be kicked.
     */
    boolean isCurrentPlayerWhite = gameBoard[row][column].isStoneWhite();
    if (isAdjacentStoneOpponent(row - 1, column, isCurrentPlayerWhite)) {
      kickGroupIfDead(row - 1, column);
    }
    if (isAdjacentStoneOpponent(row + 1, column, isCurrentPlayerWhite)) {
      kickGroupIfDead(row + 1, column);
    }
    if (isAdjacentStoneOpponent(row, column + 1, isCurrentPlayerWhite)) {
      kickGroupIfDead(row, column + 1);
    }
    if (isAdjacentStoneOpponent(row, column - 1, isCurrentPlayerWhite)) {
      kickGroupIfDead(row, column - 1);
    }
  }

  private boolean isAdjacentStoneOpponent(int row, int column, boolean isCurrentPlayerWhite) {
    try {
      if (gameBoard[row][column] != null && isCurrentPlayerWhite != gameBoard[row][column]
          .isStoneWhite()) {
        return true;
      }
    } catch (ArrayIndexOutOfBoundsException ignored) {
      return false;
    }
    return false;
  }

  void removeAllDeadGroups() {
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardHeight; j++) {
        kickGroupIfDead(i, j);
      }
    }
  }

  private void kickGroupIfDead(int row, int column) {
    group.findGroup(row, column);
    if (!group.isGroupAlive()) {
      kickSelectedGroup();
    }
    group.resetGroupSelection();
  }

  private void kickSelectedGroup() {
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardHeight; j++) {
        if (gameBoard[i][j] != null && gameBoard[i][j].isPartOfGroup) {
          if (gameBoard[i][j].isStoneWhite()) {
            pointsBlack++;
          } else {
            pointsWhite++;
          }
          removeStone(i, j);
        }
      }
    }
  }

  public void reset() {
    group.resetGroupSelection();
    pointsBlack = 0;
    pointsWhite = 0;
  }

  private void removeStone(int row, int column) {
    gameBoard[row][column] = null;
  }
}
