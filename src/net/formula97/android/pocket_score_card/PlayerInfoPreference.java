/**
 *
 */
package net.formula97.android.pocket_score_card;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author kazutoshi
 *
 */
public class PlayerInfoPreference extends PreferenceActivity {

	/**
	 *
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
		addPreferencesFromResource(R.xml.player_info);
	}

}
