package orther;

import android.graphics.Bitmap;

public class poison extends GameEffect{
	//hieu ung doc
	public int dame=1;
	public poison() {
		this.name="Posion";
		tontai=10;
		maxcollum=5;
		//max anh =15;
	}
	public poison(Bitmap bit){
		this.name="Posion";
		tontai=10;
		maxcollum=5;
		img=bit;
		width = img.getWidth()/5;
		height=img.getHeight()/3;
		crimg=8;
		maximg=15;
		setImg(0);
	}
	@Override
	public void EffectDo(GameCharacter gc) {
		if(tontai>0){
			gc.hp=gc.hp-dame;
			tontai=tontai-1;
		}
	}
	@Override
	public void nextImg() {
		crimg= crimg+1;
		if(crimg==maximg-1){
			crimg=8;
		}
	}

}
