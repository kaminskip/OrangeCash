package eu.netforms.orangecash.view;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import eu.netforms.orangecash.R;
import eu.netforms.orangecash.model.Trans;

public class TransacionFragment extends ListFragment implements PageFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TransactionAdapter transactionAdapter  = new TransactionAdapter(getActivity(), getTrans());
		setListAdapter(transactionAdapter);
	}

	private List<Trans> getTrans() {
		List<Trans> list = new ArrayList<Trans>();
	    //list.add(new Trans());
	    //list.add(new Trans());
	    //list.add(new Trans());
	    return list;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.transactions, container, false);
	}

	public int getFragmentCodeName() {
		return R.string.tab_transactions;
	}
}