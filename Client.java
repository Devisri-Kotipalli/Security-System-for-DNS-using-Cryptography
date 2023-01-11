import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import javax.swing.*; 

class Client extends JFrame implements ActionListener
{
	Button Send,Clear,Exit;
	TextField  SystemName,SystemIp,ReceiverName,ReceiverIp,SenderName,SenderIp;
	TextField SenderPassword;
	Label n1,n2,n3,n4,n5;
	Byte str[];

	Client()
	{
		super("Sender");
		Container c = getContentPane();
		setSize(400,300);
		setResizable(false);
		JPanel jp = new JPanel();
		jp.setLayout(null);
		SenderName= new TextField(20);
		SenderPassword = new TextField(20);
		SenderPassword.setEchoChar('*');
		ReceiverName=new TextField(20);
		n3= new Label("SENDERNAME    :",Label.LEFT);
		n4= new Label("SENDERPASSWORD:",Label.LEFT);
		n5= new Label("RECEIVERNAME  :",Label.LEFT);
		//n6= new Label("RECEIVER-IP    :",Label.LEFT
		Send=new Button("Send");
		Clear=new Button("Clear");
		Exit=new Button("Exit");
		jp.add(n3);
		jp.add(SenderName);
		jp.add(n4);
		jp.add(SenderPassword);
		jp.add(n5);
		jp.add(ReceiverName);
		jp.add(Send);
		jp.add(Clear);
		jp.add(Exit);
		c.add(jp);
		n3.setBounds(40,25,120,25);
		SenderName.setBounds(200,25,120,25);
		n4.setBounds(40,70,140,25);
		SenderPassword.setBounds(200,70,120,25);
		n5.setBounds(40,115,120,25);
		ReceiverName.setBounds(200,115,120,25);
		Send.setBounds(80,200,50,25);
		Clear.setBounds(140,200,50,25);
		Exit.setBounds(200,200,50,25);
		Send.addActionListener(this);
		Clear.addActionListener(this);
		Exit.addActionListener(this);

	}


	public void actionPerformed(ActionEvent ae)

	{
		String st;
		st=ae.getActionCommand();

		if(st.equals("Send"))
		{

			try
			{
				SwingMes sm = new SwingMes(this);
		 		sm.setVisible(true);
				setVisible(false);

			}

			catch(Exception e)
			{
				//System.out.println(e);
			}

		}

		else if(st.equals("Clear"))
		{
			SenderName.setText("");
			SenderPassword.setText("");
			ReceiverName.setText("");

		}

		else if(st.equals("Exit"))
		{
			System.exit(0);
		}

	}

}

