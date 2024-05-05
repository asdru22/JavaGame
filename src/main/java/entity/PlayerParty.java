package entity;

import main.Game;
import main.GamePanel;
import utils.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class PlayerParty {

    private static final int MAX_PARTY_SIZE = 4;
    public List<Playable> characters = new ArrayList<>();
    public int turn = 0;
    public Game game = null;
    public Vector2D pos;
    boolean isOwnTurn = false;

    private final GamePanel gamePanel;

    public PlayerParty(Vector2D pos,GamePanel gamePanel) {
        this.pos = pos;
        this.gamePanel = gamePanel;

    }

    public void addCharacter(Playable e) {
        if (characters.size() != MAX_PARTY_SIZE) characters.add(e);
    }

    public void initialize(Game game) {
        this.game = game;

        Vector2D[] defaultPositions = {
                new Vector2D(0, -160),
                new Vector2D(0, -80),
                new Vector2D(0, 0),
                new Vector2D(0, 80)
        };

        for (int i = 0; i < characters.size(); i++) {
            PlayableEntity e = characters.get(i);
            if (i < defaultPositions.length) initializeCharacter(e, defaultPositions[i]);
            else initializeCharacter(e, new Vector2D());
        }
    }

    private void initializeCharacter(PlayableEntity p, Vector2D offset) {
        p.pos = new Vector2D(pos);
        p.pos.add(offset);
        p.party = this;
        p.originalPosition = new Vector2D(p.pos);
        p.updateCenter();
        p.setParty(this);
    }

    public void update() {
        for (PlayableEntity c : characters) {
            c.update();
        }
    }

    public void draw(Graphics2D g2D) {
        for (PlayableEntity c : characters) {
            c.draw(g2D);
        }
    }

    public Playable getActiveCharacter() {
        return characters.get(turn);
    }

    public void nextTurn() {
        // Old player handling
        Playable oldTurn = characters.get(turn);
        oldTurn.isOwnTurn = false;
        oldTurn.pos = new Vector2D(oldTurn.originalPosition);
        turn++;
        turnCore();
    }

    private void turnCore() {
        int alive = getAliveCharacters();
        if (alive != 0) {
            PlayableEntity e = getNextAliveCharacter();
            if (e != null) {
                e.isOwnTurn = true;
                Vector2D center = new Vector2D((double) gamePanel.getWidth() /2,pos.y);
                e.moveHorizontallyTowards(center,70);
            } else {
                game.nextTurn();
                turn = 0;
                // Update effects
                for (Playable p : characters) {
                    if (!p.getEffects().isEmpty()) p.effectTick();
                }
            }
        } else {
            game.setWinner(getOther());
        }
    }

    public int getOther() {
        int n = game.turn;
        return (n + 1) % 2;
    }

    public PlayableEntity getNextAliveCharacter() {
        while (turn < characters.size()) {
            PlayableEntity character = characters.get(turn);
            if (character.isAlive()) {
                return character;
            }
            turn++;
        }
        return null;
    }

    public void startTurn() {
        turn = 0;
        turnCore();
    }

    public int getAliveCharacters() {
        int alive = 0;
        for (PlayableEntity e : characters) {
            if (e.isAlive()) alive++;
        }
        return alive;
    }
}
