package RankerGUI;

import RankerGUI.Scenes.*;
import javax.swing.*;

public class Window extends JFrame {
    // The frame dimensions
    static final int FRAME_SIZE_X = 1200;
    static final int FRAME_SIZE_Y = 600;

    /// A variable to ensure that only one Window object can exist for the duration of the program.
    private static boolean alreadyInitialized = false;

    static SceneID currentScene = null;
    static Scene[] sceneArray;

    public Window() {
        super("Steam Ranker Window");

        // In case of improper Window use, throw error.
        if (alreadyInitialized)
            throw new RuntimeException("Attempted to initialize more than one frame, program terminated.");
        else
            alreadyInitialized = true;

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
        setSize(FRAME_SIZE_X, FRAME_SIZE_Y);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(null); // TODO: Change icon image
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        changeScene(SceneID.Input);
    }

    /// Removes the previous scene unless there has been no scenes added yet, adds the requested scene regardless.
    public void changeScene(SceneID nextScene) {
        if (currentScene != null)
            sceneArray[currentScene.ordinal()].removeScene();

        sceneArray[nextScene.ordinal()].addScene();
        currentScene = nextScene;
    }
}