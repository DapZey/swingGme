import javax.swing.*;
import java.awt.*;

public class gameOverPanel extends JPanel {
    JButton retryButton;
    public gameOverPanel(){
        this.retryButton = new JButton("retry");
        retryButton.setBounds(250,10,100,25);

        this.setBackground(Color.lightGray);
        this.setBounds(0,0,600,600);
        this.setLayout(null);
        this.add(retryButton);
        retryButton.setVisible(true);
    }
}
