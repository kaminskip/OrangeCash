package eu.netforms.orangecash.data;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import eu.netforms.orangecash.data.UpdateEcxeption.Code;
import eu.netforms.orangecash.model.AccountData;
import eu.netforms.orangecash.model.Info;
import eu.netforms.orangecash.model.Update;

public class OrangeBalanceReader implements IBalanceReader {
	
	private static final String IBRE_HOST = "https://www.ibre.com.pl";
	private static final String IBRE_LOGIN = "/mt/fragments/orangeCash/orangeCashLogin.jsp";
	private static final String IBRE_POST = "/mt/submitOrangeCashCardLogin";
		
	private static final String PARAM_TIMESTAMP = "TIMESTAMP_TOKEN_PARAM";
	private static final String PARAM_CARD_NO = "card_no";
	private static final String PARAM_CARD_MONTH = "selectMonth";
	private static final String PARAM_CARD_YEAR = "selectYear";
	private static final String PARAM_CVC2 = "cvv2";
	private static final String COOKIE_PARAM = "JSESSIONID";
	private static final int TIMEOUT = 1000 * 60;//60 seconds
	
	private String jsessionid;
	private String timestamp;
	private Update update;
	

	@Override
	public Update readBalance(AccountData accountData) throws UpdateEcxeption {
		validateAccountData(accountData);
		timestamp = null;
		jsessionid = null;
		update = null;
		readLoginPage();
		String html = getData(accountData);
		parseData(html);
		return update;
	}
	
	private void validateAccountData(AccountData accountData) throws UpdateEcxeption {
		if(accountData == null){
			throw new UpdateEcxeption(Code.ACCOUNT_DATA, "Brak danych autoryzacyjnych");
		}
		if(accountData.getCardNumber() == null || accountData.getCardNumber().trim().length() == 0){
			throw new UpdateEcxeption(Code.ACCOUNT_DATA, "Brak numeru karty");
		}
		if(accountData.getValidThruMonth() == null || accountData.getValidThruMonth().trim().length() == 0){
			throw new UpdateEcxeption(Code.ACCOUNT_DATA, "Brak miesiąca ważności");
		}
		if(accountData.getValidThruYear() == null || accountData.getValidThruYear().trim().length() == 0){
			throw new UpdateEcxeption(Code.ACCOUNT_DATA, "Brak roku ważności");
		}
		if(accountData.getCcv2() == null || accountData.getCcv2().trim().length() == 0){
			throw new UpdateEcxeption(Code.ACCOUNT_DATA, "Brak kodu CVV2");
		}
	}

	private void readLoginPage() throws UpdateEcxeption{
		String url = IBRE_HOST + IBRE_LOGIN;
		Document doc;
		try {
			Connection.Response res = Jsoup.connect(url).timeout(TIMEOUT).method(Method.GET).execute();
			jsessionid = res.cookie(COOKIE_PARAM);
			if(jsessionid == null || jsessionid.length() == 0){
				throw new UpdateEcxeption(Code.LOGIN_READ_PAGE, "Can not read jsessionid");
			}
			doc = res.parse();
			Elements elements = doc.select("input[name=TIMESTAMP_TOKEN_PARAM]");
			if(elements.size() == 1) {
				this.timestamp = elements.get(0).attr("value");
				if(timestamp == null || timestamp.length() == 0){
					throw new UpdateEcxeption(Code.LOGIN_READ_PAGE, "TIMESTAMP_TOKEN_PARAM is empty");
				}
			} else {
				throw new UpdateEcxeption(Code.LOGIN_READ_PAGE, "Can not find input TIMESTAMP_TOKEN_PARAM");
			}
		} catch (IOException e) {
			throw new UpdateEcxeption(Code.LOGIN_READ_PAGE, e);
		}
	}
	
	private void parseData(String html) throws UpdateEcxeption{
		this.update = new Update();
		try{
			Elements infos = Jsoup.parse(html).select(".prepaidCardDetailsTableLeft .prepaidRight");
			Info info = new Info();
			info.setUpdateDate(new Date());
			if(infos != null && infos.size() == 5){
				String cartNr = infos.get(0).text();
				info.setCardNr(cartNr);
				info.setCardValidThru(infos.get(1).text());
				info.setCardType(infos.get(2).text());
				info.setCardStatus(infos.get(3).text());
				info.setInfoDate(infos.get(4).text());
			}
			Elements amount = Jsoup.parse(html).select(".prepaidTransactionFundsAmount");
			if(amount != null && amount.size() == 1){
				info.setBalance(amount.get(0).text() + " PLN");
			}
			Elements sum = Jsoup.parse(html).select(".prepaidTransactionChargeAmount");
			if(sum != null && sum.size() == 1){
				info.setSum(sum.get(0).text() + " PLN");
			}
			addTransactions(html);
			update.setInfo(info);
		} catch (Exception e){
			throw new UpdateEcxeption(Code.PARSE, e);
		}
	}
	
	private String getData(AccountData accountData) throws UpdateEcxeption{
		String url = IBRE_HOST + IBRE_POST;
		Document doc;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put(PARAM_CARD_NO, accountData.getCardNumber());
			params.put(PARAM_CARD_YEAR, accountData.getValidThruYear());
			params.put(PARAM_CARD_MONTH, accountData.getValidThruMonth());
			params.put(PARAM_CVC2, accountData.getCcv2());
			params.put(PARAM_TIMESTAMP, timestamp);
			doc = Jsoup.connect(url).timeout(TIMEOUT).cookie(COOKIE_PARAM, jsessionid).data(params).post();
			Elements error = doc.select("div.loginErrorMsg");
			if(error.size() == 1){
				throw new UpdateEcxeption(Code.LOGIN_POST, error.text());
			}
			return doc.html();
		} catch (IOException e) {
			throw new UpdateEcxeption(Code.LOGIN_POST, e);
		}
	}
	
	private void addTransactions(String html){
		html = html
				.replace("\t", "")
				.replace("<br />", "")
				.replace("<div class=\"separator\" style=\"display: inline;margin-left:5px;\"></div>", "")
				.replace("\n", "");
		Elements trans = Jsoup.parse(html).select("tbody .prepaidTransactionsListLight, tbody .prepaidTransactionsListDark");
		
		if(trans != null && trans.size() > 0){
			for (Element transRow : trans) {
				addTransaction(transRow);
			}
		}
	}
	
	private void addTransaction(Element html){
		String transDate = html.getAllElements().get(1).text();
		String transAmountCard = html.getAllElements().get(2).text();
		String transAmountSettlement = html.getAllElements().get(3).text();
		String transAmountOrginal = html.getAllElements().get(4).text();
		String transDesc = html.getAllElements().get(5).text();
		String transPlace = html.getAllElements().get(6).text();
		update.addTrans(transDate, transAmountCard, transAmountSettlement, transAmountOrginal, transDesc, transPlace);
	}

}