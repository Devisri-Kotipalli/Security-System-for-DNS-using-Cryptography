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

public class SwingMes extends JFrame implements ActionListener
{
	JTabbedPane tp ;
	JButton send,browse,send1;
	JTextField tf1;
	JTextArea ta,ta1;
	JScrollPane sp,sp1;
	String g1=" ";
	byte str[];
	byte realSig[];
	
	String y="";
	String st="";
	Client ct;

	public SwingMes(Client ct)

	{

		super("Transaction");
		this.ct=ct;
		setSize(500,500);
		Container c = getContentPane();
		JPanel jp = new JPanel(new FlowLayout());
		JLabel jl = new JLabel("Enter the Text: ");
		ta = new JTextArea(20,40);
		sp = new JScrollPane(ta);
		send= new JButton("Send");
		jp.add(jl);
		jp.add(sp);
		jp.add(send);
		jp.setVisible(true);
		JPanel jp1 = new JPanel(new FlowLayout());
		JLabel jl1 = new JLabel("Enter the File Name ",JLabel.LEFT);
		tf1 = new JTextField(15);
		ta1 = new JTextArea(20,40);
		sp1 = new JScrollPane(ta1);
		browse = new JButton("Browse");
		send1= new JButton("Send");
		send.addActionListener(this);
		browse.addActionListener(this);
		send1.addActionListener(this);
		jp1.add(jl1);
		jp1.add(tf1);
		jp1.add(browse);
		jp1.add(sp1);
		jp1.add(send1);
		jp1.setVisible(true);
		tp = new JTabbedPane();
		tp.addTab("Message",null,jp,"Enter the Message here");
		tp.addTab("File",null,jp1,"Enter the File Name here");
		c.add(tp);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}


	/*         MESSAGE DIGEST ALOGORITHM          */


	private byte[] content;

	public byte[] digestValue(byte[] in_text1)

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


	public void keys()

	{

		try

		{

			KeyPairGenerator keygen = KeyPairGenerator.getInstance("DSA","SUN");

		 /* cryptographically strong pseudo-random number generator (PRNG) */

			SecureRandom random = SecureRandom.getInstance("SHA1PRNG","SUN");
			keygen.initialize(1024,random);
			KeyPair keypair = keygen.generateKeyPair();
			PrivateKey privatekey = keypair.getPrivate();
			PublicKey publickey = keypair.getPublic();
			FileOutputStream pubkeyfos = new FileOutputStream("public.txt");
			pubkeyfos.write(publickey.getEncoded());
			System.out.println("PublicKey"+publickey.getEncoded());
			pubkeyfos.close();
			FileOutputStream prikeyfos = new FileOutputStream("private.txt");
			prikeyfos.write(privatekey.getEncoded());
			System.out.println("PrivateKey:"+privatekey);
			prikeyfos.close();
			JOptionPane.showMessageDialog(null, "keys are created","Keys", JOptionPane.INFORMATION_MESSAGE);

		}

		catch(Exception e)

		{
			System.out.println(e);
		}
	}


	public void met2(String dis2)

