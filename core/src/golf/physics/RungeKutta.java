package golf.physics;

import golf.course.object.GameObject;
import golf.course.PuttingCourse;
import golf.course.*;
import com.badlogic.gdx.math.Vector3;

public class RungeKutta implements PhysicsEngine {

    public Vector3 force(GameObject obj, PuttingCourse c, double h) {

        Vector2d gradient = c.height.gradient(obj.position.x, obj.position.y);
        
        Vector3 friction = new Vector3(
            obj.velocity.len() == 0 ? 0 : (float) (-c.frictionCoefficient * obj.mass * c.g * obj.velocity.x / (double) obj.velocity.len()),
            obj.velocity.len() == 0 ? 0 : (float) (-c.frictionCoefficient * obj.mass * c.g * obj.velocity.y / (double) obj.velocity.len()),
            0
        );
        
        Vector3 gravity = new Vector3(
            (float) (-obj.mass * c.g * gradient.x),
            (float) (-obj.mass * c.g * gradient.y),
            0
        );

        return friction.add(gravity);
    }

    public Vector3 getAcceleration(Vector3 velocity, Vector3 position, PuttingCourse c) {
        
        Vector2d gradient = c.height.gradient(position.x, position.y);

        Vector3 acceleration = new Vector3();

        if (velocity.x != 0 || velocity.y != 0) {
            //𝑥̈ =−𝑔h (𝑥,𝑦)−𝜇𝑔𝑥̇⁄√𝑥̇2 +𝑦̇2
            //𝑦̈ =−𝑔h (𝑥,𝑦)−𝜇𝑔𝑦̇⁄√𝑥̇2 +𝑦̇2
            acceleration.x = (float) ((-c.g * gradient.x) - c.frictionCoefficient * c.g * velocity.x / (Math.sqrt(velocity.x*velocity.x + velocity.y*velocity.y)));
            acceleration.y = (float) ((-c.g * gradient.y) - c.frictionCoefficient * c.g * velocity.y / (Math.sqrt(velocity.x*velocity.x + velocity.y*velocity.y)));

            return acceleration;
        } 
        return new Vector3(0,0,0);
    }

    @Override
    public Vector3[] solve(GameObject obj, PuttingCourse c, double h) {

        Vector3[] response = new Vector3[3];
        
        double k1x = obj.velocity.x * h;
        double k2x = (obj.velocity.x + k1x/2) * h;
        double k3x = (obj.velocity.x + k2x/2) * h;
        double k4x = (obj.velocity.x + k3x) * h;

        double k1y = obj.velocity.y * h;
        double k2y = (obj.velocity.y + (k1y/2)) * h;
        double k3y = (obj.velocity.y + (k2y/2)) * h;
        double k4y = (obj.velocity.y + k3y) * h;

        double k1vx = getAcceleration(obj.velocity, obj.position, c).x * h;
        double k2vx = getAcceleration(obj.velocity.add((float) (k1vx/2)), obj.position.add( (float) (k1x/2)), c).x * h;
        obj.velocity.add((float) (-k1vx/2));
        obj.position.add((float) (-k1x/2));
        
        double k3vx = getAcceleration(obj.velocity.add((float) (k2vx/2)), obj.position.add((float) (k2x/2)), c).x * h;
        obj.velocity.add((float) (-k2vx/2));
        obj.position.add((float) (-k2x/2));
        
        double k4vx = getAcceleration(obj.velocity.add((float) (k3vx)),obj.position.add((float) (k3x)), c).x * h;
        obj.velocity.add((float) (-k3vx)); 
        obj.position.add((float) (-k3x));

        double k1vy = getAcceleration(obj.velocity,obj.position, c).y * h;
        double k2vy = getAcceleration(obj.velocity.add((float) (k1vy/2)), obj.position.add((float) (k1y/2)), c).y * h;
        obj.velocity.add((float) (-k1vy/2));
        obj.position.add((float) (-k1y/2));
        
        double k3vy = getAcceleration(obj.velocity.add((float) (k2vy/2)), obj.position.add((float) (k2y/2)), c).y * h;
        obj.velocity.add((float) (-k2vy/2));
        obj.position.add((float) (-k2y/2));
        
        double k4vy = getAcceleration(obj.velocity.add((float) (k3vy)), obj.position.add((float) (k3y)), c).y * h;
        obj.velocity.add((float) (-k3vy));
        obj.position.add((float) (-k3y));

        float updX = (float) ((k1x + 2*k2x + 2*k3x + k4x) / 6);
        float updY = (float) ((k1y + 2*k2y + 2*k3y + k4y) / 6);
        float updZ = (float) (c.height.evaluate(obj.position.x + updX, obj.position.y + updY)) - (float) (c.height.evaluate(obj.position.x, obj.position.y));
        
        float updXV = (float) (k1vx + 2*k2vx + 2*k3vx + k4vx)/6;
        float updYV = (float) (k1vy + 2*k2vy + 2*k3vy + k4vy)/6;
        
        //position vector
        response[0] = new Vector3(updX, updY, 0).add(obj.position);
        //response[0].z = (float) c.height.evaluate(obj.position.x + updX, obj.position.y + updY);
        response[0].z = (float) c.height.evaluate(response[0].x, response[0].y);

        //velocity vector
        response[1] = new Vector3();
        response[1].x = obj.velocity.x + updXV;
        response[1].y = obj.velocity.y + updYV;
        //response[1] = new Vector3(updXV, updYV, 0f).add(obj.velocity);

        Vector3 f = this.force(obj, c, h);
       
        //force vector
        response[2] = f;

        return response;
        
    }
}