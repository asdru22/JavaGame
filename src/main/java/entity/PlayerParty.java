package entity;

import utils.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerParty {

    private static final int MAX_PARTY_SIZE = 4;

    public List<PlayableEntity> party = new ArrayList<>();
    public int turn = 0;

    public Vector2D pos;

    public PlayerParty(Vector2D pos){

        this.pos = pos;

    }

    public void addCharacter(PlayableEntity e){
        if(party.size()==MAX_PARTY_SIZE) return;
        party.add(e);
        }


    public void initializeCharacters(){
        initializeCharacter(party.get(0), new Vector2D(-20,-75));
        initializeCharacter(party.get(1), new Vector2D(20,-25));
        initializeCharacter(party.get(2), new Vector2D(-20,25));
        initializeCharacter(party.get(3), new Vector2D(20,75));

        System.out.println("current pos: "+party.get(0).pos);

    }

    private void initializeCharacter(PlayableEntity p, Vector2D offset){
        if(p!=null){
            p.pos = pos;
            p.pos.x += offset.x;
            p.pos.y += offset.y;
            p.party = this;
        }
    }

    public void update(){
        for(PlayableEntity c : party){
            if(c!=null) c.update();
        }
    }

    public void draw(Graphics2D g2D){
        for(PlayableEntity c : party){
            if(c!=null) c.draw(g2D);
        }
    }

    public void nextTurn(){
        do{
            turn+=1;
        } while (party.get(turn) == null);

    }
}
