package eu.netforms.orangecash.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import eu.netforms.orangecash.R;

public class InfoFragment extends Fragment implements PageFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.info, container, false);
		return rootView;
	}

	public int getFragmentCodeName() {
		return R.string.tab_info;
	}
}
