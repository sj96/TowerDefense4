package Tower;

import playing.*;

import Monter.Monster;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import orther.GameEffect;

public class arow extends GameObject{
	Monster block;
	int useRow=0;
	int dame;
	float speedy;
	float speedx;
	float maxy;
	int k=25;
	//int t=65;//35 farme len va 30 frame doi huong do giam toc speedy
	GameEffect ge =null;
	public arow(Bitmap bit, float bx, float by, int da, Monster m) {
		super(bit, ID.GameObject, bx, by, SubSetting.ruler, 4, 2);
		block=m;
		this.dame=da;
		checkImg();
		/*if(this.block.getY()<this.getY()) maxy=this.block.getY()-(2*SubSetting.ruler);
		else maxy=this.getY()-(2*SubSetting.ruler);
		float longway;
		longway= maxy-this.getY();
		speedy=longway/45;
		vy=speedy/15;*/
		this.setFrame(2, 0);
	}
	public void setGe(GameEffect ge) {
		this.ge = ge;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(block!=null){
			vacham();
			checkImg();
			if(k>0){
				float longwayx= block.getX()-x;
				speedx=longwayx/k;
				float longwayy= block.getY()-y;
				speedy=longwayy/k;
				k=k-SubSetting.timePlus;
			}
			else{
				block.defend(dame);
				if(ge!=null){
					block.getEffect(ge);
				}
				noUse=true;
			}
		}else{
			noUse=true;
		}
		this.y=this.y+speedy*SubSetting.timePlus;
		this.x=this.x+speedx*SubSetting.timePlus;
		/*if(t>0) t=t-SubSetting.timePlus;
		if(t<=30) speedy=speedy-(vy*SubSetting.timePlus);
		if(t<0) {
			t=t+1;
			speedy=speedy+vy;
		}
		if(block.getX()+block.getWidth()/2>this.x+width/2) useRow=1;
		else useRow=0;
		if(t<=30 && t>=15){
			this.setFrame(1, useRow);
		}
		if(t<15) this.setFrame(3, useRow);
		if(this.y<=maxy) this.setFrame(0, useRow);*/
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(drawbitmap, x+SubSetting.x, y+SubSetting.y, null);
	}
	public void vacham(){
		if(!noUse && x+width/2>block.getX() && x+width/2<block.getX()+block.getWidth() && y+height/2>block.getY() && y+height/2<block.getY()+block.getHeight()){
			block.defend(this.dame);
			if(ge!=null){
				block.getEffect(ge);
			}
			this.noUse=true;
		}
	}
	public void checkImg(){
		if(block.getX()+block.getWidth()/2<this.x+width/2) useRow=0;
		else if(block.getX()+block.getWidth()/2>this.x+width/2) useRow =1;
		if(block.getX()<this.x+width/2 && block.getX()+block.getWidth()>this.x+width/2){
			if(block.getY()+block.getHeight()/2<y+height/2) this.setFrame(2, 0);
			else this.setFrame(2, 1);
		}else{
			if(block.getY()<y)  this.setFrame(1, useRow);
			else if(block.getY()>y+height)  this.setFrame(3 , useRow);
			else{
				this.setFrame(0, useRow);
			}
		}
	}
}
