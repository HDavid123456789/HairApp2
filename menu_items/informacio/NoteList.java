package informacio;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import commonComponent.Method;
import commonComponent.SaveData;
import gui.GUI;
import vasarlas.Select;

public class NoteList {
	static JInternalFrame frame = new JInternalFrame();
	JPanel panel = new JPanel();
	
	DefaultListModel lm = new DefaultListModel();
	JList list = new JList(lm);
	JScrollPane scroll = new JScrollPane(list);
	JButton client = new JButton();
	JButton note = new JButton();
	JButton back = new JButton();
	JTextArea txt = new JTextArea ();
	JScrollPane scrollTxt = new JScrollPane (txt);
	
	static int x =gui.GUI.x; static int y=gui.GUI.y;
	
	NoteList(){
		frame.setLocation(x/6+x/25,y/20);
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
		scroll.setBounds(x/50,y/10,x-x/15,y/10*7);
		
		note.setBounds(x/5+x/10,y/20*17,x/5*2,y/15);
		note.setText("Jegyzet hozzáadása");
		note.setFont(new Font("Dialog",Font.BOLD,y/40));
		
		back.setVisible(false);
		back.setBounds(x/5*3,y/5*3,x/5,y/15);
		back.setText("vissza");
		back.setFont(new Font("Dialog",Font.BOLD,y/40));
		
		scrollTxt.setBounds(x/50,y/10,x-x/15,y/5*2);
		scrollTxt.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollTxt.setVisible(false);
		txt.setFont(new Font("Dialog",Font.BOLD,y/30));
		
		panel.add(scrollTxt);
		panel.add(back);
		panel.add(note);
		panel.add(client);
		panel.add(scroll);
		frame.add(panel);
		GUI.lp.add(frame,Integer.valueOf(3));
		
		
		note.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(note.getText().equals("Jegyzet hozzáadása")) {
					note.setText("Jegyzet mentése");
					note.setBounds(x/10,y/5*3,x/5*2,y/15);
					scroll.setVisible(false);
					back.setVisible(true);
					scrollTxt.setVisible(true);
				}else {
					note.setBounds(x/5+x/10,y/20*17,x/5*2,y/15);
					note.setText("Jegyzet hozzáadása");
					update(client.getText());
					back.setVisible(false);
					scrollTxt.setVisible(false);
					scroll.setVisible(true);
					write();
				}
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scroll.setVisible(true);
				note.setBounds(x/5+x/10,y/20*17,x/5*2,y/15);
				note.setText("Jegyzet hozzáadása");
				scrollTxt.setVisible(false);
				back.setVisible(false);
			}
		});
		
	}
	
	void visible(boolean set, String name) {
		frame.setLocation(x/6+x/25,y/20);
		frame.setSize(x/3+x/25,y/20*17);
		frame.setVisible(set);
		frame.setTitle(name);
		client.setText(name);
		
		note.setBounds(x/5+x/10,y/20*17,x/5*2,y/15);
		note.setText("Jegyzet hozzáadása");
		update(client.getText());
		back.setVisible(false);
		scrollTxt.setVisible(false);
		scroll.setVisible(true);
		
		update(name);
	}
	
	static void visible(boolean set) {
		frame.setLocation(x/6+x/25+x/5*2,y/20);
		frame.setSize(x/3+x/25,y/20*17);
		frame.setVisible(set);
	}
	
	void update(String name) {
		ArrayList<String>lines = new ArrayList<String>();
		SaveData.read("mentes/szemelyesadatok/"+name+"/megjegyzes.txt");
		list(name);
		
	}
	
	void list (String name) {
		String date="";
		ArrayList<String> list=SaveData.read("mentes/szemelyesadatok/"+name+"/megjegyzes.txt");
		
		lm.removeAllElements();
		if(list.size()==0) {
			lm.addElement("  Még egy elem sincs hozzáadva");
		}
		
		boolean newNote=true;
		for(int i=0; i<list.size(); i++) {
			if(newNote) {
				String split[]=list.get(i).split(" ");
				if(!date.equals(split[0])) {
					lm.addElement(split[0]);
					date=split[0];
				}
				lm.addElement("  "+split[1]);
				newNote=false;
			}else {
				if(list.get(i).equals("$")) {
					newNote=true;
				}else {
					lm.addElement("       "+list.get(i));
				}
			}
			
		}
	}
	
	
	void write() {
		String name=client.getText();
		ArrayList<String> list = new ArrayList<String>();
		list.add(Method.date("yyyy-MM-dd HH:mm:ss"));
		list.add(txt.getText());
		list.add("$");
		SaveData.writeAndRead("mentes/szemelyesadatok/"+name+"/megjegyzes.txt",list);
	}
	
}
