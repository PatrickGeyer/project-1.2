package golf.physics;

import golf.course.object.Object;
import golf.course.Course;

public class Euler {

    public static Vector2d force(Object obj, Course c, int h) {

        // Friction
        Vector2d friction = new Vector2d(
            -c.frictionCoefficient * obj.mass * c.gravity * obj.velocity[0] / obj.velocity.magnitude(),
            -c.frictionCoefficient * obj.mass * c.gravity * obj.velocity[1] / obj.velocity.magnitude()
        );
        // Gravity
        Vector2d gravity = new Vector2d(
            −obj.mass * c.gravity * c.height.gradient(obj.x, obj.y)[0],
            −obj.mass * c.gravity * c.height.gradient(obj.x, obj.y)[1])
        );

        return friction.add(gravity);
    }

    public static Vector2d[] solve(Object obj, Course c, int h) {
        Vector2d[] response = new Vector2d[2];
        // Position vector
        response[0] = new Vector2d(
            obj.position[0] + h * obj.velocity[0], 
            obj.position[1] + h * obj.velocity[1]
        );
        // Velocity vector
        Vector2d f = Euler.force(obj, c, h);
        response[1] = new Vector2d(
            obj.velocity[0] + (h * f[0]) / obj.mass, 
            obj.velocity[1] + (h * f[1]) / obj.mass
        );
        return response;
    }
}