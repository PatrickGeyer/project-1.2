package golf.course.object;

import golf.physics.*;

public class Object {
    public float friction = 0;        // coefficient of friction
    public float bounciness = 0;      // coefficient of restitution
    public int x = 0;
    public int y = 0;
    public int z = 0;
    public Vector2d velocity = new Vector2d(0,0);
    public Vector2d acceleration = new Vector2d(0,0);
}