package Ranker.GUI;

import Ranker.Data.GameList;
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

    private WrappedLabel gameLabel;
    private int gameIndex;

    public GamePanel(final int gameIndex, final int yPos) {
        super(false);

        this.gameIndex = gameIndex;

        setBounds(leftMargin, yPos, width, height);

        gameLabel = new WrappedLabel(GameList.nameOf(gameIndex));

        add(gameLabel);
        add(new Panel(false, g -> {
            g.setColor(new Color(114, 9, 183));
            g.fillRoundRect(0, 0, width, height, arc, arc);
        }));
    }

    public void update() {
        gameLabel.setText(GameList.nameOf(gameIndex));
    }
}
