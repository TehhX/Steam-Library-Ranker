package RankerGUI;

abstract public class Scene extends Panel {
    public Scene(final boolean opaque) {
        super(opaque);


    }

    abstract public void addScene();

    abstract public void removeScene();
}