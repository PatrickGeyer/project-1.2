package golf;

import golf.course.*;
import golf.physics.*;
import golf.visualization.*;

public class Golf {
  public static void main(String[] args) {
    Course c = new Course();
    PhysicsEngine p = new EulerSolver(0.01);

    Visualization v = new Visualization(c, p);
  }
}