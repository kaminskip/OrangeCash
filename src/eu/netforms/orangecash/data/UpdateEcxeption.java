package eu.netforms.orangecash.data;


public class UpdateEcxeption extends Exception {

	private static final long serialVersionUID = 8594465945646210413L;

	public enum Code {
		LOGIN_READ_PAGE(100),
		LOGIN_POST(101),
		ACCOUNT_DATA(102),
		PARSE(103);
		
		private int code;

		private Code(int code) {
			this.code = code;
		}

		public int getCode() {
			return this.code;
		}
	}

	private Code errorCode;

	public UpdateEcxeption(Code code, Throwable e) {
		super(e);
		this.errorCode = code;
	}
	
	public UpdateEcxeption(Code code, String message) {
		super(message);
		this.errorCode = code;
	}

	public Code getErrorCode() {
		return errorCode;
	}
}
