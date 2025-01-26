package Ranker.GUI.Basic;

import Ranker.GUI.SceneChangeActions;

public class Scene extends Panel {
    private SceneChangeActions changeActions = null;

    public Scene(final boolean opaque) {
        super(opaque);

        setVisible(false);
    }

    public void addChangeActions(final SceneChangeActions changeActions) {
        this.changeActions = changeActions;
    }

    /// Code to execute when adding a scene to the frame.
    public void addScene() {
        if (changeActions != null)
            changeActions.addActions();

        setVisible(true);
    }

    /// Code to execute when removing a scene from the frame.
    public void removeScene() {
        setVisible(false);

        if (changeActions != null)
            changeActions.removeActions();
    }
}