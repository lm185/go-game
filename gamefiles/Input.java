import java.util.Scanner;

class Input {
    private final Scanner scanner;
    private final int boardHeight;
    Stone[][] gameBoard;

    Input(Scanner scanner) {
        this.scanner = scanner;
        BoardSize boardSize = new BoardSize(scanner);
        this.boardHeight = boardSize.getSize();
        this.gameBoard = new Stone[boardHeight][boardHeight];
    }
    int[] getRowAndColumn() {
        return isMoveValid(readRow(), readColumn());
    }

    private int readRow() {
        System.out.println("Zeile?");
        int row = scanner.nextInt();
        row = checkRow(row);
        return row;
    }

    private int readColumn() {
        System.out.println("Spalte?");
        int column = scanner.nextInt();
        column = checkColumn(column);
        return column;
    }

    private int[] isMoveValid(int row, int column) {
        int[] out = new int[2];
        while (gameBoard[row][column] != null)
        {
            System.out.println("Invalid Move");
            row = readRow();

            System.out.println("Invalid Move");
            column = readColumn();
        }

        out[0] = row;
        out[1] = column;
        return out;

    }

    private int checkRow(int row) {
        while (row >= boardHeight || row < 0) {
            System.out.println("Invalid Move");
            System.out.println("Zeile?");
            row = scanner.nextInt();

        }
        return row;
    }

    private int checkColumn(int column) {
        while (column >= boardHeight || column < 0) {
            System.out.println("Invalid Move");
            System.out.println("Spalte?");
            column = scanner.nextInt();

        }
        return column;
    }
}
