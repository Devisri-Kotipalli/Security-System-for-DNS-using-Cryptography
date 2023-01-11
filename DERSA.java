
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

public class DERSA
{

	 static String s,sn1="";
	 int info[] = {48,49,50,51,52,53,54,55,56,57};
	 long a[]    = {0,1,2,3,4,5,6,7,8,9};
  	 long d,n,g,f,value,temp=0;
  	 int k,l=0,data,i;
  	 char c[] = new char[1000];
  	 String dstr,nstr;

  	 public void ProcessDecrp(long ds,long ns,String sn) throws IOException
  	 {
		 d=ds;
  		 n=ns;
  		 byte b[]=sn.getBytes();
  		 FileOutputStream fos=new FileOutputStream("rsain.txt");
		 fos.write(b);
		 FileInputStream f0=new FileInputStream("rsain.txt");
		 FileOutputStream put = new FileOutputStream("Output.txt");

		 while((data=f0.read()) != -1)
		 {
			System.out.println("Data:"+data);
			value=0;
			for(i=0;i<info.length;i++)
	 		{
	 		  if(data==info[i])
				temp=a[i];
	 		}
	 		value=(value*10)+temp;
	 		data=f0.read();

	 		while(data!=32)
	 		{
	 		 for(i=0;i<info.length;i++)
	 		 {
	 		  if(data==info[i])
				 temp=a[i];
	 		 }
	 		 value=(value*10)+temp;
	 		 data=f0.read();
	 		}

	 		if ( d % 2 == 0)
	 		{
	 		  g = 1;
	 		  for ( i = 1; i <= d/2; i++)
	 		  {
				 f = (value*value) % n;
				 g = (f*g) % n;
				 System.out.println("if:"+g);
	 		  }
	 		}
	 		else
	 		{
	 		  g = value;
	 		  for (  i = 1; i <= d/2; i++)
	 		  {
				 f = (value*value) % n;
				 g = (f*g) % n;
				 System.out.println("else:"+g);
	 		  }
	 		}
	 		char k1=(char)g;
	 		sn1=sn1+k1;
	 		put.write(sn1.getBytes());

		}

		Recv.ja.setText(sn1.substring(0,(sn1.length()-4)));
		put.close();
   }

 }
