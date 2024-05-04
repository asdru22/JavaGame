package main;

import javax.swing.*;

public class Main {
    public static GamePanel gamePanel = new GamePanel();
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game");


        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}