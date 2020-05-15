package golf.course;

import golf.physics.*;
import golf.course.object.*;
import golf.ai.*;

import net.objecthunter.exp4j.*;
import java.io.*;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector2;


public class PuttingSimulator implements Serializable {
    public PuttingCourse course;
    private PhysicsEngine engine;
    private Vector2d ballPosition;
    private AI ai;

    public transient CourseCallback callback = new CourseCallback();

    public PuttingSimulator(PuttingCourse course, PhysicsEngine engine) {
        this.course = course;
        this.engine = engine;
        this.ballPosition = course.get_start_position();
    }
    public PuttingSimulator(PuttingCourse course, PhysicsEngine engine, AI ai) {
        this(course, engine);
        this.ai = ai;
    }

    public PuttingSimulator() {
        this.course = new PuttingCourse();
        this.engine = new Euler();
        this.ballPosition = course.get_start_position();
    }
    
    public void set_ball_position(Vector2d p) {
        this.ballPosition = p;
    }

    public Vector2d get_ball_position() {
        return this.ballPosition;
    }

    public void step() {
        this.step(0.01);
    }

    public void step(double h) {
        for(int i = 0; i < this.course.objects.size(); i++) {
            if(this.course.objects.get(i).moving) {
                Vector3[] vs = this.engine.solve(this.course.objects.get(i), this.course, h);
                this.course.objects.get(i).position = vs[0];
                this.course.objects.get(i).position.z = (float) this.course.height.evaluate((float) this.course.objects.get(i).position.x,(float) this.course.objects.get(i).position.y);
                this.course.objects.get(i).velocity = vs[1];

                if(this.course.objects.get(i) instanceof Ball) {

                    // Check that velocity and forces on ball are close to zero
                    if(this.course.objects.get(i).velocity.len() < 0.01 && vs[2].len() < 0.2) {

                        // If ball near flag

                        if(this.course.checkIfCompleted((Ball) this.course.objects.get(i))) {
                            ((Ball) this.course.objects.get(i)).complete = true;
                            this.callback.onHole((Ball) this.course.objects.get(i));
                        }

                        // If ball in water
                        else if(this.course.objects.get(i).position.z < 0) {
                            this.callback.onShotFailed((Ball) this.course.objects.get(i));
                        }
                        this.callback.onAfterShot((Ball) this.course.objects.get(i));
                        this.course.objects.get(i).moving = false;
                    }
                }
            }
        }
    }


    public void take_shot(Vector2d v) {
        this.take_shot(this.course.getBalls().get(0), v);
    }

    public void take_shot(Ball b, Vector2 v) {
        b.moving = true;
        b.shotCount++;
        v.clamp(0, (float) course.Vmax);
        b.velocity.add(new Vector3(v, 0));
    }

    public void step_until_next_shot() {
        while(this.course.getBall().moving) {
            this.step();
        }
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

    @Override
    public PuttingSimulator clone() {
        PuttingSimulator p = new PuttingSimulator();
        try {
            p = (PuttingSimulator) super.clone();
        } catch(Exception e) {
            
        }
        return p;
    }
} 
