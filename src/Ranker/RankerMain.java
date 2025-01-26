package Ranker;

import Ranker.Data.Intake;
import Ranker.GUI.Window;

public class RankerMain {
    public static void main(String[] args) {
        try {
            Intake.loadNonGames();
            new Window();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}