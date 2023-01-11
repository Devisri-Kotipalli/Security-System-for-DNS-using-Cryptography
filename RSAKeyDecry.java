import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class RSAKeyDecry implements ActionListener
{
	JButton jb,jb1,jb2;
	static long d,n;
	JPasswordField jt,jt1;
	JFrame jf;
	static String str;

	RSAKeyDecry()
	{
		jf=new JFrame("RSA DeKEY");
		JInternalFrame jf1=new JInternalFrame("KEY Value");
		JLabel jl=new JLabel("D Value:");
		JLabel jl1=new JLabel("N Value:");
		jt=new JPasswordField();
		jt1=new JPasswordField();
		//jb=new JButton("RSAKeyGen");
		jb=new JButton("Send");
		jb2=new JButton("Exit");
		JPanel jp=new JPanel();
		JPanel jp1=new JPanel();
		jp.add(jl);
		jp.add(jl1);
		jp.add(jt);
		jp.add(jt1);
		jp.add(jb);
		//jp.add(jb1);
		jp.add(jb2);
		jb.addActionListener(this);
		//jb1.addActionListener(this);
		jb2.addActionListener(this);
		jp.setLayout(null);
		jl.setBounds(40,60,100,25);
		jt.setBounds(150,60,100,25);
		jl1.setBounds(40,90,100,25);
		jt1.setBounds(150,90,100,25);
		//jb.setBounds(130,130,120,25);
		jb.setBounds(50,180,75,25);
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
			String sr1=jt.getText();
			String sr2=jt1.getText();
			try
			{
				jf.setVisible(false);
				DERSA dn=new DERSA();
				d=Long.parseLong(sr1);
				n=Long.parseLong(sr2);
				str=Req.dd;
				dn.ProcessDecrp(d,n,str);
			}
			catch(Exception ew){}
		}
		if(ae.getSource()==jb2)
		{
			jf.setVisible(false);
		}
	}
}
