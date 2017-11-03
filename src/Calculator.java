import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class Calculator {
	private JFrame f = new JFrame();
	static Point origin = new Point();
	static Point p = new Point();
	JLabel l = new JLabel();
	JLabel procedure = new JLabel("");
	JLabel result = new JLabel("0");
	Icon icon=new ImageIcon("/img/calc.png");
	
	public void init(){
		l.setIcon(icon);
		f.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				int X,Y,S;
				String z1 = "";
				String z2 = "";
				if(z2=="=")
					procedure.setText(result.getText());
				{
					if(e.getY()>=319&&e.getY()<=384)
						Y=1;
					else if(e.getY()>=410&&e.getY()<=475)
						Y=2;
					else if(e.getY()>=502&&e.getY()<=566)
						Y=3;
					else if(e.getY()>=589&&e.getY()<=654)
						Y=4;
					else
						Y=0;
				}  //按键分行
				{
					if(e.getX()>=79&&e.getX()<=157)
						X=1;
					else if(e.getX()>=183&&e.getX()<=260)
						X=2;
					else if(e.getX()>=285&&e.getX()<=364)
						X=3;
					else if(e.getX()>=387&&e.getX()<=465)
						X=4;
					else if(e.getX()>=490&&e.getX()<=569)
						X=5;
					else
						X=0;
				}  //按键分列
				{
					if(e.getX()>=387&&e.getX()<=465&&e.getY()>=502&&e.getY()<=654)
						S=1;
					else
						S=0;
				}  //跨行加号处理
				{
					if(S==1)
					{
						procedure.setText(procedure.getText()+"+");
						result.setText("0");
					}
					else
						if(Y==1)
							if(X==1)
								z1="1";
							else if(X==2)
								z1="2";
							else if(X==3)
								z1="3";
							else if(X==4)
								procedure.setText(procedure.getText().substring(0,procedure.getText().length()-1));
							else if(X==5)
							{
								procedure.setText("");
								result.setText("0");
							}
							else;
						else if(Y==2)
							if(X==1)
								z1="4";
							else if(X==2)
								z1="5";
							else if(X==3)
								z1="6";
							else if(X==4)
							{
								procedure.setText(procedure.getText()+"*");
								result.setText("0");
							}
							else if(X==5)
							{
								procedure.setText(procedure.getText()+"/");
								result.setText("0");
							}
							else;
						else if(Y==3)
							if(X==1)
								z1="7";
							else if(X==2)
								z1="8";
							else if(X==3)
								z1="9";
							else if(X==5)
							{
								procedure.setText(procedure.getText()+"-");
								result.setText("0");
							}
							else;
						else if(Y==4)
							if(X==1)
								System.exit(0);
							else if(X==2)
								z1="0";
							else if(X==3)
								z1=".";
							else if(X==5)
							{
								z2="=";
								ScriptEngineManager manager=new ScriptEngineManager();
								ScriptEngine engine =manager.getEngineByName("js");
								try 
								{
									result.setText(engine.eval(procedure.getText()).toString());
								}
								catch (ScriptException e1) {}
							}
							else;
						else if(Y==0||X==0)
						{
						    f.addMouseListener(new MouseAdapter() {
								public void mousePressed(MouseEvent e) {
									origin.x = e.getX(); 
								    origin.y = e.getY();
									p=f.getLocationOnScreen();
								}
							});
						    f.addMouseMotionListener(new MouseMotionAdapter() {
								public void mouseDragged(MouseEvent e) {
									f.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
								}
							});
						}
				}
				if(result.getText()=="0")
					if(z1==".")
						result.setText(result.getText()+z1);
					else if(z1!="")
						result.setText(z1);
					else;
				else
					result.setText(result.getText()+z1);
					procedure.setText(procedure.getText()+z1);
			}});
		{
			procedure.setFont(new Font("Monospaced",Font.BOLD,24));
			result.setFont(new Font("Monospaced",Font.BOLD,64));
		}  //Label字体设置
		{
			procedure.setBounds(120,90,405,40);
			result.setBounds(120,130,405,80);
		}  //Label位置设置
		{
			procedure.setHorizontalAlignment(SwingConstants.RIGHT);
			result.setHorizontalAlignment(SwingConstants.RIGHT);
		}  //Label文字右对齐
		f.add(l);
		l.add(procedure);
		l.add(result);
		{
			f.setUndecorated(true);
			f.setBackground(new Color(0,0,0,0));
		}   //窗体透明
		f.pack();
		f.setVisible(true);
		f.setLocationRelativeTo(null);  //窗口屏幕中央显示
	}
	public static void  main(String[] args){
		new Calculator().init();
	}
}
