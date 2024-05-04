package main;

import entity.PlayableEntity;
import entity.PlayerParty;
import utils.Vector2D;

import java.awt.*;

public class Game {
    public PlayerParty[] playerParty = new PlayerParty[2];
    public int turn = 0;
    private final GamePanel gamePanel;

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
        PlayableEntity p1 = PlayableEntity.getCharacter(PlayableEntity.Characters.ASSASSIN, gamePanel);
        PlayableEntity p2 = PlayableEntity.getCharacter(PlayableEntity.Characters.WIZARD, gamePanel);
        PlayableEntity p3 = PlayableEntity.getCharacter(PlayableEntity.Characters.WIZARD, gamePanel);

        playerParty[0] = new PlayerParty(new Vector2D(screenCenter.x - 100, screenCenter.y));
        playerParty[0].addCharacter(p1);
        playerParty[0].initialize(this);

        playerParty[1] = new PlayerParty(new Vector2D(screenCenter.x + 100, screenCenter.y));
        playerParty[1].addCharacter(p2);
        playerParty[1].addCharacter(p3);

        playerParty[1].initialize(this);
    }

    public PlayerParty getActiveParty(){
        return playerParty[turn];
    }

    public void nextTurn(){
        turn = (turn + 1) % 2;
    }
}
