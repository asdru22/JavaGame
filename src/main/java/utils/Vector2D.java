package utils;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D(){
        this.x = 0;
        this.y = 0;
    }
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2D calculateDirection(boolean north, boolean south, boolean west, boolean east) {
        // Calculate the x and y components of the movement direction
        double dx = 0;
        double dy = 0;
        if (north) dy -= 1;
        if (south) dy += 1;
        if (west) dx -= 1;
        if (east) dx += 1;

        // Normalize the vector to ensure its magnitude is between 0 and 1
        return normalize(dx, dy);
    }

    public Vector2D add(Vector2D vec2) {
        return new Vector2D(this.x += vec2.x, this.y += vec2.y);
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y;
    }

    public Vector2D multiply(double amount) {
        return new Vector2D(this.x * amount, this.y * amount);
    }

    public static Vector2D normalize(double dx, double dy) {
        double magnitude = getMagnitude(dx, dy);
        if (magnitude > 0) {
            dx /= magnitude;
            dy /= magnitude;
        }
        return new Vector2D(dx, dy);
    }

    public void normalize() {
        double magnitude = getMagnitude();
        if (magnitude > 0) {
            x /= magnitude;
            y /= magnitude;
        }
    }

    public double getMagnitude() {
        return getMagnitude(x, y);
    }

    public static double getMagnitude(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }
}
