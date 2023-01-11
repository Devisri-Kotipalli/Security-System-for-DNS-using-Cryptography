import java.io.*;
import java.net.*;
import javax.swing.*;
class prime
{
	 int i;
	 String s;
	 int flag=0;
	 boolean checkPrime(long n)
	 {
		  if(n==0 || n==1)
		  {
		  System.out.println("not prime number");
		  }
		  else
		  {
			   for(i=2;i<n;i++)
			   {
					if(n%i == 0)
					{
						 flag=1;
						 break;
					}
			   }
		  }
		  if(flag==1)
			   return false;
		  else
			   return true;
	 }
}

class calculation
{
	 long great,a;
	 double aa,bb,cc,rm;
	 long rd;
	 long eval;
	 long calE(long pi,long p,long q)
	 {
		  great=0;
		  aa=Math.log(pi)/Math.log(10);
		  bb=Math.floor(aa);
		  cc=Math.pow(10,bb);
		  rm=Math.random()*cc;
		  System.out.println("rm :"+rm);
		  rd=Math.round(rm);
		  System.out.println("rd :"+rd);
		  while(great != 1)
		  {
			   rd=rd+1;
			   great=GCD(rd,pi);
			   pi=(p-1)*(q-1);
		  }
		  return rd;
	 }

	 long GCD(long e,long pi)
	 {
		 System.out.println("e :"+e);
		  if(e > pi)
		  {
			   while(e%pi != 0)
			   {
					a=e%pi;
					e=pi;
					pi=a;
			   }
			   great=pi;
		  }
		  else
		  {
			   while(pi%e != 0)
			   {
					a=pi%e;
					System.out.println("a :"+a);
					pi=e;
					System.out.println("pi :"+pi);
					e=a;
					System.out.println("e12 :"+e);
			   }
			   great=e;
		  }
		  return great;
	 }
}


public class keyrsa1
{
	String sng1;
	keyrsa1(String sng)
	{
		sng1=sng;
	}
	prime pm = new prime();
	calculation cal = new calculation();
	String s,pstr,qstr,output;
	long p,q,pi,e,val,ds,r,qd;
	static long d,n;
	int i,cnt;
	long rst[] = new long[100];
	long div[] = new long[100];
	long qud[] = new long[100];
	long rem[] = new long[100];


	public void Key(String ps,String qs)throws Exception
	{
  		pstr=ps;
  		qstr=qs;
  		p=Long.parseLong(pstr);
  		q=Long.parseLong(qstr);
  		if(p==q)
  			System.out.println("VALUE OF p and q SHOULD NOT EQUAL");
  		else if(!pm.checkPrime(p))
  			System.out.println("PLEASE,ENTER p VALUE AS PRIME NUMBER");
  		else if(!pm.checkPrime(q))
  			System.out.println("PLEASE,ENTER q VALUE AS PRIME NUMBER");
  		else if((!pm.checkPrime(p)) && (!pm.checkPrime(q)))
  			System.out.println("PLEASE,ENTER p & q VALUE AS PRIME NUMBER");
  		else if(pm.checkPrime(p) && pm.checkPrime(q))
  		{
  			 n=p*q;
  			 pi=(p-1)*(q-1);
  			 e=cal.calE(pi,p,q);
  			 System.out.println("e :"+e);

			qd=pi/e;
   			r=pi%e;
   			cnt=0;
   			rst[cnt]=pi;
   			div[cnt]=e;
   			qud[cnt]=qd;
   			rem[cnt]=r;
			System.out.println("val	ds	qd	r");
   			do
   			{
				cnt++;
				val=div[cnt-1];	//val=e
				ds=rem[cnt-1];//ds = r
				qd=val/ds; // qd=e/r
				r=val%ds;  //  r=e%r
				System.out.println(val+"\t"+ds+"\t"+qd+"\t"+r);
				if(r != 0)
				{
					 rst[cnt]=val;		//e
					 div[cnt]=ds;		//r
					 qud[cnt]=qd;		//e/r
					 rem[cnt]=r; 		//e%r
				}
   			}while(r != 0);
   			long p1,q1,s1,t1,p2,q2,s2,t2,t;

			p1=rst[cnt-1];
			q1=-qud[cnt-1];
			s1=div[cnt-1];
			t=1;

			for(i=(cnt-2);i>=0;i--)
			{
				 p2=rst[i];
				 q2=-qud[i];
				 s2=div[i];
				 if(s1==rem[i])
				 {
				   if(p1==s2)
	   				{
						p1=p2;
						t1=t;
						t=q1;
						q1=t1+(q1*q2);
						s1=s2;
	   				}
				 }
			}
			if(q1<0)
				 d=pi+q1;
			else
				 d=q1;
			output ="\n\nPublic Key :"+"\n   Exponent Value (e) = "+String.valueOf(e)+
			        "\n   N Value (n)        ="+String.valueOf(n)+"\n\nPrivate Key :"+
		            "\n  Decryption Key (d)  ="+String.valueOf(d)+"\n  N Value (n)         ="+
	         String.valueOf(n);
	         JOptionPane.showMessageDialog(null,output,"RSA Key",JOptionPane.INFORMATION_MESSAGE);
             System.out.println(output);


			 try
			 {
				EnRSA en=new EnRSA();
				en.GetRSA(e,n,sng1);
			 }
		    catch(Exception ie)
		    {
		    	  System.out.println("Exception"+ie);
		    }
	   }
}

}
