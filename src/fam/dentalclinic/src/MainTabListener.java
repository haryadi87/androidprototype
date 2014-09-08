package fam.dentalclinic.src;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

public class MainTabListener<T extends Fragment> implements TabListener {
	private Activity mActivity;
	private Fragment mFragment;
	private String mTag;
	private Class<T> mClass;

	public MainTabListener(Activity activity, String tag, Class<T> cls){
		mActivity = activity;
		mTag = tag;
		mClass = cls;
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if(mFragment==null){
			mFragment = Fragment.instantiate(mActivity, mClass.getName());
			ft.add(android.R.id.content,mFragment,mTag);
		}else {
			ft.attach(mFragment);
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if(mFragment!= null){
			ft.detach(mFragment);
		}
	}

}
