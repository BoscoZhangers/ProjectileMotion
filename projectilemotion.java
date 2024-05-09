//Projectile Motion
//Authors: Bosco Zhang and Nihal Sidhu
//Date Modified: Wednesday
//Version Number: 1.0

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ProjectileMotion implements ActionListener, ChangeListener{
       
// Properties
JFrame MainWindow;
JPanel HomeAnimationPanel;
JPanel Animation;
JMenuBar MenuBar;
JMenu Menu;
JMenuItem Help;
JMenuItem Quiz;
JMenuItem Leaderboard;
JSlider Angle;
JSlider InitialHeight;
JSlider InitialVelocity;
JCheckBox ShowVecComponents;
JButton Ball;
JButton Baby;
JButton Apple;

//Ask how to set rotations of images (for angling)
//Frame.validate;



// Methods
public void actionPerformed(ActionEvent evt){

}

public void stateChanged(ChangeEvent evt){

}


// Constructor
public ProjectileMotion(){
MainWindow = new JFrame("Projectile Motion");
HomeAnimationPanel = new JPanel();
//Creating GUI components

HomeAnimationPanel.setPreferredSize(new Dimension(960, 540));
HomeAnimationPanel.setLayout(null);
MainWindow.setContentPane(HomeAnimationPanel);

//Menu Components
MenuBar = new JMenuBar();
Menu = new JMenu("Menu");
Help = new JMenuItem("Help");
Quiz = new JMenuItem("Quiz");
Leaderboard = new JMenuItem("Leaderboard");

Menu.add(Help);
Menu.add(Quiz);
Menu.add(Leaderboard);
MenuBar.add(Menu);
MainWindow.setJMenuBar(MenuBar);

Angle = new JSlider(-90,90,45);
Angle.setSize(300, 50);
Angle.setLocation(0,460);
Angle.setPaintTicks(true);
Angle.setPaintLabels(true);
Angle.setSnapToTicks(true);
Angle.setMinorTickSpacing(15);
Angle.setLabelTable(Angle.createStandardLabels(15));
Angle.addChangeListener(this);
HomeAnimationPanel.add(Angle);

InitialHeight = new JSlider(0,100);
InitialHeight.setSize(50,200);
InitialHeight.setLocation(50, 200);
InitialHeight.setOrientation(SwingConstants.VERTICAL);
InitialHeight.addChangeListener(this);
HomeAnimationPanel.add(InitialHeight);

ShowVecComponents = new JCheckBox("Show Vector Components");
ShowVecComponents.setSize(200,50);
ShowVecComponents.setLocation(0,0);
HomeAnimationPanel.add(ShowVecComponents);


MainWindow.pack();
MainWindow.setResizable(false);
MainWindow.setVisible(true);
MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//Modifying main GUI components (size, location, layouts, user interface settings, etc..) for panels and frames...



//SET THE BUTTON TO NON-ADJUSTABLE ONCE CLICKED!!
//USE A GRID AS THE BACKGROUND

}

// Main Program
public static void main(String [] args){
new ProjectileMotion();
}

}


