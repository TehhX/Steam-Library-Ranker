package Ranker.GUI.Scenes;

/**
 * An interface to help with adding and removing scenes from the frame. Adding or removing listeners, refreshing
 * certain things etc. can be done with this interface, and setVisible will always be called.
 */
public interface SceneChangeActions {
    void addActions();

    void removeActions();
}