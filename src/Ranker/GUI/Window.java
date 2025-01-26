package Ranker.GUI;

import Ranker.GUI.Basic.Scene;
import Ranker.GUI.Scenes.Input;
import Ranker.GUI.Scenes.Loading;
import Ranker.GUI.Scenes.Output;
import Ranker.GUI.Scenes.Rank;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelListener;

public class Window {
    // The frame dimensions
    public static final int FRAME_SIZE_X = 1200;
    public static final int FRAME_SIZE_Y = 600;

    /// The frame for adding and removing scenes to and from
    private final static JFrame frame = new JFrame("Steam Library Ranker");

    private static SceneID currentScene = null;
    private static Scene[] sceneArray;

    public Window() {
        // Define the array of scenes
        sceneArray = new Scene[] {
            new Input(),
            new Loading(),
            new Rank(),
            new Output()
        };

        // Add all scenes to the frame
        for (Scene scene : sceneArray)
            frame.getContentPane().add(scene);

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
    public static void changeScene(final SceneID nextScene) {
        if (currentScene != null)
            sceneArray[currentScene.ordinal()].removeScene();

        frame.requestFocus();
        sceneArray[nextScene.ordinal()].addScene();
        currentScene = nextScene;
    }

    public static void addListeners(final Scene sceneListener) {
        if (sceneListener instanceof KeyListener)
            frame.addKeyListener((KeyListener) sceneListener);

        if (sceneListener instanceof MouseWheelListener)
            frame.addMouseWheelListener((MouseWheelListener) sceneListener);
    }

    public static void removeListeners(final Scene sceneListener) {
        if (sceneListener instanceof KeyListener)
            frame.removeKeyListener((KeyListener) sceneListener);

        if (sceneListener instanceof MouseWheelListener)
            frame.removeMouseWheelListener((MouseWheelListener) sceneListener);
    }
}