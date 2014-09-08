package fam.dentalclinic.controller;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import fam.dentalclinic.model.PatientsDataSource;

@SuppressLint("ValidFragment")
public class SideFragment extends DialogFragment{
	View view = getView();
	private PatientsDataSource datasource;
	String userid;
	Long id;
	
	public SideFragment(Bundle extras){
		userid=extras.getString("userid");
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		datasource = new PatientsDataSource(getActivity());
		datasource.open();
		
		id = Long.parseLong(userid);
		
		builder.setTitle("Delete");
		builder.setMessage("Delete patient?");
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				datasource.deletePatient(id);
				Toast.makeText(getActivity(), "patient is deleted", Toast.LENGTH_SHORT).show();
			}
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		return builder.create();
		
	}
}