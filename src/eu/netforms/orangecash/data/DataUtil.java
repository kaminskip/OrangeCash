package eu.netforms.orangecash.data;

import java.util.Calendar;
import java.util.Date;

public class DataUtil {

	public static Date getDatefromMilliseconds(long milliseconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(milliseconds);
		return cal.getTime();
	}

}
