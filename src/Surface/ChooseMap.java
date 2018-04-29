package Surface;

import com.nienluannganh.towerdefense.R;

import Config.ColorConfig;
import Config.Config;
import Config.PointLevel;
import Database.Database;
import Log.Log;
import Others.Button;
import Others.ButtonLevel;
import Others.Collider;
import Others.Draw;
import Others.Point;
import activity.GameActivity;
import activity.MainActivity;
import activity.ReadlyActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Align;

@SuppressLint("ClickableViewAccessibility")
public class ChooseMap extends GameSurface {

	private ButtonLevel[] level;
	private Bitmap[] lvBitmap;
	private Bitmap bg;
	private Button back;
	private int levelNow;

	private enum levelValue {
		DONE, READLY, NEW
	};

	private Bitmap bgDetail;
	private Button next;
	private Bitmap[] star;
	private Collider exitColider;

	private enum starValue {
		ZERO, ONE, TWO, THREE
	};

	private int levelChooseNow = 0;
	private int numStar;
	private int highScore;
	private Boolean isGetValue = false;

	public ChooseMap(GameActivity context) {
		super(context);
		this.nameSurface = "Choose Map";
	}

	@Override
	public void onTouchDown(int x, int y) {
		if (levelChooseNow == 0) {
			// check back button
			if (back.checkEvent(x, y)) {
				// sound
				audio.playSFX(R.raw.click1);
				// run task
				back.onClick();
			}
			// check level button
			for (int i = 0; i < level.length; i++) {
				if (level[i].checkEvent(x, y)) {
					// sound
					audio.playSFX(R.raw.click1);
					// run task
					level[i].onClick();
				}
			}
		} else {
			if (exitColider.isCollider(x, y)) {
				levelChooseNow = 0;
			}
			if (next.checkEvent(x, y)) {
				// sound
				audio.playSFX(R.raw.click1);
				// run task
				next.onClick();
			}
		}
	}

	@Override
	public void processing() {
		if (this.isBackKeyPress) {
			if (this.levelChooseNow == 0) {
				this.backToMenu();
			} else {
				this.isBackKeyPress = !this.isBackKeyPress;
				this.levelChooseNow = 0;
			}
		}

		if (levelChooseNow > 0 && !isGetValue) {
			Database db = new Database(mainActivity);
			numStar = db.getStar(levelChooseNow);
			highScore = db.getHighScore(levelChooseNow);
			isGetValue = true;
		} else {
			isGetValue = false;
		}
	}

	@Override
	public void drawing(Canvas canvas) {
		canvas.drawBitmap(bg, 0, 0, null);
		Draw.bgColor(Color.BLACK, 100, canvas);

		// draw back button
		back.draw(canvas);
		// draw level
		for (int i = 0; i < level.length; i++) {
			level[i].draw(canvas);
		}
		// draw details level
		this.drawDetailsLevel(canvas);
	}

