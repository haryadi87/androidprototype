package fam.dentalclinic.model;

public class Calendar {
	private static Calendar _instance = null;
	
	private long calendar_id;
	private String calendar_title;
	private String calendar_description;
	private String calendar_starttime;
	private String calendar_endtime;
	private String calendar_startdate;
	private String calendar_enddate;
	
	protected Calendar(){
		
	}
	
	public static Calendar getInstance(){
		if(_instance == null)
			_instance = new Calendar();
		return _instance;
	}
	
	public long getId(){
		return calendar_id;
	}
	
	public void setId(Long id){
		this.calendar_id = id;
	}
	
	public String getTitle(){
		return calendar_title;
	}
	
	public void setTitle(String title){
		this.calendar_title = title;
	}
	
	public String getDescription(){
		return calendar_description;
	}
	
	public void setDescription(String description) {
		this.calendar_description = description;
	}
	
	public String getStartTime(){
		return calendar_starttime;
	}
	
	public void setStartTime(String starttime) {
		this.calendar_starttime = starttime;
	}
	
	public String getEndTime(){
		return calendar_endtime;
	}
	
	public void setEndTime(String endtime){
		this.calendar_endtime = endtime;
	}
	
	public String getStartDate(){
		return calendar_startdate;
	}
	
	public void setStartDate(String startdate){
		this.calendar_startdate = startdate;
	}
	
	public String getEndDate() {
		return calendar_enddate;
	}
	
	public void setEndDate(String enddate){
		this.calendar_enddate = enddate;
	}
	
	@Override
	public String toString(){
		return calendar_title;
	}
}