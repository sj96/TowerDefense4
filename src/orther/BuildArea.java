package orther;

import playing.*;

import Map.Nen;
import Tower.Tower;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BuildArea extends Nen{
	//khu vuc xay tru, diem xay tru
	public Bitmap SubImage;
	public boolean build = true;
	public boolean inWay = false;
	public boolean showRange=false;
	public Tower T=null;
	public DrawCircle DC = null;
	public BuildArea(Bitmap bit, float bx, float by, Handler han) {
		super(bit, ID.canbuid, bx, by, SubSetting.ruler, 1,1, han);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void changerImage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		if(T!=null && T.hp<=0){
			T=null;
		}
		if(T==null){
			DC = null;
			build = true;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(drawbitmap, x+SubSetting.x, y+SubSetting.y, null);
		if(SubImage!=null && !inWay){
			canvas.drawBitmap(SubImage, x+SubSetting.x, y+ruler+SubSetting.y-SubImage.getHeight(), null);
		}
		if(showRange && DC!=null){
			DC.draw(canvas);
		}
	}

}
