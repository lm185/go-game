package game.files.model;

import org.junit.Assert;
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

        this.group = new Group(fakeBoard);
    }

    @Test(expected = NullPointerException.class)
    public void emptyFieldIsNullPointer() {
        group.findGroup(1, 2);

        Assert.assertFalse(fakeBoard[2][0].isPartOfGroup);
    }

    @Test
    public void firstBlackGroup() {
        group.findGroup(1, 2);

        Assert.assertTrue(fakeBoard[1][0].isPartOfGroup);
        Assert.assertTrue(fakeBoard[1][1].isPartOfGroup);
        Assert.assertTrue(fakeBoard[1][2].isPartOfGroup);
        Assert.assertTrue(fakeBoard[1][3].isPartOfGroup);
        Assert.assertTrue(fakeBoard[1][4].isPartOfGroup);
        Assert.assertTrue(fakeBoard[1][5].isPartOfGroup);
        Assert.assertTrue(fakeBoard[1][6].isPartOfGroup);
        Assert.assertTrue(fakeBoard[1][7].isPartOfGroup);
        Assert.assertTrue(fakeBoard[1][8].isPartOfGroup);
    }

    @Test
    public void blackGroupsAreSeparatedFirstGroupMarked() {
        group.findGroup(1, 4);
        Assert.assertTrue(fakeBoard[1][0].isPartOfGroup);
        Assert.assertTrue(fakeBoard[1][1].isPartOfGroup);
        Assert.assertTrue(fakeBoard[1][2].isPartOfGroup);
        Assert.assertTrue(fakeBoard[1][3].isPartOfGroup);
        Assert.assertTrue(fakeBoard[1][4].isPartOfGroup);
        Assert.assertTrue(fakeBoard[1][5].isPartOfGroup);
        Assert.assertTrue(fakeBoard[1][6].isPartOfGroup);
        Assert.assertTrue(fakeBoard[1][7].isPartOfGroup);
        Assert.assertTrue(fakeBoard[1][8].isPartOfGroup);

        Assert.assertFalse(fakeBoard[6][0].isPartOfGroup);
        Assert.assertFalse(fakeBoard[6][1].isPartOfGroup);
        Assert.assertFalse(fakeBoard[6][2].isPartOfGroup);
        Assert.assertFalse(fakeBoard[6][3].isPartOfGroup);
        Assert.assertFalse(fakeBoard[6][4].isPartOfGroup);
        Assert.assertFalse(fakeBoard[6][5].isPartOfGroup);
        Assert.assertFalse(fakeBoard[6][6].isPartOfGroup);
        Assert.assertFalse(fakeBoard[6][7].isPartOfGroup);
        Assert.assertFalse(fakeBoard[6][8].isPartOfGroup);
    }

    @Test
    public void blackGroupsAreSeparatedSecondGroupMarked() {
        group.findGroup(6, 4);
        Assert.assertFalse(fakeBoard[1][0].isPartOfGroup);
        Assert.assertFalse(fakeBoard[1][1].isPartOfGroup);
        Assert.assertFalse(fakeBoard[1][2].isPartOfGroup);
        Assert.assertFalse(fakeBoard[1][3].isPartOfGroup);
        Assert.assertFalse(fakeBoard[1][4].isPartOfGroup);
        Assert.assertFalse(fakeBoard[1][5].isPartOfGroup);
        Assert.assertFalse(fakeBoard[1][6].isPartOfGroup);
        Assert.assertFalse(fakeBoard[1][7].isPartOfGroup);
        Assert.assertFalse(fakeBoard[1][8].isPartOfGroup);

        Assert.assertTrue(fakeBoard[6][0].isPartOfGroup);
        Assert.assertTrue(fakeBoard[6][1].isPartOfGroup);
        Assert.assertTrue(fakeBoard[6][2].isPartOfGroup);
        Assert.assertTrue(fakeBoard[6][3].isPartOfGroup);
        Assert.assertTrue(fakeBoard[6][4].isPartOfGroup);
        Assert.assertTrue(fakeBoard[6][5].isPartOfGroup);
        Assert.assertTrue(fakeBoard[6][6].isPartOfGroup);
        Assert.assertTrue(fakeBoard[6][7].isPartOfGroup);
        Assert.assertTrue(fakeBoard[6][8].isPartOfGroup);
    }

    @Test
    public void whiteGroup() {
        group.findGroup(5, 3);
        Assert.assertTrue(fakeBoard[3][0].isPartOfGroup);
        Assert.assertTrue(fakeBoard[3][1].isPartOfGroup);
        Assert.assertTrue(fakeBoard[3][2].isPartOfGroup);
        Assert.assertTrue(fakeBoard[3][3].isPartOfGroup);
        Assert.assertTrue(fakeBoard[3][4].isPartOfGroup);
        Assert.assertTrue(fakeBoard[3][5].isPartOfGroup);
        Assert.assertTrue(fakeBoard[3][6].isPartOfGroup);
        Assert.assertTrue(fakeBoard[3][7].isPartOfGroup);
        Assert.assertTrue(fakeBoard[3][8].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][0].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][1].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][2].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][3].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][4].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][5].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][6].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][7].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][8].isPartOfGroup);
        Assert.assertTrue(fakeBoard[4][0].isPartOfGroup);
        Assert.assertTrue(fakeBoard[4][8].isPartOfGroup);
    }

    @Test
    public void resetGroupSelection() {
        group.findGroup(1, 3);

        Assert.assertFalse(fakeBoard[3][0].isPartOfGroup);
        Assert.assertFalse(fakeBoard[3][1].isPartOfGroup);
        Assert.assertFalse(fakeBoard[3][2].isPartOfGroup);
        Assert.assertFalse(fakeBoard[3][3].isPartOfGroup);
        Assert.assertFalse(fakeBoard[3][4].isPartOfGroup);
        Assert.assertFalse(fakeBoard[3][5].isPartOfGroup);
        Assert.assertFalse(fakeBoard[3][6].isPartOfGroup);
        Assert.assertFalse(fakeBoard[3][7].isPartOfGroup);
        Assert.assertFalse(fakeBoard[3][8].isPartOfGroup);
        Assert.assertFalse(fakeBoard[5][0].isPartOfGroup);
        Assert.assertFalse(fakeBoard[5][1].isPartOfGroup);
        Assert.assertFalse(fakeBoard[5][2].isPartOfGroup);
        Assert.assertFalse(fakeBoard[5][3].isPartOfGroup);
        Assert.assertFalse(fakeBoard[5][4].isPartOfGroup);
        Assert.assertFalse(fakeBoard[5][5].isPartOfGroup);
        Assert.assertFalse(fakeBoard[5][6].isPartOfGroup);
        Assert.assertFalse(fakeBoard[5][7].isPartOfGroup);
        Assert.assertFalse(fakeBoard[5][8].isPartOfGroup);
        Assert.assertFalse(fakeBoard[4][0].isPartOfGroup);
        Assert.assertFalse(fakeBoard[4][8].isPartOfGroup);

        group.resetSelection();
        group.findGroup(5, 3);

        Assert.assertTrue(fakeBoard[3][0].isPartOfGroup);
        Assert.assertTrue(fakeBoard[3][1].isPartOfGroup);
        Assert.assertTrue(fakeBoard[3][2].isPartOfGroup);
        Assert.assertTrue(fakeBoard[3][3].isPartOfGroup);
        Assert.assertTrue(fakeBoard[3][4].isPartOfGroup);
        Assert.assertTrue(fakeBoard[3][5].isPartOfGroup);
        Assert.assertTrue(fakeBoard[3][6].isPartOfGroup);
        Assert.assertTrue(fakeBoard[3][7].isPartOfGroup);
        Assert.assertTrue(fakeBoard[3][8].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][0].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][1].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][2].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][3].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][4].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][5].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][6].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][7].isPartOfGroup);
        Assert.assertTrue(fakeBoard[5][8].isPartOfGroup);
        Assert.assertTrue(fakeBoard[4][0].isPartOfGroup);
        Assert.assertTrue(fakeBoard[4][8].isPartOfGroup);
    }

    @Test
    public void blackGroupAlive() {
        group.findGroup(1, 2);
        Assert.assertTrue(group.isGroupAlive());
    }

    @Test
    public void deadGroup() {
        Stone[][] foo = new Stone[3][3];
        foo[0][0] = new Stone(true);
        foo[0][1] = new Stone(true);
        foo[1][0] = new Stone(false);
        foo[1][1] = new Stone(false);
        foo[0][2] = new Stone(false);

        Group bar = new Group(foo);
        bar.findGroup(0, 0);

        Assert.assertFalse(bar.isGroupAlive());

    }

}