package eu.netforms.orangecash.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import eu.netforms.orangecash.model.AccountData;

public class PropertiesDataSource {
	
	public static final String APP_PREFERENCES = "eu.netforms.orangecash.SharedPreferences";
	
	private SharedPreferences preferences;
	
	public PropertiesDataSource(Context context) {
		super();
		this.preferences = PreferenceManager
			    .getDefaultSharedPreferences(context);
	}

	public AccountData getAccountData(){
		String cardNumber = preferences.getString("preference_cart_number", "xxx");
		String validThruMonth = preferences.getString("preference_valid_thrue_month", "xxx");
		String validThruYear = preferences.getString("preference_valid_thrue_year", "xxx");
		String ccv2 = preferences.getString("preference_ccv2", "xxx");
		return new AccountData(cardNumber, validThruMonth, validThruYear, ccv2);
	}
}
