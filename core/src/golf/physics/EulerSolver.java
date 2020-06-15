package golf.physics;

import golf.course.object.GameObject;
import golf.course.PuttingCourse;
import golf.course.*;
import com.badlogic.gdx.math.Vector3;

public class EulerSolver extends PhysicsEngine {

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

        response[1] = new Vector3(
            (float) (obj.velocity.x + (h * f.x) / obj.mass), 
            (float) (obj.velocity.y + (h * f.y) / obj.mass),
            (float) (obj.velocity.z + (h * f.z) / obj.mass)
        );
        response[2] = f;
                System.out.println(response[1]);

        return response;
    }
}