	{

		System.out.println("FileName:"+dis2);
		try

		{
			int i;
			String g=" ";
			char x[]=new char[50];
			char d,s;
			String s1;

			InputStream in = new FileInputStream(dis2);
			str=new byte[in.available()];
			in.read(str, 0, str.length);
			System.out.println(new String (str));
			ta1.setText(new String (str));
			int len=str.length;
			System.out.println("StringLength:"+len);
			int b[]=new int[len];
			int a[]=new int[len];
			System.out.print("HASH-CODE:");

			for(i=0;i<len;i++)

			{

				int aa= str[i];
				System.out.print("\t"+ aa);
				a[i]=aa;

			}

			System.out.print("\n");
			System.out.print("HEX-CODE:");

			for(i=0;i<len;i++)

			{

				g=" ";
				b[i]=a[i]%16;
				a[i]=a[i]/16;

				if(a[i]==10)

				{
					s='A';
					g=g+s;
				}

				else if(a[i]==11)

				{
					s='B';
					g=g+s;
				}

				else if(a[i]==12)

				{
					s='C';
					g=g+s;
				}

				else if(a[i]==13)

				{
					s='D';
					g=g+s;
				}

				else if(a[i]==14)

				{
					s='E';
					g=g+s;
				}

				else if(a[i]==15)

				{
					s='F';
					g=g+s;
				}

				else

				{
					s=(char) a[i];
					g=g+a[i];
				}


				if(b[i]==10)

				{
					s='A';
					g=g+s;
					System.out.print(g.trim());

				}

				else if(b[i]==11)

				{
					s='B';
					g=g+s;
					System.out.print(g.trim());
				}

				else if(b[i]==12)

				{
					s='C';
					g=g+s;
					System.out.print(g.trim());
				}

				else if(b[i]==13)

				{
					s='D';
					g=g+s;
					System.out.print(g.trim());
				}

				else if(b[i]==14)

				{
					s='E';
					g=g+s;
					System.out.print(g.trim());
				}

				else if(b[i]==15)

				{
					s='F';
					g=g+s;
					System.out.print(g.trim());
				}

				else

				{
			        s=(char) b[i];
					g=g+b[i];
					System.out.print(g.trim());
				}

				g1=g1+g.trim();

			}

			str=g1.getBytes();
			digestValue(g1.getBytes());
			

		}

		catch(IOException e)

		{
			System.out.println(e);
		}

		keys();
		genSig("public.txt");
		String inputValue = JOptionPane.showInputDialog("Enter the System Name for Domain1");

		try

		{
			Socket s1 = new Socket(InetAddress.getByName(inputValue),9999);
			PrintStream ps = new PrintStream(s1.getOutputStream());
			ps.println(ct.SenderName.getText());
			System.out.println("SenderName    :"+ct.SenderName.getText());
			ps.println(ct.SenderPassword.getText());
			ps.println(ct.ReceiverName.getText());
			System.out.println("ReceiverName  :"+ct.ReceiverName.getText());
			System.out.println("Encrypted Data:"+ new String(str));
			ps.println(new String(str));
			ps.println("public.txt");
			ps.println("realSign.txt");
			ps.flush();
			s1.close();

		}

		catch(Exception ex)
		{
			System.out.println(ex);
		}

	}

	public void genSig(String fname)

	{

		try

		{
			byte[] md;
			String args=fname;
			FileInputStream fin=new FileInputStream(args);
			byte[] in_text=new byte[fin.available()];
			fin.read(in_text);
			fin.close();
			//SHA sha=new SHA(in_text);
			md=digestValue(in_text);

			FileInputStream fins=new FileInputStream("private.txt");
			byte[] enc_priv=new byte[fins.available()];
			fins.read(enc_priv);
			fins.close();
			Signature dsa=Signature.getInstance("SHA1withDSA","SUN");

			PKCS8EncodedKeySpec privKeySpec=new PKCS8EncodedKeySpec(enc_priv);
			KeyFactory keyFactory=KeyFactory.getInstance("DSA","SUN");
			PrivateKey priv=(PrivateKey)keyFactory.generatePrivate(privKeySpec);

			dsa.initSign(priv);
			dsa.update(md);
			realSig=dsa.sign();
			System.out.println("Generated Signature :"+realSig);

			FileOutputStream sigfos = new FileOutputStream("realSign.txt");
			sigfos.write(realSig);
			sigfos.close();
			JOptionPane.showMessageDialog(null, "Signature are Generated","Signature", JOptionPane.INFORMATION_MESSAGE);
			//Thread.wait(8000);
		}

		catch(Exception e)

		{
			System.out.println(e);
		}

	}


	public void actionPerformed(ActionEvent e)

