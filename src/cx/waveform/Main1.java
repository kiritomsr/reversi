package cx.waveform;

import java.awt.EventQueue;

/**
 * Main1
 * 实验题目1主程序入口
 * Launch the WavWindow
 * to realize logical operation and generate waveform diagram
 * @author 蔡翔
 * @since JDK 1.8
 */
public class Main1
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(
		new Runnable()
		{
			public void run()
			{
				try
				{
					WavWindow frame = new WavWindow();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
