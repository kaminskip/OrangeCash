package eu.netforms.orangecash.data;

import java.util.Date;

import eu.netforms.orangecash.model.AccountData;
import eu.netforms.orangecash.model.Update;

public class MockBalanceReader implements IBalanceReader {

	@Override
	public Update readBalance(AccountData accountData) throws UpdateEcxeption {
		Update update = new Update();
		String infoDate = new Date().toString();
		String cardNr = accountData.getCardNumber();
		String cardValidThru = accountData.getValidThruMonth() + "/" + accountData.getValidThruYear();
		String cardType = "Test";
		String cardStatus = "Test";
		Double balance = 1200.33d;
		Double sum = 24340.33d;
		update.addInfo(infoDate, cardNr, cardValidThru, cardType, cardStatus, balance, sum);
		addTrans(update);
		return update;
	}
	
	private void addTrans(Update update){
		update.addTrans("12.06.2013", "-2,50PLN", "", "", "ZAKUP PRZY UŻYCIU KARTY W KRAJU ROLLO-CATERING AND LONG  AND LONG  AND LONG  AND LONG  AND LONG  AND LONG  AND LONG  AND LONG  AND LONG  AND LONG", "ROLLO-CATERING");
		update.addTrans("08.06.2013", "-25,30PLN", "-25,30PLN", "-25,30PLN", "ZAKUP PRZY UŻYCIU KARTY W KRAJU", "Zabka SA ");
		update.addTrans("08.06.2013", "-9,00PLN", "-9,00PLN", "-9,00PLN", "ZAKUP PRZY UŻYCIU KARTY W KRAJU", "McDonalds");
		update.addTrans("07.06.2013", "100,00PLN", "100,00PLN", "100,00PLN", "ZASILENIE KARTY", "");
		update.addTrans("05.06.2013", "15,00PLN", "15,00PLN", "15,00PLN", "ZASILENIE KARTY", "");
		update.addTrans("04.06.2013", "-6,80PLN", "-6,80PLN", "-6,80PLN", "ZAKUP PRZY UŻYCIU KARTY W KRAJU", "RESTAURACJA");
	}

}
