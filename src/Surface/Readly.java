package Surface;

import com.nienluannganh.towerdefense.R;

import Config.*;
import Log.Log;
import Others.Button;
import Others.Draw;
import activity.ChooseMapActivity;
import activity.GameActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import playing.SubSetting;

@SuppressLint("ClickableViewAccessibility")
public class Readly extends GameSurface {

	private int maxColumn = 4;

	private int level = 0;

	private Bitmap bg1;
	private Bitmap bg2;
	private Bitmap[] title;
	private Bitmap[] archer;
	private Bitmap[] tanker;
	private Bitmap[] posion;
	private Bitmap[] mage;
	private Bitmap title2;

	private enum titleValue {
		ACHER, TANKER, POSION, MAGE
	}

	private Button[] plus;
	private Button[] minus;
	private Button back;
	private Button next;

	private int acherLv = 1;
	private int tankerLv = 1;
	private int posionLv = 1;
	private int mageLv = 1;

	private int upgradePoint;

	public Readly(GameActivity context) {
		super(context);
		this.nameSurface = "Readly";

		// Get Value put Extra before activity
		Bundle bundle = mainActivity.getIntent().getExtras();
		if (bundle != null) {
			level = bundle.getInt("Level");
			Log.write("Get level :" + level);
		}

		upgradePoint = level / 3 + 1;
	}

	@Override
	public void processing() {
		if (this.isBackKeyPress) {
			this.backToChooseMap();
		}
		resetButton();
		if (upgradePoint == 0) {
			for (int i = 0; i < maxColumn; i++) {
				plus[i].setEnabled(false);
			}
			if (acherLv == Config.MIN_LEVEL_TOWER) {
				minus[titleValue.ACHER.ordinal()].setEnabled(false);
			}
			if (tankerLv == Config.MIN_LEVEL_TOWER) {
				minus[titleValue.TANKER.ordinal()].setEnabled(false);
			}
			if (posionLv == Config.MIN_LEVEL_TOWER) {
				minus[titleValue.POSION.ordinal()].setEnabled(false);
			}
			if (mageLv == Config.MIN_LEVEL_TOWER) {
				minus[titleValue.MAGE.ordinal()].setEnabled(false);
			}
		} else if (upgradePoint > 0) {
			if (acherLv == Config.MIN_LEVEL_TOWER) {
				minus[titleValue.ACHER.ordinal()].setEnabled(false);
			}
			if (tankerLv == Config.MIN_LEVEL_TOWER) {
				minus[titleValue.TANKER.ordinal()].setEnabled(false);
			}
			if (posionLv == Config.MIN_LEVEL_TOWER) {
				minus[titleValue.POSION.ordinal()].setEnabled(false);
			}
			if (mageLv == Config.MIN_LEVEL_TOWER) {
				minus[titleValue.MAGE.ordinal()].setEnabled(false);
			}
			// check plus
			if (acherLv == Config.MAX_LEVEL_ACHER) {
				plus[titleValue.ACHER.ordinal()].setEnabled(false);
			}
			if (tankerLv == Config.MAX_LEVEL_TANKER) {
				plus[titleValue.TANKER.ordinal()].setEnabled(false);
			}
			if (posionLv == Config.MAX_LEVEL_POSION) {
				plus[titleValue.POSION.ordinal()].setEnabled(false);
			}
			if (mageLv == Config.MAX_LEVEL_MAGE) {
				plus[titleValue.MAGE.ordinal()].setEnabled(false);
			}
		}
	}

	private void resetButton() {
		for (int i = 0; i < maxColumn; i++) {
			plus[i].setEnabled(true);
			minus[i].setEnabled(true);
		}

	}

	@Override
	public void onTouchDown(int x, int y) {
		for (int i = 0; i < maxColumn; i++) {
			if (plus[i].checkEvent(x, y)) {
				// sound
				audio.playSFX(R.raw.click1);
				// run task
				plus[i].onClick();
			}
			if (minus[i].checkEvent(x, y)) {
				// sound
				audio.playSFX(R.raw.click1);
				// run task
				minus[i].onClick();
			}
		}

		if (back.checkEvent(x, y)) {
			// sound
			audio.playSFX(R.raw.click1);
			// run task
			back.onClick();
		}
		if (next.checkEvent(x, y)) {
			// sound
			audio.playSFX(R.raw.click1);
			// run task
			next.onClick();
		}
		//
	}

