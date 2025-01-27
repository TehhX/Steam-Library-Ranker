package Ranker.GUI.Scenes;

import Ranker.Data.GameList;
import Ranker.GUI.Basic.Panel;
import Ranker.GUI.Basic.Scene;
import Ranker.GUI.GamePanel;
import Ranker.GUI.Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Rank extends Scene implements SceneChangeActions, MouseWheelListener, KeyListener {
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

    public static void clearPanelArray() {
        panelArray = null;
    }

    private void swapPanels(final int p1, final int p2) {
        GameList.swap(p1, p2);

        panelArray[p1].update();
        panelArray[p2].update();
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

    @Override
    public void keyPressed(KeyEvent e) {
        final int num = Character.getNumericValue(e.getKeyChar());

        if (e.getKeyChar() == 'e')
            System.out.println(GameList.getListPlain());

        if (num < 0 || num > 9)
            return;

        swapPanels(num, num + 1);
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}