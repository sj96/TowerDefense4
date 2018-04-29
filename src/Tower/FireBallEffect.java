package Tower;

import playing.*;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class FireBallEffect extends GameObject{
	int Time=15;
	public FireBallEffect(Bitmap bit, float bx, float by) {
		super(bit, ID.GameObject, bx, by, SubSetting.ruler, 3, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		Time=Time-SubSetting.timePlus;
		if(Time>10) this.setFrame(0, 0);
		else if(Time>5) this.setFrame(1, 0);
		else this.setFrame(2, 0);
		if(Time<=0) noUse=true;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(drawbitmap, x+SubSetting.x, y+SubSetting.y, null);
	}
	public FireBallEffect makeLone(){
		return new FireBallEffect(bitmap, x, y);
	}
}
