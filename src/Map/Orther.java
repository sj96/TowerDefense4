package Map;

import playing.*;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Orther extends Nen{

	public Orther(Bitmap bit, ID mID, float bx, float by, Handler han) {
		super(bit, mID, bx, by, SubSetting.ruler, 1,1, han);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void changerImage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(drawbitmap, SubSetting.x+x, SubSetting.y+y, null);
	}

}
