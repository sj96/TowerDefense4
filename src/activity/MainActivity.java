package activity;

import Log.Log;
import Surface.MainMenu;

public class MainActivity extends GameActivity {

	@Override
	public void init() {
		// set up Activity.
		surface = new MainMenu(this);
		this.setContentView(surface);
		Log.write("Event: Main Menu Activity Start...");
	}
}
