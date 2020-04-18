package golf.physics;

import golf.course.*;
import golf.course.object.GameObject;
import com.badlogic.gdx.math.Vector3;

public interface PhysicsEngine {
    // void setPositionVector(Vector2d v);
    // void setVelocityVector(Vector2d v);
    // void setAccelerationVector(Vector2d v);
    // void approximate();

    Vector3[] solve(GameObject obj, PuttingCourse c, double h);
}
