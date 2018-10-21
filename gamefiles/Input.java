import java.util.Scanner;
import java.lang.String;

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
        return isMoveValid();
    }

	private int scanInput(){
		String input = scanner.nextLine();
		if(input.length() <= 0){
			return -1;
		}
		if(input.matches("^[0-9]*$")){
			return Integer.parseInt(input);
		}
		return -1;
	}

    private int readInput(String hint) {
		System.out.println(hint);
		int input = scanInput();
		while(!isInBorder(input)){
			System.out.println("Invalid Input\n" + hint);
	        input = scanInput();
		}
		return input;
    }

	private boolean isInBorder(int input) {
        if(input >= boardHeight || input < 0) {
            return false;
        }
        return true;
    }

    private int[] isMoveValid() {
		int row, column;
        do{
			row = readInput("Zeile?");

			column = readInput("Spalte?");

			if(gameBoard[row][column] != null)
				System.out.println("Invalid Move");
			else
				break;
        }while(true);

        return new int[] {row, column};
    }
}
