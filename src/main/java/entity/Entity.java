package entity;

import main.GamePanel;
import utils.Rect;
import utils.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Entity extends Rect {
    public GamePanel gamePanel;
    public BufferedImage texture;
    public Stats stats;
    private boolean alive = true;

    Entity(Vector2D pos, GamePanel gamePanel, String texturePath,Stats stats) {
        super(pos);
        this.gamePanel = gamePanel;
        setTexture(texturePath);
        this.center = new Vector2D(pos.x + size.x / 2, pos.y + size.y / 2);
        this.stats = stats;
    }

    Entity(Vector2D pos, Vector2D size, GamePanel gamePanel) {
        super(pos, size);
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

    public abstract void onLeftClick(Vector2D pos);

    public boolean isAlive(){
        return alive;
    }
    public void setDead(){
        alive = false;
    }
}
