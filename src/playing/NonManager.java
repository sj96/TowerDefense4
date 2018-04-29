package playing;
import java.util.LinkedList;
import android.graphics.Canvas;
public class NonManager {
	//khong su dung thanh phan nay
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	public void draw(Canvas canvas){
		for(int i=0; i<object.size(); i++){
			GameObject temp = object.get(i);
			temp.draw(canvas);
		}
	}
	public void addGameObject(GameObject newobject){
		this.object.add(newobject);
	}
	public void removeGameObject(GameObject removeobject){
		this.object.remove(removeobject);
	}
	public void removeGameObject(int location){
		this.object.remove(location);
	}
	public int getsize(){
		return this.object.size();
	}
	public GameObject getGameObject(int i){
		return this.object.get(i);
	}
}
