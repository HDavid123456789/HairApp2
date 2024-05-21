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

public class Service extends SaveData{
int x=gui.Menu.x; int y=gui.Menu.y;
	
	static JInternalFrame frame = new JInternalFrame("SZOLGÁLTATÁSOK");
	static DefaultListModel lm = new DefaultListModel();
	static JList list = new JList(lm);
	static JScrollPane scroll = new JScrollPane(list);
	
	Service_edit edit=new Service_edit();
	
	public Service(){
		
		frame.setLocation(x/6+x/25+x/5*2,y/20);
		frame.setSize(x/3+x/25,y/40*17);
		frame.setVisible(false);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		int x=frame.getWidth(),	y=frame.getHeight();
		lm.addElement("<ÚJ SZOLGÁLTATÁS HOZZÁADÁSA>");	for(int i=0; i<service.size(); i++) {
			String split[]=service.get(i).split(";");
			lm.addElement(split[0]);
		}
		
		list.setBackground(new Color(30,30,30));
		list.setForeground(Color.WHITE);
		list.setFont(new Font("Dialog",Font.BOLD,y/20));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setBorder(new EmptyBorder(10,x/25,10,10));
			
		frame.add(scroll);
		GUI.lp.add(frame,Integer.valueOf(3));
		
		
		list.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent evt) {
		    	  if (!list.getValueIsAdjusting() && list.getSelectedIndex()>=0) {
		    		  update();
		    		  String block[];
		    		  if(list.getSelectedIndex()-1>=0) {
		    			  block=service.get(list.getSelectedIndex()-1).split(";");
		    		  }else{
		    			  block=new String[1];
		    			  block[0]=(String)list.getSelectedValue();
		    			  
		    		  }
		    		  edit.update(list.getSelectedIndex(),block);
		    		  edit.block=block;
		    		  
		    		  edit.index=list.getSelectedIndex();
		    		  
		    		  edit.visible(true);  
		    	  }
		      }
		    	  
		 });
		
	}
	
	void visible(boolean set) {
		frame.setVisible(set);
		frame.setLocation(x/6+x/25+x/5*2,y/20);
		frame.setSize(x/3+x/25,y/40*17);
		edit.visible(set);
	}
	
	void update(){
		frame.setSize(x/3+x/25,y/40*17-2);
	}
	
	
	public static void dataupdate() {
		lm.removeAllElements();
		lm.addElement("<ÚJ SZOLGÁLTATÁS HOZZÁADÁSA>");
		
		for(int i=0; i<service.size(); i++) {
			String split[]=service.get(i).split(";");
			lm.addElement(split[0]);}
		
		SaveData.write("mentes/szolgaltatasok.txt",service);
	}
}
