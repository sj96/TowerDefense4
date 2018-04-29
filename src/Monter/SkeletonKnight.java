package Monter;

import playing.*;

import Tower.Tower;
import android.graphics.Bitmap;

public class SkeletonKnight extends Monster{
	//DrawCircle DC;
	final int thuong=0;
	final int tancong=1;
	final int chet=2;
	int crImage=0;
	int rowImage=0;
	int wailtime=12;
	int effTime=50;
	int time=0;
	public SkeletonKnight(Bitmap bit, float bx, float by, Handler h) {
		super(bit,ID.skeletonKnight, bx, by, SubSetting.ruler, 8, 2, h);
		this.setInfo(20, 10, 3, 3);
		this.range=3*ruler/4;
		this.mv=ruler/50;
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
		else moveToBlock();
		if(block==null){
			findTarget();
		}
		if(block!=null){
			if(!block.isLive()){
				block=null;
				onMove=true;
				statup=thuong;
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
			return false;
		}
	}
	@Override
	public void findTarget() {
		for(int t=0; t<this.handler.getsize(); t++){
			Tower T= (Tower) handler.getGameObject(t);
			if(T.getID()==ID.tank && T.blockCount>0){
				float Rx, Ry, R;
				Rx= T.getX()+T.getWidth()/2-x-width/2;
				Ry= T.getY()+T.getHeight()/2-y-height/2;
				R=(float)Math.sqrt(Rx*Rx+Ry*Ry);
				if(R<range){
					this.block=T;
					T.blockCount=T.blockCount-1;
					this.i=this.i-1;
					onMove=false;
					t=this.handler.getsize();
				}
			}
			if(this.block!=null){
				break;
			}
		}
	}
	public void moveToBlock(){
		x=x+mx*SubSetting.timePlus;
		y=y+my*1.5f*SubSetting.timePlus;
		if(this.y<block.getY()) my=mv; else if(this.y>block.getY()+ruler/5) my=-mv; else my=0;
		if(this.x<block.getX()-range/50) mx=mv; else if(this.x>block.getX()+ruler/50) mx=-mv;
					else{
						mx=0;
						statup=tancong;
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
			if(crImage<6) crImage=crImage+1;
			if(crImage>=6) crImage=3;
			wailtime=16;
			break;
		case chet:
			if(crImage<5) crImage=5;
			if(crImage<7) crImage=crImage+1;
			break;
		default: crImage=0;
			break;
		}
	}

	@Override
	public Monster makeLone() {
		return new SkeletonKnight(bitmap, x, y, handler);
	}
}
