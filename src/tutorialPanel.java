import javax.swing.*;
import java.awt.*;

public class tutorialPanel extends JPanel{
    String welcome1 = "Use 'a' and 'd' keys to move and space to shoot. ";
    String welcome2 = "You must eliminate all targets before you run out of ammo.";
    String welcome3 = "GLHF.";
    JButton mainButton;
    JLabel tutorialText1;
    JLabel tutorialText2;
    JLabel tutorialText3;
    //divided texts into separate labels just in case I want to customize them differently later
    public tutorialPanel(){
        this.tutorialText1 = new JLabel(welcome1);
        tutorialText1.setBounds(175,200,1000,40);

        this.tutorialText2 = new JLabel(welcome2);
        tutorialText2.setBounds(150,211,1000,40);

        this.tutorialText3 = new JLabel(welcome3);
        tutorialText3.setBounds(260,222,1000,40);


        this.mainButton = new JButton("main menu");
        mainButton.setBounds(250,10,100,25);

        this.setBackground(Color.lightGray);
        this.setBounds(0,0,600,600);
        this.setLayout(null);
        this.add(mainButton);
        this.add(tutorialText1);
        this.add(tutorialText2);
        this.add(tutorialText3);
        mainButton.setVisible(false);
    }
}
