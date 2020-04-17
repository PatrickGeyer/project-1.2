package golf.physics;

import golf.course.object.GameObject;
import golf.course.PuttingCourse;

public class VerletSolver extends EulerSolver implements PhysicsEngine {

    public VerletSolver(double stepSize) {
        super(stepSize);
    }

    public VerletSolver() {
        super(0.01);
    }

    public void approximatePosition() {
        super.approximatePosition();
        double accelerationAdditionX = 0.5 * Math.pow(stepSize, 2) * accelerationVector.get_x();
        double accelerationAdditionY = 0.5 * Math.pow(stepSize, 2) * accelerationVector.get_y();
        positionVector.addX(accelerationAdditionX);
        positionVector.addY(accelerationAdditionY);
    }
}