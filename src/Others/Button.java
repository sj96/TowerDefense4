package Others;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

public abstract class Button {

	private Collider collider;

	private float x;
	private float y;

	private int height;
	private int width;

	private boolean isEnabled = true;
	
	private boolean isHover = false;

	private Bitmap bg;

	public Button(float x, float y, Bitmap bg) {
		this.bg = bg;
		this.height = bg.getHeight();
		this.width = bg.getWidth();
		this.collider = new Collider(x, y, bg.getHeight(), bg.getWidth());
		this.setX(x);
		this.setY(y);
	}

	public boolean checkEvent(float x, float y) {
		if (isEnabled) {
			// kiểm tra vị trí điểm nhấn có thuôc vào collider của button hay k?
			if (collider.isCollider(x, y)) {
				// thực hiện công việc
				// this.onClick();
				return true;
			}
		}
		return false;
	}
	
	public abstract void onClick();

	public void onRelease() {
		// công việc thực hiện sau khi button đc nhấn
	}

	public void draw(Canvas canvas) {
		Paint paint = null;
		if (!isEnabled) {
			paint = new Paint();
			paint.setColorFilter(new PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY));
		}
		// vẽ hình ảnh button
		canvas.drawBitmap(this.bg, this.getX(), this.getY(), paint);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
		this.collider.setY(y);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
		this.collider.setX(x);
	}

	public void setEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public boolean isHover() {
		return isHover;
	}

	public void setHover(boolean isHover) {
		this.isHover = isHover;
	}
}
