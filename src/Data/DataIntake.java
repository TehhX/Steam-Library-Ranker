package Data;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;

class DataIntake {
    /// Master array of GameData objects
    static ArrayList<GameData> gameDataList = new ArrayList<>();

    /// Downloads the XML into memory, creates the appropriate Nodes/Node Lists for use with addGameObject().
    public static void inputXML(final String userID) {
        gameDataList.clear();

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
        for (int i = 0; i < lastIndex; i++) {
            Node name = namesList.item(i);
            Node id = idList.item(i);

            if (areBothElements(name, id))
                gameDataList.add(new GameData(
                    name.getTextContent(),
                    Integer.parseInt(id.getTextContent())
                ));
        }
    }

    /// Checks to see if the two passed nodes are both elements, returns result.
    private static boolean areBothElements(Node n1, Node n2) {
        return n1.getNodeType() == Node.ELEMENT_NODE &&
            n2.getNodeType() == Node.ELEMENT_NODE;
    }

    public static int getRankOf(GameData gameObject) {
        return gameDataList.indexOf(gameObject);
    }
}