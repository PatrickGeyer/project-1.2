package golf.course;

import golf.physics.*;
import golf.course.object.*;
import golf.ai.*;

import net.objecthunter.exp4j.*;
import java.io.*;
import java.util.ArrayList;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector2;


public class PuttingSimulator implements Cloneable, Serializable {
    public PuttingCourse course;
    public PhysicsEngine engine;
    private transient AI ai = null;
    public Vector2d ballPosition;

    public int maxShots = 50;

    public transient ArrayList<CourseCallback> callbacks = new ArrayList<CourseCallback>();
    
    public PuttingSimulator(PuttingCourse course, PhysicsEngine engine) {
        this.course = course;
        this.engine = engine;
        
        this.ballPosition = course.get_start_position();
        addDefaultHandler();
    }
    public PuttingSimulator(PuttingCourse course, PhysicsEngine engine, AI ai) {
        this(course, engine);
        this.ai = ai;
    }

    public PuttingSimulator() {
        this(new PuttingCourse(), new EulerSolver());
    }

    public void addDefaultHandler() {
        this.callbacks = new ArrayList<CourseCallback>();
        class Handler extends CourseCallback {
            @Override
            public void onBeforeShot(Ball b) {
                if(ai instanceof AI) {
                    // System.out.println("HERE: " + PuttingSimulator.this.course.getBall().position);
                    take_shot(ai.calculate_shot(PuttingSimulator.this));
                }
            }
            @Override
            public void onAfterShot(Ball b) {
                // System.out.println("Ball is not at: " + b.position);
            }
            @Override
            public void onHole(Ball b) {
                b.complete = true;
                // System.out.println("Course complete!");
            }
        }
        callbacks.add(new Handler());
    }
    
    public void set_ball_position(Vector2d p) {
        this.ballPosition = p;
    }

    public Vector2d get_ball_position() {
        return this.ballPosition;
    }

    public void step() {//called every 0.01 second
        this.step(0.01);
    }

    public boolean intersects(Ball ball, Obstacle obstacle) {
        // get box closest point to sphere center by clamping
     /*   var x = Math.max(obstacle.position.x, Math.min(ball.position.x, obstacle.position.x+obstacle.dimensions.x));
        var y = Math.max(obstacle.position.y, Math.min(ball.position.y, obstacle.position.y+obstacle.dimensions.y));
        var z = Math.max(obstacle.position.z, Math.min(ball.position.z, obstacle.position.z+obstacle.dimensions.z));

       // this is the same as isPointInsideSphere
        var distance = Math.sqrt((x - ball.position.x) * (x - ball.position.x) +
                                (y - ball.position.y) * (y - ball.position.y) +
                                (z - ball.position.z) * (z - ball.position.z));
        
        return distance < ball.radius;*/
        if(Math.abs(obstacle.position.x-ball.position.x)<obstacle.dimensions.x && Math.abs(obstacle.position.y-ball.position.y)<obstacle.dimensions.x){
            return true;
        }
        else return false;
    }

