package Ranker.GUI.Scenes;

import Ranker.Data.Intake;
import Ranker.GUI.Scene;
import Ranker.GUI.SceneID;
import Ranker.GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Input extends Scene implements ActionListener {
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
        inputField.addActionListener(this);
        add(inputField);
    }

    private void showError() {
        JOptionPane.showMessageDialog(null, "Enter a valid SteamID64 and try again.");
        inputField.setText("");
        inputField.requestFocusInWindow();
    }

    @Override
    public void addScene() {
        setVisible(true);
        inputField.requestFocusInWindow();
    }

    @Override
    public void removeScene() {
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent ignored) {
        final String id = inputField.getText().trim();

        if (id.length() != 17) {
            showError();
            return;
        }

        Window.changeScene(SceneID.Loading);

        // invokeLater allows the loading screen to be displayed before loading the game library into GameList.
        // Therefore, all code below will execute only after the loading screen is shown.
        SwingUtilities.invokeLater(() -> {
            final int returnCode = Intake.inputXML(id);

            if (returnCode == 0)
                Window.changeScene(SceneID.Output);
            else if (returnCode == 1) {
                Window.changeScene(SceneID.Input);
                showError();
            }
        });
    }
}