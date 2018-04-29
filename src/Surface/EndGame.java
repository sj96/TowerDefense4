package Surface;

import com.nienluannganh.towerdefense.R;

import Config.Config;
import Database.Database;
import Others.Button;
import Others.Draw;
import activity.GameActivity;
import activity.MainActivity;
import activity.ReadlyActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;

public class EndGame extends GameSurface {

	// win
	private Bitmap bgWin;
	private Bitmap[] star;
	private Button homeBt;
	private Button againBt;
	private Button nextBt;

	private int level = 0;
	private int score = 0;
	private int numberStar = 0;

	private int highScore;

	// lose
	private Bitmap bgLose;
	private Button yesBt;
	private Button noBt;

	public static enum statusValue {
		WIN, LOSE
	}

	private int status;

	public EndGame(GameActivity context) {
		super(context);

		// set name for surface
		this.nameSurface = "End Game";
	}

	@Override
	public void setUp() {

		Bundle bundle = mainActivity.getIntent().getExtras();
		if (bundle != null) {
			status = bundle.getInt("Status");
			if (status == statusValue.WIN.ordinal()) {
				level = bundle.getInt("Level");
				score = bundle.getInt("Score");
				numberStar = bundle.getInt("Star");
				
				if (numberStar > 3) {
					numberStar = 3;
				} else if (numberStar < 0) {
					numberStar = 0;
				}
				
				Database db = new Database(mainActivity);
				highScore = db.getHighScore(level);
				// unlock next level
				if(level>db.getLevel()){
					db.nextLevel();
				}
				// update highScore
				if (score > highScore) {
					db.setHighScore(level, score);
					db.setStar(level, numberStar);
				}
			}
		}

		float heightWindow = this.mainActivity.getHeight();
		float widthWindow = this.mainActivity.getWidth();

		Bitmap temp;

		// set up win window
		bgWin = getBitmap(R.drawable.endgame_winer_bg);
		bgWin = scaledBitmap(bgWin, heightWindow, widthWindow);
		// - star
		star = new Bitmap[4];

		star[0] = getBitmap(R.drawable.star0);
		star[1] = getBitmap(R.drawable.star1);
		star[2] = getBitmap(R.drawable.star2);
		star[3] = getBitmap(R.drawable.star3);

		int scaled = 2;
		for (int i = 0; i < 4; i++) {
			star[i] = scaledBitmap(star[i], star[i].getHeight() * scaled, star[i].getWidth() * scaled);
		}

		// - button
		int between = 50;
		temp = getBitmap(R.drawable.endgame_bt_home);
		homeBt = new Button(widthWindow / 2 - temp.getWidth() * 1.5f - between,
				heightWindow * .2f + star[0].getHeight() * 2f, temp) {

			@Override
			public void onClick() {
				switchActivity(MainActivity.class);
			}

		};

		temp = getBitmap(R.drawable.endgame_bt_again);
		againBt = new Button(widthWindow / 2 - temp.getWidth() / 2, heightWindow * .2f + star[0].getHeight() * 2f,
				temp) {

			@Override
			public void onClick() {
				playagain();
			}

		};
		temp = getBitmap(R.drawable.endgame_bt_next);
		nextBt = new Button(widthWindow / 2 + temp.getWidth() / 2 + between,
				heightWindow * .2f + star[0].getHeight() * 2f, temp) {

			@Override
			public void onClick() {
				nextLv();
			}

		};

		// set up lose window
		// - background
		bgLose = getBitmap(R.drawable.endgame_loser_bg);
		bgLose = scaledBitmap(bgLose, heightWindow, widthWindow);
		// - button
		between = 50;
		temp = getBitmap(R.drawable.endgame_bt_yes);
		yesBt = new Button(widthWindow / 2 - between - temp.getWidth(), heightWindow * .6f, temp) {

			@Override
			public void onClick() {
				playagain();
			}
		};
		temp = getBitmap(R.drawable.endgame_bt_no);
		noBt = new Button(widthWindow / 2 + between, heightWindow * .6f, temp) {

			@Override
			public void onClick() {
				switchActivity(MainActivity.class);
			}
		};
	}

