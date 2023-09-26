import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class Reversi extends JFrame{
    JFrame playFrame;
    JPanel jPanel,jPanel01,jPanel02;
    JPanel[] jPanels;
    JLabel jLabel01,jLabel02,jLabel03;
    JButton jButton,jButton02;
    final int gridSide = 80;
    final int chessSide = gridSide - 30;
    int gridNum;
    int clickA = -1, clickB = -1;
    int nowTurn;
    int nowCan;
    int mode;
    int blackNum,whiteNum;
    int[][] nowChess ;
    boolean[][] canChess ;
    MouseListener myMouseListener;

    public Reversi(int modeChoose,int setNum){

        playFrame = this;
        this.setTitle("Reversi");

        mode = modeChoose;
        gridNum = setNum;

        nowChess = new int[gridNum][gridNum];
        canChess = new boolean[gridNum][gridNum];

        FlowLayout frameLayout = new FlowLayout(FlowLayout.CENTER,0,0);
        jPanel = new JPanel(frameLayout);

        jPanel01 = new JPanel(new GridLayout(gridNum, gridNum));
        jPanel02 = new JPanel(new GridLayout(5, 1));
        jLabel01 = new JLabel("Black turn...");
        jLabel02 = new JLabel("Black: 2");
        jLabel03 = new JLabel("White: 2");
        jPanel01.setPreferredSize(new Dimension(gridNum*gridSide, gridNum*gridSide));
        jPanel.add(jPanel01);
        jPanel.add(jPanel02);
        jLabel01.setFont(new Font("微软雅黑", Font.BOLD, 20));
        jLabel02.setFont(new Font("微软雅黑", Font.BOLD, 20));
        jLabel03.setFont(new Font("微软雅黑", Font.BOLD, 20));
        jPanel02.add(jLabel01);
        jPanel02.add(jLabel02);
        jPanel02.add(jLabel03);

        if(mode != 2){
            jButton = new JButton("Robot");
            jPanel02.add(jButton);
            jButton.addActionListener(actionEvent -> autoWait());
        }

        jButton02 = new JButton("Return");
        jPanel02.add(jButton02);
        jButton02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playFrame.dispose();
                new StartFrame(8);
            }
        });

        jPanel02.setPreferredSize(new Dimension(gridSide * 2 ,gridNum * gridSide));
        this.add(jPanel);
        initial();
        //draw chess board
        jPanels = new JPanel[gridNum * gridNum];
        for (int i = 0;i < gridNum * gridNum;i++){
            jPanels[i] = new JPanel();
            jPanels[i].setOpaque(true);
            if ((i / gridNum + i % gridNum) % 2 == 1) {
                jPanels[i].setBackground(new Color(	210,105,30));
            }else {
                jPanels[i].setBackground(new Color(244,164,96));
            }
            jPanel01.add(jPanels[i]);
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myMouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                clickA = mouseEvent.getX()  / gridSide;
                clickB = mouseEvent.getY()  / gridSide;

                validChess();
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
        };
        jPanel01.addMouseListener(myMouseListener);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);

    }

    public void paint(Graphics g){


        super.paint(g);

        g.drawLine(50,50,100,100);

        for(int i = 0;i < gridNum;i++){
            for(int j = 0;j < gridNum;j++){

                int paintX = i * gridSide + gridSide / 2 - chessSide / 2 + this.getInsets().left;
                int paintY = j * gridSide + gridSide / 2 - chessSide / 2 + this.getInsets().top ;

                if(nowChess[i][j] == 1){
                    g.setColor(Color.BLACK);
                    g.fillOval(paintX,paintY,chessSide,chessSide);
                    continue;
                }

                if(nowChess[i][j] == -1){
                    g.setColor(Color.WHITE);
                    g.fillOval(paintX,paintY,chessSide,chessSide);
                    continue;
                }

                if(canChess[i][j]){
                    g.setColor(new Color(192,192,192,128));
                    g.fillOval(paintX,paintY,chessSide,chessSide);
                }

            }
        }

        if(clickA!=-1){
            int paintA = clickA * gridSide + gridSide / 2 - chessSide / 4 + this.getInsets().left;
            int paintB = clickB * gridSide + gridSide / 2 - chessSide / 4 + this.getInsets().top ;
            g.setColor(new Color(192,192,192,128));
            g.fillOval(paintA,paintB,chessSide / 2,chessSide / 2);
        }

    }

    public void initial(){

        //initialize
        for(int i=0;i<gridNum;i++) {
            for (int j = 0; j < gridNum; j++) {
                nowChess[i][j] = 0;
            }
        }

        nowChess[gridNum/2-1][gridNum/2-1] = 1;
        nowChess[gridNum/2][gridNum/2] = 1;
        nowChess[gridNum/2][gridNum/2-1] = -1;
        nowChess[gridNum/2-1][gridNum/2] = -1;
        nowTurn = 1;

        setCanChess();

    }

    public void changeTurn(){
        nowTurn = - nowTurn;
        switch (nowTurn){
            case 1: jLabel01.setText("Black turn...");
                    break;
            case -1: jLabel01.setText("White turn...");
                    break;
        }
    }

    public void deterTurn(){

        changeTurn();
        setCanChess();

        if(nowCan == 0){
            changeTurn();
            setCanChess();

            if(nowCan == 0){
                countChess();
                this.repaint();
                String[] options ={"Retry","Exit"};
                String content;
                if(blackNum > whiteNum) {content = "Black wins!";}
                else if(blackNum < whiteNum) {content = "White wins!";}
                else {content = "Tie!";}

                int result = JOptionPane.showOptionDialog(this,content,"Game over",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "Retry");
                switch (result) {
                    case 0:
                        this.dispose();
                        new StartFrame(8);
                        break;
                    case 1:
                        System.exit(0);
                }
            }
        }
    }

    public void validChess(){

        if(canChess[clickA][clickB]){
            nowChess[clickA][clickB] = nowTurn;
            turnChess();
            countChess();
            deterTurn();


            this.repaint();





        }

    }

    public void turnCanChess(int i,int j,int x,int y,int oppoSide,int bias){

        if((i+x<gridNum) && (i+x>-1) && (j+y<gridNum) && (j+y>-1)){

            if(nowChess[i+x][j+y] == -nowTurn){
                bias++;
                turnCanChess(i+x,j+y,x,y,1,bias);
            }

            if((oppoSide == 1) && (nowChess[i+x][j+y] == nowTurn)){

                for(int k=1;k<=bias;k++){
                    nowChess[clickA +k*x][clickB +k*y] = nowTurn;
                }
            }

        }
    }

    public void turnChess(){
        turnCanChess(clickA, clickB,1,0,0,0);
        turnCanChess(clickA, clickB,0,1,0,0);
        turnCanChess(clickA, clickB,-1,0,0,0);
        turnCanChess(clickA, clickB,0,-1,0,0);
        turnCanChess(clickA, clickB,1,1,0,0);
        turnCanChess(clickA, clickB,1,-1,0,0);
        turnCanChess(clickA, clickB,-1,1,0,0);
        turnCanChess(clickA, clickB,-1,-1,0,0);
    }

    public void checkCanChess(int i,int j,int x,int y,int oppoSide,int bias){

        if(canChess[i][j]) return;
        if((i+x<gridNum) && (i+x>-1) && (j+y<gridNum) && (j+y>-1)){

            if(nowChess[i+x][j+y] == -nowTurn){
                bias++;
                checkCanChess(i+x,j+y,x,y,1,bias);
            }

            if((oppoSide == 1) && (nowChess[i+x][j+y] == nowTurn)){
                canChess[i-bias*x][j-bias*y] = true;
                nowCan = 1;
            }

        }
    }

    public void setCanChess(){

        nowCan = 0;
        for(int i=0;i<gridNum;i++) {
            for (int j = 0; j < gridNum; j++) {
            canChess[i][j] = false;
            }
        }

        for(int j=0;j<gridNum;j++) {
            for (int i = 0; i < gridNum; i++) {
                if (nowChess[i][j] == 0) {

                    checkCanChess(i,j,1,0,0,0);
                    checkCanChess(i,j,-1,0,0,0);
                    checkCanChess(i,j,0,1,0,0);
                    checkCanChess(i,j,0,-1,0,0);
                    checkCanChess(i,j,1,1,0,0);
                    checkCanChess(i,j,1,-1,0,0);
                    checkCanChess(i,j,-1,1,0,0);
                    checkCanChess(i,j,-1,-1,0,0);

                }
            }
        }
    }

    public void countChess(){
        blackNum = 0;
        whiteNum = 0;
        for(int i=0;i<gridNum;i++) {
            for (int j = 0; j < gridNum; j++) {
                if(nowChess[i][j] == 1) blackNum++;
                if(nowChess[i][j] == -1) whiteNum++;
            }
        }
        jLabel02.setText("Black: " + blackNum);
        jLabel03.setText("White: " + whiteNum);

    }

    public void autoWait(){
        autoChess();
    }

    public void autoChess(){

        int[][] weight = new int[gridNum][gridNum];
        int chooseA = 0,chooseB = 0;
        int clickA, clickB;

        for(int i=0;i<gridNum;i++) {
            for (int j = 0; j < gridNum; j++) {
                weight[i][j] = 0;
            }
        }

        for(int i=0;i<gridNum;i++) {
            for (int j = 0; j < gridNum; j++) {
                if(!canChess[i][j]){
                    weight[i][j] = -10000000;
                    continue;
                }
                chooseA = i;
                chooseB = j;
                if(   ((i==0)&&(j==0)) || ((i==0)&&(j==7))  || ((i==7)&&(j==0)) || ((i==7)&&(j==7))  ) {
                    weight[i][j] = 1000;
                    continue;
                }

                if(   (i==0) || (i==7) || (j==0) || (j==7)   ) {
                    weight[i][j] = 500;
                    continue;
                }

                if(   ((i==1)&&(j==2)) || ((i==2)&&(j==1))  || ((i==1)&&(j==5)) || ((i==5)&&(j==1)) || ((i==6)&&(j==2)) || ((i==2)&&(j==6))  || ((i==6)&&(j==5)) || ((i==5)&&(j==6)) ) {
                    weight[i][j] = -100;
                    continue;
                }

                if(  ((i==1)&&(j==1)) || ((i==1)&&(j==6))  || ((i==6)&&(j==1)) || ((i==6)&&(j==6))  ) {
                    weight[i][j] = -1000;
                    continue;
                }

                if(   (i==1) || (i==6) || (j==1) || (j==6)   ) {
                    weight[i][j] = -50;
                    continue;
                }

                if(   (i==2) || (i==5) || (j==2) || (j==5)   ) {
                    weight[i][j] = 50;

                }
            }
        }

        for(int i=0;i<gridNum;i++) {
            for (int j = 0; j < gridNum; j++) {
                if(weight[chooseA][chooseB]<weight[i][j]){
                    chooseA = i;
                    chooseB = j;
                }
            }
        }

        clickA = chooseA * gridSide + gridSide / 2;
        clickB = chooseB * gridSide + gridSide / 2;

        long time = new  Date().getTime();

        MouseEvent e = new MouseEvent(jPanel01,501,time, InputEvent.BUTTON1_DOWN_MASK,clickA,clickB,1,false);

        myMouseListener.mousePressed(e);

    }

    public static void main(String[] args){
        new StartFrame(8);
    }
}

