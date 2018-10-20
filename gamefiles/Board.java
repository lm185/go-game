class Board {
    private Stone[][] gameBoard;
    private boolean isCurrentPlayerWhite = false;
    private int pointsWhite = 0;
    private int pointsBlack = 0;
    private final Input input;

    Board(int boardHeight) { //For Testing Purpose
        this.gameBoard = new Stone[boardHeight][boardHeight];
        this.input = null;
    }

    Board(Input input) {
        this.input = input;
        this.gameBoard = input.gameBoard;
    }

    void play(int numberOfMoves) {
        for (int i = 0; i < numberOfMoves; i++) {
            int[] rowAndColumn = input.getRowAndColumn();
            int row = rowAndColumn[0];
            int column = rowAndColumn[1];

            move(row, column);
            kick(row, column);
            draw();
            nextPlayer();
        }
        System.out.println("Spielende erreicht");
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

        kick.findAndKickDeadStones(row, column, isCurrentPlayerWhite);

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

    int getPointsBlack() {
        return pointsBlack;
    }

    void setGameBoard(Stone[][] gameBoard) {
        this.gameBoard = gameBoard;
    }
}
