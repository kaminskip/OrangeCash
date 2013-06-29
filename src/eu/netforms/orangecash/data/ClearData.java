package eu.netforms.orangecash.data;

import android.os.AsyncTask;
import android.widget.Toast;
import eu.netforms.orangecash.R;
import eu.netforms.orangecash.view.MainActivity;

public class ClearData extends AsyncTask<String, Void, String> {
	
	private BalanceDataSource balanceDataSource;
	
	private MainActivity context;

	public ClearData(MainActivity context) {
		super();
		this.context = context;
		this.balanceDataSource = new BalanceDataSource(this.context);
	}

	@Override
	protected String doInBackground(String... params) {
		balanceDataSource.clearData();
		return context.getResources().getText(R.string.action_clear_done).toString();
	}
	
	@Override
    protected void onPostExecute(String result) {
		this.context.refresh();
		Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show();
    }
}
