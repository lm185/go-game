package game.files.model;

import game.files.service.LibertiesService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LibertiesServiceTest {

  private LibertiesService libertiesService;

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

    this.libertiesService = new LibertiesService(fakeBoard);
  }

  @Test
  public void edgeStoneHas2Liberties() {
    Assert.assertEquals(2, libertiesService.findLibertiesForStone(0, 0));
  }

  @Test
  public void surroundedStoneHas1Liberty() {
    Assert.assertEquals(1, libertiesService.findLibertiesForStone(5, 0));
  }

  @Test
  public void deadStoneHasNoLiberties() {
    Assert.assertEquals(0, libertiesService.findLibertiesForStone(8, 0));
  }

  @Test
  public void stoneWithOneNeighbourHas4Liberties() {
    Assert.assertEquals(4, libertiesService.findLibertiesForStone(2, 2));
  }

  @Test
  public void edgeStoneHasNoConnections() {
    Assert.assertEquals(0, libertiesService.findConnectionsForStone(0, 0));
  }

  @Test
  public void stoneWithOneNeighbourHas1Connection() {
    Assert.assertEquals(1, libertiesService.findConnectionsForStone(2, 2));
  }

  @Test
  public void stoneHas3Connections() {
    Assert.assertEquals(3, libertiesService.findConnectionsForStone(3, 6));
  }
}