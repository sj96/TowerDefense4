package Others;

import java.io.IOException;

import Database.Database;
import Log.Log;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

public class Audio {

	Context context;
	Boolean isRunning;

	public Audio(Context context) {
		this.context = context;
		isRunning = true;
	}

	public void setRunning(Boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void playBG(int res) {
		// MediaPlayer mp = MediaPlayer.create(context.getApplicationContext(), res);
		// mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
		// mp.setLooping(true);
		// float volumn = getVolumnMusic();
		// mp.setVolume(volumn, volumn);
		// try {
		// mp.prepare();
		// } catch (IllegalStateException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// mp.start();

		final MediaPlayer mp = new MediaPlayer();
		String path = "android.resource://" + context.getPackageName() + "/" + res;
		Uri myUri = Uri.parse(path);
		try {
			mp.setDataSource(context, myUri);
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mp.setLooping(true);
		float volumn = getVolumnMusic();
		mp.setVolume(volumn, volumn);
		try {
			mp.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mp.start();

		Thread audio = new Thread() {
			@Override
			public void run() {
				while (isRunning) {
					if (!mp.isPlaying()) {
						mp.start();
						Log.write("BG replay");
					}
				}
				mp.release();
				Log.write("BG end");
			}
		};
		audio.start();
	}

	public void playSFX(int res) {
		MediaPlayer mp = MediaPlayer.create(context, res);
		mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
		float volumn = getVolumnMusic();
		mp.setVolume(volumn, volumn);

		mp.start();
	}

	public float getVolumnMusic() {
		float volumn;

		Database db = new Database(context);

		float master = db.getVolMaster() / 100f;
		float music = db.getVolMusic() / 100f;

		volumn = music * master * .8f;

		return volumn;
	}

	public float getVolumnSFX() {
		float volumn;

		Database db = new Database(context);

		float master = db.getVolMaster() / 100f;
		float sfx = db.getVolSFX() / 100f;

		volumn = sfx * master;

		return volumn;
	}
}
