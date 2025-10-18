import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;

/** Child class called AnimationPanel that extends from the parent class: JPanel*/
public class AnimationPanel extends JPanel{

	//Properties
	/** Background image */
	public BufferedImage imgBackground;

	/** Cannon image */
	public BufferedImage imgCannon;

	/** Cannon Wheel image */
	public BufferedImage imgWheel;

	/** Projectile image */
	public BufferedImage imgProjectile;

	/** Bang image (launch explosion effect) */
	public BufferedImage imgBang;

	/** Start angle */
	public double dblAng=60.0;

	/** Change in X */
	public double dblDeltaX;

	/** Change in Y */
	public double dblDeltaY;

	/** X-value of projectile */
	public double dblProjectileX;

	/** Y-Value of projectile */
	public double dblProjectileY;

	/** X-velocity */
	public double dblVX;

	/** Y-velocity */
	public double dblVY;

	/** Lift of the cannon from the ground considering user-modified initial height */
	public double dblLift;

	/** Name of projectile being used */
	public String strProjectile;

	/** The status of the projectile (being lauched or not) */
	public boolean blnStatus = false;

	/**Are the vector components being shown */
	public boolean blnShowVectors = true;

	/** Boolean for if boom image (launch explosion effect) should be shown */
	public boolean blnBoom = false;

	//Methods
	/** Calls the paint method to override the default JPanel*/
	public void paintComponent (Graphics g){
		super.paintComponent(g);

		g.drawImage(imgBackground, 15, 10, null);    
		g.setColor(Color.GRAY);
 		g.fillRect(65, 375-3*(int)dblLift, 100, 3*(int)dblLift);
		g.drawImage(imgWheel, 42, 267-3*(int)dblLift, null);

		
		if(dblAng==-90.0){
			try{
				imgCannon = ImageIO.read(new File("assets/img/Cannon-90.png"));
			} catch (IOException e){
				System.out.println("Unable to load image");
			}
			g.drawImage(imgCannon, 100, 265-3*(int)dblLift, null); 
		} else if (dblAng == -60.0){
			try{
				imgCannon = ImageIO.read(new File("assets/img/Cannon-60.png"));
			} catch (IOException e){
				System.out.println("Unable to load image");
			}
			g.drawImage(imgCannon, 85, 265-3*(int)dblLift, null);
		} else if (dblAng == -30.0){
			try{
				imgCannon = ImageIO.read(new File("assets/img/Cannon-30.png"));
			} catch (IOException e){
				System.out.println("Unable to load image");
			}
			g.drawImage(imgCannon, 85, 270-3*(int)dblLift, null);
		} else if (dblAng == 0){
			try{
				imgCannon = ImageIO.read(new File("assets/img/Cannon0.png"));
			} catch (IOException e){
				System.out.println("Unable to load image");
			}
			g.drawImage(imgCannon, 80, 210-3*(int)dblLift, null);
			g.drawImage(imgWheel, 42, 267-3*(int)dblLift, null);
		} else if (dblAng == 30){
			try{
				imgCannon = ImageIO.read(new File("assets/img/Cannon30.png"));
			} catch (IOException e){
				System.out.println("Unable to load image");
			}
			g.drawImage(imgCannon, 80, 220-3*(int)dblLift, null);
			g.drawImage(imgWheel, 42, 267-3*(int)dblLift, null);
		} else if (dblAng == 60){
 			try{
				imgCannon = ImageIO.read(new File("assets/img/Cannon60.png"));
			} catch (IOException e){
				System.out.println("Unable to load image");
			}
			g.drawImage(imgCannon, 85, 220-3*(int)dblLift, null);
			g.drawImage(imgWheel, 42, 267-3*(int)dblLift, null); 
		} else if (dblAng == 90){
			try{
				imgCannon = ImageIO.read(new File("assets/img/Cannon90.png"));
			} catch(IOException e){
				System.out.println("Unanle to load image");
			}
			g.drawImage(imgCannon, 96, 220-3*(int)dblLift, null);
			g.drawImage(imgWheel, 42, 267-3*(int)dblLift, null);
		}
		
		if (strProjectile == "Ball"){
			try{
				imgProjectile = ImageIO.read(new File("assets/img/Ball.png"));
			} catch(IOException e){
				System.out.println("Unable to load image");
			} 
		} else if (strProjectile == "Basketball"){
			try{
				imgProjectile = ImageIO.read(new File("assets/img/Basketball.png"));
			} catch (IOException e){
				System.out.println("Unable to load image");
			} 
		} else if (strProjectile == "Bosco"){
			try{
				imgProjectile = ImageIO.read(new File("assets/img/Bosco.png"));
			} catch (IOException e){
				System.out.println("Unable to load image");
			}
		} else if (strProjectile == "Nihal"){
			try{
				imgProjectile = ImageIO.read(new File("assets/img/Nihal.png"));
			} catch (IOException e){
				System.out.println("Unable to load image");
			}
		}

		if (blnBoom == true){
			g.drawImage(imgBang, 85,250-3*(int)dblLift , null);
		}
		if (blnStatus == true && dblProjectileY > 10 && dblProjectileX<930){
                 	g.drawImage(imgProjectile, (int)dblProjectileX, (int)dblProjectileY, null);
	
			if (blnShowVectors == true){
				g.setColor(Color.RED);
				g.fillRect((int)(dblProjectileX+50), (int)dblProjectileY+10, (int)(Math.abs(dblVX)), 30);
				g.setColor(Color.GREEN);
				if (dblVY >=0){
					g.fillRect((int)dblProjectileX+10, (int)dblProjectileY-(int)(Math.abs(dblVY)), 30, (int)(Math.abs(dblVY)));
				} else if (dblVY < 0){	
					g.fillRect((int)dblProjectileX+10, (int)dblProjectileY+50, 30, (int) (Math.abs(dblVY)));
				}
			}	
                }
		//Sets the restrictions to when the projectile is visible and when it is not ~ a.k.a. when it is not drawn and covered only by the other background images. Specifically, this stops the projectile from appearing at the edge of the panel or outside of the animation screen for better quality. 
	}
	
	//Constructor
	/**Creates an instance of the AnimationPanel class; a object constructor extended from the parent class: JPanel. Objects created under this constructor are AnimationPanels.*/
	public AnimationPanel(){
		super();
		try{
			imgBackground = ImageIO.read(new File("assets/img/Background.png"));
			imgWheel = ImageIO.read(new File("assets/img/Wheel.png"));
			imgBang = ImageIO.read(new File("assets/img/BANG.png"));
		} catch(IOException e){
			System.out.println("Unable to load image");
			System.out.println(e.toString());
		}
	}
}
