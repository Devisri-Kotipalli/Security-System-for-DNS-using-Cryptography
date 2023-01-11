import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Password  extends JFrame implements ActionListener
{
	JPasswordField password;
	JTextField Text1;
	JLabel login,pass,hea1;
	JButton ok,cancel;
	final String log = "routing";
	final String passW = "location";
	JFrame fpass;

	Password()
	 {

	 	fpass = new JFrame("Login");


	    fpass.getContentPane().setLayout(null);

		hea1 = new JLabel("LOG IN SCREEN");
		login = new JLabel("USER-ID");
		Text1 = new JTextField(10);
		pass = new JLabel("PASSWORD");
		password = new JPasswordField(10);
		ok = new JButton("OK");
		cancel = new JButton("CANCEL");


		hea1.setBounds(130,10,100,50);
		login.setBounds(70,40,70,50);
		Text1.setBounds(157,55,125,20);
		pass.setBounds(70,70,70,50);
		password.setBounds(158,83,125,20);
		ok.setBounds(80,130,90,20);
		cancel.setBounds(180,130,110,20);


		fpass.getContentPane().add(hea1);
		fpass.getContentPane().add(login);
		fpass.getContentPane().add(Text1);
		fpass.getContentPane().add(pass);
		fpass.getContentPane().add(password);
		fpass.getContentPane().add(ok);
		fpass.getContentPane().add(cancel);
		ok.addActionListener(this);
		cancel.addActionListener(this);

		fpass.setSize(400, 250);
        fpass.setVisible(true);

	 }
	 public void actionPerformed(ActionEvent e)
	 {
		 String s = e.getActionCommand();
		 if(s.equals("OK"))
		 {
			String str1;
			str1=Text1.getText();
			String str2= new String (password.getPassword());
			if(!str1.equals("admin"))
			{
			  JOptionPane.showMessageDialog(null,"Invalid User-ID","Error",1);
			  Text1.setText("");
			  password.setText("");
			}
			if(!str2.equals("admin"))
			{
			  JOptionPane.showMessageDialog(null,"Invalid Password","Error",1);
			  Text1.setText("");
			  password.setText("");
			}
			if(str1.equals("admin") && str2.equals("admin"))
			{
			       fpass.setVisible(false);
          	       Sender sen=new Sender();

			}

		 }
		else if(s.equals("CANCEL"))
		 {
			System.exit(0);
		 }
	 }
	 public static void main(String ar[])
	 {
		Password ps = new Password();
		ps.addWindowListener( new WindowAdapter()
			{
			 public void windowClosing(WindowEvent e)
			 {
			   System.exit(0);
			 }
			}
	                        ); //addWindowListener

	 }//main method
}