	@Override
	public void drawing(Canvas canvas) {
		float heightWindow = mainActivity.getHeight();
		float widthWindow = mainActivity.getWidth();

		// Draw background
		canvas.drawBitmap(bg1, 0, 0, null);
		canvas.drawBitmap(bg2, (widthWindow - bg2.getWidth()) / 2, heightWindow * 0.95f - bg2.getHeight(), null);

		// Draw title
		canvas.drawBitmap(title[titleValue.ACHER.ordinal()],
				widthWindow / 8 - title[titleValue.ACHER.ordinal()].getWidth() / 2, heightWindow * .05f, null);
		canvas.drawBitmap(title[titleValue.TANKER.ordinal()],
				widthWindow * 3 / 8 - title[titleValue.TANKER.ordinal()].getWidth() / 2, heightWindow * .05f, null);
		canvas.drawBitmap(title[titleValue.POSION.ordinal()],
				widthWindow * 5 / 8 - title[titleValue.POSION.ordinal()].getWidth() / 2, heightWindow * .05f, null);
		canvas.drawBitmap(title[titleValue.MAGE.ordinal()],
				widthWindow * 7 / 8 - title[titleValue.MAGE.ordinal()].getWidth() / 2, heightWindow * .05f, null);

		// Draw tower

		canvas.drawBitmap(archer[acherLv - 1], widthWindow / 8 - archer[acherLv - 1].getWidth() / 2,
				heightWindow * .45f - archer[acherLv - 1].getHeight(), null);
		canvas.drawBitmap(tanker[tankerLv - 1], widthWindow * 3 / 8 - tanker[tankerLv - 1].getWidth() / 2,
				heightWindow * .45f - tanker[tankerLv - 1].getHeight(), null);
		canvas.drawBitmap(posion[posionLv - 1], widthWindow * 5 / 8 - posion[posionLv - 1].getWidth() / 2,
				heightWindow * .45f - posion[posionLv - 1].getHeight(), null);
		canvas.drawBitmap(mage[mageLv - 1], widthWindow * 7 / 8 - mage[mageLv - 1].getWidth() / 2,
				heightWindow * .45f - mage[mageLv - 1].getHeight(), null);

		// Draw sub title
		canvas.drawBitmap(title2, widthWindow / 8 - title2.getWidth() / 2, heightWindow * .5f, null);
		canvas.drawBitmap(title2, widthWindow * 3 / 8 - title2.getWidth() / 2, heightWindow * .5f, null);
		canvas.drawBitmap(title2, widthWindow * 5 / 8 - title2.getWidth() / 2, heightWindow * .5f, null);
		canvas.drawBitmap(title2, widthWindow * 7 / 8 - title2.getWidth() / 2, heightWindow * .5f, null);

		// Draw minus button
		for (int i = 0; i < maxColumn; i++) {
			minus[i].draw(canvas);
		}

		// Draw value tower
		int fontSize = 80;
		Draw.drawTextWithStroke(acherLv + "", widthWindow / 8, heightWindow * .55f + title2.getHeight() + 50,
				Color.WHITE, ColorConfig.OUTLINE_1, getFontTyfe(Config.font1), fontSize, 2, Align.CENTER, canvas);
		Draw.drawTextWithStroke(tankerLv + "", widthWindow * 3 / 8, heightWindow * .55f + title2.getHeight() + 50,
				Color.WHITE, ColorConfig.OUTLINE_1, getFontTyfe(Config.font1), fontSize, 2, Align.CENTER, canvas);
		Draw.drawTextWithStroke(posionLv + "", widthWindow * 5 / 8, heightWindow * .55f + title2.getHeight() + 50,
				Color.WHITE, ColorConfig.OUTLINE_1, getFontTyfe(Config.font1), fontSize, 2, Align.CENTER, canvas);
		Draw.drawTextWithStroke(mageLv + "", widthWindow * 7 / 8, heightWindow * .55f + title2.getHeight() + 50,
				Color.WHITE, ColorConfig.OUTLINE_1, getFontTyfe(Config.font1), fontSize, 2, Align.CENTER, canvas);

		// Draw plus button
		for (int i = 0; i < maxColumn; i++) {
			plus[i].draw(canvas);
		}

		// Draw back button
		back.draw(canvas);

		// Draw upgrade point
		fontSize = 40;
		Draw.drawTextWithStroke("Upgrade point: " + upgradePoint, widthWindow / 2,
				heightWindow * 0.95f - (bg2.getHeight() - fontSize + 10) / 2, Color.WHITE, ColorConfig.OUTLINE_1,
				getFontTyfe(Config.font1), fontSize, 2, Align.CENTER, canvas);

		// Draw next button
		next.draw(canvas);
	}

