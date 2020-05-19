
package golf.ai;

import golf.physics.*;
import golf.course.object.Ball;
import golf.course.PuttingCourse;
import golf.course.PuttingSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.Serializable;

import com.badlogic.gdx.math.Vector2;

public class AI implements Serializable {
    
    public Vector2d calculate_shot(PuttingSimulator simulation, Ball ball){

        // Calculate the direction & strength needed, could be used for heuristics
        Vector2 vToEnd = new Vector2(simulation.course.flag).sub(new Vector2d(ball.position));

        // List of simulated shot results
        ArrayList<ShotResult> l = new ArrayList<ShotResult>();

        // Simulate shot from various angles
        for(float x = (float) -simulation.course.Vmax; x <= simulation.course.Vmax; x+=.2) {

            for(float y = (float) -simulation.course.Vmax; y <= simulation.course.Vmax; y+=.2) {
                if(x == 0 && y == 0) continue;

                PuttingSimulator sim = simulation.clone();
                Vector2d shot = new Vector2d(x, y);

                // ShotResult will take simulation and shot as parameters
                // Calls simulation.step_until_next_shot to find ball landing position
                l.add(new ShotResult(shot, sim));
                if(sim.course.getBall().complete) {
                    break;
                }
            }
        }

        // Sort list of simulations to find closest hit
        Collections.sort(l, new OrderByDistance());
        System.out.println("Taking shot: " 
        + l.get(0).shot 
        + " with distance " 
        + l.get(0).distance 
        + " from " 
        + simulation.course.getBall().position 
        + " to " 
        + l.get(0).simulation.course.getBall().position
        );

        return l.get(0).shot;
    }

    public Vector2d calculate_shot(PuttingSimulator simulation){
        return this.calculate_shot(simulation, simulation.course.getBall());
    }
}

class ShotResult {
    Vector2d shot;
    Vector2d start;
    Vector2d end;
    PuttingSimulator simulation;
    Float distance;

    public ShotResult(Vector2d shot, PuttingSimulator sim) {
        this.shot = shot;
        this.simulation = sim;
        
        this.start = new Vector2d(this.simulation.course.getBall().position);
        this.simulation.take_shot(shot);
        this.simulation.step_until_next_shot();

        this.end = new Vector2d(this.simulation.course.getBall().position);
        this.distance = end.dst(sim.course.flag);
    }

    public String toString() {
        return "Shot Result: " 
        + String.format("%" + 15 + "s", this.shot)
        + " start point "
        + String.format("%" + 10 + "s", this.start)
        + " ending point " 
        + String.format("%" + 5 + "s", this.end)
        + " with distance from flag " 
        + String.format("%" + 10 + "s", this.distance);
    }
}

class OrderByDistance implements Comparator<ShotResult> {
    public int compare(ShotResult p1, ShotResult p2) {
        return Float.compare(
            p1.distance,
            p2.distance
        );
    }
}