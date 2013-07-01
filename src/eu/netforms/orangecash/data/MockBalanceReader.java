package eu.netforms.orangecash.data;

import eu.netforms.orangecash.model.AccountData;
import eu.netforms.orangecash.model.Update;

public class MockBalanceReader implements IBalanceReader {

	@Override
	public Update readBalance(AccountData accountData) throws UpdateEcxeption {
		Update update = new Update();
		String infoDate = "29.06.2013 19:58";
		String cardNr = accountData.getCardNumber();
		String cardValidThru = accountData.getValidThruMonth() + "/" + accountData.getValidThruYear();
		String cardType = "Test";
		String cardStatus = "Test";
		String balance = "243,50 PLN";
		String sum = "2 622,50 PLN";
		update.addInfo(infoDate, cardNr, cardValidThru, cardType, cardStatus, balance, sum);
		addTrans(update);
		return update;
	}
	
	private void addTrans(Update update){
		update.addTrans("12.06.2013", "-2,50 PLN", "", "", "ZAKUP PRZY UŻYCIU KARTY W KRAJU ROLLO-CATERING", "ROLLO-CATERING");
		update.addTrans("08.06.2013", "-25,30 PLN", "-25,30 PLN", "-25,30 PLN", "ZAKUP PRZY UŻYCIU KARTY W KRAJU", "Zabka SA ");
		update.addTrans("08.06.2013", "-9,00 PLN", "-9,00 PLN", "-9,00 PLN", "ZAKUP PRZY UŻYCIU KARTY W KRAJU", "McDonalds");
		update.addTrans("07.06.2013", "100,00 PLN", "100,00 PLN", "100,00 PLN", "ZASILENIE KARTY", "");
		update.addTrans("05.06.2013", "15,00 PLN", "15,00 PLN", "15,00 PLN", "ZASILENIE KARTY", "");
		update.addTrans("04.06.2013", "-6,80 PLN", "-6,80 PLN", "-6,80 PLN", "ZAKUP PRZY UŻYCIU KARTY W KRAJU", "RESTAURACJA");
	}

}
