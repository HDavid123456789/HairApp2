package vasarlas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import commonComponent.Method;
import commonComponent.SaveData;
import gui.GUI;

public class AmountList extends SaveData{	
	static int x=gui.GUI.x;


	static int y=gui.GUI.y;
	
	
	static JInternalFrame frame = new JInternalFrame();
	static JPanel panel = new JPanel();
	static DefaultListModel lm = new DefaultListModel();
	static JList list = new JList(lm);
	static JScrollPane scroll = new JScrollPane(list);
	static JButton client = new JButton();
	static JButton buy = new JButton();
	static JButton withPoint = new JButton();
	static JButton returning = new JButton("Összeg");
	static JButton returning2 = new JButton("Visszajáró:");
	static JTextArea returningTxt = new JTextArea();
	static JTextArea returningTxt2 = new JTextArea();
	static JTextArea sum= new JTextArea();
	static JTextArea discountTxt = new JTextArea();
	static JButton discountBtn = new JButton("Kedvezmény (%-ban)");
	
	static ArrayList<Boolean> discountBool = new ArrayList<Boolean>();
	static ArrayList<String> datas = new ArrayList<String>();
	static ArrayList<String> lmBasic = new ArrayList<String>();
	static ArrayList<Integer> lmDiscount = new ArrayList<Integer>();
	static ArrayList<Integer> priceArray = new ArrayList<Integer>();
	
	int discount=0;
	static int multipoint=10;
	static int price=0;
	
