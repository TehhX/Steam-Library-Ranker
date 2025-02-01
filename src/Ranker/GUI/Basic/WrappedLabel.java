package Ranker.GUI.Basic;

import Ranker.GUI.GamePanel;

import javax.swing.*;
import java.awt.*;

public class WrappedLabel extends JLabel {
    private static final int maxCharsPerLine = 32;

    public WrappedLabel(final String text) {
        super(getWrappedText(text));

        setFont(new Font("Monospaced", Font.PLAIN, 25));
        setForeground(Color.black);
        setBackground(null);
        setOpaque(false);
        setBorder(null);
        setBounds(50, 0, GamePanel.width, GamePanel.height);
    }

    public static String getWrappedText(final String text) {
        final int charCount = text.length();

        if (charCount < maxCharsPerLine)
            return text;

        String wrappedText = "<html>";

        int lastSpace = 0;
        int lastStart = 0;
        for (int i = 0; i < charCount; i++) {
            if (text.charAt(i) == ' ')
                lastSpace = i;

            if (i - lastStart >= maxCharsPerLine) {
                wrappedText += text.substring(lastStart, lastSpace) + "<br>";
                lastStart = lastSpace;
            }
        }

        return wrappedText + text.substring(lastStart) + "</html>";
    }
}