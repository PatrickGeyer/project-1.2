package golf.physics;

import golf.course.object.GameObject;
import golf.course.PuttingCourse;
import golf.course.*;
import com.badlogic.gdx.math.Vector3;

public class RK4Solver extends PhysicsEngine {

    @Override
    public Vector3[] solve(GameObject obj, PuttingCourse c, double h) {

        double kx0, kx1, kx2, kx3, ky0,ky1,ky2,ky3,lx0,lx1,lx2,lx3,ly0,ly1,ly2,ly3;
        double x=obj.position.x, y=obj.position.y,vx=obj.velocity.x,vy=obj.velocity.y;
        GameObject ob1 = obj.clone(), ob2 = obj.clone(), ob3 = obj.clone();

        kx0 = h * vx;
        ky0 = h * vy;

        Vector3 a = getAcceleration(obj, c, h);
        lx0 = h * a.x;
        ly0 = h * a.y;
        kx1 = h * (vx + 0.5 * lx0);
        ky1 = h * (vy + 0.5 * ly0);

        ob1.position.add(new Vector3((float) (0.5 * kx0), (float) (0.5 * ky0), 0));
        ob1.velocity.add(new Vector3((float) (0.5 * lx0), (float) (0.5 * ly0), 0));
        a = getAcceleration(ob1, c, h);
        lx1 = h * a.x;
                System.out.println(lx1);

        ly1 = h * a.y;
        kx2 = h * (vx + 0.5 * lx1);
        ky2 = h * (vy + 0.5 * ly1);

        ob2.position.add(new Vector3((float) (0.5 * kx1), (float) (0.5 * ky1), 0));
        ob2.velocity.add(new Vector3((float) (0.5 * lx1), (float) (0.5 * ly1), 0));
        a = getAcceleration(ob2, c, h);
        lx2 = h * a.x;
        ly2 = h * a.y;
        kx3 = h * (vx + lx2);
        ky3 = h * (vy + ly2);

        ob3.position.add(new Vector3((float) (kx2), (float) (ky2), 0));
        ob3.velocity.add(new Vector3((float) (lx2), (float) (ly2), 0));
        a = getAcceleration(ob3, c, h);
        lx3 = h * a.x;
        ly3 = h * a.y;
        
        Vector3[] response = new Vector3[3];

        //position vector
        response[0] = new Vector3(
            (float) ((1.0 / 6.0) * (kx0 + 2 * kx1 + 2 * kx2 + kx3)),
            (float) ((1.0 / 6.0) * (ky0 + 2 * ky1 + 2 * ky2 + ky3)), 
            0).add(obj.position);

        //velocity vector
        response[1] = new Vector3(
            (float) ((1.0 / 6.0) * (lx0 + 2 * lx1 + 2 * lx2 + lx3)), 
            (float) ((1.0 / 6.0) * (ly0 + 2 * ly1 + 2 * ly2 + ly3)),
             0f).add(obj.velocity);

        //force vector
        response[2] = this.force(obj, c, h);

        return response;
        
    }
}