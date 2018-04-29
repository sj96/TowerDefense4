package Monter;

import playing.*;

import android.graphics.Bitmap;

public class SkeletonNorman extends Monster{
	//DrawCircle DC;
	final int thuong=0;
	final int tancong=1;
	final int chet=2;
	int crImage=0;
	int rowImage=0;
	int wailtime=12;
	int effTime=50;
	int time=0;
	Handler GameHan;
	Bitmap xuongbitmap;
	public SkeletonNorman(Bitmap bit, Bitmap xbm, float bx, float by, Handler h, Handler TList) {
		super(bit,ID.skeletonN, bx, by, SubSetting.ruler, 7, 2, TList);
		xuongbitmap=xbm;
		this.block=null;
		this.setInfo(20, 20, 0, 1);
		this.range=2*ruler;
		this.mv=ruler/50;
		GameHan=h;
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
			xuong xuo = new xuong(xuongbitmap, x+width/2, y+height/2, at, this.block);
			this.GameHan.addGameObject(xuo);
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
		if(this.x<0 || this.y<0){
			if(this.onMove) move();
		}
		else{
			if(this.onMove) move();
			if(block==null){
				findTarget();
			}
			if(block!=null){
				if(!block.isLive()){
					block=null;
					onMove=true;
					statup=thuong;
				}
				else{
					statup=tancong;
				}
			}	
		}
		if(time>=wailtime){
			time=0;
			checkStatup();
		}
		if(crImage==5 && time==0){
			tancong();
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
			if(crImage<3) crImage=crImage+1;
			if(crImage>=3) crImage=0;
			wailtime=12;
			break;
		case tancong:
			this.onMove=false;
			if(crImage<6) crImage=crImage+1;
			if(crImage>=6) crImage=3;
			wailtime=16;
			break;
		case chet:
			crImage=6;
			break;
		default: crImage=0;
			break;
		}
	}

	@Override
	public Monster makeLone() {
		// TODO Auto-generated method stub
		return new SkeletonNorman(bitmap, xuongbitmap, x, y, GameHan, handler);
	}
}
