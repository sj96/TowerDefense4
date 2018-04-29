package Map;

import playing.*;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Nen23 extends Nen{
	int imgWidth, setImg, time;
	Bitmap sub1, sub3, sub7, sub9;
	//time=100 :1s;
	//setImg: chi so xac dinh vi tri anh dong
	//setImg=n*2*width, setImg<imgWidth
	//imgWidth: chi so chieu ngan cua anh goc
	//sub*: cac anh phu cua vi tri tuong ung
	boolean left, right, up, down, v1, v3, v7, v9; //chi tiet vi tri xem tai changeImage
	public Nen23(Bitmap bit, ID mID, float bx, float by, int Ruler, int mcol, int mrow, Handler han) {
		super(bit, mID, bx, by, Ruler, mcol, mrow, han);
		left = false; right = false; up = false; down = false;
		v1=false; v3=false; v7=false; v9=false;
		imgWidth = this.bitmap.getWidth();
		setImg=0;
		time=0;
		sub1=null;
		sub3=null;
		sub7=null;
		sub9=null;
	}

	@Override
	public void changerImage() {
		// v1   up    v3
		//left this right
		// v7  down   v9
		left = false; right = false; up = false; down = false;
		v1=false; v3=false; v7=false; v9=false;
		for(int i=0; i<handler.getsize(); i++){
			GameObject temp = handler.getGameObject(i);
			float tx, ty;
			tx = temp.getX();
			ty = temp.getY();
			if(temp.getID()==this.id){
				if(ty==y){
					if((tx+ruler) == x) left=true;
					if((x+ruler)==tx) right=true;
				}else
				if(tx==x){
					if((ty+ruler)==y) up=true;
					if((y+ruler)==ty) down =true;
				}else
				if((x-ruler)==tx){
					if(ty+ruler==y) v1=true;
					if((y+ruler)==ty) v7=true;
				}else
					if((x+ruler)==tx){
					if(ty+ruler==y) v3=true;
					if((y+ruler)==ty) v9=true;
				}
			}
		}
		if(!left && !right && !up && !down){dx=0; dy=0;}
		else{
			dx=0;
			dy=height;
			if(left && !right) dx= width;
			if(!left && right) dx= 0;
			if(left && right) dx=width/2;
			if(up && !down) dy= 2*height;
			if(up && down) dy=(height*3)/2;
		}
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		time=time+SubSetting.timePlus;
		if(time>=20){
			setImg=setImg+(2*width);
			if(setImg==imgWidth){
				setImg=0;
			}
			time=0;
			this.drawbitmap = Bitmap.createBitmap(bitmap, dx+setImg, dy, width, height);
			if((up || down) && (!left && !right)){
				sub3 = Bitmap.createBitmap(bitmap, ((3*width)/2)+setImg, dy, (width/2), height);
				//g.drawImage(anh.getImage(), x+ruler/2, y, x+ruler, y+ruler, ((3*width)/2)+setImg, dy, 2*width+setImg, dy+height, null);
			}
			if((left || right) && (!up && !down)){
				sub7 = Bitmap.createBitmap(bitmap, dx+setImg,((5*height)/2), width,  (height/2));
				//g.drawImage(anh.getImage(), x, y+ruler/2, x+ruler, y+ruler, dx+setImg, (5*height)/2, dx+width+setImg, 3*height, null);
			}
			if(left && up && !v1){
				sub1 = Bitmap.createBitmap(bitmap, width+setImg, 0, (width/2), (height/2));
				//g.drawImage(anh.getImage(), x, y, x+ruler/2, y+ruler/2, width+setImg, 0, (3*width)/2+setImg, height/2, null);
			}
			if(right && up && !v3){
				sub3 = Bitmap.createBitmap(bitmap, ((3*width)/2)+setImg, 0, width/2, height/2);
				//g.drawImage(anh.getImage(), x+ruler/2, y, x+ruler, y+ruler/2, ((3*width)/2)+setImg, 0, 2*width+setImg, height/2, null);
			}
			if(left && down && !v7){
				sub7 = Bitmap.createBitmap(bitmap, width+setImg, height/2, width/2, height/2);
				//g.drawImage(anh.getImage(), x, y+ruler/2, x+ruler/2, y+ruler, width+setImg, height/2, (3*width)/2+setImg, height, null);
			}
			if(right && down && !v9){
				sub9 = Bitmap.createBitmap(bitmap, ((3*width)/2)+setImg, height/2, width/2, height/2);
				//g.drawImage(anh.getImage(), x+ruler/2, y+ruler/2, x+ruler, y+ruler, ((3*width)/2)+setImg, height/2, 2*width+setImg, height, null);
			}
		}
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(drawbitmap, SubSetting.x+x, SubSetting.y+y, null);
		if(sub1!=null) canvas.drawBitmap(sub1, SubSetting.x+x, SubSetting.y+y, null);
		if(sub3!=null) canvas.drawBitmap(sub3, SubSetting.x+x+width/2, SubSetting.y+y, null);
		if(sub7!=null) canvas.drawBitmap(sub7, SubSetting.x+x, SubSetting.y+y+height/2, null);
		if(sub9!=null) canvas.drawBitmap(sub9, SubSetting.x+x+width/2, SubSetting.y+y+height/2, null);
	}

}
