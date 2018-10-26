import java.util.Scanner;

public class PlayGO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Input input = new Input(scanner);
        Board board = new Board(input);

        board.draw();
        board.play();
        board.addTerritoryPoints();
    }
}
