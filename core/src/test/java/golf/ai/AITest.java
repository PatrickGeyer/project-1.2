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

  @Test
  public void flatTest() {
    AI ai = new AI(2);
    ai.restrictAngle = 1;
    simulation.take_shot(ai.calculate_shot(simulation));
    simulation.step_until_next_shot();
    // System.out.println(course.getBall().position.toString());
    // System.out.println(course.flag.toString());
    assertThat(course.getBall().complete, equalTo(true));
  }

// @Test
// public void AIBranchingTest() {

//     course.height = new Function2d("1");
//     course.Vmax = 2;
//     course.flag = new Vector2d(10,10);

//     ArrayList<AIBranchingTest> tests = new ArrayList();
//     tests.add(new AIBranchingTest(1));
//     tests.add(new AIBranchingTest(2));

//     int iterations = 1;
//     for(AIBranchingTest test : tests) {
//       AI ai = new AI(test.layers);
//       for(int i = 0; i < iterations; i++) {
//         PuttingCourse c = course.clone();
//         PuttingSimulator sim = new PuttingSimulator(c, new EulerSolver());
//         try {
//           int shots = sim.play_until_done(ai);
//           test.shotsTaken.add(shots);
//         } catch(Exception e) {
//           test.fails++;
//         }
//       }
//       System.out.println("Intensity: " + test.intensity + ", average shots: " + test.getAvg());
//     }
//   }

//   @Test
//   public void windTest() {

//     course.height = new Function2d("1");
//     course.Vmax = 2;
//     course.flag = new Vector2d(10,10);

//     ArrayList<WindTest> tests = new ArrayList();

//     for(double x = 0; x <= 1; x+=0.2) {
//       tests.add(new WindTest(x));
//     }

//     int iterations = 1;
//     AI ai = new AI(2);
//     for(WindTest test : tests) {
//       for(int i = 0; i < iterations; i++) {
//         PuttingCourse c = course.clone();
//         c.windIntensity = test.intensity;
//         PuttingSimulator sim = new PuttingSimulator(c, new EulerSolver());
//         try {
//           int shots = sim.play_until_done(ai);
//           test.shotsTaken.add(shots);
//         } catch(Exception e) {
//           test.fails++;
//         }
//       }
//       System.out.println("Intensity: " + test.intensity + ", average shots: " + test.getAvg());
//     }
//   }

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
  int layers = 1;
  int fails = 0;
  ArrayList<Integer> timePerShot = new ArrayList<Integer>();
  ArrayList<Integer> shotsTaken = new ArrayList<Integer>();
  public AIBranchingTest(int l) {
    this.layers = l;
  }
  public double getAvg() {
    return (double) shotsTaken.stream().mapToDouble(a -> a).average().orElse(-1);
  }
  public double getAvgTime() {
    return (double) timePerShot.stream().mapToDouble(a -> a).average().orElse(-1);
  }
}