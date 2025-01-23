package Ranker.Data;

import java.util.ArrayList;

public class GameList {
    private static final ArrayList<Game> array = new ArrayList<>();

    public static void clear() {
        array.clear();
    }

    public static void add(final String name, final int id) {
        array.add(new Game(name, id));
    }

    public static String nameOf(final int index) {
        return array.get(index).getName();
    }

    public static int idOf(final int index) {
        return array.get(index).getID();
    }

    public static int rankOf(final int id) {
        for (int i = 0; i < array.size(); i++)
            if (array.get(i).getID() == id)
                return i;

        throw new RuntimeException("Cannot retrieve rank of gameID " + id + ": game does not exist.");
    }

    public static String getListPlain() {
        String toReturn = "";

        for (Game game : array)
            toReturn += game + "\n";

        return toReturn;
    }

    public static String getListHTML() {
        String toReturn = "<html>";

        for (Game game : array)
            toReturn += game + "<br>\n";

        return toReturn + "</html>";
    }

    public static String getListSteam() {
        return "Not yet implemented."; // TODO: Implement.
    }
}