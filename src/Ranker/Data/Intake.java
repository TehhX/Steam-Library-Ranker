package Ranker.Data;

import Ranker.GUI.Window;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Intake {
    /// Non-game count, for the nonGames size. Update when adding new non-games.
    private static final int nonGameCount = 84;

    /// A set of non-games that will not be included in the ranking.
    private static final Set<Integer> nonGames = new HashSet<>(nonGameCount, 1.1f);

    /// Downloads the user library associated with the userID into GameList.
    public static void downloadUserLibrary(final String userID) throws RuntimeException {
        if (userID.length() != 17)
            throw new RuntimeException("SteamID is " + (userID.length() > 17 ? "too long." : "too short."));

        Document document;

        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new URL(
                "https://steamcommunity.com/profiles/" + userID + "/games?tab=all&xml=1").openStream()
            );

            document.getDocumentElement().normalize();
        }
        catch (Exception e) {
            throw new RuntimeException("Could not download XML file from Steam servers.");
        }

        if (document.getElementsByTagName("error").getLength() != 0)
            throw new RuntimeException("Profile ID malformed, library private, or other profile specific error.");

        final NodeList namesList = document.getElementsByTagName("name");
        final NodeList idList = document.getElementsByTagName("appID");

        final int lastIndex = Math.min(namesList.getLength(), idList.getLength());

        if (lastIndex < 2)
            throw new RuntimeException("There are not enough games to rank in the given profile.");

        for (int i = 0; i < lastIndex; i++) {
            Node name = namesList.item(i);
            Node id = idList.item(i);

            // Will skip entry if it is found in the non-games list.
            if (isGame(id)) {
                final String gameName = name.getTextContent();
                final int gameID = Integer.parseInt(id.getTextContent());

                GameList.add(gameName, gameID);
            }
        }

        Window.refreshRank();
    }

    /// Checks the appID against the list of non-games, returns true if it is a game.
    private static boolean isGame(Node idNode) {
        return !nonGames.contains(Integer.parseInt(idNode.getTextContent()));
    }

    /// Loads the set of non-games from Non Games.txt for downloadUserLibrary to skip over.
    public static void loadNonGames() {
        try {
            final Scanner sc = new Scanner(new File("Resources/Non Games.txt"));

            while (sc.hasNext()) {
                final String next = sc.nextLine();

                if (!next.startsWith("//"))
                    nonGames.add(Integer.parseInt(next));
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (nonGames.size() != nonGameCount)
            throw new RuntimeException("Incorrect non-game count. Should be " + nonGames.size() + ".");
    }
}
