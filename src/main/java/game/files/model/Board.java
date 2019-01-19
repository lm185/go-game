package game.files.model;

import game.files.service.InputService;
import game.files.service.KickService;
import game.files.service.OutputService;
import game.files.service.TerritoryService;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Board {
    private Stone[][] gameBoard;
    private boolean isCurrentPlayerWhite = false;
    private int pointsWhite = 0;
    private int pointsBlack = 0;
    private InputService inputService;
    private int passes = 0;

    public Board() {
    }

    public Board(int boardHeight) { //For Testing Purpose
        this.gameBoard = new Stone[boardHeight][boardHeight];
    }

    public Board(InputService inputService) {
        this.inputService = inputService;
        this.gameBoard = inputService.getGameBoard();
    }

    public void play() {
        System.out.println("Enter column and row to place a stone");
        System.out.println("Entering 'pass' will skip your turn");
        System.out.println("If both players pass the game ends");
        System.out.println();

        while (true) {
            int[] rowAndColumn = inputService.findRowAndColumn();
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

    public void testPlay(int row, int column, boolean isCurrentPlayerWhite) {
        this.isCurrentPlayerWhite = isCurrentPlayerWhite;
        move(row, column);
        kick(row, column);
        draw();
        nextPlayer();
    }

    private void move(int row, int column) {
        OutputService.printCurrentPlayer(isCurrentPlayerWhite);
        setStone(row, column);
    }

    private void kick(int row, int column) {
        KickService kickService = new KickService(gameBoard);

        kickService.findAndKickDeadStones(row, column);

        pointsWhite += kickService.getPointsWhite();
        pointsBlack += kickService.getPointsBlack();
    }

    public void addTerritoryPoints() {
        TerritoryService territoryService = new TerritoryService(gameBoard);
        this.pointsWhite += territoryService.getPointsWhite();
        this.pointsBlack += territoryService.getPointsBlack();
    }

    public void draw() {
        OutputService.draw(gameBoard);
    }

    private void nextPlayer() {
        isCurrentPlayerWhite = !isCurrentPlayerWhite;
    }

    private void setStone(int row, int column) {
        gameBoard[row][column] = new Stone(isCurrentPlayerWhite);
    }

    private boolean doesPlayerPass(int[] rowAndColumn) {
        return rowAndColumn[0] == -1337 || rowAndColumn[1] == -1337;
    }
}
