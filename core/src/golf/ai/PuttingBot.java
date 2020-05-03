package golf.ai;
import golf.course.*;
import golf.physics.*;

public interface PuttingBot {
    Vector2d shot_velocity(PuttingCourse course, Vector2d ball_position);
}
