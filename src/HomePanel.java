import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel{
    tutorialPanel tutorialPanel;
    JButton startbutton;
    JButton aboutbutton;
    public HomePanel(){
        this.tutorialPanel = new tutorialPanel();
        this.startbutton = new JButton("START");
        startbutton.setBounds(200,250,100,50);
        JLabel levelNum = new JLabel("Level 0");
        levelNum.setBounds(0,0,300,10);

        this.aboutbutton = new JButton("TUTORIAL");
        aboutbutton.setBounds(310,250,100,50);

        this.setBounds(0,0,600,600);
        this.setBackground(Color.cyan);
        this.add(startbutton);
        this.add(aboutbutton);
        this.add(tutorialPanel);
        this.setVisible(true);
        tutorialPanel.setVisible(false);
    }

}
