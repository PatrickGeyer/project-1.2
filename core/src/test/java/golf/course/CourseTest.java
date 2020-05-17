import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

import golf.course.*;
import golf.ai.*;
import golf.physics.*;
import com.badlogic.gdx.math.Vector2;

public class CourseTest {

  PuttingSimulator simulation = new PuttingSimulator(new PuttingCourse(), new Euler());

  public CourseTest() {
  }

  @Test
  public void cloneTest() {
    simulation.take_shot(new Vector2d(5, 5));
    simulation.step_until_next_shot();
    Vector2d firstPos = new Vector2d(simulation.course.getBall().position);
    // System.out.println(firstPos);
    PuttingSimulator simulationCopy = simulation.clone();
    Vector2d clonedPos = new Vector2d(simulationCopy.course.getBall().position);
    // System.out.println(clonedPos);
    assertThat(clonedPos, equalTo(firstPos));
    assertThat(simulationCopy != simulation, equalTo(true));

    simulationCopy.course.getBall().position.x = 1000;
    // System.out.println(simulation.course.getBall().position.x);
    assertEquals(simulation.course.getBall().position.x, firstPos.x, 0.001);
  }

}
