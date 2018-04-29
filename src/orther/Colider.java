package orther;

import playing.*;

public class Colider {
	//khong su dung thanh phan nay
	public float x, y;
	public float width, height;
	public Colider(float vx, float vy, float w, float h) {
		this.x=vx;
		this.y=vy;
		this.width=w;
		this.height=h;
	}
	public float getHeight() {
		return height;
	}
	public float getWidth() {
		return width;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public boolean isColiderWith(Colider C){//co va cham voi C hay khong
		if(this.x>=C.x+C.width || this.x+width<=C.x) return false;
		if(this.y>=C.y+C.height || this.y+height<=C.y) return false;
		return true;
	}
	public boolean isColoderWith(GameObject G){
		if(this.x>=G.getX()+G.getWidth() || this.x+width<=G.getX()) return false;
		if(this.y>=G.getY()+G.getHeight() || this.y+height<=G.getY()) return false;
		return true;
	}
}
