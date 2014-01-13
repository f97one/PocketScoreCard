/**
 * 
 */
package net.formula97.android.pocket_score_card;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author HAJIME
 *
 */
public class NetUtils {

	// 引き渡されたコンテクストを保持する
	private Context context;
	
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
	protected void setContext(Context context) {
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
}
