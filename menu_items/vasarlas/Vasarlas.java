package vasarlas;

import java.util.ArrayList;

import gui.Menu;

public class Vasarlas implements Menu {
	

	SearchName search = new SearchName();
	
	
	@Override
	public String name() {
		return "Vásárlás";
	}
	
	
	@Override
	public void general() {
		search.visible(true);
	}
	
	@Override
	public void exit() {
		search.visible(false);
		Select.visible(false);
		SelectService.visible(false);
		AmountList.visible(false);
		
	}
	
}