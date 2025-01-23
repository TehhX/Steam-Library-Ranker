package Ranker.GUI;

import Ranker.GUI.Scenes.*;

import javax.swing.*;

public class Window {
    // The frame dimensions
    static final int FRAME_SIZE_X = 1200;
    static final int FRAME_SIZE_Y = 600;

    /// The frame for adding and removing scenes to and from
    private final static JFrame frame = new JFrame("Steam Library Ranker");

    /// A variable to ensure that only one Window object can exist for the duration of the program.
    private static boolean alreadyInitialized = false;

    private static SceneID currentScene = null;
    private static Scene[] sceneArray;

    public Window() {
        if (!alreadyInitialized) {
            windowSetup();
            alreadyInitialized = true;
        }
        else
            throw new RuntimeException("Attempted to instantiate multiple windows, not allowed.");
    }

    private static void windowSetup() {
        // Define the array of scenes
        sceneArray = new Scene[] {
            new Input(),
            new Loading(),
            new Rank(),
            new Output()
        };

        // Add all scenes to the frame
        for (Scene scene : sceneArray)
            frame.add(scene);

        // Set up the frame with the input panel
        frame.setSize(FRAME_SIZE_X, FRAME_SIZE_Y);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(null); // TODO: Change icon image
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Starting scene
        changeScene(SceneID.Input);
    }

    /// Removes the previous scene unless there has been no scenes added yet, adds the requested scene regardless.
    public static void changeScene(SceneID nextScene) {
        if (currentScene != null)
            sceneArray[currentScene.ordinal()].removeScene();

        frame.requestFocus();
        sceneArray[nextScene.ordinal()].addScene();
        currentScene = nextScene;
    }
}