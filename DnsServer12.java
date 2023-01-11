                                                                                                                                                                                                import java.io.*;
import java.net.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DnsServer12 extends Thread implements ActionListener

{
	static String str2,str21,str31,str41,str51,str71,str72;
	static String str80,str81,str82,str83,str84,str85;
	static JFrame jf;
	static JTextField t1,t2,t3,t4,t5,t6;
	static JLabel l,l1,l2,l3,l4,l5,l6,l7;
	static JTextArea ta;
	static JScrollPane js;
	static JButton Send,Exit;
	static JPanel p;
	static Socket s2;
	static String subs1;
	static String sub="";
	static String output;
	static String output1;
	// boolean succ;
	DnsServer12()
	{
		String inf="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try
		{
			UIManager.setLookAndFeel(inf);
		}
		catch(Exception e){}
		jf =new JFrame("DNSServer");
		l = new JLabel("DNS-Server Contains the Domains");
		l1 = new JLabel("SenderName			:");
		l2 = new JLabel("ReceiverName		:");
		l3 = new JLabel("Public Key			:");
		l4 = new JLabel("Signature			:");
		l5 = new JLabel("EncyyptedData		:");
		l6 = new JLabel("SenderDomain		:");
		l7 = new JLabel("ReceiverDomain		:");
		t1 = new JTextField(20);
		t2 = new JTextField(20);
		t3 = new JTextField(20);
		t4 = new JTextField(20);
		t5 = new JTextField(20);
		t6 = new JTextField(20);
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
		p.add(l6);
		p.add(l7);
		p.add(t1);
		p.add(t2);
		p.add(t3);
		p.add(t4);
		p.add(t5);
		p.add(t6);
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
		l6.setBounds(40,190,120,25);
		t5.setBounds(180,190,120,25);
		l7.setBounds(40,220,120,25);
		t6.setBounds(180,220,120,25);
		l5.setBounds(40,250,120,25);
		js.setBounds(40,270,300,180);
		Send.setBounds(100,470,75,25);
		Exit.setBounds(200,470,75,25);
		jf.setContentPane(p);
		jf.setSize(400,530);
		jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent e12)
	{
		if(e12.getSource()==Send)
		{
			
			Soc1();
			//d1.ServSoc1();
			//d1.Soc1();
		}
		else
		{
			System.out.println("Connection Refused");
		}

		if(Exit== e12.getSource())
		{
			System.exit(0);
		}

	}

	public void run(){
		try {
			DnsServer12.main(null);
			Thread.sleep(2000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static void main(String []args) throws Exception

	{

		DnsServer12 DS = new DnsServer12();
		DS.ServSoc1();
		//DS.Soc1();
		//DS.ServSoc11();
		//DS.Soc11();

	}

	public static void ServSoc1()
	{
		try
		{
			ServerSocket ss = new ServerSocket(9999);
			Socket s1 = ss.accept();
			BufferedReader ps2=new BufferedReader(new InputStreamReader(s1.getInputStream()));
			str2 = ps2.readLine();
			str2=str2.trim();
			System.out.println("SenderDomain  :"+str2);

			str21 = ps2.readLine();
			str21=str21.trim();
			System.out.println("ReceiverDomain:"+str21);


			str31 = ps2.readLine();
			str31=str31.trim();
			System.out.println("SenderName    :"+str31);

			str41 = ps2.readLine();
			str41=str41.trim();
			System.out.println("ReceiverName  :"+str41);

			str51 = ps2.readLine();
			str51=str51.trim();
			System.out.println("EncryptedData :"+str51);
			try
			{
				int len=str51.length();
				System.out.println("Length:"+len);
				int len1=len/8;
				System.out.println("Length1:"+len1);
				int len2=len%8;
				int k=8;
				System.out.println("Length2:"+len2);
				int c=0;
				for(int l=0;l<len;l+=8)
				{
					System.out.println("Source:"+str31);
					if(k<=len-len2)
					{
						String subs = str51.substring(l,k);
						subs1 = subs + "\n";
						sub+=subs1;
						System.out.println("Stirnfg1:"+subs1);
						c++;
						k+=8;
						System.out.println("inside "+k); 
					}
					System.out.println("Destination:"+str41);
				}

				if(len2>0)
				{
					System.out.println("Source:"+str31);
					System.out.println("inside ");
					int g =(len- c*8);
					System.out.println("Value:"+g);
					subs1 = str51.substring(c*8,len);
					sub+=subs1;
					System.out.println("String2:"+subs1);
					System.out.println("Destination:"+str41);
				}
				ta.setText("Source :"+str31+"\n"+sub+"\n"+"Destination :"+str41);

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}


			str71 = ps2.readLine();
			String sstr = str71;
			str71=str71.trim();
			System.out.println("Public Key    :"+str71);

			str72 = sstr;
			str72=str72.trim();
			System.out.println("Signature     :"+str72);


			System.out.println("Server Checking the List of Domains");
			System.out.println("Now Checking wih Domain1");
			Dom1(str2);
			System.out.println("Now Checking wih Domain2");
			Dom2(str21);
			s1.close();

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public static void Soc1()
	{
		try
		{
			System.out.println("I am inside try");
			
			/*if(output.equals("Service Protected"))
			{
				JOptionPane.showMessageDialog(null,"Connection Terminated","Connection",JOptionPane.ERROR_MESSAGE);
				s2.close();
			}
			else if(output1.equals("Service Protected"))
			{
				JOptionPane.showMessageDialog(null,"Connection Terminated","Connection",JOptionPane.ERROR_MESSAGE);
				s2.close();
			}
			else*/
			{
				String inputValue = JOptionPane.showInputDialog("Enter the System Name for Domain2");
				Socket s2 = new Socket(InetAddress.getByName(inputValue),7878);
				System.out.println("Socket Created");
				PrintStream ps = new PrintStream(s2.getOutputStream());
				///
				System.out.println("str2 " + str2);
				System.out.println("str21 " + str21);
				System.out.println("str31 " + str31);
				System.out.println("str41 " + str41);
				System.out.println("str51 " + str51);
				System.out.println("str71 " + str71);
				System.out.println("str72 " + str72);
				ps.println(str2);
				ps.println(str21);
				ps.println(str31);
				ps.println(str41);
				ps.println(str51);
				ps.println(str71);
				ps.println(str72);
				ps.flush();
				System.out.println("Data Has Been written");
				s2.close();
			}

		}
		catch(Exception e)
		{
			System.out.println("i am printing " + e);
		}
	}

	public static void Dom1(String temp)throws Exception

	{
		int Result;
		String Response;
		String Rname;
		Result=0;
		Rname=temp;
		FileReader fr = new FileReader("c:\\file1.txt");
		BufferedReader br = new BufferedReader(fr);
		System.out.println("File has readed sucessfully");
		String s="";
		String str[];


		while((s=br.readLine())!=null)
		{

			str=s.split(" ");


			if(Rname.equalsIgnoreCase(str[0]))
			{

				Result=1;
				output=str[1];
				System.out.println("SenderDomainIP : "+output);
				break;

			}

			else
			{
				output="Service Protected";
				System.out.println("SenderDomainIP : "+output);
			}
		}

		if(output.equals("Service Protected"))
		{
			JOptionPane.showMessageDialog(null,"Server Protected","Service",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Server Provided","Service",JOptionPane.INFORMATION_MESSAGE);
			t1.setText(str31);
			t5.setText(str2);

		}
	}

	public static void Dom2(String temp)throws Exception

		{
			int Result;
			String Response;
			String Rname;
			Result=0;
			Rname=temp;
			FileReader fr = new FileReader("c:\\file1.txt");
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
					System.out.println("ReceiverDomainIP : "+output1);
					break;

				}

				else
				{

					output1="Service Protected";
					System.out.println("ReceiverDomainIP : "+output1);

				}
		}
		if(output1.equals("Service Protected"))
		{
			JOptionPane.showMessageDialog(null,"Server Protected","Service",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Server Provided","Service",JOptionPane.INFORMATION_MESSAGE);
			t6.setText(str21);
			t2.setText(str41);
			t3.setText(str71);
			t4.setText(str72);

		}
	}


}

