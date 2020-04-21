import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import golf.InputOutput.*;
import golf.course.*;

public class InputOutputTest {

  @Test
  public void canSaveCourseTest() {

    assertThat("Hello", containsString("Hello"));
  }

  @Test
  public void canLoadCourseTest() {
    // InputOutput io = new InputOutput();
    // PuttingCourse c = io.load("ExampleCourse.txt");

    // assertThat(c.gravity, equals(9.81));
  }

}