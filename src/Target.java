import javax.swing.*;
import java.awt.*;

public class Target extends JPanel {

    private int x;

    @Override
    public int getX() {
        super.getX();
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }
    private boolean hit = false;

    public void setHit(boolean hit) {

        this.hit = hit;

    }

    public boolean isHit() {
        return hit;
    }

    public Target(int x, int y, int w, int h, boolean isMoving, int speed){
        this.x = x;
        this.setBounds(this.x, y, w,h);
        this.setBackground(Color.orange);
        this.setOpaque(true);
        if (isMoving){
            //if our target is a moving target we want a thread to keep it moving
            this.setBackground(Color.blue);
            new Thread(() ->{
                    while (true){
                        while (this.x <550 ){
                            if (!this.isHit()){
                                this.setX(this.x + 1);
                            }
                            this.setBounds(this.x, y, w,h);
                            Utils.sleep(speed);
                        }
                        while (this.x > 10){
                            if (!this.isHit()){
                                this.setX(this.x -1);
                            }
                            this.setBounds(this.x, y, w,h);
                            Utils.sleep(speed);
                        }
                    }
            }).start();
        }

    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }
}
