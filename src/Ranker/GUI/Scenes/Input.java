package Ranker.GUI.Scenes;

import Ranker.Data.Intake;
import Ranker.GUI.Basic.Scene;
import Ranker.GUI.SceneID;
import Ranker.GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Input extends Scene implements ActionListener, SceneChangeActions {
    private final JTextField inputField = new JTextField();

    public Input() {
        super(true);

        inputField.setFont(new Font("Monospaced", Font.PLAIN, 15));
        inputField.setBorder(null);
        inputField.setBackground(null);
        inputField.setForeground(Color.black);
        inputField.setFocusable(true);
        inputField.setBounds(100, 100, 500, 500);

        add(inputField);
    }

    private void showError(final String msg) {
        JOptionPane.showMessageDialog(null, msg);
        inputField.requestFocusInWindow();
        inputField.selectAll();
    }

    @Override
    public void addActions() {
        SwingUtilities.invokeLater(inputField::requestFocusInWindow);
        inputField.addActionListener(this);
    }

    @Override
    public void removeActions() {
        inputField.setText("");
        inputField.removeActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ignored) {
        final boolean debug = true;
        String id;

        if (debug)
            id = "76561198284660364";
        else
            id = inputField.getText().trim();

        if (id.length() != 17) {
            showError("Incorrect SteamID64. Check your input.");
            return;
        }

        Window.changeScene(SceneID.Loading);

        // invokeLater allows the loading screen to be displayed before loading the game library into GameList.
        // Therefore, all code below will execute only after the loading screen is shown.
        SwingUtilities.invokeLater(() -> runIntake(id));
    }

    private void runIntake(final String id) {
        final int returnCode = Intake.downloadUserLibrary(id);

        if (returnCode == 0)
            Window.changeScene(SceneID.Rank);

        else if (returnCode == 1) {
            Window.changeScene(SceneID.Input);
            showError("Server/Steam error. Try again later.");
        }

        else if (returnCode == 2) {
            Window.changeScene(SceneID.Input);
            showError("Incorrect SteamID64. Check your input.");
        }
    }
}