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
        this.isWhite = isWhite;
        setIds(calculateIds(startGroupId));
        // countEyes();
    }

    private Set<Integer> calculateIds(int groupId) {
        List<Integer> idsList = new ArrayList<>();
        idsList.add(groupId);

        for (int k = 0; k < idsList.size(); k++) {
            for (int i = 0; i < boardHeight; i++) {
                for (int j = 0; j < boardHeight; j++) {
                    for (int id : findConnectedGroupIds(i, j, idsList.get(k))) {
                        if (!idsList.contains(id)) {
                            idsList.add(id);
                        }
                    }
                }
            }
        }
        return new HashSet<>(idsList);
    }

    private Set<Integer> findConnectedGroupIds(int i, int j, int groupId) {
        if (gameBoard[i][j] == null || gameBoard[i][j].getGroupId() != groupId) {
            return new HashSet<>();
        }

        Set<Integer> connectedGroupIds = new HashSet<>();

        if (isDiagonalFriend(i - 1, j - 1, groupId) &&
            isUpperFieldEmpty(i, j) && isLeftFieldEmpty(i, j)) {
            connectedGroupIds.add(gameBoard[i - 1][j - 1].getGroupId());
        }
        if (isDiagonalFriend(i - 1, j + 1, groupId) &&
            isUpperFieldEmpty(i, j) && isRightFieldEmpty(i, j)) {
            connectedGroupIds.add(gameBoard[i - 1][j + 1].getGroupId());
        }
        if (isDiagonalFriend(i + 1, j - 1, groupId) &&
            isLeftFieldEmpty(i, j) && isLowerFieldEmpty(i, j)) {
            connectedGroupIds.add(gameBoard[i + 1][j - 1].getGroupId());
        }
        if (isDiagonalFriend(i + 1, j + 1, groupId) &&
            isRightFieldEmpty(i, j) && isLowerFieldEmpty(i, j)) {
            connectedGroupIds.add(gameBoard[i + 1][j + 1].getGroupId());
        }

        return connectedGroupIds;
    }

    private boolean isDiagonalFriend(int i, int j, int groupId) {
        try {
            return gameBoard[i][j] != null && gameBoard[i][j].isStoneWhite() == this.isWhite()
                && gameBoard[i][j].getGroupId() != groupId;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
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


    private boolean isUpperFieldEmpty(int i, int j) {
        return gameBoard[i - 1][j] == null;
    }

    private boolean isLowerFieldEmpty(int i, int j) {
        return gameBoard[i + 1][j] == null;
    }

    private boolean isLeftFieldEmpty(int i, int j) {
        return gameBoard[i][j - 1] == null;
    }

    private boolean isRightFieldEmpty(int i, int j) {
        return gameBoard[i][j + 1] == null;
    }
}
