package game.files.model;

import game.files.service.TerritoryService;
import java.util.HashSet;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Territory {
  private boolean[][] territory;
  private Stone[][] gameBoard;

  public Territory(){

  }

  public Territory(Stone[][] gameBoard){
    this.territory = new boolean[gameBoard.length][gameBoard.length];
    this.gameBoard = gameBoard;
  }

  public boolean[][] findTerritory(HashSet<Integer> ids) {
    for (int id : ids) {
      for (int i = 0; i < gameBoard.length; i++) {
        for (int j = 0; j < gameBoard.length; j++) {
          if (gameBoard[i][j] == null) {
            markTerritory(id, i, j);
          }
        }
      }
    }
    return this.territory;
  }

  private void markTerritory(int id, int i, int j) {
    TerritoryService territoryService = new TerritoryService(gameBoard);
    boolean[][] territory = territoryService.findTerritory(i, j);
    if (territoryService.borderId() == id) {
      for (int k = 0; k < territory.length; k++) {
        for (int l = 0; l < territory.length; l++) {
          if (territory[k][l]) {
            this.territory[k][l] = true;
          }
        }
      }
    }
  }
}
