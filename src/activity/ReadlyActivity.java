package activity;

import Log.Log;
import Surface.*;

public class ReadlyActivity extends GameActivity {

	@Override
	public void init() {
		// Set SurfaceView for Activity.
		surface = new Readly(this);
		this.setContentView(surface);
		Log.write("Event: Readly Activity Start...");
	}
}
