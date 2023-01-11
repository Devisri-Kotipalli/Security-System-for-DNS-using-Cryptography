import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;

public class tab extends JFrame
{
	int a;
	JTable jt;
	Container c;
	static int k=0;
	static int l=0;
	static DefaultTableModel df;
	tab()
	{
	df=new DefaultTableModel(50,0);
	jt=new JTable(df);
	df.addColumn("Request");
	df.addColumn("Acknowledgement");

	jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	c=getContentPane();

	JScrollPane jsp=new JScrollPane(jt);
	c.add(jsp);

	setVisible(true);
	setSize(800,800);
	}

	static void setTable(String s)
	{
		df.setValueAt(s,k,0);
		k++;
	}
	static void setTable1(String s)
	{
		df.setValueAt(s,l,1);
		l++;
	}
}
