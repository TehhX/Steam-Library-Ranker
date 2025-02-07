package ranker.gui.basic;

import ranker.gui.Window;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    final PanelPainter panelPainter;

    public Panel(final boolean opaque, final PanelPainter panelPainter) {
        super(null);

        this.panelPainter = panelPainter;

        setBackground(null);
        setOpaque(opaque);
        setSize(ranker.gui.Window.FRAME_SIZE_X, Window.FRAME_SIZE_Y);
    }

    public Panel(final boolean opaque) {
        this(opaque, null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (panelPainter != null)
            panelPainter.paintPanel(g);
    }
}