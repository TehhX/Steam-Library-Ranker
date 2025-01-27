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
        @SuppressWarnings("ConstantConditionalExpression")

        // Ternary boolean changes debug mode on and off.
        final String id = (true ? "76561198284660364" : inputField.getText().trim());

        Window.changeScene(SceneID.Loading);
        SwingUtilities.invokeLater(() -> runIntake(id));
    }

    private void runIntake(final String id) {
        try {
            Intake.downloadUserLibrary(id);
            Window.changeScene(SceneID.Rank);
        }
        catch (RuntimeException re) {
            Window.changeScene(SceneID.Input);
            showError(re.getMessage());
        }
    }
}