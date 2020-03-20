package golf.physics;

import golf.course.object.Object;
import golf.course.Course;

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

    /*
    public static Vector2d force(Object obj, Course c, int h) {

        // Friction
        Vector2d friction = new Vector2d(0.0,0.0);
        // Gravity
        Vector2d gravity = new Vector2d(0.0,0.0);

        return friction.add(gravity);
    }

    public static Vector2d[] solve(Object obj, Course c, int h) {
        Vector2d[] response = new Vector2d[2];
        // Position vector
        // response[0] = new Vector2d(obj.position[0] + h * obj.velocity[0], obj.position[1] + h * obj.velocity[1]);
        // Velocity vector
        Vector2d f = Euler.force(obj, c, h);
        // response[1] = new Vector2d(obj.velocity[0] + (h * f[0]) / obj.mass, obj.velocity[0] + (h * f[1]) / obj.mass);
        return response;
    }*/
}