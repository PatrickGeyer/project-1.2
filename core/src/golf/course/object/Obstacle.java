package golf.course.object;
import com.badlogic.gdx.math.Vector3;

public class Obstacle extends GameObject {
    public double restitution = 0.7;
    int xy = 3;
    public Vector3 dimensions = new Vector3(xy, xy, 6);//3 10 3
}