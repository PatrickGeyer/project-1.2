package golf.physics;

import golf.course.object.GameObject;
import golf.course.PuttingCourse;

public class Euler {

    public static Vector2d force(GameObject obj, PuttingCourse c, int h) {

        // Friction
        Vector2d friction = new Vector2d(
            -c.frictionCoefficient * obj.mass * c.g * obj.velocity.get_x() / obj.velocity.magnitude(),
            -c.frictionCoefficient * obj.mass * c.g * obj.velocity.get_y() / obj.velocity.magnitude()
        );
        // Gravity
        Vector2d gravity = new Vector2d(
            -obj.mass * c.g * c.height.gradient(obj.x, obj.y).get_x(),
            -obj.mass * c.g * c.height.gradient(obj.x, obj.y).get_y()
        );

        return friction.add(gravity);
    }

    public static Vector2d[] solve(GameObject obj, PuttingCourse c, int h) {
        Vector2d[] response = new Vector2d[2];
        // Position vector
        response[0] = new Vector2d(
            obj.position.get_x() + h * obj.velocity.get_x(), 
            obj.position.get_y() + h * obj.velocity.get_y()
        );
        // Velocity vector
        Vector2d f = Euler.force(obj, c, h);
        response[1] = new Vector2d(
            obj.velocity.get_x() + (h * f.get_x()) / obj.mass, 
            obj.velocity.get_y() + (h * f.get_y()) / obj.mass
        );
        return response;
    }
}