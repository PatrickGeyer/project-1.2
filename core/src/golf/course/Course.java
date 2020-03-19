package golf.course;
import golf.physics.*;
import net.objecthunter.exp4j.*;

public class Course {
    public Vector2d start = new Vector2d(0,0);
    public Vector2d goal = new Vector2d(10, 10);
    public double g = 9.81;              // Gravitational acceleration
    public double ballMass = 45.00;    // Mass of ball (g)
    public double frictionCoefficient = 0.131;            // Coefficient of friction (rolling ball) // Typical 0.065<=mu<=0.196
    public double vmax = 3.0;            // Maximum initial ball speed [m/s]
    public double goalTolerance = 0.02;  // Distance from hole for a successful putt [m]
    public Function2d height = new Function2d("x-y");         // String of a function that returns height for each x, y coordinate

    public double friction(Vector2d pos) {
        return 0.0;
    }
    // public PuttingCourse(Function2d height,
    //                      Vector2d flag, Vector2d start);
    // public Function2d get_height();
    // public Vector2d get_flag_position(); 
    // public Vector2d get_start_position(); 
    // public double get_friction_coefficient(); 
    // public double get_maximum_velocity(); 
    // public double get_hole_tolerance();
}

/*
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
*/