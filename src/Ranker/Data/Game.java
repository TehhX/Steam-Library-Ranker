package Ranker.Data;

public class Game {
    private final String gameName;
    private final int gameID;

    Game(final String gameName, final int gameID) {
        this.gameName = gameName;
        this.gameID = gameID;
    }

    String getName() {
        return gameName;
    }

    int getID() {
        return gameID;
    }

    @Override
    public String toString() {
        return "[" + gameName + ", " + gameID + "]";
    }
}