package fam.dentalclinic.src;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper{

	public static final String TABLE_PATIENT="patients";
	public static final String COLUMN_PATIENT_NAME="name";
	public static final String COLUMN_PATIENT_ID="id";
	public static final String COLUMN_PATIENT_NUMBER="number";
	public static final String COLUMN_PATIENT_ADDRESS="address";
	public static final String COLUMN_PATIENT_EMAIL="email";
	
	public static final String TABLE_CALENDAR="calendars";
	public static final String COLUMN_CALENDAR_ID="id";
	public static final String COLUMN_CALENDAR_TITLE="title";
	public static final String COLUMN_CALENDAR_DESC="description";
	public static final String COLUMN_CALENDAR_STARTDATE="startdate";
	public static final String COLUMN_CALENDAR_STARTTIME="starttime";
	public static final String COLUMN_CALENDAR_ENDDATE="enddate";
	public static final String COLUMN_CALENDAR_ENDTIME="endtime";
	
	private static final String DATABASE_NAME = "dentalclinic.db";
	private static final int DATABASE_VERSION=3;
	
	private static final String DATABASE_CREATE_PATIENT = "create table "+TABLE_PATIENT+"("+COLUMN_PATIENT_ID
			+" integer primary key autoincrement, " + COLUMN_PATIENT_NAME 
			+" text not null, " + COLUMN_PATIENT_NUMBER
			+" text not null, " + COLUMN_PATIENT_ADDRESS
			+" text not null, " + COLUMN_PATIENT_EMAIL
			+" text not null);";
	//create table patients(id integer primary key autoincrement, name text not null, 
	//number text not null, address text not null, email text not null);
	
	private static final String DATABASE_CREATE_CALENDAR = "create table "+TABLE_CALENDAR+"("+COLUMN_CALENDAR_ID
			+ " integer primary key autoincrement, " + COLUMN_CALENDAR_TITLE
			+ " text not null, " + COLUMN_CALENDAR_DESC
			+ " text not null, " + COLUMN_CALENDAR_STARTDATE
			+ " text not null, " + COLUMN_CALENDAR_ENDDATE
			+ " text not null, " + COLUMN_CALENDAR_STARTTIME
			+ " text not null, " + COLUMN_CALENDAR_ENDTIME
			+ " text not null);";
	
	//create table calendar(id integer primary key autoincrement, title text not null,
	//description text not null, startdate text not null, enddate text not null, starttime text not null,
	//endtime text not null);
	
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE_PATIENT);
		db.execSQL(DATABASE_CREATE_CALENDAR);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " +oldVersion+"to "+newVersion);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALENDAR);
		onCreate(db);
	}
	
}