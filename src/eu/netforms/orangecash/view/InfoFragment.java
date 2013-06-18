package eu.netforms.orangecash.view;

import eu.netforms.orangecash.R;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InfoFragment extends PageFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.info, container, false);
		return rootView;
	}
	
	
	
	@Override
	public void onResume() {
		super.onStart();
		Log.d("INFO", getPropertiesDataSource().getAccountData().toString());
	}



	@Override
	int getFragmentCodeName() {
		return R.string.tab_info;
	}
}
