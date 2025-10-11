import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
	
public class AboutPanel extends JScrollPane {

    // Properties
    BufferedImage imgAbout1;
    JLabel theLabel;

    // Methods; overriding the default ScrollPane to add images and drawings overtop
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgAbout1, 0, 0, null);
        this.repaint();
    }

    // Constructor
    public AboutPanel() {
        super();

	//Allow the vertical scroll bar to appear even when not used (constant: ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS)
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.setPreferredSize(new Dimension(960, 540));
        this.getViewport().setOpaque(false);
        this.setOpaque(false);

        // Declare the imageIcon
        ImageIcon imageIcon = null;

        try {
            // Initialize the imageIcon
            imageIcon = new ImageIcon("About1.png");
        } catch (Exception e) {
            System.out.println("Unable to load image");
        }

        // Initialize theLabel with the imageIcon
        theLabel = new JLabel(imageIcon);

        // Set the JLabel as the viewport view
        this.setViewportView(theLabel);
    }
}

