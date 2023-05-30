import javax.swing.*;
import java.util.ArrayList;

public class Bullet extends JLabel {
    private int x;
    private int y = 550;
    public Bullet(GamePanel gamePanel, ArrayList<Target> targets){
        this.x = gamePanel.getPlayerPosX()+25;
        this.setOpaque(true);
        new Thread(() ->{
            while (this.y != 5){
                //while our bullet doesn't reach the top of the screen
                this.setBounds(x,y,5,10);
                this.y-=2;
                Utils.sleep(1);
                for (Target target : targets) {
                    boolean isInWithOfLabel = this.x >= target.getX() && this.x < target.getX() + target.getWidth()
                            && this.y >=target.getY();
                    boolean isInHeight = this.y < target.getY() + target.getHeight();
                    if (isInHeight && isInWithOfLabel) {
                        //if our bullet hits a target we want our target to stop existing
                        target.setHit(true);
                        target.setBounds(0,0,0,0);
                        this.y = 0;
                        break;
                    }
                }
                if (this.y ==0){
                    //once our bullet hits a target or the top of the screen we want it to stop existing
                    this.setBounds(0,0,0,0);
                    gamePanel.remove(this);
                    break;
                }
            }
        }).start();
        gamePanel.add(this);
    }
}
