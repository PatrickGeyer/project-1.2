package golf.course;
import golf.physics.*;
import net.objecthunter.exp4j.*;

import java.io.*;
import golf.course.object.*;
import com.badlogic.gdx.math.Vector3;


public class PuttingSimulator {
    public PuttingCourse course;
    public PhysicsEngine engine;

    public PuttingSimulator(PuttingCourse course, PhysicsEngine engine) {
        this.course = course;
        this.engine = engine;
    }
    
    public void step(double h) {
        for(int i = 0; i < this.course.objects.size(); i++) {
            Vector3[] vs = this.engine.solve(this.course.objects.get(i), this.course, h);
            this.course.objects.get(i).position = vs[0];
            this.course.objects.get(i).velocity = vs[1];
        }
    }

    public void take_shot(Ball b, Vector2d initial_ball_velocity) {
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
} 
