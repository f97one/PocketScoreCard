package net.formula97.android.pocket_score_card;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v4.app.NavUtils;

//@EActivity
public class MainTitleActivity extends Activity implements OnClickListener {

//	@ViewById(R.id.btn_playcont)
	Button btn_playcont;
//	@ViewById(R.id.btn_add_browse_course)
	Button btn_add_browse_course;
//	@ViewById(R.id.btn_player_info)
	Button btn_player_info;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_title);
        
        // ViewIDの取得
        btn_playcont = (Button)findViewById(R.id.btn_playcont);
        btn_add_browse_course = (Button)findViewById(R.id.btn_add_browse_course);
        btn_player_info = (Button)findViewById(R.id.btn_player_info);
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
		switch (item.getItemId()) {
		case R.id.course_info:
			btn_add_browse_course_Click();
			break;
		case R.id.player_info:
			btn_player_info_Click();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);

	}

	/**
	 * クリックイベント発生時の処理。
	 * @see android.view.View.OnClickListener#onClick(View)
	 */
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btn_playcont:
			// TODO プレイ／続行ボタンを押したときの処理を書く
			btn_playcont_Click();
			break;
		case R.id.btn_add_browse_course:
			// TODO コース情報表示ボタンを押したときの処理を書く
			btn_add_browse_course_Click();
			break;
		case R.id.btn_player_info:
			// TODO プレイヤー情報を表示する
			btn_player_info_Click();
			break;
		default:
			Log.d("MainTitleActivity#onClik", "nothing to operate");
			break;
		}
	}

	/**
	 * プレイヤー情報表示ボタンを押したときの処理。
	 */
	private void btn_player_info_Click() {
		Log.d("MainTitleActivity#btn_player_info_Click", "Player info. button clicked.");
		
		Intent i = new Intent(this, PlayerInfoPreference.class);
		startActivity(i);
	}

	/**
	 * コース情報表示ボタンを押したときの処理。
	 */
	private void btn_add_browse_course_Click() {
		Log.d("MainTitleActivity#btn_add_browse_course_Click", "Add/Browse course button clicked.");
	}

	/**
	 * プレイ／再開ボタンを押したときの処理。
	 */
	private void btn_playcont_Click() {
		Log.d("MainTitleActivity#btn_playcont_Click", "Play/Cont button clicked.");
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
		
		btn_playcont.setOnClickListener(this);
		btn_add_browse_course.setOnClickListener(this);
		btn_player_info.setOnClickListener(this);
	}


}
