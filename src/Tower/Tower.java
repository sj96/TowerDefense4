package Tower;

import playing.*;

import Monter.Monster;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import orther.GameCharacter;

public abstract class Tower extends GameCharacter{
	final int thuong=0;
	final int tancong=1;
	final int vethuthe=2;
	int atackimage;
	int crImage;
	int rowImage;
	int time=0;
	int subx, suby;
	Handler handler, enemylist;
	Monster block;
	int maxhp, lv;
	float wi2, he2;
	public float drx, dry;
	int BuildPoint;
	public int blockCount=0;
	public boolean enabled=false;
	Paint pa;
	//block: muc tieu hien tai
	//range: pham vi tan cong
	public Tower(Bitmap bit, ID mID, float bx, float by, int mcol, int mrow, Handler h, Handler EnemyList) {
		super(bit, mID, bx, by, SubSetting.ruler, mcol, mrow);
		wi2 = bitmap.getWidth()/maxcollum;
		he2 = bitmap.getHeight()/maxrow;;
		drx=0; dry=0; subx=0; suby=0;
		handler = h;
		enemylist=EnemyList;
		block=null;
		pa= new Paint();
		pa.setColor(Color.GREEN);
		pa.setStrokeWidth(ruler/15);
		// TODO Auto-generated constructor stub
	}
	public void setInfo(int nhp, int nat,  int ndf, float ngr, int nBP){
		lv=0;
		maxhp=nhp;
		hp= maxhp;
		at=nat;
		df=ndf;
		range = ngr;
		BuildPoint=nBP;
	}
	public int getBuildPoint(){
		return BuildPoint;
	}
	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(drawbitmap, SubSetting.x+x+drx+subx, SubSetting.y+y+dry+suby, null);
		int k=hp*100/maxhp;
		float lx=SubSetting.x+x-ruler/4+width/2+subx;
		canvas.drawLine(lx,SubSetting.y+y+height+suby, lx+(ruler*k/200), SubSetting.y+y+height+suby, pa);
	}
	public void findTarget(){
		if(this.statup!=thuong) this.statup=vethuthe;
		double tgmin=range;
		//if(block != null) return;
		//else 
		for(int i=0; i<enemylist.getsize(); i++){
			GameObject temp = enemylist.getGameObject(i);
			if(temp.getType()!=Type.monster) continue;
			double tx, ty, tg;
			tx=x-temp.getX();
			ty=y-temp.getY();
			tg= Math.sqrt((tx*tx)+(ty*ty));
			if(tg<range && tg<tgmin && temp.getType()==Type.monster){
				tgmin=tg;
				block =(Monster) temp;
			}
		}
	}
	public boolean isLive() {
		if(this.hp>0) return true;
		else return false;
	}
	public void setMelee(boolean m){
		
	}
	public void CheckBlockLeft(){//kiem tra muc tieu co nam ben trai hay khong, dam bao block != null
		float bx = block.getX()+block.getWidth()/2;
		float tx = x+ width/2;
		if(bx<=tx) Left=true;
		else Left=false;
	}
	public abstract void defend(int nat);
	public abstract void tancong();
	public abstract Tower MakeClone();
	public abstract void checkStatup();//dung de xac dinh trang thai va anh ve
}
