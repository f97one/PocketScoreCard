/**
 * 
 */
package net.formula97.android.pocket_score_card;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * @author HAJIME
 *
 */
public class DbUtils extends SQLiteOpenHelper {

	// CLUB_SETTINGSテーブルのDDL
	private final String CREATE_CLUB_SETTINGS = 
			"CREATE TABLE CLUB_SETTINGS ("
			+ "_id INTEGER PRIMARY KEY, "
			+ "SETTING_TYPE_ID INTEGER, "
			+ "CLUB_NAME TEXT, "
			+ "USING INTEGER DEFAULT 0)";
	
	/**
	 * API Level 10のコンストラクタ。基本的にこちらを使う。
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public DbUtils(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	/**
	 * API Level 11以上のコンストラクタ。SQLiteのエラーハンドラAPIが利用できる。
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 * @param errorHandler
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public DbUtils(Context context, String name, CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
