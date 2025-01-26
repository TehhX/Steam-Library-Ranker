package Ranker.GUI.Basic;

import Ranker.GUI.GamePanel;

import javax.swing.*;
import java.awt.*;

public class WrappedLabel extends JLabel {
    public WrappedLabel(final String text) {
        super(text);

        setFont(new Font("Arial", Font.PLAIN, 30));
        setForeground(Color.black);
        setBackground(null);
        setOpaque(false);
        setBorder(null);
        setBounds(50, 0, GamePanel.width, GamePanel.height);
    }
}