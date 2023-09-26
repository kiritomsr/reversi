import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
    static int N = 8, SIZE = 75;

    public Main() {
        super(new GridLayout(N, N));
        this.setPreferredSize(new Dimension(N * SIZE, N * SIZE));
        for (int i = 0; i < N * N; i++) {
            this.add(new ChessButton(i));
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Main());
        f.pack();
        f.setVisible(true);
    }
}

class ChessButton extends JButton {
    static int N = 8;

    public ChessButton(int i) {
        super(i / N + "," + i % N);
        this.setOpaque(true);
        this.setBorderPainted(false);
        if ((i / N + i % N) % 2 == 1) {
            this.setBackground(Color.gray);
        }
    }
}

