package golf.course;
import golf.physics.*;

public class Course {
    public Vector2d start = new Vector2d(0,0);
    public Vector2d goal = new Vector2d(10, 10);
    public float g = 9.81;              // Gravitational acceleration
    public float ballWeight = 45.00;    // Mass of ball (g)
    public float mu = 0.131;            // Coefficient of friction (rolling ball) // Typical 0.065<=mu<=0.196
    public float vmax = 3.0;            // Maximum initial ball speed [m/s]
    public float goalTolerance = 0.02;  // Distance from hole for a successful putt [m]
    public String height = "0";         // String of a function that returns height for each x, y coordinate

    public float getHeight(float x, float y) {
        Expression e = new ExpressionBuilder(this.height)
            .variables("x", "y")
            .build()
            .setVariable("x", x)
            .setVariable("y", y);
        return e.evaluate();
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
