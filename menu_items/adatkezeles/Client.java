package adatkezeles;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import commonComponent.SaveData;
import gui.GUI;

public class Client extends SaveData{
	int x=gui.Menu.x; int y=gui.Menu.y;
	
	static JInternalFrame frame = new JInternalFrame("ÜGYFELEK");
	static DefaultListModel lm = new DefaultListModel();
	static JList list = new JList(lm);
	static JScrollPane scroll = new JScrollPane(list);
	
	Client_edit edit=new Client_edit();
	
	public Client(){
		
		frame.setLocation(x/6+x/25,y/20);
		frame.setSize(x/3+x/25,y/40*17);
		frame.setVisible(false);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		int x=frame.getWidth(),	y=frame.getHeight();
		lm.addElement("<ÚJ ÜGYFÉL HOZZÁADÁSA>");	for(int i=0; i<client.size(); i++) {lm.addElement(client.get(i));}
		
		list.setBackground(new Color(30,30,30));
		list.setForeground(Color.WHITE);
		list.setFont(new Font("Dialog",Font.BOLD,y/20));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setBorder(new EmptyBorder(10,x/25,10,10));
			
		frame.add(scroll);
		GUI.lp.add(frame,Integer.valueOf(2));
		
		
		list.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent evt) {
		    	  if (!list.getValueIsAdjusting() && list.getSelectedIndex()>=0) {
		    		  update();
		    		  edit.update((String)list.getSelectedValue(),list.getSelectedIndex());
		    		  edit.index=list.getSelectedIndex();
		    		  edit.visible(true);  
		    	  }
		      }
		    	  
		 });
		
	}
	
	void visible(boolean set) {
		frame.setVisible(set);
		frame.setLocation(x/6+x/25,y/20);
		frame.setSize(x/3+x/25,y/40*17);
		edit.visible(set);
		
	}
	
	void update(){
		frame.setSize(x/3+x/25,y/40*17-2);
	}
	
	
	static void dataupdate() {
		//int index = lm.size()-1;
		lm.removeAllElements();
		lm.addElement("<ÚJ ÜGYFÉL HOZZÁADÁSA>");
		for(int i=0; i<client.size(); i++) {lm.addElement(client.get(i));}
		
		SaveData.write("mentes/nevek.txt",client);

	}

}
