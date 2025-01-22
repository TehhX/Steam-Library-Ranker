package RankerGUI;

import RankerGUI.Scenes.*;
import javax.swing.*;

public class Window extends JFrame {
    // The frame dimensions
    static final int FRAME_SIZE_X = 1200;
    static final int FRAME_SIZE_Y = 600;

    static SceneID currentScene = null;
    static Scene[] sceneArray;

    public Window() {
        super("Steam Ranker Window");

        // Define the array of scenes
        sceneArray = new Scene[] {
            new Input(),
            new Rank(),
            new Output()
        };

        // Add all scenes to the frame
        for (Scene scene : sceneArray)
            add(scene);

        // Set up the frame with the input panel
        frameSetup();
        changeScene(SceneID.Input);
    }

    /// Does basic frame setup like setting the size, position and behavior.
    private void frameSetup() {
        setSize(FRAME_SIZE_X, FRAME_SIZE_Y);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(null); // TODO: Change icon image
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /// Removes the previous scene if it exists, adds the requested scene.
    public void changeScene(SceneID nextScene) {
        if (currentScene != null)
            sceneArray[currentScene.ordinal()].removeScene();

        sceneArray[nextScene.ordinal()].addScene();
        currentScene = nextScene;
    }
}