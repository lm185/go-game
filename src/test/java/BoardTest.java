import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {
    private Board board;

    @Before
    public void setUp(){
        this.board = new Board(9);
    }

    @Test
    public void withInput(){
       //TODO
    }

    @Test
    public void kickPoints() {
        Stone[][] fakeBoard = new Stone[9][9];
        fakeBoard[0][0] = new Stone(true);
        fakeBoard[0][1] = new Stone(true);
        fakeBoard[0][2] = new Stone(true);
        fakeBoard[0][3] = new Stone(true);
        fakeBoard[0][4] = new Stone(true);
        fakeBoard[0][5] = new Stone(true);
        fakeBoard[0][6] = new Stone(true);
        fakeBoard[0][7] = new Stone(true);
        fakeBoard[1][0] = new Stone(false);
        fakeBoard[1][1] = new Stone(false);
        fakeBoard[1][2] = new Stone(false);
        fakeBoard[1][3] = new Stone(false);
        fakeBoard[1][4] = new Stone(false);
        fakeBoard[1][5] = new Stone(false);
        fakeBoard[1][6] = new Stone(false);
        fakeBoard[1][7] = new Stone(false);
        fakeBoard[1][8] = new Stone(false);
        fakeBoard[2][0] = new Stone(true);
        fakeBoard[2][1] = new Stone(true);
        fakeBoard[2][2] = new Stone(true);
        fakeBoard[2][3] = new Stone(true);
        fakeBoard[2][4] = new Stone(true);
        fakeBoard[2][5] = new Stone(true);
        fakeBoard[2][6] = new Stone(true);
        fakeBoard[2][7] = new Stone(true);
        fakeBoard[2][8] = new Stone(true);

        fakeBoard[4][4] = new Stone(true);
        fakeBoard[4][5] = new Stone(true);
        fakeBoard[3][4] = new Stone(false);
        fakeBoard[3][5] = new Stone(false);
        fakeBoard[5][4] = new Stone(false);
        fakeBoard[5][5] = new Stone(false);
        fakeBoard[4][3] = new Stone(false);
        fakeBoard[4][6] = new Stone(false);

        board.setGameBoard(fakeBoard);
        board.draw();
        board.testPlay(0,8,true);
        Assert.assertEquals(2,board.getPointsBlack());
    }

    @Test
    public void territoryPoints() {
        Stone[][] fakeBoard = new Stone[9][9];
        fakeBoard[0][0] = new Stone(true);
        fakeBoard[0][1] = new Stone(true);
        fakeBoard[0][2] = new Stone(true);
        fakeBoard[0][3] = new Stone(true);
        fakeBoard[0][4] = new Stone(true);
        fakeBoard[0][5] = new Stone(true);
        fakeBoard[0][6] = new Stone(true);
        fakeBoard[0][7] = new Stone(true);
        fakeBoard[1][0] = new Stone(false);
        fakeBoard[1][1] = new Stone(false);
        fakeBoard[1][2] = new Stone(false);
        fakeBoard[1][3] = new Stone(false);
        fakeBoard[1][4] = new Stone(false);
        fakeBoard[1][5] = new Stone(false);
        fakeBoard[1][6] = new Stone(false);
        fakeBoard[1][7] = new Stone(false);
        fakeBoard[1][8] = new Stone(false);
        fakeBoard[2][0] = new Stone(true);
        fakeBoard[2][1] = new Stone(true);
        fakeBoard[2][2] = new Stone(true);
        fakeBoard[2][3] = new Stone(true);
        fakeBoard[2][4] = new Stone(true);
        fakeBoard[2][5] = new Stone(true);
        fakeBoard[2][6] = new Stone(true);
        fakeBoard[2][7] = new Stone(true);
        fakeBoard[2][8] = new Stone(true);

        fakeBoard[4][4] = new Stone(true);
        fakeBoard[4][5] = new Stone(true);
        fakeBoard[3][4] = new Stone(false);
        fakeBoard[3][5] = new Stone(false);
        fakeBoard[5][4] = new Stone(false);
        fakeBoard[5][5] = new Stone(false);
        fakeBoard[4][3] = new Stone(false);
        fakeBoard[4][6] = new Stone(false);

        board.setGameBoard(fakeBoard);
        board.draw();
        board.testPlay(0,8,true);
        Assert.assertEquals(2,board.getPointsBlack());

        board.addTerritoryPoints();

        Assert.assertEquals(18,board.getPointsWhite());
        Assert.assertEquals(4,board.getPointsBlack());
    }
    @Test
    public void twoFieldTerritory(){
        Stone[][] fakeBoard = new Stone[9][9];
        fakeBoard[4][4] = new Stone(true);
        fakeBoard[4][5] = new Stone(true);
        fakeBoard[3][4] = new Stone(false);
        fakeBoard[3][5] = new Stone(false);
        fakeBoard[5][4] = new Stone(false);
        fakeBoard[5][5] = new Stone(false);
        fakeBoard[4][3] = new Stone(false);
        fakeBoard[4][6] = new Stone(false);

    }
}