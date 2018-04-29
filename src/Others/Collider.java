package Others;

public class Collider {
	private float height;
	private float width;
	private float x;
	private float y;

	public Collider(float x, float y, float h, float w) {
		this.x = x;
		this.y = y;
		this.height = h;
		this.width = w;
	}

	public boolean isCollider(float x, float y) {
		if (this.x < x && (this.x + this.width) > x && this.y < y && (this.y + this.height) > y)
			return true;
		return false;
	}

	public boolean isCollider(Collider collider) {
		boolean flag = this.isColliderConner(collider);
		if (!flag)
			flag = collider.isColliderConner(this);
		return flag;
	}

	private boolean isColliderConner(Collider collider) {
		if (this.isCollider(collider.getX(), collider.getX())
				|| this.isCollider(collider.getX() + collider.getWidth(), collider.getX())
				|| this.isCollider(collider.getX(), collider.getX() + collider.getWidth())
				|| this.isCollider(collider.getX() + collider.getWidth(), collider.getX() + collider.getWidth()))
			return true;
		return false;
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public float getHeight() {
		return this.height;
	}

	public float getWidth() {
		return width;
	}

	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
}
