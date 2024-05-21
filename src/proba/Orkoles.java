package proba;

public class Orkoles {
	public static void main(String args[]) {
		Sablonok[] s = new Sablonok[2];
		s[0]= new A();
		s[1]= new B();
		
		for(int i=0; i<s.length; i++) {
			s[i].kiir();
		}
	}


}


interface Sablonok{
	void  kiir();
}

class A implements Sablonok{
	
	public void  kiir() {
		System.out.println("10");
	}
	
}


class B implements Sablonok{
	
	public void  kiir() {
		System.out.println("20");
	}

}