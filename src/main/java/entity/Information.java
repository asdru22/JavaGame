package entity;

import java.awt.*;

import static main.Main.gamePanel;

public class Information {
    public Playable playable;
    public Information(){
    }

    public void draw(Graphics2D g2D){
        if(gamePanel.isPaused()){
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

            // Calculate position to center text
            int x = (gamePanel.getWidth() - textWidth) / 2;
            int y = (gamePanel.getHeight() - textHeight) / 2;

            // Fill black background
            g2D.setColor(Color.BLACK);
            g2D.fillRect(x, y, (int)(textWidth*1.1), (int)(textHeight*1.1));

            // Set color back to white for text
            g2D.setColor(Color.WHITE);

            // Draw each line separately
            for (String line : lines) {
                g2D.drawString(line, x, y += g2D.getFontMetrics().getHeight());
            }
        }
    }
}
