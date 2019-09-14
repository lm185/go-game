package game.files.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class ConnectedGroup {
    /*
     * Is the collection of groups which are connected indirectly but definitely
     * Ids contains all group ids which belong to the same connected group
     */

    private Stone[][] gameBoard;
    private int boardHeight;

    private Set<Integer> ids;
    private boolean isWhite;

    public ConnectedGroup() {

    }

    public ConnectedGroup(int startGroupId, boolean isWhite, Stone[][] gameBoard) {
        this.gameBoard = gameBoard;
        this.boardHeight = gameBoard.length;
        this.isWhite = isWhite;
        calculateIds(startGroupId);
    }

    private void calculateIds(int groupId) {
        this.ids = new HashSet<>();
        this.ids.add(groupId);

        addGroupsByFullConnectionPoints();
        addGroupsByHalfConnectionPoints();

    }

    private void addGroupsByHalfConnectionPoints() {
        List<Integer> idsList = new ArrayList<>(this.ids);


    }

    private Set<Integer> findHalfConnectionPoints(int i, int j, int groupId) {
        if (isFieldEmpty(i, j) || !sameGroup(i, j, groupId)) {
            return new HashSet<>();
        }

        Set<Integer> groupIds = new HashSet<>();

        if (isDiagonalSameColor(i - 1, j - 1, groupId) &&
            isFieldEmpty(i - 1, j) && isFieldEmpty(i, j - 1)) {
            groupIds.add(gameBoard[i - 1][j - 1].getGroupId());
        }
        return groupIds;
    }


    private void addGroupsByFullConnectionPoints() {
        List<Integer> idsList = new ArrayList<>(this.ids);

        for (int k = 0; k < idsList.size(); k++) {
            for (int i = 0; i < boardHeight; i++) {
                for (int j = 0; j < boardHeight; j++) {
                    for (int id : findFullConnectionPoints(i, j, idsList.get(k))) {
                        if (!idsList.contains(id)) {
                            idsList.add(id);
                        }
                    }
                }
            }
        }
        this.ids.addAll(idsList);
    }

    private Set<Integer> findFullConnectionPoints(int i, int j, int groupId) {
        if (gameBoard[i][j] == null || gameBoard[i][j].getGroupId() != groupId) {
            return new HashSet<>();
        }

        Set<Integer> groupIds = new HashSet<>();
        if (isDiagonalSameColor(i - 1, j - 1, groupId) &&
            isFieldEmpty(i - 1, j) && isFieldEmpty(i, j - 1)) {
            groupIds.add(gameBoard[i - 1][j - 1].getGroupId());
        }
        if (isDiagonalSameColor(i - 1, j + 1, groupId) &&
            isFieldEmpty(i - 1, j) && isFieldEmpty(i, j + 1)) {
            groupIds.add(gameBoard[i - 1][j + 1].getGroupId());
        }
        if (isDiagonalSameColor(i + 1, j - 1, groupId) &&
            isFieldEmpty(i, j - 1) && isFieldEmpty(i + 1, j)) {
            groupIds.add(gameBoard[i + 1][j - 1].getGroupId());
        }
        if (isDiagonalSameColor(i + 1, j + 1, groupId) &&
            isFieldEmpty(i, j + 1) && isFieldEmpty(i + 1, j)) {
            groupIds.add(gameBoard[i + 1][j + 1].getGroupId());
        }

        return groupIds;
    }


    private boolean isDiagonalSameColor(int i, int j, int groupId) {
        try {
            return !isFieldEmpty(i, j) && isSameColorAsGroup(i, j)
                && !sameGroup(i, j, groupId);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean sameGroup(int i, int j, int groupId) {
        return gameBoard[i][j].getGroupId() == groupId;
    }

    private boolean isSameColorAsGroup(int i, int j) {
        return gameBoard[i][j].isStoneWhite() == this.isWhite();
    }

    private boolean isFieldEmpty(int i, int j) {
        return gameBoard[i][j] == null;
    }
}
