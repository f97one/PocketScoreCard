/**
 * 
 */
package net.formula97.android.pocket_score_card;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author HAJIME
 *
 */
public class AppPreference extends PreferenceActivity {

	/**
	 * 
	 */
	public AppPreference() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.app_settings);
	}

}
