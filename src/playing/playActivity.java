package playing;

import com.nienluannganh.towerdefense.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import menu.PauseMenu;

public class playActivity extends Activity{
	//giao dien tro choi chinh
	Handler handler;
	GameSurface GS;
	TouchEvent TE;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		handler = new Handler();
		GS = new GameSurface(this, handler);
		this.setContentView(GS);
		GS.gameplay=this;
		GS.i= new Intent(this.getApplicationContext(), activity.EndGameActivity.class);
		GS.ia = new Intent(this.getApplicationContext(), activity.MainActivity.class);
		TE = new TouchEvent(handler, GS.getHeight(), GS);
		this.setFont();
		SubSetting.x=0;
		SubSetting.y=0;
		SubSetting.wave=0;
		SubSetting.isWin=false;
		SubSetting.exit=false;
		SubSetting.scorce=0;
		SubSetting.nodie=true;
		SubSetting.heat=4;
	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(SubSetting.onpause){
				int k=SubSetting.lastSpeed;
				SubSetting.timePlus=k;
				SubSetting.onpause=false;
				/*try {
					Intent i = new Intent(this.getBaseContext(), activity.MainActivity.class);
					this.startActivity(i);
					this.finalize();
					//GS.Exit();
					//this.finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(0);
				}*/
			}else{
				int k= SubSetting.timePlus;
				SubSetting.lastSpeed=k;
				SubSetting.timePlus=0;
				SubSetting.onpause=true;
				PauseMenu P = new PauseMenu(BitmapFactory.decodeResource(GS.getResources(), R.drawable.playing_gbpause), 0, 0, SubSetting.ruler, this.GS);
				handler.addGameObjectOnTop(P);
				P.setup();
			}
			
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		TE.setMotionEvent(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			TE.onTouchDown();
			break;
		case MotionEvent.ACTION_UP:
			TE.onTouchUp();
			break;
		case MotionEvent.ACTION_MOVE:
			TE.onTouchMove();
			break;
		}
		return true;
	}
	public void setFont(){
		GameFont.type1 = Typeface.createFromAsset(this.getAssets(), "fonts/iciel_Cadena.ttf");
	}
}
