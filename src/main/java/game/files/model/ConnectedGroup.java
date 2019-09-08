package game.files.model;

import game.files.service.RulesService;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class ConnectedGroup {

    private Set<Integer> ids;
    private boolean isWhite;
    private int eyes;
    private int eyeLikes;
    private boolean[][] enclosedFields;
    private Stone[][] gameBoard;

    public ConnectedGroup() {

    }

    private void calculateIds(int groupId) {
        this.ids = new HashSet<>();
        //TODO
    }

    public ConnectedGroup(Set<Integer> ids, boolean isWhite, Stone[][] gameBoard) {
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
                if (this.enclosedFields[i][j] && rulesService.doesStoneDie(i, j, isWhite)) {
                    eyes++;
                }
            }
        }
    }

}
