package golf.physics;

import golf.course.object.GameObject;
import golf.course.PuttingCourse;
import golf.course.*;
import com.badlogic.gdx.math.Vector3;

public class EulerSolver implements PhysicsEngine {
    /*
    private double h;
    public void set_step_size(double h) {
        this.h = h;
    }
    */

    public Vector3 force(GameObject obj, PuttingCourse c, double h) {

        Vector2d gradient = c.height.gradient(obj.position.x, obj.position.y);
        // Friction
        Vector3 friction = new Vector3(
            obj.velocity.len() == 0 ? 0 : (float) (-c.frictionCoefficient * obj.mass * c.g * obj.velocity.x / (double) obj.velocity.len()),
            obj.velocity.len() == 0 ? 0 : (float) (-c.frictionCoefficient * obj.mass * c.g * obj.velocity.y / (double) obj.velocity.len()),
            0
        );
        
        // Gravity
        Vector3 gravity = new Vector3(
            (float) (-obj.mass * c.g * gradient.x),
            (float) (-obj.mass * c.g * gradient.y),
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
            (float) (obj.position.z + h * obj.velocity.z)
        );

        // Force vector
        Vector3 f = this.force(obj, c, h);

        // Velocity vector
        response[1] = new Vector3(
            (float) (obj.velocity.x + (h * f.x) / obj.mass), 
            (float) (obj.velocity.y + (h * f.y) / obj.mass),
            (float) (obj.velocity.z + (h * f.z) / obj.mass)
        );
        response[2] = f;
        return response;
    }
}
