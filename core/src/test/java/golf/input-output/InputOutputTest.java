import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import golf.inputoutput.*;
import golf.course.*;

import java.lang.Thread;
import java.io.File;
import java.net.URL;

public class InputOutputTest {

  /**
   * Should read from a given file and return a PuttingCourse object
   */
  @Test
  public void readTest() {    
    // InputOutput io = new InputOutput();
    // PuttingCourse course = io.read(System.getProperty("user.dir") + "/src/test/java/golf/input-output/ExampleCourse.txt");
    // assertEquals(course.g, equals(9.81));
  }

  /**
   * Should have a PuttingCourse object as an input, transfer it 
   * to String-type and put it into a file.
   */
  @Test
  public void saveTest() {
    PuttingCourse c = new PuttingCourse();
    c.g = 0.1;
    c.getBalls().get(0).position.x = 2;
    InputOutput io = new InputOutput();
    String path = io.save(System.getProperty("user.dir") + "/src/test/java/golf/input-output/ExampleCourseSave.txt", c);
    PuttingCourse recovered = io.read(path);
    assertEquals(0.1, recovered.g, 0);
    assertEquals(2, recovered.getBalls().get(0).position.x, 0);
  }

}
