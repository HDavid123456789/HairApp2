package vasarlas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import adatkezeles.Client_edit;
import commonComponent.SaveData;
import gui.GUI;

public class SearchName extends SaveData{
	
	static int x=gui.Menu.x; static int y=gui.Menu.y;
	
	static JInternalFrame frame = new JInternalFrame("KERESENDÕ NEVEK");
	static DefaultListModel lm = new DefaultListModel();
	static JList list = new JList(lm);
	static JScrollPane scroll = new JScrollPane(list);
	static JTextArea search = new JTextArea();
	static JButton button = new JButton();
	static JPanel panel = new JPanel();
	
	static ArrayList<String> names = new ArrayList<String>();
	
	Select select = new Select();
	
	public SearchName(){
		names=client;
		frame.setLocation(x/6+x/25,y/20);
		frame.setSize(x/3+x/25,y/20*17);
		frame.setVisible(false);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		int x=frame.getWidth(),	y=frame.getHeight();
		
		search.setBounds(x/50,y/50,x/3*2,y/20);
		search.setFont(new Font("Dilaog",Font.BOLD,y/30));
		
		button.setBounds(x/25+x/3*2,y/50,x/5,y/20);
		button.setEnabled(false);
		button.setText("KERESÉS");
		button.setFont(new Font("Dialog",Font.BOLD,y/50));
		
		for(int i=0; i<names.size(); i++) {lm.addElement(client.get(i));}
		
		list.setBackground(new Color(30,30,30));
		list.setForeground(Color.WHITE);
		list.setFont(new Font("Dialog",Font.BOLD,y/40));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setBorder(new EmptyBorder(10,x/25,10,10));
			
		scroll.setBounds(0,y/10,x-x/50,y/10*9-x/25);
		
		
		panel.add(search);
		panel.add(scroll);
		panel.add(button);
		
		panel.setLayout(null);
		panel.setBackground(new Color(30,30,30));
		//panel.setBounds(0,0,x,y);
		
		frame.add(panel);
		GUI.lp.add(frame,Integer.valueOf(2));
		
		
		list.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent evt) {
		    	  if (!list.getValueIsAdjusting() && list.getSelectedIndex()>=0) {
		    		  select.visible(true);
		    		  select.title((String) list.getSelectedValue());
		    	  }
		      }
		    	  
		 });
		
		search.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				change(search.getText());
			}
		});
		
	}
	
	static void visible(boolean set) {
		frame.setVisible(set);
		frame.setLocation(x/6+x/25,y/20);
		frame.setSize(x/3+x/25,y/20*17);
		
		GUI.dataUpdate();
		change();
	}
	
	
	void change(String txt) {
		names=search(txt,client);
		lm.removeAllElements();
		for(int i=0; i<names.size(); i++) {lm.addElement(names.get(i));}
		
	}
	
	
	static void change() {
		names=client;
		lm.removeAllElements();
		for(int i=0; i<names.size(); i++) {lm.addElement(names.get(i));}
		
	}
	
	
	ArrayList<String> search(String search,ArrayList<String> list){
		ArrayList<String> select= new ArrayList<String>();
		if(search.length()==0) {return list;}
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).toLowerCase().contains(search.toLowerCase())) {
				select.add(list.get(i));
			}else {
			}
		}
		
		return select;
	}
	
}
