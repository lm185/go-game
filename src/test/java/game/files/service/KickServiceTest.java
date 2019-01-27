package game.files.service;

import game.files.model.Stone;
import org.junit.Assert;
import org.junit.Test;

public class KickServiceTest {

  @Test
  public void edgeStoneNoLiberties() {
    Stone[][] fakeBoard = new Stone[9][9];
    fakeBoard[0][0] = new Stone(true);
    fakeBoard[1][0] = new Stone(false);
    fakeBoard[0][1] = new Stone(false);

    KickService kickService = new KickService(fakeBoard);
    kickService.markGroups();
    kickService.kickOtherDeadGroups();
    Assert.assertNull(fakeBoard[0][0]);
    Assert.assertNotNull(fakeBoard[1][0]);
    Assert.assertNotNull(fakeBoard[0][1]);
    Assert.assertEquals(1, kickService.getPointsBlack());
    Assert.assertEquals(0, kickService.getPointsWhite());

  }

  @Test
  public void edgeStoneNotDead() {
    Stone[][] fakeBoard = new Stone[9][9];
    fakeBoard[0][0] = new Stone(true);
    fakeBoard[1][0] = new Stone(false);

    KickService kickService = new KickService(fakeBoard);
    kickService.kickOtherDeadGroups();

    Assert.assertNotNull(fakeBoard[0][0]);
    Assert.assertEquals(0, kickService.getPointsBlack());
    Assert.assertEquals(0, kickService.getPointsWhite());
  }

  @Test
  public void deadGroupIsKicked() {
    Stone[][] fakeBoard = new Stone[9][9];
    fakeBoard[4][4] = new Stone(true);
    fakeBoard[4][5] = new Stone(true);
    fakeBoard[3][4] = new Stone(false);
    fakeBoard[3][5] = new Stone(false);
    fakeBoard[5][4] = new Stone(false);
    fakeBoard[5][5] = new Stone(false);
    fakeBoard[4][3] = new Stone(false);
    fakeBoard[4][6] = new Stone(false);

    KickService kickService = new KickService(fakeBoard);
    kickService.markGroups();
    kickService.kickOtherDeadGroups();

    Assert.assertNull(fakeBoard[4][4]);
    Assert.assertNull(fakeBoard[4][5]);
    Assert.assertEquals(2, kickService.getPointsBlack());
    Assert.assertEquals(0, kickService.getPointsWhite());
  }

  @Test
  public void aliveGroupIsNotKicked() {
    Stone[][] fakeBoard = new Stone[9][9];
    fakeBoard[4][4] = new Stone(true);
    fakeBoard[4][5] = new Stone(true);
    fakeBoard[3][5] = new Stone(false);
    fakeBoard[5][4] = new Stone(false);
    fakeBoard[5][5] = new Stone(false);
    fakeBoard[4][3] = new Stone(false);
    fakeBoard[4][6] = new Stone(false);
    KickService kickService = new KickService(fakeBoard);

    kickService.kickAllDeadGroups(3, 5);

    Assert.assertNotNull(fakeBoard[4][4]);
    Assert.assertNotNull(fakeBoard[4][5]);
    Assert.assertEquals(0, kickService.getPointsBlack());
    Assert.assertEquals(0, kickService.getPointsWhite());
  }

  @Test
  public void bothStonesDeadLastMoveKicksOpponent() {
    Stone[][] fakeBoard = new Stone[9][9];
    fakeBoard[0][0] = new Stone(false);
    fakeBoard[0][1] = new Stone(true);
    fakeBoard[0][2] = new Stone(false);
    fakeBoard[1][0] = new Stone(true);
    fakeBoard[1][1] = new Stone(false);
    KickService kickService = new KickService(fakeBoard);

    kickService.kickAllDeadGroups(0, 0);

    Assert.assertEquals(1, kickService.getPointsBlack());
    Assert.assertEquals(0, kickService.getPointsWhite());
    Assert.assertNotNull(fakeBoard[0][0]);
    Assert.assertNull(fakeBoard[0][1]);
  }

  @Test
  public void bothRowsDeadLastMoveKicksOpponent() {
    Stone[][] fakeBoard = new Stone[9][9];
    fakeBoard[0][0] = new Stone(true);
    fakeBoard[0][1] = new Stone(true);
    fakeBoard[0][2] = new Stone(true);
    fakeBoard[0][3] = new Stone(true);
    fakeBoard[0][4] = new Stone(true);
    fakeBoard[0][5] = new Stone(true);
    fakeBoard[0][6] = new Stone(true);
    fakeBoard[0][7] = new Stone(true);
    fakeBoard[0][8] = new Stone(true);
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
    KickService kickService = new KickService(fakeBoard);

    kickService.kickAllDeadGroups(0, 5);

    Assert.assertEquals(0, kickService.getPointsBlack());
    Assert.assertEquals(9, kickService.getPointsWhite());
  }

  @Test
  public void bothRowsDeadLastMoveKicksOpponentOneFunction() {
    Stone[][] fakeBoard = new Stone[9][9];
    fakeBoard[0][0] = new Stone(true);
    fakeBoard[0][1] = new Stone(true);
    fakeBoard[0][2] = new Stone(true);
    fakeBoard[0][3] = new Stone(true);
    fakeBoard[0][4] = new Stone(true);
    fakeBoard[0][5] = new Stone(true);
    fakeBoard[0][6] = new Stone(true);
    fakeBoard[0][7] = new Stone(true);
    fakeBoard[0][8] = new Stone(true);
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
    KickService kickService = new KickService(fakeBoard);

    kickService.kickAllDeadGroups(0, 5);

    Assert.assertEquals(0, kickService.getPointsBlack());
    Assert.assertEquals(9, kickService.getPointsWhite());
  }

  @Test
  public void multipleGroupsKicked() {
    Stone[][] fakeBoard = new Stone[9][9];
    fakeBoard[0][0] = new Stone(true);
    fakeBoard[0][1] = new Stone(true);
    fakeBoard[0][2] = new Stone(true);
    fakeBoard[0][3] = new Stone(true);
    fakeBoard[0][4] = new Stone(true);
    fakeBoard[0][5] = new Stone(true);
    fakeBoard[0][6] = new Stone(true);
    fakeBoard[0][7] = new Stone(true);
    fakeBoard[0][8] = new Stone(true);
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
    KickService kickService = new KickService(fakeBoard);

    kickService.kickAllDeadGroups(0, 2);

    Assert.assertEquals(9, kickService.getPointsWhite());
    Assert.assertEquals(2, kickService.getPointsBlack());
  }
}