// sorry i don't know what to do with this one mates xo

public class Course {

    double gravitationalAcc;
    double ballMass;
    double friction;
    double maxBallSpeed;
    double distance;
    String startPoint;
    String targetPoint;
    String formula;

    public Course(double gravitationalAcc, double ballMass, double friction, double maxBallSpeed, double distance, 
    String startPoint, String targetPoint, String formula) {

        this.gravitationalAcc = gravitationalAcc;
        this.ballMass = ballMass;
        this.friction  = friction;
        this.maxBallSpeed = maxBallSpeed;
        this.distance = distance;
        this.startPoint = startPoint;
        this.targetPoint = targetPoint;
        this.formula = formula;
    }
}
