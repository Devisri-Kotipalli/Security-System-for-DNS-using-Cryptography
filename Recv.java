import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Recv implements ActionListener
{
	JFrame jf;
	static JTextArea ja;
	JButton jb;
	JLabel jl;
	JScrollPane jsp;
	static String sr,sr1,sr2;
	static long d,n;
	Recv()
	{
		jf=new JFrame("Receiver");
		jl=new JLabel("Receive the Message");
		ja=new JTextArea();
		jsp=new JScrollPane(ja);
		jb=new JButton("Receive");
		JPanel jp=new JPanel();
		jp.add(jb);
		jp.add(jsp);
		jp.add(jl);
		jb.addActionListener(this);
		jp.setLayout(null);
		jl.setBounds(40,60,150,25);
		jsp.setBounds(40,90,250,300);
		jb.setBounds(100,420,100,25);
		jf.setContentPane(jp);
		jf.setSize(350,500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}


	public void actionPerformed(ActionEvent ae)
	{
		if((JButton)jb==ae.getSource())
		{
			RSAKeyDecry dn=new RSAKeyDecry();
		}
	}
}
