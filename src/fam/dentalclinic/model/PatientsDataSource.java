package fam.dentalclinic.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import fam.dentalclinic.src.MySQLiteHelper;

public class PatientsDataSource{
	private SQLiteDatabase database;
	private MySQLiteHelper helper;
	private String[] patientcolumns = {MySQLiteHelper.COLUMN_PATIENT_ID, MySQLiteHelper.COLUMN_PATIENT_NAME, MySQLiteHelper.COLUMN_PATIENT_NUMBER, MySQLiteHelper.COLUMN_PATIENT_ADDRESS, MySQLiteHelper.COLUMN_PATIENT_EMAIL};
	
	public PatientsDataSource(Context context){
		helper = new MySQLiteHelper(context);
	}
	
	public void open()throws SQLException {
		database = helper.getWritableDatabase();
	}
	
	public void close(){
		helper.close();
	}
	
	public Patient createPatient(String name, String number, String address, String email){
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_PATIENT_NAME, name);
		values.put(MySQLiteHelper.COLUMN_PATIENT_NUMBER, number);
		values.put(MySQLiteHelper.COLUMN_PATIENT_ADDRESS, address);
		values.put(MySQLiteHelper.COLUMN_PATIENT_EMAIL, email);
		long insertId = database.insert(MySQLiteHelper.TABLE_PATIENT, null, values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_PATIENT, patientcolumns, MySQLiteHelper.COLUMN_PATIENT_ID+"="+insertId, null, null, null, null);
		cursor.moveToFirst();
		Patient newPatient = cursorToPatients(cursor);
		cursor.close();
		return newPatient;
	}
	
	public void updatePatient(Long id, String name, String number, String address, String email){
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_PATIENT_NAME, name);
		values.put(MySQLiteHelper.COLUMN_PATIENT_NUMBER, number);
		values.put(MySQLiteHelper.COLUMN_PATIENT_ADDRESS, address);
		values.put(MySQLiteHelper.COLUMN_PATIENT_EMAIL, email);
		database.update(MySQLiteHelper.TABLE_PATIENT, values, MySQLiteHelper.COLUMN_PATIENT_ID +"="+ Long.toString(id), null);
	}
	
	/*public void deletePatient(Patient patient){
		long id = patient.getId();
		Log.i("PatientsDataSource", "Line 47");
		database.delete(MySQLiteHelper.TABLE_PATIENT, MySQLiteHelper.COLUMN_PATIENT_ID+"="+id, null);
	}*/
	
	public void deletePatient(Long id){
		database.delete(MySQLiteHelper.TABLE_PATIENT, MySQLiteHelper.COLUMN_PATIENT_ID+"="+id, null);
	}
	
	public List<Patient> getAllPatients(){
		List<Patient> patients = new ArrayList<Patient>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_PATIENT, patientcolumns, null, null, null, null, MySQLiteHelper.COLUMN_PATIENT_NAME);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Patient patient = cursorToPatients(cursor);
			patients.add(patient);
			cursor.moveToNext();
		}
		cursor.close();
		return patients;
	}
	
	public Patient getPatient(Long id) {
		Log.i("PatientsDataSource", "Line 65");
		Patient newPatient = new Patient();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_PATIENT, patientcolumns, MySQLiteHelper.COLUMN_PATIENT_ID+"="+id, null, null, null, null);
		if(cursor!=null){
			cursor.moveToFirst();
			newPatient = cursorToPatients(cursor);
		}
		else {
			Log.w("PatientsDataSource", "Line 73");
		}
		cursor.close();
		return newPatient;
	}
	
	private Patient cursorToPatients(Cursor cursor){
		Patient patient = new Patient();
		patient.setId(cursor.getLong(0));
		patient.setName(cursor.getString(1));
		patient.setNumber(cursor.getString(2));
		patient.setAddress(cursor.getString(3));
		patient.setEmail(cursor.getString(4));
		return patient;
	}
}