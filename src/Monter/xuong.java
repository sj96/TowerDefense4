package Monter;

import playing.*;

import Tower.Tower;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class xuong extends GameObject{
	//doi tuong cuc suong, dung cho SkeletonNorman
	Tower block=null;
	float speed, mx, my;
	int Fr, dame, time=5, crImg=0;
	public xuong(Bitmap bit, float bx, float by, int da, Tower bl) {
		super(bit, ID.GameObject, bx, by, SubSetting.ruler, 4, 1);
		block=bl;
		dame=da;
		speed=ruler/20;
		float kx, ky;
		kx=block.getX()+block.getWidth()/2-x+width/2;
		ky=block.getY()+block.getHeight()/2-y+height/2;
		float R = (float) Math.sqrt(kx*kx+ky*ky);
		Fr =(int)(R/speed);
		if(Fr<1) Fr=1;
		mx=kx/Fr;
		my=ky/Fr;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		if(block!=null){
			x=x+mx*SubSetting.timePlus;
			y=y+my*SubSetting.timePlus;
			Fr=Fr-SubSetting.timePlus;
			if(Fr<=0 && !noUse){
				block.defend(dame);
				noUse=true;
			}
		}else{
			noUse=true;
		}
		time=time-SubSetting.timePlus;
		if(time<=0){
			crImg=crImg+1;
			if(crImg==4) crImg=0;
			time=5;
		}
		this.setFrame(crImg, 0);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(drawbitmap, x+SubSetting.x, y+SubSetting.y, null);
	}

}
