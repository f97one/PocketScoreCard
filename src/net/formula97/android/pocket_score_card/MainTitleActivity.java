package net.formula97.android.pocket_score_card;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class MainTitleActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_title, menu);
        return true;
    }

	/* (非 Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO 自動生成されたメソッド・スタブ

		Intent i = null;

		switch (item.getItemId()) {
		case R.id.course_info:
			break;
		case R.id.player_info:
			i = new Intent(this, PlayerInfoPreference.class);
			break;
		default:
			break;
		}

		startActivity(i);

		return super.onOptionsItemSelected(item);

	}


}
