package Config;

public enum PointLevel {

	LV1(0.0833f, 0.5472f),
	LV2(0.2068f, 0.3139f),
	LV3(0.1630f, 0.6426f),
	LV4(0.3844f, 0.4620f),
	LV5(0.4495f, 0.2666f),
	LV6(0.5536f, 0.4139f),
	LV7(0.6130f, 0.1194f),
	LV8(0.6896f, 0.3648f),
	LV9(0.7802f, 0.2380f),
	LV10(0.7490f,0.5556f),
	LV11(0.3406f, 0.8611f),
	LV12(0.6250f,0.8852f)
	;
	

	private float x;
	private float y;

	PointLevel(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
}
