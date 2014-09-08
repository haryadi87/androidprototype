package fam.dentalclinic.model;

public class Patient {
	private static Patient _instance = null;
	
	private long patient_id;
	private String patient_name;
	private String patient_address;
	private String patient_number;
	private String patient_email;
	
	protected Patient(){
		
	}
	
	public static Patient getInstance(){
		if(_instance == null)
			_instance = new Patient();
		return _instance;
	}
	
	public long getId() {
		return patient_id;
	}
	
	public void setId(long id){
		this.patient_id = id;
	}
	
	public String getName() {
		return patient_name;
	}
	
	public void setName(String name) {
		this.patient_name = name;
	}
	
	public String getAddress(){
		return patient_address;
	}
	
	public void setAddress(String address) {
		this.patient_address = address;
	}
	
	public String getNumber(){
		return patient_number;
	}
	
	public void setNumber(String number) {
		this.patient_number = number;
	}
	
	public String getEmail() {
		return patient_email;
	}
	
	public void setEmail(String email) {
		this.patient_email = email;
	}
	
	@Override
	public String toString(){
		return Long.toString(patient_id);
	}
}