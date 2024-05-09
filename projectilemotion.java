//Projectile Motion
//Authors: Bosco Zhang and Nihal Sidhu
//Date Modified: Wednesday
//Version Number: 1.0

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
<<<<<<< HEAD
public class projectilemotion implements ActionListener, ChangeListener{
        // Properties
        JFrame theframe;
        JPanel thepanel;
        JTextField mealcost;
        JLabel costlabel;
        JButton bad;
        JLabel resultlabel;
        JSlider theslider;
        // Methods
        public void actionPerformed(ActionEvent evt){
                if(evt.getSource()== bad){
                        // Tip 5%
                        // Tax 13%
                        // Tot
                        double dblTip = Double.parseDouble(mealcost.getText())*0.05;
                        double dblTax = Double.parseDouble(mealcost.getText()) *0.13;
                        double dblTot = Double.parseDouble(mealcost.getText())+dblTip+dblTax;
                        resultlabel.setText("Shot");
                }else if(evt.getSource() == mealcost){
                        try{
                                int intVal = Integer.parseInt(mealcost.getText());
                                theslider.setValue(intVal);
                        }catch(NumberFormatException e){
                                mealcost.setText("0");
                                theslider.setValue(0);
                        }
}
        }
        public void stateChanged(ChangeEvent evt){
                if(evt.getSource() == theslider){
                        //System.out.println(theslider.getValue());
                        mealcost.setText(theslider.getValue()+"");
}
        }
        // Constructor
        public projectilemotion(){
                theframe = new JFrame("Projectile Motion");
                thepanel = new JPanel();
                thepanel.setPreferredSize(new Dimension(960, 540));
                theframe.setResizable(false);
                thepanel.setLayout(null);
                mealcost = new JTextField("0");
                mealcost.setSize(200, 50);
                mealcost.setLocation(0,0);
                mealcost.addActionListener(this);
                thepanel.add(mealcost);
                costlabel = new JLabel("Angle of launch");
                costlabel.setSize(200, 50);
                costlabel.setLocation(0,50);
                thepanel.add(costlabel);
                theslider = new JSlider(0, 90, 0);
                theslider.setSize(200, 50);
                theslider.setLocation(0, 100);
                theslider.addChangeListener(this);
                thepanel.add(theslider);
                bad = new JButton("shoot");
                bad.setSize(200, 50);
                bad.setLocation(200, 0);
                bad.addActionListener(this);
                thepanel.add(bad);
                resultlabel = new JLabel("Aiming");
                resultlabel.setSize(400, 50);
                resultlabel.setLocation(0, 150);
                thepanel.add(resultlabel);
                theframe.setContentPane(thepanel);
                theframe.pack();
                theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                theframe.setVisible(true);
}
        // Main method
        public static void main(String[] args){
                new projectilemotion();
}
=======

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


>>>>>>> 47b023ef37db14e81d2affff1d50f62e4f889c99
}
