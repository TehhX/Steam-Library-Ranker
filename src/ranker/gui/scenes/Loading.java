package ranker.gui.scenes;

import ranker.gui.basic.Scene;

import javax.swing.*;

public class Loading extends Scene {
    public Loading() {
        super(false);

        JLabel loadLabel = new JLabel("Loading library...");
        loadLabel.setBounds(100, 100, 500, 500);
        add(loadLabel);
    }
}