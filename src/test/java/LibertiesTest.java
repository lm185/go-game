package go.web.demo.gamefiles;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LibertiesTest {
    private Liberties liberties;

    @Before
    public void setUp() {
        Stone[][] fakeBoard = new Stone[9][9];
        fakeBoard[0][0] = new Stone(true); // expected 2 liberties

        fakeBoard[2][2] = new Stone(true); // excepted 4 liberties
        fakeBoard[2][1] = new Stone(true);

        fakeBoard[4][0] = new Stone(false);
        fakeBoard[5][0] = new Stone(true); // expected 1 liberty
        fakeBoard[5][1] = new Stone(false);

        fakeBoard[8][0] = new Stone(true); // expected 0 liberties
        fakeBoard[7][0] = new Stone(false);
        fakeBoard[8][1] = new Stone(false);

        fakeBoard[3][5] = new Stone(true);
        fakeBoard[3][6] = new Stone(true);
        fakeBoard[3][7] = new Stone(true);
        fakeBoard[2][6] = new Stone(true);


        this.liberties = new Liberties(fakeBoard);
    }

    @Test
    public void edgeStoneHas2Liberties() {
        Assert.assertEquals(2, liberties.getLibertiesForStone(0, 0));
    }

    @Test
    public void surroundedStoneHas1Liberty() {
        Assert.assertEquals(1, liberties.getLibertiesForStone(5, 0));
    }

    @Test
    public void deadStoneHasNoLiberties() {
        Assert.assertEquals(0, liberties.getLibertiesForStone(8, 0));
    }

    @Test
    public void stoneWithOneNeighbourHas4Liberties() {
        Assert.assertEquals(4, liberties.getLibertiesForStone(2, 2));
    }

    @Test
    public void edgeStoneHasNoConnections() {
        Assert.assertEquals(0, liberties.getConnectionsForStone(0, 0));
    }

    @Test
    public void stoneWithOneNeighbourHas1Connection() {
        Assert.assertEquals(1, liberties.getConnectionsForStone(2, 2));
    }

    @Test
    public void stoneHas3Connections() {
        Assert.assertEquals(3, liberties.getConnectionsForStone(3, 6));
    }
}