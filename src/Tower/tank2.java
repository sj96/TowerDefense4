package Tower;

import playing.*;

import android.graphics.Bitmap;

public class tank2 extends Tower{
	public boolean melee = true; //la can chien hay tam xa;
	Bitmap javelinBitmap = null;
	public tank2(Bitmap bit,Bitmap javelin, float bx, float by, Handler h, Handler E) {
		super(bit, ID.tank, bx, by, 20, 4, h, E);
		this.setInfo(600,  6, 8, ruler, 4);
		blockCount=3;
		javelinBitmap=javelin;
		atackimage=14;
		crImage=0;
		rowImage=0;
		subx=-width/8;
		//statup=tancong;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void setMelee(boolean m){
		this.melee=m;
		if(melee) {
			this.maxcollum=20;
			this.range=ruler;
			atackimage=14;
		}
		else{
			this.maxcollum=8;
			this.range=ruler*2;
			atackimage=7;
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
		time=time+SubSetting.timePlus;
		if(enabled){
			if(block!=null){
				CheckBlockLeft();
				if(melee){
					if(Left){
						subx=-width/8;
						rowImage=0;
					}else{ 
						rowImage=1;
						subx=width/8;
					}
				}
				else{
					if(Left){
						subx=-width/8;
						rowImage=2;
					}else{
						subx=width/8;
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
				}else {
					statup = vethuthe;
					block=null;
				}
			}else findTarget();
		}
		//xac dinh anh ve
		if(time>=8){
			hoimau();
			checkStatup();
			time=0;
		}
		if(time==0 && melee && crImage==8 && block!=null){//tai vi tri anh thu 9
			tancong();
		}
		if(time==0 && !melee && crImage==4 && block!=null){
			tancong();
		}
		this.setFrame(crImage, rowImage, wi2, he2);
	}
	@Override
	public Tower MakeClone() {
		tank2 ta = new tank2(bitmap, javelinBitmap, x, y, handler, enemylist);
		return ta;
	}
	@Override
	public void defend(int nat) {
		int s=nat-this.df;
		if(s<0) s=1;
		this.hp=this.hp-s;
	}
	@Override
	public void checkStatup(){
		switch (statup) {
		case thuong:
			crImage=0;
			break;
		case tancong:
			if(crImage<atackimage) crImage=crImage+1;
			else{
				if(melee) crImage=6;
				else crImage=1;
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
