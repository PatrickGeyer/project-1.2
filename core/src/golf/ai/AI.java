
package golf.ai;

import golf.physics.*;
import golf.course.object.Ball;
import golf.course.PuttingCourse;
import golf.course.PuttingSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.badlogic.gdx.math.Vector2;

public class AI {
    public Vector2d calculate_shot(PuttingSimulator simulation, Ball ball){
        // Calculate the direction & strength needed
        Vector2 vToEnd = new Vector2(simulation.course.flag).len(new Vector2(ball.position));

        ArrayList<ShotResult> l = new ArrayList<ShotResult>();

        // Simulate shot from various angles similar to actual birds-flight direction to flag
        for(int i = 0; i < 100; i++) {
            PuttingSimulator sim = simulation.clone();
            Vector2d shot = new Vector2d(0,0);
            sim.take_shot(shot);
            sim.step_until_next_shot();
            l.add(new ShotResult(shot, sim));
            if(sim.course.getBall().complete) {
                break;
            }
        }

        // Sort list of simulations to find closest hit
        Collections.sort(l, new OrderByDistance());

        return l.get(0).shot;
    }

    public Vector2d calculate_shot(PuttingSimulator simulation){
        return this.calculate_shot(simulation, simulation.course.getBall());
    }
}

class ShotResult {
    Vector2d shot;
    PuttingSimulator simulation;
    public ShotResult(Vector2d shot, PuttingSimulator sim) {
        this.shot = shot;
        this.simulation = sim;
    }
}
class OrderByDistance implements Comparator<ShotResult> {
    public int compare(ShotResult p1, ShotResult p2) {
        return Float.compare(
            new Vector2d(p2.simulation.course.getBall().position).len(p2.simulation.course.flag), 
            new Vector2d(p1.simulation.course.getBall().position).len(p1.simulation.course.flag)
        );
    }
}