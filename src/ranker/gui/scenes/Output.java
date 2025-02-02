package ranker.gui.scenes;

import ranker.data.GameList;
import ranker.gui.SceneID;
import ranker.gui.Window;
import ranker.gui.basic.Scene;

import javax.swing.*;

public class Output extends Scene {
    public Output() {
    // Rank button and setup
        JButton rankButton = new JButton("Back to Rank");
        rankButton.setBounds(100, 100, 180, 30);
        rankButton.addActionListener(e -> Window.changeScene(SceneID.Rank));
        add(rankButton);

    // Paired printing button and setup
        JButton printPairedButton = new JButton("Print Paired");
        printPairedButton.setBounds(100, 180, 180, 30);
        printPairedButton.addActionListener(e -> System.out.println(GameList.getListPaired()));
        add(printPairedButton);

    // Plain printing button and setup
        JButton printPlainButton = new JButton("Print Plain");
        printPlainButton.setBounds(100, 220, 180, 30);
        printPlainButton.addActionListener(e -> System.out.println(GameList.getListPlain()));
        add(printPlainButton);

    // HTML printing button and setup
        JButton printHTMLButton = new JButton("Print HTML");
        printHTMLButton.setBounds(100, 260, 180, 30);
        printHTMLButton.addActionListener(e -> System.out.println(GameList.getListHTML()));
        add(printHTMLButton);

    // Steam formatted printing button and setup
        JButton printSteamButton = new JButton("Print Steam Formatted");
        printSteamButton.setBounds(100, 300, 180, 30);
        printSteamButton.addActionListener(e -> System.out.println(GameList.getListSteam()));
        add(printSteamButton);
    }
}