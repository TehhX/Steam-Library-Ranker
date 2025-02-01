package ranker.gui.basic;

import ranker.gui.scenes.SceneChangeActions;
import ranker.gui.Window;

public class Scene extends Panel {
    private SceneChangeActions changeActions = null;

    public Scene(final boolean opaque) {
        super(opaque);

        if (this instanceof SceneChangeActions)
            addChangeActions((SceneChangeActions) this);

        setVisible(false);
    }

    public void addChangeActions(final SceneChangeActions changeActions) {
        this.changeActions = changeActions;
    }

    /// Code to execute when adding a scene to the frame.
    public void addScene() {
        if (changeActions != null)
            changeActions.addActions();

        Window.addListeners(this);
        setVisible(true);
    }

    /// Code to execute when removing a scene from the frame.
    public void removeScene() {
        setVisible(false);
        Window.removeListeners(this);

        if (changeActions != null)
            changeActions.removeActions();
    }
}