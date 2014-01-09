/**
 * 
 */
package net.formula97.android.pocket_score_card;

import java.util.HashMap;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

/**
 * @author HAJIME
 *
 */
public class DbUtils extends SQLiteOpenHelper {

	// CLUB_SETTINGSテーブルのDDL
	private final String[] FIELDS_CLUB_SETTINGS = {
			"SETTING_TYPE_ID",
			"CLUB_NAME",
			"USINGFLAG"
	};
	private final String CREATE_CLUB_SETTINGS = 
			"CREATE TABLE " + ProjConstants.DB.TABLE_CLUB_SETTINGS +" ("
			+ "_id INTEGER PRIMARY KEY, "
			+ FIELDS_CLUB_SETTINGS[0] + " INTEGER, "
			+ FIELDS_CLUB_SETTINGS[1] + " TEXT, "
			+ FIELDS_CLUB_SETTINGS[2] + " INTEGER DEFAULT 0);";
	private Context ctx;
	
	/**
	 * API Level 10のコンストラクタ。基本的にこちらを使う。
	 * dbファイルがなかったら勝手に作るので、スーパークラスのコンストラクタを呼ぶだけ。
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public DbUtils(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// Contextの保存
		setCtx(context);
	}

	/**
	 * API Level 11以上のコンストラクタ。SQLiteのエラーハンドラAPIが利用できる。
	 * dbファイルがなかったら勝手に作るので、スーパークラスのコンストラクタを呼ぶだけ。
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 * @param errorHandler
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public DbUtils(Context context, String name, CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// Contextの保存
		setCtx(context);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// テーブルの作成
		createTables(db);
		setInitialClubSettings(db);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	/**
	 * クラブセッティングIDに応じたクラブ名と使用フラグを返す。
	 * @param db SQLiteDatabase型、操作するDBインスタンス
	 * @param clubSettingId int型、取得するクラブセッティングID
	 * @return Cursor型、DBから取得した「クラブ名,使用フラグ（0=使用しない, 1=使用する）」の順の連想配列(みたいなもの)
	 */
	public Cursor getClubSettings(SQLiteDatabase db, int clubSettingId) {
		String table = ProjConstants.DB.TABLE_CLUB_SETTINGS;
		String[] columns = {FIELDS_CLUB_SETTINGS[1], FIELDS_CLUB_SETTINGS[2]};
		String selection = FIELDS_CLUB_SETTINGS[0] + " = ?";
		String[] selectionArgs = {String.valueOf(clubSettingId)};
		String groupBy = null;
		String having = null;
		String orderBy = FIELDS_CLUB_SETTINGS[1];
		
		Cursor q = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		q.moveToFirst();
		return q;
	}

	/**
	 * 使用クラブ設定をDBへセーブする。
	 * @param db SQLiteDatabase型、操作するDBインスタンス
	 * @param clubSettingId int型、取得するクラブセッティングID
	 * @param currentSettings HashMap&lt;String, String&gt;型、セーブするクラブ設定
	 * @param allClubs String[]型、クラブ全体の一覧
	 */
	public void saveClubSettings(SQLiteDatabase db, int clubSettingId, HashMap<String, String> currentSettings) {
		// クラブ一覧の取得
		String[] clubs = getDBClubList();
		
		// 検索条件をANDで二つ指定するので、execSQLで直接実行する
		try {
			// Transactionに処理をまとめる
			db.beginTransaction();
			for (int i = 0; i < currentSettings.size(); i++) {
				db.execSQL(
						"UPDATE " + ProjConstants.DB.TABLE_CLUB_SETTINGS + " "
						+ "SET " + FIELDS_CLUB_SETTINGS[2] +" = '" + currentSettings.get(clubs[i]) + "' "
						+ "WHERE " + FIELDS_CLUB_SETTINGS[0] + " = '" + String.valueOf(clubSettingId)
						+ "' AND " + FIELDS_CLUB_SETTINGS[1] + " = '" + clubs[i] + "';"
						);
			}
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}

	/**
	 * @return
	 */
	private String[] getDBClubList() {
		String[] clubs = getCtx().getResources().getStringArray(R.array.ClubNames);
		return clubs;
	}
	
	/**
	 * 使用クラブ一覧の初期データを作成する。
	 * @param db SQLiteDatabase型、操作するDBインスタンス
	 */
	public void setInitialClubSettings(SQLiteDatabase db) {
		// クラブ一覧の取得
		String[] clubs = getDBClubList();
		String[] usingFlags = getCtx().getResources().getStringArray(R.array.DefaultClubUse);
		long inserted = 0;
		try {
			db.beginTransaction();
			ContentValues val = new ContentValues();
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < clubs.length; j++) {
					val.put(FIELDS_CLUB_SETTINGS[0], String.valueOf(i));	// セッティングID
					val.put(FIELDS_CLUB_SETTINGS[1], clubs[j]);				// クラブ名
					val.put(FIELDS_CLUB_SETTINGS[2], usingFlags[j]);		// デフォルトセッティング
					inserted = db.insertOrThrow(ProjConstants.DB.TABLE_CLUB_SETTINGS, null, val);
					//Log.d("DbUtils#setInitialClubSettings", "SettingId: " + String.valueOf(i) + ", ClubName: " + clubs[j] + " inserted.");
					val.clear();
				}
			}
			db.setTransactionSuccessful();
			Log.d("DbUtils#setInitialClubSettings", "transaction succeeded.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.w("DbUtils#setInitialClubSettings", "no rows was inserted. throwed SQLException.");
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}

	/**
	 * 引き渡されたContextオブジェクトを取り出す。
	 * @return the ctx
	 */
	public Context getCtx() {
		return ctx;
	}

	/**
	 * 引き渡されたContextオブジェクトをフィールドへ格納する。
	 * @param ctx the ctx to set
	 */
	public void setCtx(Context ctx) {
		this.ctx = ctx;
	}
	
	public void createTables(SQLiteDatabase db) {
		// CLUB_SETTINGSテーブルの作成
		try {
			db.execSQL(CREATE_CLUB_SETTINGS);
			Log.i("DbUtils#createTables()", "table created.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
