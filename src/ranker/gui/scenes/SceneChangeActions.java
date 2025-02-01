package ranker.gui.scenes;

/**
 * An interface to help with adding and removing scenes from the frame. Adding or removing listeners, refreshing
 * certain things etc. can be done with this interface, and setVisible will always be called.
 */
public interface SceneChangeActions {
    /// Before setting the scene to visible, addScene will run addActions.
    void addActions();

    /// After setting the scene to visible, removeScene will run removeActions.
    void removeActions();
}