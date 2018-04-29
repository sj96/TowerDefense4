package Map;

import playing.*;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Nen21 extends Nen{
	int imgHeight, setImg, time;
	boolean left, right;
	Bitmap sub;
	public Nen21(Bitmap bit, ID mID, float bx, float by, int Ruler, int mcol, int mrow, Handler han) {
		super(bit, mID, bx, by, Ruler, mcol, mrow, han);
		left = false; right = false;
		imgHeight = this.bitmap.getHeight();
		setImg=0;
		time=0;
		sub=null;
	}

	@Override
	public void changerImage() {
		left=false;
		right=false;
		for(int i=0; i<handler.getsize(); i++){
			GameObject temp = handler.getGameObject(i);
			float tx, ty;
			tx = temp.getX();
			ty = temp.getY();
			if(temp.getID()==this.id && ty==this.y){
				if((tx+ruler) == x) left=true;
				if((x+ruler)==tx) right=true;
			}
		}
		dx=0;
		if(left && right){
			dx = width/2;
		}
		if(left && !right){
			dx = width;
		}
		setImg=0;
		this.drawbitmap = Bitmap.createBitmap(bitmap, dx, dy+setImg, width, height);
		//g.drawImage(anh.getImage(), x, y, x+ruler, y+ruler, dx, dy+setImg, dx+width, dy+height+setImg, null);
		if(!left && !right){
			sub = Bitmap.createBitmap(bitmap,(3*width/2),(dy+setImg), (width/2), height);
			//g.drawImage(anh.getImage(), x+ruler/2, y, x+ruler, y+ruler, 3*width/2, dy+setImg, 2*width, dy+height+setImg, null);
		}
	}

	@Override
	public void update() {
		time=time+SubSetting.timePlus;
		if(time>=8){
			setImg=setImg+(int)height;
			if(setImg==imgHeight){
				setImg=0;
			}
			time=0;
			this.drawbitmap = Bitmap.createBitmap(bitmap, dx, dy+setImg, width, height);
			//g.drawImage(anh.getImage(), x, y, x+ruler, y+ruler, dx, dy+setImg, dx+width, dy+height+setImg, null);
			if(!left && !right){
				sub = Bitmap.createBitmap(bitmap, (3*width/2), dy+setImg, (width/2), height);
				//g.drawImage(anh.getImage(), x+ruler/2, y, x+ruler, y+ruler, 3*width/2, dy+setImg, 2*width, dy+height+setImg, null);
			}
		}
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(drawbitmap, SubSetting.x+x, SubSetting.y+y, null);
		if(sub!=null) canvas.drawBitmap(sub, SubSetting.x+x+width/2, SubSetting.y+y, null);
	}

}
