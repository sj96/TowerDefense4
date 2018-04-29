package Monter;

import playing.*;

import Tower.Tower;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import orther.GameCharacter;
import orther.GameEffect;

public abstract class Monster extends GameCharacter{
	int[] pointX, pointY;
	int i, lx, ly;
	int point;
	float mx, my, mv;
	boolean onMove=true;
	GameEffect[] GE = new GameEffect[10];
	public Tower block;
	Handler handler;
	Paint pa;
	//hp:mau   mv: di chuyen   df: thu   at: tan cong
	//ix, iy: chi so xac dinh vi tri x, y can toi hien tai!
	//mx, my: chi so di chuyen theo x, y;
	//lx, ly: chi so gioi han hien tai
	//pointX, pointY: diem doi huong di X, Y
	//point: diem nhan duoc sau khi giet quai
	//block: muc tieu hien tai
	public Monster(Bitmap bit, ID mID, float bx, float by, int Ruler, int mcol, int mrow, Handler h) {
		super(bit, mID, bx, by, Ruler, mcol, mrow);
		i=0;
		this.type=Type.monster;
		block=null;
		for(int i=0; i<0; i++)GE[i] = null;
		handler=h;
		pa= new Paint();
		pa.setColor(Color.RED);
		pa.setStrokeWidth(ruler/15);
		// TODO Auto-generated constructor stub
	}
	public void setInfo(int nhp, int nat, int ndf, int npoint){
		maxhp=nhp;
		hp=nhp;
		at=nat;
		df=ndf;
		point=npoint;
	}

	@Override
	public void draw(Canvas canvas) {
		int k=hp*100/maxhp;
		canvas.drawBitmap(drawbitmap, SubSetting.x+x, SubSetting.y+y, null);
		canvas.drawLine(SubSetting.x+x,SubSetting.y+y+height, SubSetting.x+x+(width*k/100), SubSetting.y+y+height, pa);
		for(int i=0; i<10; i++){
			if(GE[i]!=null){
				canvas.drawBitmap(GE[i].Drawimg, SubSetting.x+x+width/2-GE[i].width/2, SubSetting.y+y+height/2-GE[i].height/2, null);
			}
		}
	}
	public void setpointX(int[] pX){
		pointX=pX;
	}
	public void setpointY(int[] pY){
		pointY=pY;
	}
	public void move(){
		x=x+mx*SubSetting.timePlus;
		y=y+my*SubSetting.timePlus;
		if(mx<0 && x<=lx || mx>0 && x>=lx){
			mx=0;
		}
		if(my<0 && y<=ly || my>0 && y>=ly){
			my=0;
		}
		if(mx==0 && my==0){
			checkMove();
		}
	}
	public void checkMove(){
		i=i+1;
		lx=pointX[i]+ruler/4;
		ly=pointY[i];
		if(lx>9999 && isLive()){
			SubSetting.heat=SubSetting.heat-1;
			this.hp=0;
			SubSetting.scorce=SubSetting.scorce-this.point;
		}
		if(x>lx) mx=-mv; else if(x<lx) mx=mv; else mx=0;
		if(y>ly) my=-mv; else if(y<ly) my=mv; else my=0;
	}
	public void getEffect(GameEffect effect) {
		int i;
		for(i=0; i<10; i++) if(GE[i]==null || GE[i].name.equalsIgnoreCase(effect.name)) break;
		GE[i]=effect;
	}
	public void removeEffect(int i){
		if(i==9) GE[i]=null;
		else{
			for(int j=i; j<9; j++){
				GE[i]=GE[i+1];
			}
			GE[9]=null;
		}
	}
	public void removeEffect(String name){
		int i;
		for(i=0; i<10; i++){
			if(GE[i].name.equalsIgnoreCase(name)) break;
		}
		for(;i<9;i++){
			GE[i]=GE[i+1];
		}
		GE[9]=null;
	}
	public void EffectDo(){
		for(int i=0; i<10; i++){
			if(GE[i]!=null){
				GE[i].EffectDo(this);
				if(GE[i].tontai==0){
					removeEffect(i);
					i=i-1;
				}
			}
		}
	}
	public void EffectDraw(){
		for(int i=0; i<10; i++){
			if(GE[i]!=null){
				if(GE[i].img!=null){
					GE[i].nextImg();
					GE[i].setImg(GE[i].crimg);
				}
			}
		}
	}
	public void checkLooking(){ //xac dinh huong quai quay mat qua
		if(mx>0) Left=false;
		else if(mx<0) Left=true;
		else{
			if(block!=null){
				if(x+width/2<block.getX()+block.getX()+width/2) Left=false;
				else{
					Left = true;
				}
			}
		}
	}
	public int getPoint(){
		return this.point;
	}
	public abstract void defend(int nat);
	public abstract void tancong();
	public abstract Monster makeLone();
	public void findTarget(){
		double tgmin=range;
		if(block != null) return;
		else for(int i=0; i<handler.getsize(); i++){
			Tower temp =(Tower) handler.getGameObject(i);
			double tx, ty, tg;
			tx=x+width/2-temp.getX()-temp.getWidth()/2;
			ty=y+height/2-temp.getY()-temp.getHeight()/2;
			tg= Math.sqrt((tx*tx)+(ty*ty));
			if(tg<range && tg<tgmin){
				tgmin=tg;
				block = temp;
			}
		}
	}
}
