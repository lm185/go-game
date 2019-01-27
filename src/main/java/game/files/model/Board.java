package game.files.model;

import game.files.service.InputService;
import game.files.service.KickService;
import game.files.service.OutputService;
import game.files.service.TerritoryService;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Board {

  private Stone[][] gameBoard;
  private boolean isCurrentPlayerWhite = false;
  private InputService inputService;
  private int pointsWhite = 0;
  private int pointsBlack = 0;
  private int consecutivePasses = 0;

  public Board() {
  }

  public Board(Stone[][] gameBoard) { //For Testing Purpose
    this.gameBoard = gameBoard;
  }

  public Board(InputService inputService) {
    this.inputService = inputService;
    this.gameBoard = inputService.getGameBoard();
  }

  public void play() {
    System.out.println("Enter column and row to place a stone");
    System.out.println("Entering 'pass' will skip your turn");
    System.out.println("If both players pass the game ends");
    System.out.println();

    while (true) {
      //TODO routine for rulesservice
      int[] rowAndColumn = inputService.findRowAndColumn();
      int row = rowAndColumn[0];
      int column = rowAndColumn[1];

      if (isGameOver(rowAndColumn)) {
        break;
      }
      if (doesPlayerPass(rowAndColumn)) {
        System.out.println("Player passed");
      } else {
        move(row, column);
        kick(row, column);
      }
      draw();
      nextPlayer();
    }
    gameOver();
  }

  private void gameOver() {
    addTerritoryPoints();
    OutputService.gameOver(pointsWhite, pointsBlack);
  }

  private boolean isGameOver(int[] rowAndColumn) {
    if (doesPlayerPass(rowAndColumn)) {
      consecutivePasses++;
    } else {
      consecutivePasses = 0;
    }
    return consecutivePasses == 2;
  }

  void testPlay(int row, int column, boolean isCurrentPlayerWhite) {
    this.isCurrentPlayerWhite = isCurrentPlayerWhite;
    move(row, column);
    kick(row, column);
    draw();
    nextPlayer();
  }

  private void move(int row, int column) {
    OutputService.printCurrentPlayer(isCurrentPlayerWhite);
    setStone(row, column);
  }

  private void kick(int row, int column) {
    KickService kickService = new KickService(copyBoard());
    setGameBoard(kickService.kickAllDeadGroups(row, column));

    pointsWhite += kickService.getPointsWhite();
    pointsBlack += kickService.getPointsBlack();
  }

  void addTerritoryPoints() {
    TerritoryService territoryService = new TerritoryService(copyBoard());
    pointsWhite += territoryService.getPointsWhite();
    pointsBlack += territoryService.getPointsBlack();
  }

  public void draw() {
    OutputService.draw(this.gameBoard);
  }

  private void nextPlayer() {
    isCurrentPlayerWhite = !isCurrentPlayerWhite;
  }

  private void setStone(int row, int column) {
    gameBoard[row][column] = new Stone(isCurrentPlayerWhite);
  }

  private boolean doesPlayerPass(int[] rowAndColumn) {
    return rowAndColumn[0] == -1337 || rowAndColumn[1] == -1337;
  }

  public Stone[][] copyBoard() {
    Stone[][] destination = new Stone[gameBoard.length][gameBoard.length];
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard.length; j++) {
        if (gameBoard[i][j] != null) {
          destination[i][j] = new Stone(gameBoard[i][j].isWhite());
        }
      }
    }
    return destination;
  }
}
