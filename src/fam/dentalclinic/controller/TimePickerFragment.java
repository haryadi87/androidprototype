package fam.dentalclinic.controller;

import java.util.Calendar;

import fam.dentalclinic.R;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;

@SuppressLint("ValidFragment")
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
	String status;
	
	public TimePickerFragment (Bundle bundle) {
		status = bundle.getString("Time");
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		final Calendar c = Calendar.getInstance();
		int hour= c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		
		return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// TODO Auto-generated method stub
		if(status.equalsIgnoreCase("Start")){
			Button button = (Button)getActivity().findViewById(R.id.calendarStartTime);
			button.setText(hourOfDay + ":" + minute);
		}
		else{
			Button button = (Button)getActivity().findViewById(R.id.calendarEndTime);
			button.setText(hourOfDay + ":" + minute);
		}
	}
	
}