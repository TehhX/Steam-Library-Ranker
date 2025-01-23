package Ranker.GUI.Scenes;

import Ranker.GUI.Scene;

public class Rank extends Scene {
    public Rank() {
        super(true);
    }

    @Override
    public void addScene() {
        setVisible(true);
    }

    @Override
    public void removeScene() {
        setVisible(false);
    }
}