package Ranker.GUI;

import javax.swing.*;

public class Panel extends JPanel {
    public Panel(final boolean opaque) {
        super(null);

        setBackground(null);
        setOpaque(opaque);
        setSize(Window.FRAME_SIZE_X, Window.FRAME_SIZE_Y);
    }
}