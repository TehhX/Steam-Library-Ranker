package Ranker.GUI;

import Ranker.Data.Game;
import Ranker.GUI.Basic.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends Panel implements MouseListener {
    // Various constants
    public static final int arc = 25;
    public static final int width = 600;
    public static final int height = 150;
    public static final int margin = height + 15;

    private final JTextArea gameName;

    final Game game;

    public GamePanel(final Game game, final int yPos) {
        super(false);

        this.game = game;
        this.gameName = new JTextArea(game.getName());

        setBackground(Color.green);
        setBounds(300, yPos, width, height);

        gameName.setBounds(0, 0, width, height);
        gameName.setEditable(false);
        gameName.setFocusable(false);
        gameName.setFont(new Font("Arial", Font.PLAIN, 30));
        gameName.setLineWrap(true);
        gameName.setBorder(null);
        gameName.setBackground(null);
        gameName.setForeground(Color.black);
        gameName.setOpaque(false);
        add(gameName);
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
        System.out.println(e.getPoint());
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
