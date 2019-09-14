package game.files.model;

import java.util.*;

import lombok.Data;

@Data
public class LinkedGroup {
    /*
     * Is the collection of groups which are connected indirectly but definitely
     * Ids contains all group ids which belong to the same connected group
     * Friend := Stone has same Color as this Linked Group
     *
     */

    private Stone[][] gameBoard;
    private int boardHeight;

    private Set<Integer> ids;
    private boolean isWhite;

    public LinkedGroup() {

    }

    public LinkedGroup(int startGroupId, boolean isWhite, Stone[][] gameBoard) {
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

    private void addGroupsByFullConnectionPoints() {
        List<Integer> idList = new ArrayList<>(this.ids);

        for (int group = 0; group < idList.size(); group++) {
            for (int row = 0; row < boardHeight; row++) {
                for (int column = 0; column < boardHeight; column++) {
                    updateFCPointsList(idList, group, row, column);
                }
            }
        }
        this.ids.addAll(idList);
    }

    private void addGroupsByHalfConnectionPoints() {
        List<Integer> idList = new ArrayList<>(this.ids);

        for (int group = 0; group < idList.size(); group++) {
            Map<Integer, Integer> groupHCPointsMap = new HashMap<>();

            for (int row = 0; row < boardHeight; row++) {
                for (int column = 0; column < boardHeight; column++) {
                    updateHCPointsMap(idList, groupHCPointsMap, group, row, column);
                    updateIdList(idList, groupHCPointsMap);
                }
            }
        }

        this.ids.addAll(idList);
    }

    private void updateFCPointsList(List<Integer> idList, int i, int row, int column) {
        for (int id : findGroupIdsFCPoints(row, column, idList.get(i))) {
            if (!idList.contains(id)) {
                idList.add(id);
            }
        }
    }

    private void updateIdList(List<Integer> idList, Map<Integer, Integer> groupHCPointsMap) {
        for (Integer id : groupHCPointsMap.keySet()) {
            if (groupHCPointsMap.getOrDefault(id, 0) >= 2 &&
                !idList.contains(id)) {
                idList.add(id);
            }
        }
    }

    private void updateHCPointsMap(List<Integer> idList, Map<Integer, Integer> groupHCPointsMap,
                                   int i, int row, int column) {
        if (!isFieldEmpty(row, column) && isSameGroup(row, column, idList.get(i))) {
            countDiagonalHCPoints(row, column, groupHCPointsMap);
            countStraightHCPoints(row, column, groupHCPointsMap);
        }
    }

    private void countStraightHCPoints(int row, int column,
                                       Map<Integer, Integer> groupConnectionCount) {
        if (isFieldFriend(row - 2, column) && isFieldEmpty(row - 1, column)) {
            int fieldId = getGroupId(row - 2, column);
            groupConnectionCount.put(fieldId, groupConnectionCount.getOrDefault(fieldId, 0) + 1);
        }
        if (isFieldFriend(row + 2, column) && isFieldEmpty(row + 1, column)) {
            int fieldId = getGroupId(row + 2, column);
            groupConnectionCount.put(fieldId, groupConnectionCount.getOrDefault(fieldId, 0) + 1);
        }
        if (isFieldFriend(row, column - 2) && isFieldEmpty(row, column - 1)) {
            int fieldId = getGroupId(row, column - 2);
            groupConnectionCount.put(fieldId, groupConnectionCount.getOrDefault(fieldId, 0) + 1);
        }
        if (isFieldFriend(row, column + 2) && isFieldEmpty(row, column + 1)) {
            int fieldId = getGroupId(row, column + 2);
            groupConnectionCount.put(fieldId, groupConnectionCount.getOrDefault(fieldId, 0) + 1);
        }
    }

    private void countDiagonalHCPoints(int row, int column,
                                       Map<Integer, Integer> groupConnectionCount) {
        if (isFieldFriend(row - 1, column - 1) &&
            (isFieldEmpty(row - 1, column) || isFieldEmpty(row, column - 1))) {
            int fieldId = getGroupId(row - 1, column - 1);
            groupConnectionCount.put(fieldId, groupConnectionCount.getOrDefault(fieldId, 0) + 1);
        }

        if (isFieldFriend(row - 1, column + 1) &&
            (isFieldEmpty(row - 1, column) || isFieldEmpty(row, column + 1))) {
            int fieldId = getGroupId(row - 1, column + 1);
            groupConnectionCount.put(fieldId, groupConnectionCount.getOrDefault(fieldId, 0) + 1);
        }

        if (isFieldFriend(row + 1, column - 1) &&
            (isFieldEmpty(row + 1, column) || isFieldEmpty(row, column - 1))) {
            int fieldId = getGroupId(row + 1, column - 1);
            groupConnectionCount.put(fieldId, groupConnectionCount.getOrDefault(fieldId, 0) + 1);
        }

        if (isFieldFriend(row + 1, column + 1) &&
            (isFieldEmpty(row + 1, column) || isFieldEmpty(row, column + 1))) {
            int fieldId = getGroupId(row + 1, column + 1);
            groupConnectionCount.put(fieldId, groupConnectionCount.getOrDefault(fieldId, 0) + 1);
        }
    }


    private Set<Integer> findGroupIdsFCPoints(int row, int column, int groupId) {
        if (isFieldEmpty(row, column) || !isSameGroup(row, column, groupId)) {
            return new HashSet<>();
        }

        Set<Integer> groupIds = new HashSet<>();
        if (isFieldFriend(row - 1, column - 1) &&
            isFieldEmpty(row - 1, column) && isFieldEmpty(row, column - 1)) {
            groupIds.add(gameBoard[row - 1][column - 1].getGroupId());
        }
        if (isFieldFriend(row - 1, column + 1) &&
            isFieldEmpty(row - 1, column) && isFieldEmpty(row, column + 1)) {
            groupIds.add(gameBoard[row - 1][column + 1].getGroupId());
        }
        if (isFieldFriend(row + 1, column - 1) &&
            isFieldEmpty(row, column - 1) && isFieldEmpty(row + 1, column)) {
            groupIds.add(gameBoard[row + 1][column - 1].getGroupId());
        }
        if (isFieldFriend(row + 1, column + 1) &&
            isFieldEmpty(row, column + 1) && isFieldEmpty(row + 1, column)) {
            groupIds.add(gameBoard[row + 1][column + 1].getGroupId());
        }

        return groupIds;
    }


    private int getGroupId(int row, int column) {
        return gameBoard[row][column].getGroupId();
    }

    private boolean isFieldFriend(int row, int column) {
        try {
            return !isFieldEmpty(row, column) && isSameColorAsLinkedGroup(row, column);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean isSameGroup(int row, int column, int groupId) {
        try {
            return gameBoard[row][column].getGroupId() == groupId;
        } catch (ArrayIndexOutOfBoundsException ignored) {
            return false;
        }
    }

    private boolean isSameColorAsLinkedGroup(int row, int column) {
        return gameBoard[row][column].isStoneWhite() == this.isWhite();
    }

    private boolean isFieldEmpty(int row, int column) {
        return gameBoard[row][column] == null;
    }
}
