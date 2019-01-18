package game.files.model;

import lombok.Data;

@Data
public class Stone {

    private boolean isWhite;
    private int liberties = 0;
    private int connectionCounter = 0;
    public boolean isPartOfGroup = false;

    public Stone(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isStoneWhite() {
        return this.isWhite;
    }

    public void markAsPartOfGroup() {
        this.isPartOfGroup = true;
    }

    public void unMarkAsPartOfGroup() {
        this.isPartOfGroup = false;
    }
}
