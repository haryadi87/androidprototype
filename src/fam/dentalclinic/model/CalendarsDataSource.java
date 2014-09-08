package fam.dentalclinic.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import fam.dentalclinic.src.MySQLiteHelper;

public class CalendarsDataSource {
	private SQLiteDatabase database;
	private MySQLiteHelper helper;
	private String[] calendarcolumns = {MySQLiteHelper.COLUMN_CALENDAR_ID, MySQLiteHelper.COLUMN_CALENDAR_TITLE,
			MySQLiteHelper.COLUMN_CALENDAR_DESC, MySQLiteHelper.COLUMN_CALENDAR_STARTDATE, MySQLiteHelper.COLUMN_CALENDAR_ENDDATE,
			MySQLiteHelper.COLUMN_CALENDAR_STARTTIME, MySQLiteHelper.COLUMN_CALENDAR_ENDTIME};
	public CalendarsDataSource(Context context) {
		helper = new MySQLiteHelper (context);
	}
	
	public void open() throws SQLException{
		database = helper.getWritableDatabase();
	}
	
	public void close(){
		helper.close();
	}
	
	public Calendar createCalendar(String title, String description, String startdate, String enddate, String starttime, String endtime) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_CALENDAR_TITLE, title);
		values.put(MySQLiteHelper.COLUMN_CALENDAR_DESC, description);
		values.put(MySQLiteHelper.COLUMN_CALENDAR_STARTDATE, startdate);
		values.put(MySQLiteHelper.COLUMN_CALENDAR_ENDDATE, enddate);
		values.put(MySQLiteHelper.COLUMN_CALENDAR_STARTTIME, starttime);
		values.put(MySQLiteHelper.COLUMN_CALENDAR_ENDTIME, endtime);
		long insertId = database.insert(MySQLiteHelper.TABLE_CALENDAR, null, values);
		Cursor cursor =  database.query(MySQLiteHelper.TABLE_CALENDAR, calendarcolumns, MySQLiteHelper.COLUMN_CALENDAR_ID+"="+insertId, null, null, null, null);
		cursor.moveToFirst();
		Calendar newCalendar = cursorToCalendar(cursor);
		cursor.close();
		return newCalendar;
	}
	
	public void deleteCalendar(Calendar calendar){
		long id = calendar.getId();
		Log.i("CalendarsDataSource", "Line 48");
		database.delete(MySQLiteHelper.TABLE_CALENDAR, MySQLiteHelper.COLUMN_CALENDAR_ID+"="+id, null);
	}
	
	public List<Calendar> getAllCalendars(){
		List<Calendar> calendars = new ArrayList<Calendar>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CALENDAR, calendarcolumns, null, null, null, null, MySQLiteHelper.COLUMN_CALENDAR_STARTDATE);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Calendar calendar = cursorToCalendar(cursor);
			calendars.add(calendar);
			cursor.moveToNext();
		}
		cursor.close();
		return calendars;
	}
	
	public Calendar getCalendar(Long id){
		Calendar calendar = new Calendar();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CALENDAR, calendarcolumns, MySQLiteHelper.COLUMN_CALENDAR_ID+"="+id, null, null, null, null);
		if(cursor!=null){
			cursor.moveToFirst();
			calendar = cursorToCalendar(cursor);
		} else{
			Log.w("CalendarsDataSource", "Line 75");
		}
		cursor.close();
		return calendar;
	}
	
	private Calendar cursorToCalendar(Cursor cursor){
		Calendar calendar = new Calendar();
		calendar.setId(cursor.getLong(0));
		calendar.setTitle(cursor.getString(1));
		calendar.setDescription(cursor.getString(2));
		calendar.setStartDate(cursor.getString(3));
		calendar.setEndDate(cursor.getString(4));
		calendar.setStartTime(cursor.getString(5));
		calendar.setEndTime(cursor.getString(6));
		return calendar;
	}
}