package game.files.model;

import java.util.*;

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

    private int getGroupId(int row, int column) {
        return gameBoard[row][column].getGroupId();
    }

    private Set<Integer> findHalfConnectionPoints(int row, int column, int groupId) {
        if (isFieldEmpty(row, column) || !hasSameId(row, column, groupId)) {
            return new HashSet<>();
        }

        Set<Integer> groupIds = new HashSet<>();
        Map<Integer, Integer> groupConnectionCount = new HashMap<>();

        if (isFieldFriend(row - 1, column - 1) &&
            (isFieldEmpty(row - 1, column) || isFieldEmpty(row, column - 1))) {
            int fieldId = getGroupId(row - 1, column - 1);
            groupConnectionCount.put(fieldId, groupConnectionCount.getOrDefault(fieldId, 1));
        }

        if (isFieldFriend(row - 1, column + 1) &&
            (isFieldEmpty(row - 1, column) || isFieldEmpty(row, column + 1))) {
            int fieldId = getGroupId(row - 1, column + 1);
            groupConnectionCount.put(fieldId, groupConnectionCount.getOrDefault(fieldId, 1));
        }

        if (isFieldFriend(row + 1, column - 1) &&
            (isFieldEmpty(row + 1, column) || isFieldEmpty(row, column - 1))) {
            int fieldId = getGroupId(row + 1, column - 1);
            groupConnectionCount.put(fieldId, groupConnectionCount.getOrDefault(fieldId, 1));
        }

        if (isFieldFriend(row + 1, column + 1) &&
            (isFieldEmpty(row + 1, column) || isFieldEmpty(row, column + 1))) {
            int fieldId = getGroupId(row + 1, column + 1);
            groupConnectionCount.put(fieldId, groupConnectionCount.getOrDefault(fieldId, 1));
        }


        return groupIds;
    }


    private void addGroupsByFullConnectionPoints() {
        List<Integer> idsList = new ArrayList<>(this.ids);

        for (int i = 0; i < idsList.size(); i++) {
            for (int row = 0; row < boardHeight; row++) {
                for (int column = 0; column < boardHeight; column++) {
                    for (int id : findIdsFullConnectionPoints(row, column, idsList.get(i))) {
                        if (!idsList.contains(id)) {
                            idsList.add(id);
                        }
                    }
                }
            }
        }
        this.ids.addAll(idsList);
    }

    private Set<Integer> findIdsFullConnectionPoints(int row, int column, int groupId) {
        if (gameBoard[row][column] == null || gameBoard[row][column].getGroupId() != groupId) {
            return new HashSet<>();
        }

        Set<Integer> groupIds = new HashSet<>();
        if (isFieldFriend(row - 1, column - 1) &&
            !hasSameId(row - 1, column - 1, groupId) &&
            isFieldEmpty(row - 1, column) && isFieldEmpty(row, column - 1)) {
            groupIds.add(gameBoard[row - 1][column - 1].getGroupId());
        }
        if (isFieldFriend(row - 1, column + 1) &&
            !hasSameId(row - 1, column + 1, groupId) &&
            isFieldEmpty(row - 1, column) && isFieldEmpty(row, column + 1)) {
            groupIds.add(gameBoard[row - 1][column + 1].getGroupId());
        }
        if (isFieldFriend(row + 1, column - 1) &&
            !hasSameId(row + 1, column - 1, groupId) &&
            isFieldEmpty(row, column - 1) && isFieldEmpty(row + 1, column)) {
            groupIds.add(gameBoard[row + 1][column - 1].getGroupId());
        }
        if (isFieldFriend(row + 1, column + 1) &&
            !hasSameId(row + 1, column + 1, groupId) &&
            isFieldEmpty(row, column + 1) && isFieldEmpty(row + 1, column)) {
            groupIds.add(gameBoard[row + 1][column + 1].getGroupId());
        }

        return groupIds;
    }


    private boolean isFieldFriend(int row, int column) {
        try {
            return !isFieldEmpty(row, column) && isSameColorAsGroup(row, column);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean hasSameId(int row, int column, int groupId) {
        try {
            return gameBoard[row][column].getGroupId() == groupId;
        } catch (ArrayIndexOutOfBoundsException ignored) {
            return false;
        }
    }

    private boolean isSameColorAsGroup(int row, int column) {
        return gameBoard[row][column].isStoneWhite() == this.isWhite();
    }

    private boolean isFieldEmpty(int i, int j) {
        return gameBoard[i][j] == null;
    }
}
