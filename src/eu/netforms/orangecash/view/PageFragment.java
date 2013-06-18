package eu.netforms.orangecash.view;

import eu.netforms.orangecash.data.PropertiesDataSource;
import android.support.v4.app.Fragment;

public abstract class PageFragment extends Fragment {
	
	abstract int getFragmentCodeName();
	
	PropertiesDataSource getPropertiesDataSource(){
		MainActivity activity = (MainActivity) getActivity();
		return activity.propertiesDataSource;
	}
}
