import java.io.*;
import java.net.*;
import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;

public class Domn12 implements ActionListener
{
	static String str90,str91,str92,str93,str94,str95;
	static String str1,str2,str3,str4,str5,str15,str21,str35;
	static String output;
	static String wq =new String();
	static JFrame jf;
	static JTextField t1,t2,t3,t4;
	static JLabel l,l1,l2,l3,l4,l5;
	static JTextArea ta;
	static JScrollPane js;
	static JButton Send,Exit;
	static JPanel p;
	static Socket s2;
	static String subs1;
	static String sub="";
	static boolean succ;
	Domn12()
	{
		String inf="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try
		{
			UIManager.setLookAndFeel(inf);
		}
		catch(Exception e){}
		jf =new JFrame("Domain1");
		l = new JLabel("Domain-1 Contains the Sub-Domains");
		l1 = new JLabel("SenderName			:");
		l2 = new JLabel("ReceiverName		:");
		l3 = new JLabel("Public Key			:");
		l4 = new JLabel("Signature			:");
		l5 = new JLabel("EncyyptedData		:");
		t1 = new JTextField(20);
		t2 = new JTextField(20);
		t3 = new JTextField(20);
		t4 = new JTextField(20);
		ta = new JTextArea();
		js = new JScrollPane(ta);
		Send= new JButton("Send");
		Exit= new JButton("Exit");
		Font f = new Font("Bold Italic",Font.PLAIN,20);
		l.setFont(f);
		p = new JPanel();
		p.add(l);
		p.add(l1);
		p.add(l2); 
		p.add(l3);
		p.add(l4);
		p.add(l5);
		p.add(t1);
		p.add(t2);
		p.add(t3);
		p.add(t4);
		p.add(js);
		p.add(Send);
		p.add(Exit);
		Send.addActionListener(this);
		Exit.addActionListener(this);
		p.setLayout(null);
		l.setBounds(40,40,350,25);
		l1.setBounds(40,70,120,25);
		t1.setBounds(180,70,120,25);
		l2.setBounds(40,100,120,25);
		t2.setBounds(180,100,120,25);
		l3.setBounds(40,130,120,25);
		t3.setBounds(180,130,120,25);
		l4.setBounds(40,160,120,25);
		t4.setBounds(180,160,120,25);
		l5.setBounds(40,190,120,25);
		js.setBounds(40,220,300,200);
		Send.setBounds(100,430,75,25);
		Exit.setBounds(200,430,75,25);
		jf.setContentPane(p);
		jf.setSize(400,500);
		jf.setVisible(true);
	}

	public static void main(String args[])throws Exception

	{
		Domn12 d1 = new Domn12();
		succ=false;
		succ= d1.ServSoc();
	}

