package eu.netforms.orangecash.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import eu.netforms.orangecash.R;
import eu.netforms.orangecash.data.BalanceDataSource;
import eu.netforms.orangecash.model.Info;

public class BalanceFragment extends Fragment implements PageFragment {

	private TextView balanceTextView;
	private TextView updateDateTextView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		BalanceDataSource dataSource = new BalanceDataSource(getActivity());
		Info info = dataSource.getInfo();
		if (info != null) {
			final View rootView = inflater.inflate(R.layout.balance, container,
					false);
			balanceTextView = (TextView) rootView.findViewById(R.id.balance);
			balanceTextView.setText(info.getBalance());
			updateDateTextView = (TextView) rootView
					.findViewById(R.id.updateDate);
			updateDateTextView.setText(info.getInfoDate());
			return rootView;
		} else {
			final View rootView = inflater.inflate(R.layout.empty, container,
					false);
			return rootView;
		}
	}

	public int getFragmentCodeName() {
		return R.string.tab_balance;
	}
}
