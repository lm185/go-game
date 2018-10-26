import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TerritoryTest {
    private Board board;
    @Before
    public void createBoard(){
        this.board = new Board(9);
        Stone[][] fakeBoard = new Stone[9][9];
        fakeBoard[1][0] = new Stone(false);
        fakeBoard[1][1] = new Stone(false);
        fakeBoard[1][2] = new Stone(false);
        fakeBoard[1][3] = new Stone(false);
        fakeBoard[1][4] = new Stone(false);
        fakeBoard[1][5] = new Stone(false);
        fakeBoard[1][6] = new Stone(false);
        fakeBoard[1][7] = new Stone(false);
        fakeBoard[1][8] = new Stone(false);
        fakeBoard[3][0] = new Stone(true);
        fakeBoard[3][1] = new Stone(true);
        fakeBoard[3][2] = new Stone(true);
        fakeBoard[3][3] = new Stone(true);
        fakeBoard[3][4] = new Stone(true);
        fakeBoard[3][5] = new Stone(true);
        fakeBoard[3][6] = new Stone(true);
        fakeBoard[3][7] = new Stone(true);
        fakeBoard[3][8] = new Stone(true);
        fakeBoard[4][0] = new Stone(true);
        fakeBoard[4][8] = new Stone(true);
        fakeBoard[5][0] = new Stone(true);
        fakeBoard[5][1] = new Stone(true);
        fakeBoard[5][2] = new Stone(true);
        fakeBoard[5][3] = new Stone(true);
        fakeBoard[5][4] = new Stone(true);
        fakeBoard[5][5] = new Stone(true);
        fakeBoard[5][6] = new Stone(true);
        fakeBoard[5][7] = new Stone(true);
        fakeBoard[5][8] = new Stone(true);
        fakeBoard[6][0] = new Stone(false);
        fakeBoard[6][1] = new Stone(false);
        fakeBoard[6][2] = new Stone(false);
        fakeBoard[6][3] = new Stone(false);
        fakeBoard[6][4] = new Stone(false);
        fakeBoard[6][5] = new Stone(false);
        fakeBoard[6][6] = new Stone(false);
        fakeBoard[6][7] = new Stone(false);
        fakeBoard[6][8] = new Stone(false);

        this.board.setGameBoard(fakeBoard);
    }
    @Test
    public void whiteBorder() {
        Territory territory = new Territory(board.getGameBoard());
        boolean[][] expectedTerritoryBorder = new boolean[9][9];
        expectedTerritoryBorder[3][1] = true;
        expectedTerritoryBorder[3][2] = true;
        expectedTerritoryBorder[3][3] = true;
        expectedTerritoryBorder[3][4] = true;
        expectedTerritoryBorder[3][5] = true;
        expectedTerritoryBorder[3][6] = true;
        expectedTerritoryBorder[3][7] = true;
        expectedTerritoryBorder[4][0] = true;
        expectedTerritoryBorder[4][8] = true;
        expectedTerritoryBorder[5][1] = true;
        expectedTerritoryBorder[5][2] = true;
        expectedTerritoryBorder[5][3] = true;
        expectedTerritoryBorder[5][4] = true;
        expectedTerritoryBorder[5][5] = true;
        expectedTerritoryBorder[5][6] = true;
        expectedTerritoryBorder[5][7] = true;

        territory.markTerritory(4,1);

        Assert.assertEquals(expectedTerritoryBorder, territory.getIsTerritoryBorder());
    }
    @Test
    public void blackBorder() {
        Territory territory = new Territory(board.getGameBoard());
        boolean[][] expectedTerritoryBorder = new boolean[9][9];
        expectedTerritoryBorder[1][0] = true;
        expectedTerritoryBorder[1][1] = true;
        expectedTerritoryBorder[1][2] = true;
        expectedTerritoryBorder[1][3] = true;
        expectedTerritoryBorder[1][4] = true;
        expectedTerritoryBorder[1][5] = true;
        expectedTerritoryBorder[1][6] = true;
        expectedTerritoryBorder[1][7] = true;
        expectedTerritoryBorder[1][8] = true;

        territory.markTerritory(0,0);

        Assert.assertEquals(expectedTerritoryBorder, territory.getIsTerritoryBorder());
    }

    @Test
    public void whiteTerritory() {
        boolean[][] expectedTerritory = new boolean[9][9];
        boolean[][] countedFields = new boolean[9][9];
        Territory territory = new Territory(board.getGameBoard());
        expectedTerritory[4][1] = true;
        expectedTerritory[4][2] = true;
        expectedTerritory[4][3] = true;
        expectedTerritory[4][4] = true;
        expectedTerritory[4][5] = true;
        expectedTerritory[4][6] = true;
        expectedTerritory[4][7] = true;
        countedFields[4][1] = true;
        countedFields[4][2] = true;
        countedFields[4][3] = true;
        countedFields[4][4] = true;
        countedFields[4][5] = true;
        countedFields[4][6] = true;
        countedFields[4][7] = true;

        territory.markTerritory(4,1);

        Assert.assertEquals(expectedTerritory, territory.getIsPartOfMarkedTerritory());
        Assert.assertEquals(countedFields, territory.getIsFieldCounted());
    }
    @Test
    public void blackTerritory() {
        boolean[][] expectedTerritory = new boolean[9][9];
        boolean[][] countedFields = new boolean[9][9];
        Territory territory = new Territory(board.getGameBoard());
        expectedTerritory[7][0] = true;
        expectedTerritory[7][1] = true;
        expectedTerritory[7][2] = true;
        expectedTerritory[7][3] = true;
        expectedTerritory[7][4] = true;
        expectedTerritory[7][5] = true;
        expectedTerritory[7][6] = true;
        expectedTerritory[7][7] = true;
        expectedTerritory[7][8] = true;
        expectedTerritory[8][0] = true;
        expectedTerritory[8][1] = true;
        expectedTerritory[8][2] = true;
        expectedTerritory[8][3] = true;
        expectedTerritory[8][4] = true;
        expectedTerritory[8][5] = true;
        expectedTerritory[8][6] = true;
        expectedTerritory[8][7] = true;
        expectedTerritory[8][8] = true;
        countedFields[7][0] = true;
        countedFields[7][1] = true;
        countedFields[7][2] = true;
        countedFields[7][3] = true;
        countedFields[7][4] = true;
        countedFields[7][5] = true;
        countedFields[7][6] = true;
        countedFields[7][7] = true;
        countedFields[7][8] = true;
        countedFields[8][0] = true;
        countedFields[8][1] = true;
        countedFields[8][2] = true;
        countedFields[8][3] = true;
        countedFields[8][4] = true;
        countedFields[8][5] = true;
        countedFields[8][6] = true;
        countedFields[8][7] = true;
        countedFields[8][8] = true;

        territory.markTerritory(7,5);

        Assert.assertEquals(expectedTerritory, territory.getIsPartOfMarkedTerritory());
        Assert.assertEquals(countedFields, territory.getIsFieldCounted());
    }
    @Test
    public void zoneIsWhite() {
        Territory territory = new Territory(board.getGameBoard());

        territory.markTerritory(4,2);

        Assert.assertTrue(territory.isTerritoryWhite());
    }

    @Test
    public void zoneIsBlack(){
        Territory territory = new Territory(board.getGameBoard());

        territory.markTerritory(0,0);

        Assert.assertTrue(territory.isTerritoryBlack());
    }

    @Test
    public void nobodyOwnsZone(){
        Territory territory = new Territory(board.getGameBoard());

        territory.markTerritory(2,4);

        Assert.assertFalse(territory.isTerritoryBlack());
        Assert.assertFalse(territory.isTerritoryWhite());
    }

    @Test
    public void whitePoints() {
        Territory territory = new Territory(board.getGameBoard());
        Assert.assertEquals(7, territory.getPointsWhite());
    }

    @Test
    public void blackPoints() {
        Territory territory = new Territory(board.getGameBoard());
        Assert.assertEquals(27, territory.getPointsBlack());
    }

    @Test
    public void blackOwnsEverything(){
        Stone[][] foo = new Stone[9][9];
        foo[3][4] = new Stone(false);
        Territory territory = new Territory(foo);

        Assert.assertEquals(80,territory.getPointsBlack());
    }

    @Test
    public void twoFiledTerritory() {
        Stone[][] fakeBoard = new Stone[9][9];
        fakeBoard[0][0] = new Stone(true);
        fakeBoard[3][4] = new Stone(false);
        fakeBoard[3][5] = new Stone(false);
        fakeBoard[5][4] = new Stone(false);
        fakeBoard[5][5] = new Stone(false);
        fakeBoard[4][3] = new Stone(false);
        fakeBoard[4][6] = new Stone(false);

        Territory territory = new Territory(fakeBoard);

        Assert.assertEquals(2, territory.getPointsBlack());
    }
}
