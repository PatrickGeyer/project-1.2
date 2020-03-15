package hello;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

public class PhysicsTest {

  @Test
  public void testTest() {
    assertThat("Hello", containsString("Hello"));
  }

}