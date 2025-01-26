package Ranker.GUI;

import Ranker.Data.Game;
import Ranker.GUI.Basic.Panel;
import Ranker.GUI.Basic.WrappedLabel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends Panel implements MouseListener {
    // Various constants
    public static final int arc = 25;
    public static final int width = 600;
    public static final int height = 150;
    public static final int margin = height + 15;

    private final Panel background = new Panel(false, g -> {
        g.setColor(new Color(114, 9, 183));
        g.fillRoundRect(0, 0, width, height, arc, arc);
    });

    public GamePanel(final Game game, final int yPos) {
        super(false);

        setBackground(Color.green);
        setBounds(0, yPos, width, height);

        add(new WrappedLabel(game.getName()));
        add(background);
    }

    public void addListener() {
        addMouseListener(this);
    }

    public void removeListener() {
        removeMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
