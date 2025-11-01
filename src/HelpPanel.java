// Importing Java libraries and toolkits as needed
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;


public class HelpPanel extends JPanel{
		
	// Properties
	int intPageNum = 1;
	BufferedImage imgHelpPage;

	//Methods
	//Overriding default JPanel to load graphics and images overtop
	public void paintComponent (Graphics g){
		super.paintComponent(g);

		//For each help page, use the intPageNum (changeable variable) to determine which to draw as imgHelpPage and establish a page layout graphic using ovals and color differentiation
		if (intPageNum == 1){
			try{	
				imgHelpPage = ImageIO.read(new File("assets/img/HelpPage1.png"));
			} catch (IOException e){
				System.out.println("Unable to load image");
			}
			g.setColor(Color.DARK_GRAY);
			g.fillOval(508, 620, 20, 20);
			g.setColor(Color.WHITE);
			g.fillOval(562, 620, 20, 20);
			g.fillOval(616, 620, 20, 20);
			g.fillOval(670, 620, 20, 20);
			g.fillOval(724, 620, 20, 20);
			g.fillOval(779, 620, 20, 20);
		} else if (intPageNum == 2){
			try{
				imgHelpPage = ImageIO.read(new File("assets/img/HelpPage2.png"));
			} catch (IOException e){
				System.out.println("Unable to load image");
			}
			g.setColor(Color.WHITE);
            g.fillOval(508, 620, 20, 20);
            
			g.setColor(Color.DARK_GRAY);
			g.fillOval(562, 620, 20, 20);
            g.setColor(Color.WHITE);
			g.fillOval(616, 620, 20, 20);
			g.fillOval(670, 620, 20, 20);
			g.fillOval(724, 620, 20, 20);
			g.fillOval(779, 620, 20, 20);
		} else if (intPageNum == 3){
			try{
				imgHelpPage = ImageIO.read(new File("assets/img/HelpPage3.png"));
			} catch (IOException e){
				System.out.println("Unable to load image");
			}
			g.setColor(Color.WHITE);
			g.fillOval(508, 620, 20, 20);
			g.fillOval(562, 620, 20, 20);

			g.setColor(Color.DARK_GRAY);
			g.fillOval(616, 620, 20, 20);

			g.setColor(Color.WHITE);
			g.fillOval(670, 620, 20, 20);
			g.fillOval(724, 620, 20, 20);
			g.fillOval(779, 620, 20, 20);
		} else if (intPageNum == 4){
			try{
				imgHelpPage = ImageIO.read(new File("assets/img/HelpPage4.png"));
			} catch (IOException e){
				System.out.println("Unable to load image");
			}
			g.setColor(Color.WHITE);
			g.fillOval(508, 620, 20, 20);
			g.fillOval(562, 620, 20, 20);
			g.fillOval(616, 620, 20, 20);

			g.setColor(Color.DARK_GRAY);
			g.fillOval(670, 620, 20, 20);

			g.setColor(Color.WHITE);
			g.fillOval(724, 620, 20, 20);
			g.fillOval(779, 620, 20, 20);
		} else if (intPageNum == 5){
			try{
				imgHelpPage = ImageIO.read(new File("assets/img/HelpPage5.png"));
			} catch (IOException e) {
				System.out.println("Unable to load image");
			}
			g.setColor(Color.WHITE);
			g.fillOval(508, 620, 20, 20);
			g.fillOval(562, 620, 20, 20);
			g.fillOval(616, 620, 20, 20);
			g.fillOval(670, 620, 20, 20);

			g.setColor(Color.DARK_GRAY);
			g.fillOval(724, 620, 20, 20);

			g.setColor(Color.WHITE);
			g.fillOval(779, 620, 20, 20);
		} else if (intPageNum == 6){
			try{
				imgHelpPage = ImageIO.read(new File("assets/img/HelpPage6.png"));
			} catch (IOException e){
				System.out.println("Unable to load image");
			}
			g.setColor(Color.WHITE);
			g.fillOval(508, 620, 20, 20);
			g.fillOval(562, 620, 20, 20);
			g.fillOval(616, 620, 20, 20);
			g.fillOval(670, 620, 20, 20);
			g.fillOval(724, 620, 20, 20);

			g.setColor(Color.DARK_GRAY);
			g.fillOval(779, 620, 20, 20);
		}
		g.drawImage(imgHelpPage, 155, 30, null);
	}
	// Constructor
	public HelpPanel(){
		super();
	}
}	
