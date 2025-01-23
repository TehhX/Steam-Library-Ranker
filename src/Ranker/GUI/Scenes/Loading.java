package Ranker.GUI.Scenes;

import Ranker.GUI.Scene;

import javax.swing.*;

public class Loading extends Scene {
    private JLabel loadLabel = new JLabel("Loading library...");

    public Loading() {
        super(false);

        setLoadLabel();
        add(loadLabel);
    }

    private void setLoadLabel() {
        loadLabel.setBounds(100, 100, 500, 500);
    }

    @Override
    public void addScene() {
        setVisible(true);
    }

    @Override
    public void removeScene() {
        setVisible(false);
    }
}