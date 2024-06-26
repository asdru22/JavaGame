package utils;


public abstract class Rect {
    public Vector2D pos;
    public Vector2D size = new Vector2D();
    public Vector2D center = new Vector2D();
    public boolean render = true;

    public Rect(Vector2D pos) {
        this.pos = pos;
        updateCenter();
    }

    public Rect(Vector2D pos, Vector2D size) {
        this.pos = pos;
        this.size = size;
    }

    public double getDistanceFrom(Rect r) {
        return getDistanceFrom(r.center);
    }

    public double getDistanceFrom(Vector2D point) {
        double dx = pos.x - point.x;
        double dy = pos.y - point.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void delete() {
        render = false;
    }

    public boolean contains(Vector2D point) {
        return point.x >= pos.x && point.x <= pos.x + size.x && point.y >= pos.y && point.y <= pos.y + size.y;
    }

    public boolean intersects(Rect r) {
        double tw = size.x, th = size.y, rw = r.size.x, rh = r.size.y;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        double tx = this.pos.x, ty = this.pos.y, rx = r.pos.x, ry = r.pos.y;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

    public abstract void onCollision(Rect r);

    public void updateCenter() {
        center.x = (pos.x + size.x) / 2;
        center.y = (pos.y + size.y) / 2;
    }

    public void moveHorizontallyTowards(Vector2D target,int amount) {
        double dx = target.x - pos.x;

        // Calculate the direction of movement (left or right)
        int direction = dx > 0 ? 1 : -1;

        if (Math.abs(dx) > amount) {
            pos.x += amount * direction; // Move at maximum speed towards the target
        } else {
            pos.x += dx;
        }
        updateCenter();
    }

}
