package Ranker.Data;

import java.util.ArrayList;

public class GameList {
    private static final ArrayList<Game> gameArrayList = new ArrayList<>();

    public static void clear() {
        gameArrayList.clear();
    }

    public static void add(final String name, final int id) {
        gameArrayList.add(new Game(name, id));
    }

    public static String nameOf(final int index) {
        return gameArrayList.get(index).getName();
    }

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

    public static String getListPaired() {
        String toReturn = "";

        for (int i = 0; i < gameArrayList.size(); i++)
            toReturn += nameOf(i) + ": " + idOf(i) + "\n";

        return toReturn;
    }

    public static String getListPlain() {
        String toReturn = "";

        for (int i = 0; i < gameArrayList.size(); i++)
            toReturn += (i + 1) + ": " + nameOf(i) + '\n';

        return toReturn;
    }
    
    public static String getListHTML() {
        String toReturn = "<html>";

        for (int i = 0; i < gameArrayList.size(); i++)
            toReturn += (i + 1) + ": " + nameOf(i) + "<br>";

        return toReturn + "</html>";
    }

    public static String getListSteam() {
        String toReturn = "";

        for (int i = 0; i < gameArrayList.size(); i++)
            toReturn += (i + 1) + ": [url=https://store.steampowered.com/app/" + idOf(i) + "] " + nameOf(i) + " [/url]\n";

        return toReturn;
    }
}