package game.files.model;

import lombok.Data;


@Data
public class Stone {

    private boolean isWhite;
    private boolean isPartOfGroup = false;
    private int groupId;

    public Stone() {

    }

    public Stone(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isStoneWhite() {
        return this.isWhite;
    }
}
