package golf.InputOutput;

import java.io.*;
import java.util.Scanner;
import golf.course.*;
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 * input-output module to read or write courses from or to a file
 */

public class InputOutput {


    public PuttingCourse read(String filename) {
        PuttingCourse object = new PuttingCourse();

        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream input = new ObjectInputStream(file);
            object = (PuttingCourse)input.readObject();
    
            input.close();
            file.close();
        }
        catch (IOException io) {
            System.out.println("An error occurred.");
            io.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return object;
    }

    public String save(String path, PuttingCourse c) {

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy-hhmmss");
        // FileOutputStream file = new FileOutputStream("Game-" + sdf.format(new Date()) + ".txt");
        try {
            FileOutputStream file = new FileOutputStream(new File(path));
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(c);
            output.close();
            file.close();
        }
        catch (IOException io) {

            io.printStackTrace();
            System.out.println("An error occurred.");
        }
        return path;
    }

}
