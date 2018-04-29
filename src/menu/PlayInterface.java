package menu;

import java.io.IOException;
import java.io.InputStream;

import com.nienluannganh.towerdefense.R;

import playing.*;

import Map.MapGame;
import Map.TowerIcon;
import Tower.Tower;
import Tower.archer1;
import Tower.archer2;
import Tower.archer3;
import Tower.posion1;
import Tower.posion2;
import Tower.posion3;
import Tower.tank1;
import Tower.tank2;
import Tower.tank3;
import Tower.wizar1;
import Tower.wizar2;
import Tower.wizar3;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class PlayInterface extends GameObject{
	//day la giao dien tro choi, dung de khoi tao, nap tgro choi, map,...
	MapGame map;
	GameSurface GS;
	ImageButton PausePlay, AcButton;
	TowerIcon Tank, Archer, Wizar, Posion;
	Bitmap HeatIcon;
	Paint P;
	int time=0;
	public PlayInterface(float bx, float by, int Ruler, GameSurface gs, InputStream imap) {
		super(ID.GamePlay, bx, by, Ruler);
		P = new Paint();
		P.setTypeface(GameFont.type1);
		P.setColor(Color.BLACK);
		P.setTextSize(ruler/2);
		GS=gs;
		//khoi tao cac bieu tuong xay tru
		HeatIcon = BitmapFactory.decodeResource(GS.getResources(), R.drawable.healt_icon);
		Bitmap icon = BitmapFactory.decodeResource(GS.getResources(), R.drawable.archer_build);
		int xButton = icon.getWidth();
		int yButton = GS.getHeight()-icon.getHeight();
		Archer = new TowerIcon(BitmapFactory.decodeResource(GS.getResources(), R.drawable.archer_build),GS.getWidth()-4*xButton, yButton, ruler, null,5);
		Tank = new TowerIcon(BitmapFactory.decodeResource(GS.getResources(), R.drawable.tank_build), GS.getWidth()-3*xButton, yButton, ruler, null,5);
		Posion = new TowerIcon(BitmapFactory.decodeResource(GS.getResources(), R.drawable.posion_build), GS.getWidth()-2*xButton, yButton, ruler, null,5);
		Wizar = new TowerIcon(BitmapFactory.decodeResource(GS.getResources(), R.drawable.magic_build), GS.getWidth()-xButton, yButton, ruler, null,5);
		
		/*SubSetting.tank=1;
		SubSetting.archer=1;
		SubSetting.posion=1;
		SubSetting.wizar=1;*/
		this.setLeverTower();
		//khoi tao cac button
		icon = BitmapFactory.decodeResource(GS.getResources(), R.drawable.play_pause);
		yButton = GS.getHeight()-icon.getHeight();
		PausePlay = new ImageButton(BitmapFactory.decodeResource(GS.getResources(), R.drawable.play_pause), 0, yButton, ruler, 2) {
			@Override
			public void update() {
				if(SubSetting.timePlus==0){
					setFrame(0, 0);
				}
				else{
					setFrame(1, 0);
				}
				super.update();
			}
			@Override
			public void onclick() {
				if(SubSetting.timePlus!=0){
					int k=SubSetting.timePlus;;
					SubSetting.lastSpeed=k;
					SubSetting.timePlus=0;
					setFrame(0, 0);
				}
				else{
					int k=SubSetting.lastSpeed;
					SubSetting.timePlus=k;
					setFrame(1, 0);
				}
			}
		};
		PausePlay.setFrame(1,  0);
		
		AcButton = new ImageButton(BitmapFactory.decodeResource(GS.getResources(), R.drawable.speed), xButton, yButton, ruler, 1) {
			Paint p = new Paint();
			@Override
			public void onclick() {
				switch(SubSetting.timePlus){
					case 1: 
						SubSetting.timePlus=2;
						break;
					case 2:
						SubSetting.timePlus=3;
						break;
					case 3:
						SubSetting.timePlus=1;
						break;
				}
			}
			@Override
			public void draw(Canvas canvas) {
				p.setTypeface(GameFont.type1);
				p.setTextSize(this.height/2);
				p.setColor(Color.WHITE);
				canvas.drawBitmap(drawbitmap, x, y, null);
				canvas.drawText("X"+SubSetting.timePlus, x+width/2, y+height, p);
			}
		};
		//tien hanh nap map vao tro choi
		map = new MapGame(0, 0, GS);
		this.GS.handler.addGameObjectOnBelow(map);
		map.loadMap(imap);
		try {
			imap.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//cai dat cac doi tuong da khoi tao va nap
		this.GS.handler.addGameObjectOnTop(AcButton);
		this.GS.handler.addGameObjectOnTop(PausePlay);
		this.GS.handler.addGameObjectOnTop(Archer);
		this.GS.handler.addGameObjectOnTop(Posion);
		this.GS.handler.addGameObjectOnTop(Tank);
		this.GS.handler.addGameObjectOnTop(Wizar);
		// TODO Auto-generated constructor stub
	}
	//dat cac tru vao cac icon tuong ung
	public void setIcon(Tower tank, Tower archer, Tower wizar,  Tower posion){
		this.Tank.setTower(tank);
		this.Archer.setTower(archer);
		this.Wizar.setTower(wizar);
		this.Posion.setTower(posion);
	}
	public void setLeverTower(){
		Tower tank, archer, posion, wizar;
		Bitmap img1, img2, img3, img4;
		switch(SubSetting.tank){
		case 1: img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.tank1);
				img2 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.javelin);
				tank = new tank1(img1, img2, 0, 0, GS.handler, GS.enemylist);
				break;
		case 2: img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.tank2);
				img2 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.javelin);
				tank = new tank2(img1, img2, 0, 0, GS.handler, GS.enemylist);
				break;
		case 3: img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.tank3);
				img2 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.tank3javelin);
				tank = new tank3(img1, img2, 0, 0, GS.handler, GS.enemylist);
				break;
		default: 
			img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.tank1);
			img2 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.javelin);
			tank = new tank1(img1, img2, 0, 0, GS.handler, GS.enemylist);
			break;
		}
		img2 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.arow);
		switch(SubSetting.archer){
		case 1: img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.archer1);
				archer = new archer1(img1, img2, 0, 0, GS.handler, GS.enemylist);
				break;
		case 2: img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.arche2);
				archer = new archer2(img1, img2, 0, 0, GS.handler, GS.enemylist);
				break;
		case 3: img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.arche3);
				archer = new archer3(img1, img2, 0, 0, GS.handler, GS.enemylist);
				break;
		default: 
			img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.archer1);
			archer = new archer1(img1, img2, 0, 0, GS.handler, GS.enemylist);
			break;
		}
		img3 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.state1);
		switch(SubSetting.posion){
		case 1: img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.posion1);
				posion = new posion1(img1, img2, img3, 0, 0, GS.handler, GS.enemylist);
				break;
		case 2: img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.posion2);
				posion = new posion2(img1, img2, img3, 0, 0, GS.handler, GS.enemylist);
				break;
		case 3: img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.posion3);
				posion = new posion3(img1, img2, img3, 0, 0, GS.handler, GS.enemylist);
				break;
		default:
			img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.posion1);
			posion = new posion1(img1, img2, img3, 0, 0, GS.handler, GS.enemylist);
			break;
		}
		img2=BitmapFactory.decodeResource(GS.getResources(), R.drawable.cast_wizar2);
		img3=BitmapFactory.decodeResource(GS.getResources(), R.drawable.fire_ball);
		img4=BitmapFactory.decodeResource(GS.getResources(), R.drawable.wizar_boom);
		switch(SubSetting.wizar){
		case 1: img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.magic1);
				wizar = new wizar1(img1, img2, img3, img4, 0, 0, GS.handler, GS.enemylist);
				break;
		case 2: img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.magic2);
				wizar = new wizar2(img1, img2, img3, img4, 0, 0, GS.handler, GS.enemylist);
				break;
		case 3: img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.magic3);
				wizar = new wizar3(img1, img2, img3, img4, 0, 0, GS.handler, GS.enemylist);
				break;
		default:
			img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.magic1);
			wizar = new wizar1(img1, img2, img3, img4, 0, 0, GS.handler, GS.enemylist);
			break;
		}
		this.Tank.setTower(tank);
		this.Archer.setTower(archer);
		this.Wizar.setTower(wizar);
		this.Posion.setTower(posion);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		time=time+SubSetting.timePlus;
		if(time>50){
			SubSetting.BuildPoint =SubSetting.BuildPoint+1;
			time=0;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		for(int i=0; i<SubSetting.heat; i++){
			canvas.drawBitmap(HeatIcon, 0+i*HeatIcon.getWidth(),  0, null);
		}
		canvas.drawText("BP: "+SubSetting.BuildPoint, GS.getWidth()-3*ruler, ruler/2, P);
		canvas.drawText("Điểm: "+SubSetting.scorce, GS.getWidth()-3*ruler, ruler, P);
		canvas.drawText("Đợt tiếp theo sau: "+SubSetting.timeNext, 0, 2*HeatIcon.getHeight(), P);
	}

}
