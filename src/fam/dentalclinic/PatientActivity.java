package fam.dentalclinic;

import java.util.List;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import fam.dentalclinic.controller.SideFragment;
import fam.dentalclinic.model.Patient;
import fam.dentalclinic.model.PatientsDataSource;
import fam.dentalclinic.view.PatientDetailActivity;
import fam.dentalclinic.view.PatientListAdapter;

public class PatientActivity extends ListFragment{
	private PatientsDataSource datasource;
	private ArrayAdapter<Patient> adapter;
	private List<Patient> values;
	private Bundle extras;
	View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		view = inflater.inflate(R.layout.patient, container,false);
		datasource = new PatientsDataSource(getActivity());
		datasource.open();
		
		values = datasource.getAllPatients();
		adapter = new PatientListAdapter(getActivity(), R.layout.patient, values);
		setListAdapter(adapter);
		
		addListenerOnButton();
		addListenerOnText(adapter);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		final ListView lv = getListView();
		lv.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
				extras = new Bundle();
				extras.putString("userid", lv.getAdapter().getItem(position).toString());
				SideFragment newSideFragment = new SideFragment(extras);
				newSideFragment.show(getFragmentManager(), "sideFragment");
				adapter.notifyDataSetChanged();
				return true;
			}
		});
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		Intent intent = new Intent(getActivity().getApplicationContext(), PatientDetailActivity.class);
		extras = new Bundle();
		Patient item = (Patient)getListAdapter().getItem(position);
		intent.putExtra("Patient", item.getId());
		extras.putString("Status", "getPatient");
		intent.putExtras(extras);
		startActivity(intent);
		//Toast.makeText(getActivity(), "Selected: " + item.toString(), Toast.LENGTH_SHORT).show();
	}
	
	private void addListenerOnButton(){
		Button add = (Button)view.findViewById(R.id.AddPatient);
		add.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Log.i("PatientActivity", "Line 63");
				extras = new Bundle();
				extras.putString("Status", "addPatient");
				Intent intent =  new Intent(getActivity().getApplicationContext(),PatientDetailActivity.class);
				intent.putExtras(extras);
				startActivity(intent);
			}
		});
	}
	
	private void addListenerOnText(ArrayAdapter<Patient> adapter ){
		EditText inputSearch = (EditText)view.findViewById(R.id.searchPatient);
		inputSearch.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				PatientActivity.this.adapter.getFilter().filter(s);
			}
		});
	}
}