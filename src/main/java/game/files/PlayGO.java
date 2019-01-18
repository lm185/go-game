package game.files;

import game.files.model.Board;
import game.files.service.Input;

import java.util.Scanner;

public class PlayGO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Input input = new Input(scanner);
        Board board = new Board(input);

        board.draw();
        board.play();
    }
}
