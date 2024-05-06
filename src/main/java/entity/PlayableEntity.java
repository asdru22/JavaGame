package entity;

import entity.characters.Assassin;
import entity.characters.Brute;
import entity.characters.Spike;
import entity.characters.Wizard;
import main.GamePanel;
import utils.Rect;
import utils.Vector2D;

import java.awt.*;
import java.util.List;


public abstract class PlayableEntity extends MovableEntity {

    Vector2D originalPosition = null;
    public boolean isOwnTurn = false;
    PlayerParty party = null;
    private String name;

    public PlayableEntity(PlayableEntity playableEntity, String pathName, Stats stats) {
        super(playableEntity.pos, playableEntity.gamePanel, 10, pathName, stats);
        this.gamePanel = playableEntity.gamePanel;
        this.pos = new Vector2D(playableEntity.pos);
        this.name = pathName;

    }

    public PlayableEntity(GamePanel gamePanel, String pathName, Stats stats) {
        super(new Vector2D(), gamePanel, 20, pathName, stats);
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
            if (party.characters.contains(executor)) {
                executor.passive((Playable) this);
                executor.logPassive();
            } else {
                executor.active((Playable) this);
                executor.logActive();
            }
            gamePanel.game.getActiveParty().nextTurn();
        }
    }

    private void logActive() {
        Playable p = (Playable) this;
        System.out.println(p.getName() + " used " + p.active.getName()+"!");
    }

    private void logPassive() {
        Playable p = (Playable) this;
        System.out.println(p.getName() + " used " + p.passive.getName()+"!");
    }

    @Override
    public void onRightClick(Vector2D pos) {
        gamePanel.pause();
        gamePanel.information.playable = (Playable) this;
    }

    @Override
    public void onCollision(Rect r) {

    }

    public enum Characters {
        ASSASSIN, WIZARD, BRUTE,SPIKE
    }

    public static Playable getCharacter(Characters c, GamePanel gamePanel) {
        if (c == Characters.ASSASSIN) return new Assassin(gamePanel);
        else if (c == Characters.WIZARD) return new Wizard(gamePanel);
        else if (c == Characters.BRUTE) return new Brute(gamePanel);
        else if (c == Characters.SPIKE) return new Spike(gamePanel);

        else return null;
    }

    public String getName() {
        StringBuilder result = new StringBuilder();
        boolean makeUpper = true;

        for (char c : name.toCharArray()) {
            if (c == '_') {
                result.append(' '); // Replace underscore with space
                makeUpper = true;
            } else if (makeUpper) {
                result.append(Character.toUpperCase(c));
                makeUpper = false;
            } else {
                result.append(Character.toLowerCase(c));
            }
        }

        return result.toString();
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(texture, (int) pos.x, (int) pos.y, (int) size.x, (int) size.y, null);
        drawCenteredString(g2D, ("Health: " + stats.health), -40);
        if (isOwnTurn) drawCenteredString(g2D, ("--------"), 25);
    }

    public abstract void active(Playable target);

    public abstract void passive(Playable target);

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

    public PlayerParty getParty() {
        return party;
    }

    public void setParty(PlayerParty party) {
        this.party = party;
    }

    public Playable getAbove(){
        List<Playable> c = this.getParty().characters;
        int n = c.indexOf(this);
        if(n>=1){
            return c.get(n-1);
        } else return null;
    }
    public Playable getBelow(){
        List<Playable> c = this.getParty().characters;
        int n = c.indexOf(this);
        if(n<c.size()-1){
            return c.get(n+1);
        } else return null;
    }
}
