package playing;


import com.nienluannganh.towerdefense.R;

import Map.TowerIcon;
import Tower.Tower;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import menu.ImageButton;
import orther.BuildArea;
import orther.DrawCircle;
//su ly su kien nhan man hinh
public class TouchEvent{
	Handler handler;
	MotionEvent e;
	GameSurface GS;
	int ruler;
	Tower reTower=null;
	TowerIcon ToI=null;
	DrawCircle DC = null;
	float vx, vy;
	boolean lockmap=false;
	public TouchEvent(Handler h, int r, GameSurface G){
		this.handler=h;
		e=null;
		this.ruler=r;
		GS=G;
		
	}
	public void setMotionEvent(MotionEvent event){
		e=event;
	}
	public void onTouchDown(){
		float x,y;
		x=e.getX();
		y=e.getY();
		vx=e.getX()-SubSetting.x;
		vy=e.getY()-SubSetting.y;
		for(int i=0; i<handler.getsize(); i++){
			GameObject temp = handler.getGameObject(i);
			if(temp.x<x && (temp.x+temp.width)>x && temp.y<y && (temp.y+temp.height)>y){
				if(temp.id==ID.TowerIcon && reTower==null){
					TowerIcon TI = (TowerIcon) temp;
					reTower=TI.CreatTower();
					if(reTower!=null){
						DC = new DrawCircle(ID.GameObject, reTower.x, reTower.y, SubSetting.ruler, reTower.range);
						lockmap=true;
						DC.x=reTower.x;
						DC.y=reTower.y;
						this.handler.addGameObject(DC);
						this.handler.addGameObject(reTower);
						ToI=TI;
					}
				}
				/*if(temp.type==Type.button){
					Button bt = (Button) temp;
					bt.setFrame(1, 0);
					bt.p.setTextSize(bt.TextSize2);
				}
				if(temp.type==Type.pointmap){
					PointMap pm = (PointMap) temp;
					for(int j=0; j<handler.getsize(); j++){
						GameObject recheck = handler.getGameObject(j);
						if(recheck.type==Type.pointmap){
							PointMap Ob = (PointMap) recheck;
							Ob.setChoose(false);
							Ob.setFrame(0, 0);
						}
					}
					pm.setChoose(true);
					pm.setFrame(1, 0);
				}*/
				//code tai day
			}
		}
	}
	public void onTouchUp(){
		float x,y;
		x=e.getX();
		y=e.getY();
		lockmap=false;
		for(int i=0; i<handler.getsize(); i++){
			GameObject temp = handler.getGameObject(i);
			if(temp.id==ID.canbuid && reTower!=null){
				//keo va tha tru tai vi tri xay
				BuildArea b = (BuildArea) temp;
				if(reTower.getBuildPoint()<=SubSetting.BuildPoint && b.build && reTower.x>=b.x && reTower.x<=(b.x+b.width) && reTower.y>=b.y && reTower.y<=(b.y+b.height)){
					//Tower k = reTower.MakeClone();
					//k.x=b.x;
					//k.y=b.y;
					//this.handler.addGameObject(k);
					reTower.x=b.x;
					reTower.y=b.y;
					if(!b.inWay){
						b.SubImage= BitmapFactory.decodeResource(this.GS.getResources(), R.drawable.tower);
						reTower.dry=-6.5f*b.SubImage.getHeight()/10;
						reTower.drx= b.x+b.width/2-(reTower.x+reTower.width/2);
						reTower.blockCount=0;
						if(reTower.id==ID.tank) reTower.setMelee(false);
						DC.R= reTower.range;
						b.build=false;
					}else{
						if(reTower.id==ID.tank) b.build=false;
					}
					if(b.build==false){
						b.T=reTower;
						DC.x=b.x+SubSetting.ruler/2;
						DC.y=b.y+SubSetting.ruler/2;
						b.DC=DC;
						//this.handler.removeGameObject(reTower);
						this.handler.removeGameObject(DC);
						GS.towerlist.addGameObject(reTower);
						reTower.enabled=true;
						ToI.numTowerDown();
						SubSetting.BuildPoint= SubSetting.BuildPoint-reTower.getBuildPoint();
						ToI=null;
						DC=null;
						this.reTower=null;
					}
				}
			}
			if(temp.id==ID.canbuid){
				//an di vong tron tam dang cua tru
				BuildArea b = (BuildArea) temp;
				b.showRange=false;
			}
			if(temp.x<x && (temp.x+temp.width)>x && temp.y<y && (temp.y+temp.height)>y){
				if(temp.id==ID.ibutton){
					ImageButton ibt = (ImageButton) temp;
					ibt.onclick();
				}
				/*if(temp.type==Type.button){
					Button bt = (Button) temp;
					bt.setFrame(0, 0);
					bt.p.setTextSize(bt.TextSize1);
					if(bt.id == ID.buttonExit){
						System.exit(0);
					}
					if(bt.id == ID.buttonPlay){
						moveOutMenu();
						Bitmap img = BitmapFactory.decodeResource(GS.getResources(), R.drawable.map);
						MapMenu mapMenu = new MapMenu(img, ID.mapmenu, ruler*2, 0, ruler, 1, 1);
						mapMenu.setHandler(handler);
						handler.addGameObject(mapMenu);
						Bitmap pointimg = BitmapFactory.decodeResource(GS.getResources(), R.drawable.pointm);
						//mapMenu.setup(pointimg, GS.button, GS.getWidth());
						//mapMenu.setMoveTo(0, 0);
					}
					if(bt.id==ID.buttonGallery){
						moveOutMenu();
						Bitmap img = BitmapFactory.decodeResource(GS.getResources(), R.drawable.book);
						Libary lib = new Libary(img, ID.libary, ruler*2, 0, ruler, 1, 1);
						lib.setHandler(handler);
						handler.addGameObject(lib);
						lib.setup(GS);
						lib.setMoveTo(0, 0);
					}
					if(bt.id== ID.buttonClose){
						for(int j=0; j<handler.getsize(); j++){
							GameObject recheck = handler.getGameObject(j);
							if(recheck.type==Type.button && recheck.id!=ID.buttonClose && recheck.id!=ID.buttonStart && recheck.id!=ID.buttonPlay){
								Button moveOb = (Button) recheck;
								moveOb.setMoveTo(10, moveOb.y);
							}
							if(recheck.id==ID.buttonPlay){
								Button moveOb = (Button) recheck;
								moveOb.setMoveTo(GS.getWidth()-moveOb.width-10, moveOb.y);
							}
							if(recheck.id==ID.mapmenu){
								MapMenu mapMenu = (MapMenu) recheck;
								mapMenu.close();
							}
							if(recheck.id==ID.libary){
								Libary lib = (Libary) recheck;
								lib.close();
							}
						}
					}
				}*/
			}
		}
		if(reTower!=null){
			//xoa tru khong duoc xay
			this.handler.removeGameObject(reTower);
			this.handler.removeGameObject(DC);
			reTower=null;
			DC=null;
		}
	}
	public void onTouchMove(){
		float x,y;
		x=e.getX();
		y=e.getY();
		boolean onBuild=false;
		if(!lockmap){
			SubSetting.x=e.getX()-vx;
			SubSetting.y=e.getY()-vy;
		}
		if(SubSetting.x<-1*SubSetting.ruler*SubSetting.maxX+GS.getWidth()) SubSetting.x=-1*SubSetting.ruler*SubSetting.maxX+GS.getWidth();
		if(SubSetting.x>0) SubSetting.x=0;
		if(SubSetting.y<-1*SubSetting.ruler*SubSetting.maxY+GS.getHeight()) SubSetting.y=-1*SubSetting.ruler*SubSetting.maxY+GS.getHeight();
		if(SubSetting.y>0) SubSetting.y=0;
		if(reTower!=null){
			reTower.setX(x-SubSetting.x);
			reTower.setY(y-SubSetting.y);
			DC.x=reTower.x;
			DC.y=reTower.y;
		}
		for(int i=0; i<handler.getsize(); i++){
			GameObject temp = handler.getGameObject(i);
			if(temp.id==ID.canbuid && reTower!=null){
				BuildArea b = (BuildArea) temp;
				if(b.build && reTower.x>=b.x && reTower.x<=(b.x+b.width) && reTower.y>=b.y && reTower.y<=(b.y+b.height)){
					onBuild=true;
				}
				else if(!b.build){
					//hien vong tron tam danh
					b.showRange=true;
				}
			}
			/*if(temp.x<x && (temp.x+temp.width)>x && temp.y<y && (temp.y+temp.height)>y){
				if(temp.type==Type.button){
					Button bt = (Button) temp;
					bt.setFrame(1, 0);
					bt.p.setTextSize(bt.TextSize2);
				}
			}else{
				if(temp.type==Type.button){
					Button bt = (Button) temp;
					bt.setFrame(0, 0);
					bt.p.setTextSize(bt.TextSize1);
				}
			}*/
		}
		if(onBuild && reTower!=null){
			DC.OV.setColor(DC.ovuonT);
			DC.p.setColor(DC.tamdanh);
		}
		if(!onBuild && reTower!=null){
			DC.OV.setColor(DC.ovuonF);
			DC.p.setColor(DC.ovuonF);
		}
	}
	/*public void moveOutMenu(){
		for(int j=0; j<handler.getsize(); j++){
			GameObject recheck = handler.getGameObject(j);
			if(recheck.type==Type.button && recheck.id!=ID.buttonPlay){
				Button moveOb = (Button) recheck;
				moveOb.setMoveTo(moveOb.x-ruler, moveOb.y);
			}
			if(recheck.id==ID.buttonPlay){
				Button moveOb = (Button) recheck;
				moveOb.setMoveTo(moveOb.x-2*ruler, moveOb.y);
			}
		}
	}*/
}
