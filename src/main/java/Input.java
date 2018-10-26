import java.util.Scanner;
import java.lang.String;

class Input {
    private final Scanner scanner;
    private final int boardHeight;
    Stone[][] gameBoard;

    Input(Scanner scanner) {
        this.scanner = scanner;
        this.boardHeight = getSize();
        this.gameBoard = new Stone[boardHeight][boardHeight];
    }

    int[] getRowAndColumn() {
        return isMoveValid();
    }

    private int scanInput() {
        String input = scanner.nextLine();
        if (input.equals("pass")) { // Checks if the player actually wants to make a move or pass
            return -1337;
        }
        if (input.length() <= 0) {
            return -1;
        }
        if (input.matches("^[0-9]*$")) {
            return Integer.parseInt(input);
        }
        return -1;
    }

    private int readInput(String hint) {
        System.out.println(hint);
        int input = scanInput();
        if (input == -1337) {
            return -1337;
        }
        while (!isInBorder(input)) {
            System.out.println("Invalid Input\n" + hint);
            input = scanInput();
        }
        return input;
    }

    private boolean isInBorder(int input) {
        return input < boardHeight && input >= 0;
    }

    private int[] isMoveValid() {
        int row, column;
        do {
            row = readInput("Row?");

            column = readInput("Column?");

            if (row == -1337 || column == -1337) {
                return new int[]{-1337, -1337};
            }
            if (gameBoard[row][column] != null)
                System.out.println("Invalid Move");
            else
                break;
        } while (true);

        return new int[]{row, column};
    }

    private int getSize() {
        System.out.println("Board Size?");
        int n = scanner.nextInt();
        while (n <= 0) {
            System.out.println("Please enter a positive Integer Value");
            n = scanner.nextInt();
        }
        scanner.nextLine();
        System.out.println();
        return n;
    }
}
