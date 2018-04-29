package orther;

import playing.*;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class DrawCircle extends GameObject {
	//vong tron xac dinh tam danh cua tru
	public float R;
	public Paint p;
	public Paint OV;
	public int tamdanh = Color.argb(125, 0, 204, 153);
	public int ovuonT = Color.argb(100, 0, 128, 129);
	public int ovuonF = Color.argb(100, 254, 111, 94);

	public DrawCircle(ID mID, float bx, float by, int Ruler, float r) {
		super(mID, bx, by, Ruler);
		R = r;
		p = new Paint();
		OV = new Paint();
		p.setColor(ovuonF);
		OV.setColor(ovuonF);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawCircle(x+SubSetting.x, y+SubSetting.y, R, p);
		canvas.drawRect(x-ruler/2+SubSetting.x, y-ruler/2+SubSetting.y, x+ruler/2+SubSetting.x, y+ruler/2+SubSetting.y, OV);
	}

}
