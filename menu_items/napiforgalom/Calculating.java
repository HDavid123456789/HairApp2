package napiforgalom;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import commonComponent.Method;
import commonComponent.SaveData;

public class Calculating {
	
	ArrayList<String> list (String start,String end){
		int sumWithProd=0;
		int sumWithoutProd=0;
		ArrayList<String> line = new ArrayList<String>();
		ArrayList<String> lm = new ArrayList<String>();
		
		File file = new File("mentes/napiforgalom/lista.txt");
		if(file.exists()) {
		
			line=SaveData.read("mentes/napiforgalom/lista.txt");
			boolean begin=false;
			String date="";
			int discount=0;
			
			for(int i=0; i<line.size(); i++) {
				String split[]=line.get(i).split(";");
				String split2[]=split[0].split(" ");
				if(split.length==3) {
					if(i!=0 && begin) {lm.add("____________________________________________________");}
					
					
					
					
					if(split2[0].compareTo(end)>0) {break;}
					if(split2[0].compareTo(start)>=0) {begin=true;}
					
					if(!date.equals(split2[0])) {
						date=split2[0];
						if(begin) {
						lm.add(date);
						lm.add("____________________________________________________");
						}
						
					}
					i++;
					
					if(begin) {
						if(!split[2].equals("0")) {
							discount=Integer.parseInt(split[2]);
							lm.add("  "+split2[1]+ " - "+line.get(i).toUpperCase()+"  Kedvezmény: "+discount+"%");
						}else {
							lm.add("  "+split2[1]+ " - "+line.get(i).toUpperCase());
						}
					}
				
				}else {
					if(begin) {
						String split3[]=line.get(i).split(";");
						lm.add("      "+split[0]);
						int price=Integer.parseInt(split3[1]);
						int piece=Integer.parseInt(split3[2]);
						boolean discountBool;
						if(split3[3].equals("true")) {discountBool=true;}else {discountBool=false;}
						lm.add("         ár: "+price+ " ft  db: "+piece+"  összesen: "+price*piece+" ft");
						
						if(discount>0 && discountBool) {
							lm.add("         "+discount+"%  -> "+price*(100-discount)/100*piece+" ft");
							sumWithoutProd+=price*(100-discount)/100*piece;
						}else {
							if(discountBool) {sumWithoutProd+=price*piece;}
							sumWithProd+=price*piece;
						}
					}
				}
			}
		}
		lm.add(sumWithProd+"");
		lm.add(sumWithoutProd+"");
		
		
		return lm;
	}
	
	int readStartDay() {
		int back=0;
		String now=Method.date("yyyy-MM-dd");
		
		File file = new File("mentes/napiforgalom/napnyitas.txt");
		if(file.exists()) {
			ArrayList<String> lines =SaveData.read("mentes/napiforgalom/napnyitas.txt");
			
			if(now.equals(lines.get(0))) {
				
				back=Integer.parseInt(lines.get(1));
			}
		}
		return back;
	}
	
	void writeStartDay(int many) {
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(Method.date("yyyy-MM-dd"));
		lines.add(many+"");
		SaveData.write("mentes/napiforgalom/napnyitas.txt",lines);
	}

}
