import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel{
    private boolean moveLeft = false;
    private boolean moveRight = false;

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    private int levelCount = 1;

    public void setLevelCount(int levelCount) {
        this.levelCount = levelCount;
    }

    private int ShotCount = 1;

    public int getShotCount() {
        return ShotCount;
    }

    public void setShotCount(int shotCount) {
        ShotCount = shotCount;
    }

    private int playerPosX = 275;

    public int getPlayerPosX() {
        return playerPosX;
    }

    JLabel player;
    JLabel levelCounter;
    JLabel shotCounter;
    ArrayList<Target> targets1;
    Target targetA1 = new Target(50,50,50,50, false,0);


    public GamePanel(){
        this.targets1 = new ArrayList<>();
        this.player = new JLabel();
        player.setBackground(Color.red);
        player.setOpaque(true);
        player.setBounds(playerPosX,550,50,50);

        this.levelCounter = new JLabel("level: " + this.levelCount);
        levelCounter.setBounds(0,0,50,10);

        this.shotCounter = new JLabel("ammunition: " + this.ShotCount);
        shotCounter.setBounds(51,0,100,10);

        this.setLayout(null);
        this.setBounds(0,0,600,600);
        this.setBackground(Color.gray);
        this.add(player);
        this.add(levelCounter);
        this.add(shotCounter);
        this.levelCount = 1;
        gameLoop();
    }
    public void movement(){
        //logic that keeps the game running when inside a thread
        this.shotCounter.setText("ammunition: " + this.ShotCount);
        this.levelCounter.setText("level: " + this.levelCount);
        if (this.moveLeft){
            if (playerPosX != 0){
                this.playerPosX--;
                repaint();
            }
        }
        if (this.moveRight) {
            if (playerPosX != 550) {
                this.playerPosX++;
                repaint();
            }
        }
        repaint();
    }
    public void gameLoop(){
        new Thread(() ->{
            this.ShotCount = 1;
            //add levels here:
            int bossx = 0;
            this.add(targetA1);
            targets1.add(targetA1);
            while (this.levelCount == 1){
                movement();
                Utils.sleep(1);
                player.setBounds(playerPosX, 550, 50, 50);
                if (targetA1.isHit()){
                    targets1.remove(targetA1);
                    targetA1.setBounds(0,0,0,0);
                    this.levelCount = 2;
                }
            }
            Target targetA2 = new Target(100,15,30,30,true,5);
            this.add(targetA2);
            targets1.add(targetA2);
            setShotCount(2);
            while (this.levelCount == 2){
                movement();
                Utils.sleep(1);
                player.setBounds(playerPosX, 550, 50, 50);
                if (targetA2.isHit()){
                    targets1.remove(targetA2);
                    targetA2.setBounds(0,0,0,0);
                    this.remove(targetA2);
                    this.levelCount = 3;
                }
            }
            Target targetB1 = new Target(10,150,30,30,true,5);
            this.add(targetB1);
            targets1.add(targetB1);
            Target targetB2 = new Target(500,100,30,30,true,5);
            this.add(targetB2);
            targets1.add(targetB2);
            targetB2.setBackground(Color.red);
            Target targetB3 = new Target(275,30,50,50,false,5);
            targets1.add(targetB3);
            this.add(targetB3);
            setShotCount(3);
            while (this.levelCount == 3){
                movement();
                Utils.sleep(1);
                player.setBounds(playerPosX, 550, 50, 50);
                if (targetB2.isHit()){
                    System.exit(1);
                }
                if (targetB1.isHit()){
                    targets1.remove(targetB1);
                    this.remove(targetB1);
                }
                if (targetB3.isHit()){
                    targets1.remove(targetB3);
                    this.remove(targetB3);
                }
                if (targetB1.isHit() && targetB3.isHit()){
                    this.remove(targetB2);
                    targets1.remove(targetB2);
                    this.levelCount = 4;
                }
            }
            setShotCount(4);
            Target targetBoss = new Target(100,100,100,100,true,10);
            targets1.add(targetBoss);
            this.add(targetBoss);
            targetBoss.setBackground(Color.white);
            while (this.levelCount == 4){
                movement();
                Utils.sleep(1);
                player.setBounds(playerPosX, 550, 50, 50);
                if (targetBoss.isHit()){
                    targets1.remove(targetBoss);
                    this.remove(targetBoss);
                    bossx = targetBoss.getX();
                    break;
                }

            }
            Target targetBoss1 = new Target(bossx,125,50,50,true,8);
            targets1.add(targetBoss1);
            this.add(targetBoss1);
            targetBoss1.setBackground(Color.green);
            while (this.levelCount == 4){
                movement();
                Utils.sleep(1);
                player.setBounds(playerPosX, 550, 50, 50);
                if (targetBoss1.isHit()){
                    targets1.remove(targetBoss1);
                    this.remove(targetBoss1);
                    bossx = targetBoss1.getX();
                    break;
                }
            }
            Target targetBoss2 = new Target(bossx,140,25,25,true,3);
            targets1.add(targetBoss2);
            this.add(targetBoss2);
            targetBoss2.setBackground(Color.black);
            while (this.levelCount == 4){
                movement();
                Utils.sleep(1);
                player.setBounds(playerPosX, 550, 50, 50);
                if (targetBoss2.isHit()){
                    targets1.remove(targetBoss2);
                    this.remove(targetBoss2);
                    this.levelCount =5;
                }
            }
            setShotCount(5);
            Target targetf = new Target(6,10,10,10,true,1);
            targets1.add(targetf);
            this.add(targetf);
            targetf.setBackground(Color.cyan);
            while (this.levelCount == 5){
                movement();
                Utils.sleep(1);
                player.setBounds(playerPosX, 550, 50, 50);
                if (targetf.isHit()){
                    targets1.remove(targetf);
                    this.remove(targetf);
                    break;
                }
            }
            setShotCount(0);
            if (this.ShotCount == 0){
                JLabel label = new JLabel("VICTORY!!");
                label.setBounds(250,250,100,100);
                this.add(label);
                repaint();
            }
        }).start();

    }
    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        this.player.paint(g);
    }
}
