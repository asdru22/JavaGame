package entity;

import main.GamePanel;
import utils.Rect;
import utils.Vector2D;


public class PlayableEntity extends MovableEntity {

    Vector2D originalPosition = null;
    boolean isOwnTurn = false;
    PlayerParty party = null;

    private String name;

    public PlayableEntity(PlayableEntity playableEntity, String pathName, Stats stats) {
        super(playableEntity.pos, playableEntity.gamePanel, 4,pathName, stats);
        this.gamePanel = playableEntity.gamePanel;
        this.pos = new Vector2D(playableEntity.pos);
        this.name = pathName;
    }

    public PlayableEntity(GamePanel gamePanel, String pathName, Stats stats) {
        super(new Vector2D(), gamePanel, 4,pathName, stats);
        this.name = pathName;
    }

    public PlayableEntity(GamePanel gamePanel, Vector2D pos, String pathName, Stats stats) {
        super(pos, gamePanel, 4,pathName, stats);
    }


    @Override
    public void update() {
    }

    @Override
    public void onLeftClick(Vector2D pos) {
        PlayableEntity executor = gamePanel.game.getActiveParty().getActiveCharacter();
        if(party.party.contains(executor)) System.out.println("passive");
        else System.out.println("active");
        //System.out.println("i was clicked by "+gamePanel.game.getActiveParty().getActiveCharacter().getName());
        //System.out.println("party: "+gamePanel.game.turn+", turn: "+gamePanel.game.getActiveParty().turn);
        gamePanel.game.getActiveParty().nextTurn();
    }

    @Override
    public void onCollision(Rect r) {

    }

    public enum Characters{
        PLAYER, ENEMY
    }

    public static PlayableEntity getCharacter(Characters c,GamePanel gamePanel){
        if(c==Characters.PLAYER) return new PlayableEntity(gamePanel, "player",new Stats(100,20));
        if(c==Characters.ENEMY) return new PlayableEntity(gamePanel, "enemy",new Stats(63,4));

        else return null;
    }

    public String getName(){
        return name;
    }
}
