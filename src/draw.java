import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class draw extends JFrame{
    public draw()
    {
        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public void paint(Graphics g)
    {
        g.setColor(Color.blue);
        g.drawLine(50,30,450,260);
    }
    public static void main(String[] args)
    {
        new draw().setVisible(true);
    }
}
