import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class test extends JFrame implements MouseListener {

    int clickNum = 0;

    public static void main(String[] args) {
        new test();

    }

    public test() {
        this.setSize(500, 300);
        this.addMouseListener(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.blue);
        g.drawLine(50, 30, 450, 260);
        for(int i = 0;i<clickNum;i++){
            g.drawLine(i*10,i*10,i*10,i*10);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        clickNum++;
        System.out.println(clickNum);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
