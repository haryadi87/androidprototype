package fam.dentalclinic;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import fam.dentalclinic.src.MainTabListener;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		Tab tab = actionBar.newTab().setText("Patients").setTabListener(new MainTabListener<PatientActivity>(this,"patient",PatientActivity.class));
		actionBar.addTab(tab);
		
		tab = actionBar.newTab().setText("Calendar").setTabListener(new MainTabListener<CalendarActivity>(this,"calendar",CalendarActivity.class));
		actionBar.addTab(tab);
		
		tab = actionBar.newTab().setText("Email").setTabListener(new MainTabListener<EmailActivity>(this,"expense",EmailActivity.class));
		actionBar.addTab(tab);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
	}
}
