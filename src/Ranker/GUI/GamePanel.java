package Ranker.GUI;

import Ranker.Data.Game;

import java.awt.*;

public class GamePanel extends Panel {
    // Various constants
    public static final int arc = 25;
    public static final int width = 600;
    public static final int height = 150;
    public static final int margin = height + 15;

    final Game game;

    public GamePanel(final Game game, final int yPos) {
        super(true);

        this.game = game;

        setBackground(Color.green);
        setBounds(300, yPos, width, height);
    }

    @Override
    public void paint(Graphics g) {
        g.fillRoundRect(0, 0, width, height, arc, arc);
    }
}
