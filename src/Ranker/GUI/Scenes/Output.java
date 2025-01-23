package Ranker.GUI.Scenes;

import Ranker.GUI.Scene;

public class Output extends Scene {
    public Output() {
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