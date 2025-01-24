package Ranker.GUI.Scenes;

import Ranker.Data.GameList;
import Ranker.GUI.GamePanel;
import Ranker.GUI.Scene;
import Ranker.GUI.SceneChangeActions;

public class Rank extends Scene implements SceneChangeActions {
    private GamePanel[] panelArray;

    public Rank() {
        super(true);

        addChangeActions(this);
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

        for (GamePanel panel : panelArray)
            panel.addListener();
    }

    @Override
    public void addActions() {
        refresh();
    }

    @Override
    public void removeActions() {
        for (GamePanel panel : panelArray)
            panel.removeListener();
    }
}