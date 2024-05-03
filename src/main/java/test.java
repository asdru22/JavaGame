import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class test extends JPanel {
    private Image texture;

    public test() throws IOException {
        // Load the texture image
        texture = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/assets/player.png")));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the texture onto the panel
        g.drawImage(texture, 100, 100, 200, 200, this); // Draw the texture at position (100, 100) with size 200x200
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Texture Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        test panel = new test();
        frame.add(panel);

        frame.setVisible(true);
    }
}