	AmountList(){
		frame.setLocation(x/6+x/25,y/20);
		frame.setSize(x/3+x/25,y/20*17);
		frame.setVisible(false);
		frame.setResizable(true);
		frame.setTitle("KOSÁRBA TÉTEL");
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		int x=frame.getWidth(),	y=frame.getHeight();
		
		client.setBounds(x/10,y/50,x/5*4,y/20);
		client.setEnabled(false);
		client.setFont(new Font("Dialog",Font.BOLD,y/50));
		
		panel.setBackground(new Color(30,30,30));
		panel.setLayout(null);
		
		list.setVisibleRowCount(1);		
		list.setBackground(new Color(30,30,30));
		list.setForeground(Color.WHITE);
		list.setFont(new Font("Dialog",Font.BOLD,y/40));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setBorder(new EmptyBorder(10,x/25,10,10));
		
		scroll.setBounds(x/50,y/5,x-x/25,y/5*2);
		
		
		Font font =new Font("Dialog",Font.BOLD,y/50);
		
		buy.setBounds(x/5*4-x/50,y/10*9,x/5,y/20);
		buy.setFont(font);
		buy.setText("Fizetés");
		
		withPoint.setText("Pontok felhasználása: KI");
		withPoint.setFont(font);
		withPoint.setBounds(x/50,y/5*3+y/12+y/50,x/5*2,y/20);
		
		returning.setBounds(x/50,y/5*3+y/25+y/12+y/20,x/5,y/20);
		returning.setFont(font);
		returning.setEnabled(false);
		
		returning2.setBounds(x/25+x/5*2+x/50,y/5*3+y/25+y/12+y/20,x/5,y/20);
		returning2.setFont(font);
		returning2.setEnabled(false);
		
		returningTxt.setFont(new Font("Dialog",Font.BOLD,y/30));
		returningTxt.setBounds(x/25+x/5,y/5*3+y/25+y/12+y/20,x/5,y/20);
		
		returningTxt2.setFont(new Font("Dialog",Font.BOLD,y/30));
		returningTxt2.setEnabled(false);
		returningTxt2.setBounds(x/25+x/5*3+x/25,y/5*3+y/25+y/12+y/20,x/5,y/20);
		
		
		sum.setBounds(x/50,y/5*3+y/50,x-x/25,y/12);
		sum.setBackground(new Color(50,50,50));
		sum.setFont(font);
		sum.setBorder(new EmptyBorder(10,x/25,10,10));
		sum.setForeground(Color.WHITE);
		
		
		discountBtn.setEnabled(false);
		discountBtn.setBounds(x/50,y/10*9,x/5*2,y/20);
		discountBtn.setFont(font);
		
		discountTxt.setBounds(x/25+x/5*2,y/10*9,x/5,y/20);
		discountTxt.setFont(new Font("Dialog",Font.BOLD,y/30));
		discountTxt.setText("0");
		
		panel.add(discountBtn);
		panel.add(discountTxt);
		panel.add(returningTxt);
		panel.add(returning2);
		panel.add(returningTxt2);
		panel.add(returning);
		panel.add(withPoint);
		panel.add(buy);
		panel.add(client);
		panel.add(scroll);
		panel.add(sum);
		frame.add(panel);
		GUI.lp.add(frame,Integer.valueOf(2));
		
		
		list.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent evt) {
		    	  if (!list.getValueIsAdjusting() && list.getSelectedIndex()>=0) {
		    		  if(list.getSelectedIndex()%3==2) {remove(list.getSelectedIndex());}
		    	  }
		      }
		    	  
		 });
		
		
		buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean run=false;
				if(Method.convert(discountTxt.getText(),"Kedvezmény",false,frame)) {run=true;discount=Integer.parseInt(discountTxt.getText());}
				if(discountTxt.getText().equals("")) {discount=0;}
				if(run) {
					Object[] options = {"Vétel","MÉGSEM"};
					int n = JOptionPane.showOptionDialog(frame,"Biztosan meg szeretnéd venni a fenti listában található termékeket?",null, JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
					if(n==0) {
					if(withPoint.getText().equals("Pontok felhasználása: KI")) {multipoint=multipoint+(price/15);}else {
						if(price>multipoint) {multipoint=0;}else {multipoint=multipoint-price;}
					}
					datas.add(Method.date("yyyy-MM-dd HH:mm:ss")+";"+multipoint+";"+discount);
					
					writeAndRead("mentes/szemelyesadatok/"+client.getText()+"/vetel.txt",datas);
					
					ArrayList<String> basic = new ArrayList<String>();
					basic.add(datas.get(datas.size()-1));
					basic.add(client.getText());
					for(int i=0; i<datas.size()-1;i++) {
						basic.add(datas.get(i));
					}
					
					
					writeAndRead("mentes/napiforgalom/lista.txt",basic);
					
					close();
					}
				}
			}
		});
		
		
		withPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(withPoint.getText().equals("Pontok felhasználása: KI")) {
					if(multipoint<1000) {
						JOptionPane.showMessageDialog(frame,"1 000 Pont alatt nics lehetõség a pontok beszámítására","Kevés pont",JOptionPane.ERROR_MESSAGE);
					}else {
					withPoint.setText("Pontok felhasználása: BE");
					point();
					}
					
				}else {
					withPoint.setText("Pontok felhasználása: KI");
					point();
				}
			}
		});
		
		
		returningTxt.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				point();
			}
		});
		
		
		discountTxt.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				boolean make=false;
				if(discountTxt.equals("")) {
					discount=0;
					discountTxt.setText("0");
					make=true;
				}else {
					
					//String txt2="  -> "+this.discount+"% kedvezménnyel: "+(price*pieces)*(100-this.discount)/100 + "ft";
					if(Method.convert(discountTxt.getText(),"Kedvezmény",false,frame) && !discountTxt.equals("")) {
						discount=Integer.parseInt(discountTxt.getText());
						make=true;
					}
					if(make) {
						for(int i=0; i<discountBool.size(); i++) {
							if(discountBool.get(i)) {
								lm.set(i*3+1, lmBasic.get(i)+" -> "+discount+"% kedvezménnyel: "+lmDiscount.get(i)*(100-discount)/100 + "ft");
							}else {
								lm.set(i*3+1, lmBasic.get(i));
							}
						}
						}
				}
				setprice();
			}
		});
		
	}
	
	
	
	void point() {
		int back;
		if(returningTxt.getText().equals("")) {returningTxt2.setText("- ft");
		}else {
			if(Method.convert(returningTxt.getText(),"Visszajáró",false,frame)) {
				if(withPoint.getText().equals("Pontok felhasználása: KI")) {back=Integer.parseInt(returningTxt.getText())-price;}else {back=Integer.parseInt(returningTxt.getText())-price+multipoint;}
				if(back>=0) {
					returningTxt2.setText( back+ " ft");
				}else {
					returningTxt2.setText("- ft");
				}
			}else {
				returningTxt.setText("");
				returningTxt2.setText("- ft");
			}
		}
	}
	
	void close() {
		
		visible(false);
		SelectService.visible(false);
		Select.visible(false);
		SearchName.visible(true);
		
		discountBool = new ArrayList<Boolean>();
		datas = new ArrayList<String>();
		lmBasic = new ArrayList<String>();
		lmDiscount = new ArrayList<Integer>();
		priceArray = new ArrayList<Integer>();
		
		lm.removeAllElements();
		discountTxt.setText("0");
		
		discount=0;
		multipoint=10;
		price=0;
		
		
	}
	
	
	void remove(int index) {
		Object[] options = {"TÖRLÉS","MÉGSEM"};
		int n = JOptionPane.showOptionDialog(frame,lm.getElementAt(index-2)+"\nBiztosan törlöd?",null, JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
		if(n==0) {
			index-=index%3;
			lm.removeElementAt(index);
			lm.removeElementAt(index);
			lm.removeElementAt(index);
			String split[]=datas.get(index/3).split(";");
			//price-=Integer.parseInt(split[1]);
			priceArray.remove(index/3);
			setprice();
			datas.remove(index/3);
			lmBasic.remove(index/3);
			lmDiscount.remove(index/3);
			discountBool.remove(index/3);
			setprice();
			JOptionPane.showMessageDialog(frame, "a törlés sikeresen megtörtént");
		}
	}
	
	void setprice() {
		price=0;
		for(int i=0; i<lmDiscount.size();i++) {
			if(discountBool.get(i) && discount!=0) {
				price+=lmDiscount.get(i)*(100-discount)/100;
			}else {
				price+=lmDiscount.get(i);
			}
		}
		sum();
		
	}
	
	
	
	static void visible(boolean set) {
		frame.setVisible(set);
		frame.setLocation(x/6+x/25,y/20);
		frame.setSize(x/3+x/25,y/20*17);
	}
	
	
	void visible(boolean set,String name) {
		frame.setVisible(set);
		frame.setLocation(x/6+x/25,y/20);
		frame.setSize(x/3+x/25,y/20*17);
		client.setText(name);
		
		multipoint=point(name);
		
	}
	
	void addElement(String client, String service, String type, String priceS, String piecesS,boolean discount) {
		int pieces=Integer.parseInt(piecesS);
		String split[]=priceS.split(" ");
		int price=Integer.parseInt(split[2]);
		priceArray.add(Integer.parseInt(split[2])*pieces);
		setprice();
		String details=(service +" ("+type+")");
		lm.addElement(details+" - " +pieces+" db"); 
		
		String txt="  [ "+pieces+" db * "+price+" ft ="+" "+(price*pieces)+" ft ]";
				lmBasic.add(txt);
				lm.addElement(txt);
		
		
		lmDiscount.add(price*pieces);	
		String txt2="  -> "+this.discount+"% kedvezménnyel: "+(price*pieces)*(100-this.discount)/100 + "ft";
		
		if(discount && this.discount>0) {
			lm.set((lmDiscount.size()-1)*3+1,txt+txt2);
		}
		
		lm.addElement(" Elem törlésse");
		this.price+=price*pieces ;
		
		datas.add(details+";"+price+";"+pieces+";"+discount);
		discountBool.add(discount);
		sum();
	}
	
	void sum() {
		int point=price-multipoint;
		if(point<0) {point=0;}
		sum.setText("ÖSSZESEN: "+this.price+" ft\nBeszámítható pontokkal: "+point+"   ("+multipoint+" pont)");
	}
	
}
