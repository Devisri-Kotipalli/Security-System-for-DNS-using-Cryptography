import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;

public class RSAKeyDsgn extends JFrame implements ActionListener
{
	private JLabel pval,qval,rsa;
	private JTextField pfield,qfield;
	private JButton ok,cancel;
	String pstr,qstr;
	JFrame frsa;

	 RSAKeyDsgn()
	 {

		 frsa = new JFrame("RSA KeyGeneration");
		 JPanel jp=new JPanel();
		 jp.setLayout(null);
		 rsa = new JLabel("RSA KEYGENERATION");
		 pval = new JLabel("First Prime Number (P)");
		 qval = new JLabel("Second Prime Number (Q)");
		 pfield = new JTextField(20);
		 qfield = new JTextField(20);
		 ok = new JButton("OK");
		 cancel = new JButton("CANCEL");
		 jp.add(pval);
		 jp.add(qval);
		 jp.add(pfield);
		 jp.add(qfield);
		 jp.add(ok);
		 jp.add(cancel);

		rsa.setBounds(140,20,140,20);
		pval.setBounds(45,55,150,20);
		pfield.setBounds(210,55,150,20);
		qval.setBounds(45,90,150,20);
		qfield.setBounds(210,90,150,20);
		ok.setBounds(110,150,90,20);
		cancel.setBounds(210,150,90,20);
		frsa.setContentPane(jp);

		ok.addActionListener(this);
		cancel.addActionListener(this);

		frsa.setSize(450,250);
		frsa.setVisible(true);
		frsa.addWindowListener( new WindowAdapter()
		{
			 public void windowClosing(WindowEvent e)
			 {
				 frsa.setVisible(false);
			 }
		}
			);
	}

	public void actionPerformed(ActionEvent arg0)
	{
	    if(arg0.getSource()==ok)
	    {
	  	  pstr=pfield.getText();
	  	  qstr=qfield.getText();
	  	  try
	  	  {
			if(Integer.parseInt(pstr) >=100 || Integer.parseInt(qstr) >=100)
			{
				JOptionPane.showMessageDialog(null,"You Have Entered into HIGH LEVEL TRUST ","LEVEL OF TRUST",JOptionPane.INFORMATION_MESSAGE);
				try{
				FileOutputStream fos=new FileOutputStream("trust");
				   ObjectOutputStream oos=new ObjectOutputStream(fos);
   					oos.writeObject("high");}
   					catch(Exception e){}
			}
			else if(Integer.parseInt(pstr) >=25 || Integer.parseInt(qstr) >=25)
			{
				JOptionPane.showMessageDialog(null,"You Have Entered into MEDIUM LEVEL TRUST ","LEVEL OF TRUST",JOptionPane.INFORMATION_MESSAGE);
				try{
				FileOutputStream fos=new FileOutputStream("trust");
				  ObjectOutputStream oos=new ObjectOutputStream(fos);
   				oos.writeObject("medium");}
   				catch(Exception e){}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"You Have Entered into LOW LEVEL TRUST ","LEVEL OF TRUST",JOptionPane.INFORMATION_MESSAGE);
				try{
				FileOutputStream fos=new FileOutputStream("trust");
				   ObjectOutputStream oos=new ObjectOutputStream(fos);
   					oos.writeObject("low");}
   					catch(Exception e){}
			}
	  	  	FileInputStream fis=new FileInputStream("msg.txt");
	  	  	byte str[]=new byte[fis.available()];
	  	  	fis.read(str,0,str.length);
	  	  	String str1=new String(str);
		    System.out.println(str1);
		  	keyrsa1 k1=new keyrsa1(str1);
		  	k1.Key(pstr,qstr);
		  	frsa.setVisible(false);
		  	}
		  catch(Exception ew){}
	  	}
    }
}