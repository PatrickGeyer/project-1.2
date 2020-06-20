
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

    public int layers = 1;
    public double restrictAngle = 0;
    public int possibilitiesExamined = 0;
    public int possibilitiesExaminedTotal = 0;
    public boolean returnOnHole = true;

    public AI() {

    }

    public AI(int l) {
        this.layers = l;
    }

    public Vector2d calculate_shot(PuttingSimulator simulation, Ball ball){

        // List of simulated shot results
        ArrayList<ShotResult> l = list_shots_n_layers(this.layers, simulation);
               // Sort list of simulations to find closest hit
        Collections.sort(l, new OrderByDistance());
        this.possibilitiesExamined = l.size();
        this.possibilitiesExaminedTotal+= possibilitiesExamined;

        // If multilayered, we need to return the first shot of sequence
        ShotResult s = l.get(0);
        while(s.previous != null) {
            s = s.previous;
        }

        // System.out.println("Taking shot: " 
        // + s.shot 
        // + " with distance " 
        // + s.distance 
        // + " from " 
        // + simulation.course.getBall().position 
        // + " to " 
        // + s.simulation.course.getBall().position
        // + " out of available end layer shots: " + l.size()
        // );

        return s.shot;
    }

    public Vector2d calculate_shot(PuttingSimulator simulation){
        return this.calculate_shot(simulation, simulation.course.getBall());
    }

    public ArrayList<ShotResult> list_shots_n_layers(int layers, PuttingSimulator simulation) {

        ArrayList<ShotResult> l = get_shots(simulation.clone());

        // System.out.println("calculating shots for layer: " + layers + ", shot count: " + l.size());
        for(ShotResult n: l) {
            if( n.simulation.course.getBall().complete && this.returnOnHole) {
                return l;
            }
        }
        if(layers - 1 > 0) {
            ArrayList<ShotResult> sub = new ArrayList();
            outerloop:
            for(ShotResult s : l) {
                ArrayList<ShotResult> newLayer = list_shots_n_layers(layers - 1, s.simulation);
                sub.addAll(newLayer);
                for(ShotResult n: newLayer) {
                    n.previous = s;
                    if( n.simulation.course.getBall().complete && this.returnOnHole) {
                        break outerloop;
                    }
                }
            }
            // System.out.println("finished: " + layers);
            return sub;
        }
        // System.out.println("finished: " + layers);

        return l;
    }

    public ArrayList<ShotResult> get_shots(PuttingSimulator simulation) {
        ArrayList<ShotResult> l = new ArrayList<ShotResult>();
        Vector2 vToEnd = new Vector2(simulation.course.flag).sub(new Vector2d(simulation.course.getBall().position));
        // System.out.println(vToEnd);

        outerloop:
        // Simulate shot from various angles
        for(float x = (float) -simulation.course.Vmax; x <= simulation.course.Vmax; x+=.2) {

            for(float y = (float) -simulation.course.Vmax; y <= simulation.course.Vmax; y+=.2) {
                if(x == 0 && y == 0) continue;

                Vector2d shot = new Vector2d(x, y);

                if(this.restrictAngle > 0 && Math.abs(shot.angle(vToEnd)) > this.restrictAngle) {
                    continue;
                }

                PuttingSimulator sim = simulation.clone();

                // ShotResult will take simulation and shot as parameters
                // Calls simulation.step_until_next_shot to find ball landing position
                l.add(new ShotResult(shot, sim));
                if(sim.course.getBall().complete && this.returnOnHole) {
                    break outerloop;
                }
            }
        }
        return l;
    }

}

class ShotResult {
    Vector2d shot;
    Vector2d start;
    Vector2d end;
    PuttingSimulator simulation;
    Float distance;
    ShotResult previous = null;

    public ShotResult(Vector2d shot, PuttingSimulator sim) {
        this.shot = shot;
        this.simulation = sim;

        this.simulation.course.windDuration = 0;
        
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