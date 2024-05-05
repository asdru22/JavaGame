package entity;

import java.awt.*;

import static main.Main.gamePanel;

public class Information {
    public Playable playable;

    public Information() {
    }

    public void draw(Graphics2D g2D) {
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (gamePanel.isPaused()) {
            paused(g2D);
        } else if (gamePanel.isFinished()) {
            finished(g2D);
        }
    }
    private void paused(Graphics2D g2D){
        Font font = new Font("Arial", Font.PLAIN, 14);
        g2D.setFont(font);

        String text = playable.getInfo();

        String[] lines = text.split("\n");

        // Calculate text width and height
        int textWidth = 0;
        int textHeight = lines.length * g2D.getFontMetrics().getHeight();
        for (String line : lines) {
            int lineWidth = g2D.getFontMetrics().stringWidth(line);
            if (lineWidth > textWidth) {
                textWidth = lineWidth;
            }
        }

        // Get the dimensions of the screen
        int screenWidth = gamePanel.getWidth();
        int screenHeight = gamePanel.getHeight();

        int x = (screenWidth - textWidth) / 2;
        int y = (screenHeight - textHeight) / 2;

        g2D.setColor(new Color(0, 0, 0, 200));
        g2D.fillRect(x, y, textWidth + 10, textHeight + 10);

        g2D.setColor(Color.WHITE);

        y += g2D.getFontMetrics().getAscent();

        // Draw the first line centered
        String firstLine = lines[0];
        int firstLineX = x + (textWidth - g2D.getFontMetrics().stringWidth(firstLine)) / 2;
        g2D.drawString(firstLine, firstLineX, y);

        // Draw subsequent lines left-aligned
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            y += g2D.getFontMetrics().getHeight();
            g2D.drawString(line, x, y);
        }
    }
    private void finished(Graphics2D g2D){
        Font font = new Font("Arial", Font.PLAIN, 30);
        g2D.setFont(font);

        String text = gamePanel.game.winner+" wins!";


        // Calculate text width and height
        int textWidth = g2D.getFontMetrics().stringWidth(text);
        int textHeight = g2D.getFontMetrics().getHeight();


        // Get the dimensions of the screen
        int screenWidth = gamePanel.getWidth();
        int screenHeight = gamePanel.getHeight();

        int x = (screenWidth - textWidth) / 2;
        int y = (screenHeight - textHeight) / 2;

        g2D.setColor(new Color(0, 0, 0, 200));
        g2D.fillRect(x, y, textWidth + 10, textHeight + 10);

        g2D.setColor(Color.WHITE);

        y += g2D.getFontMetrics().getAscent();

        int firstLineX = x + (textWidth - g2D.getFontMetrics().stringWidth(text)) / 2;
        g2D.drawString(text, firstLineX, y);
    }
}
