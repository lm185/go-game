package game.files.model;

import game.files.service.RulesService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private int boardHeight;

    //TODO avoid duplicates for connected groups by comparing sets
    public ConnectedGroup() {

    }

    public ConnectedGroup(int startGroupId, boolean isWhite, Stone[][] gameBoard) {
        this.gameBoard = gameBoard;
        this.boardHeight = gameBoard.length;
        this.ids = calculateIds(startGroupId);
        this.isWhite = isWhite;
        // countEyes();
    }

    private Set<Integer> calculateIds(int groupId) {
        List<Integer> ids = new ArrayList<>();
        ids.add(groupId);

        for (int k = 0; k < ids.size(); k++) {
            for (int i = 0; i < boardHeight; i++) {
                for (int j = 0; j < boardHeight; j++) {
                    if (gameBoard[i][j] != null) {
                        for (int id : findConnectedIds(i, j, ids.get(k))) {
                            if (!ids.contains(id)) {
                                ids.add(id);
                            }
                        }
                    }
                }
            }
        }
        return new HashSet<>(ids);
    }

    private Set<Integer> findConnectedIds(int i, int j, int groupId) {
        boolean isGroupWhite = gameBoard[i][j].isStoneWhite();
        Set<Integer> ids = new HashSet<>();

        if (gameBoard[i][j] != null && gameBoard[i][j].getGroupId() == groupId) {
            if (isDiagonalFriend(i - 1, j - 1, groupId, isGroupWhite)) {
                if (gameBoard[i - 1][j] == null && gameBoard[i][j - 1] == null) {
                    ids.add(gameBoard[i - 1][j - 1].getGroupId());
                }
            }
            if (isDiagonalFriend(i - 1, j + 1, groupId, isGroupWhite)) {
                if (gameBoard[i - 1][j] == null && gameBoard[i][j + 1] == null) {
                    ids.add(gameBoard[i - 1][j + 1].getGroupId());
                }
            }
            if (isDiagonalFriend(i + 1, j - 1, groupId, isGroupWhite)) {
                if (gameBoard[i][j - 1] == null && gameBoard[i + 1][j] == null) {
                    ids.add(gameBoard[i + 1][j - 1].getGroupId());
                }
            }
            if (isDiagonalFriend(i + 1, j + 1, groupId, isGroupWhite)) {
                if (gameBoard[i + 1][j] == null && gameBoard[i][j + 1] == null) {
                    ids.add(gameBoard[i + 1][j + 1].getGroupId());
                }
            }
        }
        return ids;
    }

    private boolean isDiagonalFriend(int i, int j, int groupId, boolean isGroupWhite) {
        try {
            if (gameBoard[i][j] != null && gameBoard[i][j].isStoneWhite() == isGroupWhite
                && gameBoard[i][j].getGroupId() != groupId) {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return false;
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
