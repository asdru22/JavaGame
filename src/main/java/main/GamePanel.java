package main;

import entity.Enemy;
import entity.Entity;
import entity.Player;
import utils.Vector2D;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    // screen settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    // 4x3 screen ratio
    final int screenColumns = 16;
    final int screenRows = 12;
    final int screenWidth = tileSize * screenColumns;
    final int screenHeight = tileSize * screenRows;
    final int FPS = 60;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    ArrayList<Entity> entities = new ArrayList<>();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.CYAN);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);

        populate();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 10e8 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // update information
                update();
                // draw the screen with updated information
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 10e8) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        for(Entity e : entities){
            e.update(entities);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        for(Entity e : entities){
            e.draw(g2D);
        }

        g2D.dispose();
    }

    private void populate(){
        entities.add(new Player(this,keyHandler,new Vector2D(100,100)));
        entities.add(new Enemy(this,new Vector2D(100,200)));
    }
}
