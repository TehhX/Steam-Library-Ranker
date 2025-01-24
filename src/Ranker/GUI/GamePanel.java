package Ranker.GUI;

import Ranker.Data.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends Panel implements MouseListener {
    // Various constants
    public static final int arc = 25;
    public static final int width = 600;
    public static final int height = 150;
    public static final int margin = height + 15;

    private boolean mouseIn = false;

    final Game game;

    public GamePanel(final Game game, final int yPos) {
        super(false);

        this.game = game;

        setBounds(300, yPos, width, height);
    }

    public void addListener() {
        addMouseListener(this);
    }

    public void removeListener() {
        removeMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.fillRoundRect(0, 0, width, height, arc, arc);
        g.setColor(Color.green);
        g.setFont(new Font("Arial", Font.PLAIN, 40));
        g.drawString(game.getName(), 20, 40);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(game.getName());
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
