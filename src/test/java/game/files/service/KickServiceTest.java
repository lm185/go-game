package game.files.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import game.files.model.Stone;
import org.junit.Test;

public class KickServiceTest {

  @Test
  public void edgeStoneDead() {
    Stone[][] fakeBoard = new Stone[9][9];
    fakeBoard[0][0] = new Stone(true);
    fakeBoard[1][0] = new Stone(false);
    fakeBoard[0][1] = new Stone(false);
    KickService kickService = new KickService(fakeBoard);

    kickService.markGroups();
    kickService.kickDeadGroupsByOrder();

    assertNull(fakeBoard[0][0]);
    assertNotNull(fakeBoard[1][0]);
    assertNotNull(fakeBoard[0][1]);
    assertEquals(1, kickService.getPointsBlack());
    assertEquals(0, kickService.getPointsWhite());

  }

  @Test
  public void edgeStoneNotDead() {
    Stone[][] fakeBoard = new Stone[9][9];
    fakeBoard[0][0] = new Stone(true);
    fakeBoard[1][0] = new Stone(false);

    KickService kickService = new KickService(fakeBoard);
    kickService.markGroups();
    kickService.kickDeadGroupsByOrder();

    assertNotNull(fakeBoard[0][0]);
    assertEquals(0, kickService.getPointsBlack());
    assertEquals(0, kickService.getPointsWhite());
  }

  @Test
  public void deadGroup() {
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
    kickService.kickDeadGroupsByOrder();

    assertNull(fakeBoard[4][4]);
    assertNull(fakeBoard[4][5]);
    assertEquals(2, kickService.getPointsBlack());
    assertEquals(0, kickService.getPointsWhite());
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

    assertNotNull(fakeBoard[4][4]);
    assertNotNull(fakeBoard[4][5]);
    assertEquals(0, kickService.getPointsBlack());
    assertEquals(0, kickService.getPointsWhite());
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

    assertEquals(1, kickService.getPointsBlack());
    assertEquals(0, kickService.getPointsWhite());
    assertNotNull(fakeBoard[0][0]);
    assertNull(fakeBoard[0][1]);
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

    assertEquals(0, kickService.getPointsBlack());
    assertEquals(9, kickService.getPointsWhite());
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

    assertEquals(0, kickService.getPointsBlack());
    assertEquals(9, kickService.getPointsWhite());
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

    assertEquals(9, kickService.getPointsWhite());
    assertEquals(2, kickService.getPointsBlack());
  }
}