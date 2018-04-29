package Map;

import java.io.InputStream;
import java.util.Scanner;

import com.nienluannganh.towerdefense.R;

import playing.*;

import Monter.Monster;
import Monter.SkeletonBig;
import Monter.SkeletonKnight;
import Monter.SkeletonNorman;
import Monter.SkeletonRun;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import orther.BuildArea;

public class MapGame extends GameObject{
	public Handler NoUpdate;
	//public Handler HaveUpdate;
	int[] pointX, pointY; //cac diem di chuyen
	GameSurface GS;
	//way se duoc dua vao hanler chu!
	public MapGame(float bx, float by, GameSurface gs) {
		super(ID.map, bx, by, SubSetting.ruler);
		GS = gs;
		this.bitmap = BitmapFactory.decodeResource(GS.getResources(), R.drawable.desert);
		this.width= SubSetting.ruler*20;
		this.height= SubSetting.ruler*15;
		//HaveUpdate = new Handler();
		NoUpdate = new Handler();
		pointX= new int[20];
		pointY= new int[20];
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, SubSetting.x, SubSetting.y, null);
		NoUpdate.draw(canvas);
	}
	public Monster getMonster(String code){
		//dung de xac dinh loai quai se them
		Monster k=null;
		Bitmap img1, img2;
		if(code.equalsIgnoreCase("SK")){
			img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.maxuong1);
			k=new SkeletonKnight(img1, 0, 0, GS.towerlist);
		}
		if(code.equalsIgnoreCase("SN")){
			img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.maxuong5);
			img2 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.xuong);
			k=new SkeletonNorman(img1, img2, 0, 0, GS.handler, GS.towerlist);
		}
		if(code.equalsIgnoreCase("SR")){
			img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.maxuong2);
			k=new SkeletonRun(img1, 0, 0, GS.towerlist);
		}
		if(code.equalsIgnoreCase("SB")){
			img1 = BitmapFactory.decodeResource(GS.getResources(), R.drawable.maxuong4);
			k=new SkeletonBig(img1, 0, 0, GS.towerlist);
		}
		return k;
	}
	
	public void loadMap(InputStream s){
		//nap du lieu map tu file .map
		Scanner scan = new Scanner(s);
		String CID="";
		float cx, cy;
		int j=0, k=0;
		float[][] way = new float[100][2];
		BuildArea[] ba = new BuildArea[100];
		int klv=scan.nextInt(); scan.nextLine();//lay dong xac dinh lever dau tien
		klv=(klv-1)/3;
		switch (klv) {
		case 0:
			this.bitmap = BitmapFactory.decodeResource(GS.getResources(), R.drawable.desert);
			break;
		case 1:
			this.bitmap = BitmapFactory.decodeResource(GS.getResources(), R.drawable.dirt2);
			break;
		case 2:
			this.bitmap = BitmapFactory.decodeResource(GS.getResources(), R.drawable.snowfield);
			break;
		case 3:
			this.bitmap = BitmapFactory.decodeResource(GS.getResources(), R.drawable.meadow2);
			break;
		default:
			this.bitmap = BitmapFactory.decodeResource(GS.getResources(), R.drawable.desert);
			break;
		}
		SubSetting.BuildPoint=scan.nextInt(); scan.nextLine(); //lay vao chi so build point co ban
		int wn = scan.nextInt(); scan.nextLine(); //lay vao so wave toi da
		Wave gameWave = new Wave(0, 0, GS.handler, GS.enemylist, wn);
		while(!scan.nextLine().equalsIgnoreCase("endWave")){
			int n, sl;
			String code="";
			n= scan.nextInt(); scan.nextLine(); //xac dinh way se them
			code=scan.nextLine(); //xac dinh loai quai
			sl=scan.nextInt(); scan.nextLine(); //xac dinh so luong
			Monster temp = getMonster(code);
			System.out.println(code+"||"+ temp.getID());
			for(int i=0; i<sl; i++){
				gameWave.addMonter(n-1, temp.makeLone());
			}
		}
		while(scan.hasNext()){
			CID=scan.nextLine();//xac dinh thanh phan cua map
			cx=scan.nextInt()*ruler; scan.nextLine(); //toa do x cua thanh phan
			cy=scan.nextInt()*ruler; scan.nextLine(); //toa do y cua thanh phan
			System.out.println(CID+"|"+cx/ruler+"|"+cy/ruler);
			if(CID.equalsIgnoreCase("NenDa")){
				Nen23 nenda = new Nen23(BitmapFactory.decodeResource(this.GS.getResources(), R.drawable.da), ID.nenda, cx, cy, this.ruler, 2, 3,this.NoUpdate);
				NoUpdate.addGameObjectOnBelow(nenda);
			}
			if(CID.equalsIgnoreCase("Build")){
				ba[k]= new BuildArea(BitmapFactory.decodeResource(this.GS.getResources(), R.drawable.canbuild), cx, cy, GS.handler);
				GS.handler.addGameObjectOnBelow(ba[k]);
				k=k+1;
			}
			if(CID.equalsIgnoreCase("END")){
				Orther build = new Orther(BitmapFactory.decodeResource(this.GS.getResources(), R.drawable.heat), ID.end, cx, cy, GS.handler);
				GS.handler.addGameObjectOnBelow(build);
			}
			if(CID.equalsIgnoreCase("START")){
				Orther build = new Orther(BitmapFactory.decodeResource(this.GS.getResources(), R.drawable.cross_sword), ID.end, cx, cy, GS.handler);
				GS.handler.addGameObjectOnBelow(build);
				pointX[0]=(int) cx;
				pointY[0]=(int) cy;
			}
			if(CID.equalsIgnoreCase("Way")){
				way[j][0] = cx;
				way[j][1] = cy;
				j=j+1;
			}
		}
		// cac dong trong for dung xac dinh diem xay dung co nam tren duong hay khong, dong thoi dat lai point
		for(int i=0; i<j-1; i++){
			int start, end;
			if(way[i][0]==way[i+1][0] && way[i][1]!=way[i+1][1]){
				if(way[i][1]<way[i+1][1]){
					start=(int) way[i][1]; end=(int) way[i+1][1];
				}
				else{
					start=(int) way[i+1][1]; end=(int) way[i][1];
				}
				for(int p=0; p<k; p++){
					if(ba[p].getX()==way[i][0] && ba[p].getY()>=start && ba[p].getY()<=end) ba[p].inWay=true;
				}
			}
			if(way[i][0]!=way[i+1][0] && way[i][1]==way[i+1][1]){
				
				if(way[i][0]<way[i+1][0]){
					start=(int) way[i][0]; end=(int) way[i+1][0];
				}else{
					start=(int) way[i+1][0]; end=(int) way[i][0];
				}
				for(int p=0; p<k; p++){
					if(ba[p].getY()==way[i][1] && ba[p].getX()>=start && ba[p].getX()<=end) ba[p].inWay=true;
				}
			}
			pointX[i]=(int) way[i][0];
			pointY[i]=(int) way[i][1];
		}
		pointX[j-1]=(int) way[j-1][0];
		pointY[j-1]=(int) way[j-1][1];
		pointX[j]=9999;
		pointY[j]=pointY[j-1];
		//cap nhat lai cac thanh phan can ve cua map
		for(int i=0; i<NoUpdate.getsize(); i++){
			NoUpdate.getGameObject(i).update();
		}
		scan.close();
		//dat lai thong tin quai cho wave
		for(int i=0; i<gameWave.n; i++){
			for(int l=0; l<gameWave.way[i].size(); l++){
				Monster temp = gameWave.way[i].get(l);
				temp.setX(pointX[0]-l*SubSetting.ruler);
				temp.setY(pointY[0]);
				temp.setpointX(pointX);
				temp.setpointY(pointY);
				temp.checkMove();
			}
		}
		this.GS.handler.addGameObject(gameWave);
		/*SkeletonKnight sk = new SkeletonKnight(BitmapFactory.decodeResource(GS.getResources(), R.drawable.maxuong1), pointX[0], pointY[0], GS.towerlist);
		SkeletonRun sr= new SkeletonRun(BitmapFactory.decodeResource(GS.getResources(), R.drawable.maxuong2), pointX[0], pointY[0], GS.towerlist);
		Bitmap xuong = BitmapFactory.decodeResource(GS.getResources(), R.drawable.xuong);
		SkeletonNorman sn = new SkeletonNorman(BitmapFactory.decodeResource(GS.getResources(), R.drawable.maxuong5), xuong, pointX[0], pointY[0], GS.handler, GS.towerlist);
		sk.setpointX(pointX);
		sk.setpointY(pointY);
		sk.checkMove();
		sr.setpointX(pointX);
		sr.setpointY(pointY);
		sr.checkMove();
		sn.setpointX(pointX);
		sn.setpointY(pointY);
		sn.checkMove();
		GS.handler.addGameObject(sk);
		GS.enemylist.addGameObject(sk);
		GS.handler.addGameObject(sr);
		GS.enemylist.addGameObject(sr);
		GS.handler.addGameObject(sn);
		GS.enemylist.addGameObject(sn);*/
		//scan.next();
	}
}
