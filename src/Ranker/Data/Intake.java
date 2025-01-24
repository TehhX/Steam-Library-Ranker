package Ranker.Data;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;

public class Intake {
    /// Downloads the XML into memory, creates the appropriate Nodes/Node Lists for use with addGameObject().
    public static int inputXML(final String userID) {
        GameList.clear();

        Document document;

        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new URL(
                "https://steamcommunity.com/profiles/" + userID + "/games?tab=all&xml=1").openStream()
            );
            document.getDocumentElement().normalize();
        }
        catch (Exception e) {
            return 1;
        }

        if (document.getElementsByTagName("error").getLength() != 0)
            return 2;

        final NodeList namesList = document.getElementsByTagName("name");
        final NodeList idList = document.getElementsByTagName("appID");

        final int lastIndex = Math.min(namesList.getLength(), idList.getLength());
        for (int i = 0; i < lastIndex; i++) {
            Node name = namesList.item(i);
            Node id = idList.item(i);

            if (areBothElements(name, id))
                GameList.add(name.getTextContent(), Integer.parseInt(id.getTextContent()));
        }

        return 0;
    }

    /// Checks to see if the two passed nodes are both elements, returns result.
    private static boolean areBothElements(Node n1, Node n2) {
        return n1.getNodeType() == Node.ELEMENT_NODE &&
               n2.getNodeType() == Node.ELEMENT_NODE;
    }
}