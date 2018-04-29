package menu;

import playing.*;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;

public class Button extends GameObject{
	//doi tuong nay khong duoc su dung nua
	String text="";
	public Paint p;
	float mx, my;
	public float TextSize1;
	public float TextSize2;
	public Button(Bitmap bit, ID mID, float bx, float by, int ruler, int mcol, int mrow) {
		super(bit, mID, bx, by, ruler, mcol, mrow);
		this.type = Type.button;
		// TODO Auto-generated constructor stub
		TextSize1 = 2*this.height/5;
		TextSize2 = TextSize1*4/5;
		p= new Paint();
		p.setColor(Color.WHITE);
		p.setTextAlign(Align.CENTER);
		p.setTextSize(TextSize1);
		mx=x;
		my=y;
	}
	public void setText(String s){
		text = s;
	}

	@Override
	public void update() {
		if(x<mx){
			x=x+ruler/50;
		}
		if(x>mx){
			x=x-ruler/50;
		}
		if(y<my){
			y=y+ruler/50;
		}
		if(y>my){
			y=y-ruler/50;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(this.drawbitmap, x, y, null);
		canvas.drawText(text, x+this.width/2, y+(2*this.height/3), p);
	}
	public void setMoveTo(float toX, float toY){
		this.mx=toX;
		this.my=toY;
	}
}
