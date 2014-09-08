package fam.dentalclinic.controller;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import fam.dentalclinic.R;

@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
	String status;
	
	public DatePickerFragment(Bundle bundle){
		status = bundle.getString("Date");
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	@Override
	public void onDateSet(DatePicker arg0, int year, int month, int date) {
		// TODO Auto-generated method stub
		if(status.equalsIgnoreCase("Start")){
			Button button = (Button)getActivity().findViewById(R.id.calendarStartDate);
			button.setText(date + "-" + (month) + "-" + year);
		}
		else{
			Button button = (Button)getActivity().findViewById(R.id.calendarEndDate);
			button.setText(date + "-" + (month+1) + "-" + year);
		}
	}
	
}