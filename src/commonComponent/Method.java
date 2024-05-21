package commonComponent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class Method {
	public static boolean convert(String convert, String name,boolean empty, JInternalFrame frame) {
		
		try {
		    Integer.parseInt(convert);
		  } catch (NumberFormatException e) {
			  if(!convert.equals("")) {
			  JOptionPane.showMessageDialog(frame,name+" - ("+convert+") nem felel meg a kritériumnak","HIBA",JOptionPane.ERROR_MESSAGE);
		    return false;
		    }else {
		    	if(!empty) {
		    		JOptionPane.showMessageDialog(frame,name+" - ("+convert+") nem felel meg a kritériumnak","HIBA",JOptionPane.ERROR_MESSAGE);
		    		return false;
		    	}
		    }
		  }
		
		return true;
	}
	
	
	public static String date(String format) {
		Date date = new Date();
		DateFormat form = new SimpleDateFormat(format);
		
		return form.format(date);
	}
}
