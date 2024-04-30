import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
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
}
