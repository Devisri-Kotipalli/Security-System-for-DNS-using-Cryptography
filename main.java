import com.sun.tools.javac.Main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class main extends Thread{
    public static void main(String[] args) throws Exception {

        Swing1 ob = new Swing1();
        ob.start();
    }


}
