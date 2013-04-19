package mk.ukim.finki.jmm.sports.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ToDoDbOpenHelper extends SQLiteOpenHelper {

	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_CATEGORY = "category";
	public static final String COLUMN_LINK = "link";
	
	public static final String COLUMN_DISTANCE = "distance";
	public static final String COLUMN_LATITUDE = "latitude";
	public static final String COLUMN_LONGITUDE = "longitude";
	
	public static final String TABLE_NAME = "SportsItems";

	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_NAME_EXPRESSION = "SportsDatabase.db";

	private static final String DATABASE_CREATE = String
			.format("create table %s (%s text not null, %s  text not null, %s  text not null, %s text not null,%s text not null,%s text not null);",
					TABLE_NAME,  COLUMN_NAME, COLUMN_CATEGORY, COLUMN_LINK,
					COLUMN_DISTANCE, COLUMN_LATITUDE, COLUMN_LONGITUDE
					);

	

	public ToDoDbOpenHelper(Context context) {
		super(context, DATABASE_NAME_EXPRESSION, null,
				DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
		onCreate(db);
	}

}
