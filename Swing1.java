import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 

public class Swing1 extends Thread implements ActionListener
{
	JFrame f;
	JButton Submit,Reset;
	JTextField t1;
	JPasswordField t2;

	Swing1()
	{
			String inf="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			try
			{
				UIManager.setLookAndFeel(inf);
			}
			catch(Exception e){}
	}

	public void Met()
	{

		JLabel l1 = new JLabel("UserName :",JLabel.RIGHT);
		JLabel l2 = new JLabel("PassWord :",JLabel.RIGHT);
		t1 = new JTextField(10);
		t2 = new JPasswordField(10);
		l1.setLabelFor(t1);
		l2.setLabelFor(t2);
		Submit = new JButton("Submit");
		Reset = new JButton("Reset");

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,2,5,5));
		p.add(l1);
		p.add(t1);
		p.add(l2);
		p.add(t2);
		p.add(Submit);
		p.add(Reset);
		Submit.addActionListener(this);
		Reset.addActionListener(this);
		f = new JFrame("Login");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(p);
		f.setBounds(250,250,180,120);
		//f.pack();
		f.setResizable(false);
		f.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{

			if(Submit==(JButton) e.getSource())
			{
				if(((t1.getText()).equals("admin")) && ((t2.getText()).equals ("admin")))
				{
					Client c1 = new Client();
		 			c1.setVisible(true);
		 			f.setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invaild UserName and PassWord","Alert", JOptionPane.ERROR_MESSAGE);
					t1.setText("");
					t2.setText("");
					f.setVisible(true);
				}
		  	}
			else if(Reset == (JButton)e.getSource())
			{
				t1.setText("");
				t2.setText("");

			}
	}
	public void run(){
		try {
			Swing1.main(null);
			Thread.sleep(2000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args) throws Exception
	{
		Swing1 s1 = new Swing1();
		s1.Met();
	}
}