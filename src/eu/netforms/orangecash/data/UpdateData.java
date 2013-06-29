package eu.netforms.orangecash.data;

import android.os.AsyncTask;
import android.widget.Toast;
import eu.netforms.orangecash.R;
import eu.netforms.orangecash.model.AccountData;
import eu.netforms.orangecash.view.MainActivity;

public class UpdateData extends AsyncTask<String, Void, String> {
	
	private PropertiesDataSource propertiesDataSource;
	
	private BalanceDataSource balanceDataSource;
	
	private IBalanceReader balanceReader;
	
	private MainActivity context;

	public UpdateData(MainActivity context) {
		super();
		this.context = context;
		this.propertiesDataSource = new PropertiesDataSource(this.context);
		this.balanceDataSource = new BalanceDataSource(this.context);
		this.balanceReader = new MockBalanceReader();
	}

	@Override
	protected String doInBackground(String... params) {
		AccountData accountData = this.propertiesDataSource.getAccountData();
		try {
			balanceDataSource.updateDB(balanceReader.readBalance(accountData));
			return context.getResources().getText(R.string.action_update_done).toString();
		} catch (UpdateEcxeption e) {
			return context.getResources().getText(R.string.action_update_error).toString();
		}
	}
	
	@Override
    protected void onPostExecute(String result) {
		this.context.refresh();
		Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show();
    }
}
