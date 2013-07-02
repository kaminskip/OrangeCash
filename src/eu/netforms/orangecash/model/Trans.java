package eu.netforms.orangecash.model;

import java.util.Date;

import android.content.ContentValues;
import android.database.Cursor;
import eu.netforms.orangecash.data.DataUtil;

public class Trans {
	
	public static final String TABLE_TRANS = "trans";
	
	public static final String COLUMN_TRANS_ID = "_id";
	public static final String COLUMN_TRANS_UPDATE_DATE = "update_date";
	public static final String COLUMN_TRANS_DATE = "trans_date";
	public static final String COLUMN_TRANS_AMOUNT_CARD = "trans_amount_card";
	public static final String COLUMN_TRANS_AMOUNT_SETTLEMENT = "trans_amount_settlement";
	public static final String COLUMN_TRANS_AMOUNT_ORGINAL = "trans_amount_orginal";
	public static final String COLUMN_TRANS_DESC = "trans_desc";
	public static final String COLUMN_TRANS_PLACE = "trans_place";
	
	public static final String[] ALL_COLUMNS = { COLUMN_TRANS_ID,
			COLUMN_TRANS_UPDATE_DATE, COLUMN_TRANS_DATE,
			COLUMN_TRANS_AMOUNT_CARD, COLUMN_TRANS_AMOUNT_SETTLEMENT,
			COLUMN_TRANS_AMOUNT_ORGINAL, COLUMN_TRANS_DESC, COLUMN_TRANS_PLACE };

	private Long id;
	private Date updateDate;
	private String transDate;
	private String transAmountCard;
	private String transAmountSettlement;
	private String transAmountOrginal;
	private String transDesc;
	private String transPlace;

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

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getTransAmountCard() {
		return transAmountCard;
	}

	public void setTransAmountCard(String transAmountCard) {
		this.transAmountCard = transAmountCard;
	}

	public String getTransAmountSettlement() {
		return transAmountSettlement;
	}

	public void setTransAmountSettlement(String transAmountSettlement) {
		this.transAmountSettlement = transAmountSettlement;
	}

	public String getTransAmountOrginal() {
		return transAmountOrginal;
	}

	public void setTransAmountOrginal(String transAmountOrginal) {
		this.transAmountOrginal = transAmountOrginal;
	}

	public String getTransDesc() {
		return transDesc;
	}

	public void setTransDesc(String transDesc) {
		this.transDesc = transDesc;
	}

	public String getTransPlace() {
		return transPlace;
	}

	public void setTransPlace(String transPlace) {
		this.transPlace = transPlace;
	}
	
	public static Trans cursorToTrans(Cursor cursor) {
		Trans trans = new Trans();
		trans.setId(cursor.getLong(0));
		trans.setUpdateDate(DataUtil.getDatefromMilliseconds(cursor.getLong(1)));
		trans.setTransDate(cursor.getString(2));
		trans.setTransAmountCard(cursor.getString(3));
		trans.setTransAmountOrginal(cursor.getString(4));
		trans.setTransAmountSettlement(cursor.getString(5));
		trans.setTransDesc(cursor.getString(6));
		trans.setTransPlace(cursor.getString(7));
		return trans;
	}
	
	public boolean isCharge() {
		if(this.getTransAmountCard() != null && this.getTransAmountCard().contains("-")){
			return true;
		} else {
			return false;
		}
	}
	
	public ContentValues getAllContentValues() {
		ContentValues values = new ContentValues();
		values.put(Trans.COLUMN_TRANS_ID, getId());
		values.put(Trans.COLUMN_TRANS_UPDATE_DATE, getUpdateDate().getTime());
		values.put(Trans.COLUMN_TRANS_DATE, getTransDate());
		values.put(Trans.COLUMN_TRANS_AMOUNT_CARD, getTransAmountCard());
		values.put(Trans.COLUMN_TRANS_AMOUNT_SETTLEMENT,
				getTransAmountSettlement());
		values.put(Trans.COLUMN_TRANS_AMOUNT_ORGINAL, getTransAmountOrginal());
		values.put(Trans.COLUMN_TRANS_DESC, getTransDesc());
		values.put(Trans.COLUMN_TRANS_PLACE, getTransPlace());
		return values;
	}

	@Override
	public String toString() {
		return "Trans [id=" + id + ", updateDate=" + updateDate
				+ ", transDate=" + transDate + ", transAmountCard="
				+ transAmountCard + ", transAmountSettlement="
				+ transAmountSettlement + ", transAmountOrginal="
				+ transAmountOrginal + ", transDesc=" + transDesc
				+ ", transPlace=" + transPlace + "]";
	}
}
