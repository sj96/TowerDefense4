package playing;

public class SubSetting {
	//luu tru cac gia tri tam cua man choi hien tai
	public static int ruler;
	public static float x=0; //chi so keo tha theo x
	public static float y=0; //chi so keo tha theo y
	public static final int maxX=15; //be ngan toi da
	public static final int maxY=11; //be doc toi da
	public static boolean onpause=false; //dung game?
	public static int timePlus; //bo diem, quy dinh game chay hay dung, toc do chay cua game phu thuoc vao bo diem
	//vi du bo diem: timePlus=0: game dung; timePlus=n: game chay binh thuong; timePlus=2n: game chay voi toc do x2
	public static int BuildPoint=0; //diem dung xay tru (BP)
	public static int scorce=0; // diem dat duoc do giet quai
	public static int wave=0; //dot song thu wave
	public static int timeNext=0; // thoi gian toi dot song tiep theo
	public static int heat=4; //mau (mang) cau nguoi choi
	public static int lever=0; //lever hien tai
	public static int lastSpeed=0;
	public static int tank, archer, posion, wizar; //cac gia tri lever cua tru
	public static int numtower; //so luong tru toi da cho tung loai
	public static boolean isWin=false; //win game?
	public static boolean nodie=true; //khong map tru nao?
	public static boolean exit=false; //thoat?
}