	@Override
	public void drawing(Canvas canvas) {
		if (status == statusValue.WIN.ordinal()) {
			this.drawWin(canvas);
		} else if (status == statusValue.LOSE.ordinal()) {
			this.drawLose(canvas);
		}
	}

	@Override
	public void processing() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTouchDown(int x, int y) {
		if (status == statusValue.WIN.ordinal()) {
			if (homeBt.checkEvent(x, y)) {
				// sound
				audio.playSFX(R.raw.click1);
				// run task
				homeBt.onClick();
			}
			if (againBt.checkEvent(x, y)) {
				// sound
				audio.playSFX(R.raw.click1);
				// run task
				againBt.onClick();
			}
			if (nextBt.checkEvent(x, y)) {
				// sound
				audio.playSFX(R.raw.click1);
				// run task
				nextBt.onClick();
			}
		} else if (status == statusValue.LOSE.ordinal()) {
			if (yesBt.checkEvent(x, y)) {
				// sound
				audio.playSFX(R.raw.click1);
				// run task
				yesBt.onClick();
			}
			if (noBt.checkEvent(x, y)) {
				// sound
				audio.playSFX(R.raw.click1);
				// run task
				noBt.onClick();
			}
		}
	}

	private void drawWin(Canvas canvas) {
		// draw background
		canvas.drawBitmap(bgWin, 0, 0, null);

		// draw text
		Draw.drawTextWithStroke("Level " + level, getWidth() / 2, getHeight() * .2f, Color.WHITE, Color.GRAY,
				getFontTyfe(Config.font2), 40, 4, Align.CENTER, canvas);

		// draw star
		canvas.drawBitmap(star[numberStar], getWidth() / 2 - star[numberStar].getWidth() / 2, getHeight() * .2f + 10,
				null);
		Draw.drawTextWithStroke("" + score, getWidth() / 2, getHeight() * .2f + star[numberStar].getHeight() * 1.2f,
				Color.WHITE, Color.GRAY, getFontTyfe(Config.font2), 80, 4, Align.CENTER, canvas);

		if (highScore > 0) {
			Draw.drawTextWithStroke("Best score: " + highScore, getWidth() / 2,
					getHeight() * .2f + star[numberStar].getHeight() * 1.5f, Color.WHITE, Color.GRAY,
					getFontTyfe(Config.font2), 30, 4, Align.CENTER, canvas);
		}

		// draw button
		homeBt.draw(canvas);
		againBt.draw(canvas);
		nextBt.draw(canvas);
	}

	private void drawLose(Canvas canvas) {
		// draw background
		canvas.drawBitmap(bgLose, 0, 0, null);

		// draw text
		// - Game Over
		Draw.drawText("Game Over", getWidth() / 2, getHeight() * .35f, getFontTyfe(Config.font3), 90, Align.CENTER,
				Color.WHITE, canvas);
		// - Play again?
		Draw.drawText("Play again?", getWidth() / 2, getHeight() / 2, getFontTyfe(Config.font2), 68, Align.CENTER,
				Color.WHITE, canvas);

		// draw button
		yesBt.draw(canvas);
		noBt.draw(canvas);
	}

	private void playagain() {
		Intent nextScreen = new Intent(mainActivity.getApplicationContext(), ReadlyActivity.class);
		nextScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		nextScreen.putExtra("Level", level);
		mainActivity.startActivity(nextScreen);
		mainActivity.finish();
	}

	private void nextLv() {
		Intent nextScreen = new Intent(mainActivity.getApplicationContext(), ReadlyActivity.class);
		nextScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		nextScreen.putExtra("Level", level + 1);
		mainActivity.startActivity(nextScreen);
		mainActivity.finish();
	}
}
