package ce1002.finalproj.s103502014;
//103502014 Bob Wang
//NCU CSIE 
//Java Final Project
//Name: Frame Snake
//Description:Snake game using JFrame
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class FinalProj{
	public static void main(String[] args) throws InterruptedException
	{
		JFrame frame=new JFrame();
		JOptionPane beforestart=new JOptionPane();
		beforestart.showMessageDialog(frame,"Get yourself a best view and click OK to start.","Starting",JOptionPane.PLAIN_MESSAGE);
		SnakeMenu sm=new SnakeMenu();
		//new Head(0);
		//new SpeedHead();
		//new Credits();
		//new StageHead(1,0,0,true,9);
	}
	
}
