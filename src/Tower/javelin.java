package Tower;

import playing.*;

import Monter.Monster;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class javelin extends GameObject{
	Monster block;
	int dame;
	int dcol, drow; 
	float sp, ls, kx, ky, mx, my;//ls quang duong di, sp la toc do, kx: khoan cach theo x 
	//mx toc do theo x, my toc do theo y
	//dang viet
	public javelin(Bitmap bit, float bx, float by, int da, Monster bl) {
		super(bit, ID.GameObject, bx, by,SubSetting.ruler, 4, 2);
		this.noUse=false;
		this.block = bl;
		dame=da;
		sp=ruler/7.5f;
		checkImage();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		if(block!=null && !noUse){
			kx=block.getX()-x;
			ky=block.getY()-y;
			ls = (float) Math.sqrt(kx*kx+ky*ky);
			int fr= (int) (ls/sp); //fr so frame
			if(fr<0) fr=1;
			mx=kx/fr;
			my=ky/fr;
			x=x+mx*SubSetting.timePlus;
			y=y+my*SubSetting.timePlus;
			vacham();
		}else{
			this.noUse=true;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(drawbitmap, SubSetting.x+x, SubSetting.y+y,null);
	}
	public void vacham(){
		if(x+width/2>block.getX() && x+width/2<block.getX()+block.getWidth() && y+height/2>block.getY() && y+height/2<block.getY()+block.getHeight()){
			block.defend(this.dame);
			this.noUse=true;
		}
	}
	public void checkImage(){
		if(this.block.getX()+block.getWidth()/2<=this.x+width/2) drow=0;
		else drow=1;
		if(this.block.getY()<this.y-ruler && (block.getX()+block.getWidth()/2)>(x+width/2-ruler) &&  (block.getX()+block.getWidth()/2)<(x+width/2+ruler)) dcol=3;
		else if(this.block.getY()>this.y+ruler &&  (block.getX()+block.getWidth()/2)>(x+width/2-ruler) &&  (block.getX()+block.getWidth()/2)<(x+width/2+ruler)) dcol=2;
		else {
			if(this.block.getY()<y) dcol=0;
			else dcol=1;
		}
		this.setFrame(dcol, drow);
	}
}
