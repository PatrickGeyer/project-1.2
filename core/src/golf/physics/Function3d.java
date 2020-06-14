package golf.physics;
import golf.physics.*;

import net.objecthunter.exp4j.*;
import com.badlogic.gdx.math.Vector3;
import java.io.Serializable;

// interface Function2d {
//    public double evaluate(Vector2d p);
//    public Vector2d gradient(Vector2d p);
// }

public class Function3d implements Serializable {
   public String function;
   public Function3d(String function) {
      this.function = function;
   }
   public double evaluate(double x, double y, double z) {
      return evaluate(new Vector3((float) x, (float) y, (float) z));
   }
   
   public double evaluate(Vector3 p) {
      return new ExpressionBuilder(this.function)
            .variables("x", "y", "z")
            .build()
            .setVariable("x", p.x)
            .setVariable("y", p.y)
            .setVariable("z", p.z).evaluate();
   }
   
   public Vector3 gradient(Vector3 p, double delta) {
      double val = evaluate(p);
      return new Vector3(
         (float) ((evaluate(new Vector3(p).add((float) delta, 0, 0)) - val) / delta),
         (float) ((evaluate(new Vector3(p).add(0, (float) delta, 0)) - val) / delta),
         (float) ((evaluate(new Vector3(p).add(0, 0, (float) delta)) - val) / delta)
      );
   }

   public Vector3 gradient(double x, double y, double z) {
      return this.gradient(new Vector3((float) x, (float) y, (float) z), 0.01);
   }

}

