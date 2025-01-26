package Ranker.GUI;

import Ranker.Data.Game;
import Ranker.GUI.Basic.Panel;
import Ranker.GUI.Basic.WrappedLabel;

import java.awt.*;

public class GamePanel extends Panel {
    // Various constants
    public static final int arc = 40;
    public static final int width = 600;
    public static final int height = 150;
    public static final int topMargin = height + 15;
    public static final int leftMargin = (Window.FRAME_SIZE_X - width) / 2;

    private Game game;

    public GamePanel(final Game game, final int yPos) {
        super(false);

        this.game = game;

        setBounds(leftMargin, yPos, width, height);

        add(new WrappedLabel(game.getName()));

        add(new Panel(false, g -> {
            g.setColor(new Color(114, 9, 183));
            g.fillRoundRect(0, 0, width, height, arc, arc);
        }));
    }

    @Override
    public String toString() {
        return game.getName();
    }
}
