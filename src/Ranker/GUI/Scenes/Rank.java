package Ranker.GUI.Scenes;

import Ranker.Data.GameList;
import Ranker.GUI.Basic.Scene;
import Ranker.GUI.GamePanel;

public class Rank extends Scene implements SceneChangeActions {
    private static GamePanel[] panelArray;

    public Rank() {
        super(true);

        addChangeActions(this);
    }

    public void refresh() {
        if (panelArray != null)
            for (int i = 0; i < panelArray.length; i++)
                remove(panelArray[i]);

        panelArray = new GamePanel[GameList.length()];

        for (int i = 0; i < panelArray.length; i++) {
            panelArray[i] = new GamePanel(GameList.getGame(i), 10 + GamePanel.margin * i);
            add(panelArray[i]);
        }

        for (GamePanel panel : panelArray)
            panel.addListener();
    }

    public static void clearPanelArray() {
        panelArray = null;
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