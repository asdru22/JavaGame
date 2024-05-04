package entity;

import entity.characters.Assassin;
import entity.characters.Wizard;
import main.GamePanel;
import utils.Rect;
import utils.Vector2D;

import javax.swing.*;
import java.awt.*;


public abstract class PlayableEntity extends MovableEntity {

    Vector2D originalPosition = null;
    boolean isOwnTurn = false;
    PlayerParty party = null;
    private JLabel healthbar = new JLabel("hello exy");
    private String name;

    public PlayableEntity(PlayableEntity playableEntity, String pathName, Stats stats) {
        super(playableEntity.pos, playableEntity.gamePanel, 4, pathName, stats);
        this.gamePanel = playableEntity.gamePanel;
        this.pos = new Vector2D(playableEntity.pos);
        this.name = pathName;
        gamePanel.add(healthbar);
    }

    public PlayableEntity(GamePanel gamePanel, String pathName, Stats stats) {
        super(new Vector2D(), gamePanel, 4, pathName, stats);
        this.name = pathName;
    }

    public PlayableEntity(GamePanel gamePanel, Vector2D pos, String pathName, Stats stats) {
        super(pos, gamePanel, 4, pathName, stats);
    }


    @Override
    public void update() {
        healthbar.setText("Health: " + stats.health);
    }

    @Override
    public void onLeftClick(Vector2D pos) {
        if(isAlive()) {
            PlayableEntity executor = gamePanel.game.getActiveParty().getActiveCharacter();
            if (party.characters.contains(executor)) executor.passive(this);
            else executor.active(this);
            gamePanel.game.getActiveParty().nextTurn();
        }
    }

    @Override
    public void onCollision(Rect r) {

    }

    public enum Characters {
        ASSASSIN, WIZARD
    }

    public static PlayableEntity getCharacter(Characters c, GamePanel gamePanel) {
        if (c == Characters.ASSASSIN) return new Assassin(gamePanel);
        if (c == Characters.WIZARD) return new Wizard(gamePanel);

        else return null;
    }

    public String getName() {
        return name;
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(texture, (int) pos.x, (int) pos.y, (int) size.x, (int) size.y, null);
        g2D.drawString("Health: " + stats.health, (int) pos.x, (int) (pos.y - 10));

    }

    public abstract void active(PlayableEntity target);

    public abstract void passive(PlayableEntity target);
}
