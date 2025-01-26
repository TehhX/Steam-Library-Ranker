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

        inputField.setBorder(null);
        inputField.setBackground(null);
        inputField.setForeground(Color.black);
        inputField.setFocusable(true);
        inputField.setBounds(100, 100, 500, 500);
        inputField.addActionListener(this);

        addChangeActions(this);
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
    }

    @Override
    public void removeActions() {
        inputField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent ignored) {
        // To use debug SteamID
        final String id = "76561198284660364";

        // To get SteamID from field.
        //final String id = inputField.getText().trim();
        //
        //if (id.length() != 17) {
        //    showError("Incorrect SteamID64. Check your input.");
        //    return;
        //}

        Window.changeScene(SceneID.Loading);

        // invokeLater allows the loading screen to be displayed before loading the game library into GameList.
        // Therefore, all code below will execute only after the loading screen is shown.
        SwingUtilities.invokeLater(() -> {
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
        });
    }
}