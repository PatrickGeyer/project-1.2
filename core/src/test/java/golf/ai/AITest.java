import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

import golf.course.*;
import golf.ai.*;
import golf.physics.*;
import com.badlogic.gdx.math.Vector2;

public class AITest {

  PuttingCourse course = new PuttingCourse();
  PuttingSimulator simulation = new PuttingSimulator(this.course, new EulerSolver());

  public AITest() {
    course.height = new Function2d("1");
    course.Vmax = 2;
    course.flag = new Vector2d(1,1);
  }

  // @Test
  // public void flatTest() {
  //   AI ai = new AI(2);
  //   ai.restrictAngle = 1;
  //   simulation.take_shot(ai.calculate_shot(simulation));
  //   simulation.step_until_next_shot();
  //   // System.out.println(course.getBall().position.toString());
  //   System.out.println(ai.possibilitiesExamined);
  //   assertThat(course.getBall().complete, equalTo(true));
  // }

// @Test
// public void AIBranchingTest() {

//     course.height = new Function2d("1");
//     course.Vmax = 2;
//     course.flag = new Vector2d(2,2);

//     ArrayList<AIBranchingTest> tests = new ArrayList();
//     ArrayList<Integer> angles = new ArrayList();
//     ArrayList<Integer> branches = new ArrayList();
//     angles.add(0);
//     angles.add(180);
//     angles.add(90);
//     branches.add(1);
//     branches.add(2);

//     for(Integer angle : angles) {
//       for(Integer branch : branches) {
//         AIBranchingTest t1 = new AIBranchingTest();
//         AIBranchingTest t2 = new AIBranchingTest();
//         t1.ai = new AI(branch);
//         t2.ai = new AI(branch);

//         t1.ai.restrictAngle = t2.ai.restrictAngle = angle;
//         t1.ai.returnOnHole = true;
//         t2.ai.returnOnHole = false;

//         tests.add(t1);
//         tests.add(t2);
//       }
//     }

//     int iterations = 1;
//     for(AIBranchingTest test : tests) {
//       for(int i = 0; i < iterations; i++) {
//         PuttingCourse c = course.clone();
//         PuttingSimulator sim = new PuttingSimulator(c, new EulerSolver());
//         try {
//           int shots = sim.play_until_done(test.ai);
//           test.shotsTaken.add(shots);
//         } catch(Exception e) {
//           test.fails++;
//         }
//       }
//       System.out.println("Layers,Restrict Angle,Return On Hole,NumberOfShots,Examined Simulations");
//       System.out.println(test.ai.layers + "," + test.ai.restrictAngle + "," + test.ai.returnOnHole + "," + test.getAvg() + "," + test.ai.possibilitiesExaminedTotal);
//     }
//     System.out.println("Done");
//   }

  @Test
  public void windTest() {

    course.height = new Function2d("1");
    course.Vmax = 2;
    course.frictionCoefficient = 0.5;
    course.flag = new Vector2d(10,10);

    ArrayList<WindTest> tests = new ArrayList();

    for(double x = 0; x <= 1; x+=0.05) {
      tests.add(new WindTest(x));
    }

    int iterations = 1;
    AI ai = new AI(1);
    for(WindTest test : tests) {

      for(int i = 0; i < iterations; i++) {
        PuttingCourse c = course.clone();
        c.wind = new Function3d("sin(x) * cos(y) * cos(z)");
        c.windIntensity = test.intensity;
        PuttingSimulator sim = new PuttingSimulator(c, new EulerSolver());
        try {
          int shots = sim.play_until_done(ai);
          test.shotsTaken.add(shots);
        } catch(Exception e) {
          test.fails++;
        }
      }
      System.out.println("Intensity: " + test.intensity + ", average shots: " + test.getAvg());
    }
  }

}

class WindTest {
  double intensity = 0;
  int fails = 0;
  ArrayList<Integer> shotsTaken = new ArrayList<Integer>();
  public WindTest(double i) {
    this.intensity = i;
  }
  public double getAvg() {
    return (double) shotsTaken.stream().mapToDouble(a -> a).average().orElse(-1);
  }
}

class AIBranchingTest {
  public AI ai;
  int fails = 0;

  ArrayList<Integer> timePerShot = new ArrayList<Integer>();
  ArrayList<Integer> shotsTaken = new ArrayList<Integer>();

  public double getAvg() {
    return (double) shotsTaken.stream().mapToDouble(a -> a).average().orElse(-1);
  }
  public double getAvgTime() {
    return (double) timePerShot.stream().mapToDouble(a -> a).average().orElse(-1);
  }

}