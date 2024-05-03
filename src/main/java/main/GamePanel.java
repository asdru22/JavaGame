package main;

import entity.Enemy;
import entity.Entity;
import entity.Player;
import utils.Vector2D;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;
    // 48x48 tile
    public final int tileSize = originalTileSize * scale;
    // 4x3 screen ratio
    final int screenColumns = 16, screenRows = 12;
    final int screenWidth = tileSize * screenColumns;
    final int screenHeight = tileSize * screenRows;
    final int FPS = 60;

    Thread gameThread;
    InputHandler inputHandler = new InputHandler();
    ArrayList<Entity> entities = new ArrayList<>();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.CYAN);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(inputHandler.keyHandler);
        this.addMouseMotionListener(inputHandler.mouseHandler);

        populate();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // FPS implementation
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
        entities.add(new Player(this,inputHandler.keyHandler,new Vector2D(100,100)));
        entities.add(new Enemy(this,new Vector2D(100,200)));
    }
}
