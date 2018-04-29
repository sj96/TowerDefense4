package Tower;

import playing.*;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class wizar2 extends Tower{
	Bitmap castWi, Fireball, boom;
	RedyCast cast;
	public wizar2(Bitmap bit,Bitmap cast, Bitmap fireb, Bitmap Boom, float bx, float by,Handler h , Handler EnemyList) {
		super(bit, ID.wizar, bx, by, 13, 2, h, EnemyList);
		this.setInfo(150,  20, 4, ruler*3, 8);
		// hp, at, df, rg, bp
		this.blockCount=0;
		castWi=cast;
		Fireball = fireb;
		boom=Boom;
		atackimage=12;
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
		//arow a= new arow(ArrowBItmap, x+width/2, y+dry,at, block);
		//this.handler.addGameObject(a);
		if(cast!=null) {
			cast.noUse=true;
			cast=null;
		}
		BoomEffect BE = new BoomEffect(boom, 0, 0, at, enemylist);
		FireBallEffect FireE = new FireBallEffect(Fireball, 0, 0);
		Fireball F = new Fireball(Fireball, x+width/2, y+height/2+dry, BE, FireE, block, this.handler);
		this.handler.addGameObject(F);
		if(!block.isLive()){
			this.block=null;
			this.statup=vethuthe;
		}
	}
	public void CastSpell(){
		cast = new RedyCast(castWi, x+width/2, y+height/2+dry);
		this.handler.addGameObject(cast);
	}
	@Override
	public Tower MakeClone() {
		wizar2 ac = new wizar2(bitmap, castWi, Fireball, boom, x, y, handler, enemylist);
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
				crImage=0;
			}
			break;
		case vethuthe:
			if(crImage<maxcollum-1) crImage=crImage+1;
			else{
				if(cast!=null){
					cast.noUse=true;
				}
				crImage=0;
				statup=thuong;
			}
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
					rowImage=0;
				}else rowImage=1;
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
		if(time>=10){
			checkStatup();
			time=0;
		}
		if(time==0 && crImage==3 && block!=null){//tai vi tri anh thu 4
			CastSpell();
		}
		if(time==0 && crImage==11 && block!=null){//tai vi tri anh thu 12
			tancong();
		}
		if(cast!=null && !cast.noUse){
			cast.wail=5;
		}
		this.setFrame(crImage, rowImage);
	}
}
