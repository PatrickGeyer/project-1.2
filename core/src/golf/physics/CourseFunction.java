package golf.physics;
import golf.physics.*;
import net.objecthunter.exp4j.*;

public class CourseFunction implements Function2d {
   public String function;
   
   public Function2d(String function) {
      this.function = function;
   }

   public double evaluate(Vector2d p) {
      return new ExpressionBuilder(this.function)
            .variables("x", "y")
            .build()
            .setVariable("x", p.get_x())
            .setVariable("y", p.get_y()).evaluate();
   }

   public double evaluate(double x, double y) {
      return evaluate(new Vector2d(x, y));
   }

   public Vector2d gradient(Vector2d p) {
      double z = evaluate(p);
      double pdx = evaluate(new Vector2d(p.get_x()+DELTA, p.get_y()));
      double pdy = evaluate(new Vector2d(p.get_x(), p.get_y()+DELTA));
      return new Vector2d((pdx-z)/DELTA, (pdy-z)/DELTA);
   }
}