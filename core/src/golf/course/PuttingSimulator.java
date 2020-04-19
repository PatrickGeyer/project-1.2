package golf.course;
import golf.physics.*;
import net.objecthunter.exp4j.*;

import java.io.*;
import golf.course.object.*;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector2;


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

    public void take_shot(Vector2d v) {
        this.take_shot(this.course.getBalls().get(0), new Vector2((float) v.x, (float) v.y));
    }
    public void take_shot(Ball b, Vector2 v) {
        b.velocity.add(new Vector3(v, 0));
    }

    public void set_ball_position(Vector2d p) {

    }
    public void get_ball_position() {
        
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
