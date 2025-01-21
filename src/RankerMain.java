import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.*;

public class RankerMain {
    /// The Steam64 ID provided by the user.
    static String userID;
    /// The master array list of game name to ID associations.
    static ArrayList<Game> gameList = new ArrayList<>();
    /// The scanner object to take user input from the terminal.
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        userID = getInput();

        inputXML();
        if (gameList.isEmpty())
            throw new RuntimeException("Game list is empty, error has occurred.");
        for (Game ind : gameList)
            System.out.println(ind);
    }

    /// Gets a SteamID64 from the user via the terminal.
    private static String getInput() {
        System.out.print("Enter a SteamID: ");
        String input = sc.nextLine().trim();

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

        gameList.add(new Game(name.getTextContent(), Integer.parseInt(id.getTextContent())));
    }

    /// Checks to see if the two passed nodes are both elements, returns result.
    private static boolean areBothElements(Node n1, Node n2) {
        return n1.getNodeType() == Node.ELEMENT_NODE &&
               n2.getNodeType() == Node.ELEMENT_NODE;
    }
}