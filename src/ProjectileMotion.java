// =============================================================================
// Projectile Motion
// Authors: Bosco Zhang and Nihal Sidhu
// Last Modified: Friday, May 17, 2024
// Version Number: 6.0 Beta 
// =============================================================================

// Importing all Libraries and Java Toolkits
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.event.*;
import javax.swing.event.*;

// Defining main class and implementing Java event listeners
public class ProjectileMotion implements ActionListener, ChangeListener {
	static double VERSION = 3.0;
        
	// Properties =============================================================

	//Frames and Panels
	JFrame MainWindowFrame;
	AnimationPanel AnimPanel;
	HelpPanel HelpPANEL;
	AboutPanel AboutP;	

	// Animation Timer (48 FPS) 
	Timer AnimTimer = new Timer(1000/48, this);
	Timer AirTimer = new Timer(1, this);
	int intMilSec = 0;
	
	//Menu Components
	JMenuBar MenuBar;
	JMenu Menu;
	JMenuItem Simulation;
	JMenuItem About;
	JMenuItem Help;

	// Projectile Dynamic Properties & Initialization
	double dblInitialHeight = 0.0;
	double dblLaunchAngle = 60.0;
	double dblInitialV = 60.0;
	double dblInitialVX = 51.96;
	double dblInitialVY = 30.0;

	// Labels
	JLabel SelectProjectileLabel;
	JLabel AngleLabel;
	JLabel InitialHeightLabel;
	JLabel InitialVelocityLabel;
	JLabel GravityLabel;
	
	// Text Fields
	JTextField AngleTextField;
	JTextField InitialVelocityTextField;

	// Sliders
	JSlider AngleSlider;
	JSlider InitialHeightSlider;
	JSlider InitialVelocitySlider;
	
	// CheckBoxes
	JCheckBox ShowVecComponents;
	
	// Buttons
	JButton LaunchButton;

	// DropDown (Combo Boxes)
	JComboBox ChooseProjectileComboBox;
	
	// Results Screen:
	JLabel AirTime;
	JLabel Range;
	JButton OKButton;

	// Help Panel:
	JButton RightButton;
	JButton LeftButton;

	// METHODS =============================================================================
	
