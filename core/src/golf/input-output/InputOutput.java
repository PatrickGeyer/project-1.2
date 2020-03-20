

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
// import golf.course.*;

public class InputOutput {
   
   double gravitationalAcc;
   double ballMass;
   double friction;
   double maxBallSpeed;
   double distance;
   String startPoint;
   String targetPoint;
   String formula;

   public void save(Course c) {
      try {
          FileWriter writer = new FileWriter("MyFile.txt", true);
          writer.write("gravitational acceleration = " + Course.gravitationalAcc);
          writer.write("\r\n");   // write new line
          writer.write("The mass of the ball = " + Course.ballMass);
          writer.write("\r\n");   // write new line
          writer.write("The coefficient of friction = " + Course.friction);
          writer.write("\r\n");   // write new line
          writer.write("The maximum initial ball speed = " + Course.maxBallSpeed);
          writer.write("\r\n");   // write new line
          writer.write("The distance from hole for a successful putt = " + Course.distance);
          writer.write("\r\n");   // write new line
          writer.write("The starting coordinate= " + Course.startPoint);
          writer.write("\r\n");   // write new line
          writer.write("The goal coordinate = " + Course.targetPoint);
          writer.write("\r\n");   // write new line
          writer.write("The height function = " + Course.formula);
          writer.write("\r\n");   // write new line
          writer.close();

      } catch (IOException e) {
          e.printStackTrace();
      } 
  }






   //like this?
   public Course read(String f){
      try {
         File file = new File(f);
         Scanner sc = new Scanner(file);
         while(sc.hasNextLine()||sc.hasNextDouble()){
            String[] split = sc.nextLine().split("=");
            String[] line = split[0]
            String[] property = split[1];
         }
         sc.close();
   }catch (FileNotFoundException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
      Course c = new Course(gravitationalAcc, ballMass, friction, maxBallSpeed, distance, startPoint, targetPoint, formula);
      return c;

      for(int i=0; i<8; i++){
      Course[property[i]] = line[i]; //loops through th e properties in the course object to replace them with the values from the file
      }
   }

/*

   public Course read(String f) {
      try {
         File file = new File(f);
         Scanner sc = new Scanner(file);

         if (sc.hasNextDouble()) {
            double data = sc.nextDouble();
            gravitationalAcc = data;
            System.out.println(gravitationalAcc);
            
            if (sc.hasNextDouble()) {
               double dataTwo = sc.nextDouble();
               ballMass = dataTwo;
               System.out.println(ballMass);
               
               if (sc.hasNextDouble()) {
                  double dataThree = sc.nextDouble();
                  friction = dataThree;
                  
                  if (sc.hasNextDouble()) {
                     double dataFour = sc.nextDouble();
                     maxBallSpeed = dataFour;
                     
                     if (sc.hasNextDouble()) {
                        double dataFive = sc.nextDouble();
                        distance = dataFive;
                        
                        if (sc.hasNextLine()) {
                           String dataSix = sc.nextLine();
                           startPoint = dataSix;
                           
                           if (sc.hasNextLine()) {
                              String dataSeven = sc.nextLine();
                              targetPoint = dataSeven;

                              if (sc.hasNextLine()) {
                                 String dataEight = sc.nextLine();
                                 formula = dataEight;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
         sc.close();

      } catch (FileNotFoundException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
      }

      Course c = new Course(gravitationalAcc, ballMass, friction, maxBallSpeed, distance, startPoint, targetPoint, formula);
      return c;
   }*/
}
