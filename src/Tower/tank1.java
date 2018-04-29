package Tower;

import playing.*;

import Monter.Monster;
import android.graphics.Bitmap;

public class tank1 extends Tower{
	public boolean melee = true; //la can chien hay tam xa;
	Bitmap javelinBitmap = null;
	public tank1(Bitmap bit, Bitmap javelin, float bx, float by, Handler h, Handler EnemyList) {
		super(bit, ID.tank, bx, by, 12, 4, h, EnemyList);
		this.setInfo(480,  5, 4, ruler, 2);
		this.setMelee(true);
		this.blockCount=2;
		javelinBitmap=javelin;
		atackimage=7;
		crImage=0;
		rowImage=0;
		//statup=tancong;

		// TODO Auto-generated constructor stub
	}
	@Override
	public void setMelee(boolean m){//dat lai day la can chien hay tam xa
		this.melee=m;
		if(melee) {
			this.maxcollum=12;
			this.range=ruler;
			atackimage=7;
		}
		else{
			this.maxcollum=10;
			this.range=ruler*2;
			atackimage=6;
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
		//kiem tra muc tieu ben trai hay phai, neu khong co muc tieu thi tien hanh tim muc tieu
		time=time+SubSetting.timePlus;
		if(enabled) {
			if(block!=null){
				this.CheckBlockLeft();
				if(melee){
					if(Left){
						subx=0;
						rowImage=0;
					}else{
						subx=width/4;
						rowImage=1;
					}
				}
				else{
					if(Left){
						subx=0;
						rowImage=2;
					}else{
						subx=width/4;
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
					block=null;
					this.statup=vethuthe;
				}
			}else findTarget();
		}
		//xac dinh anh ve
		if(time>=12){
			hoimau();
			checkStatup();
			time=0;
		}
		if(time==0 && crImage==5 && block!=null){//tai vi tri anh thu 6
			tancong();
		}
		this.setFrame(crImage, rowImage, wi2, he2);
	}
	@Override
	public Tower MakeClone() {
		tank1 ta = new tank1(bitmap, javelinBitmap, x, y, handler, enemylist);
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
			else {
				if(melee)crImage=1;
				else crImage=2;
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
