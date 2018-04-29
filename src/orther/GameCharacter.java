package orther;

import playing.*;

import android.graphics.Bitmap;

public abstract class GameCharacter extends GameObject{
	protected int maxhp, hp, df, at;
	public float range;
	public int statup=0;
	public boolean Left=true; //nhin ve ben trai
	public GameCharacter(Bitmap bit, ID mID, float bx, float by, int Ruler, int mcol, int mrow) {
		super(bit, mID, bx, by, Ruler, mcol, mrow);
		// TODO Auto-generated constructor stub
	}
	public abstract boolean isLive();
}
