package vasarlas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import commonComponent.SaveData;
import gui.GUI;

public class Select extends SaveData{
	
	static int x=gui.Menu.x; static int y=gui.Menu.y;
	
	static JInternalFrame frame = new JInternalFrame();
	static DefaultListModel lm = new DefaultListModel();
	static JList list = new JList(lm);
	static JScrollPane scroll = new JScrollPane(list);
	static JButton button = new JButton();
	static JPanel panel = new JPanel();
	
	static SelectService sService= new SelectService();
	
	public Select(){
		frame.setLocation(x/6+x/25+x/5*2,y/40*19+2);
		frame.setSize(x/3+x/25,y/20*17);
		frame.setVisible(false);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		int x=frame.getWidth(),	y=frame.getHeight();
		for(int i=0; i<service.size(); i++) {
			String split[]=service.get(i).split(";");
			lm.addElement(split[0]);}
		lm.addElement("<< egyéb (pl. rendelt termék) >>");
		
		list.setBackground(new Color(30,30,30));
		list.setForeground(Color.WHITE);
		list.setFont(new Font("Dialog",Font.BOLD,y/40));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setBorder(new EmptyBorder(10,x/25,10,10));
		
		
		button.setBounds(x/10,y/50,x/5*4,y/20);
		button.setEnabled(false);
		button.setFont(new Font("Dialog",Font.BOLD,y/50));
		
		
		scroll.setBounds(0,y/10,x-x/50,y/10*9-x/25);
		panel.add(scroll);
		panel.add(button);
		
		panel.setLayout(null);
		panel.setBackground(new Color(30,30,30));
		
		frame.add(panel);
		GUI.lp.add(frame,Integer.valueOf(3));
		
		
		
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				if (!list.getValueIsAdjusting() && list.getSelectedIndex()>=0) {
					frame.setVisible(false);
		    		sService.visible(frame.getTitle(),(String) list.getSelectedValue());
		    		if(list.getSelectedIndex()==service.size()) {
		    			sService.selectOthers(frame.getTitle(),(String) list.getSelectedValue());
		    		}else {
		    			sService.selecetService(service.get(list.getSelectedIndex()),frame.getTitle());
		    		}
				}
			} 	  
		 });
		
	}
	
	static void visible(boolean set) {
		frame.setVisible(set);
		frame.setLocation(x/6+x/25+x/5*2,y/20);
		frame.setSize(x/3+x/25,y/20*17);
		change();
	}
	
	static void change() {
		lm.removeAllElements();
		for(int i=0; i<service.size(); i++) {
			String split[]=service.get(i).split(";");
			lm.addElement(split[0]);}
		lm.addElement("<< egyéb (pl. rendelt termék) >>");
		
	}
	
	void title(String txt) {
		frame.setTitle(txt);
		button.setText(txt);
	}
}