class StartFrame extends JFrame{
    int girdNum;
    JFrame jFrameStart;
    JPanel jPanel;
    JButton jButton01,jButton02,jButton03;

    public StartFrame(int setNum){
        jFrameStart = this;
        girdNum = setNum;
        this.setTitle("Reversi-Menu");

        jPanel = new JPanel(new GridLayout(3,1));
        jPanel.setPreferredSize(new Dimension(500,500));
        jButton01 = new JButton("Single Player");
        jButton02 = new JButton("Double Player");
        jButton03 = new JButton("About");
        jPanel.add(jButton02);
        if(girdNum == 8){
            jPanel.add(jButton01);
        }

        jPanel.add(jButton03);
        this.add(jPanel);




        jButton01.addActionListener(actionEvent -> {
            jFrameStart.dispose();
            new Reversi(11,girdNum);
        });

        jButton02.addActionListener(actionEvent -> {
            jFrameStart.dispose();
            new Reversi(2,girdNum);
        });

        jButton03.addActionListener(actionEvent -> {
            jFrameStart.dispose();
            new SettingFrame();
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}

class SettingFrame extends JFrame{
    JFrame jFrameSetting;
    JPanel jPanel;
    JPanel jPanel01,jPanel02,jPanel03;
    JButton jButton01;
    JRadioButton jRadioButton01,jRadioButton02,jRadioButton03;
    ButtonGroup buttonGroup;
    JLabel jLabel11,jLabel12,jLabel13,jLabel14,jLabel02;

    public SettingFrame(){
        jFrameSetting = this;
        this.setTitle("Reversi-Setting");

        jPanel = new JPanel(new GridLayout(3,1));
        jPanel.setPreferredSize(new Dimension(500,500));

        GridLayout gridLayout01 = new GridLayout(4,1);

        jPanel01 = new JPanel(gridLayout01);
        jLabel11 = new JLabel("This is a reversi game.");
        jLabel12 = new JLabel("Made by msr");
        jLabel13 = new JLabel("2020.6.13");
        jLabel14 = new JLabel("Version1.0");
        jLabel11.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jLabel12.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jLabel13.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jLabel14.setHorizontalAlignment((int) CENTER_ALIGNMENT);

//        ImageIcon qrc = new ImageIcon("src/qrcode.jpg");
//        qrc.setImage(qrc.getImage().getScaledInstance(150,200,Image.SCALE_DEFAULT));
//        jLabel01.setIcon(qrc);

        jPanel02 = new JPanel();
        jLabel02 = new JLabel("Grid number:");
        jRadioButton01 = new JRadioButton("6");
        jRadioButton02 = new JRadioButton("8");
        jRadioButton03 = new JRadioButton("10");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButton01);
        buttonGroup.add(jRadioButton02);
        buttonGroup.add(jRadioButton03);

        jRadioButton02.setSelected(true);

        jPanel03 = new JPanel();
        jButton01 = new JButton("Retrun");
        jButton01.addActionListener(actionEvent -> {
            if(jRadioButton01.isSelected()) new StartFrame(6);
            if(jRadioButton02.isSelected()) new StartFrame(8);
            if(jRadioButton03.isSelected()) new StartFrame(10);

            jFrameSetting.dispose();

        });

        jPanel01.add(jLabel11);
        jPanel01.add(jLabel12);
        jPanel01.add(jLabel13);
        jPanel01.add(jLabel14);
        jPanel02.add(jLabel02);
        jPanel02.add(jRadioButton01);
        jPanel02.add(jRadioButton02);
        jPanel02.add(jRadioButton03);
        jPanel03.add(jButton01);
        jPanel.add(jPanel01);
        jPanel.add(jPanel02);
        jPanel.add(jPanel03);
        jFrameSetting.add(jPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