	@Override
	public void setUp() {
		float heightWindow = mainActivity.getHeight();
		float widthWindow = mainActivity.getWidth();

		// set bitmap background
		bg1 = getBitmap(R.drawable.readly_bg1);
		bg1 = this.scaledBitmap(bg1, heightWindow, widthWindow);

		bg2 = getBitmap(R.drawable.readly_bg2);

		// set bitmap title
		title = new Bitmap[maxColumn];

		title[titleValue.ACHER.ordinal()] = getBitmap(R.drawable.readly_acher);
		title[titleValue.TANKER.ordinal()] = getBitmap(R.drawable.readly_tanker);
		title[titleValue.POSION.ordinal()] = getBitmap(R.drawable.readly_posion);
		title[titleValue.MAGE.ordinal()] = getBitmap(R.drawable.readly_mage);

		// set bitmap tower
		// tower = new Bitmap[maxColumn];
		//
		// tower[titleValue.ACHER.ordinal()] = getBitmap(R.drawable.readly_acher_tower);
		// tower[titleValue.TANKER.ordinal()] =
		// getBitmap(R.drawable.readly_tanker_tower);
		// tower[titleValue.POSION.ordinal()] =
		// getBitmap(R.drawable.readly_posion_tower);
		// tower[titleValue.MAGE.ordinal()] = getBitmap(R.drawable.readly_mage_tower);

		archer = new Bitmap[Config.MAX_LEVEL_ACHER];

		archer[0] = getBitmap(R.drawable.readly_acher_tower);
		archer[1] = getBitmap(R.drawable.readly_acher_tower2);
		archer[2] = getBitmap(R.drawable.readly_acher_tower3);

		tanker = new Bitmap[Config.MAX_LEVEL_TANKER];

		tanker[0] = getBitmap(R.drawable.readly_tanker_tower);
		tanker[1] = getBitmap(R.drawable.readly_tanker_tower2);
		tanker[2] = getBitmap(R.drawable.readly_tanker_tower3);

		posion = new Bitmap[Config.MAX_LEVEL_POSION];

		posion[0] = getBitmap(R.drawable.readly_posion_tower);
		posion[1] = getBitmap(R.drawable.readly_posion_tower2);
		posion[2] = getBitmap(R.drawable.readly_posion_tower3);

		mage = new Bitmap[Config.MAX_LEVEL_MAGE];

		mage[0] = getBitmap(R.drawable.readly_mage_tower);
		mage[1] = getBitmap(R.drawable.readly_mage_tower2);
		mage[2] = getBitmap(R.drawable.readly_mage_tower3);

		// set bitmap sub title
		title2 = getBitmap(R.drawable.readly_level);

		Bitmap temp;

		// set button minus
		minus = new Button[maxColumn];
		temp = getBitmap(R.drawable.readly_btminus);
		minus[titleValue.ACHER.ordinal()] = new Button(widthWindow * 1 / 16 - temp.getWidth() / 2,
				heightWindow * .55f + title2.getHeight() + temp.getHeight() * 24.5f / 37, temp) {
			@Override
			public void onClick() {
				if (acherLv > Config.MIN_LEVEL_TOWER) {
					acherLv--;
					upgradePoint++;
					Log.write("acherLevel - 1, acher level : " + acherLv);
				}
			}
		};
		minus[titleValue.TANKER.ordinal()] = new Button(widthWindow * 5 / 16 - temp.getWidth() / 2,
				heightWindow * .55f + title2.getHeight() + temp.getHeight() * 24.5f / 37, temp) {
			@Override
			public void onClick() {
				if (tankerLv > Config.MIN_LEVEL_TOWER) {
					tankerLv--;
					upgradePoint++;
					Log.write("tankerLevel - 1, tanker level : " + tankerLv);
				}
			}
		};
		minus[titleValue.POSION.ordinal()] = new Button(widthWindow * 9 / 16 - temp.getWidth() / 2,
				heightWindow * .55f + title2.getHeight() + temp.getHeight() * 24.5f / 37, temp) {
			@Override
			public void onClick() {
				if (posionLv > Config.MIN_LEVEL_TOWER) {
					posionLv--;
					upgradePoint++;
					Log.write("posionLevel - 1, posion level : " + posionLv);
				}
			}
		};
		minus[titleValue.MAGE.ordinal()] = new Button(widthWindow * 13 / 16 - temp.getWidth() / 2,
				heightWindow * .55f + title2.getHeight() + temp.getHeight() * 24.5f / 37, temp) {
			@Override
			public void onClick() {
				if (mageLv > Config.MIN_LEVEL_TOWER) {
					mageLv--;
					upgradePoint++;
					Log.write("mageLevel - 1, mage level : " + mageLv);
				}
			}
		};

		// set button plus
		plus = new Button[maxColumn];
		temp = getBitmap(R.drawable.readly_btplus);
		plus[titleValue.ACHER.ordinal()] = new Button(widthWindow * 3 / 16 - temp.getWidth() / 2,
				heightWindow * .55f + title2.getHeight(), temp) {
			@Override
			public void onClick() {
				if (upgradePoint > 0 && acherLv < 3) {
					acherLv++;
					upgradePoint--;
					Log.write("acherLevel + 1, acher level : " + acherLv);
				}
			}
		};
		plus[titleValue.TANKER.ordinal()] = new Button(widthWindow * 7 / 16 - temp.getWidth() / 2,
				heightWindow * .55f + title2.getHeight(), temp) {
			@Override
			public void onClick() {
				if (upgradePoint > 0 && tankerLv < 3) {
					tankerLv++;
					upgradePoint--;
					Log.write("tankerLevel + 1, tanker level : " + tankerLv);
				}
			}
		};
		plus[titleValue.POSION.ordinal()] = new Button(widthWindow * 11 / 16 - temp.getWidth() / 2,
				heightWindow * .55f + title2.getHeight(), temp) {
			@Override
			public void onClick() {
				if (upgradePoint > 0 && posionLv < 3) {
					posionLv++;
					upgradePoint--;
					Log.write("posionLevel + 1, posion level : " + posionLv);
				}
			}
		};
		plus[titleValue.MAGE.ordinal()] = new Button(widthWindow * 15 / 16 - temp.getWidth() / 2,
				heightWindow * .55f + title2.getHeight(), temp) {
			@Override
			public void onClick() {
				if (upgradePoint > 0 && mageLv < 3) {
					mageLv++;
					upgradePoint--;
					Log.write("mageLevel + 1, mage level : " + mageLv);
				}
			}
		};

		// set back button
		temp = getBitmap(R.drawable.readly_btback);
		back = new Button(widthWindow / 8 - temp.getWidth() / 2, heightWindow * .95f - temp.getHeight(), temp) {
			@Override
			public void onClick() {
				backToChooseMap();
			}
		};

		// set next button
		temp = getBitmap(R.drawable.readly_btplay);
		next = new Button(widthWindow * 7 / 8 - temp.getWidth() / 2, heightWindow * .95f - temp.getHeight(), temp) {
			@Override
			public void onClick() {
				 nextActivity();
			}
		};
		
		//sound 
		audio.playBG(R.raw.waiting);
	}//== end setup() ==

	private void backToChooseMap() {
		Log.write("Back to Choose Map");
		this.switchActivity(ChooseMapActivity.class);
		isRuning = false;
	}

	private void nextActivity() {
		Intent nextScreen = new Intent(mainActivity, playing.playActivity.class);
		nextScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		// Send value to next Activity
		SubSetting.tank=tankerLv;
		SubSetting.archer=acherLv;
		SubSetting.posion=posionLv;
		SubSetting.wizar=mageLv;
		SubSetting.lever=level;
		// Start new Activity
		mainActivity.startActivity(nextScreen);
		mainActivity.finish();
	}
}
