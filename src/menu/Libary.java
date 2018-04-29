package menu;

import playing.*;

import com.nienluannganh.towerdefense.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;

public class Libary extends GameObject{
	//doi tuong nay khong duoc su dung nua
	Button close;
	Handler handler;
	boolean destroll;
	float mx, my;
	//chi so hien thi tren libary
	String name="";
	int hp, at, df, mv;
	Bitmap image=null;
	Bitmap miniimage[]= new Bitmap[2];
	Bitmap khung=null;
	Paint p;
	float surRuler;
	public Libary(Bitmap bit, ID mID, float bx, float by, int Ruler, int mcol, int mrow) {
		super(bit, mID, bx, by, Ruler, mcol, mrow);
		mx=x;
		my=y;
		destroll=false;
		p=new Paint();
		p.setColor(Color.BLACK);
		p.setTextSize(width/10);
		p.setTextAlign(Align.LEFT);
		surRuler = this.width/100;
		// TODO Auto-generated constructor stub
	}
	public void setHandler(Handler hand) {
		this.handler = hand;
	}
	@Override
	public void update() {
		if(x<mx){
			x=x+ruler/50;
		}
		if(x>mx){
			x=x-ruler/50;
		}
		if(y<my){
			y=y+ruler/50;
		}
		if(y>my){
			y=y-ruler/50;
		}
		if(destroll && x==mx && y==my) KillMySell();
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, this.x, this.y, null);
		if(image!=null){
			canvas.drawBitmap(image, this.x+52*surRuler, this.y+height/5, null);
			canvas.drawBitmap(miniimage[0], this.x+3*surRuler, this.y+2*surRuler, null);
			canvas.drawBitmap(miniimage[1], this.x+3*surRuler+khung.getWidth(), this.y+2*surRuler, null);
			canvas.drawBitmap(khung, this.x+3*surRuler, this.y+2*surRuler, null);
			p.setTextSize(width/10);
			canvas.drawText(name, this.x+52*surRuler, this.y+height/7, p);
			p.setTextSize(width/20);
			canvas.drawText("HP: "+hp, this.x+52*surRuler+image.getWidth()+surRuler,this.y+3*height/10, p);
			canvas.drawText("AT: "+at, this.x+52*surRuler+image.getWidth()+surRuler,this.y+4*height/10, p);
			canvas.drawText("DF: "+df, this.x+52*surRuler+image.getWidth()+surRuler,this.y+5*height/10, p);

			}
	}
	public void setup(GameSurface GS){
		//lưu ý, đây chỉ là mẫu, cần chỉnh sửa!
		Bitmap button = BitmapFactory.decodeResource(GS.getResources(), R.drawable.button);
		close = new Button(button, ID.buttonClose, this.x+10, ruler-button.getHeight()-10, ruler, 2, 1);
		close.setText("Đóng");
		image = BitmapFactory.decodeResource(GS.getResources(), R.drawable.mau);
		miniimage[0] = BitmapFactory.decodeResource(GS.getResources(), R.drawable.minimau);
		miniimage[1] = BitmapFactory.decodeResource(GS.getResources(), R.drawable.test1);
		miniimage[1] = Bitmap.createBitmap(miniimage[1] ,0, 0,  miniimage[1].getWidth()/3, miniimage[1].getHeight()/4);
		khung = BitmapFactory.decodeResource(GS.getResources(), R.drawable.khung);
		name="Devil Cute";
		hp=10;
		at=9999;
		df=0;
		mv=1;
		handler.addGameObject(close);
	}
	public void setMoveTo(float toX, float toY){
		float Rx, Ry;
		Rx=toX-x;
		Ry=toY-y;
		this.mx=toX;
		this.my=toY;
		close.setMoveTo(close.getX()+Rx, close.getY()+Ry);
	}
	public void close(){
		this.setMoveTo(2*ruler, this.y);
		destroll=true;
	}
	public void KillMySell(){
		handler.removeGameObject(close);
		handler.removeGameObject(this);
	}
}
