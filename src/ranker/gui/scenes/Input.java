package ranker.gui.scenes;

import ranker.data.Intake;
import ranker.gui.SceneID;
import ranker.gui.Window;
import ranker.gui.basic.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Input extends Scene implements ActionListener, SceneChangeActions {
    private final JTextField inputField = new JTextField();

    public Input() {
        inputField.setFont(new Font("Monospaced", Font.PLAIN, 15));
        inputField.setBorder(null);
        inputField.setBackground(null);
        inputField.setForeground(Color.white);
        inputField.setFocusable(true);
        inputField.setBounds(100, 100, 500, 500);

        add(inputField);
    }

    @Override
    public void addActions() {
        inputField.setText("Enter SteamID64");
        inputField.selectAll();
        SwingUtilities.invokeLater(inputField::requestFocusInWindow);
        inputField.addActionListener(this);
    }

    @Override
    public void removeActions() {
        inputField.removeActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ignored) {
        // Use my own SteamID if debug is true.
        final boolean debug = true;

        @SuppressWarnings("ConstantValue")
        final String id = (debug ? "76561198284660364" : inputField.getText().trim());

        Window.changeScene(SceneID.Loading);
        SwingUtilities.invokeLater(() -> runIntake(id));
    }

    /// Runs the intake method from the Intake class with a passed SteamID64, displays errors if they occur.
    private void runIntake(final String id) {
        try {
            Intake.downloadUserLibrary(id);
            Window.changeScene(SceneID.Rank);
        }
        catch (RuntimeException re) {
            Window.changeScene(SceneID.Input);

            // Show an error popup
            JOptionPane.showMessageDialog(null, re.getMessage());
            inputField.requestFocusInWindow();
            inputField.selectAll();
        }
    }
}