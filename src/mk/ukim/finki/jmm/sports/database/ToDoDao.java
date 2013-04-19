package mk.ukim.finki.jmm.sports.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mk.ukim.finki.jmm.sports.model.SportItem;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ToDoDao {
	// Database fields
	private SQLiteDatabase database;
	private ToDoDbOpenHelper dbHelper;
	private String[] allColumns = {
			ToDoDbOpenHelper.COLUMN_NAME, ToDoDbOpenHelper.COLUMN_CATEGORY,
			ToDoDbOpenHelper.COLUMN_DISTANCE, ToDoDbOpenHelper.COLUMN_LATITUDE, ToDoDbOpenHelper.COLUMN_LINK,
			ToDoDbOpenHelper.COLUMN_LONGITUDE};

	public ToDoDao(Context context) {
		dbHelper = new ToDoDbOpenHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		database.close();
		dbHelper.close();
	}

	public boolean insert(SportItem item) {
		long insertId = database.insert(ToDoDbOpenHelper.TABLE_NAME, null,
				itemToContentValues(item));
		return true;
	}

	public List<SportItem> getAllItems() {
		List<SportItem> items = new ArrayList<SportItem>();

		Cursor cursor = database.query(ToDoDbOpenHelper.TABLE_NAME, allColumns,
				null, null, null, null, null);

		if (cursor.moveToFirst()) {
			do {
				items.add(cursorToItem(cursor));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return items;
	}

	protected SportItem cursorToItem(Cursor cursor) {
		SportItem item = new SportItem();
		item.setName(cursor.getString(cursor
				.getColumnIndex(ToDoDbOpenHelper.COLUMN_NAME)));
		item.setCategory(cursor.getString(cursor
				.getColumnIndex(ToDoDbOpenHelper.COLUMN_CATEGORY)));
		item.setLink(cursor.getString(cursor
				.getColumnIndex(ToDoDbOpenHelper.COLUMN_LINK)));
		item.setLatitude(Long.parseLong(cursor.getString(cursor
				.getColumnIndex(ToDoDbOpenHelper.COLUMN_LATITUDE))));
		item.setLongitude(Long.parseLong(cursor.getString(cursor
				.getColumnIndex(ToDoDbOpenHelper.COLUMN_LONGITUDE))));
		item.setDistance(Long.parseLong(cursor.getString(cursor
				.getColumnIndex(ToDoDbOpenHelper.COLUMN_DISTANCE))));
		return item;
	}

	protected ContentValues itemToContentValues(SportItem item) {
		ContentValues values = new ContentValues();
		values.put(ToDoDbOpenHelper.COLUMN_NAME, item.getName());
		values.put(ToDoDbOpenHelper.COLUMN_CATEGORY, item.getCategory());
		values.put(ToDoDbOpenHelper.COLUMN_DISTANCE, item.getDistance());
		values.put(ToDoDbOpenHelper.COLUMN_LATITUDE, item.getLatitude());
		values.put(ToDoDbOpenHelper.COLUMN_LINK, item.getLink());
		values.put(ToDoDbOpenHelper.COLUMN_LONGITUDE, item.getLongitude());
		return values;
	}
	
	public void delete(){
		  database.delete(ToDoDbOpenHelper.TABLE_NAME, null, null);
	 }
	
}
