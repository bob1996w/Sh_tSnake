package ce1002.finalproj.s103502014;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
public class Food extends JFrame{
	private int x=0;
	private int y=0;
	static JLabel ex=new JLabel();
	public Food(int mode,int xpos,int ypos,int xdist,int ydist,int leftexp)
	{
		x=xpos;
		y=ypos;
		//System.out.println("DEBUG: Food      :: New:("+x+","+y+")");
		
		JLabel fd=new JLabel();
		if	   (mode==1)fd.setText("UCCU");
		else if(mode==2)fd.setText("SPDUP");
		else if(mode==3)fd.setText("HAHA");
		fd.setHorizontalAlignment(JLabel.CENTER);
		fd.setVerticalAlignment(JLabel.CENTER);
		fd.setForeground(Color.white);
		add(fd);
		if(mode==1)
		{
			ex.setText("Next:"+Integer.toString(leftexp));
			ex.setFont(new Font(null,Font.PLAIN,10));
			ex.setForeground(Color.white);
			ex.setVerticalAlignment(JLabel.TOP);
			ex.setHorizontalAlignment(JLabel.CENTER);
			add(ex,BorderLayout.SOUTH);
		}
		setTitle("");
		setSize(xdist,ydist);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(x*xdist,y*ydist);
		setUndecorated(true);
		if     (mode==1)getContentPane().setBackground(new Color(101,168,0));
		else if(mode==2)getContentPane().setBackground(Color.blue);
		else if(mode==3)getContentPane().setBackground(new Color(255,185,15));
		
		setVisible(true);
	}
	//change the food pos.
	public void move(int xpos,int ypos,int xdist,int ydist)
	{
		x=xpos;
		y=ypos;
		setLocation(x*xdist,y*ydist);
		//System.out.println("DEBUG: Food      :: Move:("+x+","+y+")");
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void updatenext(int leftexp)
	{
		ex.setText("Next:"+leftexp);
	}
	public void setVis(boolean vis)
	{
		this.setVisible(vis);
	}
}
