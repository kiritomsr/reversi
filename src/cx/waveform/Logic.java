package cx.waveform;

/**
 * Logic
 * 逻辑运算类
 * 属性包括信号序列长度和两个信号的序列
 * 具有相关的与、或、非、异或、同或计算和get、set、覆盖toString等成员方法
 * @author 蔡翔
 * @since JDK 1.8
 */
public class Logic
{
	private int length;
	private boolean[]A,B,F;
	
	public Logic(int length)
	{
		this.length=length;
		A=new boolean[length];
		B=new boolean[length];
	}//构造方法
	
	public Logic()
	{
		length=16;
		A=new boolean[16];
		B=new boolean[16];
	}//重载无参数构造方法
	
	public void setLength(int length)
	{
		this.length=length;
	}
	
	public void setLength(String strLength)
	{
		length = Integer.parseInt(strLength);
	}
	
	public void setA(boolean A[])
	{
		this.A=A;
	}
	
	public void setA(String strA)
	{
		char chrA[] = strA.toCharArray();
		for(int i=0;i<length;i++)
		{
			if(chrA[i]=='0') A[i]=false;
			if(chrA[i]=='1') A[i]=true;
		}
	}//重载String类型传参
	
	public void setB(boolean B[])
	{
		this.B=B;
	}
	
	public void setB(String strB)
	{
		char chrB[] = strB.toCharArray();
		for(int i=0;i<length;i++)
		{
			if(chrB[i]=='0') B[i]=false;
			if(chrB[i]=='1') B[i]=true;
		}
	}//重载String类型传参
	
	public boolean[] not_A()
	{
		F=new boolean[length];
		for(int i=0;i<length;i++)
			F[i]=!A[i];
		return F;
	}//A非运算
	
	public boolean[] not_B()
	{
		F=new boolean[length];
		for(int i=0;i<length;i++)
			F[i]=!B[i];
		return F;
	}//B非运算
	
	public boolean[] and()
	{
		F=new boolean[length];
		for(int i=0;i<length;i++)
			F[i]=A[i]&&B[i];
		return F;
	}//与运算
	
	public boolean[] or()
	{
		F=new boolean[length];
		for(int i=0;i<length;i++)
			F[i]=A[i]||B[i];
		return F;
	}//或运算
	
	public boolean[] xor()
	{
		F=new boolean[length];
		for(int i=0;i<length;i++)
			F[i]=A[i]^B[i];
		return F;
	}//异或运算
	
	public boolean[] xnor()
	{
		F=new boolean[length];
		for(int i=0;i<length;i++)
			F[i]=!(A[i]^B[i]);
		return F;
	}//同或运算
	
	public int getLength()
	{
		return length;
	}
	
	public boolean[] getA()
	{
		return A;
	}
	
	public boolean[] getB()
	{
		return B;
	}
	
	public boolean[] getF()
	{
		return F;
	}
	
	public int getF(int i)
	{
		return F[i]? 1:0;
	}//重载返回某一位
	
	public String toString()
	{
		String strF="";
		for(int i=0;i<length;i++)
		{
			if(F[i]==false) strF=strF+0;
			if(F[i]==true) strF=strF+1;
		}
		return strF;
	}//覆盖toString返回计算结果字符串
}
