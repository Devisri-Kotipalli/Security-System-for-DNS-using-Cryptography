import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;



public class EnRSA
{
  static String s,str="",ch="";
  long e,n,c,f,data;
  int k,i;
  String estr,nstr;
  public void GetRSA(long es, long ns,String s) throws IOException
  {
	  System.out.println(s);
	  byte b[]=s.getBytes();
	  FileOutputStream fos=new FileOutputStream("data1.txt");
	  fos.write(b);
	  e=es;
   	  n=ns;
   	  FileInputStream file=new FileInputStream("data1.txt");
      System.out.println("The value for e :"+e);
      System.out.println("The value of n :"+n);

   		while((data=file.read())!=-1)
   		{
			if(Math.max(data,n) == data)
			{
			  JOptionPane.showMessageDialog(null,"To Encrypt Message is too Big, must (Message < n)","Error",1);
			  System.exit(0);
			}
			if ( e % 2 == 0)
			{

			  c = 1;
			 for ( i = 1; i <= e/2; i++)
			 {
			   f = (data*data) % n;
			   c = (f*c) % n;
			 }
			}
			else
			{

			   c = data;
			   for (  i = 1; i <= e/2; i++)
			   {
				 f = (data*data) % n;
				 c = (f*c) % n;
			   }

			}
			k=(int)c;
			str=Long.toString(c);
			ch=ch+str+" ";

   		}
   		System.out.println(ch);
   		file.close();
  	}

  	public void WriteRSA(long d,long n) throws IOException
  	{
	   try
		{
			String s=d+"";
			String s1=n+"";
		    FileWriter put = new FileWriter("rsain.txt");
		    put.write(ch);
		    put.close();
		    System.out.println("Destination Name:"+Sender.destination);
		    Send.send(Sender.destination);

		}
		catch(Exception es){}
  }

}