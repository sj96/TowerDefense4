package menu;

import playing.*;

import android.graphics.Bitmap;
import android.graphics.Color;

public class PointMap extends Button{
	//doi tuong nay khong su dung nua
	protected int lever;
	protected boolean choose;
	public PointMap(Bitmap bit, ID mID, float bx, float by, int ruler, int mcol, int mrow) {
		super(bit, mID, bx, by, ruler, mcol, mrow);
		this.p.setColor(Color.RED);
		choose = false;
		lever=0;
		// TODO Auto-generated constructor stub
	}
	public void setLever(int i){
		lever = i;
	}
	public int getLever(){
		return this.lever;
	}
	public void setChoose(boolean C){
		this.choose=C;
	}
	public boolean isChoosed(){
		return this.choose;
	}
	public void setnewlocal(float nx, float ny){
		this.x=nx;
		this.y=ny;
		this.mx=x;
		this.my=y;
	}
}
