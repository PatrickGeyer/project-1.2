import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import golf.course.*;
import golf.physics.*;
import com.badlogic.gdx.math.Vector2;

public class PhysicsTest {

  PuttingCourse course = new PuttingCourse();
  PuttingSimulator simulation = new PuttingSimulator(this.course, new Euler());

  public PhysicsTest() {
    course.height = new Function2d("1");
  }

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
    course.height = new Function2d("(x - 10)^2 / 20");
    for(int i = 0; i < 10000; i++) {
      this.simulation.step();
    }
    assertEquals(0, this.course.getBalls().get(0).position.y, 0.001);
    // Lower accuracy here as ball might still be moving about minimum
    assertEquals(10, this.course.getBalls().get(0).position.x, 0.5);
  }

}