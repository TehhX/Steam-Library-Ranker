package ranker.gui.basic;

import ranker.gui.GamePanel;

import javax.swing.*;
import java.awt.*;

public class WrappedLabel extends JLabel {
    private static final int maxCharsPerLine = 32;

    public WrappedLabel(final String text) {
        super();

        setFont(new Font("Monospaced", Font.BOLD, 25));
        setForeground(Color.black);
        setBackground(null);
        setOpaque(false);
        setBorder(null);
        setBounds(50, 0, GamePanel.width, GamePanel.height);
        setText(text);
    }

    @Override
    public void setText(final String unwrappedText) {
        final int charCount = unwrappedText.length();

        if (charCount < maxCharsPerLine) {
            super.setText(unwrappedText);
            return;
        }

        String wrappedText = "<html>";

        int lastSpace = 0;
        int lastStart = 0;
        for (int i = 0; i < charCount; i++) {
            if (unwrappedText.charAt(i) == ' ')
                lastSpace = i;

            if (i - lastStart >= maxCharsPerLine) {
                wrappedText += unwrappedText.substring(lastStart, lastSpace) + "<br>";
                lastStart = lastSpace;
            }
        }

        super.setText(wrappedText + unwrappedText.substring(lastStart) + "</html>");
    }
}