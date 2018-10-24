package go.web.demo.gamefiles;

import org.junit.Test;

public class OutputTest {
    @Test
    public void zeroHeight() {
        Stone[][] fakeBoard = new Stone[0][0];
        Output.draw(fakeBoard);
    }

    @Test
    public void nineXnine() {
        Stone[][] fakeBoard = new Stone[9][9];
        Output.draw(fakeBoard);
    }
    @Test
    public void thirteenXthirteen() {
        Stone[][] fakeBoard = new Stone[13][13];
        Output.draw(fakeBoard);
    }
    @Test
    public void nineteenXnineteen() {
        Stone[][] fakeBoard = new Stone[19][19];
        Output.draw(fakeBoard);
    }
    @Test
    public void playerisWhite(){
        Output.printCurrentPlayer(true);
    }
    @Test
    public void playerisBlack(){
        Output.printCurrentPlayer(false);
    }
}