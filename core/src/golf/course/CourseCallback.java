package golf.course;

import golf.course.object.Ball;

public class CourseCallback {

    public void onShotFailed() {

    }

    public void onShotFailed(Ball b) {
        this.onShotFailed();
    }

    public void onAfterShot() {

    }

    public void onAfterShot(Ball b) {
        this.onAfterShot();
    }

    public void onBeforeShot() {

    }

    public void onBeforeShot(Ball b) {
        this.onBeforeShot();
    }

    public void onHole() {

    }

    public void onHole(Ball b) {
        this.onHole();
    }

}
