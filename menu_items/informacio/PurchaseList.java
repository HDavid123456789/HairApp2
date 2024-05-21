package informacio;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import commonComponent.SaveData;
import gui.GUI;

public class PurchaseList {
	static JInternalFrame frame = new JInternalFrame();
	JPanel panel = new JPanel();
	
	DefaultListModel lm = new DefaultListModel();
	JList list = new JList(lm);
	JScrollPane scroll = new JScrollPane(list);
	JButton client = new JButton();
	static int x =gui.GUI.x; static int y=gui.GUI.y;
	
	PurchaseList(){
		frame.setLocation(x/6+x/25+x/5*2,y/20);
		frame.setSize(x/3+x/25,y/20*17);
		frame.setVisible(false);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		int x=frame.getWidth(); int y=frame.getHeight();
		
		client.setBounds(x/5+x/10,y/50,x/5*2,y/15);
		client.setEnabled(false);
		client.setFont(new Font("Dialog",Font.BOLD,y/40));
		
		panel.setBackground(new Color(30,30,30));
		panel.setLayout(null);
		
		list.setBackground(new Color(30,30,30));
		list.setForeground(Color.WHITE);
		list.setFont(new Font("Dialog",Font.BOLD,y/40));
		scroll.setBounds(x/50,y/10,x-x/15,y/5*4);
		panel.add(client);
		panel.add(scroll);
		frame.add(panel);
		GUI.lp.add(frame,Integer.valueOf(3));
	}
	
	void visible(boolean set, String name) {
		frame.setLocation(x/6+x/25+x/5*2,y/20);
		frame.setSize(x/3+x/25,y/20*17);
		frame.setVisible(set);
		frame.setTitle(name);
		client.setText(name);
		update(name);
	}
	
	static void visible(boolean set) {
		frame.setLocation(x/6+x/25+x/5*2,y/20);
		frame.setSize(x/3+x/25,y/20*17);
		frame.setVisible(set);
	}
	
	void update(String name) {
		ArrayList<String>lines = new ArrayList<String>();
		SaveData.read("mentes/szemelyesadatok/"+name+"/vetel.txt");
		list(name);
		
	}
	
	void list (String name) {
		String date="";
		ArrayList<String> list=SaveData.read("mentes/szemelyesadatok/"+name+"/vetel.txt");
		for(int i=list.size()-1; i>=0; i--) {
			String split[]=list.get(i).split(";");
			String dateSplit[]=split[0].split(" ");
			if(split.length==3) {
				if(!date.equals(dateSplit[0])) {
					lm.addElement(dateSplit[0]);
					date=dateSplit[0];
				}
				lm.addElement("  "+dateSplit[1]);
			}else {
				lm.addElement("     "+split[0] +" - "+split [2]);
			}
			
		}
	}
	
}
