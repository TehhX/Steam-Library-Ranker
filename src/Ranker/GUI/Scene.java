package Ranker.GUI;

abstract public class Scene extends Panel {
    public Scene(final boolean opaque) {
        super(opaque);

        setVisible(false);
    }

    /// Code to execute when adding a scene to the frame.
    abstract public void addScene();

    /// Code to execute when removing a scene from the frame.
    abstract public void removeScene();
}