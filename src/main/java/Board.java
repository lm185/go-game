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
        for (int i = 0; i < 99999999; i++) {
            if(i == 0){
                System.out.println("Enter column and row to place a stone");
                System.out.println("Entering 'pass' will skip your turn");
                System.out.println("If both players pass the game ends");
                System.out.println();
            }
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
        System.out.println("Spielende erreicht");
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

        kick.findAndKickDeadStones(row, column, isCurrentPlayerWhite);

        pointsWhite += kick.getPointsWhite();
        pointsBlack += kick.getPointsBlack();
    }

    private boolean doesPlayerPass(int[] rowAndColumn) {
        return rowAndColumn[0] == -1337 || rowAndColumn[1] == -1337;
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
