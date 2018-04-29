package Map;

import java.util.LinkedList;

import playing.*;


import Monter.Monster;
import android.graphics.Canvas;

public class Wave extends GameObject{
	LinkedList<Monster> way[] = new LinkedList[100];
	Handler handler, enemyList;
	int wailTime, Timet, wavei, n;
	public Wave(float bx, float by, Handler h, Handler eList, int maxwave) {
		super(ID.way, bx, by, SubSetting.ruler);
		for(int i=0; i<maxwave; i++){
			way[i]= new LinkedList<Monster>();
		}
		handler=h;
		enemyList=eList;
		wailTime=1200;
		Timet=300;
		wavei=0;
		n=maxwave;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(wavei<n){
			Timet=Timet-SubSetting.timePlus;
			SubSetting.wave=wavei;
			SubSetting.timeNext = Timet/100;
		}
		if(wavei==n){
			SubSetting.isWin=true;
		}
		if(Timet<=0){
			StartWave(wavei);
			wavei=wavei+1;
			Timet=wailTime;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}
	public boolean addMonter(int way_i, Monster m){
		if(m!=null){
			way[way_i].add(m);
			return true;
		}else return false;
	}
	public void StartWave(int i){
		while(way[i].size()>0 ){
			handler.addGameObject(way[i].get(0));
			enemyList.addGameObject(way[i].get(0));
			way[i].remove(0);
		}
	}
}
