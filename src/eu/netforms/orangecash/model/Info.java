package eu.netforms.orangecash.model;

import java.util.Date;

import android.content.ContentValues;
import android.database.Cursor;
import eu.netforms.orangecash.data.DataUtil;

public class Info {

	public static final String TABLE_INFO = "info";

	public static final String COLUMN_INFO_ID = "_id";
	public static final String COLUMN_INFO_UPDATE_DATE = "update_date";
	public static final String COLUMN_INFO_DATE = "info_date";
	public static final String COLUMN_INFO_CARD_NR = "card_nr";
	public static final String COLUMN_INFO_CARD_VALID_THRU = "card_valid_thru";
	public static final String COLUMN_INFO_CARD_TYPE = "card_type";
	public static final String COLUMN_INFO_CARD_STATUS = "card_status";
	public static final String COLUMN_INFO_BALANCE = "balance";
	public static final String COLUMN_INFO_SUM = "sum";

	public static final String[] ALL_COLUMNS = { COLUMN_INFO_ID,
			COLUMN_INFO_UPDATE_DATE, COLUMN_INFO_DATE, COLUMN_INFO_CARD_NR,
			COLUMN_INFO_CARD_VALID_THRU, COLUMN_INFO_CARD_TYPE,
			COLUMN_INFO_CARD_STATUS, COLUMN_INFO_BALANCE, COLUMN_INFO_SUM };

	private Long id;
	private Date updateDate;
	private String infoDate;
	private String cardNr;
	private String cardValidThru;
	private String cardType;
	private String cardStatus;
	private String balance;
	private String sum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getInfoDate() {
		return infoDate;
	}

	public void setInfoDate(String infoDate) {
		this.infoDate = infoDate;
	}

	public String getCardNr() {
		return cardNr;
	}

	public void setCardNr(String cardNr) {
		this.cardNr = cardNr;
	}

	public String getCardValidThru() {
		return cardValidThru;
	}

	public void setCardValidThru(String cardValidThru) {
		this.cardValidThru = cardValidThru;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public static Info cursorToInfo(Cursor cursor) {
		Info info = new Info();
		info.setId(cursor.getLong(0));
		info.setUpdateDate(DataUtil.getDatefromMilliseconds(cursor.getLong(1)));
		info.setInfoDate(cursor.getString(2));
		info.setCardNr(cursor.getString(3));
		info.setCardValidThru(cursor.getString(4));
		info.setCardType(cursor.getString(5));
		info.setCardStatus(cursor.getString(6));
		info.setBalance(cursor.getString(7));
		info.setSum(cursor.getString(8));
		return info;
	}
	
	public ContentValues getAllContentValues(){
		ContentValues values = new ContentValues();
		values.put(Info.COLUMN_INFO_ID, getId());
		values.put(Info.COLUMN_INFO_UPDATE_DATE, getUpdateDate().getTime());
		values.put(Info.COLUMN_INFO_DATE, getInfoDate());
		values.put(Info.COLUMN_INFO_CARD_NR, getCardNr());
		values.put(Info.COLUMN_INFO_CARD_VALID_THRU, getCardValidThru());
		values.put(Info.COLUMN_INFO_CARD_TYPE, getCardType());
		values.put(Info.COLUMN_INFO_CARD_STATUS, getCardStatus());
		values.put(Info.COLUMN_INFO_BALANCE, getBalance());
		values.put(Info.COLUMN_INFO_SUM, getSum());
		return values;
	}

	@Override
	public String toString() {
		return "Info [id=" + id + ", updateDate=" + updateDate + ", infoDate="
				+ infoDate + ", cardNr=" + cardNr + ", cardValidThru="
				+ cardValidThru + ", cardType=" + cardType + ", cardStatus="
				+ cardStatus + ", balance=" + balance + ", sum=" + sum + "]";
	}
}
