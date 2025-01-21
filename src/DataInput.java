import java.net.URL;
import java.util.Scanner;

class DataInput {
    private final String userID;
    private final String xmlFull;
    private final String xmlGameIDS;
    private final String xmlGameNames;

    public DataInput(final String userID) {
        this.userID = userID;
        this.xmlFull = downloadXML();
        this.xmlGameIDS = cleanIDS();
        this.xmlGameNames = cleanNames();
    }

    private String downloadXML() {
        String toReturn = "";
        try {
            Scanner urlSc = new Scanner(new URL("https://steamcommunity.com/" + userID + "/games?tab=all&xml=1").openStream());

            while (urlSc.hasNext())
                toReturn += urlSc.nextLine();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toReturn;
    }

    private String cleanNames() {
        String toReturn = xmlFull;

        return toReturn;
    }

    private String cleanIDS() {
        String toReturn = xmlFull;



        return toReturn;
    }
}