	// ActionListener-Compatible Event Methods: Overriding actionPerformed interface method
	public void actionPerformed(ActionEvent evt){
		if (evt.getSource() == About){
			AboutP.repaint();
			AboutP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			MainWindowFrame.setContentPane(AboutP);
			MainWindowFrame.revalidate();
			MainWindowFrame.setLayout(new ScrollPaneLayout());
			// Setting up the "About Panel" when user selects "About" from Menu

		} else if (evt.getSource() == Help){
			HelpPANEL.intPageNum = 1;
			HelpPANEL.repaint();	
			MainWindowFrame.setContentPane(HelpPANEL);
			MainWindowFrame.validate();
			// Setting up the "Help Panel" when user selects "Help" from Menu
				
			LeftButton.setEnabled(false);
			RightButton.setEnabled(true);
			// Set to be false since the intPageNum always resets back to 1 whenever HelpPANEL is entered from the JMenu.
			// Resetting right button to fix runtime exception: user exits HelpPANEL on page 6 when RightButton is disabled. 
			// Re-opening help panel will leave both left and right buttons disabled. 

		} else if (evt.getSource() == RightButton){

			HelpPANEL.intPageNum = HelpPANEL.intPageNum + 1;

			if (HelpPANEL.intPageNum == 6) {
				RightButton.setEnabled(false);
			} else {
				RightButton.setEnabled(true);
			} 
			
			if (HelpPANEL.intPageNum > 1) {
				LeftButton.setEnabled(true);
			}
			HelpPANEL.repaint();
			// Executes "flip page ~ forward" by adjusting the intPageNum variable as read on the HelpPanel class. 

		} else if (evt.getSource() == LeftButton){
			HelpPANEL.intPageNum = HelpPANEL.intPageNum - 1;
			
			if (HelpPANEL.intPageNum == 1){
				LeftButton.setEnabled(false);
			} else {
				LeftButton.setEnabled(true);
			} 
			
			if (HelpPANEL.intPageNum < 6){
				RightButton.setEnabled(true);
			}
			HelpPANEL.repaint();
			// Executes "flip page ~ back" by adjusting the intPageNum variable as read on the HelpPanel class.		
	
		} else if (evt.getSource() == Simulation){
			MainWindowFrame.setContentPane(AnimPanel);
			MainWindowFrame.validate();
			// Setting up the "Animation Panel" when user selects "Simulation" from Menu

		} else if (evt.getSource() == InitialVelocityTextField){
			try{
				InitialVelocitySlider.setValue(Integer.parseInt(InitialVelocityTextField.getText()));
				dblInitialV = (double) InitialVelocitySlider.getValue();
                this.VCalc(dblInitialV, dblLaunchAngle);
				//Accepting user input values for Initial Velocity from a JTextField
			} catch (NumberFormatException e) {
				InitialVelocityTextField.setText("10");
				InitialVelocitySlider.setValue(10);
				dblInitialV = (double) InitialVelocitySlider.getValue();
				this.VCalc(dblInitialV, dblLaunchAngle);
				//Defaulting all incompatible input values to a minimal velocity magnitude of 10
			} if (Integer.parseInt(InitialVelocityTextField.getText()) < 10) {
				InitialVelocityTextField.setText("10");
				InitialVelocitySlider.setValue(10);
				dblInitialV = (double) InitialVelocitySlider.getValue();
				this.VCalc(dblInitialV, dblLaunchAngle);
				// Defaulting all undervalued inputs to 10
			} else if (Integer.parseInt(InitialVelocityTextField.getText()) > 100) {
				InitialVelocityTextField.setText("100");
				InitialVelocitySlider.setValue(100);
				dblInitialV = (double) InitialVelocitySlider.getValue();
				this.VCalc(dblInitialV, dblLaunchAngle);
				// Defaulting all overvalued inputs to 100
			}
		} else if (evt.getSource() == AngleTextField) {
			try{
				AngleSlider.setValue(Integer.parseInt(AngleTextField.getText()));
				dblLaunchAngle = (double) AngleSlider.getValue();
				this.VCalc(dblInitialV, dblLaunchAngle);
				// Accepting user input values for Launch Angle from a JTextField
			} catch (NumberFormatException e) {
				AngleTextField.setText("60");
				AngleSlider.setValue(60);
				dblLaunchAngle = (double) AngleSlider.getValue();
				this.VCalc(dblInitialV, dblLaunchAngle);
				// Defaulting all incompatible input values to an arbitrary launch angle of 60 degrees	
			}
			if (Integer.parseInt(AngleTextField.getText()) < -90) {
				AngleTextField.setText("-90");
				AngleSlider.setValue(-90);
				dblLaunchAngle = (double) AngleSlider.getValue();
				this.VCalc(dblInitialV, dblLaunchAngle);
				// Defaulting all undervalued inputs to -90 degrees
			} else if (Integer.parseInt(AngleTextField.getText()) > 90) {
				AngleTextField.setText("90");
				AngleSlider.setValue(90);
				dblLaunchAngle = (double) AngleSlider.getValue();
				this.VCalc(dblInitialV, dblLaunchAngle);
 				//Defaulting all overvalued inputs to 90 degrees
			}
		} else if (evt.getSource() == ChooseProjectileComboBox) {
			AnimPanel.strProjectile = String.valueOf(ChooseProjectileComboBox.getSelectedItem());
			AnimPanel.repaint();
			// Transfering a user-invoked projectile change signal to AnimationPanel via AnimPanel.strProjectile
		} else if (evt.getSource() == ShowVecComponents){
			if (ShowVecComponents.isSelected()==true) {
				AnimPanel.blnShowVectors = true;
				AnimPanel.repaint();
				// System.out.println("Showing Vectors");
				// Transmitting user-invoked request to show vector components via AnimPanel.blnShowVectors *Note: Vector components are displayed by default.
			} else if (ShowVecComponents.isSelected() == false) {
				AnimPanel.blnShowVectors = false;
				AnimPanel.repaint();
				// System.out.println("Hiding Vectors");
				// Transmitting user-invoked request to hide vector components via AnimPanel.blnShowVectors
			}
		} else if (evt.getSource() == LaunchButton) {
			dblInitialV = (double) InitialVelocitySlider.getValue();
			dblLaunchAngle = (double) AngleSlider.getValue();
			dblInitialHeight = (double) InitialHeightSlider.getValue();
			dblInitialVX = dblInitialV*Math.cos(Math.toRadians(dblLaunchAngle));
			dblInitialVY = dblInitialV*Math.sin(Math.toRadians(dblLaunchAngle));
			AnimPanel.dblVX = dblInitialVX;
			AnimPanel.dblVY = dblInitialVY;
			// Transposing local class data (for componentalized velocity vectors) to Animation Panel

			intMilSec = 0;
			AnimPanel.dblProjectileX = 110.0;
			AnimPanel.dblProjectileY = 475.0 - 3.0 * dblInitialHeight;
			AnimPanel.strProjectile = String.valueOf(ChooseProjectileComboBox.getSelectedItem());	
			// Resetting air timer and projectile coordinate (position) values from previous launch(es)
		
			// Disabling all modifications for a clean animation run without interruptions:
			InitialHeightSlider.setEnabled(false);
			AngleSlider.setEnabled(false);
			InitialVelocitySlider.setEnabled(false);
			AngleTextField.setEnabled(false);
			InitialVelocityTextField.setEnabled(false);
			LaunchButton.setEnabled(false);
			ShowVecComponents.setEnabled(false);
			ChooseProjectileComboBox.setEnabled(false);						
			Menu.setEnabled(false);

			System.out.println("Projectile Launch in Progress...");
			
			AnimTimer.restart();
			AirTimer.restart();
			AnimPanel.blnStatus = true;
			AnimPanel.blnShowVectors = ShowVecComponents.isSelected();
			// Starting timers and signalling to Animation Panel to display (or not) projectile mechanics (ex. vector components and projectile via blnShowVectors & blnStatus) 		
	
		} else if (evt.getSource() == AirTimer){
            if (AnimPanel.dblProjectileY > 475.0) AirTimer.stop();
		    intMilSec = intMilSec + 1;
			// Counting the number of miliseconds while the projectile is "in the air" ~ still in active motion (non-static equilibrium)

        } else if (evt.getSource() == AnimTimer) {			
			if (intMilSec < 500){
                AnimPanel.blnBoom = true;
				// Signalling to Animation Panel to display the launch explosion effects for the first 0.5 seconds
            } else {
                AnimPanel.blnBoom = false;
				// Actively disabling the launch epxlosion effects as soon as the timer no longer exists within the 0.5 second parameters 
			}

			if (AnimPanel.dblProjectileY>475.0) {
                AnimTimer.stop();
		
				AnimPanel.add(AirTime);
				AnimPanel.add(Range);
				AnimPanel.add(OKButton);

				OKButton.setVisible(true);
				OKButton.setEnabled(true);
				AirTime.setVisible(true);
				Range.setVisible(true);
				AirTime.setText("Air Time: "+(((double) intMilSec) / 1000.0)+" s");
        		Range.setText("Range: "+((Math.round(AnimPanel.dblProjectileX)*10.0)/10.0 - 110.0)+" m");
				// Stopping the timer; thus, stopping the animation algorithm and displaying results screen 
				// (with OKButton for users to proceed at their discretion)	
				// Stop is determined when the projectile surpasses the y coordinate 315
			}	
			
			dblInitialVY = dblInitialVY - (0.048 * 9.8);
			AnimPanel.dblDeltaY = dblInitialVY * 0.048;
			AnimPanel.dblProjectileY = AnimPanel.dblProjectileY - 3 * AnimPanel.dblDeltaY;
			AnimPanel.dblVY = dblInitialVY;
			dblInitialVX = dblInitialVX; 
			AnimPanel.dblDeltaX = dblInitialVX * 0.048;
			AnimPanel.dblProjectileX = AnimPanel.dblProjectileX + 3 * AnimPanel.dblDeltaX;
			AnimPanel.dblVX = dblInitialVX;
			AnimPanel.repaint();
			// Velocity and Displacement algorithm with acceleration due to gravity dependent on time as 
			// outlined by the iteration / 0.048 seconds and repainting changes via Animation Panel

		} else if (evt.getSource() == OKButton){
			InitialHeightSlider.setEnabled(true);
			AngleSlider.setEnabled(true);
			InitialVelocitySlider.setEnabled(true);
			AngleTextField.setEnabled(true);
			InitialVelocityTextField.setEnabled(true);
			LaunchButton.setEnabled(true);
			ShowVecComponents.setEnabled(true);
			ChooseProjectileComboBox.setEnabled(true);
			Menu.setEnabled(true);
			AnimPanel.blnStatus = false;
			AnimPanel.blnBoom = false;
			OKButton.setVisible(false);
			OKButton.setEnabled(false);
			AirTime.setVisible(false);
			Range.setVisible(false);
			AnimPanel.blnShowVectors = false;
			AnimPanel.repaint();
			// Re-enabling the disabled modification items and hiding all dynamic display components (ex. launch explosion effects, projectile image, vector components, etc...)
		}
	}
	
