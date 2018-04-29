package activity;

import Log.Log;
import Surface.Setting;

public class SettingActivity extends GameActivity {

	@Override
	public void init() {
		surface = new Setting(this);
		setContentView(surface);
		Log.write("Event: Setting Activity Start...");
	}

}
