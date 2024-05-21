package adatkezeles;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.GUI;
import gui.Menu;


public class Adatkezeles implements Menu {
	
	Client c = new Client();
	Service s = new Service();
	
	@Override
	public String name() {
		return "Adatkezelés";
	}
	
	
	@Override
	public void general() {
			c.visible(true);
			s.visible(true);
			
	}


	@Override
	public void exit() {
		c.visible(false);
		s.visible(false);
		
	}	

}
