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
    public Vector3 lastPosition = new Vector3(0,0,0);
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
                lastPosition = b.position;
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

    // Returns a vector to scale velocity by - depending on which side of object is hit
    public Vector3 intersects(Ball ball, Obstacle o) {
        double x1 = ball.position.x - ball.radius;
        double y1 = ball.position.y - ball.radius;
        double x2 = ball.position.x + ball.radius;
        double y2 = ball.position.y + ball.radius;

        double x3 = o.position.x;
        double y3 = o.position.y;
        double x4 = o.position.x + o.dimensions.x;
        double y4 = o.position.y + o.dimensions.y;

        // System.out.println(x3);
        // System.out.println(y3);
        // System.out.println(x4);
        // System.out.println(y4);
        //         System.out.println("---");
        //         System.out.println(x1);
        // System.out.println(y1);
        // System.out.println(x2);
        // System.out.println(y2);
        //         System.out.println(o.position);
        //         System.out.println(o.dimensions);


        if((x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2)){
            Vector3 v = new Vector3(1,1,1);
            //if bouncing off and chnaging y direcvtion i.e. bouncing between x corrdinstes of obstacle
            if((x1 < x4) && (x3 < x2) && ((y1 < y3) || (y2 > y4))  ) {
                v.y = -1;
                //if bouncing off and chnaging x direcvtion
            } else {
                v.x = -1;
            }
            return v;
        }
        return null;
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
                    
                    if(this.course.objects.get(i).position.z < 0) {

                        this.course.objects.get(i).shotCount++;
                        this.course.objects.get(i).position = lastPosition;
                        this.course.objects.get(i).velocity = new Vector3(0,0,0);
                        this.course.objects.get(i).moving = false;                        
                    }


                    for(Obstacle o : this.course.getObstacles()) {
                        Vector3 v = this.intersects((Ball) this.course.objects.get(0), o);
                        if(v != null) {//changed i with 0 as object 0 is always the ball?
                            this.course.objects.get(i).velocity.scl(v);
                            System.out.println("intersect" + v);
                            // this.course.objects.get(i).velocity.scl((float) 0);

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
                       /* else if(this.course.objects.get(i).position.z < 0) {
                            for(CourseCallback c : callbacks)
                                c.onShotFailed((Ball) this.course.objects.get(i));
                        }*/
                        for(CourseCallback c : callbacks)
                            c.onAfterShot((Ball) this.course.objects.get(i));
                                
                        this.course.objects.get(i).moving = false;
                        // if(!((Ball) this.course.objects.get(i)).complete) {
                        //     System.out.println("Course not complete");
                        //     for(CourseCallback c : callbacks)
                        //         c.onBeforeShot((Ball) this.course.objects.get(i));
                        // }
                    }
                }
            }
        }
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
