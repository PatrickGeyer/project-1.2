package golf.course.object;

import golf.physics.*;
import com.badlogic.gdx.math.Vector3;

public class GameObject {
    public double friction = 0;        // coefficient of friction
    public double bounciness = 0;      // coefficient of restitution
    public int x = 0;
    public int y = 0;
    public int z = 0;
    public Vector3 velocity = new Vector3(0, 0, 0);
    public Vector3 position = new Vector3(0, 0, 0);
    public Vector3 acceleration = new Vector3(0, 0, 0);
    public double mass = 1;
}