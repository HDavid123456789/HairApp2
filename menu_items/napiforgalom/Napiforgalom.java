package napiforgalom;

import gui.Menu;

public class Napiforgalom implements Menu {
	
	InternalFrame frame = new InternalFrame();

	@Override
	public void general() {
		frame.visible(true);
	}

	@Override
	public String name() {
		return "Napi forgalom";
	}

	@Override
	public void exit() {
		frame.visible(false);
	}
	
}