	// ChangeListener-Compatible Component Event Methods: Overriding stateChanged interface method
	public void stateChanged(ChangeEvent evt){
		if (evt.getSource() == InitialVelocitySlider){
			InitialVelocityTextField.setText(InitialVelocitySlider.getValue()+"");
			dblInitialV = (double) InitialVelocitySlider.getValue();
			this.VCalc(dblInitialV, dblLaunchAngle);
			// Processing changes to the InitialVelocitySlider and adjusting other user input sources for consistency, as well as calling VCalc to derive the component velocities
		} else if (evt.getSource() == AngleSlider){
			AngleTextField.setText(AngleSlider.getValue() + "");
			dblLaunchAngle = (double) AngleSlider.getValue();
			this.VCalc(dblInitialV, dblLaunchAngle);
			AnimPanel.dblAng = (double) AngleSlider.getValue();
			AnimPanel.repaint();
			// Processing changes to the AngleSlider and adjusting other user input sources for consistency, 
			// as well as calling VCalc to derive the component velocities
			// Repainting the panel to readjust rotational display of the cannon image
		} else if (evt.getSource() == InitialHeightSlider){
			dblInitialHeight = (double) InitialHeightSlider.getValue();
			AnimPanel.dblLift = dblInitialHeight;
			AnimPanel.repaint();
			// Processing changes to the InitialHeightSlider and transferring data to 
			// Animation Panel via AnimPanel.dblLift
			// Repainting to reflect graphical elevations of cannon in sync with 
			// user-invoked action time
		}	
	}


