package eu.netforms.orangecash.view;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import eu.netforms.orangecash.R;

public class TransacionFragment extends ListFragment implements PageFragment {
	
	static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
		"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
		"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//setListAdapter(new ArrayAdapter<String>(inflater.getContext(), R.id.testText, FRUITS));
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public int getFragmentCodeName() {
		return R.string.tab_transactions;
	}
}