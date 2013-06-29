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

public class InfoFragment extends Fragment implements PageFragment {
	
	private TextView feedInYearTextView;
	private TextView cardNrTextView;
	private TextView cardValidThruTextView;
	private TextView cardTypeTextView;
	private TextView cardStatusTextView;
	private TextView cardInfoDateTextView;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = null;
		BalanceDataSource dataSource = new BalanceDataSource(getActivity());
		Info info = dataSource.getInfo();
		if(info != null){
			rootView = inflater.inflate(R.layout.info, container, false);
			
			feedInYearTextView = (TextView) rootView.findViewById(R.id.feedInYear);
			feedInYearTextView.setText(info.getSum());
			
			cardNrTextView = (TextView) rootView.findViewById(R.id.cardNr);
			cardNrTextView.setText(info.getCardNr());
			
			cardValidThruTextView = (TextView) rootView.findViewById(R.id.cardValidThru);
			cardValidThruTextView.setText(info.getCardValidThru());
			
			cardTypeTextView = (TextView) rootView.findViewById(R.id.cardType);
			cardTypeTextView.setText(info.getCardType());
			
			cardStatusTextView = (TextView) rootView.findViewById(R.id.cardStatus);
			cardStatusTextView.setText(info.getCardStatus());
			
			cardInfoDateTextView = (TextView) rootView.findViewById(R.id.cardInfoDate);
			cardInfoDateTextView.setText(info.getInfoDate());
			
		} else {
			rootView = inflater.inflate(R.layout.empty, container, false);
		}
		return rootView;
	}

	public int getFragmentCodeName() {
		return R.string.tab_info;
	}
}
