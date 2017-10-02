package ce1002.finalproj.s103502014;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
public class Body extends JFrame{
	private int x;
	private int y;
	private int width;
	private int height;
	private int xbefore;//last x pos of this
	private int ybefore;//last y pos of this
	private boolean vis=false;
	public Body(int xpos,int ypos,int xdist,int ydist)
	{
		x=xpos;
		y=ypos;
		xbefore=x;
		ybefore=y;
		width=xdist;
		height=ydist;/*
		System.out.println("DEBUG: Body      :: constructed!");
		System.out.println("DEBUG: Body      :: x="+x);
		System.out.println("DEBUG: Body      :: y="+y);
		System.out.println("DEBUG: Body      :: width="+width);
		System.out.println("DEBUG: Body      :: height="+height);
		*/
		setTitle("");
		setSize(xdist,ydist);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(x*width,y*height);
		setUndecorated(true);
		getContentPane().setBackground(Color.gray);
		setFocusableWindowState(false);
		setVisible(true);
	}
	
	public void setVis(boolean vis)//set if visible
	{
		this.vis=vis;
		setVisible(vis);
	}
	public boolean getVis()//get if visible
	{
		return this.vis;
	}
	public void movepanel(int xpos,int ypos)
	{
		x=xpos;
		y=ypos;
		//System.out.println("DEBUG: Body      :: move x="+x);
		//System.out.println("DEBUG: Body      :: move y="+y);
		setLocation(x*width,y*height);
	}
	//get coordinates
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getXbefore()
	{
		return xbefore;
	}
	public int getYbefore()
	{
		return ybefore;
	}
	public void setbefore(int xb,int yb)
	{
		xbefore=xb;
		ybefore=yb;
	}
}
