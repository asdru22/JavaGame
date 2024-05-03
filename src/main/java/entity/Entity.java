package entity;

import main.GamePanel;
import utils.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Entity {
    public Vector2D pos;
    public Vector2D size = new Vector2D();
    public GamePanel gamePanel;
    public BufferedImage texture;

    Entity(Vector2D pos, GamePanel gamePanel, String texturePath) {
        this.pos = pos;
        this.gamePanel = gamePanel;
        setTexture(texturePath);
    }

    Entity(Vector2D pos, Vector2D size, GamePanel gamePanel) {
        this.pos = pos;
        this.size = size;
        this.gamePanel = gamePanel;
    }

    public abstract void update();

    public void draw(Graphics2D g2D) {
        g2D.drawImage(texture, (int) pos.x, (int) pos.y, (int) size.x, (int) size.y, null);
    }

    public void setTexture(String texturePath) {
        try {
            texture = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/assets/" + texturePath + ".png")));
            size.x = texture.getWidth();
            size.y = texture.getHeight();
        } catch (IOException e) {
            System.err.println("Image " + texturePath + " doesn't exist");
            throw new RuntimeException(e);
        }
    }

    public double getDistanceFrom(Entity other) {
        double dx = pos.x - other.pos.x;
        double dy = pos.y - other.pos.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public boolean contains(Vector2D point) {
        return point.x >= pos.x && point.x <= pos.x + size.x && point.y >= pos.y && point.y <= pos.y + size.y;
    }

    public boolean intersects(Entity e) {
        double tw = size.x, th = size.y, rw = e.size.x, rh = e.size.y;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        double tx = this.pos.x, ty = this.pos.y, rx = e.pos.x, ry = e.pos.y;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

    public abstract void onLeftClick();
}
