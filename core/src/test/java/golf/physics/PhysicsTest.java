import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import net.objecthunter.exp4j.*;

import org.junit.Test;

import golf.course.*;
import golf.physics.*;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.HashMap; // import the HashMap class

public class PhysicsTest {

  PuttingCourse course = new PuttingCourse();
  PuttingSimulator simulation = new PuttingSimulator(this.course, new EulerSolver());

  public PhysicsTest() {
    course.height = new Function2d("1");
  }

  @Test
  public void velocityTest() {
    this.simulation.take_shot(this.course.getBalls().get(0), new Vector2(0, 1));
    this.simulation.step();
    assertEquals(0.01, this.course.getBalls().get(0).position.y, 0.001);
    assertEquals(0, this.course.getBalls().get(0).position.x, 0.001);
  }
  @Test
  public void velocityCapTest() {
    course.Vmax = 1;
    this.simulation.take_shot(this.course.getBalls().get(0), new Vector2(0, 10000));
    this.simulation.step();
    assertEquals(0.01, this.course.getBalls().get(0).position.y, 0.001);
    assertEquals(0, this.course.getBalls().get(0).position.x, 0.001);
  }

/**
Ball should roll down slope and settle in equilibrium
 */
  @Test
  public void settleInEquilibrium() {
    course.getBalls().get(0).moving = true;
    course.height = new Function2d("(x - 10)^2 / 20");
    for(int i = 0; i < 10000; i++) {
      this.simulation.step();
    }
    assertEquals(0, this.course.getBalls().get(0).position.y, 0.001);
    // Lower accuracy here as ball might still be moving about minimum
    assertEquals(10, this.course.getBalls().get(0).position.x, 0.5);
  }


  /**
    Testing accuracy of different physics engines
 */
  @Test
  public void accuracyTestFriction() {
    
    PuttingCourse course = new PuttingCourse();
    course.height = new Function2d("1");
    course.frictionCoefficient = 0.1;
    course.g = 10;
    course.getBall().velocity.x = 1;
    course.getBall().moving = true;

    // Which engines we wish to test
    ArrayList<PuttingSimulator> engines = new ArrayList<PuttingSimulator>() { 
        { 
            add(new PuttingSimulator(course.clone(), new EulerSolver()));
            add(new PuttingSimulator(course.clone(), new VerletSolver()));
            add(new PuttingSimulator(course.clone(), new RK4Solver()));
        } 
    };

    // F = ma
    // a = F/m 
    // a = (−𝜇mg(v[x]/v))/m       (v[x]/v equals 1 when only playing on x axis)
    // a = -𝜇mg/m
    // a = -𝜇g
    // a = -1                     (for this particular example)
    // v(t) = -t + c
    // v(0) = 1, 1 = c
    // v(t) = -t + 1
    // p(t) = (-1/2)t^2 + t + c
    // p(0) = 0, c = 0
    // p(t) = (-1/2)t^2 + t

    int steps = 11;
    double interval = 0.1;
    Expression e = new ExpressionBuilder("(-1/2)t^2 + t")
            .variables("t")
            .build();
    Double[] realVals = new Double[steps + 1];

    for(int i = 0; i <= steps; i++) {
      realVals[i] = e.setVariable("t", i * interval).evaluate();
    }

    new AccuracyTester(engines, realVals, interval, steps).print();
    
  }

}

class AccuracyTester {

  ArrayList<Timestep> timesteps = new ArrayList<Timestep>();
  Double[] realResults;

  public AccuracyTester(ArrayList<PuttingSimulator> engines, Double[] realResults, double interval, int steps) {

    this.realResults = realResults;

    // Loop i steps of each simulation
    for(int i = 0; i < steps; i++) {

      Timestep t = new Timestep();
      t.real(realResults[i]);

      for(PuttingSimulator p: engines) {
        t.add(p.engine.getClass().getName(), p.course.getBall().position.x);
        p.step(interval);
      }

      timesteps.add(t);
    }

  }

  void print() {
    System.out.print(", Real,");

    for(String key : this.timesteps.get(0).results.keySet()) {
      System.out.print(key + ",");
    }
    
    System.out.println();

    for(int i = 0; i < this.timesteps.size(); i++) {
      System.out.print(i + "," + realResults[i] + ",");
      for(String key : this.timesteps.get(i).results.keySet()) {
        System.out.print(this.timesteps.get(i).results.get(key).value + ",");
        // System.out.print(this.timesteps.get(i).results.get(key).accuracy + ",");
      }
      System.out.println();
    }
  }
}

class Timestep {

  class Result {
    double accuracy;
    double value;
    public Result(double result) {
      this.value = result;
      this.accuracy = ((result - Timestep.this.real)/result) * 100;
    }
  }

  double real;
  HashMap<String, Result> results = new HashMap<String, Result>();

  void real(double real) {
    this.real = real;
  }

  void add(String name, double result) {
    this.results.put(name, new Result(result));
  }

}
