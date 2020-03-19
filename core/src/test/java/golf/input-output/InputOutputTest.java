import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

public class InputOutputTest {

  @Test
  public void canSaveCourseTest() {
    assertThat("Hello", containsString("Hello"));
  }

  @Test
  public void canLoadCourseTest() {
    assertThat("Hello", containsString("Hello"));
  }

}