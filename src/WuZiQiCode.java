import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;


public class WuZiQiCode extends JFrame implements MouseListener {

    public static void main(String[] args) {
        WuZiQiCode wuZiQiCode = new WuZiQiCode();
    }

    //Vector 可以实现自动增长的对象数组，用来存下面的步数信息。
    Vector v = new Vector();    //所有的每步走棋信息
    Vector white = new Vector(); //白方走棋信息
    Vector black = new Vector(); //黑方走棋信息
    boolean b; //用来判断白旗还是黑棋
    int whiteCount, blackCount; //计算悔棋步数
    int w = 25;    //间距大小
    int px = 150, py = 150;
    int pxw = px + w, pyw = py + w;
    int width = w * 16, height = w * 16;
    int vline = width + px;    //垂直线的长度
    int hline = height + py; //水平线的长度



    JButton jb;
    JButton reset;
    JButton send1;
    JButton send2;
    TextArea ta = new TextArea(10, 40);
    TextField tf1 = new TextField(20);
    TextField tf2 = new TextField(20);
    /**
     * 构造方法
     */
    public WuZiQiCode() {
        super("Five-in-a-row");

        Container con = this.getContentPane();
        con.setLayout(null);

        //创建按钮
        jb=new JButton("悔棋");
        Icon back = new ImageIcon("C:\\res\\button.png");
        jb.setIcon(back);
        Font f=new Font("宋体",Font.BOLD,20);//根据指定字体名称、样式和磅值大小，创建一个新 Font。
        jb.setFont(f);
        jb.setBackground(Color.ORANGE);
        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jbClicked();
            }
        });
        jb.setVisible(true);
        jb.setBounds(600,250,150,45);
        reset=new JButton("重开");
        Icon resets = new ImageIcon("C:\\res\\button.png");
        reset.setIcon(resets);
        reset.setFont(f);
        reset.setBackground(Color.pink);
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        reset.setVisible(true);
        reset.setBounds(600,350,150,45);

        send1=new JButton("发送");
        Icon iconSend1 = new ImageIcon("C:\\res\\button.png");
        send1.setIcon(iconSend1);
        send1.setFont(f);
        send1.setBackground(Color.CYAN);
        send1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                send1();
            }
        });
        send1.setVisible(true);
        send1.setBounds(0,650,150,45);



        send2=new JButton("发送");
        Icon iconSend2 = new ImageIcon("C:\\res\\button.png");
        send2.setIcon(iconSend2);
        send2.setFont(f);
        send2.setBackground(Color.CYAN);
        send2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                send2();
            }
        });
        send2.setVisible(true);
        send2.setBounds(600,650,150,45);

        tf1.setBounds(0,550,150,50);
        tf2.setBounds(600,550,150,50);
        ta.setBounds(200,550,300,200);
        ta.setBackground(Color.lightGray);
        con.add(jb);
        con.add(reset);
        con.add(send1);
        con.add(send2);
        con.add(tf1);
        con.add(tf2);
        con.add(ta);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭按钮

        playMusic();

        this.addMouseListener(this);//添加监听
        this.setSize(800, 800);//
        //this.setIconImage(new ImageIcon("D:\\20190625095140.jpg").getImage());
        //this.setBackground(Color.lightGray);
        this.setVisible(true);

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds((width - 800) / 2,
                (height - 800) / 2, 800, 800);

    }

    public void send1(){
        String tf_str = tf1.getText().trim();
        tf1.setText("");
        tf_str = "左边："+tf_str;
        ta.append(tf_str+"\r\n");
        tf2.requestFocus();
    }

    public void send2(){
        String tf_str = tf2.getText().trim();
        tf2.setText("");
        tf_str = "右边："+tf_str;
        ta.append(tf_str+"\r\n");
        tf1.requestFocus();
    }
    static void playMusic(){//背景音乐播放
        try {
            URL cb;
            File f = new File("C:\\res\\music.wav"); // 引号里面的是音乐文件所在的路径
            cb = f.toURL();
            AudioClip aau;
            aau = Applet.newAudioClip(cb);

            aau.play();
            aau.loop();//循环播放
//            FileInputStream fileau=new FileInputStream("C:\\res\\music.mp3");
//            AudioStream as=new AudioStream(fileau);
//            AudioPlayer.player.start(as);
            System.out.println("可以播放");
            // 循环播放 aau.play()
            //单曲 aau.stop()停止播放

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public void reset(){
        this.v.clear();
        this.black.clear();
        this.white.clear();
        this.repaint();
    }


    public void jbClicked(){
        if (v.isEmpty()) {
            JOptionPane.showMessageDialog(this, "没有棋可悔");
        } else {
            if (v.size() % 2 == 0) {    //判断是白棋悔棋，还是黑棋悔棋
                int i =JOptionPane.showConfirmDialog(null, "黑棋要悔棋", "询问",JOptionPane.YES_NO_OPTION);
                if(i==1){
                    blackCount++;
                    if (blackCount > 3) {
                        JOptionPane.showMessageDialog(this, "黑棋已经悔了"+blackCount+"步");
                    } else {
                        v.remove(v.lastElement());
                        this.repaint();
                    }
                }

            } else {
                int i =JOptionPane.showConfirmDialog(null, "白棋要悔棋", "询问",JOptionPane.YES_NO_OPTION);
                if(i==0){
                    whiteCount++;
                    if (whiteCount > 3) {
                        JOptionPane.showMessageDialog(this, "白棋已经悔了"+whiteCount+"步");
                    } else {
                        v.remove(v.lastElement());
                        this.repaint();
                    }
                }

            }
        }
    }

    /**
     * 画棋盘和棋子
     * @param
     */
    public void paint(Graphics g) {

        g.setFont(new Font("宋体", Font.BOLD, 24));
        g.clearRect(0, 0, this.getWidth(), this.getHeight());//清除画板
        g.drawImage(new ImageIcon("C:\\res\\backs.jpg").getImage(),0,0,800,800,null);
        g.setColor(Color.BLACK);//绘制网格颜色
        g.drawRect(px, py, width, height);//网格大小
        g.drawString("Five-in-a-row", 240, 90);

        for (int i=0; i<15; i++) {//循环画出
            g.drawLine(pxw+i*w, py, pxw+i*w, hline);//每条横线和竖线
            g.drawLine(px, pyw+i*w, vline, pyw+i*w);
        }

        for (int x=0; x<v.size(); x++) {
            String str = (String)v.get(x);
            String tmp[] = str.split("-");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);
            a = a * w + px;
            b = b * w + py;
            if (x%2 == 0) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.BLACK);
            }
            g.fillArc(a-w/2, b-w/2, w, w, 0, 360);
        }
        jb.repaint();
        reset.repaint();
        send1.repaint();
        send2.repaint();
        tf1.repaint();
        tf2.repaint();
        ta.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {//鼠标点击事件也就是左键或者右键点击
        if (e.getButton() == e.BUTTON1) {
            int x = e.getX();
            int y = e.getY();
            x = (x - x % w) + (x % w > w / 2 ? w : 0);
            y = (y - y % w) + (y % w > w / 2 ? w : 0);
            x = (x - px) / w;
            y = (y - py) / w;

            if (x >= 0 && y >= 0 && x <= 16 && y <= 16) {
                if (v.contains(x+"-"+y)) {
                    JOptionPane.showMessageDialog(this,"已经有棋了！");
                } else {
                    v.add(x+"-"+y);
                    this.repaint();
                    if (v.size() % 2 == 0) {
                        black.add(x+"-"+y);//这里加了个黑棋
                        this.victory(x, y, black);
                    } else {
                        white.add(x+"-"+y);//加了个白棋
                        this.victory(x, y, white);
                    }
                }
            } else {
            }
        }
    }

    /**
     * 判断胜利的方法
     * @param x
     * @param y
     * @param contain
     */
    private void victory(int x, int y, Vector contain) {
        int cv = 0;    //垂直方向棋子数量
        int ch = 0;    //水平方向棋子数量
        int ci1 = 0; //斜面方向棋子数量1
        int ci2 = 0; //斜面方向棋子数量2

        //计算水平方向棋子数量
        for (int i=1; i<5; i++) {
            if (contain.contains((x+i)+"-"+y)) {
                ch++;
            } else {
                break;
            }
        }
        for (int i=1; i<5; i++) {
            if (contain.contains((x-i)+"-"+y)) {
                ch++;
            } else {
                break;
            }
        }

        //计算垂直方向棋子数量
        for (int i=1; i<5; i++) {
            if (contain.contains(x+"-"+(y+i))) {
                cv++;
            } else {
                break;
            }
        }
        for (int i=1; i<5; i++) {
            if (contain.contains(x+"-"+(y-i))) {
                cv++;
            } else {
                break;
            }
        }

        //计算45°斜面方向棋子数量
        for (int i=1; i<5; i++) {
            if (contain.contains((x+i)+"-"+(y+i))) {
                ci1++;
            } else {
                break;
            }
        }
        for (int i=1; i<5; i++) {
            if (contain.contains((x-i)+"-"+(y-i))) {
                ci1++;
            } else {
                break;
            }
        }

        //计算135°斜面方向棋子数量
        for (int i=1; i<5; i++) {
            if (contain.contains((x+i)+"-"+(y-i))) {
                ci2++;
            } else {
                break;
            }
        }
        for (int i=1; i<5; i++) {
            if (contain.contains((x-i)+"-"+(y+i))) {
                ci2++;
            } else {
                break;
            }
        }

        if (ch>=4 || cv>=4 ||ci1>=4 ||ci2>=4) {
            System.out.println(v.size()+"步棋");
            if (v.size() % 2 == 0) {
                //判断是黑棋赢，还是白棋赢
                JOptionPane.showMessageDialog(null, "黑棋赢了");
            } else {
                JOptionPane.showMessageDialog(null, "白棋赢了");
            }
            this.v.clear();
            this.black.clear();
            this.white.clear();
            this.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
