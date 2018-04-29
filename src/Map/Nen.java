package Map;

import playing.*;

import android.graphics.Bitmap;

public abstract class Nen extends GameObject{
	Handler handler;
	int dx, dy;
	//ruler cua nen la width
	public Nen(Bitmap bit, ID mID, float bx, float by, int Ruler, int mcol, int mrow, Handler han) {
		super(bit, mID, bx, by, Ruler, mcol, mrow);
		this.handler=han;
		dx=0; dy=0;
		// TODO Auto-generated constructor stub
	}
	public abstract void changerImage();
}
