package game.files.model;

import game.files.service.OutputService;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class LinkedGroupTest {

    @Test
    public void twoGroupsDiagonalConnected_pos1() {
        Stone[][] fakeBoard = new Stone[9][9];
        fakeBoard[0][0] = new Stone(true);
        fakeBoard[0][0].setGroupId(1);
        fakeBoard[1][1] = new Stone(true);
        fakeBoard[1][1].setGroupId(2);
        LinkedGroup linkedGroup = new LinkedGroup(1, true, fakeBoard);
        assertThat(linkedGroup.getIds(), hasItems(1, 2));
    }

    @Test
    public void twoGroupsDiagonalConnected_pos2() {
        Stone[][] fakeBoard = new Stone[9][9];
        fakeBoard[3][3] = new Stone(true);
        fakeBoard[3][3].setGroupId(1);
        fakeBoard[4][2] = new Stone(true);
        fakeBoard[4][2].setGroupId(2);
        LinkedGroup linkedGroup = new LinkedGroup(1, true, fakeBoard);
        assertThat(linkedGroup.getIds(), hasItems(1, 2));
    }

    @Test
    public void threeGroupsDiagonalConnected() {
        Stone[][] fakeBoard = new Stone[9][9];
        fakeBoard[0][0] = new Stone(true);
        fakeBoard[0][0].setGroupId(1);
        fakeBoard[1][1] = new Stone(true);
        fakeBoard[1][1].setGroupId(2);
        fakeBoard[2][2] = new Stone(true);
        fakeBoard[2][2].setGroupId(3);

        LinkedGroup linkedGroup = new LinkedGroup(1, true, fakeBoard);
        assertThat(linkedGroup.getIds(), hasItems(1, 2, 3));
    }

    @Test
    public void groupsNotDiagonalConnected() {
        Stone[][] fakeBoard = new Stone[9][9];
        fakeBoard[0][0] = new Stone(true);
        fakeBoard[0][0].setGroupId(1);
        fakeBoard[1][0] = new Stone(false);
        fakeBoard[1][0].setGroupId(7);
        fakeBoard[1][1] = new Stone(true);
        fakeBoard[1][1].setGroupId(2);
        LinkedGroup linkedGroup = new LinkedGroup(1, true, fakeBoard);
        assertThat(linkedGroup.getIds(), hasItems(1));
    }

    @Test
    public void groupsConnectedHCPoints() {
        Stone[][] fakeBoard = new Stone[9][9];
        fakeBoard[0][0] = new Stone(true);
        fakeBoard[0][0].setGroupId(1);
        fakeBoard[2][0] = new Stone(true);
        fakeBoard[2][0].setGroupId(2);
        fakeBoard[2][1] = new Stone(true);
        fakeBoard[2][1].setGroupId(2);
        fakeBoard[2][2] = new Stone(true);
        fakeBoard[2][2].setGroupId(2);
        fakeBoard[0][2] = new Stone(true);
        fakeBoard[0][2].setGroupId(2);
        fakeBoard[1][2] = new Stone(true);
        fakeBoard[1][2].setGroupId(2);

        OutputService.draw(fakeBoard);

        LinkedGroup linkedGroup = new LinkedGroup(1, true, fakeBoard);
        assertThat(linkedGroup.getIds(), hasItems(1, 2));
    }

}