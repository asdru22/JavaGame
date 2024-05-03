package entity;

import main.GamePanel;
import utils.Vector2D;

public class Enemy extends MovableEntity {

    public Enemy(GamePanel gamePanel, Vector2D pos) {
        super(pos, gamePanel, 2,"enemy");
    }

    @Override
    public void update() {
        moveTowards(gamePanel.entities.get(0));
    }

    @Override
    public void onLeftClick() {
    }
}
