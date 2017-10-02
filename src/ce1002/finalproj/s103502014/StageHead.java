package ce1002.finalproj.s103502014;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class StageHead extends JFrame implements KeyListener{
	static int dir=6;//direction of the snake
	//8=up,6=right,2=down,4=left
	static int len=0;//the length of the snake body
	static Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	static int xdiv=20;//how many blocks on x dir;
	static int ydiv=20;//how many blocks on y dir;
	static int xblock=(int) (screenSize.getWidth()/xdiv);//how many block in x direction
	static int yblock=(int) (screenSize.getHeight()/ydiv);//how many block in y direction
	static int xscreen=(int) (screenSize.getWidth());//X cor. of screen
	static int yscreen=(int) (screenSize.getHeight());//X cor. of screen
	static int x=xdiv/2;//x position
	static int y=ydiv/2;//y position
	static int xlast=xdiv/2;//the x ooooposition of the last body
	static int ylast=ydiv/2;//the y position of the last body
	static boolean gameover=false;//if the game is over
	static boolean complete=false;//if stage mode is completed
	static boolean dirlock=false;//prevent from choosing 2 directions continuously
	static boolean isselecting=false;//whether selecting the stage
	static boolean iseatingSh_t=false;//during Sh_t eating mode
	static int mode=1;//the mode playing
	static int score=0;//The score of the game
	static int step=0;//steps snake took
	static int rstep=0;//[mode 2]region steps: the steps from food to food
	static int level=0;//The level of the game
	static int exp=0;  //The exp now
	static int expneed[]  ={ 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};//exp needed to go to the next level
	static int speed[]    ={500,400,300,200,150,100, 80, 70, 50, 40};//duration for each level
	static int duration;//The speed of the snake moving:every N ms
	static int gennum;//[mode 3]the number generator
	static int itemcount=0;
	//sh_t eat countdowns
	static int se_countdown=10;//how many before deciding to appear EAT!
	static int se_step=100;//how many steps left during EAT available
	static int se_p=10;//probability of falling shit
	
	JLabel lv=new JLabel();
	//constructor
	public StageHead(int mode,int initlevel,int iniscore,boolean stageselect,int maxselectable) throws InterruptedException 
	{
		this.mode=mode;
		if(mode==1)this.level=initlevel;
		dir=6;
		len=0;
		x=xdiv/2;
		y=ydiv/2;
		xlast=x;
		ylast=y;
		score=iniscore;
		step=0;
		exp=0;
		if(mode==1)duration=speed[level];
		else if(mode==2)duration=100;
		else if(mode==3)duration=300;
		else duration=500;
		gameover=false;
		complete=false;
		setAlwaysOnTop(true); //let the frame control
		ArrayList<Body> body=new ArrayList<Body>();
		addKeyListener(this);
		Random ran=new Random();
		
		//select stage
		if(mode==1 && stageselect)
		{
			StageSelect ss=new StageSelect(maxselectable,xblock,yblock);
			level=ss.getstart();
			duration=speed[level];
		}
		
		
		//stage mode constructor
		boolean checkfirst=false;//check if food is on the same pos. as head
		int fx=0,fy=0;//x and y pos. of food
		int sendinfo;
		
		Pupu pu=new Pupu(level,xdiv,ydiv,xblock,yblock);//[mode 1]
		Sh_t sh=new Sh_t(xdiv,ydiv,xblock,yblock);//[mode 3]
		Sh_tEat se=new Sh_tEat(xblock,yblock);//[mode 3]
		if(mode==3)sh.initcreate();
		if(mode==1)
		{
			pu.initcreate();
			x=pu.getstartpositionx(level);
			y=pu.getstartpositiony(level);
			while(!checkfirst||pu.check(fx,fy)==1)
			{
				fx=ran.nextInt(xdiv);
				fy=ran.nextInt(ydiv);
				if(fx==x&&fy==y)checkfirst=false;
				else checkfirst=true;
			}
			sendinfo=expneed[level]-exp;
		}
		else
		{
			while(!checkfirst)
			{
				fx=ran.nextInt(xdiv);
				fy=ran.nextInt(ydiv);
				if(fx==x&&fy==y)checkfirst=false;
				else checkfirst=true;
			}
			sendinfo=0;
		}
		Food food=new Food(mode,fx,fy,xblock,yblock,sendinfo);
		//score labal
		JLabel sc=new JLabel();
		sc.setText(Integer.toString(score));
		sc.setFont(new Font(null,Font.BOLD,15));
		sc.setVerticalAlignment(JLabel.CENTER);
		sc.setHorizontalAlignment(JLabel.CENTER);
		sc.setForeground(Color.white);
		add(sc,BorderLayout.CENTER);
		//level label
		if     (mode==1)lv.setText("ST:"+Integer.toString(level));
		else if(mode==2)lv.setText("d:"+duration);
		else if(mode==3)lv.setText("");
		lv.setFont(new Font(null,Font.PLAIN,10));
		lv.setVerticalAlignment(JLabel.TOP);
		lv.setHorizontalAlignment(JLabel.CENTER);
		lv.setForeground(Color.white);
		add(lv,BorderLayout.NORTH);
		
		
		setTitle("");
		setSize(xblock,yblock);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(x*xblock,y*yblock);
		setUndecorated(true);
		getContentPane().setBackground(Color.black);
		setAlwaysOnTop(true); //let the frame control
		setVisible(true);
		sc.setText("[3]->");
		Thread.sleep(1000);
		sc.setText("[2]->");
		Thread.sleep(1000);
		sc.setText("[1]->");
		Thread.sleep(1000);
		sc.setText(Integer.toString(score));
		while(true)
		{
			//System.out.println(iseatingSh_t+","+se_countdown+","+se_step);
			dirlock=false;
			Thread.sleep(duration);
			//get last cor.
			if(len==0)
			{
				xlast=x;
				ylast=y;
			}
			else
			{
				xlast=body.get(body.size()-1).getX();
				ylast=body.get(body.size()-1).getY();
			}
			int headbeforeX=x;//The head x pos before moving
			int headbeforeY=y;//The head y pos before moving
			//generate sh_t and Sh_tEat
			if(mode==3)
			{
				//new sh_t?
				gennum=ran.nextInt(se_p);
				if(gennum==0)
				{
					if(len==0)sh.setSh_t(x,y);
					else sh.setSh_t(body.get(body.size()-1).getX(),body.get(body.size()-1).getY());
				}
				//new eat chance?
				//TODO testing se
				gennum=ran.nextInt(4);
				if(!iseatingSh_t && se_countdown==0 && gennum==3 && !se.isexist())
				{
					//System.out.println("-");
					checkfirst=false;
					fx=0;
					fy=0;
					while(!checkfirst)
					{
						fx=ran.nextInt(xdiv);
						fy=ran.nextInt(ydiv);
						boolean getbody=false;
						for(Body tp:body)
						{
							if(fx==tp.getX() && fy==tp.getY())getbody=true;
						}
						if(sh.isexist(fx,fy))checkfirst=false;
						else if(fx==x && fy==y)checkfirst=false;
						else if(getbody)checkfirst=false;
						else checkfirst=true;
					}
					//get to eat!!
					if(checkfirst==true)
					{
						se.setPos(fx,fy);
					}
				}
			}
			//move Head
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
			else
			{
				//System.out.println("ERROR: Head      :: dir out of range");
			}
			//move Body
			//System.out.print("1");
			if(len!=0)
			{
				body.get(0).setbefore(body.get(0).getX(),body.get(0).getY());
				body.get(0).movepanel(headbeforeX,headbeforeY);
				//len 1=body 0
				for(int i=1;i<body.size();i++)
				{
					body.get(i).setbefore(body.get(i).getX(),body.get(i).getY());
					body.get(i).movepanel(body.get(i-1).getXbefore(),body.get(i-1).getYbefore());
				}
			}
			//GAMEOVER: touches the wall
			if(x>=xdiv||y>=ydiv||x<0||y<0)
			{
				gameover=true;
				break;
			}
			//GAMEOVER: touches body
			for(Body tp:body)
			{
				if(x==tp.getX()&&y==tp.getY())
					gameover=true;
			}
			//[mode 3]touches sh_t
			if(mode==3 && x>=0 && x<=xdiv &&y>=0 && y<=ydiv)
			{
				if(!iseatingSh_t)
				{
					if(sh.isexist(x,y))gameover=true;
				}
				else
				{
					if(sh.isexist(x,y))
					{
						score+=50;
						sh.clrSh_t(x,y);
						sc.setText(Integer.toString(score));
					}
				}
			}
			//GAMEOVER: touches pupu
			if(mode==1 && x>=0 && x<=xdiv &&y>=0 && y<=ydiv)
			{
				if(pu.check(x,y)==1)gameover=true;
			}
			//[mode 3]eat chance start!!
			if(mode==3 && se.isexist() && x==se.getX() && y==se.getY())
			{
				iseatingSh_t=true;
				getContentPane().setBackground(new Color(0,0,120));
				lv.setText(Integer.toString(se_step));
				se.hide();
				se_countdown=10;
			}
			//You successfully get the food!!
			if(x==food.getX()&&y==food.getY())
			{
				boolean levelup=false;
				if(mode==1)
				{
					score+=100;
					sc.setText(Integer.toString(score));
					exp++;
					food.updatenext(expneed[level]-exp);
					//level up
					if(exp>=expneed[level])
					{
						levelup=true;
						level++;
						JFrame lvup=new JFrame();
							lvup.setLocation(8*xblock,8*yblock);
							lvup.setSize(4*xblock,4*yblock);
							lvup.setUndecorated(true);
							lvup.getContentPane().setBackground(new Color(101,168,0));
							JLabel lvup_1=new JLabel("Stage Clear!!");
							lvup_1.setHorizontalAlignment(JLabel.CENTER);
							lvup_1.setFont(new Font(null,Font.BOLD,25));
							lvup_1.setForeground(Color.white);
							JLabel lvup_2=new JLabel();
							food.setVisible(false);
							if(level==10)
							{
								lvup_2.setText("Score +1000");
								score+=1000;
							}
							else
							{
								lvup_2.setText("Score +500");
								score+=500;
							}
							lvup_2.setForeground(Color.white);
							lvup_2.setHorizontalAlignment(JLabel.CENTER);
							lvup.add(lvup_1,BorderLayout.CENTER);
							lvup.add(lvup_2,BorderLayout.SOUTH);
							lvup.setVisible(true);
							Thread.sleep(3000);
							lvup.setVisible(false);
							//System.out.println("level= "+level);
						if(level>9)
						{
							complete=true;
							break;
						}	
						pu.change(level);
						exp=0;
						duration=speed[level];
						lv.setText("ST:"+Integer.toString(level));
						food.updatenext(expneed[level]-exp);
						//clear the rest "BODIES"
						for(Body tp:body)
						{
							tp.setVis(false);
						}
						body.clear();
						x=pu.getstartpositionx(level);
						y=pu.getstartpositiony(level);
						setLocation(x*xblock,y*yblock);
						xlast=x;
						ylast=y;
						sc.setText("[3]->");
						Thread.sleep(1000);
						sc.setText("[2]->");
						Thread.sleep(1000);
						sc.setText("[1]->");
						Thread.sleep(1000);
						sc.setText(Integer.toString(score));
						dir=6;
						len=0;
					}
				}
				else if(mode==2)
				{
					if(rstep<100)score+=(100+(100-rstep)*100/duration);
					else         score+=100;
					rstep=0;
					sc.setText(Integer.toString(score));
					//speed up
					if(duration>10)duration-=2;
					lv.setText("d:"+duration);
				}
				else if(mode==3)
				{
					score+=100;
					sc.setText(Integer.toString(score));
					if(!iseatingSh_t)se_countdown--;
				}
				//move food
				checkfirst=false;
				if(mode==1)
				{
					while(!checkfirst||pu.check(fx,fy)==1)
					{
						fx=ran.nextInt(xdiv);
						fy=ran.nextInt(ydiv);
						boolean flag=true;
						for(int i=0;i<body.size();i++)
						{
							if(fx==body.get(i).getX()&&fy==body.get(i).getY())
							{
								flag=false;
								break;
							}
						}
						if(flag==true)
						{
							if(fx==x&&fy==y)checkfirst=false;
							else checkfirst=true;
						}
					}
					food.setVisible(true);
					food.setFocusableWindowState(false);
					setAlwaysOnTop(true);
					setVisible(true);
				}
				else if(mode==2)
				{
					while(!checkfirst)
					{
						fx=ran.nextInt(xdiv);
						fy=ran.nextInt(ydiv);
						boolean flag=true;
						for(int i=0;i<body.size();i++)
						{
							if(fx==body.get(i).getX()&&fy==body.get(i).getY())
							{
								flag=false;
								break;
							}
						}
						if(flag==true)
						{
							if(fx==x&&fy==y)checkfirst=false;
							else checkfirst=true;
						}
					}
				}
				else if(mode==3)
				{
					while(!checkfirst||sh.isexist(fx,fy))
					{
						fx=ran.nextInt(xdiv);
						fy=ran.nextInt(ydiv);
						boolean flag=true;
						for(int i=0;i<body.size();i++)
						{
							if(fx==body.get(i).getX()&&fy==body.get(i).getY())
							{
								flag=false;
								break;
							}
						}
						if(flag==true)
						{
							if(fx==x&&fy==y)checkfirst=false;
							else checkfirst=true;
						}
					}
				}
				food.move(fx,fy,xblock,yblock);
				//new body
				if(!levelup)
				{
					body.add(len,new Body(xlast,ylast,xblock,yblock));
					len++;
				}
				setAlwaysOnTop(true);
				levelup=false;
			}
			if(gameover==true)break;
			setAlwaysOnTop(true); //let the frame control
			step++;
			rstep++;
			if(mode==3 && iseatingSh_t)
			{
				se_step--;
				lv.setText(Integer.toString(se_step));
			}
			if(mode==3 && iseatingSh_t && se_step==0)
			{
				iseatingSh_t=false;
				getContentPane().setBackground(Color.BLACK);
				lv.setText("");
				se_step=100;
				
			}
		}
		//end of while loop
		if(gameover==true)
		{
		//System.out.println("INFO :           :: GAME OVER");
		JFrame info_youlose=new JFrame("Too bad!!"){
			protected void processWindowEvent(WindowEvent e) {
				super.processWindowEvent(e);
				if (e.getID() == WindowEvent.WINDOW_CLOSING) {
					//System.out.println("INFO :           :: TERMINATED");
					System.exit(0);
				}
			}
		};
		food.setVisible(false);
		setVisible(false);
		for(Body tp:body)
		{
			tp.setVis(false);
		}
		if(mode==1)pu.change(0);
		if(mode==3)sh.clean();
		info_youlose.setLayout(new GridLayout(5,1));
		JLabel gomes1=new JLabel();
		JLabel gomes2=new JLabel();
		JLabel gomes3=new JLabel();
		JLabel gomes4=new JLabel();
		JLabel gomes5=new JLabel();
		gomes1.setText("Ohhhh.../3\\");
		gomes1.setFont(new Font(null,Font.BOLD,30));
		gomes2.setText("Score: "+score);
		gomes3.setText("Steps: "+step);
		gomes4.setText("Thank you for playing.");
		gomes5.setText("[This window will be closed in 5 seconds.]");
		
		info_youlose.add(gomes1);
		info_youlose.add(gomes2);
		info_youlose.add(gomes3);
		info_youlose.add(gomes4);
		info_youlose.add(gomes5);
		info_youlose.setLocation(6*xblock,6*yblock);
		info_youlose.setSize(8*xblock,8*yblock);
		info_youlose.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		info_youlose.setVisible(true);
		Thread.sleep(5000);
		info_youlose.setVisible(false);
		//System.out.println(level);
		//System.exit(0);
		}
		else if(complete==true)
		{
			JFrame win=new JFrame("You win!!!"){
				protected void processWindowEvent(WindowEvent e) {
					super.processWindowEvent(e);
					if (e.getID() == WindowEvent.WINDOW_CLOSING) {
						//System.out.println("INFO :           :: TERMINATED");
						System.exit(0);
					}
				}
			};
			food.setVisible(false);
			setVisible(false);
			for(Body tp:body)
			{
				tp.setVis(false);
			}
			if(mode==1)pu.change(0);
			if(mode==3)sh.clean();
			win.setLayout(new GridLayout(5,1));
			win.setLocation(6*xblock,6*yblock);
			win.setSize(8*xblock,8*yblock);
			win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			JLabel winmes1=new JLabel("Congratulations!! \\030/");
			winmes1.setFont(new Font(null,Font.BOLD,30));
			JLabel winmes2=new JLabel("Score: "+score);
			JLabel winmes3=new JLabel("Steps:"+step);
			JLabel winmes4=new JLabel("Thank you for playing!!");
			JLabel winmes5=new JLabel("[This game will be closed in 5 seconds.]");
			win.add(winmes1);
			win.add(winmes2);
			win.add(winmes3);
			win.add(winmes4);
			win.add(winmes5);
			win.setVisible(true);
			Thread.sleep(5000);
			win.setVisible(false);
		}
		//System.out.println("End of constructor:"+level);
	}
	//Keylisteners
	@Override
	public void keyPressed(KeyEvent e) {
		// if 2 dir pressed, it will still go back.
		if     (e.getKeyCode()==KeyEvent.VK_UP   ||e.getKeyChar()=='w'){if(dir!=2&&dirlock==false)dir=8;dirlock=true;}//Up
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyChar()=='d'){if(dir!=4&&dirlock==false)dir=6;dirlock=true;}//Right
		else if(e.getKeyCode()==KeyEvent.VK_DOWN ||e.getKeyChar()=='s'){if(dir!=8&&dirlock==false)dir=2;dirlock=true;}//Down
		else if(e.getKeyCode()==KeyEvent.VK_LEFT ||e.getKeyChar()=='a'){if(dir!=6&&dirlock==false)dir=4;dirlock=true;}//Left
		else if(e.getKeyChar()=='o')//speed up
		{
			if(mode==2)
			{
				if(duration>10 && duration<100)         duration--;
				else if(duration>=100 && duration<=1000)duration-=25;
				else if(duration<=10000)                duration-=100;
				lv.setText("d:"+duration);
			}
			else
			{
				if(duration>10)duration-=25;
			}
			
		}
		else if(e.getKeyChar()=='i')//speed down
		{
			if(mode==2)
			{
				if(duration>10 && duration<100) 		 duration++;
				else if(duration>=100 && duration <=1000)duration+=25;
				else if(duration<=10000)                 duration+=100;
				lv.setText("d:"+duration);
			}
			else
			{
				if(duration<10000)duration+=25;
			}
		}
		else if(e.getKeyChar()=='p')//back to original speed
		{
			if	   (mode==1)duration=speed[level];
			else if(mode==2)duration=100;
			else if(mode==3)duration=300;
		}
		//mode 3 sh_t falling probability
		else if(e.getKeyChar()=='6'){se_p=20;System.out.println("Sh_t 5%");}//5%
		else if(e.getKeyChar()=='7'){se_p=10;System.out.println("Sh_t 10%");}//Normal, 10%
		else if(e.getKeyChar()=='8'){se_p=4;System.out.println("Sh_t 25%");}//25%
		else if(e.getKeyChar()=='9'){se_p=2;System.out.println("Sh_t 50%");}//50%
		else if(e.getKeyChar()=='0'){se_p=1;System.out.println("Sh_t 100%!!!");}//100%
	}
	//get max level
	public int getlevel()
	{
		//System.out.println(level);
		return level;
	}
	@Override
	public void keyReleased(KeyEvent e) {

	}
	@Override
	public void keyTyped(KeyEvent e) {

	}
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}
}
