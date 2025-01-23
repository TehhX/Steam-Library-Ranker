package Ranker.GUI.Scenes;

import Ranker.Data.GameList;
import Ranker.GUI.Scene;

public class Output extends Scene {

    public Output() {
        super(true);
    }

    public void printthangs() {
        System.out.println(GameList.getListPlain());
    }

    @Override
    public void addScene() {
        setVisible(true);
        printthangs();
    }

    @Override
    public void removeScene() {
        setVisible(false);
    }
}