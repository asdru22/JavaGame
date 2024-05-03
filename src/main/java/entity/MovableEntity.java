package entity;

import main.GamePanel;
import utils.Vector2D;

public abstract class MovableEntity extends Entity {
    public int speed;
    public Vector2D velocity = new Vector2D();

    MovableEntity(Vector2D pos, Vector2D size, GamePanel gamePanel, int speed) {
        super(pos, size, gamePanel);
        this.speed = speed;
    }

    MovableEntity(Vector2D pos, GamePanel gamePanel, int speed, String texturePath) {
        super(pos, gamePanel,texturePath);
        this.speed = speed;
    }

    public void moveTowards(Entity target) {
        if (!intersects(target)) {
            // Calculate the direction vector towards the target entity
            Vector2D direction = new Vector2D(target.pos.x - pos.x, target.pos.y - pos.y);
            direction.normalize();
            // Set the velocity vector based on the normalized direction and speed
            velocity.x = direction.x * speed;
            velocity.y = direction.y * speed;
            // Update the position based on the velocity
            pos.x += velocity.x;
            pos.y += velocity.y;

        } else {
            this.velocity = new Vector2D();
            onCollision(target);
        }
    }
}
