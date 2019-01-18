package game.files.model;

import game.files.model.Stone;
import lombok.Data;

@Data
public class Liberties {
    private Stone[][] gameBoard;
    private int boardHeight;

    public Liberties(Stone[][] gameBoard) {
        this.gameBoard = gameBoard;
        this.boardHeight = gameBoard.length;
    }

    public void setLibertiesForStone(int row, int column) {
        gameBoard[row][column].setLiberties(getLibertiesForStone(row, column));
    }

    public void setConnectionsForStone(int row, int column) {
        gameBoard[row][column].setConnectionCounter(getConnectionsForStone(row, column));
    }

    int getConnectionsForStone(int row, int column) {
        boolean isStoneWhite = gameBoard[row][column].isStoneWhite();
        int connectionCounter = 0;

        if (isAdjacentFieldFriend(row - 1, column, isStoneWhite)) connectionCounter++;
        if (isAdjacentFieldFriend(row + 1, column, isStoneWhite)) connectionCounter++;
        if (isAdjacentFieldFriend(row, column - 1, isStoneWhite)) connectionCounter++;
        if (isAdjacentFieldFriend(row, column + 1, isStoneWhite)) connectionCounter++;

        return connectionCounter;
    }

    int getLibertiesForStone(int row, int column) {
        int libertiesCounter = 4;
        boolean isStoneWhite = gameBoard[row][column].isStoneWhite();

        /* Remove game.files.model.Liberties if game.files.model.Stone is on the edge of the board */
        if (row == 0 || row == boardHeight - 1) libertiesCounter -= 1;
        if (column == 0 || column == boardHeight - 1) libertiesCounter -= 1;

        /* Remove game.files.model.Liberties for enemy stones */
        if (isAdjacentFieldEnemy(row - 1, column, isStoneWhite)) libertiesCounter--;
        if (isAdjacentFieldEnemy(row + 1, column, isStoneWhite)) libertiesCounter--;
        if (isAdjacentFieldEnemy(row, column - 1, isStoneWhite)) libertiesCounter--;
        if (isAdjacentFieldEnemy(row, column + 1, isStoneWhite)) libertiesCounter--;

        return libertiesCounter;
    }

    private boolean isAdjacentFieldFriend(int row, int column, boolean isStoneWhite) {
        try {
            if (gameBoard[row][column] != null && gameBoard[row][column].isStoneWhite() == isStoneWhite) return true;
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        return false;
    }

    private boolean isAdjacentFieldEnemy(int row, int column, boolean isStoneWhite) {
        try {
            if (gameBoard[row][column] != null && gameBoard[row][column].isStoneWhite() == !isStoneWhite) return true;
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        return false;
    }
}
