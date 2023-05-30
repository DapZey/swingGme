import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainMenuFrame extends JFrame implements ActionListener, KeyListener {
    HomePanel homePanel;
    tutorialPanel tutorialPanel;
    GamePanel gamePanel;
    gameOverPanel gameOverPanel;
    MainMenuFrame(){
        this.gameOverPanel = new gameOverPanel();
        this.gamePanel = new GamePanel();
        this.homePanel = new HomePanel();
        this.add(gameOverPanel);
        gameOverPanel.setVisible(false);
        gameOverPanel.retryButton.addActionListener(this);
        this.add(homePanel);
        homePanel.aboutbutton.addActionListener(this);
        homePanel.startbutton.addActionListener(this);
        this.tutorialPanel = new tutorialPanel();
        tutorialPanel.mainButton.addActionListener(this);
        this.add(tutorialPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(600,600);
        this.setVisible(true);
        this.setResizable(false);
        this.setFocusable(true);
        this.requestFocus();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //logic to navigate between tutorial, menu and game panels
        if (e.getSource() == this.homePanel.aboutbutton){
            this.homePanel.setVisible(false);
            this.tutorialPanel.mainButton.setVisible(true);
        }
        if (e.getSource() == this.tutorialPanel.mainButton){
            this.homePanel.setVisible(true);
            this.tutorialPanel.mainButton.setVisible(false);
        }
        if (e.getSource() == this.homePanel.startbutton){
            this.homePanel.setVisible(false);
            this.tutorialPanel.setVisible(false);
            this.add(gamePanel);
            this.addKeyListener(this);
        }
        if (e.getSource() == this.gameOverPanel.retryButton){
            this.setVisible(false);
            MainMenuFrame reset = new MainMenuFrame();
            reset.homePanel.setVisible(false);
            reset.tutorialPanel.setVisible(false);
            reset.add(reset.gamePanel);
            reset.addKeyListener(reset);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'a'){
            gamePanel.setMoveLeft(true);
        }
        if (e.getKeyChar() == 'd'){
            gamePanel.setMoveRight(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'a'){
            gamePanel.setMoveLeft(false);
        }
        if (e.getKeyChar() == 'd'){
            gamePanel.setMoveRight(false);
        }
        if (e.getKeyChar() == ' '){
            if (gamePanel.getShotCount() > 0){
                //create a new bullet object on input while the shot count is > 0
                Bullet bullet = new Bullet(this.gamePanel, this.gamePanel.targets1);
            }
            gamePanel.setShotCount(gamePanel.getShotCount() -1);
            if (gamePanel.getShotCount() < 0){
//                System.out.println("game over");
//                System.exit(1);
                this.gamePanel.setVisible(false);
                gameOverPanel.setVisible(true);
            }
        }
    }
}
