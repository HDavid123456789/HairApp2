package napiforgalom;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import commonComponent.Method;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import gui.GUI;

public class InternalFrame {
	
	Calculating c = new Calculating();
	
	static int x=gui.Menu.x; static int y=gui.Menu.y;
	
	JInternalFrame frame = new JInternalFrame("NAPI FORGALOM");
	JPanel panel = new JPanel();
	DefaultListModel lm = new DefaultListModel();
	JList list = new JList(lm);
	JScrollPane scroll = new JScrollPane(list);
	JButton minDateBtn = new JButton();
	JButton maxDateBtn = new JButton("");
	JTextArea minDateTxt = new JTextArea();
	JTextArea maxDateTxt = new JTextArea();
	JButton startingBtn = new JButton();
	JTextArea startingTxt = new JTextArea();
	JButton search = new JButton();
	JTextArea sum = new JTextArea();
	JTextArea sum2 = new JTextArea();
	
	int many=0;
	
	ArrayList<String> lmList = new ArrayList<String>();
	
	
	
	public InternalFrame() {
		frame.setLocation(x/6+x/25,y/20);
		frame.setSize(x/3*2+x/25*2,y/20*17);
		frame.setVisible(false);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		int x=frame.getWidth(),	y=frame.getHeight();
		Font font = new Font("Dialog",Font.BOLD,y/35);
		
		startingTxt.setText("0");
		
		minDateBtn.setBounds(x/3*2+x/25,y/10+y/20+y/50,x/4,y/20);
		minDateBtn.setEnabled(false);
		minDateBtn.setFont(font);
		minDateBtn.setText("-TÓL (éééé-hh-nn)");
		
		minDateTxt.setBounds(x/3*2+x/25,y/10,x/5,y/20);
		minDateTxt.setFont(font);
		minDateTxt.setText(Method.date("yyyy-MM-dd"));
		
		
		maxDateBtn.setBounds(x/3*2+x/25,y/5+y/10+y/20+y/50,x/4,y/20);
		maxDateBtn.setEnabled(false);
		maxDateBtn.setFont(font);
		maxDateBtn.setText("-IG (éééé-hh-nn)");
		
		maxDateTxt.setBounds(x/3*2+x/25,y/5+y/10,x/5,y/20);
		maxDateTxt.setFont(font);
		maxDateTxt.setText(Method.date("yyyy-MM-dd"));
		
		
		Font font2 = new Font("Dialog",Font.BOLD,y/30);
		
		startingBtn.setBounds(x/3*2+x/25,y/5+y/10+y/20+y/50+y/7,x/4,y/15);
		startingBtn.setText("Nyitóösszeg (ft)");
		startingBtn.setFont(font2);
		startingBtn.setEnabled(false);
		
		startingTxt.setBounds(x/3*2+x/25,y/5+y/10*2+y/20+y/50+y/7,x/4,y/15);
		startingTxt.setFont(new Font("Dialog",Font.BOLD,y/20));
		
		search.setText("Keresés");
		search.setBounds(x/3*2+x/25,y/5+y/10*2+y/20+y/50+y/7*2,x/4,y/15);
		search.setFont(font2);
		
		list.setBackground(new Color(30,30,30));
		list.setFont(font2);
		list.setForeground(Color.WHITE);
		
		sum.setBackground(new Color(50,50,50));
		sum.setBounds(x/50,y-y/5,x/3,y/10);
		sum.setFont(new Font("Dialog",Font.BOLD,y/40));
		sum.setForeground(Color.WHITE);
		
		sum2.setBackground(new Color(50,50,50));
		sum2.setBounds(x/25+x/3,y-y/5,x/3-x/50,y/10);
		sum2.setFont(new Font("Dialog",Font.BOLD,y/40));
		sum2.setForeground(Color.WHITE);
		
		panel.add(sum);
		panel.add(sum2);
		
		panel.add(minDateTxt);
		panel.add(minDateBtn);
		
		panel.add(maxDateTxt);
		panel.add(maxDateBtn);
		
		panel.add(search);
		
		panel.add(startingBtn);
		panel.add(startingTxt);
		
		panel.setBackground(new Color(30,30,30));
		panel.setLayout(null);
		scroll.setBounds(x/50,y/50,x/3*2,y-y/4);
		panel.add(scroll);
		frame.add(panel);
		GUI.lp.add(frame,Integer.valueOf(4));
		
		startingTxt.setText(c.readStartDay()+"");
		update();
		
		
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(input(minDateTxt.getText(),"Kezdõdátum") && input(maxDateTxt.getText(),"Dátum vége") && Method.convert(startingTxt.getText(),"Napnyitás",false,frame)) {
					update();
					c.writeStartDay(many);
				}
				
			}
		});
	}
	
	boolean input(String date, String error) {
		String split[]=date.split("-");
		if(split.length==3) {
			if(Method.convert(split[0],error,false,frame) && Method.convert(split[1],error,false,frame) &&Method.convert(split[2],error,false,frame)){
				if(split[0].length()==4 && split[1].length()==2 && split[2].length()==2) {
					return true;
				}else {
					JOptionPane.showMessageDialog(frame,error+" - ("+date+") nem felel meg a kritériumnak","HIBA",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		JOptionPane.showMessageDialog(frame,error+" - ("+date+") nem felel meg a kritériumnak","HIBA",JOptionPane.ERROR_MESSAGE);
		return false;
	}
	
	void update() {
		lmList=c.list(minDateTxt.getText(),maxDateTxt.getText());
		
		lm.removeAllElements();
		for(int i=0; i<lmList.size()-2; i++) {
			lm.addElement(lmList.get(i));
		}
		many=Integer.parseInt(startingTxt.getText());
		
		int with=Integer.parseInt(lmList.get(lmList.size()-2));
		int without=Integer.parseInt(lmList.get(lmList.size()-1));
		
		sum.setText("ÖSSZES BEVÉTEL: \n termékekkel: "+with + "ft \n termékek nélkül: "+(without)+" ft ");
		sum2.setText("Kasszában lévõ összeg: \n "+(with+many)+" ft");
		
		
	}
	
	
	
	
	
	
	void visible(boolean set) {
		frame.setLocation(x/6+x/25,y/20);
		frame.setSize(x/3*2+x/10,y/20*17);
		frame.setVisible(set);
	}

}
