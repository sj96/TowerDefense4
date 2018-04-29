package Monter;

import playing.*;

import android.graphics.Bitmap;

public class SkeletonRun extends Monster{
	//DrawCircle DC;
	final int thuong=0;
	final int tancong=1;
	final int chet=2;
	int crImage=0;
	int rowImage=0;
	int wailtime=6;
	int effTime=50;
	int time=0;
	public SkeletonRun(Bitmap bit, float bx, float by, Handler h) {
		super(bit,ID.skeletonRun, bx, by, SubSetting.ruler, 5, 2, h);
		this.setInfo(15, 0, 0, 4);
		this.range=0;
		this.mv=ruler/20;
		//DC=new DrawCircle(ID.GameObject, x, y, ruler, ruler/2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void defend(int nat) {
		int c= nat-df;
		if(c<0) this.hp=this.hp-1;
		else this.hp=this.hp-c;
	}


	@Override
	public void tancong() {
		if(this.block!=null){
			this.block.defend(at);
		}
	}

	@Override
	public void update() {
		//DC.setX(x+width/2);
		//DC.setY(y+height/2);
		checkLooking();
		effTime=effTime-SubSetting.timePlus;
		if(effTime<=0){
			EffectDo();
			effTime=50;
		}
		if(effTime%5==0){
			EffectDraw();
		}
		if(Left) rowImage=1;
		else rowImage=0;
		time=time+SubSetting.timePlus;
		if(this.hp<=0) this.handler.removeGameObject(this);
		if(this.onMove) move();
		if(time>=wailtime){
			time=0;
			checkStatup();
		}
		this.setFrame(crImage, rowImage, width, height);
	}
	@Override
	public boolean isLive() {
		if(this.hp>0) return true;
		else{
			if(block!=null) block.blockCount=block.blockCount+1;
			return false;
		}
	}
	public void checkStatup(){
		switch (statup) {
		case thuong:
			if(crImage<4) crImage=crImage+1;
			if(crImage>=4) crImage=0;
			wailtime=6;
			break;
		case chet:
			crImage=4;
			break;
		default: crImage=0;
			break;
		}
	}

	@Override
	public Monster makeLone() {
		// TODO Auto-generated method stub
		return new SkeletonRun(bitmap, x, y, handler);
	}
}
