package ca.fusiondev.qfradio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "data";
	public static final int VERSION = 1;
	public static final String TABLE_NAME = "comment_table";

	public static final String C_ID = "_id";
	public static final String NAME = "name";
	public static final String COMMENT = "comment";
	public static final String LOCATION = "location";
	public static final String TIME = "time";

	private final String createDB = "create table if not exists " + TABLE_NAME + " ( " 
			+ C_ID + " Integer primary key autoincrement, " 
			+ NAME + " text, " 
			+ COMMENT + " text, "
			+ LOCATION + " text, "
			+ TIME + " Integer);";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createDB);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table " + TABLE_NAME);
	}

}
