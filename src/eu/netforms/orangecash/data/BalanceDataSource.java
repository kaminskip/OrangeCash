package eu.netforms.orangecash.data;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import eu.netforms.orangecash.model.Info;
import eu.netforms.orangecash.model.Trans;
import eu.netforms.orangecash.model.Update;

public class BalanceDataSource {

	public static final String LOG_TAG = "DATA_BASE";

	private SQLiteDatabase database;
	private OrangeCashSQLiteOpenHelper dbHelper;

	public BalanceDataSource(Context context) {
		this.dbHelper = new OrangeCashSQLiteOpenHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public List<Trans> getTrans() {
		open();
		List<Trans> transList = new ArrayList<Trans>();
		Cursor cursor = database.query(Trans.TABLE_TRANS,
				Trans.ALL_COLUMNS, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Trans trans = Trans.cursorToTrans(cursor);
			transList.add(trans);
			cursor.moveToNext();
		}
		cursor.close();
		close();
		return transList;
	}
	
	public Info getInfo() {
		Info info = null; 
		Cursor cursor = database.query(Info.TABLE_INFO,
				Info.ALL_COLUMNS, null, null, null, null, null);
		cursor.moveToFirst();
		info = Info.cursorToInfo(cursor);
		cursor.close();
		return info;
	}
	
	public void updateDB(Update update) {
		open();
		clearDb();
		insertInfo(update.getInfo());
		for (Trans trans : update.getTransList()) {
			insertTrans(trans);
		}
		close();
		Log.i(LOG_TAG, "Update complited " + update);
	}

	private Info insertInfo(Info info) {
		long insertId = database.insert(Info.TABLE_INFO, null, info.getAllContentValues());
		Log.i(LOG_TAG, "Inserted " + info);
		info.setId(insertId);
		return info;
	}

	private Trans insertTrans(Trans trans) {
		long insertId = database.insert(Trans.TABLE_TRANS, null, trans.getAllContentValues());
		Log.i(LOG_TAG, "Inserted " + trans);
		trans.setId(insertId);
		return trans;
	}

	private void clearDb() {
		database.delete(Info.TABLE_INFO, null, null);
		Log.i(LOG_TAG, Info.TABLE_INFO + " table cleared");
		database.delete(Trans.TABLE_TRANS, null, null);
		Log.i(LOG_TAG, Trans.TABLE_TRANS + " table cleared");
	}
}
