import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;

public class RankerMain {
    static String userID;
    static ArrayList<Game> gameList = new ArrayList<>();

    public static void main(String[] args) {
        userID = args[0];

        try {
            inputXML();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (Game ind : gameList)
            System.out.println(ind);
    }

    private static void inputXML() throws Exception {
        // Download and create the XML document from the Steam API in memory and normalize it
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new URL(
            "https://steamcommunity.com/profiles/" + userID + "/games?tab=all&xml=1").openStream()
        );
        document.getDocumentElement().normalize();

        // Get the appropriate parent nodes
        final NodeList namesList = document.getElementsByTagName("name");
        final NodeList idList = document.getElementsByTagName("appID");

        for (int i = 0; i < namesList.getLength(); i++) {
            // Gets sub-nodes with name and ID
            final Node nameNode = namesList.item(i);
            final Node idNode = idList.item(i);

            if (areBothElements(nameNode, idNode)) {
                // Create elements from the nodes
                Element nameElement = (Element) nameNode;
                Element idElement = (Element) idNode;

                // Get their values
                String name = nameElement.getTextContent();
                int id = Integer.parseInt(idElement.getTextContent());

                // Add a new game object to the master list with these values
                gameList.add(new Game(name, id));
            }
        }
    }

    private static boolean areBothElements(Node n1, Node n2) {
        return n1.getNodeType() == Node.ELEMENT_NODE && n2.getNodeType() == Node.ELEMENT_NODE;
    }
}