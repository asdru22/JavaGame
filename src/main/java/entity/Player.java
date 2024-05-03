package entity;

import main.GamePanel;
import main.KeyHandler;
import utils.Vector2D;

import java.util.ArrayList;

public class Player extends MovableEntity {
    public KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler, Vector2D pos) {
        super(pos, gamePanel, 4);
        this.keyHandler = keyHandler;
        setTexture("player");
    }

    @Override
    public void update(ArrayList<Entity> entities) {
        Vector2D direction = Vector2D.calculateDirection(keyHandler.upPressed, keyHandler.downPressed, keyHandler.leftPressed, keyHandler.rightPressed);
        pos.add(direction.multiply(speed));
    }
}
