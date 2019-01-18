package game.files.model;

public class Stone {

    private boolean isWhite;
    private int libertiesCounter = 0;
    private int connectionCounter = 0;
    public boolean isPartOfGroup = false;

    public Stone(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isStoneWhite() {
        return this.isWhite;
    }

    public int getLiberties() {
        return this.libertiesCounter;
    }

    public void setLiberties(int x) {
        this.libertiesCounter = x;
    }

    public int getConnectionCounter() {
        return this.connectionCounter;
    }

    public void setConnectionCounter(int x) {
        this.connectionCounter = x;
    }

    public void markAsPartOfGroup() {
        this.isPartOfGroup = true;
    }

    public void unMarkAsPartOfGroup() {
        this.isPartOfGroup = false;
    }
}
