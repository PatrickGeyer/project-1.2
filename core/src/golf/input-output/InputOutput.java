package golf.InputOutput;

import golf.course.*;

import java.io.*;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;
import java.io.File;
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

    public PuttingCourse openDialog() {
        JFileChooser fc = new JFileChooser();

        String path = "";
        // fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                path = file.getAbsolutePath();
            } catch (Exception ex) {
                System.out.println("problem accessing file" + file.getAbsolutePath() + "\n" + ex.getMessage());
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
        return read(path);
    }

    public void saveDialog(PuttingCourse c) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            // System.out.println("Save as file: " + );
            this.save(fileToSave.getAbsolutePath(), c);
        }
    }
}
