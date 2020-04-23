import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import golf.course.*;
import golf.physics.*;
import com.badlogic.gdx.math.Vector2;

public class PhysicsTest {

  PuttingCourse course = new PuttingCourse();
  PuttingSimulator simulation = new PuttingSimulator(this.course, new Euler());

  @Test
  public void frictionTest() {
    assertThat("Hello", containsString("Hello"));
  }

  @Test
  public void gravityTest() {
    assertThat("Hello", containsString("Hello"));
  }

  @Test
  public void inclineTest() {
    assertThat("Hello", containsString("Hello"));
  }

  @Test
  public void velocityTest() {
    this.simulation.take_shot(this.course.getBalls().get(0), new Vector2(0, 1));
    this.simulation.step();
    assertEquals(0.01, this.course.getBalls().get(0).position.y, 0.001);
  }

}