	// Constructor ==============================================================
	// Setting sizes, locations, etc. for JComponents and attaching them to their 
	// respective hierarchical structures
	public ProjectileMotion() {

		// Main Animation or Simulation Panels and Parent Frame 
		MainWindowFrame = new JFrame("Projectile Motion Simulation");
		AnimPanel = new AnimationPanel();	
		AnimPanel.setPreferredSize(new Dimension(1300, 700));
		AnimPanel.setLayout(null);
		MainWindowFrame.setContentPane(AnimPanel);
		
		// About Panel
		AboutP = new AboutPanel();
		AboutP.setPreferredSize(new Dimension(1300, 1000));
		AboutP.setLayout(null);
		
		// HelpPanel
		HelpPANEL = new HelpPanel();
		HelpPANEL.setPreferredSize(new Dimension(1300, 540));
		HelpPANEL.setLayout(null);

		// Menu Components
		MenuBar = new JMenuBar();
		Menu = new JMenu("Menu");
		Simulation = new JMenuItem("Simulation");
		Simulation.addActionListener(this);
		About = new JMenuItem("About");
		About.addActionListener(this);
		Help = new JMenuItem("Help");
		Help.addActionListener(this);
	
		Menu.add(Simulation);
		Menu.add(About);
		Menu.add(Help);
		MenuBar.add(Menu);
		MainWindowFrame.setJMenuBar(MenuBar);

		// Acceleration Due to Gravity Info
		GravityLabel = new JLabel("Accepted Acceleration Due to Gravity: 9.8 m/s^2 (down)");
		GravityLabel.setSize(400, 50);
		GravityLabel.setLocation(900, 15);
		AnimPanel.add(GravityLabel);

		// Display Settings
		ShowVecComponents = new JCheckBox("Show Vector Components");
		ShowVecComponents.setSize(200,50);
		ShowVecComponents.setLocation(1065,35);
		ShowVecComponents.setSelected(true);
		ShowVecComponents.addActionListener(this);
		AnimPanel.add(ShowVecComponents);

		// Angle
		AngleSlider = new JSlider(-90,90,60);
		AngleSlider.setSize(400, 50);
		AngleSlider.setLocation(250,570);
		AngleSlider.setPaintTicks(true);
		AngleSlider.setPaintLabels(true);
		AngleSlider.setSnapToTicks(true);
		AngleSlider.setMinorTickSpacing(30);
		AngleSlider.setLabelTable(AngleSlider.createStandardLabels(30));
		AngleSlider.addChangeListener(this);
		AnimPanel.add(AngleSlider);
		
		AngleLabel = new JLabel("Angle (°):");
		AngleLabel.setSize(100, 70);
		AngleLabel.setLocation(140, 560);
		AnimPanel.add(AngleLabel);
		
		AngleTextField = new JTextField(String.valueOf(AngleSlider.getValue()));
		AngleTextField.setSize(45, 35);
		AngleTextField.setLocation(205, 580);
		AngleTextField.addActionListener(this);
		AnimPanel.add(AngleTextField);


		// Initial Velocity
		InitialVelocitySlider = new JSlider(10,100, 60);
		InitialVelocitySlider.setSize(400,50);
		InitialVelocitySlider.setLocation(250,620);
		InitialVelocitySlider.setPaintTicks(true);
		InitialVelocitySlider.setPaintLabels(true);
		InitialVelocitySlider.setSnapToTicks(true);
		InitialVelocitySlider.setMinorTickSpacing(10);
		InitialVelocitySlider.setLabelTable(InitialVelocitySlider.createStandardLabels(10));
		InitialVelocitySlider.addChangeListener(this);
		AnimPanel.add(InitialVelocitySlider);

		InitialVelocityLabel = new JLabel("Initial Velocity (m/s):");
		InitialVelocityLabel.setSize(135, 70);
		InitialVelocityLabel.setLocation(65, 610);
		AnimPanel.add(InitialVelocityLabel);

		InitialVelocityTextField = new JTextField(String.valueOf(InitialVelocitySlider.getValue()));
		InitialVelocityTextField.setSize(45, 35);
		InitialVelocityTextField.setLocation(205,628);
		InitialVelocityTextField.addActionListener(this);
		AnimPanel.add(InitialVelocityTextField);

		// Projectile Selection Buttons
		SelectProjectileLabel = new JLabel("Select Projectile:");
		SelectProjectileLabel.setSize(150, 75);
		SelectProjectileLabel.setLocation(750, 550);
		AnimPanel.add(SelectProjectileLabel);
		
		ChooseProjectileComboBox = new JComboBox();
		ChooseProjectileComboBox.addItem(makeObj("Ball"));
		ChooseProjectileComboBox.addItem(makeObj("Basketball"));
		ChooseProjectileComboBox.addItem(makeObj("Bosco"));
		ChooseProjectileComboBox.addItem(makeObj("Nihal"));
		ChooseProjectileComboBox.setSize(200,50);
		ChooseProjectileComboBox.setLocation(860, 563);
		ChooseProjectileComboBox.addActionListener(this);
		AnimPanel.add(ChooseProjectileComboBox);

		// Initial Height of Cannon (Instance of Projectile Launch)
		InitialHeightSlider = new JSlider(0,60, 0);
		InitialHeightSlider.setSize(50,300);
		InitialHeightSlider.setLocation(45,50);
		InitialHeightSlider.setPaintTicks(true);
		InitialHeightSlider.setPaintLabels(true);
		InitialHeightSlider.setSnapToTicks(true);
		InitialHeightSlider.setMinorTickSpacing(5);
		InitialHeightSlider.setMajorTickSpacing(10);	
		InitialHeightSlider.setLabelTable(InitialVelocitySlider.createStandardLabels(10));
		InitialHeightSlider.setOrientation(SwingConstants.VERTICAL);
		InitialHeightSlider.addChangeListener(this);
		AnimPanel.add(InitialHeightSlider);

		InitialHeightLabel = new JLabel("Initial Height (m):");
		InitialHeightLabel.setSize(200, 50);
		InitialHeightLabel.setLocation(45, 20);
		AnimPanel.add(InitialHeightLabel);		
		
		// Launch Button	
		LaunchButton = new JButton("Launch Projectile");
		LaunchButton.setSize(300, 60);
		LaunchButton.setLocation(750, 610);
		LaunchButton.addActionListener(this);
		AnimPanel.add(LaunchButton);
	
		// Results Screen JComponents	
		AirTime = new JLabel();
		Range = new JLabel();
		OKButton = new JButton("OK");

		AirTime.setSize(200, 50);
		Range.setSize(200, 50);
		AirTime.setLocation(600, 150);
		Range.setLocation (600, 170);

		OKButton.setSize(80, 40);
		OKButton.setLocation(610, 220);
		OKButton.addActionListener(this);

		// Help Panel Page Flip Buttons
		RightButton = new JButton("Next");
		RightButton.setSize(185, 60);
		RightButton.setLocation(915, 600);
		RightButton.addActionListener(this);
		HelpPANEL.add(RightButton);  

		LeftButton = new JButton("Back");     
		LeftButton.setSize(185, 60);
		LeftButton.setLocation(200, 600);
		LeftButton.addActionListener(this);
		HelpPANEL.add(LeftButton);	
	
		// Modifying main GUI components (size, location, layouts, user interface settings, etc..) for panels and frames...
		MainWindowFrame.pack();
		MainWindowFrame.setResizable(false);
		MainWindowFrame.setVisible(true);	
	}
	// Method for converting string to object for jcombobox item (projectile selector);
	public Object makeObj(final String item){
		return new Object(){ public String toString() { return item; } };
	}
	
	// Initial Velocity Componentization Calculations Method
	public void VCalc (double dblVi, double dblAngle){
		dblInitialVX = dblVi * (Math.cos(Math.toRadians(dblAngle)));
		dblInitialVY = dblVi * (Math.sin(Math.toRadians(dblAngle)));
		// System.out.println("Vi: "+dblInitialV+" Vx: "+dblInitialVX+" Vy: "+dblInitialVY);
	}

	// Main Method
	public static void main(String [] args){
		System.out.print("Running Projectile Motion Version ");
		System.out.println(VERSION);
		new ProjectileMotion();
	}
}