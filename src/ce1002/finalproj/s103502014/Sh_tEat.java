package ce1002.finalproj.s103502014;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Sh_tEat extends JFrame{
	static int x=0;
	static int y=0;
	static int xdist,ydist;
	public Sh_tEat(int xdist,int ydist)
	{
		this.xdist=xdist;
		this.ydist=ydist;
		JLabel lb=new JLabel("EAT!");
		lb.setHorizontalAlignment(JLabel.CENTER);
		lb.setForeground(Color.white);
		getContentPane().setBackground(new Color(0,0,128));
		setUndecorated(true);
		setFocusableWindowState(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(xdist,ydist);
		add(lb,BorderLayout.CENTER);
		this.x=-2;
		this.y=-2;
		setLocation((-2)*xdist,(-2)*ydist);
	}
	public void setPos(int x,int y)
	{
		this.x=x;
		this.y=y;
		setLocation(x*xdist,y*ydist);
		setVisible(true);
		//System.out.println("EAT:"+x+","+y);
	}
	public boolean isexist()
	{
		if(this.x>=0 && this.y>=0) return true;
		else return false;
	}
	public void hide()
	{
		this.x=-2;
		this.y=-2;
		setLocation((-2)*xdist,(-2)*ydist);
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
}
