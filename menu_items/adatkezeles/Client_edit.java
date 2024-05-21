package adatkezeles;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import commonComponent.SaveData;
import gui.GUI;

public class Client_edit extends SaveData{
	JInternalFrame frame = new JInternalFrame();
	JPanel panel = new JPanel();
	int x=gui.Menu.x,	y=gui.Menu.y;
	int x2,y2;
	int index=0;
	
	JButton save = new JButton("MENTÉS");
	JButton save2 = new JButton("MENTÉS");
	JButton delete = new JButton("TÖRLÉS");
	JButton name = new JButton();
	JButton oldname = new JButton();
	JTextArea txt = new JTextArea();
	JTextArea oldtxt = new JTextArea();
	
	New n = new New(); 
	Edit e = new Edit();
	
	Client_edit(){
		frame.setLocation(x/6+x/25,y/40*19+2);
		frame.setSize(x/3+x/25,y/40*17);
		frame.setVisible(false);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		x2=frame.getWidth();
		y2=frame.getHeight();
		
		
		panel.setBackground(new Color(30,30,30));
		panel.setLayout(null);
		
		frame.add(panel);
		GUI.lp.add(frame,Integer.valueOf(4));
		
		panel.add(name);
		panel.add(save);
		panel.add(save2);
		panel.add(txt);
		panel.add(oldname);
		panel.add(oldtxt);
		panel.add(delete);
		
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt.getText().equals("")) {
					JOptionPane.showMessageDialog(frame,"A név nem lehet üres mezõ","HIBA",JOptionPane.ERROR_MESSAGE);
				}else {
					if(index==0) {
						client.add(txt.getText());
						if(write("mentes/nevek.txt",client)) {
							JOptionPane.showMessageDialog(frame, txt.getText()+" \n név mentése sikeresen megtörtént");
							newFolder("szemelyesadatok/"+txt.getText());
						}else {
							JOptionPane.showMessageDialog(frame,"A mentés nem sikerült","HIBA",JOptionPane.ERROR_MESSAGE);
						}
					
					}else {
						client.set(index-1, txt.getText());
						if(write("mentes/nevek.txt",client)) {
						JOptionPane.showMessageDialog(frame, oldtxt.getText()+" -> "+txt.getText()+" \nnév módosítása sikeresen megtörtént");
						renameFolder("szemelyesadatok/"+oldtxt.getText(),"szemelyesadatok/"+txt.getText());
					
						}else {
							JOptionPane.showMessageDialog(frame,"A mentés nem sikerült","HIBA",JOptionPane.ERROR_MESSAGE);
						}
					}
					Client.dataupdate();
					txt.setText("");
					GUI.dataUpdate();
				}
			}
		});
		
		
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object[] options = {"TÖRLÉS","MÉGSEM"};
				int n = JOptionPane.showOptionDialog(frame,"Biztosan törlöd?",null, JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
				if(n==0) {
					client.remove(index-1);
					JOptionPane.showMessageDialog(frame, "a törlés sikeresen megtörtént");
					}
				Client.dataupdate();
				txt.setText("");
			}
		});
		
	}
	
	
	
	void update(String name,int index){
		frame.setTitle(name);
		if(index==0) {n.create();}else {e.create(name);}
	}
	
	void visible(boolean set) {
		frame.setVisible(set);
		frame.setSize(x/3+x/25,y/40*17);
		frame.setLocation(x/6+x/25,y/40*19+2);
	}
	

	class New{	
		void create() {
			oldtxt.setVisible(false);
			oldname.setVisible(false);
			delete.setVisible(false);
			
			
			save.setLocation(x2/5*2,y2/5*3);
			save.setSize(x2/5,y2/10);
			save.setFont(new Font("Dialog",Font.BOLD,y2/30));
			
			
			name.setLocation(x2/50,y2/3);
			name.setSize(x2/8,y2/10);
			name.setForeground(Color.white);
			name.setFont(new Font("Dialog",Font.BOLD,y2/30));
			name.setEnabled(false);
			name.setText("NÉV:");
			
			
			txt.setLocation(x2/5,y2/3);
			txt.setSize(x2/3*2,y2/10);
			txt.setFont(new Font("Dialog",Font.BOLD,y2/15));
			
		}
	}
	
	
	class Edit{
		
		void create(String old) {
			oldtxt.setVisible(true);
			oldname.setVisible(true);
			delete.setVisible(true);
			
			save.setLocation(x2/5*3,y2/5*4);
			save.setSize(x2/5,y2/10);
			save.setFont(new Font("Dialog",Font.BOLD,y2/30));
			
			delete.setLocation(x2/5,y2/5*4);
			delete.setSize(x2/5,y2/10);
			delete.setFont(new Font("Dialog",Font.BOLD,y2/30));
			
			
			name.setLocation(x2/50,y2/5);
			name.setSize(x2/8,y2/10);
			name.setForeground(Color.white);
			name.setFont(new Font("Dialog",Font.BOLD,y2/30));
			name.setEnabled(false);
			name.setText("ÚJ NÉV");
			
			
			oldname.setLocation(x2/50,y2/5*2);
			oldname.setSize(x2/6,y2/10);
			oldname.setForeground(Color.white);
			oldname.setFont(new Font("Dialog",Font.BOLD,y2/30));
			oldname.setEnabled(false);
			oldname.setText("RÉGI NÉV");
			
			txt.setLocation(x2/5,y2/5);
			txt.setSize(x2/3*2,y2/10);
			txt.setFont(new Font("Dialog",Font.BOLD,y2/15));
			
			
			oldtxt.setLocation(x2/5,y2/5*2);
			oldtxt.setSize(x2/3*2,y2/10);
			oldtxt.setFont(new Font("Dialog",Font.BOLD,y2/15));
			oldtxt.setText(old);
			oldtxt.setEnabled(false);
			
		}
	}
	
	
}
