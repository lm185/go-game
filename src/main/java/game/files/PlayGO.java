package game.files;

import game.files.model.Board;
import game.files.service.InputService;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class PlayGO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputService inputService = new InputService(scanner);
        Board board = new Board(inputService);

        board.draw();
        board.play();
    }
}
