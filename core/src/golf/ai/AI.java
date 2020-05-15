
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
        Vector2 vToEnd = new Vector2(simulation.course.flag).sub(new Vector2d(ball.position));

        ArrayList<ShotResult> l = new ArrayList<ShotResult>();

        // Simulate shot from various angles similar to actual birds-flight direction to flag
        for(float x = 0f; x < 2*simulation.course.Vmax; x+=.2) {

            for(float y = 0f; y < 2*simulation.course.Vmax; y+=.2) {

                PuttingSimulator sim = simulation.clone();
                Vector2d shot = new Vector2d(x - simulation.course.Vmax, y - simulation.course.Vmax);
                // System.out.println(shot);
                sim.take_shot(shot);
                sim.step_until_next_shot();
                l.add(new ShotResult(shot, sim));
                if(sim.course.getBall().complete) {
                    break;
                }
            }
        }

        // Sort list of simulations to find closest hit
        Collections.sort(l, new OrderByDistance());
        System.out.println("Taking shot: " + l.get(0).shot);

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
        // System.out.println(new Vector2d(this.simulation.course.getBall().position).dst(this.simulation.course.flag));
    }
}
class OrderByDistance implements Comparator<ShotResult> {
    public int compare(ShotResult p1, ShotResult p2) {
        return Float.compare(
            new Vector2d(p1.simulation.course.getBall().position).dst(p1.simulation.course.flag),
            new Vector2d(p2.simulation.course.getBall().position).dst(p2.simulation.course.flag)
        );
    }
}