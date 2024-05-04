package entity;

import main.Game;
import utils.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerParty {

    private static final int MAX_PARTY_SIZE = 4;
    public List<PlayableEntity> characters = new ArrayList<>();
    public int turn = 0;
    public Game game = null;
    public Vector2D pos;
    boolean isOwnTurn = false;

    public PlayerParty(Vector2D pos) {
        this.pos = pos;
    }

    public void addCharacter(PlayableEntity e) {
        if (characters.size() != MAX_PARTY_SIZE) characters.add(e);
    }

    public void initialize(Game game) {
        this.game = game;

        Vector2D[] defaultPositions = {
                new Vector2D(-20, -75),
                new Vector2D(20, -25),
                new Vector2D(-20, 25),
                new Vector2D(20, 75)
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

    public PlayableEntity getActiveCharacter() {
        return characters.get(turn);
    }

    public void nextTurn() {
        // Old player handling
        PlayableEntity oldTurn = characters.get(turn);
        oldTurn.isOwnTurn = false;
        turnCore();
    }

    public void startTurn() {
        isOwnTurn = true;
        turn = 0;
        int alive = getAliveCharacters();
        if (alive == 0) {
            int w = getOther();
            game.winner = w;
            System.out.println("Party " + w + " has won!");
        } else {
            if (turn < alive) {
                handleCharacters(alive);
            } else {
                game.nextTurn();
            }
        }
    }

    private void turnCore() {
        int aliveCharacters = getAliveCharacters();
        if (aliveCharacters == 0) {
            int w = getOther();
            game.winner = w;
            System.out.println("Party " + w + " has won!");
        } else {
            turn++;
            if (turn < aliveCharacters) {
                handleCharacters(aliveCharacters);
            } else {
                game.nextTurn();
            }
        }
    }

    private void handleCharacters(int alive) {
        PlayableEntity newTurn;
        for (int i = 0; i < alive; i++) {
            newTurn = characters.get(turn);
            if (newTurn.isAlive()) {
                newTurn.isOwnTurn = true;
                System.out.println("Party:" + game.turn + ", character" + turn);
                break;
            }
        }
    }

    private int getOther() {
        int n = game.turn;
        return (n + 1) % 2;
    }

    public int getAliveCharacters() {
        int aliveCharacters = 0;
        for (PlayableEntity e : characters) {
            if (e.isAlive()) aliveCharacters++;
        }
        return aliveCharacters;
    }
}
