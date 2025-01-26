package Ranker.GUI.Scenes;

import Ranker.Data.GameList;
import Ranker.GUI.Basic.Panel;
import Ranker.GUI.Basic.Scene;
import Ranker.GUI.GamePanel;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Rank extends Scene implements SceneChangeActions, MouseWheelListener {
    private static GamePanel[] panelArray;

    private Panel innerPanel = new Panel(false);

    public Rank() {
        super(true);

        add(innerPanel);
        addChangeActions(this);
        addMouseWheelListener(this);
    }

    public void refresh() {
        if (panelArray != null)
            for (int i = 0; i < panelArray.length; i++)
                innerPanel.remove(panelArray[i]);

        panelArray = new GamePanel[GameList.length()];

        for (int i = 0; i < panelArray.length; i++) {
            panelArray[i] = new GamePanel(GameList.getGame(i), 10 + GamePanel.margin * i);
            innerPanel.add(panelArray[i]);
        }

        for (GamePanel panel : panelArray)
            panel.addListener();

        // H = Height, n = Panel Count.
        // H(n) = 165n -5
        // D: {n E N | n > 0}
        // R: {H E N}
        innerPanel.setSize(
            GamePanel.width,
            GamePanel.margin * panelArray.length - 5
        );
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

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        innerPanel.setBounds(
            innerPanel.getX(),
            innerPanel.getY() - e.getWheelRotation() * 10,
            innerPanel.getSize().width,
            innerPanel.getSize().height
        );
    }
}