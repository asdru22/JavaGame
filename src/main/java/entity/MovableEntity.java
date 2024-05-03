package entity;

import main.GamePanel;
import utils.Vector2D;

public abstract class MovableEntity extends Entity {
    public int speed;
    public Vector2D velocity = new Vector2D(0, 0);

    MovableEntity(Vector2D pos, GamePanel gamePanel, int speed) {
        super(pos, gamePanel);
        this.speed = speed;
    }

    public void moveTowards(Entity target) {
        double distance = getDistanceFrom(target);
        if (distance >= 5) {

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
            this.velocity = new Vector2D(0, 0);
        }
    }
}
