package RankerGUI;

abstract public class Scene extends Panel {
    public Scene(final boolean opaque) {
        super(opaque);

        setVisible(false);
    }

    abstract public void addScene();

    abstract public void removeScene();
}