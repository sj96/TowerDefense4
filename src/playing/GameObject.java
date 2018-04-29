package playing;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class GameObject {
	public boolean noUse=false;
	protected Bitmap bitmap;
	protected Bitmap drawbitmap; //bitmap se duoc ve
	protected float x, y;
	protected ID id;
	protected Type type;
	protected int maxcollum, maxrow; //so hang va cot anh trong bitmap
	protected int width, height;
	protected int ruler; //thuoc do
	public GameObject(Bitmap bit, ID mID, float bx, float by, int Ruler, int mcol, int mrow){
		//mcol: maxcollum, mrow:maxrow !=0
		bitmap=bit;
		id = mID;
		x=bx;
		y=by;
		ruler = Ruler;
		maxcollum=mcol;
		maxrow=mrow;
		width = bitmap.getWidth()/maxcollum;
		height = bitmap.getHeight()/maxrow;
		drawbitmap = Bitmap.createBitmap(bitmap, 0, 0,width, height);
		type=Type.object;
	}
	public GameObject(ID mID, float bx, float by, int Ruler) {
		bitmap=null;
		id = mID;
		x=bx;
		y=by;
		ruler = Ruler;
		maxcollum=1;
		maxrow=1;
		width = 0;
		height = 0;
		drawbitmap = null;
		type=Type.object;
	}
	public void setFrame(int col, int row){
		drawbitmap = Bitmap.createBitmap(bitmap,(col*width),(row*height), width, height);
	}
	public void setFrame(int col, int row, float wi, float he){
		drawbitmap = Bitmap.createBitmap(bitmap,(int)(col*wi),(int)(row*he), (int)wi, (int)he);
	}
	public void setType(Type t){
		this.type = t;
	}
	public void setX(float tx){
		this.x=tx;
	}
	public void setY(float ty){
		this.y=ty;
	}
	public float getX(){
		return this.x;
	}
	public float getY(){
		return this.y;
	}
	public int getHeight(){
		return this.height;
	}
	public int getWidth(){
		return this.width;
	}
	public ID getID(){
		return id;
	}
	public Type getType(){
		return type;
	}
	public abstract void update();
	public abstract void draw(Canvas canvas);
}
