package entity;

import main.GamePanel;
import io.KeyHandler;
import utils.Rect;
import utils.Vector2D;


public class Player extends MovableEntity {
    public KeyHandler keyHandler;

    public Player(Player player) {
        super(player.pos, player.gamePanel, 4,"player");
        this.gamePanel = player.gamePanel;
        this.keyHandler = player.keyHandler;
        // Create a copy of the position vector
        this.pos = new Vector2D(player.pos);
    }

    public Player(GamePanel gamePanel, KeyHandler keyHandler, Vector2D pos) {
        super(pos, gamePanel, 4,"player");
        this.keyHandler = keyHandler;
    }

    @Override
    public void update() {
        Vector2D direction = Vector2D.calculateDirection(keyHandler.upPressed, keyHandler.downPressed, keyHandler.leftPressed, keyHandler.rightPressed);
        pos.add(direction.multiply(speed));

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onCollision(Rect r) {

    }
}
