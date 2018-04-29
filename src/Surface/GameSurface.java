package Surface;

import Log.Log;
import Others.Audio;
import Thread.GameThread;
import activity.GameActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint("ClickableViewAccessibility")
public abstract class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

	private GameThread gameThread;
	protected GameActivity mainActivity;

	protected String nameSurface = "";

	protected Boolean isBackKeyPress;

	protected float scalseH;
	protected float scalsew;

	protected Boolean isRuning = true;
	
	protected Audio audio;

	public GameSurface(GameActivity context) {
		super(context);
		this.mainActivity = context;

		// �?ảm bảo Game Surface có thể focus để đi�?u khiển các sự kiện.
		this.setFocusable(true);

		// Sét đặt các sự kiện liên quan tới Game.
		this.getHolder().addCallback(this);

		isBackKeyPress = false;
		
		//set audio 
		audio = new Audio(mainActivity);
	}

	public void update() {
		if (this.isRuning) {
			processing();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// Log.write("Event: Touch down");
			int x = (int) event.getX();
			int y = (int) event.getY();
			
			if (this.isRuning) {
				this.onTouchDown(x, y);
			}
			return true;
		}
		return false;
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);

		if (this.isRuning) {
			this.drawing(canvas);
		}
	}

	// Thi hành phương thức của interface SurfaceHolder.Callback
	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		this.setUp();

		this.gameThread = new GameThread(this, holder);
		this.gameThread.setRunning(true);
		this.gameThread.start();
		// new Thread log
		System.out.println(nameSurface + " : new Thread start...");
	}

	// Thi hành phương thức của interface SurfaceHolder.Callback
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	// Thi hành phương thức của interface SurfaceHolder.Callback
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		while (true) {
			try {
				this.isRuning = false;
				this.audio.setRunning(false);
				this.gameThread.setRunning(false);

				// Luồng cha, cần phải tạm dừng ch�? GameThread kết thúc.
				this.gameThread.join();

				if (!this.gameThread.isAlive()) {
					// end Thread log
					System.out.println(nameSurface + " : End Thread...");
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void isBackKeyPress() {
		isBackKeyPress = !isBackKeyPress;
	}

	protected Bitmap scaledBitmap(Bitmap bitmap, float heightWindow, float widthWindow) {
		Bitmap scaled;

		scaled = Bitmap.createScaledBitmap(bitmap, (int) (widthWindow), (int) (heightWindow), false);

		return scaled;
	}

	protected void switchActivity(Class<?> cls) {
		Intent nextScreen = new Intent(mainActivity.getApplicationContext(), cls);
		nextScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		mainActivity.startActivity(nextScreen);
		mainActivity.finish();
	}

	protected Typeface getFontTyfe(String font) {
		Typeface type = null;
		try {
			type = Typeface.createFromAsset(mainActivity.getAssets(), font);
		} catch (Exception e) {
			Log.write("Error: can not get Font");
		}
		return type;
	}

	protected Bitmap getBitmap(int _id) {
		return BitmapFactory.decodeResource(this.getResources(), _id);
	}

	public abstract void setUp();

	public abstract void drawing(Canvas canvas);

	public abstract void processing();

	public abstract void onTouchDown(int x, int y);

}