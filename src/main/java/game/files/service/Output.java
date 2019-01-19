package game.files.service;

import game.files.model.Stone;
import org.springframework.stereotype.Service;

@Service
public class Output {
    public static void draw(Stone[][] brett) {
        int boardHeight = brett.length;
        System.out.print(" ");
        for (int i = 0; i < boardHeight; i++) { //Nummerierung der Spalten
            System.out.print(" " + i);
        }
        System.out.println();
        System.out.print(" ");
        for (int i = 0; i < boardHeight; i++) {
            System.out.print(" _");
        }
        System.out.println();
        for (int i = 0; i < boardHeight; i++) {
            System.out.print(i); //Nummerierung der Zeilen
            for (int j = 0; j < boardHeight; j++) {
                if (brett[i][j] == null) System.out.print("| ");
                if (brett[i][j] != null && !brett[i][j].isStoneWhite()) System.out.print("|o"); // o für Schwarz
                if (brett[i][j] != null && brett[i][j].isStoneWhite()) System.out.print("|*"); // * für Weiß
                if (j + 1 == boardHeight) System.out.print("|");
            }
            System.out.println();
        }
        System.out.print(" ");
        for (int i = 0; i < boardHeight; i++) {
            System.out.print(" ¯");
        }
        System.out.println();
    }

    public static void printCurrentPlayer(boolean isWhite) {
        if (isWhite) System.out.println("Weiß ist am Zug");
        else System.out.println("Schwarz ist am Zug");
    }
}
