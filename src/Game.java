import java.util.ArrayList;

class Game {
    private static ArrayList<Game> gameList = new ArrayList<>();

    private final String gameName;
    private final int gameID;

    public Game(final String gameName, final int gameID) {
        gameList.add(this);

        this.gameName = gameName;
        this.gameID = gameID;
    }

    public static ArrayList<Game> getGameList() {
        return gameList;
    }

    public String getName() {
        return gameName;
    }

    public int getID() {
        return gameID;
    }
}