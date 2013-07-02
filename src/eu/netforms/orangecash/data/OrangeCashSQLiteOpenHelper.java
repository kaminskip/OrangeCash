package eu.netforms.orangecash.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import eu.netforms.orangecash.model.Info;
import eu.netforms.orangecash.model.Trans;

public class OrangeCashSQLiteOpenHelper extends SQLiteOpenHelper {

	public static final String LOG_TAG = "DATA_BASE_DDL";

	private static final String DATABASE_NAME = "orangecash.db";
	private static final int DATABASE_VERSION = 1;

	public OrangeCashSQLiteOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createInfoTable(db);
		createTransTable(db);
	}
	
	private void createInfoTable(SQLiteDatabase db){
		StringBuilder table = new StringBuilder();
		table.append("create table " + Info.TABLE_INFO);
		table.append("(");
		table.append(Info.COLUMN_INFO_ID + " integer primary key autoincrement, ");
		table.append(Info.COLUMN_INFO_UPDATE_DATE + " integer, ");
		table.append(Info.COLUMN_INFO_DATE + " text, ");
		table.append(Info.COLUMN_INFO_CARD_NR + " text, ");
		table.append(Info.COLUMN_INFO_CARD_VALID_THRU + " text, ");
		table.append(Info.COLUMN_INFO_CARD_TYPE + " text, ");
		table.append(Info.COLUMN_INFO_CARD_STATUS + " text, ");
		table.append(Info.COLUMN_INFO_BALANCE + " text, ");
		table.append(Info.COLUMN_INFO_SUM + " text");
		table.append(");");
		Log.i(LOG_TAG, table.toString());
		db.execSQL(table.toString());
	}
	
	private void createTransTable(SQLiteDatabase db){
		StringBuilder table = new StringBuilder();
		table.append("create table " + Trans.TABLE_TRANS);
		table.append("(");
		table.append(Trans.COLUMN_TRANS_ID + " integer primary key autoincrement, ");
		table.append(Trans.COLUMN_TRANS_UPDATE_DATE + " integer, ");
		table.append(Trans.COLUMN_TRANS_DATE + " text, ");
		table.append(Trans.COLUMN_TRANS_AMOUNT_CARD + " text, ");
		table.append(Trans.COLUMN_TRANS_AMOUNT_SETTLEMENT + " text, ");
		table.append(Trans.COLUMN_TRANS_AMOUNT_ORGINAL + " text, ");
		table.append(Trans.COLUMN_TRANS_DESC + " text, ");
		table.append(Trans.COLUMN_TRANS_PLACE + " text");
		table.append(");");
		Log.i(LOG_TAG, table.toString());
		db.execSQL(table.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(LOG_TAG,
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		String dropTrans = "DROP TABLE IF EXISTS " + Trans.TABLE_TRANS;
		Log.i(LOG_TAG, dropTrans);
		db.execSQL(dropTrans);
		String dropInfo = "DROP TABLE IF EXISTS " + Info.TABLE_INFO;
		Log.i(LOG_TAG, dropInfo);
		db.execSQL(dropInfo);
		onCreate(db);
	}

}
