package menu;

import playing.*;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class MainMenu extends GameObject{
	//doi tuong nay khong duoc su dung nua
	Handler handler;
	Button play, option, gallery;
	float mx, my;
	public MainMenu(Bitmap bit, ID mID, float bx, float by, int Ruler, int mcol, int mrow) {
		super(bit, mID, bx, by, Ruler, mcol, mrow);
		mx=x;
		my=y;
		// TODO Auto-generated constructor stub
	}
	public void setHandler(Handler h){
		this.handler = h;
	}
	public void setupButton(Bitmap playimg, Bitmap tvimg, Bitmap cdimg, float sw){
		int vitriy = playimg.getHeight()+10;
		option = new Button(cdimg, ID.buttonOption, this.x+10, this.y+10+vitriy, this.ruler, 2,1);
		gallery = new Button(tvimg, ID.buttonGallery, this.x+10, this.y+10+2*vitriy, this.ruler, 2,1);
		play = new Button(playimg, ID.buttonPlay, this.x+sw-playimg.getWidth()/2-10, this.y+10+3*vitriy, this.ruler, 2, 1);
		this.handler.addGameObject(play);
		this.handler.addGameObject(option);
		this.handler.addGameObject(gallery);
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
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(this.bitmap, 0, 0, null);
	}
	public void setMoveTo(float toX, float toY){
		this.mx=toX;
		this.my=toY;
	}
}
