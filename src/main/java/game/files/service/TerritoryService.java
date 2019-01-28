package game.files.service;

import game.files.model.Stone;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class TerritoryService {

  private int boardHeight;
  private Stone[][] gameBoard;
  private boolean[][] isPartOfMarkedTerritory;
  private boolean[][] isFieldCounted;
  private boolean[][] isTerritoryBorder;

  public TerritoryService() {

  }

  public TerritoryService(Stone[][] gameBoard) {
    this.boardHeight = gameBoard.length;
    this.gameBoard = gameBoard;
    this.isFieldCounted = new boolean[boardHeight][boardHeight];
    this.isPartOfMarkedTerritory = new boolean[boardHeight][boardHeight];
    this.isTerritoryBorder = new boolean[boardHeight][boardHeight];
  }

  public boolean[][] findTerritory(int i, int j) {
    markTerritory(i, j);
    if (isTerritoryWhite() || isTerritoryBlack()) {
      return isPartOfMarkedTerritory;
    }
    return new boolean[boardHeight][boardHeight];
  }

  public int borderId() {
    for (int i = 0; i < isTerritoryBorder.length; i++) {
      for (int j = 0; j < isTerritoryBorder.length; j++) {
        if (isTerritoryBorder[i][j]) {
          return gameBoard[i][j].getGroupId();
        }
      }
    }
    return -1;
  }

  public int getPointsWhite() {
    resetIsFieldCounted();
    int pointsWhite = 0;
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardHeight; j++) {
        markTerritory(i, j);
        if (isTerritoryWhite()) {
          pointsWhite += countCurrentTerritoryFields();
        }
        resetMarkedTerritory();
        resetTerritoryBorder();
      }
    }
    return pointsWhite;
  }

  public int getPointsBlack() {
    resetIsFieldCounted();
    int pointsBlack = 0;
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardHeight; j++) {
        markTerritory(i, j);
        if (isTerritoryBlack()) {
          pointsBlack += countCurrentTerritoryFields();
        }
        resetMarkedTerritory();
        resetTerritoryBorder();
      }
    }
    return pointsBlack;
  }

  boolean isTerritoryWhite() {
    /* Territory only belongs to a player, if the entire border is made out of his stones */
    if (countCurrentTerritoryFields() == 0 || getBorderLength() == 0) {
      return false;
    }
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardHeight; j++) {
        if (isTerritoryBorder[i][j] && !gameBoard[i][j].isStoneWhite()) {
          return false;
        }
      }
    }
    return true;
  }

  boolean isTerritoryBlack() {
    if (countCurrentTerritoryFields() == 0 || getBorderLength() == 0) {
      return false;
    }
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardHeight; j++) {
        if (isTerritoryBorder[i][j] && gameBoard[i][j].isStoneWhite()) {
          return false;
        }
      }
    }
    return true;
  }

  void markTerritory(int row, int column) {
    if (gameBoard[row][column] == null && !isFieldCounted[row][column]) {
      isPartOfMarkedTerritory[row][column] = true;
      isFieldCounted[row][column] = true;
      markTerritoryBorder(row, column);

      /* If any adjacent field is empty recursively calls itself for that field */
      if (isFieldPartOfTerritory(row - 1, column)) {
        markTerritory(row - 1, column);
      }
      if (isFieldPartOfTerritory(row + 1, column)) {
        markTerritory(row + 1, column);
      }
      if (isFieldPartOfTerritory(row, column - 1)) {
        markTerritory(row, column - 1);
      }
      if (isFieldPartOfTerritory(row, column + 1)) {
        markTerritory(row, column + 1);
      }
    }
  }

  private boolean isFieldPartOfTerritory(int row, int column) {
    try {
      return (gameBoard[row][column] == null && !isPartOfMarkedTerritory[row][column]);
    } catch (ArrayIndexOutOfBoundsException ignored) {
      return false;
    }
  }

  private void markTerritoryBorder(int i, int j) {
    /* Mark non-empty adjacent fields as part of the territory's border */
    if (i != 0 && gameBoard[i - 1][j] != null) {
      isTerritoryBorder[i - 1][j] = true;
    }
    if (i < boardHeight - 1 && gameBoard[i + 1][j] != null) {
      isTerritoryBorder[i + 1][j] = true;
    }
    if (j != 0 && gameBoard[i][j - 1] != null) {
      isTerritoryBorder[i][j - 1] = true;
    }
    if (j < boardHeight - 1 && gameBoard[i][j + 1] != null) {
      isTerritoryBorder[i][j + 1] = true;
    }
  }

  private int countCurrentTerritoryFields() {
    return countFields(isPartOfMarkedTerritory);
  }

  private int countFields(boolean[][] territoryToCount) {
    int fields = 0;
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardHeight; j++) {
        if (territoryToCount[i][j]) {
          fields++;
        }
      }
    }
    return fields;
  }

  private void resetIsFieldCounted() {
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardHeight; j++) {
        isFieldCounted[i][j] = false;
      }
    }
  }

  private void resetMarkedTerritory() {
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardHeight; j++) {
        isPartOfMarkedTerritory[i][j] = false;
      }
    }
  }

  private void resetTerritoryBorder() {
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardHeight; j++) {
        this.isTerritoryBorder[i][j] = false;
      }
    }
  }
//TODO CLEANUP

  boolean[][] getIsPartOfMarkedTerritory() {
    return isPartOfMarkedTerritory;
  }

  boolean[][] getIsFieldCounted() {
    return isFieldCounted;
  }

  private int getBorderLength() {
    return countFields(isTerritoryBorder);
  }
}
