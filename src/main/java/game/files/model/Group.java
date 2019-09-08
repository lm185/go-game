package game.files.model;

import game.files.service.LibertiesService;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Group {

    private Stone[][] gameBoard;
    private int boardHeight;
    private LibertiesService libertiesService;

    public Group() {
    }

    public Group(Stone[][] gameBoard) {
        this.gameBoard = gameBoard;
        this.boardHeight = gameBoard.length;
        this.libertiesService = new LibertiesService(gameBoard);
    }

    public Set<Integer> markGroups() {
        // Gives every group a unique id and returns a Set of them
        resetSelection();
        HashSet<Integer> ids = new HashSet<>();
        int groupId = 1;
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if (gameBoard[i][j] != null && !gameBoard[i][j].isPartOfGroup()) {
                    markGroup(i, j, groupId);
                    ids.add(groupId);
                    groupId++;
                }
            }
        }
        return ids;
    }

    private void markGroup(int row, int column, int groupId) {
        // Marks group at given row and column, assigns the stones a group id
        if (gameBoard[row][column] == null) {
            return;
        }
        gameBoard[row][column].setPartOfGroup(true);
        gameBoard[row][column].setGroupId(groupId);

        boolean isGroupWhite = gameBoard[row][column].isStoneWhite();

        /* If one of the adjacent stones has the same color recursively calls itself */
        if (isStonePartOfGroup(row - 1, column, isGroupWhite)) {
            markGroup(row - 1, column, groupId);
        }
        if (isStonePartOfGroup(row + 1, column, isGroupWhite)) {
            markGroup(row + 1, column, groupId);
        }
        if (isStonePartOfGroup(row, column - 1, isGroupWhite)) {
            markGroup(row, column - 1, groupId);
        }
        if (isStonePartOfGroup(row, column + 1, isGroupWhite)) {
            markGroup(row, column + 1, groupId);
        }
    }

    private boolean isStonePartOfGroup(int row, int column, boolean isGroupWhite) {
        try {
            return gameBoard[row][column] != null && !gameBoard[row][column].isPartOfGroup()
                && gameBoard[row][column].isStoneWhite() == isGroupWhite;
        } catch (ArrayIndexOutOfBoundsException ignored) {
            return false;
        }
    }

    public boolean isGroupAlive(int groupId) {
        int libertiesGroup = 0;
        int connectionsGroup = 0;

        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if (gameBoard[i][j] != null && gameBoard[i][j].getGroupId() == groupId) {
                    libertiesGroup += libertiesService.findLibertiesForStone(i, j);
                    connectionsGroup += libertiesService.findConnectionsForStone(i, j);
                }
            }
        }
        return libertiesGroup - connectionsGroup != 0;
    }

    private void resetSelection() {
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if (gameBoard[i][j] != null) {
                    gameBoard[i][j].setPartOfGroup(false);
                }
            }
        }
    }

}
