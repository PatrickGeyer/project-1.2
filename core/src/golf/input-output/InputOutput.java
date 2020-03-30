

   /* // Aliases from saved file to Course object property names     
    String[][] aliases = {{"m", "ballMass"}, {"mu", "frictionCoefficient"}, {"tol", "goalTolerance"}};

    public void save(UI Course) {
        try {
            FileWriter writer = new FileWriter("MyFile.txt", true);
            writer.write("Hello World");
            writer.write("\r\n");   // write new line
            writer.write("Good Bye!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UI read(String f) throws Exception {

        // pass the path to the file as a parameter 
        File file = new File(f); 
        Scanner sc = new Scanner(file); 
  
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

        } 
        UI Course = new UI();
        return Course;
    }*/

//package golf.inputOutput;

import java.util.Scanner;
import java.io.*;
import golf.course.*;

public class InputOutput {
   
   double gravitationalAcc;
   double ballMass;
   double friction;
   double maxBallSpeed;
   double distance;
   String startPoint;
   String targetPoint;
   String formula;

   public static void save(PuttingCourse c) {
      try {
          FileWriter writer = new FileWriter("MyFile.txt", true);
         //  writer.write("gravitational acceleration = " + c.gravitationalAcc);
         //  writer.write("\r\n");   // write new line
         //  writer.write("The mass of the ball = " + c.ballMass);
         //  writer.write("\r\n");   // write new line
         //  writer.write("The coefficient of friction = " + c.friction);
         //  writer.write("\r\n");   // write new line
         //  writer.write("The maximum initial ball speed = " + c.maxBallSpeed);
         //  writer.write("\r\n");   // write new line
         //  writer.write("The distance from hole for a successful putt = " + c.distance);
         //  writer.write("\r\n");   // write new line
         //  writer.write("The starting coordinate= " + c.startPoint);
         //  writer.write("\r\n");   // write new line
         //  writer.write("The goal coordinate = " + c.targetPoint);
         //  writer.write("\r\n");   // write new line
         //  writer.write("The height function = " + c.formula);
         //  writer.write("\r\n");   // write new line
          writer.close();

      } catch (IOException e) {
          e.printStackTrace();
      } 
  }






   //like this?
   public static PuttingCourse read(String f){
      try {
         File file = new File(f);
         Scanner sc = new Scanner(file);
         while(sc.hasNextLine()||sc.hasNextDouble()){
            String[] split = sc.nextLine().split("=");
            String line = split[0];
            String property = split[1];
         }
         sc.close();
   }catch (FileNotFoundException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
      PuttingCourse c = new PuttingCourse(); //gravitationalAcc, ballMass, friction, maxBallSpeed, distance, startPoint, targetPoint, formula
      return c;

      // for(int i=0; i<8; i++){
      // c[property[i]] = line[i]; //loops through th e properties in the course object to replace them with the values from the file
      // }
   }
}
