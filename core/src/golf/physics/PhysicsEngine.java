package golf.physics;

import golf.course.*;
import golf.course.object.Object;

public interface PhysicsEngine {
    void setPositionVector(Vector2d v);
    void setVelocityVector(Vector2d v);
    void setAccelerationVector(Vector2d v);
    void approximate();
}
