class Territory {

    private final int boardHeight;
    private Stone[][] gameBoard;
    private boolean[][] isPartOfMarkedTerritory;
    private boolean[][] isFieldCounted;
    private boolean[][] isTerritoryBorder;


    Territory(Stone[][] gameBoard) {
        this.boardHeight = gameBoard.length;
        this.gameBoard = gameBoard;
        this.isFieldCounted = new boolean[boardHeight][boardHeight];
        this.isPartOfMarkedTerritory = new boolean[boardHeight][boardHeight];
        this.isTerritoryBorder = new boolean[boardHeight][boardHeight];
    }

    int getPointsWhite() {
        int pointsWhite = 0;
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardHeight; j++) {
                markTerritory(i, j);
                if (isTerritoryWhite()) {
                    pointsWhite += countCurrentTerritoryFields();
                }
                resetMarkedTerritory();
                resetTerritoryBorder();
            }
        }
        return pointsWhite;
    }

    int getPointsBlack() {
        int pointsBlack = 0;
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardHeight; j++) {
                markTerritory(i, j);
                if (isTerritoryBlack()) {
                    pointsBlack += countCurrentTerritoryFields();
                }
                resetMarkedTerritory();
                resetTerritoryBorder();
            }
        }
        return pointsBlack;
    }

    boolean isTerritoryWhite() {
        /* Territory only belongs to a player, if the entire border is made out of his stones */
        if (countCurrentTerritoryFields() == 0) {
            return false;
        }
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if (isTerritoryBorder[i][j] && !gameBoard[i][j].isStoneWhite()) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean isTerritoryBlack() {
        if (countCurrentTerritoryFields() == 0) {
            return false;
        }
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if (isTerritoryBorder[i][j] && gameBoard[i][j].isStoneWhite()) {
                    return false;
                }
            }
        }
        return true;
    }

    void markTerritory(int row, int column) {
        if (gameBoard[row][column] == null && !isFieldCounted[row][column]) {
            isPartOfMarkedTerritory[row][column] = true;
            isFieldCounted[row][column] = true;
            markTerritoryBorder(row, column);

            /* If any adjacent field is empty recursively calls itself for that field */
            if (isFieldPartOfTerritory(row - 1, column)) markTerritory(row - 1, column);
            if (isFieldPartOfTerritory(row + 1, column)) markTerritory(row + 1, column);
            if (isFieldPartOfTerritory(row, column - 1)) markTerritory(row, column - 1);
            if (isFieldPartOfTerritory(row, column + 1)) markTerritory(row, column + 1);
        }
    }

    private boolean isFieldPartOfTerritory(int row, int column) {
        try {
            if (gameBoard[row][column] == null && !isPartOfMarkedTerritory[row][column]) {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return false;
    }

    private void markTerritoryBorder(int i, int j) {
        /* Mark non-empty adjacent fields as part of the territory's border */
        if (i != 0 && gameBoard[i - 1][j] != null) isTerritoryBorder[i - 1][j] = true;
        if (i < boardHeight - 1 && gameBoard[i + 1][j] != null) isTerritoryBorder[i + 1][j] = true;
        if (j != 0 && gameBoard[i][j - 1] != null) isTerritoryBorder[i][j - 1] = true;
        if (j < boardHeight - 1 && gameBoard[i][j + 1] != null) isTerritoryBorder[i][j + 1] = true;
    }

    private int countCurrentTerritoryFields() {
        int territoryFields = 0;
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if (isPartOfMarkedTerritory[i][j]) {
                    territoryFields++;
                }
            }
        }
        return territoryFields;
    }

    private void resetMarkedTerritory() {
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardHeight; j++) {
                isPartOfMarkedTerritory[i][j] = false;
            }
        }
    }

    private void resetTerritoryBorder() {
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardHeight; j++) {
                this.isTerritoryBorder[i][j] = false;
            }
        }
    }

    boolean[][] getIsTerritoryBorder() {
        return isTerritoryBorder;
    }

    boolean[][] getIsPartOfMarkedTerritory() {
        return isPartOfMarkedTerritory;
    }

    boolean[][] getIsFieldCounted() {
        return isFieldCounted;
    }
}
