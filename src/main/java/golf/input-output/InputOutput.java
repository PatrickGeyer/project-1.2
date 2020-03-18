package golf.inputOutput;
import UI;

import java.util.Scanner;
import java.io.*;
import golf.course.*;

public class InputOutput{
    public static void main(String[] args) {
    }
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
}
