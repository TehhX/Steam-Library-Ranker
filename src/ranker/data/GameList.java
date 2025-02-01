package ranker.data;

import java.util.ArrayList;

/// The class that handles all operations regarding the array list of games and holds the list itself.
public class GameList {
    private static final ArrayList<Game> gameArrayList = new ArrayList<>();

    /// Adds a game object to the list.
    public static void add(final Game game) {
        gameArrayList.add(game);
    }

    /// Adds a game object to the list, but takes the game data raw instead of as a class object.
    public static void add(final String name, final int id) {
        add(new Game(name, id));
    }

    /// Returns the game name at specified index.
    public static String nameOf(final int index) {
        return gameArrayList.get(index).getName();
    }

    /// Returns the game ID at specified index.
    public static int idOf(final int index) {
        return gameArrayList.get(index).getID();
    }

    public static int length() {
        return gameArrayList.size();
    }

    public static void nudge(final int take, final int put) {
        final Game temp = gameArrayList.get(take);

        gameArrayList.remove(take);
        gameArrayList.add(put, temp);
    }

    /// Get a list of game names paired with game ids up to a max index. If max is -1, get all.
    public static String getListPaired(int max) {
        if (gameArrayList.size() < max || max == -1)
            max = gameArrayList.size();

        String toReturn = "";

        for (int i = 0; i < max; i++)
            toReturn += nameOf(i) + ": " + idOf(i) + "\n";

        return toReturn;
    }

    public static String getListPaired() {
        return getListPaired(-1);
    }

    /// Get a list of game names with their ranks up to a max index. If max is -1, get all.
    public static String getListPlain(int max) {
        if (gameArrayList.size() < max || max == -1)
            max = gameArrayList.size();

        String toReturn = "";

        for (int i = 0; i < max; i++)
            toReturn += (i + 1) + ": " + nameOf(i) + '\n';

        return toReturn;
    }

    public static String getListPlain() {
        return getListPlain(-1);
    }

    /// Get a list of game names separated by HTML style line breaks up to a max index. If max is -1, get all.
    public static String getListHTML(int max) {
        if (gameArrayList.size() < max || max == -1)
            max = gameArrayList.size();

        String toReturn = "<html>";

        for (int i = 0; i < max; i++)
            toReturn += (i + 1) + ": " + nameOf(i) + "<br>";

        return toReturn + "</html>";
    }

    public static String getListHTML() {
        return getListHTML(-1);
    }

    /**
     * Get a list of games as Steam formatted links for use in Steam profiles/workshop items up to a max index.
     * If max is -1, get all.
     */
    public static String getListSteam(int max) {
        if (gameArrayList.size() < max || max == -1)
            max = gameArrayList.size();

        String toReturn = "";

        for (int i = 0; i < max; i++)
            toReturn += (i + 1) + ": [url=https://store.steampowered.com/app/" + idOf(i) + "] " + nameOf(i) + " [/url]\n";

        return toReturn;
    }

    public static String getListSteam() {
        return getListSteam(-1);
    }
}