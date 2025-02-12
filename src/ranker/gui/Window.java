package ranker.gui;

import ranker.gui.basic.Scene;
import ranker.gui.scenes.Input;
import ranker.gui.scenes.Loading;
import ranker.gui.scenes.Output;
import ranker.gui.scenes.Rank;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public class Window {
    // The frame dimensions
    public static final int FRAME_SIZE_X = 1200;
    public static final int FRAME_SIZE_Y = 600;

    /// The frame for adding and removing scenes to and from.
    private static final JFrame frame = new JFrame("Steam Library Ranker");

    /// Holds the SceneID of the currently visible scene.
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

    /// Adds all the allowed listener types implemented by a scene to the frame.
    public static void addListeners(final Scene scene) {
        if (scene instanceof KeyListener)
            frame.addKeyListener((KeyListener) scene);

        if (scene instanceof MouseWheelListener)
            frame.addMouseWheelListener((MouseWheelListener) scene);

        if (scene instanceof MouseListener)
            frame.addMouseListener((MouseListener) scene);

        if (scene instanceof MouseMotionListener)
            frame.addMouseMotionListener((MouseMotionListener) scene);
    }

    /// Removes all the allowed listener types implemented by a scene from the frame.
    public static void removeListeners(final Scene scene) {
        if (scene instanceof KeyListener)
            frame.removeKeyListener((KeyListener) scene);

        if (scene instanceof MouseWheelListener)
            frame.removeMouseWheelListener((MouseWheelListener) scene);

        if (scene instanceof MouseListener)
            frame.removeMouseListener((MouseListener) scene);

        if (scene instanceof MouseMotionListener)
            frame.removeMouseMotionListener((MouseMotionListener) scene);
    }

    public static void refreshRank() {
        ((Rank) sceneArray[SceneID.Rank.ordinal()]).refresh();
    }
}