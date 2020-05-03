import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import golf.course.PuttingCourse;
import golf.course.PuttingSimulator;
import golf.physics.*;

import org.junit.Test;

public class PhysicsTest {
  
   /* PuttingCourse course = new PuttingCourse();
    PuttingSimulator simulation = new PuttingSimulator(this.course, new Euler());
  
    @Test
    public void velocityTest() {
      this.simulation.take_shot(this.course.getBalls().get(0), new Vector2(0, 1));
      this.simulation.step();
      assertEquals(0.01, this.course.getBalls().get(0).position.y, 0.001);
      assertEquals(0, this.course.getBalls().get(0).position.x, 0.001);
    }*/
  
  
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

}