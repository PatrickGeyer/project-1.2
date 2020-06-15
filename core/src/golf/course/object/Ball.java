package golf.course.object;

import golf.course.object.GameObject;
import golf.course.CourseCallback;

public class Ball extends GameObject {
    public int shotCount = 0;
    public boolean complete = false;
    public double radius = 0.4;
    public transient CourseCallback callback = new CourseCallback();

}