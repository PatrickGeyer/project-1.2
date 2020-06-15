package golf.physics;

import golf.course.*;
import golf.course.object.GameObject;
import com.badlogic.gdx.math.Vector3;
import java.io.*;

public class PhysicsEngine implements Serializable {

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

        // Wind
        // gradient of function will be force

        Vector3 wind = c.wind.gradient(
            (float) obj.position.x,
            (float) obj.position.y,
            c.elapsed
        );
        wind = wind.scl((float) c.windIntensity);
        wind = wind.clamp((float) c.windIntensity, (float) c.windIntensity);
        wind.z = 0;

        return friction.add(gravity);
    }

    public Vector3 getAcceleration(GameObject obj, PuttingCourse c, double h) {
        return this.force(obj, c, h).scl((float) ( 1 / (obj.mass)));
    }


    public Vector3[] solve(GameObject obj, PuttingCourse c, double h) {
        return new Vector3[1];
    }
}
