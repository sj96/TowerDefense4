package Tower;

import playing.*;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class archer1 extends Tower{
	Bitmap ArrowBItmap;
	public archer1(Bitmap bit, Bitmap ARB, float bx, float by,Handler h , Handler EnemyList) {
		super(bit, ID.archer, bx, by, 16, 2, h, EnemyList);
		this.setInfo(240,  10, 0, ruler*3, 2);
		// hp, at, df, rg, bp
		this.blockCount=0;
		ArrowBItmap=ARB;
		atackimage=15;
		crImage=0;
		rowImage=0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void defend(int nat) {
		int s = nat-df;
		if(s<0) s=1;
		this.hp=hp-s;
	}

	@Override
	public void tancong() {
		arow a= new arow(ArrowBItmap, x+width/2, y+dry,at, block);
		this.handler.addGameObject(a);
		if(!block.isLive()){
			this.block=null;
			this.statup=vethuthe;
		}
	}

	@Override
	public Tower MakeClone() {
		archer1 ac = new archer1(bitmap, ArrowBItmap, x, y, handler, enemylist);
		return ac;
	}

	@Override
	public void checkStatup() {
		switch (statup) {
		case thuong:
			crImage=0;
			break;
		case tancong:
			if(crImage<atackimage) crImage=crImage+1;
			else {
				crImage=1;
			}
			break;
		case vethuthe:
			crImage=0;
			statup=thuong;
			break;
		default: crImage=0;
			break;
		}
	}

	@Override
	public void update() {
		time=time+SubSetting.timePlus;
		if(enabled) {
			if(block!=null){
				this.CheckBlockLeft();
				if(Left){
					//subx=0;
					rowImage=0;
				}else{ 
					rowImage=1;
					//subx=width/4;
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
		if(time>=4){
			checkStatup();
			time=0;
		}
		if(time==0 && crImage==14 && block!=null){//tai vi tri anh thu 6
			tancong();
		}
		this.setFrame(crImage, rowImage, wi2, he2);
	}
}