	{
		if(e.getSource() == browse)
		{
			JFileChooser fc = new JFileChooser();
			int option = fc.showOpenDialog(SwingMes.this);
			if(option == JFileChooser.APPROVE_OPTION)
			{
				tf1.setText(fc.getSelectedFile().getAbsolutePath());

			}
		}

		else if(e.getSource() == send1)
		{
			String y= tf1.getText();
			met2(y);
		}

		else if(e.getSource() == send)
		{
			if (ta.getText() == null)

				JOptionPane.showMessageDialog(null,"Enter the Message","Invalid Data",JOptionPane.ERROR_MESSAGE);

			else

			{
				int i;
				int b[]=new int[5000];
				int a[]=new int[5000];
				String g=" ";
				char x[]=new char[50];
				char d,s;

				String h=ta.getText();
				System.out.println("Message Entered:"+h);
				int len=h.length();
				System.out.println("StringLength:"+len); 
				System.out.print("HASH-CODE:");

				for(i=0;i<len;i++)

				{

					int aa= h.charAt(i);
					System.out.print("\t"+ aa);
					a[i]=aa;
				}

				System.out.print("\n");
				System.out.print("HEX-CODE:");

				for(i=0;i<len;i++)

				{
					g=" ";
					b[i]=a[i]%16;
					a[i]=a[i]/16;

					if(a[i]==10)

					{
						s='A';
						g=g+s;

					}

					else if(a[i]==11)

					{
						s='B';
						g=g+s;

					}

					else if(a[i]==12)

					{
						s='C';
						g=g+s;

					}

					else if(a[i]==13)

					{
						s='D';
						g=g+s;

					}

					else if(a[i]==14)

					{
						s='E';
						g=g+s;

					}

					else if(a[i]==15)

					{
						s='F';
						g=g+s;

					}

					else

					{
						s=(char) a[i];
						g=g+a[i];

					}

					if(b[i]==10)

					{
						s='A';
						g=g+s;
						System.out.print(g.trim());

					}

					else if(b[i]==11)

					{
						s='B';
						g=g+s;
						System.out.print(g.trim());
					}

					else if(b[i]==12)

					{
						s='C';
						g=g+s;
						System.out.print(g.trim());
					}

					else if(b[i]==13)

					{
						s='D';
						g=g+s;
						System.out.print(g.trim());
					}

					else if(b[i]==14)

					{
						s='E';
						g=g+s;
						System.out.print(g.trim());
					}

					else if(b[i]==15)

					{
						s='F';
						g=g+s;
						System.out.print(g.trim());						
					}

					else

					{
					 	s=(char) b[i];
						g=g+b[i];
						System.out.print(g.trim());
					}

					g1=g1+g.trim();


				}
				System.out.println("Encoded text " + g1);
				str=g1.getBytes();
				System.out.println("This may be a File   : " +  str);
				st=new String(str);
				digestValue(g1.getBytes());
				keys();
				genSig("public.txt");
				String inputValue = JOptionPane.showInputDialog("Enter the System Name for Domain1");

				try
				{

					Socket s1 = new Socket(InetAddress.getByName(inputValue),9999);
					PrintStream ps = new PrintStream(s1.getOutputStream());
					ps.println(ct.SenderName.getText());
					System.out.println("SenderName  :"+ct.SenderName.getText());
					ps.println(ct.SenderPassword.getText());
					ps.println(ct.ReceiverName.getText());
					System.out.println("ReceiverName:"+ct.ReceiverName.getText());
					System.out.println("File:"+ new String(str));
					ps.println(new String(str));
					ps.println("public.txt");
					ps.println("realSign.txt");
					ps.flush();
					BufferedReader br=new BufferedReader(new InputStreamReader(s1.getInputStream()));
					String tr = br.readLine();
					/*if(tr .equals ("correct"))
					{
						JOptionPane.showMessageDialog(null,"Valid Password","Password",JOptionPane.ERROR_MESSAGE);
					}

					else
					{
						JOptionPane.showMessageDialog(null,"InValid Password","Password",JOptionPane.ERROR_MESSAGE);

					}*/
					s1.close();
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}

			}
		}

	}
}