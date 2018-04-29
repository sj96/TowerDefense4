package activity;

import Surface.GameSurface;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public abstract class GameActivity extends Activity {

	GameSurface surface;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set fullscreen
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Loại b�? tiêu đ�?.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//create view 
		this.init();
	}

	public float getHeight() {
		return (this.surface.getHeight());
	}

	public float getWidth() {
		return (this.surface.getWidth());
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			System.out.println("Event: Key Back is press.");
			this.surface.isBackKeyPress();
			return true;
		}

		return false;
	}

	public abstract void init();
}
