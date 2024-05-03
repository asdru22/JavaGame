package entity;

import main.GamePanel;
import utils.Rect;
import utils.Vector2D;

public class Enemy extends MovableEntity {

    public Enemy(GamePanel gamePanel, Vector2D pos) {
        super(pos, gamePanel, 2,"enemy");
    }

    @Override
    public void update() {
        moveTowards(gamePanel.sceneEntities.player);
    }

    @Override
    public void onLeftClick() {
        delete();
        gamePanel.addEnemy(new Enemy(gamePanel,new Vector2D().randomize(gamePanel)));
    }

    @Override
    public void onCollision(Rect r){
        if(r instanceof Player){
            r.delete();
            try {
                gamePanel.wait((long)10e12);
            } catch (InterruptedException e) {
                System.out.println("Game over");
                throw new RuntimeException(e);
            }
        }
    }
}
