package ce1002.finalproj.s103502014;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class StageSelect extends JDialog implements KeyListener{
	private int nowselect=0;
	private int maxlevel=0;
	static JLabel sel=new JLabel();
	StageSelect(int maxlevel,int xblock,int yblock) throws InterruptedException
	{
		this.maxlevel=maxlevel;
		setLocation(8*xblock,8*yblock);
		setSize(4*xblock,4*yblock);
		setUndecorated(true);
		getContentPane().setBackground(new Color(101,168,0));
		
		JLabel title=new JLabel("Select Stage:");
		title.setFont(new Font(null,Font.PLAIN,20));
		title.setForeground(Color.white);
		title.setHorizontalAlignment(JLabel.CENTER);
		JLabel under=new JLabel("^v choose/Space Start");
		under.setFont(new Font(null,Font.PLAIN,10));
		under.setForeground(Color.white);
		under.setHorizontalAlignment(JLabel.CENTER);
		sel.setText(Integer.toString(nowselect));      
		sel.setFont(new Font(null,Font.BOLD,30));
		sel.setHorizontalAlignment(JLabel.CENTER);
		
		add(title,BorderLayout.NORTH);
		add(under,BorderLayout.SOUTH);
		add(sel,BorderLayout.CENTER);
		addKeyListener(this);

		setModal(true);
		setVisible(true);
	}
	public int getstart()
	{
		return nowselect;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyChar()=='w')
		{
			if(nowselect<maxlevel)
			{
				nowselect++;
				sel.setText(Integer.toString(nowselect));
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyChar()=='s')
		{
			if(nowselect>0)
			{
				nowselect--;
				sel.setText(Integer.toString(nowselect));
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_SPACE || e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			setVisible(false);
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
