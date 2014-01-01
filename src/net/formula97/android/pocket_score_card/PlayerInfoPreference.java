/**
 *
 */
package net.formula97.android.pocket_score_card;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author kazutoshi
 *
 */
public class PlayerInfoPreference extends Activity {

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
		setContentView(R.layout.activity_owner_infomation);
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
	}

	private void loadFromPreference() {
		
	}
	
	private void saveToPrefence() {
		
	}
}
