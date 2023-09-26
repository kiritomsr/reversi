package cx.waveform;

import javax.print.attribute.standard.Fidelity;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

/**
 * WavWindow
 * Create the frame
 * to realize logical operation and generate waveform diagram
 * @author 蔡翔
 * @since JDK 1.8
 */
public class WavWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbl_length;
	private JLabel lbl_A;
	private JLabel lbl_B;
	private JLabel lbl_F;
	private JTextField txt_length;
	private JTextField txt_A;
	private JTextField txt_B;
	private JTextField txt_F;
	private JButton btn_notA;
	private JButton btn_notB;
	private JButton btn_AandB;
	private JButton btn_AorB;
	private JButton btn_AxorB;
	private JButton btn_AxnorB;
	private Logic wav;
	
	public WavWindow()
	{
		wav = new Logic();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbl_length = new JLabel("序列长度");
		lbl_length.setBounds(20, 5, 60, 15);
		contentPane.add(lbl_length);
		
		lbl_A = new JLabel("A");
		lbl_A.setBounds(5, 30, 20, 20);
		contentPane.add(lbl_A);
		
		lbl_B = new JLabel("B");
		lbl_B.setBounds(5, 60, 20, 20);
		contentPane.add(lbl_B);
		
		lbl_F = new JLabel("F");
		lbl_F.setBounds(205, 10, 20, 20);
		contentPane.add(lbl_F);
		
		txt_length = new JTextField("键入整数");
		txt_length.setBounds(80, 0, 65, 25);
		contentPane.add(txt_length);
		txt_length.setColumns(10);
		
		txt_A = new JTextField();
		txt_A.setBounds(15, 25, 168, 30);
		contentPane.add(txt_A);
		txt_A.setColumns(10);
		
		txt_B = new JTextField();
		txt_B.setBounds(15, 55, 168, 30);
		contentPane.add(txt_B);
		txt_B.setColumns(10);
		
		txt_F = new JTextField();
		txt_F.setEditable(false);
		txt_F.setColumns(10);
		txt_F.setBounds(215, 5, 210, 30);
		contentPane.add(txt_F);
		
		btn_notA = new JButton("A非");
		btn_notA.setBounds(20, 90, 60, 30);
		btn_notA.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				wav.setLength(txt_length.getText());
				wav.setA(txt_A.getText());
				wav.not_A();
				txt_F.setText(wav.toString());
			}
		});
		contentPane.add(btn_notA);
		
		btn_notB = new JButton("B非");
		btn_notB.setBounds(80, 90, 60, 30);
		btn_notB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				wav.setLength(txt_length.getText());
				wav.setB(txt_B.getText());
				wav.not_B();
				txt_F.setText(wav.toString());
			}
		});
		contentPane.add(btn_notB);
		
		btn_AandB = new JButton("A与B");
		btn_AandB.setBounds(23, 120, 110, 30);
		btn_AandB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				wav.setLength(txt_length.getText());
				wav.setA(txt_A.getText());
				wav.setB(txt_B.getText());
				wav.and();
				txt_F.setText(wav.toString());
			}
		});
		contentPane.add(btn_AandB);
		
		btn_AorB = new JButton("A或B");
		btn_AorB.setBounds(23, 150, 110, 30);
		btn_AorB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				wav.setLength(txt_length.getText());
				wav.setA(txt_A.getText());
				wav.setB(txt_B.getText());
				wav.or();
				txt_F.setText(wav.toString());
			}
		});
		contentPane.add(btn_AorB);
		
		btn_AxorB = new JButton("A异或B");
		btn_AxorB.setBounds(23, 180, 110, 30);
		btn_AxorB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				wav.setLength(txt_length.getText());
				wav.setA(txt_A.getText());
				wav.setB(txt_B.getText());
				wav.xor();
				txt_F.setText(wav.toString());
			}
		});
		contentPane.add(btn_AxorB);
		
		btn_AxnorB = new JButton("A同或B");
		btn_AxnorB.setBounds(23, 210, 110, 30);
		btn_AxnorB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				repaint();
				//paintWav();
			}
		});
		contentPane.add(btn_AxnorB);
		
		Panel panel = new Panel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(190, 60, 250, 200);
		contentPane.add(panel);

	}

	public void paintWav(){
		wav.setLength(txt_length.getText());
		wav.setA(txt_A.getText());
		wav.setB(txt_B.getText());
		wav.xnor();
		txt_F.setText(wav.toString());
		this.repaint();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.BLACK);
		int w = getWidth();
		int h = getHeight();

		g.drawLine(150, 150,300,300);
		g.drawLine(0, 0,300,300);
		for(int i=0;i<wav.getLength();i++) 
		{
			g.drawLine(150, 150,300,300);
		}
	}

}
