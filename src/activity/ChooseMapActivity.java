package activity;

import Surface.*;

public class ChooseMapActivity extends GameActivity {
	@Override
	public void init() {
		// TODO Auto-generated method stub
		// Set SurfaceView for Activity.
		surface = new ChooseMap(this);
		this.setContentView(surface);
		System.out.println("Event: Choose Map Activity Start...");
	}
}
