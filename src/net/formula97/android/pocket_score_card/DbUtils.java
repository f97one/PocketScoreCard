/**
 * 
 */
package net.formula97.android.pocket_score_card;

import java.util.HashMap;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
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
			+ "USING INTEGER DEFAULT 0);";
	
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
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// テーブルの作成
		db.execSQL(CREATE_CLUB_SETTINGS);

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
		String[] columns = {"CLUB_NAME", "USING"};
		String selection = "SETTING_TYPE_ID = ?";
		String[] selectionArgs = {String.valueOf(clubSettingId)};
		String groupBy = null;
		String having = null;
		String orderBy = "CLUB_NAME";
		
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
	public void saveClubSettings(SQLiteDatabase db, int clubSettingId, HashMap<String, String> currentSettings, String[] allClubs) {
		// 検索条件をANDで二つ指定するので、execSQLで直接実行する
		try {
			// Transactionに処理をまとめる
			db.beginTransaction();
			for (int i = 0; i < currentSettings.size(); i++) {
				db.execSQL(
						"UPDATE " + ProjConstants.DB.TABLE_CLUB_SETTINGS + " "
						+ "SET USING = '" + currentSettings.get(allClubs[i]) + "' "
						+ "WHERE SETTING_TYPE_ID = '" + String.valueOf(clubSettingId)
						+ "' AND CLUB_NAME = '" + allClubs[i] + "';"
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}
}
