package golf.course;
import golf.physics.*;
import golf.course.object.*;
import net.objecthunter.exp4j.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.Serializable;

public class PuttingCourse implements Serializable {
    public Function2d height = new Function2d("-0.01*x + 0.003*x^2 + 0.04 * y + sin(x)");
    public Vector2d flag = new Vector2d(0, 10);
    public Vector2d start = new Vector2d(0,0);
    public double g = 9.81;              // Gravitational acceleration
    public double frictionCoefficient = 0.131;            // Coefficient of friction (rolling ball) // Typical 0.065<=mu<=0.196
    public double Vmax = 3.0;            // Maximum initial ball speed [m/s]
    public double holeTolerance = 0.02;  // Distance from hole for a successful putt [m]
    public List<GameObject> objects = new ArrayList<GameObject>();
    public PuttingCourse () {


        if(this.objects.size() == 0) {
            this.objects.add(new Ball());
        }
    }

    public PuttingCourse (Function2d height, Vector2d flag, Vector2d start) {
        this();
        this.height = height;
        this.flag = flag;
        this.start = start;
    }

    public Function2d get_height() {
        return this.height;
    }

    public Vector2d get_flag_position() {
        return this.flag;
    }

    public Vector2d get_start_position() {
        return this.start;
    }

    public double get_friction_coefficient() {
        return frictionCoefficient;
    }

    public double get_maximum_velocity() {
        return Vmax;
    }

    public double get_hole_tolerance() {
        return holeTolerance;
    }

    public double get_gravitational_constant() {
        return g;
    }

    public void setFrictionCoefficient(double friction) {
        this.frictionCoefficient = friction;
    }

    public void setMaximumVelocity(double Vmax) {
        this.Vmax = Vmax;
    }

    public void setHoleTolerance(double tolerance) {
        this.holeTolerance = tolerance;
    }

    public void setGravitationalConstant(double g) {
        this.g = g;
    }

    public List<Ball> getBalls() {
        return this.objects.stream()
            .filter(p -> p instanceof Ball)
            .map(p -> (Ball) p)
            .collect(Collectors.toList());
    }

}
