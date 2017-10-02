package ce1002.finalproj.s103502014;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Sh_t{
	static int xdiv=0;
	static int ydiv=0;
	static int xdist=1;
	static int ydist=1;
	static JFrame[][] st=new JFrame[20][20];
	static int[][] pattern={
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};
	public Sh_t(int xdiv,int ydiv,int xdist,int ydist)
	{
		this.xdiv=xdiv;
		this.ydiv=ydiv;
		this.xdist=xdist;
		this.ydist=ydist;
	}
	public void initcreate()
	{
		for(int i=0;i<xdiv;i++)
		{
			for(int j=0;j<ydiv;j++)
			{
				st[i][j]=new JFrame();
				st[i][j].getContentPane().setBackground(new Color(139,105,20));
				st[i][j].setUndecorated(true);
				st[i][j].setLocation(i*xdist,j*ydist);
				st[i][j].setSize(xdist,ydist);
				st[i][j].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				st[i][j].setFocusableWindowState(false);
				if(pattern[j][i]==1)
				{
					st[i][j].setVisible(true);
				}
			}
		}
	}
	public void setSh_t(int x,int y)
	{
		pattern[y][x]=1;
		st[x][y].setVisible(true);
	}
	public void clrSh_t(int x,int y)
	{
		pattern[y][x]=0;
		st[x][y].setVisible(false);
	}
	public void clean()
	{
		for(int i=0;i<xdiv;i++)
		{
			for(int j=0;j<ydiv;j++)
			{
				pattern[j][i]=0;
				st[i][j].setVisible(false);
			}
		}
	}
	public boolean isexist(int x,int y)
	{
		if(pattern[y][x]==1)return true;
		else                return false;
	}
}
