package golf.physics;

import golf.course.object.GameObject;
import golf.course.PuttingCourse;
import golf.course.*;
import com.badlogic.gdx.math.Vector3;

public class Euler implements PhysicsEngine {

    public Vector3 force(GameObject obj, PuttingCourse c, double h) {

        // Friction
        Vector3 friction = new Vector3(
            obj.velocity.len() == 0 ? 0 : (float) (-c.frictionCoefficient * obj.mass * c.g * obj.velocity.x / (double) obj.velocity.len()),
            obj.velocity.len() == 0 ? 0 : (float) (-c.frictionCoefficient * obj.mass * c.g * obj.velocity.y / (double) obj.velocity.len()),
            0
        ); //friction = −𝜇𝑚𝑔𝑣 /‖𝑣‖    where  ‖𝑣‖ = √𝑣𝑥 2 + 𝑣𝑦 2 and 𝜇 is the coefficient of friction
        System.out.println(obj.velocity.len());
        // Gravity
        Vector3 gravity = new Vector3(
            (float) (-obj.mass * c.g * c.height.gradient(obj.position.x, obj.position.y).x),
            (float) (-obj.mass * c.g * c.height.gradient(obj.position.x, obj.position.y).y),
            0
        );// 𝐺 = (−𝑚𝑔ℎ,𝑥(𝑥,𝑦),−𝑚𝑔ℎ,𝑦(𝑥,𝑦))

        // System.out.println(c.height.gradient(obj.position.x, obj.position.y).x + "," + c.height.gradient(obj.position.x, obj.position.y).y);

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
        // System.out.println("Position: " + response[0]);
        // Velocity vector
        Vector3 f = this.force(obj, c, h);

        response[1] = new Vector3(
            (float) (obj.velocity.x + (h * f.x) / obj.mass), 
            (float) (obj.velocity.y + (h * f.y) / obj.mass),
            (float) (obj.velocity.z + (h * f.z) / obj.mass)
        );
        response[2] = f;
        return response;
    }
}