package fam.dentalclinic.view;

import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.TimeZone;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import fam.dentalclinic.MainActivity;
import fam.dentalclinic.R;
import fam.dentalclinic.controller.DatePickerFragment;
import fam.dentalclinic.controller.TimePickerFragment;
import fam.dentalclinic.model.CalendarsDataSource;

public class CalendarDetailActivity extends Activity implements View.OnClickListener {
	Bundle extras;
	private CalendarsDataSource datasource;
	GregorianCalendar startCalDate;
	GregorianCalendar endCalDate;
	long calendarId,eventId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_detail);
		calendarId = getCalendarID();
		datasource = new CalendarsDataSource(getApplicationContext());
		datasource.open();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		final int id = v.getId();
		extras = new Bundle();
		switch(id) {
		case R.id.calendarStartDate:
			extras.putString("Date", "Start");
			DatePickerFragment newStartDateFragment = new DatePickerFragment(extras);
			newStartDateFragment.show(getFragmentManager(), "datePicker");
			break;
		case R.id.calendarStartTime:
			extras.putString("Time", "Start");
			TimePickerFragment newStartTimeFragment = new TimePickerFragment(extras);
			newStartTimeFragment.show(getFragmentManager(), "timePicker");
			break;
		case R.id.calendarEndDate:
			extras.putString("Date", "End");
			DatePickerFragment newEndDateFragment = new DatePickerFragment(extras);
			newEndDateFragment.show(getFragmentManager(), "datePicker");
			break;
		case R.id.calendarEndTime:
			extras.putString("Time", "End");
			TimePickerFragment newEndTimeFragment = new TimePickerFragment(extras);
			newEndTimeFragment.show(getFragmentManager(), "timePicker");
			break;
		/*case R.id.calendarListParticipant:
			PatientNameFragment newFragment = new PatientNameFragment();
			newFragment.show(getFragmentManager(), "ParticipantDialog");
			break;*/
		case R.id.saveCalendar:
			calendarId= getCalendarID();
			ContentValues values = new ContentValues();
			
			EditText title = (EditText)findViewById(R.id.calendarTitle);
			EditText description = (EditText)findViewById(R.id.calendarDescription);
			Button startDate = (Button)findViewById(R.id.calendarStartDate);
			Button startTime = (Button)findViewById(R.id.calendarStartTime);
			Button endDate = (Button)findViewById(R.id.calendarEndDate);
			Button endTime = (Button)findViewById(R.id.calendarEndTime);
			
			String calendarTitle = title.getText().toString();
			String calendarDescription = description.getText().toString();
			String calendarStartDate = startDate.getText().toString();
			String calendarStartTime = startTime.getText().toString();			
			String calendarEndDate = endDate.getText().toString();
			String calendarEndTime = endTime.getText().toString();
			
			startCalDate = instansiateTime(calendarStartDate,calendarStartTime);
			endCalDate = instansiateTime(calendarEndDate,calendarEndTime);
			
			values.put(CalendarContract.Events.CALENDAR_ID, calendarId);
			values.put(CalendarContract.Events.TITLE, calendarTitle);
			values.put(CalendarContract.Events.DESCRIPTION, calendarDescription);
			values.put(CalendarContract.Events.DTSTART,startCalDate.getTimeInMillis());
			values.put(CalendarContract.Events.DTEND, endCalDate.getTimeInMillis());
			values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().toString());
			
			Log.i("CalendarDetailActivity line 100, calendar title is: ", calendarTitle);
			Log.i("CalendarDetailActivity line 101,calendar Description is: ", calendarDescription);
			Log.i("CalendarDetailActivity line 102,calendar Start Date is: ", calendarStartDate);
			Log.i("CalendarDetailActivity line 103,calendar Start Time is: ", calendarStartTime);
			Log.i("CalendarDetailActivity line 104,calendar End Date is: ", calendarEndDate);
			Log.i("CalendarDetailActivity line 105,calendar End Time is: ", calendarEndTime);
			
			Uri uri = this.getContentResolver().insert(CalendarContract.Events.CONTENT_URI, values);
			eventId = ContentUris.parseId(uri);
			
			datasource.createCalendar(calendarTitle, calendarDescription, calendarStartDate, calendarEndDate, calendarStartTime, calendarEndTime);
			
			Intent intent = new Intent(getApplicationContext(),MainActivity.class);
			Toast.makeText(getApplicationContext(), "Calendar named: " +calendarTitle+" is saved", Toast.LENGTH_SHORT).show();
			startActivity(intent);
			break;
		}
	}
	
	public long getCalendarID() {
		Cursor cur = null;
		try{
			cur = this.getContentResolver().query(CalendarContract.Calendars.CONTENT_URI, null, null, null, null);
			if(cur.moveToFirst()){
				return cur.getLong(cur.getColumnIndex(CalendarContract.Calendars._ID));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(cur!=null){ 
				cur.close();
			}
		}
		return 0;
	} 

	public GregorianCalendar instansiateTime(String date, String time){
		StringTokenizer stringDate = new StringTokenizer(date,"-");
		String dateDay = stringDate.nextToken();
		String dateMonth = stringDate.nextToken();
		String dateYear = stringDate.nextToken();
		StringTokenizer stringTime = new StringTokenizer(time,":");
		String timeHour = stringTime.nextToken();
		String timeMinute = stringTime.nextToken();
		GregorianCalendar buffer = new GregorianCalendar(Integer.parseInt(dateYear),Integer.parseInt(dateMonth),Integer.parseInt(dateDay),Integer.parseInt(timeHour),Integer.parseInt(timeMinute));
		return buffer;
	}
}

