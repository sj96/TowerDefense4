package activity;

import Log.Log;
import Surface.EndGame;

public class EndGameActivity extends GameActivity {

	@Override
	public void init() {
		this.surface = new EndGame(this);
		this.setContentView(this.surface);
		Log.write("Event: End Activity Start...");
	}

}
