package game.files.service;

import game.files.model.Stone;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class RulesService {

  private Stone[][] gameBoard;
  private Stone[][] previousStance;
  private int playedRow;
  private int playedColumn;

  // Can not put Stone where he would instantly die rule
  public boolean doesStoneDie(int playedRow, int playedColumn, boolean isWhite) {
    Stone[][] testBoard = copyBoard();
    testBoard[playedRow][playedColumn] = new Stone(isWhite);

    KickService kickService = new KickService(testBoard);
    kickService.findAndKickDeadStones(playedRow, playedColumn);

    return testBoard[playedRow][playedColumn] == null;
  }

  public RulesService() {

  }

  public RulesService(Stone[][] gameBoard) {
    this.gameBoard = gameBoard;
  }

  private void saveStance() {
    this.previousStance = copyBoard();
  }

  private Stone[][] copyBoard() {
    Stone[][] destination = new Stone[gameBoard.length][gameBoard.length];
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard.length; j++) {
        destination[i][j] = new Stone(gameBoard[i][j].isWhite());
      }
    }
    return destination;
  }

  private boolean didBoardChange() {
    //TODO tests
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard.length; j++) {
        if (previousStance[i][j] == null && gameBoard[i][j] != null
            || previousStance[i][j] != null && gameBoard[i][j] == null) {
          return true;
        }
        if (previousStance[i][j] != null && gameBoard[i][j] != null) {
          if (previousStance[i][j].isWhite() != gameBoard[i][j].isWhite()) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
