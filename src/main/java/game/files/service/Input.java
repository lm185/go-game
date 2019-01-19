package game.files.service;

import game.files.model.Stone;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.lang.String;

@Data
@Service
public class Input {
    private Scanner scanner;
    private int boardHeight;
    private Stone[][] gameBoard;

    public Input() {
    }

    public Input(Scanner scanner) {
        this.scanner = scanner;
        this.boardHeight = findSize();
        this.gameBoard = new Stone[boardHeight][boardHeight];
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

    public int[] findRowAndColumn() {
        int row, column;
        do {
            row = readInput("Row?");

            if (row == -1337) {
                return new int[]{-1337, -1337};
            }

            column = readInput("Column?");

            if (column == -1337) {
                return new int[]{-1337, -1337};
            }
            if (gameBoard[row][column] != null)
                System.out.println("Invalid Move");
            else
                break;
        } while (true);

        return new int[]{row, column};
    }

    private int findSize() {
        System.out.println("Board Size?");
        int n = scanInput();
        while (n <= 0) {
            System.out.println("Please enter a positive Integer Value");
            n = scanInput();
        }
        System.out.println();
        return n;
    }
}
