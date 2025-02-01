package ranker.gui.scenes;

import ranker.gui.basic.Scene;

import javax.swing.*;
import java.awt.*;

public class Loading extends Scene {
    public Loading() {
        JLabel loadLabel = new JLabel("Loading library...");
        loadLabel.setBounds(100, 100, 500, 500);
        loadLabel.setForeground(Color.white);
        add(loadLabel);
    }
}