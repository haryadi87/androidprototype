package fam.dentalclinic.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fam.dentalclinic.R;
import fam.dentalclinic.model.Patient;

public class PatientListAdapter extends ArrayAdapter<Patient> {
	private List<Patient> patients;
	
	public PatientListAdapter(Context context, int textViewResourceId, List<Patient> objects) {
		super(context, textViewResourceId, objects);
		this.patients = objects;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(view==null){
			LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.patient_list, null);
		} 
		
		Patient newPatient  = patients.get(position);
		
		TextView name = (TextView)view.findViewById(R.id.patient_list_name);
		TextView id = (TextView)view.findViewById(R.id.patient_list_id);
		
		name.setText(newPatient.getName());
		id.setText(Long.toString(newPatient.getId()));
		
		return view;
	}
	
}