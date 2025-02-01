package ranker.gui.basic;

import ranker.gui.scenes.SceneChangeActions;
import ranker.gui.Window;

import java.awt.*;

public class Scene extends Panel {
    private SceneChangeActions changeActions = null;

    public Scene() {
        super(true);

        setBackground(Color.DARK_GRAY);

        if (this instanceof SceneChangeActions)
            this.changeActions = (SceneChangeActions) this;

        setVisible(false);
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