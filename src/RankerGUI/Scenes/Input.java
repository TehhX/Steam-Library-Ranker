package RankerGUI.Scenes;

import RankerGUI.Scene;

import javax.swing.*;
import java.awt.*;

public class Input extends Scene {
    JTextField inputField = new JTextField();

    public Input() {
        super(true);

        setInputField();
    }

    private void setInputField() {
        inputField.setBorder(null);
        inputField.setBackground(null);
        inputField.setForeground(Color.black);
        inputField.setFocusable(true);
        inputField.setBounds(100, 100, 500, 500);
        add(inputField);
    }

    public void addScene() {
        setVisible(true);
        inputField.requestFocusInWindow();
    }

    public void removeScene() {
        setVisible(false);
    }
}