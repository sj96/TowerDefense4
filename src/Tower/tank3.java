package Tower;

import playing.*;

import android.graphics.Bitmap;

public class tank3 extends Tower {
	public boolean melee = true; //la can chien hay tam xa;
	
	int cd=12;
	Bitmap javelinBitmap = null;
	public tank3(Bitmap bit, Bitmap javelin, float bx, float by, Handler h, Handler E) {
		super(bit, ID.tank, bx, by, 22, 4, h, E);
		this.setInfo(750, 10, 15, ruler*2, 8);
		blockCount=4;
		javelinBitmap=javelin;
		atackimage=19;
		crImage=0;
		rowImage=0;
		time=3;
		subx=-ruler;
		//statup=tancong;
		//setMelee(false);
		//this.rowImage=2;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void setMelee(boolean m){
		this.melee=m;
		if(melee) {
			this.maxcollum=22;
			this.range=ruler;
			atackimage=19;
		}
		else{
			this.maxcollum=5;
			this.range=ruler*2;
			atackimage=4;
		}
	}

	@Override
	public void tancong() {
		if(melee){
			block.defend(this.at);
		}
		else{
			javelin ja = new javelin(javelinBitmap, x, y+dry, at, block);
			this.handler.addGameObject(ja);
		}
		if(!block.isLive()){
			this.block=null;
			this.statup=vethuthe;
		}
	}

	@Override
	public void update() {
		time=time-SubSetting.timePlus;
		cd=cd-SubSetting.timePlus;
		if(cd<=0){
			hoimau();
			cd=12;
		}
		if(enabled){
			if(block!=null){
				this.CheckBlockLeft();
				if(melee){
					if(Left){
						subx=-ruler;
						rowImage=0;
					}else{
						subx=ruler;
						rowImage=1;
					}
				}
				else{
					if(Left){
						rowImage=2;
					}else{
						subx=ruler/2;
						rowImage=3;
					}
				}
				float kx, ky, rg;
				kx= block.getX()-this.x;
				ky= block.getY()-this.y;
				rg = (float) Math.sqrt(kx*kx +ky*ky) ;
				//kiem tra muc tieu co trong tam danh hay khong
				if(rg<=this.range){
					statup = tancong;
				}else{
					statup=vethuthe;
					block=null;
				}
			}else findTarget();
		}
		//xac dinh anh ve
		if(time<=0){
			checkStatup();
			time=6;
		}
		if(time==6 && melee && crImage==10){//tai vi tri anh thu 11
			tancong();
		}
		if(time==6 && !melee && crImage==4){
			tancong();
		}
		this.setFrame(crImage, rowImage, wi2, he2);
	}
	@Override
	public Tower MakeClone() {
		tank3 ta = new tank3(bitmap, javelinBitmap, x, y, handler, enemylist);
		return ta;
	}
	@Override
	public void defend(int nat) {
		int s=nat-this.df;
		if(s<0) s=1;
		this.hp=this.hp-s;
	}
	@Override
	public void checkStatup() {
		switch (statup) {
		case thuong:
			crImage=0;
			break;
		case tancong:
			if(crImage<atackimage) crImage=crImage+1;
			else{
				if(melee) crImage=1;
				else crImage=0;
			}
			break;
		case vethuthe:
			if(crImage<maxcollum-1) crImage=crImage+1;
			else{
				crImage=0;
				statup=thuong;
			}
			break;
		default: crImage=0;
			break;
		}
	}
	public void hoimau(){
		if(this.hp<maxhp) this.hp=this.hp+1;
	}
}
