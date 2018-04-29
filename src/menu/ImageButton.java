package menu;

import playing.*;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class ImageButton extends GameObject{

	public ImageButton(Bitmap bit, float bx, float by, int Ruler, int maxcol) {
		super(bit, ID.ibutton, bx, by, Ruler, maxcol, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(drawbitmap, x, y, null);
	}
	public abstract void onclick();

}
