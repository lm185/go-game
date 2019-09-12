package game.files.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ConnectedGroupTest {

    @Test
    public void recognizesTwoGroups() {
        Stone[][] fakeBoard = new Stone[9][9];
        fakeBoard[0][0] = new Stone(true);
        fakeBoard[0][0].setGroupId(1);
        fakeBoard[1][1] = new Stone(true);
        fakeBoard[1][1].setGroupId(2);
        ConnectedGroup conGroup = new ConnectedGroup(1, true, fakeBoard);
        assertThat(conGroup.getIds(), hasItems(1, 2));
    }

}