package Surface;

import com.nienluannganh.towerdefense.R;

import Others.Button;
import Others.Draw;
import Config.*;
import Database.Database;
import Log.Log;
import activity.GameActivity;
import activity.MainActivity;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.view.MotionEvent;

public class Setting extends GameSurface {

	private Bitmap bg2;
	private Bitmap bg1;

	private Button master;

	private float master_min;
	private float master_max;

	private Button music;

	private Button sfx;

	private Button save;
	private Button cancel;

	public Setting(GameActivity context) {
		super(context);
		nameSurface = "Seting";
	}

	@Override
	public void setUp() {
		final Database db = new Database(mainActivity);
		float heightWindow = mainActivity.getHeight();
		float widthWindow = mainActivity.getWidth();

		bg1 = getBitmap(R.drawable.loading_bg);
		bg1 = scaledBitmap(bg1, heightWindow, widthWindow);

		bg2 = getBitmap(R.drawable.seting_bg);
		bg2 = scaledBitmap(bg2, heightWindow * .8f, widthWindow * .8f);

		Bitmap temp;
		temp = getBitmap(R.drawable.seting_btvolume);
		temp = scaledBitmap(temp, temp.getHeight() * 1.5f, temp.getWidth() * 2);

		master_min = (widthWindow - bg2.getWidth()) / 2 + 300;
		master_max = (widthWindow + bg2.getWidth()) / 2 - 100;

		master = new Button(master_min + (master_max - master_min) * db.getVolMaster() / 100 - temp.getWidth() / 2,
				(heightWindow - bg2.getHeight()) / 2 + 200 - 10 - temp.getHeight() / 2, temp) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		};

		music = new Button(master_min + (master_max - master_min) * db.getVolMusic() / 100 - temp.getWidth() / 2,
				(heightWindow - bg2.getHeight()) / 2 + 300 - 10- temp.getHeight() / 2, temp) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		};
		sfx = new Button(master_min + (master_max - master_min) * db.getVolMusic() / 100 - temp.getWidth() / 2,
				(heightWindow - bg2.getHeight()) / 2 + 400 - 10- temp.getHeight() / 2, temp) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		};
		int between = 50;
		temp = getBitmap(R.drawable.seting_bt_save);
		save = new Button(getWidth() / 2 - temp.getWidth() - between,
				(getHeight() + bg2.getHeight()) / 2 - temp.getHeight() - 50, temp) {

			@Override
			public void onClick() {
				int volume = (int) ((master.getX() - master_min + master.getWidth() / 2) / (master_max - master_min)
						* 100);				
				db.setVolMaster(volume);
				Log.write("master volumn = " + volume);
			
				volume = (int) ((music.getX() - master_min + master.getWidth() / 2) / (master_max - master_min)
						* 100);
				db.setVolMusic(volume);
				Log.write("music volumn = " + volume);
			
				volume = (int) ((sfx.getX() - master_min + master.getWidth() / 2) / (master_max - master_min)
						* 100);
				db.setVolSFX(volume);
				Log.write("sfx volumn = " + volume);
				
				switchActivity(MainActivity.class);

			}

		};

		temp = getBitmap(R.drawable.seting_bt_cancel);
		cancel = new Button(getWidth() / 2 + between, (getHeight() + bg2.getHeight()) / 2 - temp.getHeight() - 50,
				temp) {

			@Override
			public void onClick() {
				switchActivity(MainActivity.class);
			}

		};
	}

	@Override
	public void drawing(Canvas canvas) {
		canvas.drawBitmap(bg1, 0, 0, null);
		canvas.drawBitmap(bg2, (getWidth() - bg2.getWidth()) / 2, (getHeight() - bg2.getHeight()) / 2, null);
		Draw.drawText("Setting", (getWidth() - bg2.getWidth()) / 2 + 20, (getHeight() - bg2.getHeight()) / 2 + 100,
				getFontTyfe(Config.font2), 50, Align.LEFT, ColorConfig.ORANGE__1, canvas);

		// draw master
		Draw.drawText("Master", (getWidth() - bg2.getWidth()) / 2 + 100, (getHeight() - bg2.getHeight()) / 2 + 200,
				getFontTyfe(Config.font2), 30, Align.LEFT, ColorConfig.ORANGE__1, canvas);
		Draw.drawLine(master_min, (getHeight() - bg2.getHeight()) / 2 + 200 - 10, master_max,
				(getHeight() - bg2.getHeight()) / 2 + 200 - 10, 8, ColorConfig.ORANGE__1, canvas);
		master.draw(canvas);

		// draw music
		Draw.drawText("Music", (getWidth() - bg2.getWidth()) / 2 + 100, (getHeight() - bg2.getHeight()) / 2 + 300,
				getFontTyfe(Config.font2), 30, Align.LEFT, ColorConfig.ORANGE__1, canvas);
		Draw.drawLine(master_min, (getHeight() - bg2.getHeight()) / 2 + 300 - 10, master_max,
				(getHeight() - bg2.getHeight()) / 2 + 300 - 10, 8, ColorConfig.ORANGE__1, canvas);
		music.draw(canvas);
		
		// draw sfx
		Draw.drawText("SFX", (getWidth() - bg2.getWidth()) / 2 + 100, (getHeight() - bg2.getHeight()) / 2 + 400,
				getFontTyfe(Config.font2), 30, Align.LEFT, ColorConfig.ORANGE__1, canvas);
		Draw.drawLine(master_min, (getHeight() - bg2.getHeight()) / 2 + 400 - 10, master_max,
				(getHeight() - bg2.getHeight()) / 2 + 400 - 10, 8, ColorConfig.ORANGE__1, canvas);
		sfx.draw(canvas);
		
		// draw button
		save.draw(canvas);
		cancel.draw(canvas);
	}

	@Override
	public void processing() {
		// TODO Auto-generated method stub

	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (this.isRuning) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				// Log.write("Event: Touch down");
				int x = (int) event.getX();
				int y = (int) event.getY();

				if(save.checkEvent(x, y)) {
					save.onClick();
				}
				if(cancel.checkEvent(x, y)) {
					cancel.onClick();
				}
				
				buttonDown(master, x, y);
				buttonDown(music, x, y);
				buttonDown(sfx, x, y);
				
				return true;
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				float x = event.getX();
				buttonDrag(master, x, 0);
				buttonDrag(music, x, 0);
				buttonDrag(sfx, x, 0);
				
				return true;

			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				buttonUp(master);
				buttonUp(music);
				buttonUp(sfx);
				
				return true;
			}
		}
		return false;
	}

	@Override
	public void onTouchDown(int x, int y) {
		// TODO Auto-generated method stub

	}
	
	private void buttonDown(Button b, int x, int y) {
		if (b.checkEvent(x, y)) {
			b.setHover(true);
		}
	}
	
	private void buttonDrag(Button b, float x, float y) {
		if (b.isHover()) {
			if (x < master_min - b.getWidth() / 2) {
				x = master_min - b.getWidth() / 2;
			}
			if (x > master_max - b.getWidth() / 2) {
				x = master_max - b.getWidth() / 2;
			}
			b.setX(x);
		}
	}
	private void buttonUp(Button b) {
		if (b.isHover()) {
			b.setHover(false);
			int volume = (int) ((b.getX() - master_min + master.getWidth() / 2) / (master_max - master_min)
					* 100);
			Log.write("volumn = " + volume);
		}
	}
}
