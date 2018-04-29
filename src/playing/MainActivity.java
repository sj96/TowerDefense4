package playing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	//Handler handler;
	//NonManager nonmanage;
	//GameSurface GS;
	Button playbt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setContentView(R.layout.activity_main);
//		playbt = (Button) findViewById(R.id.buttonplay);
//		playbt.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				Intent i = new Intent(MainActivity.this, playActivity.class);
//				startActivity(i);
//			}
//		});
		//handler = new Handler();
		//nonmanage = new NonManager();
		//GS = new GameSurface(this, handler, nonmanage);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//TouchEvent TE = new TouchEvent(handler, event, GS.getHeight(), GS);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//TE.onTouchDown();
			break;
		case MotionEvent.ACTION_UP:
			//TE.onTouchUp();
			break;
		case MotionEvent.ACTION_MOVE:
			//TE.onTouchMove();
			break;

		}
		return true;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			System.exit(0);
		}
		return super.onKeyDown(keyCode, event);
	}
}
