package game.files.service;

import game.files.model.Board;
import game.files.model.Stone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TerritoryTest {
    private Board board;

    @Before
    public void createBoard() {
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
        TerritoryService territoryService = new TerritoryService(board.getGameBoard());
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

        territoryService.markTerritory(4, 1);

        Assert.assertEquals(expectedTerritoryBorder, territoryService.getIsTerritoryBorder());
    }

    @Test
    public void blackBorder() {
        TerritoryService territoryService = new TerritoryService(board.getGameBoard());
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

        territoryService.markTerritory(0, 0);

        Assert.assertEquals(expectedTerritoryBorder, territoryService.getIsTerritoryBorder());
    }

    @Test
    public void whiteTerritory() {
        boolean[][] expectedTerritory = new boolean[9][9];
        boolean[][] countedFields = new boolean[9][9];
        TerritoryService territoryService = new TerritoryService(board.getGameBoard());
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

        territoryService.markTerritory(4, 1);

        Assert.assertEquals(expectedTerritory, territoryService.getIsPartOfMarkedTerritory());
        Assert.assertEquals(countedFields, territoryService.getIsFieldCounted());
    }

    @Test
    public void blackTerritory() {
        boolean[][] expectedTerritory = new boolean[9][9];
        boolean[][] countedFields = new boolean[9][9];
        TerritoryService territoryService = new TerritoryService(board.getGameBoard());
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

        territoryService.markTerritory(7, 5);

        Assert.assertEquals(expectedTerritory, territoryService.getIsPartOfMarkedTerritory());
        Assert.assertEquals(countedFields, territoryService.getIsFieldCounted());
    }

    @Test
    public void zoneIsWhite() {
        TerritoryService territoryService = new TerritoryService(board.getGameBoard());

        territoryService.markTerritory(4, 2);

        Assert.assertTrue(territoryService.isTerritoryWhite());
    }

    @Test
    public void zoneIsBlack() {
        TerritoryService territoryService = new TerritoryService(board.getGameBoard());

        territoryService.markTerritory(0, 0);

        Assert.assertTrue(territoryService.isTerritoryBlack());
    }

    @Test
    public void nobodyOwnsZone() {
        TerritoryService territoryService = new TerritoryService(board.getGameBoard());

        territoryService.markTerritory(2, 4);

        Assert.assertFalse(territoryService.isTerritoryBlack());
        Assert.assertFalse(territoryService.isTerritoryWhite());
    }

    @Test
    public void whitePoints() {
        TerritoryService territoryService = new TerritoryService(board.getGameBoard());
        Assert.assertEquals(7, territoryService.getPointsWhite());
    }

    @Test
    public void blackPoints() {
        TerritoryService territoryService = new TerritoryService(board.getGameBoard());
        Assert.assertEquals(27, territoryService.getPointsBlack());
    }

    @Test
    public void blackOwnsEverything() {
        Stone[][] foo = new Stone[9][9];
        foo[3][4] = new Stone(false);
        TerritoryService territoryService = new TerritoryService(foo);

        Assert.assertEquals(80, territoryService.getPointsBlack());
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

        TerritoryService territoryService = new TerritoryService(fakeBoard);

        Assert.assertEquals(2, territoryService.getPointsBlack());
    }

    @Test
    public void noStonePlaced() {
        Stone[][] fakeBoard = new Stone[9][9];
        TerritoryService territoryService = new TerritoryService(fakeBoard);
        Assert.assertEquals(0, territoryService.getPointsBlack());
        Assert.assertEquals(0, territoryService.getPointsWhite());
    }
}
