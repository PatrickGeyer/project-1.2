package golf.physics;

import golf.course.*;
import golf.course.object.GameObject;
import com.badlogic.gdx.math.Vector3;
import java.io.*;

public class PhysicsEngine implements Serializable {

    public Vector3 force(GameObject obj, PuttingCourse c) {
        return this.force(obj, c, c.windDuration == 0 ? 0 : 1 - Math.min(c.elapsed/c.windDuration, 1));
    }

    public Vector3 force(GameObject obj, PuttingCourse c, double windFactor) {

        Vector2d gradient = c.height.gradient(obj.position.x, obj.position.y);
        // Friction
        Vector3 friction = new Vector3(
            obj.velocity.len() == 0 ? 0 : (float) (-c.frictionCoefficient * obj.mass * c.g * obj.velocity.x / (double) obj.velocity.len()),
            obj.velocity.len() == 0 ? 0 : (float) (-c.frictionCoefficient * obj.mass * c.g * obj.velocity.y / (double) obj.velocity.len()),
            0
        );
        //Sand pit 
        if(obj.position.x>10 && obj.position.x<17 && obj.position.y>19){
             friction = new Vector3(
                    obj.velocity.len() == 0 ? 0 : (float) (-c.frictionCoefficientSand * obj.mass * c.g * obj.velocity.x / (double) obj.velocity.len()),
                    obj.velocity.len() == 0 ? 0 : (float) (-c.frictionCoefficientSand * obj.mass * c.g * obj.velocity.y / (double) obj.velocity.len()),
                    0
            );
        }
        
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
        wind.scl((float) windFactor);

        return friction.add(gravity).add(wind);
    }

    public Vector3 getAcceleration(GameObject obj, PuttingCourse c, double h) {
        return this.force(obj, c).scl((float) ( 1 / (obj.mass)));
    }


    public Vector3[] solve(GameObject obj, PuttingCourse c, double h) {
        return new Vector3[1];
    }
}
