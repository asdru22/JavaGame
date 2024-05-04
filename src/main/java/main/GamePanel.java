package main;

import io.InputHandler;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    final int originalTileSize = 16;
    final int scale = 3;
    // 48x48 tile
    public final int tileSize = originalTileSize * scale;
    // 4x3 screen ratio
    final int screenColumns = 16, screenRows = 12;
    final int screenWidth = tileSize * screenColumns;
    final int screenHeight = tileSize * screenRows;
    final int TARGET_FPS = 60;
    private int FPS = TARGET_FPS;
    Thread gameThread;
    public InputHandler inputHandler = new InputHandler();
    public Game game = new Game(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.CYAN);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(inputHandler.keyHandler);
        this.addMouseMotionListener(inputHandler.mousePosHandler);
        this.addMouseListener(inputHandler.mouseListenerHandler);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // TARGET_FPS implementation
        double drawInterval = 10e8 / TARGET_FPS;
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

            if (delta >= 1 ) {
                mainLoop();
                delta--;
                drawCount++;
            }

            if (timer >= 10e8) {
                FPS = drawCount;
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        game.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        game.draw(g2D);
        g2D.drawString("FPS: "+FPS,0,10);
        g2D.drawString("Turn: "+game.turn,screenWidth/2,screenHeight/2-100);

        g2D.dispose();
    }

    public void mainLoop() {
        // update information
        update();
        // draw the screen with updated information
        repaint();
    }

    public int getWidth() {
        return screenWidth;
    }

    public int getHeight() {
        return screenHeight;
    }


}
