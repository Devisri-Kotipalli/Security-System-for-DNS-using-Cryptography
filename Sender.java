import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

class Sender implements ActionListener
{

  	JFrame jf;
  	static JTextArea jta=new JTextArea();
  	JButton jb,browse;
  	JTextField jt1,jt2,jt3;
  	JLabel jl1,jl2,jl3,jl4;
  	JScrollPane jsp;
  	Container c;
  	static String destination="";
  	static String ttt;
  	static String tte;
  	static Vector vvv=new Vector();
    static int i=0;

  	Sender()
  	{

  		 jf=new JFrame("Sender");
  		 jb=new JButton("Send");
  		 jt1=new JTextField();
  		 jt2=new JTextField();
  		 jt3=new JTextField();
  		 jl1=new JLabel("Destination");
  		 browse=new JButton("Browse");
  		 jl2=new JLabel("Request Zone Limit");
  		 jsp=new JScrollPane(jta,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

  		 jf.setSize(800,800);
  		 c=jf.getContentPane();
  		 c.setLayout(null);
  		 c.add(jsp);
  		 c.add(jb);
  		 c.add(browse);
  		 c.add(jt1);
   		 c.add(jl1);
  		 c.add(jl2);
  		 jsp.setBounds(100,100,400,400);
  		 jb.setBounds(550,350,100,25);
  		 jt1.setBounds(550,150,100,25);
  		 browse.setBounds(550,250,100,25);
  	     jl1.setBounds(550,100,100,25);
   		 jb.addActionListener(this);
   		 browse.addActionListener(this);
   		 jf.setVisible(true);

  }


  public void actionPerformed(ActionEvent ae)

  {

	  destination=jt1.getText();
	  ttt=jta.getText();

	  try
	  {
	  		FileOutputStream fop=new FileOutputStream("msg.txt");
	  		fop.write(ttt.getBytes());
	  		ObjectOutputStream oop=new ObjectOutputStream(fop);

	  }
	  catch(Exception e){}
         // vvv.add(ttt);
	  System.out.println("The vect size is "+vvv.size());
	  if(jb==ae.getSource())
	  {
	  	try
	  	{
			System.out.println("The String sent is :"+ttt);
			RSAKey rsa=new RSAKey();
	  	}
	  	catch(Exception e)
	  	{
			  System.out.println("Error  : "+e);
	  	}
	  }
	  if((JButton)browse==ae.getSource())
      {
  			JFileChooser fc = new JFileChooser();
  			int option = fc.showOpenDialog(jf);
  			if(option == JFileChooser.APPROVE_OPTION)
  			{
  				try
  				{
  					String sf=fc.getSelectedFile().getAbsolutePath();
  					FileInputStream in = new FileInputStream(sf);
  					byte str[] = new byte[in.available()];
  					in.read(str,0,str.length);
  					jta.setText(new String (str));
  				}
  				catch(Exception e){}
			}
		}
 }





   public static void Socket2(int y,String tt)throws Exception
   {
   	  FileInputStream fis=new FileInputStream("path"+y+".txt");
   	  ObjectInputStream ois=new ObjectInputStream(fis);
   	  Vector d=(Vector)ois.readObject();
   	  int siz=d.size();
   	  System.out.println("The size of the vector is ::"+siz);
   	  for(int j=0;j<siz;j++)
   	  {
   		  System.out.print("++__++-->>"+d.get(j));

   	  }
   	  if(siz==2)
   	  {

   		  Socket soc=new Socket((String)d.get(1),8888);
   		  ObjectOutputStream oos=new ObjectOutputStream(soc.getOutputStream());
   		  oos.writeObject("file");
   		  oos.writeObject(tt);
   	  }
   	  else
   	  {
   		  d.removeElementAt(0);
   		  int m=d.size();
   		  System.out.println("The size after removing the element..."+m);
   		  Socket soc=new Socket((String)d.get(1),8888);
   		  ObjectOutputStream oos=new ObjectOutputStream(soc.getOutputStream());
   		  oos.writeObject("file++");
   		  oos.writeObject(tt);
   		  oos.writeObject(d);

   	  }

   }
}






