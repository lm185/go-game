package game.files.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import game.files.service.OutputService;
import org.junit.Before;
import org.junit.Test;


public class GroupTest {

  private Stone[][] fakeBoard;
  private Group group;

  @Before
  public void setUp() {
    fakeBoard = new Stone[9][9];
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

    OutputService.draw(fakeBoard);

    this.group = new Group(fakeBoard);
  }


  @Test
  public void groupMarked() {
    group.markGroups();

    assertTrue(fakeBoard[1][0].isPartOfGroup);
    assertTrue(fakeBoard[1][1].isPartOfGroup);
    assertTrue(fakeBoard[1][2].isPartOfGroup);
    assertTrue(fakeBoard[1][3].isPartOfGroup);
    assertTrue(fakeBoard[1][4].isPartOfGroup);
    assertTrue(fakeBoard[1][5].isPartOfGroup);
    assertTrue(fakeBoard[1][6].isPartOfGroup);
    assertTrue(fakeBoard[1][7].isPartOfGroup);
    assertTrue(fakeBoard[1][8].isPartOfGroup);
  }

  @Test
  public void groupIds() {
    group.markGroups();
    int firstId = fakeBoard[1][0].getGroupId();
    int secondId = fakeBoard[6][0].getGroupId();

    assertEquals(firstId, fakeBoard[1][0].getGroupId());
    assertEquals(firstId, fakeBoard[1][1].getGroupId());
    assertEquals(firstId, fakeBoard[1][2].getGroupId());
    assertEquals(firstId, fakeBoard[1][3].getGroupId());
    assertEquals(firstId, fakeBoard[1][4].getGroupId());
    assertEquals(firstId, fakeBoard[1][5].getGroupId());
    assertEquals(firstId, fakeBoard[1][6].getGroupId());
    assertEquals(firstId, fakeBoard[1][7].getGroupId());
    assertEquals(firstId, fakeBoard[1][8].getGroupId());

    assertEquals(secondId, fakeBoard[6][0].getGroupId());
    assertEquals(secondId, fakeBoard[6][1].getGroupId());
    assertEquals(secondId, fakeBoard[6][2].getGroupId());
    assertEquals(secondId, fakeBoard[6][3].getGroupId());
    assertEquals(secondId, fakeBoard[6][4].getGroupId());
    assertEquals(secondId, fakeBoard[6][5].getGroupId());
    assertEquals(secondId, fakeBoard[6][6].getGroupId());
    assertEquals(secondId, fakeBoard[6][7].getGroupId());
    assertEquals(secondId, fakeBoard[6][8].getGroupId());
  }

  @Test
  public void whiteGroup() {
    group.markGroups();
    assertTrue(fakeBoard[3][0].isPartOfGroup);
    assertTrue(fakeBoard[3][1].isPartOfGroup);
    assertTrue(fakeBoard[3][2].isPartOfGroup);
    assertTrue(fakeBoard[3][3].isPartOfGroup);
    assertTrue(fakeBoard[3][4].isPartOfGroup);
    assertTrue(fakeBoard[3][5].isPartOfGroup);
    assertTrue(fakeBoard[3][6].isPartOfGroup);
    assertTrue(fakeBoard[3][7].isPartOfGroup);
    assertTrue(fakeBoard[3][8].isPartOfGroup);
    assertTrue(fakeBoard[5][0].isPartOfGroup);
    assertTrue(fakeBoard[5][1].isPartOfGroup);
    assertTrue(fakeBoard[5][2].isPartOfGroup);
    assertTrue(fakeBoard[5][3].isPartOfGroup);
    assertTrue(fakeBoard[5][4].isPartOfGroup);
    assertTrue(fakeBoard[5][5].isPartOfGroup);
    assertTrue(fakeBoard[5][6].isPartOfGroup);
    assertTrue(fakeBoard[5][7].isPartOfGroup);
    assertTrue(fakeBoard[5][8].isPartOfGroup);
    assertTrue(fakeBoard[4][0].isPartOfGroup);
    assertTrue(fakeBoard[4][8].isPartOfGroup);
  }


  @Test
  public void blackGroupAlive() {
    group.markGroups();
    assertTrue(group.isGroupAlive(fakeBoard[1][2].getGroupId()));
  }

}