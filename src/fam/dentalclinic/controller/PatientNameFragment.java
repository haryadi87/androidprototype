package fam.dentalclinic.controller;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import fam.dentalclinic.model.Patient;
import fam.dentalclinic.model.PatientsDataSource;

public class PatientNameFragment extends DialogFragment{
	View view = getView();
	private PatientsDataSource dataSource ;
	private List<Patient> values;
	private List<String> patients;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		final ArrayList<String> mSelectedItems = new ArrayList<String>();
		dataSource = new PatientsDataSource(getActivity());
		patients = new ArrayList<String>();
		dataSource.open();
		
		values = dataSource.getAllPatients();
		for(int i=0;i<values.size()-1;i++){
				String buffer = values.get(i).toString();
				patients.add(buffer.toString());
			}
		
		final String[] participant = patients.toArray(new String[patients.size()]);
		
		builder.setTitle("Attendee");
		builder.setMultiChoiceItems(participant, null, new DialogInterface.OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mSelectedItems.add(participant[which]);
				} else if(mSelectedItems.contains(participant[which])){
					mSelectedItems.remove(participant[which]);
				}
			}
		});
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//EditText calendarParticipant = (EditText)getActivity().findViewById(R.id.calendarParticipant);
				//calendarParticipant.setText(mSelectedItems.toString());
			}
		});
		dataSource.close();
		return builder.create();
	}
}