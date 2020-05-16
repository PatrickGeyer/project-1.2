package golf.physics;

import golf.course.object.GameObject;
import golf.course.PuttingCourse;
import golf.course.*;
import com.badlogic.gdx.math.Vector3;

public class VerletSolver implements PhysicsEngine {
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

        // Force vector
        Vector3 f = this.force(obj, c, h);

        // Position vector
        //p(t+h) = p(t) + h*v + 1/2*h^2*F/m
        response[0] = new Vector3(
                (float) (obj.position.x + (obj.velocity.x *h) +(h * h * 0.5 * f.x) / obj.mass),
                (float) (obj.position.y + (obj.velocity.y *h) +(h * h * 0.5 * f.y) / obj.mass),
                (0)
        );

        // Velocity vector
        //V(t+h) = V(t) + h*F/m
        response[1] = new Vector3(
                (float) (obj.velocity.x + (h * f.x) / obj.mass),
                (float) (obj.velocity.y + (h * f.y) / obj.mass),
                (0f)
        );
        
        response[2] = f;
        return response;
    }
}
