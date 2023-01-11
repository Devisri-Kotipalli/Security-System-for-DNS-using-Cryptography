import java.util.*;
import javax.swing.*;
import java.io.*;
class Timers implements Runnable

{
	Thread t;
	int time=0;

	static FileOutputStream fo;
	static ObjectOutputStream oos;
	FileInputStream fi;
	ObjectInputStream iis;
	Timers()
	{
		try{
				fo=new FileOutputStream("bool.txt");
				oos=new ObjectOutputStream(fo);
				oos.writeObject("false");

					}
			catch(Exception e){}
		t=new Thread(this);
		t.start();



	}
	public void run()
	{

				while(time<10)
				{
					try{
					Thread.sleep(1000);
						}
						catch(Exception e)
						{	e.printStackTrace(); }
					time++;
					System.out.println("..."+time);


				}
				String bool="";
				try{
				fi=new FileInputStream("bool.txt");
				iis=new ObjectInputStream(fi);
				bool=(String)iis.readObject();
					}
					catch(Exception e){}
				if(bool.equals("false"))
				{
					try{
						System.out.println("Run");
					File f1=new File("nextnodes.txt");

					//System.out.println("Run");
					File f2=new File("nextnodes1.txt");
					//System.out.println("Run");
					File f3=new File("nextnodes2.txt");
					//System.out.println("Run");
					File f4=new File("nextnodes.txt");
					File f5=new File("nextnodes1.txt");
					System.out.println("Run");
					Thread.sleep(1000);
					f1.renameTo(f3);

					System.out.println("Run");
					f2.renameTo(f4);

					System.out.println("Run");
					f3.renameTo(f5);

					System.out.println("Run");
						}
						catch(Exception e){ System.out.println(e); }
					JOptionPane.showMessageDialog(null,"The destination may be switched OFF","Time Out",JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(null,"Restart the Server","Restart",JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}


	}
	public static void main(String a[]) throws Exception
	{
		System.out.println("..........");
		new Timers();
	}

	public static void rece()
	{
		try{
		fo=new FileOutputStream("bool.txt");
		oos=new ObjectOutputStream(fo);
		oos.writeObject("true");

			}
			catch(Exception e){}

		JOptionPane.showMessageDialog(null,"Ack Received","Acknowledgement",JOptionPane.INFORMATION_MESSAGE);

	}

}