package playing;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameUpdate extends Thread{
	private boolean running;
	private GameSurface gamesurface;
	private SurfaceHolder surfaceholder;
	public GameUpdate(GameSurface GS, SurfaceHolder surholder){
		this.gamesurface=GS;
		this.surfaceholder=surholder;
	}
	public void setRunning(boolean run){
		this.running=run;
	}
	@Override
	public void run() {
		long starttime = System.nanoTime();
		while(running){
			Canvas canvas=null;
			try{
				canvas = this.surfaceholder.lockCanvas();
				synchronized(canvas){
					this.gamesurface.update();
					this.gamesurface.draw(canvas);
				}
			}catch(Exception e){
			}
			finally{
				if(canvas!=null){
					this.surfaceholder.unlockCanvasAndPost(canvas);
				}
			}
			long now = System.nanoTime();
			long wait = (now-starttime)/1000000;
			if(wait<10){ //thoi gian <10 miligiay, hay noi cach khac la wail chay qua nhanh, vuot qua 100 lan lap/s
				wait = 10;
			}
			try {
				sleep(wait);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			starttime = System.nanoTime();
		}
		super.run();
	}
}
