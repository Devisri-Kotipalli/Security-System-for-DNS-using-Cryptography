import java.io.*;
import java.util.*;
import java.net.*;

class Send
{
   static Vector localvect;
   static Socket s;
   static String localaddr="";
   static String addr="";
   static FileReader fr;


   public static void send(String destination) throws Exception
   {
 	   localvect=new Vector();
 	   System.out.println("Send for sending client req");
 	   localaddr=((InetAddress.getLocalHost()).getHostName());
 	   localvect.addElement(localaddr);
 	   System.out.println("The Source is :"+(String)(localvect.firstElement()));
 	   localvect.addElement(destination);
 	   System.out.println("The Source is :"+(String)(localvect.get(1)));
 	   fr=new FileReader("nextnodes.txt");
 	   BufferedReader br=new BufferedReader(fr);

 	   while((addr=br.readLine())!=null)
	    {
			try
			{
    			System.out.println("Sender is going to create a socket with the system named "+addr);
				s=new Socket(addr,8888);
				System.out.println("Sender has created a socket connection to "+addr);
    			OutputStream ous=s.getOutputStream();
    			ObjectOutputStream oos=new ObjectOutputStream(ous);
    			oos.writeObject("reqroute");
    			oos.writeObject(localvect);

    			System.out.println("Sender has sent the request the requet to "+addr);
    		}
    		catch(Exception e)
    		{
				System.out.println("Error in sending the request to :"+addr);
				continue;
			}

    	}
    	try
    	{
			fr.close();
		}
		catch(Exception e)
		{
			System.out.println("File cannot be closed ....");
		}

   }


}