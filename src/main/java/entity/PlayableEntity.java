package entity;

import entity.characters.Assassin;
import entity.characters.Wizard;
import main.GamePanel;
import utils.Rect;
import utils.Vector2D;

import java.awt.*;


public abstract class PlayableEntity extends MovableEntity {

    Vector2D originalPosition = null;
    public boolean isOwnTurn = false;
    PlayerParty party = null;
    private String name;

    public PlayableEntity(PlayableEntity playableEntity, String pathName, Stats stats) {
        super(playableEntity.pos, playableEntity.gamePanel, 4, pathName, stats);
        this.gamePanel = playableEntity.gamePanel;
        this.pos = new Vector2D(playableEntity.pos);
        this.name = pathName;

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
    }

    @Override
    public void onLeftClick(Vector2D pos) {
        if (isAlive()) {
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
        drawCenteredString(g2D, ("Health: " + stats.health), -40);
        if (isOwnTurn) drawCenteredString(g2D, ("----"), 25);
    }

    public abstract void active(PlayableEntity target);

    public abstract void passive(PlayableEntity target);

    private void drawCenteredString(Graphics2D g2D, String text, int verticalOffset) {
        if (text == null || text.isEmpty()) {
            return;
        }

        FontMetrics fm = g2D.getFontMetrics();

        int stringWidth = fm.stringWidth(text);
        int stringHeight = fm.getAscent();

        int x = (int) (pos.x + (size.x - stringWidth) / 2);
        int y = (int) (pos.y + (size.y - stringHeight) / 2 + stringHeight) + verticalOffset;

        g2D.drawString(text, x, y);
    }
}
