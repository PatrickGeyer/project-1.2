import java.util.*;
import java.awt.Container; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JButton; 

import javax.swing.JTextField; 
import javax.swing.SpringLayout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseDesigner{     
    // Main Method 
    public static void main(String[] args) 
    { 
  // Creating Object of "JFrame" class 
  JFrame frame = new JFrame("MySpringDemp"); 

  // Function to set default  
  // close operation of JFrame. 
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

  // to get content pane 
  Container contentPane = frame.getContentPane(); 

  // Creating Object of "Springlayout" class 
  SpringLayout layout = new SpringLayout(); 

  // to set content pane 
  contentPane.setLayout(layout); 

  // Initialization of object  
  // "label" of JLabel class. 
  JLabel label1 = new JLabel("HEIGHT FUNCTION          : "); 
  JLabel label2 = new JLabel("FRICTION COEFFICIENT : ");  
  JLabel label3 = new JLabel("STARTING POINT             : "); 
  JLabel label4 = new JLabel("TARGET POINT                 : ");  
  JLabel label5 = new JLabel("RADIUS TARGET              : "); 
  JLabel label6 = new JLabel("MAXIMUM VELOCITY       : ");  

  // Initialization of object  
  // "label" of JLabel class. 
  JTextField textField1 = new JTextField(" z=sin(𝑥) + 𝑦^2", 20); 
  JTextField textField2 = new JTextField(" e.g. 0.053", 20); 
  JTextField textField3 = new JTextField(" (x,y)", 20); 
  JTextField textField4 = new JTextField(" (x,y,z)", 20); 
  JTextField textField5 = new JTextField(" radius", 20); 
  JTextField textField6 = new JTextField(" max", 20); 
  JButton create = new JButton("CREATE COURSE");
  JButton preview = new JButton("SHOW COURSE PREVIEW");//when preview is shown, have a 'play game' button pop up

  // to add content pane of JLabel 
  contentPane.add(label1); 
  contentPane.add(label2); 
  contentPane.add(label3); 
  contentPane.add(label4); 
  contentPane.add(label5); 
  contentPane.add(label6); 
  contentPane.add(create);
  contentPane.add(preview);

          


  // to add content pane of JTextField 
  contentPane.add(textField1); 
  contentPane.add(textField2);
  contentPane.add(textField3); 
  contentPane.add(textField4);
  contentPane.add(textField5); 
  contentPane.add(textField6);

  Color greenish = new Color(170, 255, 128);
  Color sandy = new Color(255, 221, 153);
  Color water = new Color(179, 236, 255);

  contentPane.setBackground(greenish);
  create.setBackground(water);
  preview.setBackground(water);
  textField1.setBackground(sandy);
  textField2.setBackground(sandy);
  textField3.setBackground(sandy);
  textField4.setBackground(sandy);
  textField5.setBackground(sandy);
  textField6.setBackground(sandy);
  // It is used to put the layout 
  // constraint in JFrame using springLayout class 
               
    //label1 
    layout.putConstraint(SpringLayout.WEST, label1,  
    200, SpringLayout.WEST, contentPane); 
               
    layout.putConstraint(SpringLayout.NORTH, label1,  
    56, SpringLayout.NORTH, contentPane); 
    //label2
    layout.putConstraint(SpringLayout.WEST, label2,  
    200, SpringLayout.WEST, contentPane); 
               
    layout.putConstraint(SpringLayout.NORTH, label2,  
    75, SpringLayout.NORTH, contentPane); 
    //label3
    layout.putConstraint(SpringLayout.WEST, label3,  
    200, SpringLayout.WEST, contentPane); 

    layout.putConstraint(SpringLayout.NORTH, label3,  
    96, SpringLayout.NORTH, contentPane);
    //label4
    layout.putConstraint(SpringLayout.WEST, label4,  
    200, SpringLayout.WEST, contentPane); 

    layout.putConstraint(SpringLayout.NORTH, label4,  
    116, SpringLayout.NORTH, contentPane);
    //label5
    layout.putConstraint(SpringLayout.WEST, label5,  
    200, SpringLayout.WEST, contentPane); 

    layout.putConstraint(SpringLayout.NORTH, label5,  
    136, SpringLayout.NORTH, contentPane);
    //label6
    layout.putConstraint(SpringLayout.WEST, label6,  
    200, SpringLayout.WEST, contentPane); 

    layout.putConstraint(SpringLayout.NORTH, label6,  
    156, SpringLayout.NORTH, contentPane);

    //textfield1
    layout.putConstraint(SpringLayout.WEST, textField1, 
                       1, SpringLayout.EAST, label1); 
                         
    layout.putConstraint(SpringLayout.NORTH, textField1,  
                    56, SpringLayout.NORTH, contentPane); 
                    
    layout.putConstraint(SpringLayout.EAST, contentPane,  
                        200, SpringLayout.EAST, textField1); 
                        
    layout.putConstraint(SpringLayout.SOUTH, contentPane,  
                        200, SpringLayout.SOUTH, textField1); 
    //textfield2
    layout.putConstraint(SpringLayout.WEST, textField2, 
                        1, SpringLayout.EAST, label2); 
                
    layout.putConstraint(SpringLayout.NORTH, textField2,  
                    75, SpringLayout.NORTH, contentPane); 
                
    layout.putConstraint(SpringLayout.EAST, contentPane,  
                    200, SpringLayout.EAST, textField2); 
                    
    layout.putConstraint(SpringLayout.SOUTH, contentPane,  
                    200, SpringLayout.SOUTH, textField2); 
    //textfield3
    layout.putConstraint(SpringLayout.WEST, textField3, 
                    1, SpringLayout.EAST, label2); 
    
    layout.putConstraint(SpringLayout.NORTH, textField3,  
                96, SpringLayout.NORTH, contentPane); 
    
    layout.putConstraint(SpringLayout.EAST, contentPane,  
                200, SpringLayout.EAST, textField3); 
        
    layout.putConstraint(SpringLayout.SOUTH, contentPane,  
                200, SpringLayout.SOUTH, textField3);
    //textfield4
    layout.putConstraint(SpringLayout.WEST, textField4, 
        1, SpringLayout.EAST, label2); 

    layout.putConstraint(SpringLayout.NORTH, textField4,  
        116, SpringLayout.NORTH, contentPane); 

    layout.putConstraint(SpringLayout.EAST, contentPane,  
        200, SpringLayout.EAST, textField4); 
    
    layout.putConstraint(SpringLayout.SOUTH, contentPane,  
        200, SpringLayout.SOUTH, textField4);
    //textfield5
    layout.putConstraint(SpringLayout.WEST, textField5, 
        1, SpringLayout.EAST, label2); 

    layout.putConstraint(SpringLayout.NORTH, textField5,  
        136, SpringLayout.NORTH, contentPane); 

    layout.putConstraint(SpringLayout.EAST, contentPane,  
        200, SpringLayout.EAST, textField5); 

    layout.putConstraint(SpringLayout.SOUTH, contentPane,  
        200, SpringLayout.SOUTH, textField5);
    //textfield6
    layout.putConstraint(SpringLayout.WEST, textField6, 
                  1, SpringLayout.EAST, label2); 
  
    layout.putConstraint(SpringLayout.NORTH, textField6,  
                156, SpringLayout.NORTH, contentPane); 
    
    layout.putConstraint(SpringLayout.EAST, contentPane,  
                200, SpringLayout.EAST, textField6); 
        
    layout.putConstraint(SpringLayout.SOUTH, contentPane,  
                200, SpringLayout.SOUTH, textField6);

  //create BUTTON
  layout.putConstraint(SpringLayout.WEST, create,  
                  227, SpringLayout.WEST, contentPane); 
               
  layout.putConstraint(SpringLayout.NORTH, create,  
             200, SpringLayout.NORTH, contentPane); 
  //preview BUTTON
  layout.putConstraint(SpringLayout.WEST, preview,  
                  370, SpringLayout.WEST, contentPane); 
               
  layout.putConstraint(SpringLayout.NORTH, preview,  
             200, SpringLayout.NORTH, contentPane); 

  
  // Function to pack the JFrame. 
  frame.pack(); 

  // Function to set visible status of JFrame. 
  frame.setVisible(true); 
    
  create.addActionListener(new ActionListener() { //action performed when 'create' button is clicked
    public void actionPerformed(ActionEvent e) { 
        String FORMULA = textField1.getText();
        System.out.println(FORMULA); //formula is retrieved from user
        String FrictionCo = textField2.getText();
        System.out.println(FrictionCo);
        String start = textField3.getText();
        System.out.println(start);
        String target = textField4.getText();
        System.out.println(target);
        String radius = textField5.getText();
        System.out.println(radius);
        Course c = new Course();
        c.height = radius;//////////////////////
        String maxVel = textField6.getText();
        System.out.println(maxVel); //all inputs are printed, to show it works
        //play button      save course(uses input output module to save values etc.) load course
    } //input output should
  } );//TO DO: create course object with all given values

  /*  
  public void get_ball_position(){
    return this.position;
}

public void set_ball_position(Vector2d ball_position){
    this.position = ball_position;
}

public PuttingSimulator(PuttingCourse, PhysicsEngine){
    this.PuttingCourse = PuttingCourse;
    this.PhysicsEngine = PhysicsEngine;
}
public void take_shot(Vector2d initial_ball_velocity){
    this.shot = initial_ball_velocity;
}*/

    preview.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { 
            System.out.println("show preview of the course in the right corner");
    } 
  } );


    

    } 
} 

    
