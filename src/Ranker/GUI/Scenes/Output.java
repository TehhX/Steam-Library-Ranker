package Ranker.GUI.Scenes;

import Ranker.Data.GameList;
import Ranker.GUI.GamePanel;
import Ranker.GUI.Scene;
import Ranker.GUI.SceneChangeActions;

public class Output extends Scene implements SceneChangeActions {
    private GamePanel[] panelArray;

    public Output() {
        super(true);
        addChangeActions(this);

    }

    public void refresh() {
        panelArray = new GamePanel[GameList.length()];

        for (int i = 0; i < GameList.length(); i++)
            panelArray[i] = new GamePanel(GameList.getGame(i));
    }

    @Override
    public void addActions() {
        System.out.println(GameList.getListPlain());
        refresh();
    }

    @Override
    public void removeActions() {}
}