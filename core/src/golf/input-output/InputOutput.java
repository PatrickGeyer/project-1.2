package golf.InputOutput;

import java.io.*;
import java.util.Scanner;
import golf.course.*;

/**
 * input-output module to read or write courses from or to a file
 */

public class InputOutput {/*

    PuttingCourse object = new PuttingCourse();
    String filename = "ExampleCourse.txt";
    public PuttingCourse read(String filename) {
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
    public void save(PuttingCourse c) {
        
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(object);
            output.close();
            file.close();
        }
        catch (IOException io) {
            io.printStackTrace();
            System.out.println("An error occurred.");
        }
    }*/
/**
this should ideally be a method on the PuttingCourse class */
    // public String toString() {
    //     return "Course {  height = " + height + 
    //     ", flag = " + flag + 
    //     ", start = " + start + 
    //     ", g = " + g + 
    //     ", frictionCoefficient = " + frictionCoefficient + 
    //     ", Vmax = " + Vmax + 
    //     ", holeTolerance = " + holeTolerance +
    //     "}";     
    //     }

        

}