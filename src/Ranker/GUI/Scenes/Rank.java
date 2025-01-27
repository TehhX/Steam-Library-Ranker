package Ranker.GUI.Scenes;

import Ranker.Data.Game;
import Ranker.Data.GameList;
import Ranker.GUI.Basic.Panel;
import Ranker.GUI.Basic.Scene;
import Ranker.GUI.GamePanel;
import Ranker.GUI.Window;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Rank extends Scene implements SceneChangeActions, MouseWheelListener {
    private static final int scrollMultiplier = 30;

    private static GamePanel[] panelArray;

    private Panel innerPanel = new Panel(false);

    public Rank() {
        super(true);

        add(innerPanel);
    }

    public void refresh() {
        if (panelArray != null)
            for (int i = 0; i < panelArray.length; i++)
                innerPanel.remove(panelArray[i]);

        panelArray = new GamePanel[GameList.length()];

        for (int i = 0; i < panelArray.length; i++) {
            panelArray[i] = new GamePanel(i, 10 + GamePanel.topMargin * i);
            innerPanel.add(panelArray[i]);
        }

        setScrolledBounds(0);
    }

    private int getMaxHeight() {
        return GamePanel.topMargin * panelArray.length - 5;
    }

    private int newYPos(final int rotation) {
        return innerPanel.getY() - rotation * scrollMultiplier;
    }

    private void setScrolledBounds(final int newYPos) {
        innerPanel.setBounds(
            0,
            newYPos,
            Window.FRAME_SIZE_X,
            getMaxHeight()
        );
    }

    private void swapPanels(final int p1, final int p2) {
        final int tempIndex = panelArray[p1].getGameIndex();

        panelArray[p1].setGameIndex(panelArray[p2].getGameIndex());
        panelArray[p2].setGameIndex(tempIndex);
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
        clearPanelArray();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        final int lowestPos = -getMaxHeight() + Window.FRAME_SIZE_Y;
        final int yPos = newYPos(e.getWheelRotation());

        final boolean tooHigh = yPos > 0;
        final boolean tooLow = yPos < lowestPos;

        if (tooHigh) {
            setScrolledBounds(0);
            return;
        }

        if (tooLow) {
            setScrolledBounds(lowestPos);
            return;
        }

        setScrolledBounds(yPos);
    }
}