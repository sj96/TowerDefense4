package orther;

import android.graphics.Bitmap;

public abstract class GameEffect {
	//hieu ung tac dong len quai
	public String name;
	public int tontai, maximg, crimg, width, height;;
	public Bitmap img;
	public Bitmap Drawimg;
	int maxcollum;
	public abstract void EffectDo(GameCharacter gc);
	public abstract void nextImg();
	public void setImg(int i){
		if(i>=maximg) return;
		//i=0->n-1; n: so anh co
		int dc, dr;
		dc=i%maxcollum;
		dr=i/5;
		Drawimg = Bitmap.createBitmap(img,(dc*width),(dr*height), width, height);
	}
}