	public static boolean ServSoc()
	{
		boolean res1=false;
		try
		{
			ServerSocket ss = new ServerSocket(9998);
			Socket s1 = ss.accept();
			System.out.println("Domain-1 Contains the Sub-Domains are:");
			BufferedReader ps2 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
			str1= ps2.readLine();
			str1=str1.trim();
			System.out.println("SenderName     : "+str1);
			str2 = ps2.readLine();
			str2=str2.trim();
			System.out.println("SenderPassword : "+str2);
			str3 = ps2.readLine();
			str3=str3.trim();
			System.out.println("ReceiverName   : "+str3);

			str4 = ps2.readLine();
			str4=str4.trim();
			System.out.println("Encrypted Data : "+str4);
			try
			{
				int len=str4.length();
				System.out.println("Length:"+len);
				int len1=len/8;
				System.out.println("Length1:"+len1);
				int len2=len%8;
				int k=8;
				System.out.println("Length2:"+len2);
				int c=0;
				for(int l=0;l<len;l+=8)
				{
					System.out.println("Source:"+str1);
					if(k<=len-len2)
					{
						String subs = str4.substring(l,k);
						subs1 = subs + "\n";
						sub+=subs1;
						System.out.println("Stirnfg1:"+subs1);
						c++;
						k+=8;
						System.out.println("inside "+k);
					}
					System.out.println("Destination:"+str3);
				}

				if(len2>0)
				{
					System.out.println("Source:"+str1);
					System.out.println("inside ");
					int g =(len- c*8);
					System.out.println("Value:"+g);
					subs1 = str4.substring(c*8,len);
					sub+=subs1;
					System.out.println("Stirnfg2:"+subs1);
					System.out.println("Destination:"+str3);
				}

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			str5 = ps2.readLine();
			str5=str5.trim();
			System.out.println("Public Key     : "+str5);
			str15 = ps2.readLine();
			str15=str15.trim();
			System.out.println("Signature	   : "+str15);
			str35 = str1.substring(str1.lastIndexOf("."));
			System.out.println("SenderDomain   : "+str35);
			str21 = str3.substring(str3.lastIndexOf("."));
			System.out.println("ReceiverDomain : "+str21);
			PrintStream ps7 = new PrintStream(s1.getOutputStream());
			ps7.println(output);
			res1=pass(str1.trim());
			sen1(str1.trim());
			s1.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
 		}
		return res1;
	}

	public void actionPerformed(ActionEvent e12)
	{
		if(e12.getSource()==Send)
		{
			if (succ ==true)
			{
				JOptionPane.showMessageDialog(null,"Connection Provided","Connection",JOptionPane.INFORMATION_MESSAGE);
				Soc();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Connection Terminated","Connection",JOptionPane.ERROR_MESSAGE);
			System.out.println("Connection Refused");
		}

		if(Exit== e12.getSource())
		{
			System.exit(0);
		}
	}

	public static void Soc()
	{
		try
		{
			String input = JOptionPane.showInputDialog("Enter the System Name for DNS-Server");
			Socket s2 = new Socket(InetAddress.getByName(input),9999);
			PrintStream ps = new PrintStream(s2.getOutputStream());
			ps.println(str35);
			ps.println(str21);
			ps.println(str1);
			ps.println(str3);
			ps.println(str4);
			ps.println(str5);
			ps.println(str15);
			ps.flush();
			s2.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public static boolean pass(String temp)throws Exception
	{
		int Result;
		String Response;
		String Rname;
		Rname="";
		output="";
		Rname=temp;
		FileReader fr = new FileReader("password.txt");
		BufferedReader br = new BufferedReader(fr);
		String s="";
		String str[];
		boolean res1=false;
		while((s=br.readLine())!=null)
		{
			int i=0;
			str=s.split(" ");
			if(Rname.equalsIgnoreCase(str[i]))
			{
				Result=1;
				output=str[1];
				if(output.equalsIgnoreCase(str2))
				{
					res1=true;
					output="correct";
					System.out.println(output);
					JOptionPane.showMessageDialog(null,"Valid Password","Password",JOptionPane.INFORMATION_MESSAGE);
					break;
				}

				else
				{
					output="password is incorrect";
					System.out.println(output);
					JOptionPane.showMessageDialog(null,"InValid Password","Password",JOptionPane.ERROR_MESSAGE);
				}
			}
			i++;
		}
		return res1;
	}

	public static void sen1(String temp)throws Exception
	{
		int Result;
		String output1="";
		String Response;
		String Rname;
		Result=0;
		Rname=temp;
		FileReader fr = new FileReader("send1.txt");
		BufferedReader br = new BufferedReader(fr);
		String s="";
		String str[];

		while((s=br.readLine())!=null)
		{
			str=s.split(" ");
			if(Rname.equalsIgnoreCase(str[0]))
			{
				Result=1;
				output1=str[1];
				System.out.println("SenderIP : "+output1);
				break;
			}

			else
			{
				output1="Service Protected";
				System.out.println("SenderIP : "+output1);
			}
		}

		if(output1.equals("Service Protected"))
		{
			JOptionPane.showMessageDialog(null,"Server Protected","Service",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Server Provided","Service",JOptionPane.INFORMATION_MESSAGE);
			t1.setText(str1);
			t2.setText(str3);
			ta.setText("Source :"+str1+"\n"+sub+"\n"+"Destination :"+str3);
			t3.setText(str5);
			t4.setText(str15);
		}


	}

}
