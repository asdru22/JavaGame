package main;

import entity.*;
import utils.Vector2D;

import java.awt.*;
import java.util.Random;

public class Game {
    public PlayerParty[] playerParty = new PlayerParty[2];
    public int turn;
    private final GamePanel gamePanel;
    public int winner = -1;

    public Game(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        populate();
    }

    public void update() {
        playerParty[0].update();
        playerParty[1].update();
    }

    public void draw(Graphics2D g2D) {
        playerParty[0].draw(g2D);
        playerParty[1].draw(g2D);
    }

    private void populate() {

        Vector2D screenCenter = new Vector2D((double) gamePanel.getWidth() / 2, (double) gamePanel.getHeight() / 2);

        playerParty[0] = new PlayerParty(new Vector2D(screenCenter.x - 150, screenCenter.y),gamePanel);
        playerParty[0].addCharacter(Playable.get(AvailableCharacters.ECLIPSE,gamePanel));
        playerParty[0].initialize(this);

        playerParty[1] = new PlayerParty(new Vector2D(screenCenter.x + 150, screenCenter.y),gamePanel);
        playerParty[1].addCharacter(Playable.get(AvailableCharacters.BRUTE,gamePanel));
        playerParty[1].addCharacter(Playable.get(AvailableCharacters.BRUTE,gamePanel));
        playerParty[1].initialize(this);

        // 50% chance for each party to start
        turn = new Random().nextInt(2);
        playerParty[turn].startTurn();
    }

    public PlayerParty getActiveParty() {
        return playerParty[turn];
    }

    public void nextTurn() {
        turn = (turn + 1) % 2;
        playerParty[turn].startTurn();
    }

    public void setWinner(int p) {
        winner = p;
        gamePanel.setFinished();
    }
}
