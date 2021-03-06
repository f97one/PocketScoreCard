package net.formula97.android.pocket_score_card;

public final class ProjConstants {

	public final class Prefs {
		public static final String PREF_NAME = "PocketScoreCard_preference";
		public static final String PLAYER_NAME = "PlayerName";
		public static final String PLAYER_HDCP = "PlayerHDCP";
		public static final String CLUB_SETTING_TYPE = "ClubSettingType";
		public static final String CONTROL_FLIGHT_MODE = "ControlFlightMode";
		public static final String CHECK_14_CLUBS = "Check14Clubs";
		
		// クラブの名称：ウッド
		public static final String CLUB_1W = "Club1W";
		public static final String CLUB_3W = "Club3W";
		public static final String CLUB_4W = "Club4W";
		public static final String CLUB_5W = "Club5W";
		public static final String CLUB_6W = "Club6W";
		public static final String CLUB_7W = "Club7W";
		public static final String CLUB_UT3 = "ClubUt3";
		public static final String CLUB_UT5 = "ClubUt5";
		public static final String CLUB_UT7 = "ClubTu7";
		public static final String CLUB_UT9 = "ClubUt9";
		// クラブの名称：アイアン
		public static final String CLUB_3I = "Club3I";
		public static final String CLUB_4I = "Club4I";
		public static final String CLUB_5I = "Club5I";
		public static final String CLUB_6I = "Club6I";
		public static final String CLUB_7I = "Club7I";
		public static final String CLUB_8I = "Club8I";
		public static final String CLUB_9I = "Club9I";
		// クラブの名称：ウェッジ
		public static final String CLUB_AW = "ClubAw";
		public static final String CLUB_PW = "ClubPw";
		public static final String CLUB_LW = "ClubLw";
		public static final String CLUB_SW = "ClubSw";
		// クラブの名称：パター
		public static final String CLUB_PT = "ClubPt";
		
	}
	
	public final class DB {
		public static final String DBNAME = "PocketScoreCard.db";
		public static final String TABLE_CLUB_SETTINGS = "CLUB_SETTINGS";
		public static final String TABLE_COURSE_DATA = "COURSE_DATA";
		public static final String TABLE_COURSE_DETAILS = "COURSE_DETAILS";
		public static final String TABLE_PLAY_RESULTS = "PLAY_RESULTS";
		public static final int CURRENT_DB_VERSION = 1;
		public static final int OLDER_DB_VERSION = 1;
	}
}
