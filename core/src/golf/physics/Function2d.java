package golf.physics;

import net.objecthunter.exp4j.*;

// interface Function2d {
//    public double evaluate(Vector2d p);
//    public Vector2d gradient(Vector2d p);
// }

public class Function2d {
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
   public Vector2d gradient(Vector2d p) {
      return new Vector2d(0,0);
   }

   public double partialDerivativeX(double x, double y) {
      return 0.0;
   }
   public double partialDerivativeY(double x, double y) {
      return 0.0;
   }

}