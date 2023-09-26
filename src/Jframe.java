import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;


public class Jframe extends JFrame{
    int x = 40,y=50;
    Jframe(){
        this.setSize(800,700);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.GREEN);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new Jframe();
    }
    public void paint(Graphics g){
        super.paint(g);//调用super.paint（g）去清除运动的痕迹
        g.setColor(Color.RED);
        g.fillOval(x, y, 20, 20);
        y++;
        repaint();//重画
        try {
            Thread.sleep(10);//在此处睡眠一会，要不运动太快
        } catch (InterruptedException e) {
// TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}

