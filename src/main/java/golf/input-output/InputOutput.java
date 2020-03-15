package golf.inputOutput;

import java.util.Scanner;
import java.io.*;
import golf.course.*;

public class InputOutput {
    // Aliases from saved file to Course object property names
    String[][] aliases = {{"m", "ballMass"}, {"mu", "frictionCoefficient"}, {"tol", "goalTolerance"}};

    public void save(Course c) {
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

    public Course read(String f) throws Exception {

        // pass the path to the file as a parameter 
        File file = new File(f); 
        Scanner sc = new Scanner(file); 
  
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

        } 
        Course c = new Course();
        return c;
    }
}