import java.io.*;
import java.net.*;
import java.util.*;

class Store_Path
{
  static int i=0;
  static Vector v;
  static String a;
  Store_Path(int i,Vector v)
  {
	  this.i=i;
	  this.v=v;
  }

  Store_Path(String aa)
  {
	  this.a=aa;

  }

  static String get()
  {
	 System.out.println("The string in storepath is :"+a);
	 return a;
  }

  static void file() throws Exception
  {
		FileOutputStream fos=new FileOutputStream("path"+i+".txt");
  		ObjectOutputStream oos=new ObjectOutputStream(fos);
  		oos.writeObject(v);
  }

}
