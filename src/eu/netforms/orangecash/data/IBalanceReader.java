package eu.netforms.orangecash.data;

import eu.netforms.orangecash.model.AccountData;
import eu.netforms.orangecash.model.Update;

public interface IBalanceReader {
	Update readBalance(AccountData accountData) throws UpdateEcxeption;
}
