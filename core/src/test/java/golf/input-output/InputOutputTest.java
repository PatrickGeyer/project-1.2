import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import golf.InputOutput.*;
import golf.course.*;

public class InputOutputTest {

  /* @Test
  public void canSaveCourseTest() {       // write 
    assertThat("Hello", containsString("Hello"));
  }
  @Test
  public void canLoadCourseTest() {       // read
    // InputOutput io = new InputOutput();
    // PuttingCourse c = io.load("ExampleCourse.txt");
    // assertThat(c.gravity, equals(9.81));
  }
  */

  /**
   * Should read from a given file and return a PuttingCourse object
   */
  @Test
  public void readTest() {    
    // InputOutput io = new InputOutput();
    // PuttingCourse course = io.read("ExampleCourse.txt");
    // assertEquals(course.g, equals(9.81));
  }

  /**
   * Should have a PuttingCourse object as an input, transfer it 
   * to String-type and put it into a file.
   */
  @Test
  public void saveTest() {
    // PuttingCourse c = new PuttingCourse(height, flag, start, g, frictionCoefficient, Vmax, holeTolerance);
    // InputOutput oi = c.save();
    // assertEquals("ExampleCourse.txt", equals(save(c)));
  }

}
