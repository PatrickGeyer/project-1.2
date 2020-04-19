package golf.physics;
import golf.physics.*;

import net.objecthunter.exp4j.*;
import com.badlogic.gdx.math.Vector2;

// interface Function2d {
//    public double evaluate(Vector2d p);
//    public Vector2d gradient(Vector2d p);
// }

public class Function2d {
   public String function;
   public Function2d(String function) {
      this.function = function;
   }
   public double evaluate(double x, double y) {
      return evaluate(new Vector2d(x, y));
   }
   public double evaluate(Vector2 v) {
      return evaluate(new Vector2d(v.x, v.y));
   }
   
   public double evaluate(Vector2d p) {
      return new ExpressionBuilder(this.function)
            .variables("x", "y")
            .build()
            .setVariable("x", p.get_x())
            .setVariable("y", p.get_y()).evaluate();
   }
   
   public Vector2d gradient(Vector2d p, double delta) {
      double z = evaluate(p);
      return new Vector2d((z - evaluate(p.addX(delta))) * delta, (z - evaluate(p.addY(delta)) * delta));
   }

   public Vector2d gradient(double x, double y) {
      return this.gradient(new Vector2d(x, y), 0.01);
   }

}

