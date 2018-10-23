package com.company;

import java.util.Scanner;

class BoardSize {
    private Scanner scanner;

    BoardSize(Scanner scanner) {
        this.scanner = scanner;
    }

    int getSize() {
        boolean sizeGotten = false;
        do {
            try {
                this.scanner = new Scanner(System.in);
                System.out.println("Spielfeldgröße?");
                int n = scanner.nextInt();
                while (n <= 0) {
                    System.out.println("Ungültige Größe");
                    n = scanner.nextInt();
                }
                scanner.nextLine();
                System.out.println();
                sizeGotten = true;
                return n;
            } catch (Exception e) {
                System.out.println("Bitte versuche es erneut.");
            }
        } while (!sizeGotten);

        //Dies wird niemals vorkommen.
        return 0;
    }
}