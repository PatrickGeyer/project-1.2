package golf.physics;
import golf.physics.*;

interface Function2d {
	double DELTA = 0.00001;
	public double evaluate(Vector2d p);
	public Vector2d gradient(Vector2d p);
}
