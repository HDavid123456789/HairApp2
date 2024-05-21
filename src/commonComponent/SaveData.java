package commonComponent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveData {
	public static ArrayList<String> client= new ArrayList<String>();
	public static ArrayList<String> service= new ArrayList<String>();
	
	
	public static boolean write(String url,ArrayList<String> list){
		boolean successful=false;
		File dir = new File("mentes");
		dir.mkdir();
		
		File names = new File(url);
		try {
			FileWriter fw = new FileWriter(names);
			BufferedWriter br = new BufferedWriter(fw);
			for(int i=0; i<list.size()-1; i++) {
				fw.write(list.get(i)+"\n");
			}
			fw.write(list.get(list.size()-1));
			
			fw.close();
			successful=true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return successful;
	}
	
	
	public static ArrayList read(String url) {
		ArrayList<String> data = new ArrayList<String>();
		File dir = new File("mentes");
		dir.mkdir();
		File names = new File(url);
		try {
			FileReader fr = new FileReader(names);
			BufferedReader br = new BufferedReader(fr);
			String sor=br.readLine();
			while(sor!=null) {
					data.add(sor);
					sor=br.readLine();
			}
			br.close();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return data;
	}
	
	
	public static boolean writeAndRead(String url,ArrayList<String> list){
		boolean successful=false;
		File dir = new File("mentes");
		if(!dir.exists()) {dir.mkdir();}
		
		File names = new File(url);
		ArrayList<String> complete=read(url);
		for(int i=0; i<list.size(); i++) {
			complete.add(list.get(i));
		}
		
		try {
			FileWriter fw = new FileWriter(names);
			BufferedWriter br = new BufferedWriter(fw);
			for(int i=0; i<complete.size()-1; i++) {
				fw.write(complete.get(i)+"\n");
			}
			fw.write(complete.get(complete.size()-1));
			
			fw.close();
			successful=true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return successful;
	}
	
	
	
	public static void renameFolder(String old, String nw) {
		old="mentes/"+old;
		nw="mentes/"+nw;
		File sourceFile = new File(old);
		File destFile = new File(nw);
		
		sourceFile.renameTo(destFile);
	}
	
	public static void newFolder(String folder) {
		File file = new File("mentes/"+folder);
		file.mkdir();
		
		File megyjegyzes = new File("mentes/"+folder+"/megjegyzes.txt");
		File vetel = new File("mentes/"+folder+"/vetel.txt");
		try {
			vetel.createNewFile();
			megyjegyzes.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
	
	
	public static int point(String name) {
		ArrayList<String> point=read("mentes/szemelyesadatok/"+name+"/vetel.txt");
		if(point.size()>0) {
			String split[]=point.get(point.size()-1).split(";");
			return Integer.parseInt(split[1]); 
		}else {return 0;}
	}
	
	
}
