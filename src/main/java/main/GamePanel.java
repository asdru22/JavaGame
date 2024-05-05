package main;

import entity.Information;
import io.InputHandler;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    final int originalTileSize = 16;
    final int scale = 3; // 48x48 tile
    public final int tileSize = originalTileSize * scale;
    final int screenColumns = 16, screenRows = 12; // 4x3 screen ratio
    final int screenWidth = tileSize * screenColumns;
    final int screenHeight = tileSize * screenRows;
    final int TARGET_FPS = 60;
    private int FPS = TARGET_FPS;

    // Game state
    public int gameState;
    final int playState = 1;
    final int pauseState = 2;
    final int finishedState = 3;


    private Thread gameThread;
    public InputHandler inputHandler = new InputHandler();
    public Information information = new Information();

    public Game game;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.LIGHT_GRAY  );
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(inputHandler.keyHandler);
        this.addMouseMotionListener(inputHandler.mousePosHandler);
        this.addMouseListener(inputHandler.mouseListenerHandler);
        game = new Game(this);
        gameState = playState;
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

            if (delta >= 1) {
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
        if (gameState == playState) {
            game.update();
        } else if (gameState == pauseState) {
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        game.draw(g2D);
        g2D.drawString("FPS: " + FPS, 0, 10);
        g2D.drawString("Turn: " + game.turn, screenWidth / 2, screenHeight / 2 - 200);
        information.draw(g2D);
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

    public void pause(){
        gameState = pauseState;
    }
    public void resume(){
        gameState = playState;
    }
    public void setFinished(){
        gameState = finishedState;
    }
    public boolean isPaused(){
        return gameState == pauseState;
    }
    public boolean isFinished(){
        return gameState == finishedState;
    }

}
