package menu;

import playing.*;

import com.nienluannganh.towerdefense.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class PauseMenu extends GameObject{
	GameSurface GS;
	ImageButton tieptuc, vemenu;
	Bitmap title;
	public PauseMenu(Bitmap bit, float bx, float by, int Ruler, GameSurface gsg) {
		super(bit, ID.PauseMenu, bx, by, Ruler, 1,1);
		GS=gsg;
		x=GS.getWidth()/2-width/2;
		y=GS.getHeight()/2-height/2;
		Bitmap img1, img2;
		title = BitmapFactory.decodeResource(GS.getResources(), R.drawable.playing_title);
		img1=BitmapFactory.decodeResource(GS.getResources(), R.drawable.playing_continue);
		tieptuc = new ImageButton(img1, GS.getWidth()/2-img1.getWidth()/2, y+3*ruler, Ruler, 1) {
			@Override
			public void onclick() {
				int k=SubSetting.lastSpeed;
				SubSetting.timePlus=k;
				SubSetting.onpause=false;
			}
		};
		img2=BitmapFactory.decodeResource(GS.getResources(), R.drawable.playing_backtomenu);
		vemenu = new ImageButton(img2, GS.getWidth()/2-img1.getWidth()/2, y+4*Ruler, Ruler, 1) {
			
			@Override
			public void onclick() {
				SubSetting.exit=true;
			}
		};
		// TODO Auto-generated constructor stub
	}
	public void setup(){
		GS.handler.addGameObjectOnTop(tieptuc);
		GS.handler.addGameObjectOnTop(vemenu);
	}
	@Override
	public void update() {
		if(SubSetting.timePlus!=0){
			GS.handler.removeGameObject(vemenu);
			GS.handler.removeGameObject(tieptuc);
			GS.handler.removeGameObject(this);
		}
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, x,  y, null);
		canvas.drawBitmap(title, x+width/2-title.getWidth()/2,  y+ruler, null);
	}

}
