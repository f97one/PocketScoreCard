/**
 *
 */
package net.formula97.android.pocket_score_card;

import java.util.HashMap;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

/**
 * @author kazutoshi
 *
 */
public class PlayerInfoPreference extends Activity implements OnItemSelectedListener {

	SharedPreferences sp;
	
	EditText editText_ownerName;
	EditText editText_ownerHDCP;
	Spinner spinner_clubSettingType;
	ToggleButton toggleButton_1w;
	ToggleButton toggleButton_3w;
	ToggleButton toggleButton_4w;
	ToggleButton toggleButton_5w;
	ToggleButton toggleButton_6w;
	ToggleButton toggleButton_7w;
	ToggleButton toggleButton_ut3;
	ToggleButton toggleButton_ut5;
	ToggleButton toggleButton_ut7;
	ToggleButton toggleButton_ut9;
	ToggleButton toggleButton_3i;
	ToggleButton toggleButton_4i;
	ToggleButton toggleButton_5i;
	ToggleButton toggleButton_6i;
	ToggleButton toggleButton_7i;
	ToggleButton toggleButton_8i;
	ToggleButton toggleButton_9i;
	ToggleButton toggleButton_pw;
	ToggleButton toggleButton_aw;
	ToggleButton toggleButton_lw;
	ToggleButton toggleButton_sw;
	ToggleButton toggleButton_pt;
	
	ToggleButton[] btns;
	int[] defaultClubUse;
	String[] clubNames;

	private int beforePosition = 0;
	
	/**
	 * 自動生成されたコンストラクタ。Activityなので特に何もしない。
	 */
	public PlayerInfoPreference() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/* (非 Javadoc)
	 * @see android.preference.PreferenceActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_owner_infomation);
		
		// Preferenceを取得
		sp = getSharedPreferences(ProjConstants.Prefs.PREF_NAME, MODE_PRIVATE);
		
		// ViewIdを取得
		editText_ownerName = (EditText)findViewById(R.id.editText_ownerName);
		editText_ownerHDCP = (EditText)findViewById(R.id.editText_ownerHDCP);
		spinner_clubSettingType = (Spinner)findViewById(R.id.spinner_clubSettingType);
		toggleButton_1w = (ToggleButton)findViewById(R.id.toggleButton_1w);
		toggleButton_3w = (ToggleButton)findViewById(R.id.toggleButton_3w);
		toggleButton_4w = (ToggleButton)findViewById(R.id.toggleButton_4w);
		toggleButton_5w = (ToggleButton)findViewById(R.id.toggleButton_5w);
		toggleButton_6w = (ToggleButton)findViewById(R.id.toggleButton_6w);
		toggleButton_7w = (ToggleButton)findViewById(R.id.toggleButton_7w);
		toggleButton_ut3 = (ToggleButton)findViewById(R.id.toggleButton_ut3);
		toggleButton_ut5 = (ToggleButton)findViewById(R.id.toggleButton_ut5);
		toggleButton_ut7 = (ToggleButton)findViewById(R.id.toggleButton_ut7);
		toggleButton_ut9 = (ToggleButton)findViewById(R.id.toggleButton_ut9);
		toggleButton_3i = (ToggleButton)findViewById(R.id.toggleButton_3i);
		toggleButton_4i = (ToggleButton)findViewById(R.id.toggleButton_4i);
		toggleButton_5i = (ToggleButton)findViewById(R.id.toggleButton_5i);
		toggleButton_6i = (ToggleButton)findViewById(R.id.toggleButton_6i);
		toggleButton_7i = (ToggleButton)findViewById(R.id.toggleButton_7i);
		toggleButton_8i = (ToggleButton)findViewById(R.id.toggleButton_8i);
		toggleButton_9i = (ToggleButton)findViewById(R.id.toggleButton_9i);
		toggleButton_pw = (ToggleButton)findViewById(R.id.toggleButton_pw);
		toggleButton_aw = (ToggleButton)findViewById(R.id.toggleButton_aw);
		toggleButton_lw = (ToggleButton)findViewById(R.id.toggleButton_lw);
		toggleButton_sw = (ToggleButton)findViewById(R.id.toggleButton_sw);
		toggleButton_pt = (ToggleButton)findViewById(R.id.toggleButton_pt);
		
		// ToggleButtonの配列を作る
		btns = getToggleButtonsArray();
		
		// XMLの配列定義から、クラブの一覧とデフォルト使用フラグを取得する
		defaultClubUse = getResources().getIntArray(R.array.DefaultClubUse);
		clubNames = getResources().getStringArray(R.array.ClubNames);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		// プリファレンスの保存
		savePlayerDataToPrefence();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// スピナーの選択イベントリスナーを定義
		spinner_clubSettingType.setOnItemSelectedListener(this);
		
		// プリファレンスの復元
		loadPlayerDataFromPreference();
	}

	/**
	 * プリファレンスからプレイヤー情報を取得して、画面に反映する。
	 */
	private void loadPlayerDataFromPreference() {
		editText_ownerName.setText(sp.getString(ProjConstants.Prefs.PLAYER_NAME, ""));
		editText_ownerHDCP.setText(String.valueOf(sp.getInt(ProjConstants.Prefs.PLAYER_HDCP, 0)));	// 初期値は0とする
		// TODO Spinnerへの値セットを実装する
		int clubSettingId = sp.getInt(ProjConstants.Prefs.CLUB_SETTING_TYPE, 0);
		spinner_clubSettingType.setSelection(clubSettingId);	// 初期値は0とする
		
		// 所有クラブのセッティングをリストア
		restoreClubSetting(clubSettingId);
		
	}
	
