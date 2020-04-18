package golf.physics;

import golf.course.object.GameObject;
import golf.course.PuttingCourse;
import golf.course.*;
import com.badlogic.gdx.math.Vector3;

public class Euler implements PhysicsEngine {

    public Vector3 force(GameObject obj, PuttingCourse c, double h) {

        // Friction
        Vector3 friction = new Vector3(
            (float) (-c.frictionCoefficient * obj.mass * c.g * obj.velocity.x / (double) obj.velocity.len()),
            (float) (-c.frictionCoefficient * obj.mass * c.g * obj.velocity.y / (double) obj.velocity.len()),
            0
        );
        // Gravity
        Vector3 gravity = new Vector3(
            (float) (-obj.mass * c.g * c.height.gradient(obj.x, obj.y).x),
            (float) (-obj.mass * c.g * c.height.gradient(obj.x, obj.y).y),
            0
        );

        return friction.add(gravity);
    }

    public Vector3[] solve(GameObject obj, PuttingCourse c, double h) {
        Vector3[] response = new Vector3[3];
        // Position vector
        response[0] = new Vector3(
            (float) (obj.position.x + h * obj.velocity.x), 
            (float) (obj.position.y + h * obj.velocity.y),
            (float) (obj.position.z)
        );
        // Velocity vector
        Vector3 f = this.force(obj, c, h);
        response[1] = new Vector3(
            (float) (obj.velocity.x + (h * f.x) / obj.mass), 
            (float) (obj.velocity.y + (h * f.y) / obj.mass),
            (float) (obj.velocity.z)
        );
        response[2] = f;
        return response;
    }
}