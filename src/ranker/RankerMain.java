package ranker;

import ranker.data.Intake;
import ranker.gui.Window;

public class RankerMain {
    public static void main(String[] args) {
        Intake.loadNonGames();
        new Window();
    }
}
