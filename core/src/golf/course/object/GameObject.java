package golf.course.object;

import golf.physics.*;
import com.badlogic.gdx.math.Vector3;
import java.io.Serializable;

public class GameObject implements Serializable {
    public double friction = 0;        // coefficient of friction
    public double bounciness = 0;      // coefficient of restitution
    public Vector3 velocity = new Vector3(0, 0, 0);
    public Vector3 position = new Vector3(0, 0, 0);
    public Vector3 acceleration = new Vector3(0, 0, 0);
    public double mass = 0.1;
    public boolean moving = false;
}