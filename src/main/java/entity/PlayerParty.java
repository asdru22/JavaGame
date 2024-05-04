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

    public PlayerParty(Vector2D pos) {
        this.pos = pos;
    }

    public void addCharacter(PlayableEntity e) {
        if (characters.size() != MAX_PARTY_SIZE) characters.add(e);
    }


    public void initialize(Game game) {
        this.game = game;
        characters.get(0).isOwnTurn = true;

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

    public void nextTurn() {
        PlayableEntity c = characters.get(turn);
        c.isOwnTurn = false;

        int aliveCharacters = 0;
        for(PlayableEntity e : characters){
            if(e.isAlive()) aliveCharacters++;
        }

        turn++;
        if (turn < aliveCharacters) {
            // go to next player if current one is dead
            if(!c.isAlive()) {
                c.isOwnTurn = false;
                nextTurn();
            } else {
                c.isOwnTurn = true;
            }
        } else {
            // If characters turn is over, give control to other characters;
            game.nextTurn();
            turn = 0;
        }
    }

    public PlayableEntity getActiveCharacter() {
        return characters.get(turn);
    }
}
