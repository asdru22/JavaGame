package entity;

import utils.Vector2D;

import java.awt.*;

public class PlayerParty {

    public PlayableEntity[] characters;
    public int turn = 0;

    public Vector2D pos;

    public PlayerParty(Vector2D pos, PlayableEntity[] playableEntities){
        this.characters = playableEntities;
        this.pos = pos;

        initializeCharacters();
    }

    private void initializeCharacters(){
        initializeCharacter(characters[0], new Vector2D(-20,-75));
        initializeCharacter(characters[1], new Vector2D(20,-25));
        initializeCharacter(characters[2], new Vector2D(-20,25));
        initializeCharacter(characters[3], new Vector2D(20,75));

        System.out.println("current pos: "+characters[0].pos);

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
        for(PlayableEntity c : characters){
            if(c!=null) c.update();
        }
    }

    public void draw(Graphics2D g2D){
        for(PlayableEntity c : characters){
            if(c!=null) c.draw(g2D);
        }
    }

    public void nextTurn(){
        do{
            turn+=1;
        } while (characters[turn] == null);

    }
}
