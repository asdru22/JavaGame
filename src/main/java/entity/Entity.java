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
    public GamePanel gamePanel;
    public BufferedImage texture;

    Entity(Vector2D pos,GamePanel gamePanel){
        this.pos = pos;
        this.gamePanel = gamePanel;
    }

    public abstract void update(ArrayList<Entity> entities);
    public void draw(Graphics2D g2D) {
        g2D.drawImage(texture, (int)pos.x, (int)pos.y, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public void setTexture(String texturePath) {
        try {
            texture = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/assets/" + texturePath + ".png")));
        } catch (IOException e) {
            System.err.println("Image "+texturePath+" doesn't exist");
            throw new RuntimeException(e);
        }
    }
    public double getDistanceFrom(Entity other) {
        double dx = pos.x - other.pos.x;
        double dy = pos.y - other.pos.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
