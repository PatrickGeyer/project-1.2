package golf.physics;

public class Vector2d {

    public double x;
    public double y;
    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double get_x() {
        return this.x;
    }
    public double get_y() {
        return this.y;
    }

    public Vector2d add (Vector2d a) {
        return new Vector2d(this.get_x() + a.get_x(), this.get_y() + a.get_y());
    }
}