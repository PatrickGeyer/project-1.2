package golf.course;
import golf.physics.*;
import net.objecthunter.exp4j.*;

import java.io.*;

class PuttingSimulator {
    private PuttingCourse course;
    private PhysicsEngine engine;
    private Vector2d ballPosition;

    public PuttingSimulator(PuttingCourse course, PhysicsEngine engine) {
        this.course = course;
        this.engine = engine;
        this.ballPosition = course.get_start_position();
    }

    public void set_ball_position(Vector2d pos) {
        this.ballPosition = pos;
    }

    public Vector2d get_ball_position() {
        return this.ballPosition;
    }

    public void take_shot(Vector2d initial_ball_velocity) {
        //TO ADD
    }

    public boolean saveCourse(String filePath) {
        try {
            FileOutputStream f = new FileOutputStream(filePath);
            ObjectOutputStream outputStream = new ObjectOutputStream(f);
            outputStream.writeObject(course);
            outputStream.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean loadCourse(String filePath) {
        try {
            FileInputStream f = new FileInputStream(filePath);
            ObjectInputStream inputStream = new ObjectInputStream(f);
            course = (PuttingCourse) (inputStream.readObject());
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        PuttingCourse course = new PuttingCourse(courseFunction, flag);
        PhysicsEngine engine = new EulerSolver();
        PuttingSimulator simulator = new PuttingSimulator(course, engine);
        //TO ADD

    }
} 