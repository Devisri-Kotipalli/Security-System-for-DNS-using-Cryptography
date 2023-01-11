import javax.swing.*;
import java.awt.event.*;

class RSAKey implements ActionListener
{
	JButton jb,jb1,jb2;
	JFrame jf;

	RSAKey()
	{
		jf=new JFrame("RSA KEY");
		JInternalFrame jf1=new JInternalFrame("KEY Value");
		JLabel jl=new JLabel("Exponent Value:");
		JLabel jl1=new JLabel("N Value:");
		JPasswordField jt=new JPasswordField();
		JPasswordField jt1=new JPasswordField();
		jb=new JButton("RSAKeyGen");
		jb1=new JButton("Send");
		jb2=new JButton("Exit");
		JPanel jp=new JPanel();
		JPanel jp1=new JPanel();
		jp.add(jl);
		jp.add(jl1);
		jp.add(jt);
		jp.add(jt1);
		jp.add(jb);
		jp.add(jb1);
		jp.add(jb2);
		jb.addActionListener(this);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jp.setLayout(null);
		jl.setBounds(40,60,100,25);
		jt.setBounds(150,60,100,25);
		jl1.setBounds(40,90,100,25);
		jt1.setBounds(150,90,100,25);
		jb.setBounds(130,130,120,25);
		jb1.setBounds(50,180,75,25);
		jb2.setBounds(140,180,75,25);
		jf1.setContentPane(jp);
		jp1.setLayout(null);
		jf1.setBounds(30,30,180,180);
		jf1.setVisible(true);
		jf.setContentPane(jf1);
		jf.setSize(300,300);
		jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==jb)
		{
			RSAKeyDsgn rsad=new RSAKeyDsgn();
		}
		if(ae.getSource()==jb1)
		{
			try
			{
				new Timers();
				jf.setVisible(false);
				EnRSA en=new EnRSA();
				en.WriteRSA(keyrsa1.d,keyrsa1.n);
			}
			catch(Exception ex){}
		}
		if(ae.getSource()==jb2)
		{
			//System.exit(0);
			jf.setVisible(false);
		}
	}
	public static void main(String arg[])
	{
		RSAKey rsa=new RSAKey();
	}
}
