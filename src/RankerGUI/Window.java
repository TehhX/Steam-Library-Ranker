package RankerGUI;

import RankerGUI.Scenes.*;

import javax.swing.*;

public class Window {
    static final int FRAME_SIZE_X = 1200;
    static final int FRAME_SIZE_Y = 600;

    static SceneID currentScene = null;
    static Scene[] sceneArray;

    private static JFrame frame = new JFrame("Steam Library Ranker");

    public Window() {
        sceneArray = new Scene[]{
            new Rank()
        };

        frameSetup();
        changeScene(SceneID.Input);
    }

    private static void frameSetup() {
        frame.setSize(FRAME_SIZE_X, FRAME_SIZE_Y);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(null); // TODO: Change icon image
        frame.setVisible(true);
    }

    /// Removes the previous scene if it exists, adds the requested scene.
    public static void changeScene(SceneID nextScene) {
        if (currentScene != null)
            sceneArray[currentScene.ordinal()].removeScene();
        sceneArray[nextScene.ordinal()].addScene();
        currentScene = nextScene;
    }
}