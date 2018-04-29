package Surface;

import com.nienluannganh.towerdefense.R;

import Log.Log;
import Others.*;
import activity.ChooseMapActivity;
import activity.GameActivity;
import activity.SettingActivity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.annotation.SuppressLint;

@SuppressLint("ClickableViewAccessibility")
public class MainMenu extends GameSurface {

	public MainMenu(GameActivity context) {
		super(context);
		nameSurface = "Main menu";
	}

	private enum buttonName {
		Play, Library, Setings, Yes, No
	};

	private Bitmap background;
	private Bitmap title;

	private Bitmap[] exit;

	private Button[] button;

	@Override
	public void onTouchDown(int x, int y) {
		if (!isBackKeyPress) {
			for (int i = 0; i < 3; i++) {
				if (button[i].checkEvent(x, y)) {
					//sound sfx
					audio.playSFX(R.raw.click1);
					//run task
					button[i].onClick();
				}
			}
		} else {
			for (int i = 3; i < 5; i++) {
				if (button[i].checkEvent(x, y)) {
					audio.playSFX(R.raw.click1);
					button[i].onClick();
				}
			}
		}
	}

	private void drawExitWindow(Canvas canvas) {
		if (this.isBackKeyPress == true) {
			// draw gray alpha background
			Paint myPaint = new Paint();
			myPaint.setColor(Color.BLACK);
			myPaint.setAlpha(200);
			Rect rectangle = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
			canvas.drawRect(rectangle, myPaint);
			// draw exit window
			canvas.drawBitmap(exit[0], (canvas.getWidth() - exit[0].getWidth()) / 2.0f,
					(canvas.getHeight() - exit[0].getHeight()) / 2.0f, null);
			canvas.drawBitmap(exit[1], (canvas.getWidth() - exit[1].getWidth()) / 2.0f,
					canvas.getHeight() / 2.0f - exit[1].getHeight() * 2, null);
			// draw button
			for (int i = 3; i < 5; i++) {
				button[i].draw(canvas);
			}
		}
	}

	@Override
	public void processing() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawing(Canvas canvas) {
		canvas.drawBitmap(background, 0.0f, 0.0f, null);
		canvas.drawBitmap(title, (canvas.getWidth() - title.getWidth()) / 2.0f, canvas.getHeight() * 0.1f, null);
		// draw button menu
		for (int i = 0; i < 3; i++) {
			button[i].draw(canvas);
		}
		// Draw Exit window
		this.drawExitWindow(canvas);
	}

	@Override
	public void setUp() {
		float heightWindow = mainActivity.getHeight();
		float widthWindow = mainActivity.getWidth();
		// thiết lập main menu
		background = getBitmap(R.drawable.mainmenu_bg);
		title = getBitmap(R.drawable.title);

		button = new Button[5];

		// Main menu Button
		int distanceBetweenButton = 10;
		// Set up to Choose Map Activity
		Bitmap temp = getBitmap(R.drawable.mainmenu_btplay);
		button[buttonName.Play.ordinal()] = new Button((widthWindow - temp.getWidth()) / 2,
				// (heightWindow * 0.9f - title.getHeight() - temp.getHeight() * 3) / 2 - 10 +
				// title.getHeight(), temp) {
				(heightWindow * 1.1f + title.getHeight() - temp.getHeight() * 3 - distanceBetweenButton * 2) / 2,
				temp) {
			@Override
			public void onClick() {
				Log.write("Go to Choose Map");
				switchActivity(ChooseMapActivity.class);
			}
		};

		temp = getBitmap(R.drawable.mainmenu_btsetings);
		button[buttonName.Setings.ordinal()] = new Button(button[buttonName.Play.ordinal()].getX(),
				button[buttonName.Play.ordinal()].getY() + button[buttonName.Play.ordinal()].getHeight()
						+ distanceBetweenButton,
				temp) {
			@Override
			public void onClick() {
				Log.write("Go to Seting");
				switchActivity(SettingActivity.class);
				
//				Log.write("test end game");
//				Intent nextScreen = new Intent(mainActivity.getApplicationContext(), EndGameActivity.class);
//				nextScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				//win
////				nextScreen.putExtra("Status", EndGame.statusValue.WIN.ordinal());
////				nextScreen.putExtra("Level", 1);
////				nextScreen.putExtra("Star", 2);
////				nextScreen.putExtra("Score", 1000);
//				//lose
//				nextScreen.putExtra("Status", EndGame.statusValue.LOSE.ordinal());
//				mainActivity.startActivity(nextScreen);
//				mainActivity.finish();
			}
		};

		temp = getBitmap(R.drawable.mainmenu_btlibrary);
		button[buttonName.Library.ordinal()] = new Button(button[buttonName.Setings.ordinal()].getX(),
				button[buttonName.Setings.ordinal()].getY() + button[buttonName.Setings.ordinal()].getHeight()
						+ distanceBetweenButton,
				temp) {
			@Override
			public void onClick() {
				Log.write("Go to help");
			}
		};

		background = this.scaledBitmap(background, heightWindow, widthWindow);

		// set up Exit Window
		exit = new Bitmap[2];

		exit[0] = getBitmap(R.drawable.exit_bg);
		exit[1] = getBitmap(R.drawable.exit_title);

		// Exit menu button
		distanceBetweenButton = 30;
		temp = getBitmap(R.drawable.exit_btyes);
		button[buttonName.Yes.ordinal()] = new Button(widthWindow / 2.0f - temp.getWidth() - distanceBetweenButton,
				heightWindow / 2.0f + 10, temp) {
			@Override
			public void onClick() {
				Log.write("Exit program");
				mainActivity.finish();
			}
		};

		temp = getBitmap(R.drawable.exit_btno);
		button[buttonName.No.ordinal()] = new Button(widthWindow / 2.0f + distanceBetweenButton,
				heightWindow / 2.0f + 10, temp) {
			@Override
			public void onClick() {
				isBackKeyPress = !isBackKeyPress;
			}
		};
		
		//sound
		audio.playBG(R.raw.awakening);
	}

}
