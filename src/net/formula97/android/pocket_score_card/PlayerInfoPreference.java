/**
 *
 */
package net.formula97.android.pocket_score_card;

import android.app.Activity;
import android.content.SharedPreferences;
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
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
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
		editText_ownerName.setText(sp.getString(ProjConstants.Prefs.PREF_NAME, ""));
		editText_ownerHDCP.setText(String.valueOf(sp.getInt(ProjConstants.Prefs.PLAYER_HDCP, 0)));	// 初期値は0とする
		// TODO Spinnerへの値セットを実装する
		spinner_clubSettingType.setSelection(sp.getInt(ProjConstants.Prefs.CLUB_SETTING_TYPE, 0));	// 初期値は0とする
		
		// 所有クラブのセッティングをリストア
		//   以下のクラブはデフォルトで所有しているものとする。
		//     1W, 7W, Ut7, 7I, 9I, PW, SW, PT
		// TODO getClubSettingArray()で取得したboolean[]に変更する。
		toggleButton_1w.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_1W, true));		// 1W
		toggleButton_3w.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_3W, false));		// 3W
		toggleButton_4w.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_4W, false));		// 4W
		toggleButton_5w.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_5W, false));		// 5W
		toggleButton_6w.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_6W, false));		// 6W
		toggleButton_7w.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_7W, true));		// 7W
		toggleButton_ut3.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_UT3, false));	// Ut3
		toggleButton_ut5.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_UT5, false));	// Ut5
		toggleButton_ut7.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_UT7, true));		// Ut7
		toggleButton_ut9.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_UT9, false));	// Ut9
		toggleButton_3i.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_3I, false));		// 3I
		toggleButton_4i.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_4I, false));		// 4I
		toggleButton_5i.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_5W, false));		// 5I
		toggleButton_6i.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_6I, false));		// 6I
		toggleButton_7i.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_7I, true));		// 7I
		toggleButton_8i.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_8I, false));		// 8I
		toggleButton_9i.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_9I, true));		// 9I
		toggleButton_pw.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_PW, true));		// PW
		toggleButton_aw.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_AW, false));		// AW
		toggleButton_lw.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_LW, false));		// LW
		toggleButton_sw.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_SW, true));		// SW
		toggleButton_pt.setChecked(sp.getBoolean(ProjConstants.Prefs.CLUB_PT, true));		// PT
	}
	
	/**
	 * プリファレンスへプレイヤー情報を保存する。
	 */
	private void savePlayerDataToPrefence() {
		SharedPreferences.Editor editor = sp.edit();
		
		// 名前とHDCPの格納
		editor.putString(ProjConstants.Prefs.PLAYER_NAME, editText_ownerName.getText().toString());
		editor.putInt(ProjConstants.Prefs.PLAYER_HDCP, Integer.parseInt(editText_ownerHDCP.getText().toString()));
		
		// TODO Spinnerの値を保存する処理を書く
		int position = spinner_clubSettingType.getSelectedItemPosition();
		editor.putInt(ProjConstants.Prefs.CLUB_SETTING_TYPE, position);
		saveClubSettingArray(position);
		
		editor.commit();
	}
	
	/**
	 * クラブセッティングを画面へ反映するために、セッティングタイプに対応するクラブの所有有無をDBから取得する。
	 * @return ToggleButtonのチェック状態にそのまま反映できる、boolean型の配列
	 */
	private boolean[] getClubSettingArray() {
		// TODO 
		
		return null;
	}
	
	/**
	 * クラブセッティングIDに対応するクラブの所有情報をDBへ格納する。
	 * @param settingTypeIndex int型、所有情報をひもづけるクラブセッティングID
	 */
	private void saveClubSettingArray(int settingTypeIndex) {
		
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(android.widget.AdapterView)
	 */
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		// 初期値をセット
		
	}
}
