package go.web.demo.gamefiles;

class Stone {

    private boolean isWhite;
    private int libertiesCounter = 0;
    private int connectionCounter = 0;
    boolean isPartOfGroup = false;

    Stone(boolean isWhite) {
        this.isWhite = isWhite;
    }

    boolean isStoneWhite() {
        return this.isWhite;
    }

    int getLiberties() {
        return this.libertiesCounter;
    }

    void setLiberties(int x) {
        this.libertiesCounter = x;
    }

    int getConnectionCounter() {
        return this.connectionCounter;
    }

    void setConnectionCounter(int x) {
        this.connectionCounter = x;
    }

    void markAsPartOfGroup() {
        this.isPartOfGroup = true;
    }

    void unMarkAsPartOfGroup() {
        this.isPartOfGroup = false;
    }
}
