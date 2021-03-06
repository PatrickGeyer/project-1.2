package golf.physics;
import golf.physics.*;

import net.objecthunter.exp4j.*;
import com.badlogic.gdx.math.Vector2;
import java.io.Serializable;

// interface Function2d {
//    public double evaluate(Vector2d p);
//    public Vector2d gradient(Vector2d p);
// }

public class Function2d implements Serializable {
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
      return new Vector2d(
         (evaluate(new Vector2d(p).addX(delta)) - z) / delta, 
         (evaluate(new Vector2d(p).addY(delta)) - z) / delta
      );
   }

   public Vector2d gradient(double x, double y) {
      return this.gradient(new Vector2d(x, y), 0.01);
   }

}

