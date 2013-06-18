package eu.netforms.orangecash.model;

public class AccountData {
	
	private String cardNumber;
	private String validThruMonth;
	private String validThruYear;
	private String ccv2;
	
	public AccountData(String cardNumber, String validThruMonth,
			String validThruYear, String ccv2) {
		super();
		this.cardNumber = cardNumber;
		this.validThruMonth = validThruMonth;
		this.validThruYear = validThruYear;
		this.ccv2 = ccv2;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public String getValidThruMonth() {
		return validThruMonth;
	}
	
	public String getValidThruYear() {
		return validThruYear;
	}
	
	public String getCcv2() {
		return ccv2;
	}
	
	@Override
	public String toString() {
		return "AccountData [cardNumber=" + cardNumber + ", validThruMonth="
				+ validThruMonth + ", validThruYear=" + validThruYear
				+ ", ccv2=" + ccv2 + "]";
	}
}
