package fam.dentalclinic.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import fam.dentalclinic.MainActivity;
import fam.dentalclinic.R;
import fam.dentalclinic.model.Patient;
import fam.dentalclinic.model.PatientsDataSource;

public class PatientDetailActivity extends Activity{
	private PatientsDataSource datasource;
	private Patient dataPatient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient_detail);
		datasource = new PatientsDataSource(getApplicationContext());
		datasource.open();
				
		Bundle bundle = getIntent().getExtras();
		String status = bundle.getString("Status");
		
		if(status.equalsIgnoreCase("addPatient")) {
			Log.i("PatientDetailActivity", "Line 32");
			addListenerOnButton();
		}
		else {
			Log.i("PatientDetailActivity", "Line 36");
			addListenerOnView(bundle);
		}
	}
	
	protected void addListenerOnButton() {
		final EditText patientName = (EditText)findViewById(R.id.patientName);
		final EditText patientNumber = (EditText)findViewById(R.id.patientNumber);
		final EditText patientAddress = (EditText)findViewById(R.id.patientAddress);
		final EditText patientEmail = (EditText)findViewById(R.id.patientEmail);
		Button submit = (Button)findViewById(R.id.patientSubmit);
		submit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				String name = patientName.getText().toString();
				String number = patientNumber.getText().toString();
				String address = patientAddress.getText().toString();
				String email = patientEmail.getText().toString();
				datasource.createPatient(name,number,address,email);
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				Toast.makeText(getApplicationContext(), "Patient name: "+name+" is saved", Toast.LENGTH_LONG).show();
				startActivity(intent);
			}
		});
	}
	
	protected void addListenerOnView(Bundle bundle){
		final long id = bundle.getLong("Patient");
		
		dataPatient = datasource.getPatient(id);
		final EditText patientName = (EditText)findViewById(R.id.patientName);
		final EditText patientNumber = (EditText)findViewById(R.id.patientNumber);
		final EditText patientAddress = (EditText)findViewById(R.id.patientAddress);
		final EditText patientEmail = (EditText)findViewById(R.id.patientEmail);
		patientName.setText(dataPatient.getName());
		patientNumber.setText(dataPatient.getNumber());
		patientAddress.setText(dataPatient.getAddress());
		patientEmail.setText(dataPatient.getEmail());
		
		Button submit = (Button)findViewById(R.id.patientSubmit);
		submit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				String name = patientName.getText().toString();
				String number = patientNumber.getText().toString();
				String address = patientAddress.getText().toString();
				String email = patientEmail.getText().toString();
				datasource.updatePatient(id, name, number, address, email);
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				Toast.makeText(getApplicationContext(), "Patient name: "+name+" is updated", Toast.LENGTH_LONG).show();
				startActivity(intent);
			}
		});
	}
}