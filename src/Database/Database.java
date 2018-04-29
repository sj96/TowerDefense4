package Database;

import Config.Config;
import Log.Log;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

	private final static int DATABASE_VERSION = 1;

	@Override
	public void onCreate(SQLiteDatabase db) {
		// create table `map`
		db.execSQL(
				"CREATE TABLE `map` ( `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`highscore` INTEGER DEFAULT 0, `star` INTEGER DEFAULT 0 )");
		db.execSQL(
				"INSERT INTO `map` (`_id`, `highscore`, `star`) VALUES (1, 0, 0),(2, 0, 0),(3, 0, 0),(4, 0, 0), (5, 0, 0), (6, 0, 0), (7, 0, 0), (8, 0, 0), (9, 0, 0), (10, 0, 0), (11, 0, 0), (12, 0, 0);");
		// create table `setings` and default value
		db.execSQL(
				"CREATE TABLE `setings` ( `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `name` TEXT NOT NULL UNIQUE, `value` REAL )");
		db.execSQL("INSERT INTO `setings` (`_id`, `name`, `value`) VALUES" + "(1, 'LEVEL', 0)," + "(2, 'MASTER', 100),"
				+ "(3, 'MUSIC', 100)," + "(4, 'SFX', 100);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion != newVersion) {
			db.execSQL("DROP TABLE IF EXISTS `map`");
			db.execSQL("DROP TABLE IF EXISTS `setings`");
			onCreate(db);
		}
	}

	public Database(Context context) {
		super(context, Config.DatabaseName, null, DATABASE_VERSION);
	}

	public int getLevel() {
		int levelNow;
		try {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor result = db.rawQuery("SELECT `value` FROM `setings` WHERE `name` = 'LEVEL'", null);
			result.moveToFirst();
			levelNow = (int) result.getFloat(result.getColumnIndex("value"));
			db.close();
		} catch (SQLException e) {
			Log.write("Error: Can't get level now from DB!");
			levelNow = 0;
		}
		return levelNow;
	}

	public boolean nextLevel() {
		int oldLv = getLevel();
		if (oldLv < Config.MAX_LEVEL) {
			try {
				SQLiteDatabase db = this.getWritableDatabase();
				db.execSQL("UPDATE `setings` SET `value` = " + (oldLv + 1) + " WHERE `name` = 'LEVEL'");
				if (getLevel() > oldLv) {
					db.close();
					return true;
				}
			} catch (SQLException e) {
				Log.write("Error: Can't update level now to DB!");
				return false;
			}
		}
		return false;
	}

	public int getHighScore(int level) {
		int highScore;

		try {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor result = db.rawQuery("SELECT `highscore` FROM `map` WHERE `_id` = " + level, null);
			result.moveToFirst();
			highScore = (int) result.getInt(result.getColumnIndex("highscore"));
			db.close();
		} catch (SQLException e) {
			Log.write("Error: Can't get HighScore level " + level + " from DB!");
			highScore = 0;
		}

		return highScore;
	}

	public boolean setHighScore(int level, int highScore) {
		// check highScore
		if (highScore < 0) {
			highScore = 0;
		}

		try {
			SQLiteDatabase db = this.getWritableDatabase();
			db.execSQL("UPDATE `map` SET `highscore` = " + highScore + " WHERE `_id` = " + level);
			db.close();
			return true;
		} catch (SQLException e) {
			Log.write("Error: Can't update HighScore level " + level + " to DB!");
		}
		return false;
	}

	public int getStar(int level) {
		int star;

		try {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor result = db.rawQuery("SELECT `star` FROM `map` WHERE `_id` = " + level, null);
			result.moveToFirst();
			star = (int) result.getInt(result.getColumnIndex("star"));

			db.close();
		} catch (SQLException e) {
			Log.write("Error: Can't get star level " + level + " from DB!");
			star = 0;
		}

		if (star > 3) {
			star = 3;
		} else if (star < 0) {
			star = 0;
		}
		return star;
	}

	public boolean setStar(int level, int star) {
		// check highScore
		if (star > 3) {
			star = 3;
		} else if (star < 0) {
			star = 0;
		}

		try {
			SQLiteDatabase db = this.getWritableDatabase();
			db.execSQL("UPDATE `map` SET `star` = " + star + " WHERE `_id` = " + level);
			db.close();
			return true;
		} catch (SQLException e) {
			Log.write("Error: Can't update star level " + level + " to DB!");
		}
		return false;
	}

	// audio
	// -master
	public int getVolMaster() {
		int volumn;

		try {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor result = db.rawQuery("SELECT `value` FROM `setings` WHERE `name` = 'MASTER'", null);
			result.moveToFirst();
			volumn = (int) result.getInt(result.getColumnIndex("value"));
			db.close();
		} catch (SQLException e) {
			Log.write("Error: Can't get volumn master from DB!");
			volumn = 100;
		}

		return volumn;
	}

	public boolean setVolMaster(int volumn) {
		if (volumn < 0) {
			volumn = 0;
		} else if (volumn > 100) {
			volumn = 100;
		}

		try {
			SQLiteDatabase db = this.getWritableDatabase();
			db.execSQL("UPDATE `setings` SET `value` = " + volumn + " WHERE `name` = 'MASTER'");
			db.close();
			return true;
		} catch (SQLException e) {
			Log.write("Error: Can't update volumn master from DB!");
		}
		return false;
	}

	// -music
	public int getVolMusic() {
		int volumn;

		try {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor result = db.rawQuery("SELECT `value` FROM `setings` WHERE `name` = 'MUSIC'", null);
			result.moveToFirst();
			volumn = (int) result.getInt(result.getColumnIndex("value"));
			db.close();
		} catch (SQLException e) {
			Log.write("Error: Can't get volumn MUSIC from DB!");
			volumn = 100;
		}

		return volumn;
	}

	public boolean setVolMusic(int volumn) {
		if (volumn < 0) {
			volumn = 0;
		} else if (volumn > 100) {
			volumn = 100;
		}

		try {
			SQLiteDatabase db = this.getWritableDatabase();
			db.execSQL("UPDATE `setings` SET `value` = " + volumn + " WHERE `name` = 'MUSIC'");
			db.close();
			return true;
		} catch (SQLException e) {
			Log.write("Error: Can't update volumn MUSIC from DB!");
		}
		return false;
	}

	// -SFX
	public int getVolSFX() {
		int volumn;

		try {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor result = db.rawQuery("SELECT `value` FROM `setings` WHERE `name` = 'SFX'", null);
			result.moveToFirst();
			volumn = (int) result.getInt(result.getColumnIndex("value"));
			db.close();
		} catch (SQLException e) {
			Log.write("Error: Can't get volumn SFX from DB!");
			volumn = 100;
		}

		return volumn;
	}

	public boolean setVolSFX(int volumn) {
		if (volumn < 0) {
			volumn = 0;
		} else if (volumn > 100) {
			volumn = 100;
		}

		try {
			SQLiteDatabase db = this.getWritableDatabase();
			db.execSQL("UPDATE `setings` SET `value` = " + volumn + " WHERE `name` = 'SFX'");
			db.close();
			return true;
		} catch (SQLException e) {
			Log.write("Error: Can't update volumn SFX from DB!");
		}
		return false;
	}
}
