package Tower;

import playing.*;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class RedyCast extends GameObject{
	int time=3;
	int wail=5;
	int img=1;
	int dr, dc;
	public int hp;
	public RedyCast(Bitmap bit, float bx, float by) {
		super(bit, ID.GameObject, bx, by, SubSetting.ruler, 5, 2);
		x=x-width/2; y=y-height/2;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		time= time-SubSetting.timePlus;
		dr=img/5;
		dc=img%5;
		this.setFrame(dc, dr);
		if(time<=0){
			img=img+1;
			time=3;
		}
		if(img==10){
			img=8;
		}
		wail=wail-SubSetting.timePlus;
		if(wail<=0) noUse=true;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(drawbitmap, x+SubSetting.x, y+SubSetting.y, null);
	}

}
