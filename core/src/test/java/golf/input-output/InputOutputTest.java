import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import golf.InputOutput.*;
import golf.course.*;

public class InputOutputTest {

<<<<<<< HEAD
  @Test
=======
  /* @Test
>>>>>>> b3bc47bb2bbc1e2132f6c14043ad79fe731c92a2
  public void canSaveCourseTest() {       // write 
    assertThat("Hello", containsString("Hello"));
  }/*
  @Test
  public void canLoadCourseTest() {       // read
     InputOutput io = new InputOutput();
     PuttingCourse c = io.load("ExampleCourse.txt");
     assertThat(c.gravity, equals(9.81));
  }
<<<<<<< HEAD
  

  /**
   * Should read from a given file and return a PuttingCourse object
   *//*
  @Test
=======
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
>>>>>>> b3bc47bb2bbc1e2132f6c14043ad79fe731c92a2
  public void readTest() {    
    InputOutput io = new InputOutput();
    PuttingCourse course = io.read("ExampleCourse.txt");
    assertEquals(course.g, equals(9.81));
<<<<<<< HEAD
  }*/
=======
  }
>>>>>>> b3bc47bb2bbc1e2132f6c14043ad79fe731c92a2

  /**
   * Should have a PuttingCourse object as an input, transfer it 
   * to String-type and put it into a file.
<<<<<<< HEAD
   *//*
=======
   */
>>>>>>> b3bc47bb2bbc1e2132f6c14043ad79fe731c92a2
  @Test
  public void saveTest() {
    PuttingCourse c = new PuttingCourse(height, flag, start, g, frictionCoefficient, Vmax, holeTolerance);
    InputOutput oi = c.save();
    assertEquals("ExampleCourse.txt", equals(save(c)));
<<<<<<< HEAD
  }*/
=======
  }
>>>>>>> b3bc47bb2bbc1e2132f6c14043ad79fe731c92a2

}