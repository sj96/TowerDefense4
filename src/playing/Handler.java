package playing;

import java.util.LinkedList;
import android.graphics.Canvas;

public class Handler {
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	//Handler linker=null;
	int ontop=0; //so phan tu tren cung
	int onbelow=0; //so phan tu duoi cung
	public void draw(Canvas canvas){
		for(int i=0; i<object.size(); i++){
			GameObject temp = object.get(i);
			temp.draw(canvas);
		}
	}
	public void update(){
		for(int i=0; i<object.size(); i++){
			GameObject temp = object.get(i);
			temp.update();
		}
	}
	public void addGameObject(GameObject newobject){
		//y tang
		//float ny=newobject.y;
		int i;
		this.object.add(object.size()-ontop, newobject);
		/*for(i=onbelow; i<object.size()-ontop; i++){
			GameObject temp = object.get(i);
			if(temp.y>ny){
				this.object.add(i, newobject);
				break;
			}
		}*/
		
		//if(i==object.size()-ontop) this.object.add(i, newobject);
		//if(linker!=null) linker.addGameObject(newobject);
	}
	public void removeGameObject(GameObject removeobject){
		int k= this.object.indexOf(removeobject);
		if(k>=object.size()-ontop) this.ontop=this.ontop-1;
		else if(k<onbelow) this.onbelow=this.onbelow-1;
		this.object.remove(removeobject);
		//if(linker!=null) linker.removeGameObject(removeobject);
	}
	public void removeGameObject(int location){
		GameObject o =this.object.get(location);
		if(location>=object.size()-ontop) this.ontop=this.ontop-1;
		else if(location<onbelow) this.onbelow=this.onbelow-1;
		this.object.remove(location);
		//if(linker!=null) linker.removeGameObject(o);
	}
	public int getsize(){
		return this.object.size();
	}
	public GameObject getGameObject(int i){
		return this.object.get(i);
	}
	public void addGameObjectOnTop(GameObject newobject){
		this.object.add(newobject);
		this.ontop=this.ontop+1;
		//if(linker!=null) linker.addGameObjectOnTop(newobject);
	}
	public void addGameObjectOnBelow(GameObject newobject){
		float ny=newobject.y;
		int i=0;
		for(i=0; i<onbelow; i++){
			GameObject temp = object.get(i);
			if(temp.y>ny){
				this.object.add(i, newobject);
				break;
			}
		}
		if(i==onbelow){
			this.object.add(onbelow, newobject);
		}
		//this.object.add(onbelow, newobject);
		this.onbelow= this.onbelow+1;
		//if(linker!=null) linker.addGameObjectOnBelow(newobject);
	}
	/*public void LinkTo(Handler h){
		linker=h;
	}*/
	public void sortGameObject(){
		for(int i=onbelow; i<object.size()-ontop-1; i++){
			GameObject temp = object.get(i);
			for(int j=i+1; j<object.size()-ontop; j++){
				GameObject temp2 = object.get(j);
				if(temp.y>temp2.y){
					GameObject c = temp;
					object.set(i, temp2);
					object.set(j, c);
					//temp=temp2;
					//temp2=c;
				}
			}
		}
	}
}
