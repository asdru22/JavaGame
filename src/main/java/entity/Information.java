package entity;

import java.awt.*;

import static main.Main.gamePanel;

public class Information {
    public Playable playable;

    public Information() {
    }

    public void draw(Graphics2D g2D) {
        if (gamePanel.isPaused()) {
            // Set rendering hints for better text quality
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // Set font
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
            int screenWidth = gamePanel.getWidth(); // Replace getWidth() with the actual width of your screen
            int screenHeight = gamePanel.getHeight(); // Replace getHeight() with the actual height of your screen

            // Calculate position to center text
            int x = (screenWidth - textWidth) / 2;
            int y = (screenHeight - textHeight) / 2;

// Fill black background
            g2D.setColor(new Color(0, 0, 0, 200));
            g2D.fillRect(x, y, textWidth + 10, textHeight + 10);

// Set color back to white for text
            g2D.setColor(Color.WHITE);

// Reset y position for centering
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
    }
}
