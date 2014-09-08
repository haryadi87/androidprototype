package fam.dentalclinic;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import fam.dentalclinic.view.CalendarDetailActivity;

public class CalendarActivity extends Fragment{
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		view = inflater.inflate(R.layout.calendar, container,false);
		addListenerOnButton();
		return view;
	}
	
	private void addListenerOnButton(){
		Log.i("CalendarActivity", "Line 24");
		Button add = (Button)view.findViewById(R.id.AddCalendar);
		add.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent(getActivity().getApplicationContext(),CalendarDetailActivity.class);
				startActivity(intent);
			}
		});
	}
}