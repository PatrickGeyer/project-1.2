package golf.course.object;
import golf.course.object.Obstacle;

public class Sandpit extends Obstacle {
  
  public boolean isSand (double x, double y){

        if (x>10 && x<17 && y>19 && y<28){ //dimension of sandpit
            return true;
        }
        else {
            return false;
        }
    }

}
