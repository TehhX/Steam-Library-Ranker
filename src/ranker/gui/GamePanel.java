package ranker.gui;

import ranker.data.GameList;
import ranker.gui.basic.Panel;
import ranker.gui.basic.WrappedLabel;

import java.awt.*;

public class GamePanel extends Panel {
    // Various global GamePanel constants
    public static final int arc = 40;
    public static final int width = 600;
    public static final int height = 75;
    public static final int topMargin = height + 10;
    public static final int leftMargin = (Window.FRAME_SIZE_X - width) / 2;

    // Various instance-wide GamePanel constants
    private final Point regularPos;
    private final int gameIndex;
    private Color backgroundColor;
    private final WrappedLabel gameLabel;

    public GamePanel(final int gameIndex) {
        super(false);

        this.gameIndex = gameIndex;
        regularPos = new Point(leftMargin, 10 + topMargin * gameIndex);
        gameLabel = new WrappedLabel(GameList.nameOf(gameIndex));
        backgroundColor = getBackgroundColor(true);

        setPosition(regularPos);
        add(gameLabel);
        add(new Panel(false, g -> {
            g.setColor(backgroundColor);
            g.fillRoundRect(0, 0, width, height, arc, arc);
        }));
    }

    public void setTransparent() {
        backgroundColor = getBackgroundColor(false);
    }

    public void update() {
        setPosition(regularPos);
        backgroundColor = getBackgroundColor(true);
        gameLabel.setText(GameList.nameOf(gameIndex));
    }

    private Color getBackgroundColor(final boolean opaque) {
        final boolean isEven = gameIndex % 2 == 0;

        if (isEven)
            if (opaque)
                return new Color(33, 222, 173, 100);
            else
                return new Color(33, 222, 173, 65);

        else
            if (opaque)
                return new Color(225, 52, 30, 100);
            else
                return new Color(225, 52, 30, 65);
    }

    public void setPosition(final int x, final int y) {
        setBounds(x, y, GamePanel.width, GamePanel.height);
    }

    public void setPosition(final Point p) {
        setPosition(p.x, p.y);
    }

    public int getRegularY() {
        return regularPos.y;
    }
}
