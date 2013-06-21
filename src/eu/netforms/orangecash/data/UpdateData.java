package eu.netforms.orangecash.data;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import eu.netforms.orangecash.model.AccountData;

public class UpdateData extends AsyncTask<String, Void, Boolean> {
	
	private PropertiesDataSource propertiesDataSource;
	
	private BalanceDataSource balanceDataSource;
	
	private IBalanceReader balanceReader;
	
	private Context context;

	public UpdateData(Context context) {
		super();
		this.context = context;
		this.propertiesDataSource = new PropertiesDataSource(this.context);
		this.balanceDataSource = new BalanceDataSource(this.context);
		this.balanceReader = new MockBalanceReader();
	}

	@Override
	protected Boolean doInBackground(String... params) {
		AccountData accountData = this.propertiesDataSource.getAccountData();
		try {
			balanceDataSource.updateDB(balanceReader.readBalance(accountData));
			return true;
		} catch (UpdateEcxeption e) {
			return false;
		}
	}
	
	@Override
    protected void onPostExecute(Boolean result) {
		Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show();
    }
}
