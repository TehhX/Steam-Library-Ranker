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

    public static int length() {
        return array.size();
    }

    public static Game getGame(final int index) {
        return array.get(index);
    }

    public static String getListPlain() {
        String toReturn = "";

        for (int i = 0; i < array.size(); i++)
            toReturn += (i + 1) + ": " + nameOf(i);

        return toReturn;
    }

    public static String getListHTML() {
        String toReturn = "<html>";

        for (int i = 0; i < array.size(); i++)
            toReturn += (i + 1) + ": " + nameOf(i) + "<br>";

        return toReturn + "</html>";
    }

    public static String getListSteam() {
        String toReturn = "";

        for (int i = 0; i < array.size(); i++)
            toReturn += (i + 1) + ": [url=https://store.steampowered.com/app/" + idOf(i) + "] " + nameOf(i) + " [/url]\n";

        return toReturn;
    }
}