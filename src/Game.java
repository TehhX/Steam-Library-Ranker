class Game {
    private final String gameName;
    private final int gameID;

    public Game(final String gameName, final int gameID) {
        this.gameName = gameName;
        this.gameID = gameID;
    }

    @Override
    public String toString() {
        return gameName + ": " + gameID;
    }
}