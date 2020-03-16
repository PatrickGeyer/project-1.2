package golf.physics;

interface Function2d {
   public double evaluate(Vector2d p);
   public Vector2d gradient(Vector2d p);
}

public class Function2d {
   public String function;
   public Function2d(String function) {
      this.function = function;
   }
   public double evaluate(Vector2d p) {
      return new ExpressionBuilder(this.function)
            .variables("x", "y")
            .build()
            .setVariable("x", x)
            .setVariable("y", y).evaluate();
   }
   public Vector2d gradient(Vector2d p) {

   }
}