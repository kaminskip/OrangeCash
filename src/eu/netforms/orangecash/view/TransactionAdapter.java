package eu.netforms.orangecash.view;

import java.util.List;

import eu.netforms.orangecash.R;
import eu.netforms.orangecash.model.Trans;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TransactionAdapter extends ArrayAdapter<Trans> {

	private final List<Trans> list;
	private final Activity context;

	public TransactionAdapter(Activity context, List<Trans> list) {
		super(context, R.layout.transaction_row, list);
		this.context = context;
		this.list = list;
	}

	static class ViewHolder {
		protected TextView text;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		
		LayoutInflater inflator = context.getLayoutInflater();
		view = inflator.inflate(R.layout.transaction_row, null);
		
		final ViewHolder viewHolder = new ViewHolder();
		viewHolder.text = (TextView) view.findViewById(R.id.label);
		view.setTag(viewHolder);

		ViewHolder holder = (ViewHolder) view.getTag();
		holder.text.setText(list.get(position).getTransAmountCard());
		return view;
	}
}
