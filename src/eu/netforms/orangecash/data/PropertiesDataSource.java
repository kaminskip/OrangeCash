package eu.netforms.orangecash.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import eu.netforms.orangecash.model.AccountData;

public class PropertiesDataSource {
	
	public static final String APP_PREFERENCES = "eu.netforms.orangecash.SharedPreferences";
	
	private SharedPreferences preferences;
	
	public PropertiesDataSource(Context context) {
		super();
		this.preferences = context.getSharedPreferences(APP_PREFERENCES, Activity.MODE_PRIVATE);
	}

	public AccountData getAccountData(){
		String cardNumber = preferences.getString("preference_cart_number", "");
		String validThruMonth = preferences.getString("preference_valid_thrue_month", "");
		String validThruYear = preferences.getString("preference_valid_thrue_year", "");
		String ccv2 = preferences.getString("preference_ccv2", "");
		return new AccountData(cardNumber, validThruMonth, validThruYear, ccv2);
	}
}
