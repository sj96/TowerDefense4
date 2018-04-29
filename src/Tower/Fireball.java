package Tower;

import playing.*;

import Monter.Monster;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Fireball extends GameObject{
	float mx, my, move, kx, ky;
	int k=30;
	BoomEffect boom;
	FireBallEffect FE;
	Monster block;
	Handler ha;
	public Fireball(Bitmap bit, float bx, float by, BoomEffect b, FireBallEffect fe, Monster bl, Handler h) {
		super(bit, ID.GameObject, bx, by, SubSetting.ruler,3, 1);
		x=x-width/2; y=y-height/2;
		boom=b;
		FE=fe;
		block=bl;
		ha=h;
		kx=x; ky=y;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		float rx=kx-x; float ry=ky-y;
		float R =(float) Math.sqrt(rx*rx+ry*ry);
		if(R>=width/2){
			float vx, vy;
			vx=x; vy=y;
			kx=vx; ky=vy;
			FE.setX(x);
			FE.setY(y);
			this.ha.addGameObject(FE.makeLone());
		}
		if(block!=null){
			if(k>0){
				float longwayx= (block.getX()+block.getWidth()/2)-(x+width/2);
				mx=longwayx/k;
				float longwayy= (block.getY()+block.getHeight()/2)-(y+height/2);
				my=longwayy/k;
				k=k-SubSetting.timePlus;
				x=x+mx*SubSetting.timePlus;
				y=y+my*SubSetting.timePlus;
			}
			else{
				float vx, vy;
				boom.setX(block.getX()+block.getWidth()/2);
				boom.setY(block.getY()+block.getHeight()/2);
				ha.addGameObject(boom.makeLone());
				this.noUse=true;
			}
		}else{
			boom.setX(block.getX()+block.getWidth()/2);
			boom.setY(block.getY()+block.getHeight()/2);
			ha.addGameObject(boom.makeLone());
			this.noUse=true;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(drawbitmap, x+SubSetting.x, y+SubSetting.y, null);
	}

}
