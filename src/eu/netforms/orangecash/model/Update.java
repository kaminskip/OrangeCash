package eu.netforms.orangecash.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Update {
	
	private Date updateDate;
	private Info info = new Info();
	private List<Trans> transList = new ArrayList<Trans>();
	
	public Update() {
		this.updateDate = new Date();
	}
	
	public void setInfo(Info info){
		this.info = info;
	}
	
	public void addTrans(String transDate, String transAmountCard,
			String transAmountSettlement, String transAmountOrginal,
			String transDesc, String transPlace) {
		Trans trans = new Trans();
		trans.setTransDate(transDate);
		trans.setUpdateDate(this.updateDate);
		trans.setTransAmountCard(transAmountCard);
		trans.setTransAmountSettlement(transAmountSettlement);
		trans.setTransAmountOrginal(transAmountOrginal);
		trans.setTransDesc(transDesc);
		trans.setTransPlace(transPlace);
		this.transList.add(trans);
	}
	
	public void addInfo(String infoDate, String cardNr, String cardValidThru,
			String cardType, String cardStatus, String balance, String sum){
		this.info.setUpdateDate(this.updateDate);
		this.info.setInfoDate(infoDate);
		this.info.setCardNr(cardNr);
		this.info.setCardValidThru(cardValidThru);
		this.info.setCardType(cardType);
		this.info.setCardStatus(cardStatus);
		this.info.setBalance(balance);
		this.info.setSum(sum);
	}

	public Info getInfo() {
		return info;
	}

	public List<Trans> getTransList() {
		return transList;
	}

	@Override
	public String toString() {
		return "Update [updateDate=" + updateDate + ", info=" + info
				+ ", transList=" + transList + "]";
	}
}