class Board {
    private Stone[][] gameBoard;
    private boolean isCurrentPlayerWhite = false;
    private int pointsWhite = 0;
    private int pointsBlack = 0;
    private final Input input;
    private int passes = 0;

    Board(int boardHeight) { //For Testing Purpose
        this.gameBoard = new Stone[boardHeight][boardHeight];
        this.input = null;
    }

    Board(Input input) {
        this.input = input;
        this.gameBoard = input.gameBoard;
    }

    void play() {
        System.out.println("Enter column and row to place a stone");
        System.out.println("Entering 'pass' will skip your turn");
        System.out.println("If both players pass the game ends");
        System.out.println();

        for (int i = 0; i < 99999999; i++) {
            int[] rowAndColumn = input.getRowAndColumn();
            int row = rowAndColumn[0];
            int column = rowAndColumn[1];

            if (isGameOver(rowAndColumn)) {
                break;
            }
            if (!doesPlayerPass(rowAndColumn)) {
                move(row, column);
                kick(row, column);
            } else {
                System.out.println("Player passed");
            }
            draw();
            nextPlayer();
        }
        gameOver();
    }

    private void gameOver() {
        addTerritoryPoints();

        System.out.println();
        System.out.println("Game Over");
        System.out.println("White has " + this.pointsWhite + " points");
        System.out.println("Black has " + this.pointsBlack + " points");
        System.out.println();

        if (this.pointsBlack > this.pointsWhite) {
            System.out.println("Black won");
        } else if (this.pointsBlack < this.pointsWhite) {
            System.out.println("White won");
        } else {
            System.out.println("Tie");
        }
    }

    private boolean isGameOver(int[] rowAndColumn) {
        if (doesPlayerPass(rowAndColumn)) {
            this.passes++;
        } else {
            this.passes = 0;
        }
        return this.passes == 2;
    }

    void testPlay(int row, int column, boolean isCurrentPlayerWhite) {
        this.isCurrentPlayerWhite = isCurrentPlayerWhite;
        move(row, column);
        kick(row, column);
        draw();
        nextPlayer();
    }

    private void move(int row, int column) {
        Output.printCurrentPlayer(isCurrentPlayerWhite);
        setStone(row, column);
    }

    private void kick(int row, int column) {
        Kick kick = new Kick(gameBoard);

        kick.findAndKickDeadStones(row, column);

        pointsWhite += kick.getPointsWhite();
        pointsBlack += kick.getPointsBlack();
    }

    void addTerritoryPoints() {
        Territory territory = new Territory(gameBoard);
        this.pointsWhite += territory.getPointsWhite();
        this.pointsBlack += territory.getPointsBlack();
    }

    void draw() {
        Output.draw(gameBoard);
    }

    private void nextPlayer() {
        isCurrentPlayerWhite = !isCurrentPlayerWhite;
    }


    private void setStone(int row, int column) {
        gameBoard[row][column] = new Stone(isCurrentPlayerWhite);
    }

    Stone[][] getGameBoard() {
        return gameBoard;
    }

    int getPointsWhite() {
        return pointsWhite;
    }

    private boolean doesPlayerPass(int[] rowAndColumn) {
        return rowAndColumn[0] == -1337 || rowAndColumn[1] == -1337;
    }

    int getPointsBlack() {
        return pointsBlack;
    }

    void setGameBoard(Stone[][] gameBoard) {
        this.gameBoard = gameBoard;
    }
}
