import java.awt.Rectangle;

public class test {

    public static void moveTowardsPoint(Rectangle rect, int targetX, int targetY, int amount) {
        int currentX = rect.x;
        int currentY = rect.y;

        // Calculate distance between current position and target point
        int dx = targetX - currentX;
        int dy = targetY - currentY;

        // Calculate angle and move rectangle towards the target point along the x-axis
        double angle = Math.atan2(dy, dx);
        int newX = currentX + (int) (Math.cos(angle) * amount);
        int newY = currentY + (int) (Math.sin(angle) * amount);

        // Update rectangle position
        rect.setLocation(newX, newY);
    }

    // Example usage:
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(50, 50, 50, 50); // Initial position of the rectangle
        int targetX = 200; // Target x-coordinate
        int targetY = 150; // Target y-coordinate
        int amount = 5; // Amount to move towards the target point

        moveTowardsPoint(rectangle, targetX, targetY, amount);

        System.out.println("New Rectangle Position: " + rectangle);
    }
}
