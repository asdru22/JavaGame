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
        turn++;
        turnCore();
    }

    private void turnCore() {
        System.out.println("> Party: " + game.turn + ", turn:" + turn);
        PlayableEntity e = getNextAliveCharacter();
        if (e != null) e.isOwnTurn = true;
        else {
            System.out.println("next turn");
            game.nextTurn();
            turn = 0;
        }
        System.out.println("< Party: " + game.turn + ", turn:" + turn);
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

    public int getAliveCharacters(){
        int alive = 0;
        for (PlayableEntity e : characters){
            if(e.isAlive()) alive++;
        }
        return alive;
    }

}