	@Override
	public void setUp() {
		float heightWindow = mainActivity.getHeight();
		float widthWindow = mainActivity.getWidth();

		bg = getBitmap(R.drawable.map_bg);
		bg = this.scaledBitmap(bg, heightWindow, widthWindow);

		lvBitmap = new Bitmap[3];
		lvBitmap[levelValue.DONE.ordinal()] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.map_oldlevel);
		lvBitmap[levelValue.READLY.ordinal()] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.map_newlevel);
		lvBitmap[levelValue.NEW.ordinal()] = getBitmap(R.drawable.map_unknown);

		// get level from database
		Database db = new Database(mainActivity.getBaseContext());
		levelNow = db.getLevel();
		// levelNow = 10;
		Log.write("Level now = " + levelNow);

		int numberLevelButton;

		if (levelNow > 10) {
			numberLevelButton = 12;
		} else {
			numberLevelButton = levelNow + 2;
		}

		level = new ButtonLevel[numberLevelButton];
		Log.write("number level button = " + level.length);

		Point p;
		Bitmap temp;

		for (int i = 0; i < level.length; i++) {
			final int lv = i + 1;
			p = getPoint(lv);
			if (i < levelNow) {
				temp = lvBitmap[levelValue.DONE.ordinal()];
			} else if (i == levelNow) {
				temp = lvBitmap[levelValue.READLY.ordinal()];
			} else {
				temp = lvBitmap[levelValue.NEW.ordinal()];
			}
			if (temp != lvBitmap[levelValue.NEW.ordinal()]) {
				level[i] = new ButtonLevel(widthWindow * p.getX(), heightWindow * p.getY(), temp, i + 1 + "",
						getFontTyfe(Config.font1)) {
					@Override
					public void onClick() {

						levelChooseNow = lv;
						Log.write("Choose Lv = " + lv);
					}
				};
			} else {
				level[i] = new ButtonLevel(widthWindow * p.getX(), heightWindow * p.getY(), temp, "",
						getFontTyfe(Config.font1)) {

					@Override
					public void onClick() {

					}
				};
			}
		}

		back = new Button(heightWindow * 0.05f, heightWindow * 0.05f, getBitmap(R.drawable.map_back)) {
			@Override
			public void onClick() {
				backToMenu();
			}
		};

		// Details Level window
		bgDetail = getBitmap(R.drawable.map_selectlevelbg);
		float scaledValue = heightWindow / bgDetail.getHeight();
		bgDetail = this.scaledBitmap(bgDetail, heightWindow, bgDetail.getWidth() * scaledValue);

		exitColider = new Collider(0, 0, heightWindow, widthWindow - bgDetail.getWidth());

		star = new Bitmap[4];

		star[starValue.ZERO.ordinal()] = getBitmap(R.drawable.star0);
		star[starValue.ONE.ordinal()] = getBitmap(R.drawable.star1);
		star[starValue.TWO.ordinal()] = getBitmap(R.drawable.star2);
		star[starValue.THREE.ordinal()] = getBitmap(R.drawable.star3);

		temp = getBitmap(R.drawable.map_btplay);
		next = new Button(widthWindow - (bgDetail.getWidth() + temp.getWidth()) / 2,
				heightWindow * 0.9f - temp.getHeight(), temp) {
			@Override
			public void onClick() {
				toReadly();
			}
		};

		// sound
		audio.playBG(R.raw.waiting);
	}
	// == end setup() ==

	private void backToMenu() {
		Log.write("Back to Main menu");
		this.switchActivity(MainActivity.class);
		isRuning = false;
	}

	private Point getPoint(int id) {
		switch (id) {
		case 1:
			return new Point(PointLevel.LV1.getX(), PointLevel.LV1.getY());
		case 2:
			return new Point(PointLevel.LV2.getX(), PointLevel.LV2.getY());
		case 3:
			return new Point(PointLevel.LV3.getX(), PointLevel.LV3.getY());
		case 4:
			return new Point(PointLevel.LV4.getX(), PointLevel.LV4.getY());
		case 5:
			return new Point(PointLevel.LV5.getX(), PointLevel.LV5.getY());
		case 6:
			return new Point(PointLevel.LV6.getX(), PointLevel.LV6.getY());
		case 7:
			return new Point(PointLevel.LV7.getX(), PointLevel.LV7.getY());
		case 8:
			return new Point(PointLevel.LV8.getX(), PointLevel.LV8.getY());
		case 9:
			return new Point(PointLevel.LV9.getX(), PointLevel.LV9.getY());
		case 10:
			return new Point(PointLevel.LV10.getX(), PointLevel.LV10.getY());
		case 11:
			return new Point(PointLevel.LV11.getX(), PointLevel.LV11.getY());
		case 12:
			return new Point(PointLevel.LV12.getX(), PointLevel.LV12.getY());
		default:
			return null;
		}
	}

	private void drawDetailsLevel(Canvas canvas) {
		if (this.levelChooseNow > 0) {
			// draw gray alpha background
			Draw.bgColor(Color.BLACK, 200, canvas);
			// draw background
			canvas.drawBitmap(bgDetail, mainActivity.getWidth() - bgDetail.getWidth(), 0, null);
			// draw Level
			String txt = "Level " + levelChooseNow;
			Draw.drawText(txt, mainActivity.getWidth() - bgDetail.getWidth() / 2, mainActivity.getHeight() * 0.15f,
					getFontTyfe(Config.font1), 50, Align.CENTER, ColorConfig.ORANGE__1, canvas);

			// draw line
			Draw.drawLine(mainActivity.getWidth() * 1.05f - bgDetail.getWidth(), mainActivity.getHeight() * 0.15f + 30,
					mainActivity.getWidth() * 0.95f, mainActivity.getHeight() * 0.15f + 30, 4, ColorConfig.ORANGE__1,
					canvas);

			// draw star
			canvas.drawBitmap(star[numStar],
					mainActivity.getWidth() - bgDetail.getWidth() / 2 - star[numStar].getWidth() / 2,
					mainActivity.getHeight() * 0.15f + 40, null);

			// draw highScore
			if (highScore > 0) {
				Draw.drawText("Best score", mainActivity.getWidth() - bgDetail.getWidth() / 2,
						mainActivity.getHeight() * 0.15f + 80 + star[0].getHeight(), getFontTyfe(Config.font2), 30,
						Align.CENTER, Color.WHITE, canvas);
				Draw.drawText(""+highScore, mainActivity.getWidth() - bgDetail.getWidth() / 2,
						mainActivity.getHeight() * 0.15f + 140 + star[0].getHeight(), getFontTyfe(Config.font2), 40,
						Align.CENTER, Color.WHITE, canvas);
			}

			// draw button next Activity
			next.draw(canvas);
		}
	} // == end drawDetailsLevel(Canvas canvas) ==
	
	private void toReadly() {
		Intent nextScreen = new Intent(mainActivity.getApplicationContext(), ReadlyActivity.class);
		nextScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		nextScreen.putExtra("Level", levelChooseNow);
		mainActivity.startActivity(nextScreen);
		mainActivity.finish();
	}
}
