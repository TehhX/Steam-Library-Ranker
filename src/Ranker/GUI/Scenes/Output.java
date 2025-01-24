package Ranker.GUI.Scenes;

import Ranker.Data.GameList;
import Ranker.GUI.GamePanel;
import Ranker.GUI.Scene;
import Ranker.GUI.SceneChangeActions;

import java.awt.*;

public class Output extends Scene implements SceneChangeActions {
    private GamePanel[] panelArray;

    public Output() {
        super(true);

        addChangeActions(this);
        setBackground(Color.green);
    }

    public void refresh() {
        if (panelArray != null && panelArray.length != 0)
            for (int i = 0; i < panelArray.length; i++)
                remove(panelArray[i]);

        panelArray = new GamePanel[GameList.length()];

        for (int i = 0; i < GameList.length(); i++) {
            panelArray[i] = new GamePanel(GameList.getGame(i), 10 + GamePanel.margin * i);
            add(panelArray[i]);
        }
    }

    @Override
    public void addActions() {
        refresh();
    }

    @Override
    public void removeActions() {}
}