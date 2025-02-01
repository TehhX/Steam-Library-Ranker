package ranker.gui.scenes;

import ranker.data.GameList;
import ranker.gui.basic.Scene;
import ranker.gui.SceneID;
import ranker.gui.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Output extends Scene implements ActionListener {
    private JButton rankButton = new JButton("Rank");

    private JButton printPairedButton = new JButton("Print Paired");
    private JButton printPlainButton = new JButton("Print Plain");
    private JButton printHTMLButton = new JButton("Print HTML");
    private JButton printSteamButton = new JButton("Print Steam Formatted");

    public Output() {
        super(true);

        rankButton.setBounds(100, 100, 140, 30);
        rankButton.addActionListener(this);
        add(rankButton);

        printPairedButton.setBounds(100, 140, 140, 30);
        printPairedButton.addActionListener(this);
        add(printPairedButton);

        printPlainButton.setBounds(100, 180, 140, 30);
        printPlainButton.addActionListener(this);
        add(printPlainButton);

        printHTMLButton.setBounds(100, 220, 140, 30);
        printHTMLButton.addActionListener(this);
        add(printHTMLButton);

        printSteamButton.setBounds(100, 260, 140, 30);
        printSteamButton.addActionListener(this);
        add(printSteamButton);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == rankButton)
            Window.changeScene(SceneID.Rank);

        else if (ae.getSource() == printPairedButton)
            System.out.println(GameList.getListPaired());

        else if (ae.getSource() == printPlainButton)
            System.out.println(GameList.getListPlain());

        else if (ae.getSource() == printHTMLButton)
            System.out.println(GameList.getListHTML());

        else if (ae.getSource() == printSteamButton)
            System.out.println(GameList.getListSteam());
    }
}