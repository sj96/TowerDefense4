package menu;

import playing.*;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class MapMenu extends GameObject{
	//doi tuong nay khong su dung nua
	PointMap[] pointmap = new PointMap[5];
	Handler handler;
	Button close, start;
	boolean destroll;
	float sw=0;
	float mx, my;
	public MapMenu(Bitmap bit, ID mID, float bx, float by, int Ruler, int mcol, int mrow) {
		super(bit, mID, bx, by, Ruler, mcol, mrow);
		mx=x;
		my=y;
		destroll=false;
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
		canvas.drawBitmap(bitmap, this.x+(sw-width)/2,  this.y, null);
	}
	public void setup(Bitmap img, Bitmap button, float screenWidth){
		int vitriy = ((this.getWidth()/10)+10);
		sw=screenWidth;
		for(int i=0; i<5; i++){
			pointmap[i] = new PointMap(img, ID.pointmap,this.x+i*vitriy, this.y+this.getHeight()/2 , this.ruler, 2, 1);
			pointmap[i].setType(Type.pointmap);
			pointmap[i].setText(""+(i+1));
			pointmap[i].setLever(i+1);
			handler.addGameObject(pointmap[i]);
		}
		pointmap[0].setnewlocal(this.x+ruler, this.y+height/2);
		pointmap[1].setnewlocal(pointmap[1].getX(), this.y+height/2 - ruler/10);
		pointmap[2].setnewlocal(pointmap[2].getX(), this.y+height/2 - ruler/5);
		pointmap[3].setnewlocal(this.x+4*ruler/5, this.y+height/2 - ruler/3);
		pointmap[4].setnewlocal(this.x+5*vitriy, this.y+height/2);
		close = new Button(button, ID.buttonClose, this.x+10, this.y+ruler-button.getHeight(), ruler, 2, 1);
		close.setText("Đóng");
		start = new Button(button, ID.buttonStart, this.x+screenWidth-button.getWidth()/2-10,close.getY() , ruler, 2, 1);
		start.setText("Chọn");
		handler.addGameObject(close);
		handler.addGameObject(start);
	}
	public void setMoveTo(float toX, float toY){
		int vitriy = ((this.getWidth()/10)+10);
		float Rx, Ry;
		Rx=toX-x;
		Ry=toY-y;
		this.mx=toX;
		this.my=toY;
		for(int i=0; i<5; i++){
			pointmap[i].setMoveTo(pointmap[i].getX()+Rx, pointmap[i].getY()+Ry);
		}
		close.setMoveTo(close.getX()+Rx, close.getY()+Ry);
		start.setMoveTo(start.getX()+Rx, start.getY()+Ry);
	}
	public void setnewlocal(float nx, float ny){
		this.x=nx;
		this.y=ny;
		this.mx=x;
		this.my=y;
	}
	public void close(){
		this.setMoveTo(2*ruler, this.y);
		destroll=true;
	}
	public void KillMySell(){
		for(int i=0; i<4; i++) handler.removeGameObject(pointmap[i]);
		handler.removeGameObject(close);
		handler.removeGameObject(start);
		handler.removeGameObject(this);
	}
}
