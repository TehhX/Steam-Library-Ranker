import RankerGUI.Window;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.Scanner;

public class RankerMain {
    /// The Steam64 ID provided by the user.
    static String userID;
    /// The scanner object to take user input from the terminal.
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new Window();

        userID = getInput();
        inputXML();
        if (Game.getGameList().isEmpty())
            throw new RuntimeException("Game list is empty, error has occurred.");
    }

    /// Gets a SteamID64 from the user via the terminal.
    private static String getInput() {
        System.out.print("Enter a SteamID: ");
        String input = sc.nextLine().trim();

        // 17 is the character length for a SteamID64
        if (input.length() != 17) {
            System.out.println("Not an acceptable SteamID64. Custom URLs are not yet supported.");
            return getInput();
        }

        return input;
    }

    /// Downloads the XML into memory, creates the appropriate Nodes/Node Lists for use with addGameObject().
    private static void inputXML() {
        Document document;

        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new URL(
                "https://steamcommunity.com/profiles/" + userID + "/games?tab=all&xml=1").openStream()
            );
            document.getDocumentElement().normalize();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        final NodeList namesList = document.getElementsByTagName("name");
        final NodeList idList = document.getElementsByTagName("appID");

        final int lastIndex = Math.min(namesList.getLength(), idList.getLength());
        for (int i = 0; i < lastIndex; i++)
            addGameObject(namesList, idList, i);
    }

    /// Takes a name and ID node, makes an object of class Game with both data and adds it to the master list.
    private static void addGameObject(NodeList names, NodeList ids, int i) {
        final Node name = names.item(i);
        final Node id = ids.item(i);

        if (!areBothElements(name, id))
            return;

        new Game(name.getTextContent(), Integer.parseInt(id.getTextContent()));
    }

    /// Checks to see if the two passed nodes are both elements, returns result.
    private static boolean areBothElements(Node n1, Node n2) {
        return n1.getNodeType() == Node.ELEMENT_NODE &&
               n2.getNodeType() == Node.ELEMENT_NODE;
    }

    /// Prints the list of games in order to terminal with the formatting Steam uses for profile text.
    private static void printListSteamFormatted() {
        for (int i = 0; i < Game.getGameList().size(); i++)
            System.out.println(
                i + 1 +
                ": [url=https://store.steampowered.com/app/" +
                Game.getGameList().get(i).getID() +
                "] " +
                Game.getGameList().get(i).getName() +
                " [/url]"
            );
    }

    /// Prints a human-readable list of games.
    private static void printListUnformatted() {
        for (int i = 0; i < Game.getGameList().size(); i++)
            System.out.println(
                i + 1 +
                ": " +
                Game.getGameList().get(i).getName()
            );
    }
}