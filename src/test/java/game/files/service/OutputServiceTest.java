package game.files.service;

import game.files.model.Stone;
import org.junit.Test;

public class OutputServiceTest {
    @Test
    public void zeroHeight() {
        Stone[][] fakeBoard = new Stone[0][0];
        OutputService.draw(fakeBoard);
    }

    @Test
    public void nineXnine() {
        Stone[][] fakeBoard = new Stone[9][9];
        OutputService.draw(fakeBoard);
    }

    @Test
    public void thirteenXthirteen() {
        Stone[][] fakeBoard = new Stone[13][13];
        OutputService.draw(fakeBoard);
    }

    @Test
    public void nineteenXnineteen() {
        Stone[][] fakeBoard = new Stone[19][19];
        OutputService.draw(fakeBoard);
    }

    @Test
    public void playerisWhite() {
        OutputService.printCurrentPlayer(true);
    }

    @Test
    public void playerisBlack() {
        OutputService.printCurrentPlayer(false);
    }
}