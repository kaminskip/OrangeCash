package eu.netforms.orangecash.view;

import eu.netforms.orangecash.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BalanceFragment extends PageFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.balance, container, false);
		return rootView;
	}
	
	@Override
	int getFragmentCodeName() {
		return R.string.tab_balance;
	}
}
