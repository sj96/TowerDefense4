package Tower;

import playing.*;

import Monter.Monster;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BoomEffect extends GameObject{
	Handler h;
	int img=0;
	int dame;
	int time=3;
	int dc, dr;
	public BoomEffect(Bitmap bit, float bx, float by, int da, Handler eList) {
		super(bit,ID.GameObject, bx, by, SubSetting.ruler, 5, 3);
		x=x-width/2; y=y-height/2;
		dame=da;
		h=eList;
		dc=0; dr=0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		time=time-SubSetting.timePlus;
		dc=img%5;
		dr=img/5;
		this.setFrame(dc, dr);
		if(time<=0){
			img=img+1;
			time=3;
		}
		if(img==7 && time==3){
			tancong();
		}
		if(img==15){
			img=0;
			noUse=true;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(drawbitmap, x+SubSetting.x, y+SubSetting.y, null);
	}
	public void tancong(){
		for(int i=0; i<h.getsize(); i++){
			Monster temp=(Monster)h.getGameObject(i);
			float bx = temp.getX()+temp.getWidth()/2;
			float by =  temp.getY()+temp.getHeight()/2;
			if(bx>x && bx<x+width && by>y && by<y+height){
				temp.defend(dame);
			}
		}
	}
	public BoomEffect makeLone(){
		return new BoomEffect(bitmap, x, y, dame, h);
	}
}
