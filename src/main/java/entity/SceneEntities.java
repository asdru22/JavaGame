package entity;

import main.GamePanel;
import utils.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


public class SceneEntities {
    Player player;
    public ArrayList<Enemy> enemies = new ArrayList<>();

    public SceneEntities(GamePanel gamePanel){
        player = new Player(gamePanel,gamePanel.inputHandler.keyHandler,new Vector2D());
    }

    public void update(){
        player.update();
        for(Entity e : enemies){
            e.update();
        }
    }

    public void draw(Graphics2D g2D){
        if (player.render) player.draw(g2D);

        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Entity e = iterator.next();
            if (e.render) {
                e.draw(g2D);
            } else {
                iterator.remove();
            }
        }
    }
}
