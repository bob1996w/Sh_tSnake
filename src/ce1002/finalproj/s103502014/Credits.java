package ce1002.finalproj.s103502014;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Credits extends JFrame implements KeyListener{
	static int dir=6;//direction of the snake
	static Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	static int xdiv=20;//how many blocks on x dir;
	static int ydiv=20;//how many blocks on y dir;
	static int xblock=(int) (screenSize.getWidth()/xdiv);//how many block in x direction
	static int yblock=(int) (screenSize.getHeight()/ydiv);//how many block in y direction
	static int xscreen=(int) (screenSize.getWidth());//X cor. of screen
	static int yscreen=(int) (screenSize.getHeight());//X cor. of screen
	static int x=0;//position x
	static int y=0;//position y
	static int step=0;//step took
	static boolean gameover=false;
	static boolean dirlock=false;
	private int hiddenscore=0;
	String allthanks=
			"Thanks to the following people for making this game:\n"
			+ "Technical Support: Ting-Wei Lu\n"
			+ "Idea Support: Liang-Kun Liu, Xuan-Hao Huang\n"
			+ "\n"
			+ "\n"
			+ "\n"
			+ "Original idea from the cool guys in the 7th restaurant someday and E12\n"
			+ "\n"
			+ "Special Thanks\n"
			+ "Stage 4: The laughing bun, Ting-Wei Lu\n"
			+ "And all of you who are looking at this crappy game."
			+ "\n"
			+ "\n"
			+ "我期末考19題好像寫錯了助教不要當我QQ";
	Credits() throws InterruptedException
	{
		gameover=false;
		JFrame credit=new JFrame();
		credit.setLocation(5*xblock,2*yblock);
		credit.setSize(10*xblock,13*yblock);
		credit.setFocusableWindowState(false);
		credit.getContentPane().setBackground(Color.black);
		credit.setUndecorated(true);
		JFrame back=new JFrame();
		back.setLocation(9*xblock,17*yblock);
		back.setSize(2*xblock,2*yblock);
		back.setFocusableWindowState(false);
		back.getContentPane().setBackground(Color.green);
		back.setUndecorated(true);
		
		//this block
		setLocation(x,y);
		setSize(xblock,yblock);
		setUndecorated(true);
		getContentPane().setBackground(Color.black);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Displays
		JLabel cre1=new JLabel("Credits");
		cre1.setFont(new Font(null,Font.BOLD,30));
		cre1.setForeground(Color.white);
		cre1.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel ex1=new JLabel("Back");
		ex1.setFont(new Font(null,Font.BOLD,30));
		ex1.setForeground(Color.white);
		ex1.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel this1=new JLabel("thx");
		this1.setFont(new Font(null,Font.BOLD,20));
		this1.setForeground(Color.white);
		this1.setHorizontalAlignment(JLabel.CENTER);
		
		JTextArea thx=new JTextArea();
		thx.setText(allthanks);
		thx.setForeground(Color.white);
		thx.setBackground(Color.black);
		
		credit.add(cre1,BorderLayout.NORTH);
		credit.add(thx,BorderLayout.CENTER);
		credit.setVisible(true);
		back.add(ex1);
		back.setVisible(true);

		add(this1,BorderLayout.NORTH);
		setVisible(true);
		setAlwaysOnTop(true);
		addKeyListener(this);
		while(true)
		{
			step++;
			dirlock=false;
			Thread.sleep(500);
			if     (dir==8)//up
			{
				y--;
				setLocation(x*xblock,y*yblock);
			}
			else if(dir==6)//right
			{
				x++;
				setLocation(x*xblock,y*yblock);
			}
			else if(dir==2)//down
			{
				y++;
				setLocation(x*xblock,y*yblock);
			}
			else if(dir==4)//left
			{
				x--;
				setLocation(x*xblock,y*yblock);
			}
			//bonus score!!
			if(x>=5 && x<=14 && y>=2 && y<=14)
			{
				hiddenscore+=100;
			}
			//GAMEOVER: touches the wall
			if(x>=xdiv||y>=ydiv||x<0||y<0)
			{
				gameover=true;
				break;
			}
			//exit to main menu
			if(x>=9 && x<=10 && y>=17 && y<=18)
				break;
		}
		if(gameover)
		{
			setAlwaysOnTop(false);
			JFrame byebye=new JFrame(){
				protected void processWindowEvent(WindowEvent e) {
					super.processWindowEvent(e);
					if (e.getID() == WindowEvent.WINDOW_CLOSING) {
						//System.out.println("INFO :           :: TERMINATED");
						System.exit(0);
					}
				}
			};
			byebye.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			byebye.setLayout(new GridLayout(5,1));
			byebye.setLocation(6*xblock,6*yblock);
			byebye.setSize(8*xblock,8*yblock);
			JLabel byemes1=new JLabel();
			JLabel byemes2=new JLabel();
			JLabel byemes3=new JLabel();
			JLabel byemes4=new JLabel();
			JLabel byemes5=new JLabel();
			JLabel byemes6=new JLabel();
			byemes1.setText("So sad...");
			byemes1.setFont(new Font(null,Font.BOLD,30));
			byemes2.setText("Game over BEFORE Game start...");
			byemes3.setText("Anyway, your snake struggled for "+step+" steps. Well Done!");
			byemes4.setText("Thank you for playing.");
			byemes5.setText("[This game will be closed in 10 seconds.]");
			byemes6.setText("Your snake as gone "+step+" steps in the menu.");
			byebye.add(byemes1);
			byebye.add(byemes2);
			byebye.add(byemes3);
			byebye.add(byemes4);
			byebye.add(byemes5);
			byebye.setAlwaysOnTop(true);
			byebye.setVisible(true);
			Thread.sleep(10000);
			System.exit(0);
		}
		credit.setVisible(false);
		back.setVisible(false);
		setVisible(false);
	}
		
	public int gethiddenscore()
	{
		return hiddenscore;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if     (e.getKeyCode()==KeyEvent.VK_UP   ||e.getKeyChar()=='w'){if(dir!=2&&dirlock==false)dir=8;dirlock=true;}//Up
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyChar()=='d'){if(dir!=4&&dirlock==false)dir=6;dirlock=true;}//Right
		else if(e.getKeyCode()==KeyEvent.VK_DOWN ||e.getKeyChar()=='s'){if(dir!=8&&dirlock==false)dir=2;dirlock=true;}//Down
		else if(e.getKeyCode()==KeyEvent.VK_LEFT ||e.getKeyChar()=='a'){if(dir!=6&&dirlock==false)dir=4;dirlock=true;}//Left
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
