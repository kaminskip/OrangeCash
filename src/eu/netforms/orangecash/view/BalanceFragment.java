package eu.netforms.orangecash.view;

import eu.netforms.orangecash.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BalanceFragment extends Fragment implements PageFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.balance, container, false);
		return rootView;
	}
	
	public int getFragmentCodeName() {
		return R.string.tab_balance;
	}
}
