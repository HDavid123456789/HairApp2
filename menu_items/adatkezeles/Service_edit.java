package adatkezeles;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import commonComponent.Method;
import commonComponent.SaveData;
import gui.GUI;

public class Service_edit extends SaveData{
	JInternalFrame frame = new JInternalFrame();
	JPanel panel = new JPanel();
	int x=gui.Menu.x,	y=gui.Menu.y;
	int x2,y2;
	int index=0;
	String block[]= new String[5];
	
	JButton save = new JButton("MENTÉS");
	JButton save2 = new JButton("MENTÉS");
	JButton delete = new JButton("TÖRLÉS");
	JButton name[] = new JButton[5];
	JTextArea txt[] = new JTextArea[5];
	JTextArea oldtxt[] = new JTextArea[5];
	
	
	
	New n = new New(); 
	Edit e = new Edit();
	
	Service_edit(){
		frame.setLocation(x/6+x/25+x/5*2,y/40*19+2);
		frame.setSize(x/3+x/25,y/40*17);
		frame.setVisible(false);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		
		
		x2=frame.getWidth();
		y2=frame.getHeight();
		
		
		panel.setBackground(new Color(30,30,30));
		panel.setLayout(null);
		
		frame.add(panel);
		GUI.lp.add(frame,Integer.valueOf(5));
		
		
		panel.add(save);
		panel.add(save2);
		
		panel.add(delete);
		
		for(int i=0; i<5; i++) {
			name[i]=new JButton();
			txt[i]= new JTextArea();
			oldtxt[i]= new JTextArea();
			
			panel.add(name[i]);
			panel.add(txt[i]);
			panel.add(oldtxt[i]);
		}
		
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type[]= {"Rövid","Félhosszú","Hosszú","Extrahosszú"};
				String data=txt[4].getText()+";";
				boolean next=true;
				for(int i=0; i<4; i++) {if(!Method.convert(txt[i].getText(),type[i],true,frame)) {next=false;}}
				if(txt[4].getText().equals("")) {JOptionPane.showMessageDialog(frame,"A név nem lehet üres mezõ","HIBA",JOptionPane.ERROR_MESSAGE);next=false;}
				if(next){
					for(int i=0; i<4;i++) {if(txt[i].getText().length()==0) {data=data+"-;";}else{data=data+txt[i].getText()+";";}}
						if(index==0) { 
							service.add(data);
					if(write("mentes/szolgaltatasok.txt",service)) {
						JOptionPane.showMessageDialog(frame, txt[4].getText()+" \n szolgáltatás mentése sikeresen megtörtént");
					}else {
						JOptionPane.showMessageDialog(frame,"A mentés nem sikerült","HIBA",JOptionPane.ERROR_MESSAGE);
						}
					
						}else {
							service.set(index-1, data);
							if(write("mentes/szolgaltatasok.txt",service)) {
								JOptionPane.showMessageDialog(frame, txt[4].getText()+" \nszolgáltatás módosítása sikeresen megtörtént");
						
							}else {
								JOptionPane.showMessageDialog(frame,"A mentés nem sikerült","HIBA",JOptionPane.ERROR_MESSAGE);
							}
						}
					Service.dataupdate();
					for(int i=0; i<4; i++) {txt[i].setText("");}
					
					GUI.dataUpdate();
				}
			}
		});
		
		
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object[] options = {"TÖRLÉS","MÉGSEM"};
				int n = JOptionPane.showOptionDialog(frame,"Biztosan törlöd?",null, JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
				if(n==0) {
					service.remove(index-1);
					JOptionPane.showMessageDialog(frame, "a törlés sikeresen megtörtént");
					}
				Service.dataupdate();
				for(int i=0; i<4; i++) {txt[i].setText("");}
			}
		});
		
	}
	
	
	
	void update(int index,String split[]){
		
		frame.setTitle(split[0]);
		if(index==0) {n.create();}else {e.create(split);}
	}
	
	void visible(boolean set) {
		frame.setVisible(set);
		frame.setSize(x/3+x/25,y/40*17);
		frame.setLocation(x/6+x/25+x/5*2,y/40*19+2);
	}
	

	class New{	
		void create() {
			
			for(int i=0; i<5; i++) {
				oldtxt[i].setVisible(false);
			}
			delete.setVisible(false);
			
			
			save.setLocation(x2/5*2,y2/5*4);
			save.setSize(x2/5,y2/10);
			save.setFont(new Font("Dialog",Font.BOLD,y2/30));
			
			
			String type[]= {"Rövid:","Félhosszú:","Hosszú:","Extrahosszú:"};
			for(int i=0; i<4; i++) {
			name[i].setLocation(x2/50,y2/4+y2/8*i);
			name[i].setSize(x2/4,y2/10);
			name[i].setForeground(Color.white);
			name[i].setFont(new Font("Dialog",Font.BOLD,y2/30));
			name[i].setEnabled(false);
			name[i].setText(type[i]);
			
			
			txt[i].setLocation(x2/3+x2/25,y2/4+y2/8*i);
			txt[i].setSize(x2/5,y2/10);
			txt[i].setFont(new Font("Dialog",Font.BOLD,y2/15));
			}
			
			
			name[4].setLocation(x2/50,y2/4-y2/8);
			name[4].setSize(x2/4,y2/10);
			name[4].setForeground(Color.white);
			name[4].setFont(new Font("Dialog",Font.BOLD,y2/30));
			name[4].setEnabled(false);
			name[4].setText("Szolgáltatás neve:");
			
			
			txt[4].setLocation(x2/3+x2/25,y2/4-y2/8);
			txt[4].setSize(x2/2,y2/10);
			txt[4].setFont(new Font("Dialog",Font.BOLD,y2/15));
			
			
			
			//////////////
			
		}
	}
	
	
	class Edit{
		
		void create(String split[]) {
			for(int i=0; i<5; i++) {
				oldtxt[i].setVisible(true);
			}
			delete.setVisible(true);
			
			save.setLocation(x2/5*3,y2/5*4);
			save.setSize(x2/5,y2/10);
			save.setFont(new Font("Dialog",Font.BOLD,y2/30));
			
			delete.setLocation(x2/5,y2/5*4);
			delete.setSize(x2/5,y2/10);
			delete.setFont(new Font("Dialog",Font.BOLD,y2/30));
			
			String type[]= {"Szolgáltatás neve:","Rövid:","Félhosszú:","Hosszú:","Extrahosszú:"};
			
			for(int i=0; i<5; i++) {
				name[i].setLocation(x2/50,y2/4+y2/8*(i-1));
				name[i].setSize(x2/9*2,y2/15);
				name[i].setForeground(Color.white);
				name[i].setFont(new Font("Dialog",Font.BOLD,y2/35));
				name[i].setEnabled(false);
				name[i].setText(type[i]);
				
				
				txt[i].setLocation(x2/4+x2/9*2+x2/50,y2/4+y2/8*(i-1));
				txt[i].setSize(x2/2-x2/75,y2/15);
				txt[i].setFont(new Font("Dialog",Font.BOLD,y2/25));
				
				
				oldtxt[i].setLocation(x2/4,y2/4+y2/8*(i-1));
				oldtxt[i].setSize(x2/9*2,y2/15);
				oldtxt[i].setFont(new Font("Dialog",Font.BOLD,y2/25));
				oldtxt[i].setText(split[i]);
				oldtxt[i].setEnabled(false);
			}
			
		}
	}
}
