package eu.netforms.orangecash.view;

import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import eu.netforms.orangecash.R;
import eu.netforms.orangecash.model.Trans;

public class TransactionAdapter extends ArrayAdapter<Trans> {

	private final List<Trans> list;
	private final Activity context;
	
	private TextView transAmountTextView;
	private TextView transDateTextView;
	private TextView transDescTextView;
	
	private ImageView transChargeImageView;

	public TransactionAdapter(Activity context, List<Trans> list) {
		super(context, R.layout.transaction_row, list);
		this.context = context;
		this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		
		LayoutInflater inflator = context.getLayoutInflater();
		view = inflator.inflate(R.layout.transaction_row, null);
		
		transAmountTextView = (TextView) view.findViewById(R.id.transAmount);
		transDateTextView = (TextView) view.findViewById(R.id.transDate);
		transDescTextView = (TextView) view.findViewById(R.id.transDesc);
		 
		transChargeImageView = (ImageView) view.findViewById(R.id.transImage); 
		
		Trans trans = list.get(position);
		Log.d("TRANS_ADAPTER", "Trans" + trans);
		
		transDateTextView.setText(trans.getTransDate());
		transAmountTextView.setText(trans.getTransAmountCard());
		
		String desc = trans.getTransDesc();
		if(trans.getTransPlace() != null && trans.getTransPlace().length()>0){
			desc = desc + "\n" + trans.getTransPlace();
		}
		transDescTextView.setText(desc);
		
		if(trans.isCharge()){
			transChargeImageView.setImageResource(R.drawable.shopping_bag);
			transAmountTextView.setTextColor(context.getResources().getColor(R.color.grey));
		} else {
			transChargeImageView.setImageResource(R.drawable.load_download);
			transAmountTextView.setTextColor(context.getResources().getColor(R.color.icon_blue));
		}
		
		return view;
	}
}
