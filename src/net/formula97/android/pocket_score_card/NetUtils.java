/**
 * 
 */
package net.formula97.android.pocket_score_card;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author HAJIME
 *
 */
public class NetUtils {

	// 引き渡されたコンテクストを保持する
	private Context context;
	@SuppressWarnings("deprecation")
	private String airplaneModeSetting = android.provider.Settings.System.AIRPLANE_MODE_ON;
	
	/**
	 * コンストラクタ。
	 * @param context Context型、アプリケーションコンテクスト
	 */
	public NetUtils(Context context) {
		setContext(context);
	}

	/**
	 * 引き渡されたContextオブジェクトを返す。
	 * @return the context
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * 引き渡されたContextオブジェクトをフィールドに格納する。
	 * @param context the context to set
	 */
	private void setContext(Context context) {
		this.context = context;
	}

	/**
	 * 有効なネットワークに接続されているかどうかを判断する。
	 * @return boolean型、何らかのネットワークに接続されていればtrueを、オフラインならfalseを、それぞれ返す
	 */
	public boolean isValidNetwork() {
		ConnectivityManager man = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = man.getActiveNetworkInfo();
		return info != null ? true : false;
	}
	
	/**
	 * 端末が現在機内モード(=Airplane Mode)か否かを判断する。
	 * @return boolean型、機内モードがオンtrueを、オフならfalseを、それぞれ返す
	 */
	public boolean isAirplaneMode() {
		int airplaneMode = android.provider.Settings.System.getInt(getContext().getContentResolver(),
				airplaneModeSetting, 0);
		return airplaneMode == 1 ? true : false;
	}
	
	/**
	 * 機内モードを切り替え、その結果をブロードキャストする。
	 * @param turnAirplaneMode boolean型、機内モードをオンにする場合はtrueを、オフにする場合はfalseをセットする
	 */
	public void setAirplaneMode(boolean turnAirplaneMode) {
		// 機内モードオンの場合1を、オフの場合は0をセットする
		int airplaneModeFlag = turnAirplaneMode ? 1 : 0;
		android.provider.Settings.System.putInt(getContext().getContentResolver(),
				airplaneModeSetting,
				airplaneModeFlag);
		
		// 機内モードの切り替えをブロードキャストする
		// 切り替え後の状態は、あえてシステム設定を参照して行う。
		int state = isAirplaneMode() ? 1 : 0;
		Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
		intent.putExtra("state", state);
		getContext().sendBroadcast(intent);
	}
}
