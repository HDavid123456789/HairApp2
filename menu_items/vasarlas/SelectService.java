package vasarlas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import commonComponent.Method;
import gui.GUI;

public class SelectService {
	
	int x=gui.GUI.x,y=gui.GUI.y;
	
	static JInternalFrame frame = new JInternalFrame();
	static JButton button[] = new JButton[5];
	static JButton back=new JButton();
	static JButton count = new JButton();
	static JButton title = new JButton();
	static JTextField text = new JTextField();
	static JPanel panel = new JPanel();
	static JButton labelButton = new JButton();
	static JButton sumButton = new JButton();
	static JTextField labelTxt = new JTextField();
	static JTextField sumTxt = new JTextField();
	static JButton next = new JButton();
	
	String type[]= {"Rövid","Félhosszú","Hosszú","Extrahosszú"};
	Font font =new Font("Dialog",Font.BOLD,y/55);
	
	AmountList AList = new AmountList();
	
	SelectService(){
		frame.setLocation(x/6+x/25+x/5*2,y/20);
		frame.setSize(x/3+x/25,y/20*17);
		
		frame.setVisible(false);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		panel.setLayout(null);
		panel.setBackground(new Color(30,30,30));
		
		int x=frame.getWidth(),	y=frame.getHeight();
		font =new Font("Dialog",Font.BOLD,y/55);
		
		title.setBounds(x/100,y/50,x/5*3,y/20);
		title.setEnabled(false);
		title.setFont(new Font("Dialog",Font.BOLD,y/55));
		
		for(int i=0; i<button.length; i++) {button[i]=new JButton(); panel.add(button[i]);}
		
		panel.add(title);
		panel.add(count);
		panel.add(text);
		panel.add(back);
		panel.add(labelButton);
		panel.add(labelTxt);
		panel.add(sumButton);
		panel.add(sumTxt);
		panel.add(next);
		
		frame.add(panel);
		GUI.lp.add(frame,Integer.valueOf(3));
		
		
		for(int i=0; i<button.length; i++) {
			final int t=i;
			button[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(Method.convert(text.getText(),"db",false,frame)) {
						AList.visible(true,title.getText());
						SearchName.visible(false);
						AList.addElement(title.getText(),button[0].getText(),type[t-1],button[t].getText(),text.getText(),true);
						
					
						visible(false);
						Select.visible(true);
					}
				}
			});
		}
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visible(false);
				Select.visible(true);
			}
		});
		
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Method.convert(text.getText(),"Az összeg értéke",false,frame)) {
					AList.visible(true,title.getText());
					AList.addElement(title.getText(),labelTxt.getText(),"egyedi címke","  "+sumTxt.getText(),text.getText(),false);
					SearchName.visible(false);
					visible(false);
					Select.visible(true);
				}
			}
		});
		
		
	}
	
	static void visible(boolean set) {
		frame.setVisible(set);
	}
	
	void visible(String client, String service) {
		frame.setVisible(true);
		frame.setTitle(client+"  >>  "+ service);		
		frame.setLocation(x/6+x/25+x/5*2,y/20);
		frame.setSize(x/3+x/25,y/20*17);
		
	}
	
	void selecetService(String s,String client) {
		String split[]=s.split(";");
		int x=frame.getWidth(),	y=frame.getHeight();
		
		label(client,split[0],font);
		
		for(int i=1; i<button.length;i++) {
			button[i].setVisible(true);
			button[i].setBounds(x/8,y/25*3+y/10*i,x/2,y/20);
			button[i].setFont(font);
			if(!split[i].equals("-")) {
				button[i].setText(type[i-1]+"  "+split[i]+" ft");
				button[i].setEnabled(true);
			}else {
				button[i].setEnabled(false);
				button[i].setText(type[i-1]+": - ");
			}
		}
		
		count.setBounds(x/8,y/25*3+y/10*5,x/10,y/20);
		count.setFont(font);
		count.setText("db");
		count.setEnabled(false);
		
		text.setText("1");
		text.setFont(font);
		text.setBounds(x/8+x/10+x/50,y/25*3+y/10*5,x/5,y/20);
		
		labelButton.setVisible(false);
		labelTxt.setVisible(false);
		next.setVisible(false);
		sumTxt.setVisible(false);
		sumButton.setVisible(false);
		
	}
	
	
	void selectOthers(String client, String service) {
		label(client,service,font);
		
		labelButton.setVisible(true);
		labelTxt.setVisible(true);
		sumTxt.setVisible(true);
		sumButton.setVisible(true);
		next.setVisible(true);
		
		for(int i=1; i<button.length;i++) {button[i].setVisible(false);}
		int x=frame.getWidth(),	y=frame.getHeight();
		
		labelButton.setBounds(x/10,y/25*3+y/10,x/5,y/20);
		labelButton.setFont(font);
		labelButton.setEnabled(false);
		labelButton.setText("címke:");
		
		sumButton.setBounds(x/10,y/25*3+y/5,x/5,y/20);
		sumButton.setFont(font);
		sumButton.setEnabled(false);
		sumButton.setText("összeg (ft):");
		
		labelTxt.setBounds(x/10+x/5+x/50,y/25*3+y/10,x/5*2,y/20);
		labelTxt.setFont(font);
		
		sumTxt.setBounds(x/10+x/5+x/50,y/25*3+y/5,x/5*2,y/20);
		sumTxt.setFont(font);	
		
		count.setBounds(x/10,y/25*3+y/10*3,x/5,y/20);
		count.setFont(font);
		count.setText("db:");
		count.setEnabled(false);
		
		text.setBounds(x/10+x/5+x/25,y/25*3+y/10*3,x/5,y/20);
		text.setText("1");
		text.setFont(font);
		
		next.setBounds(x/5+x/25,y/25*3+y/10*4+y/50,x/5,y/20);
		next.setFont(font);
		next.setText("TOVÁBB");
		
		
		
	}
	
	
	
	void label(String client, String service,Font font) {
		int x=frame.getWidth(),	y=frame.getHeight();
		title.setFont(font);
		title.setText(client);
		title.setHorizontalAlignment(SwingConstants.LEFT);
		
		button[0].setBounds(x/100,y/25*2,x/5*2,y/20);
		button[0].setEnabled(false);
		button[0].setText(service);
		button[0].setFont(font);
		button[0].setHorizontalAlignment(SwingConstants.LEFT);
		
		back.setText("VISSZA");
		back.setBounds(x/5*4-x/25,y/10*9,x/5,y/20);
		back.setFont(font);
	}
	
	
	
	
}
