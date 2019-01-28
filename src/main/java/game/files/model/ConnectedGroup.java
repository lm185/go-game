package game.files.model;

import game.files.service.RulesService;
import game.files.service.TerritoryService;
import java.util.HashSet;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ConnectedGroup {

  private HashSet<Integer> ids;
  private boolean isWhite;
  private int eyes;
  private int eyeLikes;
  private boolean[][] enclosedFields;
  private Stone[][] gameBoard;

  public ConnectedGroup() {

  }

  public ConnectedGroup(HashSet<Integer> ids, boolean isWhite, Stone[][] gameBoard) {
    this.ids = ids;
    this.isWhite = isWhite;
    this.gameBoard = gameBoard;
    countEyes();
  }


  private void countEyes() {
    Territory territory = new Territory(gameBoard);
    this.enclosedFields = territory.findTerritory(ids);
    RulesService rulesService = new RulesService(gameBoard);
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard.length; j++) {
        if (this.enclosedFields[i][j]) {
          if (rulesService.doesStoneDie(i, j, isWhite)) {
            eyes++;
          }
        }
      }
    }
  }

}
