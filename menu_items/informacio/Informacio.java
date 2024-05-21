package informacio;

import gui.Menu;
import informacio.SearchName;

public class Informacio implements Menu {

	SearchName search = new SearchName();

	@Override
	public String name() {
		return "Információk";
	}
	
	@Override
	public void general() {
		search.visible(true);
	}

	@Override
	public void exit() {
		search.visible(false);
		PurchaseList.visible(false);
		NoteList.visible(false);
	}
	
}

