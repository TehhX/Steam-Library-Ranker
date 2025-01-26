package Ranker.GUI.Scenes;

import Ranker.GUI.Basic.Scene;

import javax.swing.*;

public class Loading extends Scene {
    public Loading() {
        super(false);

        JLabel loadLabel = new JLabel("Loading library...");
        loadLabel.setBounds(100, 100, 500, 500);
        add(loadLabel);
    }
}