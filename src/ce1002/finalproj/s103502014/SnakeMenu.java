package ce1002.finalproj.s103502014;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class SnakeMenu extends JFrame implements KeyListener{
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
	static int step=0;//how many steps did the snake pass
	static int initscore=0;//hidden score
	static boolean gameover=false;
	static boolean endgame=false;
	static int enterlevel=0;//the level to enter
	static int maxlevel=0;//max level able to go in to
	static boolean[] modeentered={false,false,false};//whether the mode is entered
	static boolean stageselectable=false;//when entering Stage Mode, player can choose which stage to start with.
	static JFrame maininfo=new JFrame();
	static JFrame maincontrols=new JFrame();
	static JFrame mainstagemode=new JFrame();
	static JFrame mainspeedmode=new JFrame();
	static JFrame mainsh_tmode=new JFrame();
	static JFrame mainhellmode=new JFrame();
	static JFrame maincredits=new JFrame();
	static JFrame mainexit=new JFrame();
	static int duration=500;
	static StageHead head;
	static Credits credits;
	
	
	public SnakeMenu() throws InterruptedException
	{
		//JFrames
		maininfo.setLocation(2*xblock,2*yblock);
		maininfo.setSize(7*xblock,6*yblock);
		maininfo.setUndecorated(true);
		maininfo.getContentPane().setBackground(Color.magenta);
		maininfo.setFocusableWindowState(false);
		maincontrols.setLocation(2*xblock,12*yblock);
		maincontrols.setSize(7*xblock,6*yblock);
		maincontrols.setUndecorated(true);
		maincontrols.getContentPane().setBackground(Color.lightGray);
		maincontrols.setFocusableWindowState(false);
		mainstagemode.setLocation(11*xblock,2*yblock);
		mainstagemode.setSize(7*xblock,3*yblock);
		mainstagemode.setUndecorated(true);
		mainstagemode.getContentPane().setBackground(new Color(127,255,0));
		mainstagemode.setFocusableWindowState(false);
		mainspeedmode.setLocation(11*xblock,5*yblock);
		mainspeedmode.setSize(7*xblock,3*yblock);
		mainspeedmode.setUndecorated(true);
		mainspeedmode.getContentPane().setBackground(Color.blue);
		mainspeedmode.setFocusableWindowState(false);
		mainsh_tmode.setLocation(11*xblock,8*yblock);
		mainsh_tmode.setSize(7*xblock, 3*yblock);
		mainsh_tmode.setUndecorated(true);
		mainsh_tmode.getContentPane().setBackground(new Color(255,185,15));		
		mainsh_tmode.setFocusableWindowState(false);
		mainhellmode.setLocation(11*xblock,11*yblock);
		mainhellmode.setSize(7*xblock,3*yblock);
		mainhellmode.setUndecorated(true);
		mainhellmode.getContentPane().setBackground(Color.red);
		mainhellmode.setFocusableWindowState(false);
		maincredits.setLocation(11*xblock,14*yblock);
		maincredits.setSize(7*xblock,2*yblock);
		maincredits.setUndecorated(true);
		maincredits.getContentPane().setBackground(Color.green);
		maincredits.setFocusableWindowState(false);
		mainexit.setLocation(11*xblock,16*yblock);
		mainexit.setSize(7*xblock,2*yblock);
		mainexit.setUndecorated(true);
		mainexit.getContentPane().setBackground(Color.gray);
		mainexit.setFocusableWindowState(false);
		//JLabels
		JLabel maininfo_title=new JLabel("Frame Snake");
		maininfo_title.setFont(new Font(null,Font.BOLD,45));
		maininfo_title.setForeground(Color.white);
		maininfo_title.setHorizontalAlignment(JLabel.CENTER);
		JLabel maininfo_sub1=new JLabel("Games shouldn't be limited in A window.");
		maininfo_sub1.setFont(new Font(null,Font.ITALIC,15));
		maininfo_sub1.setForeground(Color.white);
		maininfo_sub1.setHorizontalAlignment(JLabel.CENTER);
		JLabel maincontrols_1=new JLabel("Controls");
		maincontrols_1.setFont(new Font(null,Font.BOLD,45));
		maincontrols_1.setHorizontalAlignment(JLabel.CENTER);
		JLabel maincontrols_2=new JLabel("Move: arrow keys or WASD");
		maincontrols_2.setFont(new Font(null,Font.PLAIN,15));
		maincontrols_2.setHorizontalAlignment(JLabel.CENTER);
		JLabel maincontrols_3=new JLabel("poi");
		maincontrols_3.setFont(new Font(null,Font.ITALIC,15));
		maincontrols_3.setHorizontalAlignment(JLabel.CENTER);
		maincontrols_3.setForeground(new Color(92,92,92));
		JLabel mainstagemode_lb=new JLabel("Stage Mode");
		mainstagemode_lb.setFont(new Font(null,Font.BOLD,30));
		mainstagemode_lb.setForeground(Color.white);
		mainstagemode_lb.setHorizontalAlignment(JLabel.CENTER);
		JLabel mainspeedmode_lb=new JLabel("Speed Mode");
		mainspeedmode_lb.setFont(new Font(null,Font.BOLD,30));
		mainspeedmode_lb.setForeground(Color.white);
		mainspeedmode_lb.setHorizontalAlignment(JLabel.CENTER);
		JLabel mainsh_tmode_lb=new JLabel("Sh_t Mode");
		mainsh_tmode_lb.setFont(new Font(null,Font.BOLD,30));
		mainsh_tmode_lb.setForeground(Color.white);
		mainsh_tmode_lb.setHorizontalAlignment(JLabel.CENTER);
		/*
		JLabel mainhellmode_lb=new JLabel("[Deprecated]");
		mainhellmode_lb.setFont(new Font(null,Font.BOLD,30));
		mainhellmode_lb.setForeground(Color.white);
		mainhellmode_lb.setHorizontalAlignment(JLabel.CENTER);
		*/
		JLabel maincredits_lb=new JLabel("Credits");
		maincredits_lb.setFont(new Font(null,Font.BOLD,30));
		maincredits_lb.setForeground(Color.white);
		maincredits_lb.setHorizontalAlignment(JLabel.CENTER);
		JLabel mainexit_lb=new JLabel("Exit");
		mainexit_lb.setFont(new Font(null,Font.BOLD,30));
		mainexit_lb.setForeground(Color.white);
		mainexit_lb.setHorizontalAlignment(JLabel.CENTER);
		JLabel thisone=new JLabel("Hi!!");
		thisone.setFont(new Font(null,Font.BOLD,20));
		thisone.setForeground(Color.white);
		thisone.setHorizontalAlignment(JLabel.CENTER);
		
		//this block(Menu Head)
		setLocation(x,y);
		setSize(xblock,yblock);
		setUndecorated(true);
		getContentPane().setBackground(Color.black);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		addKeyListener(this);
		
		//add JLabels to JFrames
		maininfo.add(maininfo_title,BorderLayout.CENTER);
		maininfo.add(maininfo_sub1,BorderLayout.SOUTH);
		maincontrols.add(maincontrols_1,BorderLayout.NORTH);
		maincontrols.add(maincontrols_2,BorderLayout.CENTER);
		maincontrols.add(maincontrols_3,BorderLayout.SOUTH);
		mainstagemode.add(mainstagemode_lb,BorderLayout.CENTER);
		mainspeedmode.add(mainspeedmode_lb,BorderLayout.CENTER);
		mainsh_tmode.add(mainsh_tmode_lb,BorderLayout.CENTER);
		//mainhellmode.add(mainhellmode_lb,BorderLayout.CENTER);
		maincredits.add(maincredits_lb,BorderLayout.CENTER);
		mainexit.add(mainexit_lb,BorderLayout.CENTER);
		add(thisone,BorderLayout.CENTER);
		
		maininfo.setVisible(true);
		maincontrols.setVisible(true);
		mainstagemode.setVisible(true);
		mainspeedmode.setVisible(true);
		mainsh_tmode.setVisible(true);
		//mainhellmode.setVisible(true);
		maincredits.setVisible(true);
		mainexit.setVisible(true);
		setVisible(true);
		
		
		while(true)
		{
			step++;
			Thread.sleep(duration);
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
			//GAMEOVER: touches the wall
			if(x>=xdiv||y>=ydiv||x<0||y<0)
			{
				gameover=true;
				endgame=true;
				break;
			}
			
			//touches maininfo
			if(x>=2 && x<=8 && y>=2 && y<=7)
			{
				gameover=true;
				endgame=true;
				break;
			}
			//touches maincontrols
			if(x>=2 && x<=8 && y>=12 && y<=17)
			{
				gameover=true;
				endgame=true;
				break;
			}
			//touches mainstagemode
			if(x>=11 && x<=17 && y>=2 && y<=4)
			{
				//break;
				setMainVis(false);
				setVisible(false);
				{
					if(!modeentered[0])
					{
						JLabel m15=new JLabel("LEVEL? "+enterlevel);
						JFrame mode1 =new JFrame();
						mode1.setLocation(6*xblock,6*yblock);
						mode1.setSize(8*xblock,8*yblock);
						mode1.getContentPane().setBackground(Color.black);
						mode1.setLayout(new GridLayout(5,1));
						mode1.setUndecorated(true);
						mode1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						JLabel m11=new JLabel("Stage Mode");
						m11.setFont(new Font(null,Font.BOLD,30));
						m11.setForeground(Color.white);
						JLabel m12=new JLabel();
						m12.setText("Try to survive through 10 stages to complete the game!");
						m12.setForeground(Color.white);
						JLabel m13=new JLabel();
						m13.setText("If you hit obstacles, you will die right away.");
						m13.setForeground(Color.white);
						JLabel m14=new JLabel();
						m14.setText("Get 10 UCCUs to go to next stage");
						m14.setForeground(Color.white);
						
						m15.setFont(new Font(null,Font.BOLD,30));
						m15.setForeground(Color.white);
						//mode1.addKeyListener();
						mode1.add(m11);
						mode1.add(m12);
						mode1.add(m13);
						mode1.add(m14);
						mode1.add(m15);
						mode1.setVisible(true);
						for(int i=5;i>0;i--)
						{
							m15.setText("READY? "+i);
							Thread.sleep(1000);
						}
						mode1.setVisible(false);
					}
					modeentered[0]=true;
					head=new StageHead(1,enterlevel,initscore,stageselectable,maxlevel);
					initscore=0;
					if(head.getlevel()>maxlevel)maxlevel=head.getlevel();
					head=null;
					x=0;
					y=0;
					dir=6;
					setLocation(x*xblock,y*yblock);
					setMainVis(true);
					setMainTop(true);
					setVisible(true);
					setAlwaysOnTop(true);
					stageselectable=true;
				}
			}
			//touches mainspeedmode
			if(x>=11 && x<=17 && y>=5 && y<=7)
			{
				//break;
				setMainVis(false);
				setVisible(false);
				{
					JFrame mode2=new JFrame();
					mode2.setLocation(6*xblock,6*yblock);
					mode2.setSize(8*xblock,8*yblock);
					mode2.getContentPane().setBackground(Color.black);
					mode2.setLayout(new GridLayout(5,1));
					mode2.setUndecorated(true);
					mode2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					JLabel m21=new JLabel("Speed Mode");
					m21.setFont(new Font(null,Font.BOLD,30));
					m21.setForeground(Color.white);
					JLabel m22=new JLabel();
					m22.setText("Survive the high speed in this game!");
					m22.setForeground(Color.white);
					JLabel m23=new JLabel();
					m23.setText("Score is judged by score, steps and food.");
					m23.setForeground(Color.white);
					JLabel m24=new JLabel();
					m24.setText("Get food to increase speed and score!");
					m24.setForeground(Color.white);
					JLabel m25=new JLabel("READY?");
					m25.setFont(new Font(null,Font.BOLD,30));
					m25.setForeground(Color.white);
					mode2.add(m21);
					mode2.add(m22);
					mode2.add(m23);
					mode2.add(m24);
					mode2.add(m25);
					mode2.setVisible(true);
					for(int i=5;i>0;i--)
					{
						m25.setText("READY? "+i);
						Thread.sleep(1000);
					}
					mode2.setVisible(false);
					head=new StageHead(2,-1,initscore,false,0);
					modeentered[1]=true;
					initscore=0;
					head=null;
					//System.gc();
					//remove(head);
					x=0;
					y=0;
					dir=6;
					setLocation(x*xblock,y*yblock);
					setMainVis(true);
					setMainTop(true);
					setVisible(true);
					setAlwaysOnTop(true);
					
				}
			}
			//touches sh_t mode
			if(x>=11 && x<=17 && y>=8 && y<=10)
			{
				//break;
				setMainVis(false);
				setVisible(false);
				{
					JFrame mode3=new JFrame();
					mode3.setLocation(6*xblock,6*yblock);
					mode3.setSize(8*xblock,8*yblock);
					mode3.getContentPane().setBackground(Color.black);
					mode3.setLayout(new GridLayout(5,1));
					mode3.setUndecorated(true);
					mode3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					JLabel m31=new JLabel("Sh_t Mode");
					m31.setFont(new Font(null,Font.BOLD,30));
					m31.setForeground(Color.white);
					JLabel m32=new JLabel();
					m32.setText("Your snake sh_ts in this game.");
					m32.setForeground(Color.white);
					JLabel m33=new JLabel();
					m33.setText("Try to avoid those sh_ts!");
					m33.setForeground(Color.white);
					JLabel m34=new JLabel();
					m34.setText("How much score can you get?");
					m34.setForeground(Color.white);
					JLabel m35=new JLabel("READY?");
					m35.setFont(new Font(null,Font.BOLD,30));
					m35.setForeground(Color.white);
					mode3.add(m31);
					mode3.add(m32);
					mode3.add(m33);
					mode3.add(m34);
					mode3.add(m35);
					mode3.setVisible(true);
					for(int i=5;i>0;i--)
					{
						m35.setText("READY? "+i);
						Thread.sleep(1000);
					}
					mode3.setVisible(false);
					head=new StageHead(3,-1,initscore,false,0);
					modeentered[2]=true;
					initscore=0;
					head=null;
					//System.gc();
					//remove(head);
					x=0;
					y=0;
					dir=6;
					setLocation(x*xblock,y*yblock);
					setMainVis(true);
					setMainTop(true);
					setVisible(true);
					setAlwaysOnTop(true);
				}
			}
			//touches secret area
			if(x>=11 && x<=17 && y>=11 && y<=13)
			{
				stageselectable=true;
				maxlevel=9;
				mainstagemode.getContentPane().setBackground(new Color(101,168,0));
			}
			//touches credits
			if(x>=11 && x<=17 && y>=14 && y<=15)
			{
				setMainVis(false);
				setVisible(false);
				credits=new Credits();
				initscore+=credits.gethiddenscore();
				credits=null;
				x=0;
				y=0;
				dir=6;
				setLocation(x*xblock,y*yblock);
				setMainVis(true);
				setMainTop(true);
				setVisible(true);
				setAlwaysOnTop(true);
			}
			//touches exit
			if(x>=11 && x<=17 && y>=16 && y<=17)
			{
				endgame=true;
				break;
			}
		}
		if(endgame)
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
			if(gameover)
			{
				byebye.add(byemes1);
				byebye.add(byemes2);
				byebye.add(byemes3);
			}
			else
			{
				byebye.add(byemes6);
			}
			byebye.add(byemes4);
			byebye.add(byemes5);
			byebye.setAlwaysOnTop(true);
			byebye.setVisible(true);
			Thread.sleep(10000);
			System.exit(0);
		}
		
		
	}
	public void setmax(int level)
	{
		maxlevel=level;
	}
	public void setMainVis(boolean vis)
	{
		maininfo.setVisible(vis);
		maincontrols.setVisible(vis);
		mainstagemode.setVisible(vis);
		mainspeedmode.setVisible(vis);
		mainsh_tmode.setVisible(vis);
		//mainhellmode.setVisible(vis);
		maincredits.setVisible(vis);
		mainexit.setVisible(vis);
	}
	public void setMainTop(boolean top)
	{
		maininfo.setAlwaysOnTop(top);
		maincontrols.setAlwaysOnTop(top);
		mainstagemode.setAlwaysOnTop(top);
		mainspeedmode.setAlwaysOnTop(top);
		mainsh_tmode.setAlwaysOnTop(top);
		//mainhellmode.setAlwaysOnTop(top);
		maincredits.setAlwaysOnTop(top);
		mainexit.setAlwaysOnTop(top);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if     (e.getKeyCode()==KeyEvent.VK_UP   ||e.getKeyChar()=='w'){if(dir!=2)dir=8;}//Up
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyChar()=='d'){if(dir!=4)dir=6;}//Right
		else if(e.getKeyCode()==KeyEvent.VK_DOWN ||e.getKeyChar()=='s'){if(dir!=8)dir=2;}//Down
		else if(e.getKeyCode()==KeyEvent.VK_LEFT ||e.getKeyChar()=='a'){if(dir!=6)dir=4;}//Left
		else if(e.getKeyChar()=='o'){if(duration>10)duration-=25;}
		else if(e.getKeyChar()=='i'){if(duration<10000)duration+=25;}
		else if(e.getKeyChar()=='p'){duration=500;}
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
