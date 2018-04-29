package Config;

public enum ConfigMonter {
	
	QUAI(10, 10, 10f, 10);
	
	private int atk;
	private int def;
	private float speed;
	private int score;
	
	ConfigMonter(int atk, int def, float speed, int score) {
		this.atk = atk;
		this.def = def;
		this.speed = speed;
		this.score = score;
	}
	
	public int getAtk() {
		return this.atk;
	}
	public int getDef() {
		return this.def;
	}
	public float getSpeed() {
		return this.speed;
	}
	public int getScore() {
		return this.score;
	}
}
