package playing;

import java.io.InputStream;

import com.nienluannganh.towerdefense.R;

import Monter.Monster;
import Others.Audio;
import Tower.Tower;
import Tower.tank2;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import menu.PlayInterface;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback{
	public Handler handler, enemylist, towerlist;
	GameUpdate gameupdate;
	public Activity gameplay;
	int ruler, wait;
//	Others.Audio audio;
	public Intent i;
	Intent ia;
	//Bitmap buttonplay, buttoncd, buttontv, titleimg, bg;
	Bitmap bg, tankimg;
	public GameSurface(Context context, Handler h) {
		super(context);
		this.handler = h;
		this.setFocusable(true);
		this.getHolder().addCallback(this);
		SubSetting.timePlus=1;
		enemylist = new Handler();
		//enemylist.LinkTo(handler);
		towerlist= new Handler();
		wait=100;
		//towerlist.LinkTo(handler);
		//InputStream s = this.getResources().openRawResource(R.raw.map);
		// TODO Auto-generated constructor stub
	}
	public void update(){
		handler.update();
		for(int i=0; i<handler.getsize(); i++){
			if(handler.getGameObject(i).noUse){
				handler.removeGameObject(i);
			}
		}
		for(int i=0; i<towerlist.getsize(); i++){
			Tower T = (Tower)towerlist.getGameObject(i);
			if(!T.isLive()){
				SubSetting.nodie=false;
				handler.removeGameObject(T);
				towerlist.removeGameObject(T);
			}
		}
		for(int i=0; i<enemylist.getsize(); i++){
			Monster T = (Monster)enemylist.getGameObject(i);
			if(!T.isLive()){
				SubSetting.scorce = SubSetting.scorce + T.getPoint();
				if((T.id==ID.skeletonKnight || T.id==ID.skeletonBig) && T.block!=null){
					T.block.blockCount=T.block.blockCount+1;
				}
				handler.removeGameObject(T);
				enemylist.removeGameObject(T);
			}
		}
		if(SubSetting.heat==0 && wait==100){
			//lost
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			i.putExtra("Status", Surface.EndGame.statusValue.LOSE.ordinal());
			wait=wait-1;
			//SubSetting.timePlus=0;
		}
		if(SubSetting.isWin && enemylist.getsize()==0 && wait==100){
			//win
			int sc = SubSetting.scorce;//diem
			int start=3;
			if(!SubSetting.nodie){
				start=start-1;
			}
			if(SubSetting.heat<4){
				start=start-1;
			}
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
			i.putExtra("Status", Surface.EndGame.statusValue.WIN.ordinal());
			i.putExtra("Level", SubSetting.lever);
			i.putExtra("Score", sc);
			i.putExtra("Star", start);
			wait=wait-1;
//			else lose
//			i.putExtra("Status", Surface.EndGame.statusValue.LOSE.ordinal());
		}
		if(wait<100){
			wait=wait-1;
		}
		if(wait==0){
			gameplay.startActivity(i);
			SubSetting.x=0;
			SubSetting.y=0;
			SubSetting.wave=0;
			SubSetting.isWin=false;
			SubSetting.exit=false;
			SubSetting.scorce=0;
			SubSetting.nodie=true;
			SubSetting.heat=4;
			gameplay.finish();
		}
		if(SubSetting.exit){
			/*for(int i=0; i<handler.getsize(); i++){
				handler.removeGameObject(i);
			}*/
			ia.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			gameplay.startActivity(ia);
			gameplay.finish();
		}
	}
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		//handler.sortGameObject();
		//canvas.drawBitmap(bg, 0, 0, null);
		handler.draw(canvas);
		//System.out.println("###");
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		ruler = this.getHeight();
		Setup();
		this.gameupdate = new GameUpdate(this, holder);
		this.gameupdate.setRunning(true);
		this.gameupdate.start();
		System.out.println("#");
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		while(true){
			try {
				this.gameupdate.setRunning(false);
				this.gameupdate.join();
//				this.audio.setRunning(false);
				if(!this.gameupdate.isAlive()){
					break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void Exit() throws Throwable{
		this.gameupdate.setRunning(false);
		System.exit(0);
	}
	public void Setup(){
		//bg = BitmapFactory.decodeResource(this.getResources(), R.drawable.bg);
		tankimg = BitmapFactory.decodeResource(this.getResources(), R.drawable.da);
		SubSetting.ruler=tankimg.getWidth()/2;
		InputStream input;
		switch (SubSetting.lever) {
		case 1:
			input = getResources().openRawResource(R.raw.map7);
			break;
		case 2:
			input = getResources().openRawResource(R.raw.map5);
			break;
		case 3:
			input = getResources().openRawResource(R.raw.map8);
			break;
		case 4:
			input = getResources().openRawResource(R.raw.map9);
			break;
		case 5:
			input = getResources().openRawResource(R.raw.map10);
			break;
		case 6:
			input = getResources().openRawResource(R.raw.map11);
			break;
		case 7:
			input = getResources().openRawResource(R.raw.map7h);
			break;
		case 8:
			input = getResources().openRawResource(R.raw.map5h);
			break;
		case 9:
			input = getResources().openRawResource(R.raw.map8h);
			break;
		case 10:
			input = getResources().openRawResource(R.raw.map9h);
			break;
		case 11:
			input = getResources().openRawResource(R.raw.map10h);
			break;
		case 12:
			input = getResources().openRawResource(R.raw.map11h);
			break;
		default:
			input = getResources().openRawResource(R.raw.map7);
			break;
		}
		
		PlayInterface playI = new PlayInterface(0, 0, SubSetting.ruler, this, input);
		handler.addGameObjectOnTop(playI);
		
		
		//sound 
//		audio = new Audio(gameplay.getBaseContext());
//		
//		audio.playBG(R.raw.awakening);
		
		
		/*MapGame map = new MapGame(0, 0, SubSetting.ruler, this);
		InputStream input = getResources().openRawResource(R.raw.map3);
		map.loadMap(input);
		try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		handler.addGameObject(map);*/
		//map.loadMap();
		//tank = new tank2(tankimg, ID.tank, this.getWidth()/2, this.getHeight()/2, SubSetting.ruler, handler);
		//handler.addGameObject(tank);
		/*buttonplay = BitmapFactory.decodeResource(this.getResources(), R.drawable.button);
		buttoncd = BitmapFactory.decodeResource(this.getResources(), R.drawable.cd);
		buttontv = BitmapFactory.decodeResource(this.getResources(), R.drawable.tv);
		MainMenu menu = new MainMenu(bg, ID.menu, 0, 0,ruler , 1, 1);
		menu.setHandler(handler);
		this.handler.addGameObject(menu);
		menu.setupButton(buttonplay, buttontv, buttoncd, this.getWidth());*/
	}
	/*@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			System.exit(0);
		}
		return super.onKeyDown(keyCode, event);
	}*/
	
	
}
