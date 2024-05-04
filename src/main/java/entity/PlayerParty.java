package entity;

import main.Game;
import utils.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerParty {

    private static final int MAX_PARTY_SIZE = 4;

    public List<PlayableEntity> party = new ArrayList<>();
    public int turn = 0;

    public Game game = null;

    public Vector2D pos;

    public PlayerParty(Vector2D pos) {
        this.pos = pos;
    }

    public void addCharacter(PlayableEntity e) {
        if (party.size() != MAX_PARTY_SIZE) party.add(e);
    }


    public void initialize(Game game) {
        this.game = game;
        party.get(0).isOwnTurn = true;

        Vector2D[] defaultPositions = {
                new Vector2D(-20, -75),
                new Vector2D(20, -25),
                new Vector2D(-20, 25),
                new Vector2D(20, 75)
        };

        for (int i = 0; i < party.size(); i++) {
            PlayableEntity e = party.get(i);
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
        for (PlayableEntity c : party) {
            c.update();
        }
    }

    public void draw(Graphics2D g2D) {
        for (PlayableEntity c : party) {
            c.draw(g2D);
        }
    }

    public void nextTurn() {
        party.get(turn).isOwnTurn = false;
        turn++;
        if (turn < party.size()) {
            party.get(turn).isOwnTurn = true;
        } else {
            // If party turn is over, give control to other party;
            game.turn = (game.turn + 1) % 2;
            turn = 0;
        }
    }

    public PlayableEntity getActiveCharacter() {
        return party.get(turn);
    }
}
