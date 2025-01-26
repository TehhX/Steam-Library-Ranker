package Ranker.GUI.Scenes;

import Ranker.Data.GameList;
import Ranker.GUI.Basic.Panel;
import Ranker.GUI.Basic.Scene;
import Ranker.GUI.GamePanel;
import Ranker.GUI.Window;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Rank extends Scene implements SceneChangeActions, MouseWheelListener {
    private static final int leftMargin = 300;
    private static final int scrollMultiplier = 50;

    private static GamePanel[] panelArray;

    private Panel innerPanel = new Panel(true);

    public Rank() {
        super(true);

        innerPanel.setBounds(leftMargin, 0, 0, 0);
        innerPanel.setBackground(Color.GREEN);

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

        setScrolledBounds(0);
    }

    // H = Height, n = Panel Count.
    // H(n) = 165n -5
    // D: {n E N | n > 0}      R: {H E N}
    private int getMaxHeight() {
        return GamePanel.margin * panelArray.length - 5;
    }

    private int newYPos(final int rotation) {
        return innerPanel.getY() - rotation * scrollMultiplier;
    }

    private void setScrolledBounds(final int newYPos) {
        innerPanel.setBounds(
            leftMargin,
            newYPos,
            GamePanel.width,
            getMaxHeight()
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