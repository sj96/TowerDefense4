package Map;

import playing.*;


import Tower.Tower;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

public class TowerIcon extends GameObject{
	//bieu tuong cua tru, dung de xay tru trong luc choi
	Tower T; //loai tru
	int numtower; //so luong tru co the xay
	Paint p;
	public TowerIcon(Bitmap bit, float bx, float by, int Ruler, Tower t, int numT) {
		super(bit, ID.TowerIcon, bx, by, Ruler, 1, 1);
		T=t;
		numtower=numT;
		p=new Paint();
		p.setColor(Color.WHITE);
		p.setTextSize(width/2);
		p.setTypeface(GameFont.type1);
		// TODO Auto-generated constructor stub
	}
	public void setTower(Tower Tow){
		T= Tow;
	}

	@Override
	public void update() {
		if(T.getBuildPoint()>SubSetting.BuildPoint || numtower==0){
			p.setAlpha(50);
		}else {
			p.setAlpha(255);
		}
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(drawbitmap, x, y, p);
		canvas.drawText(""+numtower, x+width/2, y+height, p);
	}
	public Tower CreatTower(){
		if(T!=null && numtower>0 && SubSetting.timePlus!=0)	{
			T.setX(x+width/2-SubSetting.x);
			T.setY(y+height/2-SubSetting.y);
			return T.MakeClone();
		}
		else return null;
	}
	public void numTowerDown(){
		this.numtower=this.numtower-1;
	}
}
