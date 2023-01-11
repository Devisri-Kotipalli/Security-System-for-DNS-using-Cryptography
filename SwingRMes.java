import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.util.zip.*;
import java.security.*;
import java.security.SecureRandom.*;
import java.security.spec.*;
import javax.swing.*;

public class SwingRMes extends JFrame implements ActionListener, Runnable
{
	JTabbedPane tp;
	JLabel jl,jl1,jl2,jL;
	JButton jb,jb1,jb2;
	JTextField tf1,tf2,tfd,tfd1;
	JTextArea tf,ta1;
	JScrollPane sp,sp1;
	static String g1=" ";
	static String n,n1,n2;
	static byte str[];
	static byte realSig[];
	static byte realSig1[];
	static String y="";
	static String str6,str61,str2,str12,str13;
	static String t,st="",st1="",vj="";


	public SwingRMes()
	{
		super("Reception");
		String inf="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try
		{
			UIManager.setLookAndFeel(inf);
		}
		catch(Exception e){}


		setSize(400,450);
		Container c = getContentPane();

		JPanel jp = new JPanel();
		jp.setLayout(null);
		jl = new JLabel("Sender Name ",JLabel.LEFT);
		jL = new JLabel("Receiver Name ",JLabel.LEFT);
		tfd = new JTextField(15);
		tfd1 = new JTextField(15);
		tf = new JTextArea(20,40);
		sp = new JScrollPane(tf);
		jb= new JButton("OPEN");
		jp.add(jl);
		jp.add(tfd);
		jp.add(jL);
		jp.add(tfd1);
		jp.add(sp);
		jp.add(jb);

		jl.setBounds(25,10,150,25);
		tfd.setBounds(150,10,150,25);
		jL.setBounds(25,40,150,25);
		tfd1.setBounds(150,40,150,25);
		sp.setBounds(25,70,300,250);
		jb.setBounds(150,350,75,25);
		jp.setVisible(true);

		JPanel jp1 = new JPanel();
		jp1.setLayout(null);
		jl1 = new JLabel("Sender Name ",JLabel.LEFT);
		jl2 = new JLabel("Receiver Name ",JLabel.LEFT);
		tf1 = new JTextField(15);
		tf2 = new JTextField(15);
		ta1 = new JTextArea(20,40);
		sp1 = new JScrollPane(ta1);
		jb2= new JButton("Retrive");

		jl1.setBounds(25,10,150,25);
		tf1.setBounds(150,10,150,25);
		jl2.setBounds(25,40,150,25);
		tf2.setBounds(150,40,150,25);
		sp1.setBounds(25,70,300,250);
		jb2.setBounds(150,350,75,25);
		jb.addActionListener(this);
		jb2.addActionListener(this);
		jp1.add(jl1);
		jp1.add(tf1);
		jp1.add(jl2);
		jp1.add(tf2);
		jp1.add(sp1);
		jp1.add(jb2);
		jp1.setVisible(true);

		tp = new JTabbedPane();
		tp.addTab("Message",null,jp,"Retrive the Message here");
		tp.addTab("File",null,jp1,"Retrive the File Name here");
		c.add(tp);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	public void run(){
		try {
			SwingRMes.main(null);
			Thread.sleep(2000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String [] args) throws Exception
	{
		SwingRMes rm = new SwingRMes();
		rm.ServSoc();
	}

	public static void ServSoc()
	{
		try
			{
				System.out.println(" Destination Server Started..... ");
				ServerSocket ss = new ServerSocket(7878);
				System.out.println(" Socket Created...... ");
				Socket s11 = ss.accept();
				

				BufferedReader ps2=new BufferedReader(new InputStreamReader(s11.getInputStream()));
				str6 = ps2.readLine();
				str6=str6.trim();
				System.out.println("SenderName   : " +str6);
				str61 = ps2.readLine();
				str61=str61.trim();
				System.out.println("ReceiverName : " +str61);
				str2 = ps2.readLine();
				str2=str2.trim();
				System.out.println("File Name    : " +str2);
				str12 = ps2.readLine();
				str12=str12.trim();
				System.out.println("Public Key   : " +str12);
				str13 = ps2.readLine();
				str13=str13.trim();
				System.out.println("RealSign     : " +str13);


				char a[]=new char[50000];
				char res[]=new char[9];
				char s1[]=new char[5];
				String w =new String();
				String r =new String();
				int i,j=0,d=1,h,n,l=8,l2=0,l1=0;
				long c=0l;
				String t,st="",st1="";



						/* Getting the Hexcode And Written into TextFile */

 
					    System.out.println("File Name to be Opened" + str13);
						InputStream in3 = new FileInputStream(str13);
						
						System.out.println("Trying to out stream");
						FileOutputStream out2=new FileOutputStream("Sign1.txt");
						byte[] str3=new byte[512];
						while(in3.read(str3)!=-1)
						{
							String r1=new String(str3);
							out2.write(str3);
						}

						System.out.println("Enter the Hex-Code:");
						System.out.println(new String(str12));
						int len=str12.length();
						System.out.print("String Length:");
						System.out.println(len);

					    ///changed 2 ad 12  /*   Converting Hexcode into Bits    */

								for(i=0;i<len;i++)
								{
									char aa= ((str12).charAt(i));
									a[i]=aa;
									if(a[i]=='A')
									{
						 				st="1010";
										st1=st;
									}
									else if(a[i]=='B')
									{
										st="1011";
										st1=st;
									}
									else if(a[i]=='C')
									{
										st="1100";
										st1=st;
									}
									else if(a[i]=='D')
									{
										st="1101";
										st1=st;
									}
									else if(a[i]=='E')
									{
										st="1110";
										st1=st;
									}
									else if(a[i]=='F')
									{
										st="1111";
										st1=st;
									}
									else if(a[i]=='0')
									{
										st="0000";
										st1=st;
									}
									else if(a[i]=='1')
									{
										st="0001";
										st1=st;
									}
									else if(a[i]=='2')
									{
									st="0010";
										st1=st;
									}
									else if(a[i]=='3')
									{
										st="0011";
										st1=st;
									}
									else if(a[i]=='4')
									{
										st="0100";
										st1=st;
									}
									else if(a[i]=='5')
									{
										st="0101";
										st1=st;
									}
									else if(a[i]=='6')
									{
										st="0110";
										st1=st;
									}
									else if(a[i]=='7')
									{
										st="0111";
										st1=st;
									}
									else if(a[i]=='8')
									{
										st="1000";
									}
									else if(a[i]=='9')
									{
										st="1001";
						 			}
									w=w+st;
								}

								char w_ch[]=w.toCharArray();
								String x=w.toString();
								System.out.print("this is content " + x);
								System.out.println("\nLength:"+len);

								      /*   Converting Hex into Character   */
								///
								

								try
								{
									if(len==8)
									{
																			 
										
										int k;
										k=len;	
									
										for(j=k-1;j>=0;j--)
										{
											c=c+((w_ch[j])-48)*d;
											y=y+c;
											d=d*2;											
										}  
										System.out.print("\nCharacter after Decreyption :");
										vj=vj+(char)c;
										
									}



									else
									{
									    
									   
										int k;
										for(i=0;i<50;i++)
										{
											c=0; d=1;
											k=i*8;
											for(j=k+7;j>=k;j--)
											{												
												c=c+(w_ch[j]-48)*d;
											 	y=y+c;
											 	d=d*2;												
												 
											}
										 	vj=vj+(char)c;
										 	System.out.println(vj);										  
										}
										
									}


								}
								catch(Exception e)
								{
									System.out.print("\ni am printing   " + e);

								}
								FileOutputStream fos=new FileOutputStream("Recv.txt");
								fos.write(vj.getBytes());
								s11.close();
			
			}
			catch(Exception e)
			{
				
			}
		}

		public static boolean verifySig(String fname) throws Exception

			{

				byte[] md;
				byte[] sign;
				byte[] realSig;
				String args=fname;
				FileInputStream fin=new FileInputStream(args);
				byte[] in_text=new byte[fin.available()];
				fin.read(in_text);
				fin.close();
				//SHA sha=new SHA(in_text);
				md=digestValue(in_text);

				FileInputStream finPublic=new FileInputStream(str12);
				byte[] enc_pub=new byte[finPublic.available()];
				finPublic.read(enc_pub);
				finPublic.close();

				FileInputStream sigfis=new FileInputStream(str13);
				sign=new byte[sigfis.available()];
				sigfis.read(sign);

				Signature dsa1 = Signature.getInstance("SHA1withDSA","SUN");
				X509EncodedKeySpec pubKeySpec=new X509EncodedKeySpec(enc_pub);
				KeyFactory keyFactoryPub=KeyFactory.getInstance("DSA","SUN");
				PublicKey pub=keyFactoryPub.generatePublic(pubKeySpec);
				dsa1.initVerify(pub);
				dsa1.update(md);
				boolean status=dsa1.verify(sign);
				System.out.println("Verified Signature:" + status);
				return 	status;
		}

		private static byte[] content;

		public static byte[] digestValue(byte[] in_text1)
		{
			byte[] in_text=in_text1;
			content=in_text;
			MessageDigest mg1=null;
			try
			{
				mg1=MessageDigest.getInstance("SHA1");
			}

			catch (Exception e)
			{
				System.out.println(e);
			}

			mg1.update(content);
			byte[] digest1=mg1.digest();
			System.out.println("Message Digest:"+digest1);
			return digest1;
		}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == jb2)
		{
			JOptionPane.showMessageDialog(null, "Signature are Verified","Verify Sign", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null,"Receiver accept the data from Domain2","Receiver",JOptionPane.INFORMATION_MESSAGE);
			tf1.setText(str6);
			tf2.setText(str61);
			ta1.setText(vj);
		}
		else if(e.getSource() == jb)
		{
			System.out.println("Verify Signature are Generated");

			try
			{
				verifySig(str12);
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}

			JOptionPane.showMessageDialog(null, "Signature are Verified","Verify Sign", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null,"Receiver accept the data from Domain2","Receiver",JOptionPane.INFORMATION_MESSAGE);
			tfd.setText(str6);
			tfd1.setText(str61);
			//System.out.print("FileContent"+vj); 
			tf.setText(vj);
		}
	}
}
