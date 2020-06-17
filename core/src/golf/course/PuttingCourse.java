package golf.course;
import golf.physics.*;
import golf.course.object.*;
import net.objecthunter.exp4j.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.*;
import com.badlogic.gdx.math.Vector2;

public class PuttingCourse implements Cloneable, Serializable {
    public Function2d height = new Function2d("sin(x/2)");
    public Vector2 flag = new Vector2(10, 10);
    public Vector2 start = new Vector2(0, 0);
    public double windIntensity = 0;
    // z represents time variable in below equation
    public Function3d wind = new Function3d("sin(x) * cos(y) * cos(z)");
    public Vector2 prevailingWindDirection = new Vector2(1, 1);
    public double g = 9.81;              // Gravitational acceleration
    public double m = 45.93;
    public double frictionCoefficient = 0.131;            // Coefficient of friction (rolling ball) // Typical 0.065<=mu<=0.196
    public double Vmax = 10.0;            // Maximum initial ball speed [m/s]
    public double holeTolerance = 0.2;  // Distance from hole for a successful putt [m]
    public double elapsed = 0.0;

    public ArrayList<GameObject> objects = new ArrayList<GameObject>();
    
    public PuttingCourse () {
        if(this.objects.size() == 0) {
            this.objects.add(new Ball());
        }

        // Add any obstacles needed 
        Tree t = new Tree();
        t.position.x = 10;
        t.position.y = 15;
        this.objects.add(t);
    }

    public PuttingCourse (Function2d height, Vector2d flag, Vector2d start) {
        this.height = height;
        this.flag = new Vector2((float) flag.get_x(), (float) flag.get_y());
        this.start = new Vector2((float) start.get_x(), (float) start.get_y());
    }

    public Function2d get_height() {
        return this.height;
    }

    public Vector2d get_flag_position() {
        return new Vector2d(this.flag);
    }

    public Vector2d get_start_position() {
        return new Vector2d(this.start);
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

    public double get_ball_mass() {
        return m;
    }

    public void setBallMass(double m) {
        this.m = m;
    }

    public boolean checkIfCompleted(Ball b) {

        // If ball within tolerance of finish flag
        // and ball is not moving return true
        if(
            this.flag.dst(new Vector2(b.position.x, b.position.y)) <= this.holeTolerance
            &&
            b.velocity.len() <= 0.01
            ) {
                return true;
            }
            return false;
    }

    public List<Ball> getBalls() {
        return this.objects.stream()
            .filter(p -> p instanceof Ball)
            .map(p -> (Ball) p)
            .collect(Collectors.toList());
    }
    public List<Obstacle> getObstacles() {
        return this.objects.stream()
            .filter(p -> p instanceof Obstacle)
            .map(p -> (Obstacle) p)
            .collect(Collectors.toList());
    }

    public Ball getBall() {
        return this.getBalls().get(0);
    }

    // public String toString() {
    //     return "PuttingCourse{" +
    //     "height=" + height +
    //     ", flag=" + flag +
    //     ", holeTolerance=" + holeTolerance +
    //     ", start=" + start +
    //     ", frictionCoefficient=" + frictionCoefficient +
    //     ", maximumVelocity=" + Vmax +
    //     ", gravitationalConstant=" + g +
    //     '}';
    // }


    @Override
    public PuttingCourse clone() {
        PuttingCourse p = new PuttingCourse();
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            serializeToOutputStream(this, bos);
            byte[] bytes = bos.toByteArray();
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            p = (PuttingCourse)ois.readObject();
            return p;
        } catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return p;
    }
    private void serializeToOutputStream(Serializable ser, OutputStream os) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(os);
            oos.writeObject(ser);
            oos.flush();
        } finally {
            oos.close();
        }
    }

}