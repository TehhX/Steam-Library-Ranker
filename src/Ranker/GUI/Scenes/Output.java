package Ranker.GUI.Scenes;

import Ranker.Data.GameList;
import Ranker.GUI.Scene;

import javax.swing.*;

public class Output extends Scene {
    JLabel label = new JLabel("");

    public Output() {
        super(true);
        label.setBounds(50, 50, 0, 0);
        add(label);
        refreshLabel();
    }

    public void refreshLabel() {
        label.setText(GameList.getListHTML());
        label.setSize(label.getPreferredSize().width, label.getPreferredSize().height);
    }

    @Override
    public void addScene() {
        refreshLabel();
        System.out.println(GameList.getListSteam());
        setVisible(true);
    }

    @Override
    public void removeScene() {
        setVisible(false);
    }
}