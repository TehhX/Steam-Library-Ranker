package Ranker.GUI.Basic;

import javax.swing.*;
import java.awt.*;

public class WrappedLabel extends JLabel {
    public WrappedLabel(final String text, final int yPos, final int xSize) {
        super(text);

        setFont(new Font("Arial", Font.PLAIN, 30));
        setForeground(Color.black);
        setBackground(null);
        setOpaque(false);
        setBorder(null);
        setBounds(200, yPos, xSize, getPreferredSize().height);
    }
}