    public void step(double h) {
        boolean movingBallExists = false;
        for(Ball b : this.course.getBalls()) {
            if(b.complete || b.moving) {
                movingBallExists = true;
            }
        }
        if(!movingBallExists) {
            for(CourseCallback c : callbacks)
                c.onBeforeShot((Ball) this.course.objects.get(0));
        }
        
        for(int i = 0; i < this.course.objects.size(); i++) {
            if(this.course.objects.get(i).moving) {

                Vector3[] vs = this.engine.solve(this.course.objects.get(i), this.course, h);
                this.course.objects.get(i).position = vs[0];
                this.course.objects.get(i).position.z = (float) this.course.height.evaluate((float) this.course.objects.get(i).position.x,(float) this.course.objects.get(i).position.y);
                this.course.objects.get(i).velocity = vs[1];

                if(this.course.objects.get(i) instanceof Ball) {//if the ball still exists

                    for(Obstacle o : this.course.getObstacles()) {//objects contains ball, tree etc.
                        if(this.intersects((Ball) this.course.objects.get(0), o)) {///////////scale the velocity in some form //changed i with 0 as object 0 is always the ball?
                         /* if(this.course.objects.get(0).velocity.x>this.course.objects.get(0).velocity.y){
                                this.course.objects.get(0).velocity = new Vector3(this.course.objects.get(0).velocity.x, this.course.objects.get(0).velocity.y*(-1), this.course.objects.get(0).velocity.z);
                          }
                          else{//y>x
                            this.course.objects.get(0).velocity = new Vector3(this.course.objects.get(0).velocity.x*(-1), this.course.objects.get(0).velocity.y, this.course.objects.get(0).velocity.z);
                          }*/
                          /*   Float ball=this.course.objects.get(0).position.y +1;//y position of ball +1
                            Float treemin =this.course.objects.get(1).position.y -3;//min y tree
                            if(Float.compare(ball, treemin) == 0){
                                this.course.objects.get(0).velocity = new Vector3(this.course.objects.get(0).velocity.x*(-1), this.course.objects.get(0).velocity.y*(-1), this.course.objects.get(0).velocity.z);
                            }
                            else{
                                this.course.objects.get(0).velocity = new Vector3(this.course.objects.get(0).velocity.x*(-1), this.course.objects.get(0).velocity.y*(-1), this.course.objects.get(0).velocity.z);
                            }*/ 
                          float b=this.course.objects.get(0).position.y+1;
                          float t = this.course.objects.get(1).position.y -3;//change minus value according to x/y tree
                          float bx=this.course.objects.get(0).position.x;//where the ball is in width
                          float tx = this.course.objects.get(1).position.x +3;//where the tree stops existing in width (x)
                        if((Float.compare(b,t)>=0)&&(Float.compare(bx,tx)<0)){//checks if something behind and in the same x position
                            this.course.objects.get(0).velocity = new Vector3(this.course.objects.get(0).velocity.x, this.course.objects.get(0).velocity.y*(-1), this.course.objects.get(0).velocity.z);
                        }
                        else{
                            this.course.objects.get(0).velocity = new Vector3(this.course.objects.get(0).velocity.x*(-1), this.course.objects.get(0).velocity.y, this.course.objects.get(0).velocity.z);
                        }

                        }
                    }
                    

                    // Check that velocity and forces on ball are close to zero
                    if(this.course.objects.get(i).velocity.len() < 0.01 && vs[2].len() < 0.2) {

                        // If ball near flag
                        if(this.course.checkIfCompleted((Ball) this.course.objects.get(i))) {
                            ((Ball) this.course.objects.get(i)).complete = true;
                            ((Ball) this.course.objects.get(i)).moving = false;
                            for(CourseCallback c : callbacks)
                                c.onHole((Ball) this.course.objects.get(i));
                        }

                        // If ball in water
                        else if(this.course.objects.get(i).position.z < 0) {
                            for(CourseCallback c : callbacks)
                                c.onShotFailed((Ball) this.course.objects.get(i));
                        }
                        for(CourseCallback c : callbacks)
                            c.onAfterShot((Ball) this.course.objects.get(i));
                                
                        this.course.objects.get(i).moving = false;
                        // if(!((Ball) this.course.objects.get(i)).complete) {
                        //     System.out.println("Course not complete");
                        //     for(CourseCallback c : callbacks)
                        //         c.onBeforeShot((Ball) this.course.objects.get(i));
                        // }
                    }
                }//end if statement
            }
        }//end for statement
        this.course.elapsed += h;
    }


    public void take_shot(Vector2d v) {
        this.take_shot(this.course.getBalls().get(0), v);
    }

    public void take_shot(Ball b, Vector2 v) {
        b.moving = true;
        b.shotCount++;
        v.clamp(0, (float) course.Vmax);
        b.velocity.add(new Vector3(v, 0));
        course.elapsed = 0;
    }

    public void step_until_next_shot() {
        while(this.course.getBall().moving) {
            // System.out.println(this.course.getBall().velocity);
            this.step();
        }
    }

    public int play_until_done(AI ai) throws Exception {
        int shots = 0;
        while(this.course.getBall().complete == false) {
            take_shot(ai.calculate_shot(this));
            step_until_next_shot();

            if(shots > this.maxShots) {
                throw new Exception("Too many shots");
            }
            shots++;
        }

        return shots;
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
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            serializeToOutputStream(this, bos);
            byte[] bytes = bos.toByteArray();
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            p = (PuttingSimulator)ois.readObject();
            p.addDefaultHandler();
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
