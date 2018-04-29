package Others;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;

public class Draw {

	public static void bgColor(int color, int alpha, Canvas canvas) {
		Paint myPaint = new Paint();
		myPaint.setColor(color);
		myPaint.setAlpha(alpha);
		Rect rectangle = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
		canvas.drawRect(rectangle, myPaint);
	}

	public static void drawTextShadow(String txt, float x, float y, Canvas canvas) {
		Paint shadowPaint = new Paint();
		shadowPaint.setAntiAlias(true);
		shadowPaint.setTextSize(45.0f);
		shadowPaint.setStrokeWidth(2.0f);
		shadowPaint.setStyle(Paint.Style.STROKE);
		shadowPaint.setShadowLayer(5.0f, 10.0f, 10.0f, Color.YELLOW);

		canvas.drawText(txt, x, y, shadowPaint);
	}

	public static void drawTextWithStroke(String str, float x, float y, int colorText, int colorStroke, Typeface type,
			int fontSize, int strokeSize, Align align, Canvas canvas) {
		Paint paint = new Paint();
		paint.setTypeface(type);
		paint.setTextSize(fontSize);
		paint.setTextAlign(align);

		paint.setStrokeWidth(strokeSize);
		paint.setStyle(Style.STROKE);
		paint.setColor(colorStroke);
		canvas.drawText(str, x, y, paint);
		paint.setStrokeWidth(0);
		paint.setStyle(Style.FILL);
		paint.setColor(colorText);
		canvas.drawText(str, x, y, paint);
	}

	public static void drawText(String txt, float x, float y, Typeface type, int fontSize, Align align, int color, Canvas canvas) {
		Paint paint = new Paint();
		paint.setTypeface(type);
		paint.setTextSize(fontSize);
		paint.setTextAlign(align);
		paint.setColor(color);
		canvas.drawText(txt, x, y,paint);
	}
	
	public static void drawLine(float x1, float y1, float x2, float y2, int size, int color, Canvas canvas) {
		Paint paint = new Paint();
		paint = new Paint();
		paint.setStrokeWidth(size);
		paint.setColor(color);
		canvas.drawLine(x1, y1, x2, y2, paint);
	}
}
