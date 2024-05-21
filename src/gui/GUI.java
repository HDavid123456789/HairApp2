package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import adatkezeles.Adatkezeles;
import adatkezeles.Service;
import commonComponent.SaveData;
import informacio.Informacio;
import napiforgalom.Napiforgalom;
import vasarlas.Vasarlas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI extends JPanel{
	
	static Toolkit tk= Toolkit.getDefaultToolkit();
	public static int y = (int)tk.getScreenSize().getHeight();
	public static int x = (int)tk.getScreenSize().getWidth();
	
	public static JFrame frame = new JFrame("HairApp 2");
	public static JLayeredPane lp = new JLayeredPane();
	
	
	static Menu menu[] = new Menu[4];
	
	public static void main(String args[]) {
		new GUI();
	}
	
	public GUI() {
		dataUpdate();
		
		
		
		Frame f = new Frame();
		
		menu[0]= new Adatkezeles();
		menu[1]= new Vasarlas();
		menu[2]= new Informacio();
		menu[3] = new Napiforgalom();
		
		Font font = new Font("Dialog",Font.BOLD,y/40);
		
		
		
		
		//frame.setUndecorated(true);
		frame.setSize(x/2,y/2);
		frame.setVisible(true);
		frame.setResizable(true);
		//frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		
		
		
		JPanel bg = new JPanel();
		bg.setLayout (null);
		bg.setBounds(0,0,x,y);
		bg.setBackground(new Color(30,30,30));
		
		JPanel panel_btns = new JPanel();
		panel_btns.setLayout (null);
		panel_btns.setBounds(0,0,x/7+x/25,y);
		panel_btns.setBackground(new Color(30,30,30));
		panel_btns.setBorder(new LineBorder(Color.WHITE,3));
		
		JButton button[]= new JButton[4];
		for(int i=0; i<button.length; i++) {
			button[i]= new JButton(menu[i].name());
			button[i].setBounds(x/50,y/8+(y/6*i),x/7,y/15);
			//button[i].setBackground(new Color(220,220,220));
			button[i].setFont(font);
			panel_btns.add(button[i]);
		}
		

		frame.add(lp);
		lp.add(bg,Integer.valueOf(0));
		lp.add(panel_btns,Integer.valueOf(1));
		
		for(int i=0; i<button.length; i++) {
			final int t=i;
			button[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(int i=0; i<menu.length; i++) {menu[i].exit();}
						menu[t].general();
					
				}
			});
		}
	}
	
	
	public static void dataUpdate() {
		File file = new File("mentes/nevek.txt");
		if(file.exists()) {SaveData.client=SaveData.read("mentes/nevek.txt");}
		file = new File("mentes/szolgaltatasok.txt");
		if(file.exists()) {SaveData.service=SaveData.read("mentes/szolgaltatasok.txt");}
		
		file = new File("mentes/szemelyesadatok");
		if(!file.exists()) {file.mkdir();}
		
		file = new File("mentes/napiforgalom");
		if(!file.exists()) {file.mkdir();}
		
		Service.dataupdate();
		
	}
	

}
