package golf.physics;

import golf.course.object.GameObject;
import golf.course.PuttingCourse;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector2;


public class EulerSolver implements PhysicsEngine {
    protected double stepSize;
    protected Vector2d positionVector;
    protected Vector2d velocityVector;
    protected Vector2d accelerationVector;

    public EulerSolver(double stepSize) {
        this.stepSize = stepSize;
    }

    public void setStepSize(double h) {
        this.stepSize = h;
    }

    public void setPositionVector(Vector2d p) {
        this.positionVector = p;
    }

    public void setVelocityVector(Vector2d v) {
        this.velocityVector = v;
    }

    public void setAccelerationVector(Vector2d a) {
        this.accelerationVector = a;
    }

    protected void approximatePosition() {
        double dX = this.stepSize * this.velocityVector.get_x();
        double dY = this.stepSize * this.velocityVector.get_y();
        positionVector.addX(dX);
        positionVector.addY(dY); 
    }            

    private void approximateVelocity() {
        double dX = this.stepSize * this.accelerationVector.get_x();
        double dY = this.stepSize * this.accelerationVector.get_y();
        velocityVector.addX(dX);
        velocityVector.addY(dY);
    }

    public void approximate() {
        approximatePosition();
        approximateVelocity();
    }

    public Vector3[] solve(GameObject obj, PuttingCourse c, double h) {
        return new Vector3[1];
    }
}