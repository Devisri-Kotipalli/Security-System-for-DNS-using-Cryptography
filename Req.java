import java.net.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class Req
{

	static Socket s1,s2,d;
	static Vector localvect=new Vector(10);
	static Vector localvecttemp=new Vector(10);
	static Vector recvect=new Vector(10);
	static int i=0;
	static String file="";
	static Vector vv=new Vector();
	static String mm;
	static String tte;
	static Sender sen;
	static String jdd;
	static String dd;

	public static void main(String a[]) throws Exception
	{

		 String path="";
		 Enumeration it;
		 String sender="";
		 String localaddr=(InetAddress.getLocalHost()).getHostName();
		 String destination="";
		 Socket s1,s2,d;
		 String sockaddr="";
		 String next="";
		 int vectsize;

	     try
	     {

		  ServerSocket ss=new ServerSocket(8888);
		  new tab();
          while(true)
  		  {
			  System.out.println("Connected ");
			  tab.setTable("Connected ");

		      System.out.println(localaddr+" is Waiting....... ");
		      tab.setTable(localaddr+" is Waiting....... ");
			  Socket s=ss.accept();
	  		  InputStream ins=s.getInputStream();
			  OutputStream ous=s.getOutputStream();
			  ObjectOutputStream oos=new ObjectOutputStream(ous);
			  ObjectInputStream ois=new ObjectInputStream(ins);
			  FileReader fr=new FileReader("nextnodes.txt");
			  BufferedReader br=new BufferedReader(fr);
			  String fd=(String)ois.readObject();
			   if(fd.equals("reqroute"))
	            {
				  	System.out.println("Route Request...");
				  	tab.setTable("Route Request...");
				  	localvect=(Vector)ois.readObject();
				  	sender=(String)localvect.firstElement();
				  	System.out.println("The Sender address is : "+sender);
				  	tab.setTable("The Sender address is : "+sender);
                  	destination=(String)localvect.get(1);
				  	System.out.println("The Destined address is : "+destination);
				  	tab.setTable("The Destined address is : "+destination);
				  	System.out.println("The Local Address is : "+localaddr);
				  	tab.setTable("The Local Address is : "+localaddr);
				  	if(destination.equalsIgnoreCase(localaddr))
				  	{
						  req(localvect);
                  	}
                  	else
                  	{
						  while((next=br.readLine())!=null)
						  {

						     try
						     {
		          	         	  s1=new Socket(next,8888);
		          	         	  OutputStream ous1=s1.getOutputStream();
		          	         	  ObjectOutputStream oos3=new ObjectOutputStream(ous1);
		          	         	  localvect.addElement(localaddr);
			      	         	  oos3.writeObject("reqroute");
			                 	  oos3.writeObject(localvect);
      						 }
      						 catch(Exception e)
      						 {
								 System.out.println("Error in file "+e);
      						 	 tab.setTable("Error in file "+e);
      					     }


						  }
					}

        	    }

        	    else if(fd.equals("ack"))
        	    {
					Timers.rece();
					System.out.println("Acknowledgement from destination ...");
					tab.setTable1("Acknowledgement from destination ...");
					localvect=(Vector)ois.readObject();
					it=localvect.elements();
					while(it.hasMoreElements())
					{
						path=(String)it.nextElement();
						System.out.print(" "+path+" --> ");
						tab.setTable1(" "+path+" --> ");
					}
					System.out.println();
				    if(((String)localvect.firstElement()).equalsIgnoreCase(localaddr))
				    {
                        i++;
						System.out.println("Acknowledgement sent from the destination ....");
						tab.setTable1("Acknowledgement sent from the destination ....");
						System.out.println("The sender can send the data now....");
						tab.setTable1("The sender can send the data now....");
						String destin=(String)localvect.remove(1);
						localvect.add(destin);
						it=localvect.elements();
						System.out.println("Source  ");
						tab.setTable1("Source ");
						System.out.println("The path from source to destination is as follows :");
						tab.setTable1("The path from source to destination is as follows :");
						while(it.hasMoreElements())
						{
							path=(String)it.nextElement();
							System.out.println(" "+path+" --> ");
							tab.setTable1(" "+path+" --> ");

						}
						new Store_Path(i,localvect);
						Store_Path.file();
						System.out.println("   Destination ");
						tab.setTable1("    Destination   ");
						Socket1();

					}

					else if(((String)localvect.get(2)).equalsIgnoreCase(localaddr))
					{

						localvect.remove(localvect.size()-1);
						s2=new Socket((String)localvect.firstElement(),8888);
						OutputStream os2=s2.getOutputStream();
						ObjectOutputStream ous2=new ObjectOutputStream(os2);
						ous2.writeObject("ack");
						FileOutputStream foo=new FileOutputStream("paths.txt");
						ObjectOutputStream oopp=new ObjectOutputStream(foo);
						oopp.writeObject(localvect);
						ous2.writeObject(localvect);
					}

					else

					{
		                localvect.remove(localvect.size()-1);
						sockaddr=(String)localvect.get(localvect.size()-1);
		                Enumeration en=localvect.elements();
	                    System.out.println("The next system address is "+sockaddr);
						tab.setTable1("The next system address is "+sockaddr);
						s2=new Socket(sockaddr,8888);
						System.out.println("Intermediate node "+localaddr+" forwarding the ack to "+sockaddr);
						tab.setTable1("Intermediate node "+localaddr+" forwarding the ack to "+sockaddr);
						OutputStream os2=s2.getOutputStream();
						ObjectOutputStream ous2=new ObjectOutputStream(os2);
						ous2.writeObject("ack");
						ous2.writeObject(localvect);

					}
				}

				else if(fd.equals("file++"))

				{
					i++;
					String sub=(String)ois.readObject();
					vv=(Vector)ois.readObject();
					System.out.println("The String sent is "+sub);
					new Store_Path(i,vv);
					Store_Path.file();
					Sender.Socket2(i,sub);
				}
				else if(fd.equals("file"))
				{
					JOptionPane.showMessageDialog(null,"File Received","File",JOptionPane.INFORMATION_MESSAGE);
					dd=(String)ois.readObject();
					System.out.println("The message sent from source is ::"+dd);
					tab.setTable1("The message sent from source is ::"+dd);
					Recv re=new Recv();
					FileInputStream pa=new FileInputStream("paths.txt");
					ObjectInputStream ooo=new ObjectInputStream(pa);
					Vector n=(Vector)ooo.readObject();
					for(int h=0;h<n.size();h++)
					{
						System.out.println("The value inside vector is  "+n.get(h));
						tab.setTable("The value inside vector is  "+n.get(h));
					}
					if(n.size()==2)
					{
						Socket ssp=new Socket((String)n.get(0),8888);
						ObjectOutputStream oop=new ObjectOutputStream(ssp.getOutputStream());
						oop.writeObject("ackfile");
						oop.writeObject("The message \""+dd+"\" has received by the destination "+(String)n.get(1));
					}
					else
					{
						Socket sss=new Socket((String)n.lastElement(),8888);
						n.removeElementAt(n.size()-1);
						ObjectOutputStream oop1=new ObjectOutputStream(sss.getOutputStream());
						oop1.writeObject("ackfile1");
						oop1.writeObject("The message \""+dd+"\" has received by the destinaton "+(String)n.get(1));
						oop1.writeObject(n);

					}

				}
				else if(fd.equals("ackfile"))
				{
					jdd=(String)ois.readObject();
					System.out.println(jdd);
					tab.setTable1(jdd);
				}
				else if(fd.equals("ackfile1"))
				{
					String ds=(String)ois.readObject();
					Vector vl=(Vector)ois.readObject();
					if(vl.size()==2)
					{
						Socket sspd=new Socket((String)vl.get(0),8888);
						ObjectOutputStream oop=new ObjectOutputStream(sspd.getOutputStream());
						oop.writeObject("ackfile");
						oop.writeObject(ds);

					}
					else
					{
						Socket ssj=new Socket((String)vl.lastElement(),8888);
						vl.removeElementAt(vl.size()-1);
						ObjectOutputStream oop1=new ObjectOutputStream(ssj.getOutputStream());
						oop1.writeObject("ackfile1");
						oop1.writeObject(ds);
						oop1.writeObject(vl);

					}

				}
	     	 }

	     }
	     catch(Exception e)
	     {
			 System.out.println("Some Error has occurred : "+e);
			 tab.setTable("Some Error has occurred : "+e);
		 }

	 }

	 public static void req(Vector v)
	 {
		 String b="";
		 try
		 {
		      int o=v.size();
      		  if(o==2)
      		  {
				  b=(String)v.firstElement();
			  }
      		  else
         	  {
	  			b=(String)(v.lastElement());
	  		  }
	  		  Enumeration en=v.elements();
	  		  while(en.hasMoreElements())
	  		  {
	  			String f=(String)en.nextElement();
	  			System.out.println("The path from source to destination  : "+f);
	  			tab.setTable1("The path from source to destination  : "+f);
      		  }
	  		  d=new Socket(b,8888);
	  		  OutputStream os=d.getOutputStream();
	  		  ObjectOutputStream oos1=new ObjectOutputStream(os);
	  		  oos1.writeObject("ack");
	  		  String ss="";
	   		  localvect=v;
	  		  FileOutputStream foo=new FileOutputStream("paths.txt");
	     	  ObjectOutputStream oopp=new ObjectOutputStream(foo);
			  oopp.writeObject(localvect);
	  		  boolean boo=true;
	  		  if(o==2)
	  			{}
	  		  else
	    	  {
	  			for(int i=2;i<o;i++)
	  			{
				  v.add((String)localvect.get(i));
	  			}
      		  }

	 		  oos1.writeObject(v);

		    }
		    catch(Exception e)
		    {
				System.out.println("Error at ack sending in destination "+e);
				tab.setTable1("Error at ack sending in destination "+e);
			}
	  }


	public static void Socket1()throws Exception
    {
	 	  System.out.println("Inside Socket1");
	 	  FileInputStream fip=new FileInputStream("rsain.txt");
	 	  byte str[]=new byte[fip.available()];
	 	  fip.read(str,0,str.length);
	 	  String tte=new String(str);
	 	  System.out.println("The mmm is :::"+tte);
	 	  FileInputStream fis=new FileInputStream("path1.txt");
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
	 		  System.out.println("The string in the textbox ::"+tte);
	 		  oos.writeObject(tte);
	 	  }
	 	  else
	 	  {
	 		  d.removeElementAt(0);
	 		  int m=d.size();
	 		  System.out.println("The size after removing the element..."+m);
	 		  Socket soc=new Socket((String)d.get(1),8888);
	 		  ObjectOutputStream oos=new ObjectOutputStream(soc.getOutputStream());
	 		  oos.writeObject("file++");
	 		  System.out.println("The string in the textbox ::"+tte);
	 		  oos.writeObject(tte);
	 		  oos.writeObject(d);

	 	  }

   }


 }