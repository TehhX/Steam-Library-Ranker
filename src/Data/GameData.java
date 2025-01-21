package Data;

class GameData {
    private final String gameName;
    private final int gameID;

    public GameData(final String gameName, final int gameID) {
        this.gameName = gameName;
        this.gameID = gameID;
    }

    public String getName() {
        return gameName;
    }

    public int getID() {
        return gameID;
    }
}