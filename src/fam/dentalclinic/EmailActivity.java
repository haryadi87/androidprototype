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

public class EmailActivity extends Fragment {
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		view = inflater.inflate(R.layout.email, container,false);
		addListenerOnButton();
		return view;
	}

	private void addListenerOnButton() {
		// TODO Auto-generated method stub
		Log.i("Email activity", "Line 21");
		Button sendEmail = (Button)view.findViewById(R.id.sendEmail);
		sendEmail.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent emailIntent = new Intent(Intent.ACTION_SEND);
				
				String subject = "Hi!";
				String body= "hello from Android...";
				
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
				emailIntent.putExtra(Intent.EXTRA_TEXT, body);
				emailIntent.setType("message/rfc822");
				
				startActivity(emailIntent);
			}
		});
	}
}