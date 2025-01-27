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
    public final Point regularPos;

    private final WrappedLabel gameLabel;
    private final int gameIndex;

    public GamePanel(final int gameIndex) {
        super(false);

        this.gameIndex = gameIndex;
        regularPos = new Point(leftMargin, 10 + topMargin * gameIndex);

        setPosition(regularPos);

        gameLabel = new WrappedLabel(GameList.nameOf(gameIndex));

        add(gameLabel);
        add(new Panel(false, g -> {
            g.setColor(new Color(157, 42, 234));
            g.fillRoundRect(0, 0, width, height, arc, arc);
        }));
    }

    public void update() {
        final String normName = GameList.nameOf(gameIndex);
        final String wrapName = WrappedLabel.getWrappedText(normName);

        setPosition(regularPos);

        gameLabel.setText(wrapName);
    }

    public void setPosition(final int x, final int y) {
        setBounds(x, y, GamePanel.width, GamePanel.height);
    }

    public void setPosition(final Point point) {
        setPosition(point.x, point.y);
    }
}
