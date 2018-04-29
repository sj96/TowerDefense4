package Others;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;

public abstract class ButtonLevel extends Button {

	private String str;
	private Typeface type;

	public ButtonLevel(float x, float y, Bitmap bg, String str, Typeface type) {
		super(x - bg.getWidth() / 2, y - bg.getHeight() / 2, bg);
		this.str = str;
		this.type = type;
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		// draw text button
		// draw Level
		Draw.drawTextWithStroke(str, getX() + getWidth()*.8f , getY() + getHeight()*1.1f, Color.WHITE, Color.BLACK, type, 40, 4, Align.CENTER, canvas);
	}
}
