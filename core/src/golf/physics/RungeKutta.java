package golf.physics;

import golf.course.object.GameObject;
import golf.course.PuttingCourse;
import golf.course.*;
import com.badlogic.gdx.math.Vector3;

public class RungeKutta implements PhysicsEngine {

    public Vector3 force(GameObject obj, PuttingCourse c, double h) {

        Vector2d gradient = c.height.gradient(obj.position.x, obj.position.y);
        
        Vector3 friction = new Vector3(
            obj.velocity.len() == 0 ? 0 : (float) (-c.frictionCoefficient * obj.mass * c.g * obj.velocity.x / (double) obj.velocity.len()),
            obj.velocity.len() == 0 ? 0 : (float) (-c.frictionCoefficient * obj.mass * c.g * obj.velocity.y / (double) obj.velocity.len()),
            0
        );
        
        Vector3 gravity = new Vector3(
            (float) (-obj.mass * c.g * gradient.x),
            (float) (-obj.mass * c.g * gradient.y),
            0
        );

        return friction.add(gravity);
    }

    public Vector3 getAcceleration(Vector3 position, Vector3 velocity, PuttingCourse c) {
        
        Vector2d gradient = c.height.gradient(position.x, position.y);

        Vector3 acceleration = new Vector3();
        acceleration.x = (float) ((-c.g * gradient.x) - c.frictionCoefficient * c.g * velocity.x / (Math.sqrt(velocity.x*velocity.x + velocity.y*velocity.y)));
        acceleration.y = (float) ((-c.g * gradient.y) - c.frictionCoefficient * c.g * velocity.x / (Math.sqrt(velocity.x*velocity.x + velocity.y*velocity.y)));
        
        return acceleration;
    }

    @Override
    public Vector3[] solve(GameObject obj, PuttingCourse c, double h) {
        Vector3 k1v, k1x, k2v, k2x, k3v, k3x, k4v, k4x;

        float s = (float) h;
       
        k1v = getAcceleration(obj.position, obj.velocity, c).scl(s);
        k1x = obj.velocity.scl(s);

        k2v = getAcceleration(obj.position.add(k1x.scl(0.5f)), obj.velocity.add(k1v.scl(0.5f)), c).scl(s);
        k2x = (obj.velocity.add(k1v.scl(0.5f)).scl(s));

        k3v = getAcceleration(obj.position.add((k1x.scl(-0.5f)).add(k2x.scl(0.5f))), obj.velocity.add((k1v.scl(-0.5f)).add((k2v.scl(0.5f)))), c).scl(s);
        k3x = (obj.velocity.add((k1v.scl(-0.5f)).add((k2v.scl(0.5f))).scl(s)));

        k4v = getAcceleration(obj.position.add((k2x.scl(-0.5f)).add(k3x)), obj.velocity.add((k2v.scl(-0.5f)).add(k3v)), c).scl(s);
        k4x = (obj.velocity.add((k2v.scl(-0.5f)).add(k3x))).scl(s);

        Vector3[] response = new Vector3[3];

        // Position vector
        response[0] = (k1x.add((k2x.scl(2))).add((k3x.scl(2))).add(k4x)).scl(1/6);

        // Velocity vector
        Vector3 f = this.force(obj, c, h);

        response[1] = (k1v.add((k2v.scl(2))).add((k3v.scl(2))).add(k4v)).scl(1/6);

        response[2] = f;
        return response;
        
    }
}