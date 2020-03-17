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

public class UI{  
    double frictionCo; 
    String formula;
    String start;
    String target;
    double radius;
    double maxVel;
    
    public UI(double frictionCo, String formula, String start, String target, double radius, double maxVel){
        this.frictionCo = frictionCo;
        this.formula = formula;
        this.start = start;
        this.target = target;
        this.radius = radius;
        this.maxVel = maxVel;
    }  

  

     //getters
     public String getFormula(){
        return formula;
     }
     public double getFriction( ) {
        return frictionCo;
     }
     public String getStart( ) {
        return start;
     }
     public String getTarget( ) {
        return target;
     }
     public double getRadius( ) {
        return radius;
     }
     public double getMaxVel(){
         return maxVel;
     }

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


  JTextField textField1 = new JTextField(" z=sin(𝑥) + 𝑦^2", 20); 
  JTextField textField2 = new JTextField(" 0.052", 20); 
  JTextField textField3 = new JTextField(" (x,y)", 20); 
  JTextField textField4 = new JTextField(" (x,y,z)", 20); 
  JTextField textField5 = new JTextField(" 2", 20); 
  JTextField textField6 = new JTextField(" 5", 20); 
  JButton save = new JButton("SAVE COURSE");
  JButton load = new JButton("LOAD COURSE");
  JButton play = new JButton("PLAY");

  // to add content pane of JLabel 
  contentPane.add(label1); 
  contentPane.add(label2); 
  contentPane.add(label3); 
  contentPane.add(label4); 
  contentPane.add(label5); 
  contentPane.add(label6); 
  contentPane.add(save);
  contentPane.add(play);
  contentPane.add(load);

          


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
  save.setBackground(water);
  play.setBackground(water);
  load.setBackground(water);
  textField1.setBackground(sandy);
  textField2.setBackground(sandy);
  textField3.setBackground(sandy);
  textField4.setBackground(sandy);
  textField5.setBackground(sandy);
  textField6.setBackground(sandy);
  // It is used to put the layout constraint in JFrame using springLayout class 
               
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

  //save BUTTON
  layout.putConstraint(SpringLayout.WEST, save,  
                200, SpringLayout.WEST, contentPane); 
               
  layout.putConstraint(SpringLayout.NORTH, save,  
            200, SpringLayout.NORTH, contentPane); 
  //load BUTTON
  layout.putConstraint(SpringLayout.WEST, load,  
             345, SpringLayout.WEST, contentPane); 

  layout.putConstraint(SpringLayout.NORTH, load,  
             200, SpringLayout.NORTH, contentPane);

  //play BUTTON
  layout.putConstraint(SpringLayout.WEST, play,  
            489, SpringLayout.WEST, contentPane); 
               
  layout.putConstraint(SpringLayout.NORTH, play,  
             200, SpringLayout.NORTH, contentPane); 

  
  // Function to pack the JFrame. 
  frame.pack(); 

  // Function to set visible status of JFrame. 
  frame.setVisible(true); 
    
  save.addActionListener(new ActionListener() { //action performed when 'save' button is clicked
    public void actionPerformed(ActionEvent e) {  //save course(uses input output module to save values etc.)
        String formula       = textField1.getText();
        double friction      = Double.parseDouble(textField2.getText());
        String startingpoint = textField3.getText();
        String targetpoint   = textField4.getText();
        double rad           = Double.parseDouble(textField5.getText());
        double maxVel       = Double.parseDouble(textField6.getText());

        UI Course = new UI(friction, formula, startingpoint, targetpoint, rad, maxVel);
        
          //just  to show it works 
          System.out.println("radius :" + Course.getRadius() );
       }
       
  } );


    save.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { 
            //saves course
        } 
    } );
  
    play.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { 
            //let's user play course
        } 
    } );


    

    } 
} 

    
