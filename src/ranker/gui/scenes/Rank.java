package ranker.gui.scenes;

import ranker.data.GameList;
import ranker.gui.basic.Panel;
import ranker.gui.basic.Scene;
import ranker.gui.GamePanel;
import ranker.gui.SceneID;
import ranker.gui.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rank extends Scene implements MouseWheelListener, MouseListener, MouseMotionListener, ActionListener {
    private GamePanel[] panelArray;

    private Point initialPos;
    private int initialIndex;

    /// The panel which contains all GamePanels, and is moved by scrolling.
    private Panel innerPanel = new Panel(false);

    public Rank() {
        super(true);

        JButton outputButton = new JButton("Output");
        outputButton.setBounds(1000, 300, 100, 30);
        outputButton.addActionListener(this);
        add(outputButton);

        add(innerPanel);
    }

    public void refresh() {
        if (panelArray != null)
            for (int i = 0; i < panelArray.length; i++)
                innerPanel.remove(panelArray[i]);

        panelArray = new GamePanel[GameList.length()];

        for (int i = 0; i < panelArray.length; i++) {
            panelArray[i] = new GamePanel(i);
            innerPanel.add(panelArray[i]);
        }

        setScrolledBounds(0);
    }

    private int getMaxHeight() {
        return GamePanel.topMargin * panelArray.length + 5;
    }

    private void setScrolledBounds(final int newYPos) {
        innerPanel.setBounds(
            0,
            newYPos,
            Window.FRAME_SIZE_X,
            getMaxHeight()
        );
    }

    private int indexOf(final Point point) {
        // Account for the scrolled distance
        point.y -= innerPanel.getY();

        if (point.x < GamePanel.leftMargin || point.x > GamePanel.leftMargin + GamePanel.width)
            return -1;

        return (int) ((point.y - 10.0) / GamePanel.topMargin);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        final int scrollMultiplier = 35;

        final int lowestPos = Window.FRAME_SIZE_Y - getMaxHeight();
        final int yPos = innerPanel.getY() - e.getWheelRotation() * scrollMultiplier;

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
    public void mousePressed(MouseEvent e) {
        initialPos = e.getPoint();
        initialIndex = indexOf(initialPos);

        if (initialIndex == -1)
            return;

        innerPanel.setComponentZOrder(panelArray[initialIndex], 0);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (initialIndex == -1)
            return;

        final int finalIndex = indexOf(e.getPoint());

        // If mouse wasn't moved from start, or was moved out of the width of game panels, put the panel back and return.
        if (finalIndex == -1 || initialIndex == finalIndex) {
            panelArray[initialIndex].update();
            return;
        }

        GameList.nudge(initialIndex, finalIndex);

        // Only update panels between start and end indices.
        final int start = Math.min(initialIndex, finalIndex);
        final int end = Math.max(initialIndex, finalIndex);
        for (int i = start; i <= end; i++)
            panelArray[i].update();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (initialIndex == -1)
            return;

        // Set the position of the panel relative to the starting position of the cursor and scroll of the innerPanel.
        panelArray[initialIndex].setPosition(
            e.getX() - initialPos.x + GamePanel.leftMargin,
            e.getY() - innerPanel.getY() - initialPos.y + panelArray[initialIndex].getRegularY()
        );
    }

    @Override
    public void actionPerformed(ActionEvent ignored) {
        Window.changeScene(SceneID.Output);
    }

    // Below methods MUST be implemented, but produce no side effects or return values and can therefore be ignored.
        @Override public void mouseEntered(MouseEvent ignored) {}
        @Override public void mouseExited(MouseEvent ignored) {}
        @Override public void mouseClicked(MouseEvent ignored) {}
        @Override public void mouseMoved(MouseEvent ignored) {}
}