	/**
	 * プリファレンスへプレイヤー情報を保存する。
	 */
	private void savePlayerDataToPrefence() {
		SharedPreferences.Editor editor = sp.edit();
		
		// 名前とHDCPの格納
		editor.putString(ProjConstants.Prefs.PLAYER_NAME, editText_ownerName.getText().toString());
		editor.putInt(ProjConstants.Prefs.PLAYER_HDCP, Integer.parseInt(editText_ownerHDCP.getText().toString()));
		
		// Spinnerの値を保存する処理
		int position = spinner_clubSettingType.getSelectedItemPosition();
		editor.putInt(ProjConstants.Prefs.CLUB_SETTING_TYPE, position);
		saveClubSettings(position);
		
		editor.commit();
	}
	
	/**
	 * クラブセッティングIDに対応するクラブの所有情報をDBへ格納する。
	 * @param settingTypeIndex int型、所有情報をひもづけるクラブセッティングID
	 */
	private void saveClubSettings(int settingTypeIndex) {
		String using = "0";
		HashMap<String, String> map = new HashMap<String, String>();
		
		for (int index = 0; index < btns.length; index++) {
			if (btns[index].isChecked()) {
				using = "1";
			} else {
				using = "0";
			}
			map.put(clubNames[index], using);
		}
		// DBにクラブ設定をコミットする
		DbUtils dbUtils = new DbUtils(this, ProjConstants.DB.DBNAME, null, ProjConstants.DB.CURRENT_DB_VERSION);
		SQLiteDatabase database = dbUtils.getReadableDatabase();
		dbUtils.saveClubSettings(database, settingTypeIndex, map);
		
		database.close();
		dbUtils.close();
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// 変更前に現在の設定をセーブする
		saveClubSettings(beforePosition);
		// 使用クラブ設定をリストアする
		restoreClubSetting(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(android.widget.AdapterView)
	 */
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		// 初期値をセット
		
	}
	
	/**
	 * Activityに出しているToggleButonの配列を作る。
	 * @return　ToggleButton[]型、ToggleButonの配列
	 */
	public ToggleButton[] getToggleButtonsArray() {
		ToggleButton[] array = {
				toggleButton_1w,
				toggleButton_3w,
				toggleButton_4w,
				toggleButton_5w,
				toggleButton_6w,
				toggleButton_7w,
				toggleButton_ut3,
				toggleButton_ut5,
				toggleButton_ut7,
				toggleButton_ut9,
				toggleButton_3i,
				toggleButton_4i,
				toggleButton_5i,
				toggleButton_6i,
				toggleButton_7i,
				toggleButton_8i,
				toggleButton_9i,
				toggleButton_pw,
				toggleButton_aw,
				toggleButton_lw,
				toggleButton_sw,
				toggleButton_pt
		};
		
		return array;
	}
	
	/**
	 * DBから使用クラブ設定をリストアする。
	 * @param cluSettingId int型、使用クラブ設定のインデックス
	 */
	public void restoreClubSetting(int cluSettingId) {
		DbUtils dbUtils = new DbUtils(this, ProjConstants.DB.DBNAME, null, ProjConstants.DB.CURRENT_DB_VERSION);
		SQLiteDatabase database = dbUtils.getReadableDatabase();
		
		Cursor clubs = dbUtils.getClubSettings(database, cluSettingId);
		
		// 取得したCursorが先頭に巻き戻されていない場合は、先頭に巻き戻す
		if (!clubs.isFirst()) clubs.moveToFirst();
		
		boolean using = false;
		int index = 0;
		
		while (!clubs.isAfterLast()) {
			if (clubs.getInt(1) == 0) {
				using = false;
			} else {
				using = true;
			}
			// ボタンのチェックをリストアする
			btns[index].setChecked(using);
			
			index++;
			clubs.moveToNext();
		}
		// DBを閉じる
		clubs.close();
		database.close();
		dbUtils.close();
		
		beforePosition = cluSettingId;
	}
	
}
