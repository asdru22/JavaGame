package entity;

import main.GamePanel;
import utils.Vector2D;

import java.util.ArrayList;

public class Enemy extends MovableEntity {

    public Enemy(GamePanel gamePanel, Vector2D pos) {
        super(pos, gamePanel,2);
        setTexture("enemy");
    }

    @Override
    public void update(ArrayList<Entity> entities) {
        moveTowards(entities.get(0));
    }

}
