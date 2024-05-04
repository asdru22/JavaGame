package entity;

import main.GamePanel;
import utils.Rect;
import utils.Vector2D;


public class PlayableEntity extends MovableEntity {

    boolean isOwnTurn = false;
    PlayerParty party = null;

    public PlayableEntity(PlayableEntity playableEntity, String pathName, Stats stats) {
        super(playableEntity.pos, playableEntity.gamePanel, 4,pathName, stats);
        this.gamePanel = playableEntity.gamePanel;
        this.pos = new Vector2D(playableEntity.pos);
    }

    public PlayableEntity(GamePanel gamePanel, String pathName, Stats stats) {
        super(new Vector2D(), gamePanel, 4,pathName, stats);
    }

    public PlayableEntity(GamePanel gamePanel, Vector2D pos, String pathName, Stats stats) {
        super(pos, gamePanel, 4,pathName, stats);
    }


    @Override
    public void update() {
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onCollision(Rect r) {

    }

    public enum Characters{
        PLAYER
    }

    public static PlayableEntity getCharacter(Characters c,GamePanel gamePanel){
        if(c==Characters.PLAYER) return new PlayableEntity(gamePanel, "player",new Stats(100,20));
        else return null;
    }
}
